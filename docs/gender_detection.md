# Detección de género

Si bien Twitter provee mediante su API de diversos datos personales de sus usuarios, como foto de perfil, nombre, descripción o idioma, no ocurre lo mismo con el género de éste. Por ello, y porque no fuimos capaces de encontrar nada parecido, nos propusimos crear nuestro propio detector de género partiendo de los datos de los que disponíamos.

Tras un primer análisis, constatamos que las cuentas de Twitter se clasificaban no sólo en personas masculinas y personas femeninas, sino que también había un cierto porcentaje de cuentas impersonales, tales como las cuentas de empresas o marcas. Esta cuentas decidimos obviarlas, asumiendo que cuando nuestro algoritmo no fuese capaz de identificar al usuario como masculino o femenino, estaría identificando correctamente a ese usuario como impersonal. Esto fue debido a que los datos disponibles en la API de Twitter más prometedores eran el nombre y la descripción, y ninguno de los dos aportaba luz sobre las cuentas impersonales.

En rasgos generales, los pasos que hemos seguido para incorporar la detección de género de los usuarios de Twitter a nuestro proyecto han sido los siguientes:
1. Hemos obtenido una muestra representativa de usuarios de Twitter y sus datos asociados: nombre, biografía, lenguaje y situación geográfica.
2. Hemos implementado tres algoritmos diferentes para detectar el género, usando diferentes técnicas.
3. Hemos implementado un módulo de pruebas dentro de Play! Framework, en el que podemos conectarnos a Twitter y extraer una muestra de usuarios con sus respectivos datos. Además, una vez implementados los algoritmos, este módulo nos permite correr dichos algoritmos sobre una muestra de usuarios a elegir, por lo que podemos evaluar la eficacia de cada una de las implementaciones sobre datos de usuarios reales.
4. Hemos marcado un pequeño porcentaje de la muestra para poder evaluar la eficacia del algoritmo. Definimos como usuario marcado todo aquel usuario del que disponemos de los datos necesarios por los diferentes algoritmos, así como un género asignado manualmente, y con certeza absoluta, por una persona.
5. Hemos evaluado la precisión de los algoritmos, comparando las predicciones hechas por las diferentes versiones de los algoritmos sobre diferentes muestras, y el género que había sido marcado a mano para cada usuario.
6. Hemos añadido esta nueva funcionalidad a nuestro proyecto, usando la implementación que se ha demostrado más eficaz en las pruebas anteriores.


## Obtención de la muestra

Para poder sacar ideas, reconocer patrones, y probar los algoritmos desarrollados, nuestro primer paso fue obtener una muestra de usuarios de Twitter. Definimos como muestra de usuarios a una colección de datos de usuarios reales que tuitean en español, extraídos de Twitter. Además, hemos intentado que la muestra fuese lo más homogénea posible, extrayendo tweets a diversas horas para tener datos tanto de España como de Hispanoamérica, y de un tamaño suficientemente representativo.

Para tomar la muestra hemos aprovechado que en el proyecto ya teníamos implementada la conexión a Twitter Streaming. Anteriormente, ya éramos capaces de recibir tweets en tiempo real con las características de palabras, lenguaje o situación geográfica que quisiéramos, por lo que para obtener la muestra lo lanzamos con un filtro de lenguaje en español, para así recibir sólo aquellos tweets escritos en español en ese momento.

La API de Twitter está diseñada de forma que cualquier tweet que te manden siempre contenga información básica del usuario que ha creado ese tweet, o lo ha retuiteado, por lo que cada vez que nos llegaba un tweet nosotros podíamos almacenar en nuestra base de datos los datos referentes al usuario que nos interesaban. De esta forma, conseguimos tener una muestra de 50.000 usuarios, con su nombre, su biografía, su lenguaje, su imagen de perfil, y la localización geográfica del tweet por el cual le detectamos (imprescindible para segmentar a los usuarios por país).


## Implementación de algoritmos

Una vez obtenida la muestra, nos centramos en implementar varias versiones del algoritmo de detección de género. Estos algoritmos tendrían todos como objetivo ser capaces de extraer el género de un usuario cualquiera de Twitter que usase el español como su idioma en la red, partiendo de la información de usuario proporcionada por la API de Twitter.

Estudiando los datos disponibles, determinamos que los campos más prometedores para esta tarea eran primero el nombre elegido por el usuario, y luego su biografía. En el caso del nombre, es importante destacar que no es el mismo que el nombre de usuario en Twitter: todos los usuarios de Twitter empiezan por "@" y no permiten espacios, mientras que los nombres permiten espacios e incluso caracteres no alfanuméricos.

El nombre del usuario es prometedor por razones obvias: es un campo obligatorio para los usuarios, y la gran mayoría de usuarios de Twitter usa su nombre real. Y quiénes no, suelen usar pequeñas variaciones sobre éste, como diminutivos o derivaciones. Esto hizo que nuestros primeros intentos se centrasen en detectar el género mediante la comparación del nombre con diccionarios de nombres en español.

Por otro lado, el usuario tiene la opción de escribir una breve biografía para su perfil, y una gran mayoría de ellos tiene escrita una. En esta biografía notamos que debido a la restricción de tamaño, son muy abundantes aquellas que consisten en una concatenación de adjetivos con género, como por ejemplo "Madre. Profesora. Salmantina." o "Enamorado de mi trabajo". Esto nos hizo pensar que aquellos usuarios que no habíamos podido detectar gracias a su nombre, podríamos detectar su género mediante el reconocimiento de ciertas palabras, en su mayoría los adjetivos con género de los que hablábamos antes.

### Algoritmo A

Lo primero de todo, creamos dos diccionarios de nombres por género partiendo de los datos del INE (Instituto Nacional de Estadística), tomando los dos mil nombres más comunes en español de cada género. Los nombres son guardados igual que nos los provee el INE: en minúsculas y sin tildes.

En esta primera versión, el algoritmo solo usaba estos diccionarios, obteniendo posibles nombres del nombre usado por el usuario, y buscando dichos candidatos en los diccionarios. Para obtener estos candidatos a nombres primero realizamos una tarea que denominamos normalización sobre todo el texto del nombre, que consta de los siguientes pasos:
* Paso a minúsculas.
* Substitución de tildes por su correspondiente sin tilde. Ejemplo: María por Maria.
* Substitución de caracteres que la gente usa como vocales por su correspondiente. Ejemplo: Cαrmεn por Carmen.
* Normalización de todos los caracteres que no sean ASCII.
* Eliminación de todos los caracteres no alfabéticos.

Una vez normalizado el nombre, se divide en partes usando como delimitador cualquier carácter en blanco. Estas partes, almacenadas en un array, son buscadas una por una en los diferentes diccionarios de nombres. En caso de encontrar esa parte en el diccionario, el algoritmo acaba y se le asigna el género del diccionario, y en caso contrario, sigue buscando más partes del nombre.

A continuación, si el algoritmo no ha sido capaz de asignar un género al usuario, se toman estas partes y se combinan en parejas de partes adyacentes. Por ejemplo, de "Ana María Matute" saldrían las parejas "Ana María" y "María Matute". Con estas parejas se repite de nuevo la parte de búsqueda en diccionarios, con el objetivo de buscar en ellos nombres compuestos que podrían no haber sido detectados al estar en partes separadas antes.

Finalmente, si no se detecta ningún género en ninguno de los candidatos, el propio algoritmo busca y elimina vocales que aparezcan consecutivas, y repite el proceso anterior, con los mismos diccionarios. Este paso fue necesario porque un porcentaje significativo de los usuarios de Twitter alteran sus nombres añadiendo letras, por ejemplo, "Anaaa".

### Algoritmo B

Para esta segunda versión, quisimos aprovechar el trabajo hecho en la parte de normalización y creación de diccionarios, pero intentando un nuevo enfoque: en vez de obtener candidatos y buscarlos en los diccionarios, intentar lo contrario: iterar sobre los diccionarios y buscar en el nombre del usuario. Como más adelante se puede ver en los resultados, este enfoque finalmente resultó ser erróneo porque si bien aumentaba muchísimo el porcentaje de detecciones, también aumentaba mucho el porcentaje de nombres que detectaba como ambos géneros. Esto es debido a nombres como Adriana, que en esta implementación sería detectado como hombre al buscar desde los diccionarios Adrián y Adriana.


### Algoritmo C

Esta versión fue fruto de que notamos que la gente pone como nombre de Twitter modificaciones de su nombre real y aunque la normalización estaba siendo efectiva, hacía falta algo más. Por ese motivo, construimos un analizador de la muestra, que cada vez que no éramos capaces de detectar el género de un candidato a nombre lo guardaba en una lista automáticamente, y al acabar el análisis de toda la muestra nos mostraba esta lista ordenada por apariciones. De esta forma, fuimos capaces de crear cuatro nuevos diccionarios adicionales para analizar los nombres: uno con nombres que no estaban en la lista del INE (Mari, Manolo), y otro con palabras que denotan género (princesa, rey), cada uno con una versión por género.

Por otro lado, usando una modificación del anterior analizados, obtuvimos una lista de las palabras más frecuentes en las biografías de los usuarios, de forma que pudiésemos crear otros dos diccionarios específicos para la biografía. Estos diccionarios quedaron compuestos de palabras como "orgullosa" o "actor" y el proceso de detección usado fue el mismo que con el nombre: normalización del texto de la biografía, obtención de candidatos, y búsqueda en los diccionarios.

## Evaluación de los algoritmos

### Objetivo

Una vez desarrolladas tres algoritmo diferentes para la detección de género, nos propusimos averiguar cuál era el que presentaba mejor rendimiento bajo unas condiciones de funcionamiento reales.

### Metodología

El proceso que seguimos para la evaluación de rendimiento consistió en obtener una muestra de datos, clasificar el género de esta muestra a mano, ejecutar los tres algoritmos sobre esta muestra y finalmente de los resultados obtenidos, decidir cuál es el algoritmo que mejor se adapta a nuestras necesidades.

Fue muy importante asegurarnos de que la muestra de datos tomada era una muestra adecuada: nos aseguramos de tomar una muestra diferente a la usada durante el desarrollo de los algoritmos para evitar que los datos fuesen adulterados. Además, esta muestra fue tomada a diferentes horas para asegurar una buena homogeneidad de usuarios españoles y latinoamericanos. Finalmente, la detección manual del género se hizo sobre una porción de la muestra que consideramos suficiente, 1.200 usuarios.

Una vez marcados los datos, evaluamos los algoritmos ejecutándolos sobre los usuarios a los  que ya habíamos asignado un género manualmente, y así poder extraer datos exactos de su rendimiento. Para la evaluación de los algoritmos hemos usado métricas de Recuperación de Información (Christopher D. Manning, Prabhakar Raghavan and Hinrich Schütze, Introduction to Information Retrieval, Cambridge University Press. 2008.), en concreto Precission and Recall. Para calcular dichas métricas hemos definido los siguientes casos posibles en la ejecución:
 * True positive: el usuario tenía género marcado, y el algoritmo lo ha detectado correctamente.
 * True negative: el usuario no tenía género marcado, y el algoritmo no ha detectado ninguno.
 * False positive: el usuario tenía género marcado, y el algoritmo lo ha detectado erróneamente, o el usuario no tenía género y el algoritmo ha detectado alguno.
 * False negative: el usuario tenía género marcado y el algoritmo no ha detectado ninguno.

### Experimentación

Posteriormente, creamos un pequeño sistema de marcado de imágenes en Ruby on Rails, en el cual se te mostraba un usuario de Twitter, con su nombre, su biografía y su foto, y cuatro opciones para marcar su género: hombre, mujer, desconocido, y otro. La razón de estas cuatro categorías es porque en principio diferenciamos entre los usuarios de Twitter de los que no podíamos sacar información con los datos disponibles y los usuarios que sí podíamos saber que no tenían género (este es el caso de las cuentas de empresas, por ejemplo). Más adelante nos dimos cuenta de que tener dos categorías para los usuarios sin género no nos aportaba casi nada, ya que nos hemos centrado en las cuentas de personas, y además dificultaba la evaluación del rendimiento de los algoritmos, por lo que redujimos estas dos categorías a una sola. (GJD: Lía contar lo que hicisteis inicialmente y luego lo que usasteis. Dadlo la vuelta y contad lo que usasteis explicando, si queréis, los motivos)

Finalmente marcamos 1.200 cars de forma manual, usando este sistema de marcado. Estas caras fueron marcadas a partir de una base de datos de usuarios de Twitter nueva, extraída a diferentes horas para intentar que fuese lo más homogénea posible. La razón de que sea una nueva es que la primera se usó para desarrollar los algoritmos, extrayendo candidatos a marcadores de género que fueron añadidos manualmente, por lo que evaluar los algoritmos sobre esa misma base de datos daría resultados adulterados.

Es importante mencionar que de estos 1.200 usuarios marcados, había 300 que contenían información sobre su ubicación en el momento de escribir el tweet por el que Twitter nos envió sus datos. Esta información geolocalizada nos permitió saber si el usuario era español o latinoamericano, con lo que pudimos evaular los algoritmos sobre diferentes bases de usuarios, confirmando así que el rendimiento mejoraba cuando solo eran considerados usuarios españoles, que era una de nuestras hipótesis.

### Resultados

Partiendo de la muestra marcada a mano, hemos evaluado el rendimiento de los tres algoritmos, definiendo de la siguiente forma cada uno de los casos:
 * True positive: el usuario tenía género marcado, y el algoritmo lo ha detectado correctamente.
 * True negative: el usuario no tenía género marcado, y el algoritmo no ha detectado ninguno.
 * False positive: el usuario tenía género marcado, y el algoritmo lo ha detectado erróneamente, o el usuario no tenía género y el algoritmo ha detectado alguno.
 * False negative: el usuario tenía género marcado y el algoritmo no ha detectado ninguno.
 
 Precission: porcentaje de predicciones positivas realizadas correctamente. Fórmula: (True Positives) / (True Positives + False Positives)
 Recall: porcentaje de todos los casos positivos totales detectados. Fórmula: (True Positives) / (True Positives + False Negatives)

### Algoritmo A:

              Sin geolocalización       Con geolocalización en España       Sin filtrar
Recall:               47,66%                       50,31%                   51,44%
Precision:            88,41%                       94,25%                   90,36%


### Algoritmo B:

              Sin geolocalización       Con geolocalización en España       Sin filtrar
Recall:              37,90%                        40,38%                   41,64%
Precision:           63,28%                        78,75%                   74,62%


### Algoritmo C:

              Sin geolocalización       Con geolocalización en España       Sin filtrar
Recall:              66,41%                        72,96%                   68,98%
Precision:           86,73%                        88,55%                   87,37%


Como se puede ver en los resultados, el mejor algoritmo es la versión C, el que incluía diccionarios de marcadores de género para el nombre y la descripción: este algoritmo tiene un recall muy alto, lo que significa que tiene una tasa muy alta de detección, y una precisión que si bien es menor que la del algoritmo A, sigue siendo alta, lo que nos asegura que a esa tasa de detección alta le acompaña una tasa de falsos positivos muy baja.

Por otro lado, también se puede ver cómo queda demostrado todas las versiones mejoran sus datos al restringir los usuarios analizados a aquellos usuarios que están geolocalizados en España, debido a que el diccionario de nombres usado fue extraído del INE (Instituto Nacional de Estadística). Esto hace más adecuada si cabe la elección de usar este algoritmo en el proyecto, ya que nos estamos centrando en tweets geolocalizados en tiempo real en España o una zona de ella.

