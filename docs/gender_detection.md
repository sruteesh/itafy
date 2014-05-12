# Detección de género

En rasgos generales, los pasos que hemos seguido para incorporar la detección de género de los usuarios de Twitter a nuestro proyecto han sido los siguientes:
  1. Hemos obtenido una muestra representativa de usuarios de Twitter y sus datos asociados: nombre, biografía, lenguage y situación geográfica.
2. Hemos implementado tres algoritmos diferentes para detectar el género, usando diferentes técnicas.
3. Hemos implementado un módulo de pruebas dentro de Play! Framework, en el que podemos coger una muestra y ver el resultado de los algoritmos.
4. Hemos marcado un pequeño porcentaje de la muestra para poder evaluar la eficacia del algoritmo.
5. Hemos evaluado la eficacia de los algoritmos, usando la muestra marcada.
6. Hemos añadido esta nueva funcionalidad a nuestro proyecto, usando la implementación más exitosa.


## Obtención de la muestra

Para tomar la muestra hemos aprovechado que en el proyecto ya teníamos implementada la conexión a Twitter Streaming. Anteriormente, ya eramos capaces de recibir tweets en tiempo real con las características de palabras, lenguaje o situación geográfica que quisieramos, por lo que para obtener la muestra lo lanzamos con un filtro de lenguaje en español. Este proceso lo repetimos a diferentes horas para asegurar una mayor representatividad de la muestra.

La API de Twitter está diseñada de forma que cualquier tweet que te manden siempre contenga información básica del usuario que ha creado ese tweet, o lo ha retuiteado, por lo que cada vez que nos llegaba un tweet nosotros podíamos almacenar en nuestra base de datos los datos referentes al usuario que nos interesaban. De esta forma, conseguimos tener una muestra de 25.000 usuarios, con su nombre, su biografía, su lenguaje, su imagen de perfil, y la localización geográfica del tweet por el cual le detectamos (imprescindible para segmentar a los usuarios por país).


## Implementación de algoritmos

Una vez obtenida la muestra, nos centramos en implementar varias versiones del algoritmo de detección de género. Partíamos de la idea básica de que podíamos obtener resultados interesantes usando cruzando el nombre del usuario con diccionarios de nombres, y que en la biografía del usuario podría haber palabras clave que nos ayudasen a identificar aquellos que los diccionarios no fuesen capaces.

### Primer algoritmo

Lo primero de todo, creamos dos diccionarios de nombres por género partiendo de los datos del INE (Instituto Nacional de Estadística), tomando los dos mil nombres más comunes de cada género. Los nombres son guardados igual que nos los provee el INE: en minúsculas y sin tildes.

En esta primera versión, el algoritmo solo usaba estos diccionarios, obteniendo posibles nombres del nombre usado por el usuario, y buscando dichos candidatos en los diccionarios. Para obtener estos candidatos a nombres primero realizamos una tarea que denominamos normalización sobre todo el texto del nombre, que consta de los siguientes pasos:
 * Paso a minúsculas.
* Substitución de tildes por su correspondiente sin tilde. Ejemplo: María por Maria.
* Substitución de carácteres que la gente usa como vocales por su correspondiente. Ejemplo: Cαrmεn por Carmen.
* Normalización de todos los carácteres que no sean ASCII.
* Eliminación de todos los carácteres no alfabéticos.

Una vez normalizado, el nombre es dividido en palabras usando cualquier tipo de carácter en blanco como delimitador, y a continuación se busca uno por uno en los diccionarios, así como cualquier pareja de dos candidatos, para cubrir de esta forma los nombres compuestos.

Finalmente, si no se detecta ningún género cada uno de los candidatos se revisa para que esta vez eliminar todas las vocales que aparezcan repetidas consecutivamente, y se vuelve a buscar en el diccionario. Este paso fue necesario porque un porcentaje significativo de los usuarios de Twitter usan alteran sus nombres añadiendo letras, quedando nombres como "Anaaa".

### Segundo algoritmo

Para esta segunda versión, quisimos aprovechar el trabajo hecho en la parte de normalización y creación de diccionarios, pero intentando un nuevo enfoque: en vez de obtener candidatos y buscarlos en los diccionarios, intentar lo contrario: iterar sobre los diccionarios y buscar en el nombre del usuario. Como más adelante se puede ver en los resultados, este enfoque finalmente resultó ser erróneo, porque si bien aumentaba muchísimo el porcentaje de detecciones, también aumentaba mucho el porcentaje de nombres que detectaba como ambos géneros. Esto es debido a nombres como Adriana, que en esta implementación sería detectado como hombre al buscar desde los diccionarios Adrián y Adriana.


### Tercer algoritmo

Esta versión fue fruto de que notamos que la gente pone como nombre de Twitter modificaciones de su nombre real, y aunque la normalización estaba siendo efectiva, hacía falta algo más. Por ese motivo, construimos un analizador de la muestra, que cada vez que no éramos capaces de detectar el género de un candidato a nombre lo guardaba, y al acabar el análisis de toda la muestra nos mostraba una lista de estos ordenada por apariciones. De esta forma, fuimos capaces de crear dos nuevos diccionarios adicionales para cada género: uno con nombres que no estaban en la lista del INE (Mari, Manolo), y otro con palabras que denotan género (princesa, rey).

Por otro lado, usando el analizar de la muestra, obtuvimos una lista de las palabras más frecuentes en las biografías de los usuarios, de forma que pudiésemos crear diccionarios específicos para la biografía, y añadirlo al algoritmo. Estos diccionarios quedaron compuestos de palabras como orgullosa, o actor, y el proceso de detección usado fue el mismo que con el nombre: normalización del texto de la biografía, obtención de candidatos, y búsqueda en los diccionarios.


## Marcado de una muestra

Posteriormente, creamos un pequeño sistema de marcado de imágenes en Ruby on Rails, en el cual se te mostraba un usuario de Twitter, con su nombre, su biografía y su foto, y cuatro opciones para marcar su género: hombre, mujer, desconocido, y otro. La razón de estas cuatro categorías es porque en principio diferenciamos entre los usuarios de Twitter de los que no podíamos sacar información con los datos disponibles y los usuarios que sí podíamos saber que no tenían género (este es el caso de las cuentas de empresas, por ejemplo). Más adelante nos dimos cuenta de que tener dos categorías para los usuarios sin género no nos aportaba casi nada, ya que nos hemos centrado en las cuentas de personas, y además dificultaba la evaluación del rendimiento de los algoritmos, por lo que redujimos estas dos categorías a una sola.

Finalmente marcamos mil doscientas caras de forma manual, usando este sistema de marcado. Estas caras fueron marcadas a partir de una base de datos de usuarios de Twitter nueva, extraida a diferentes horas para intentar que fuese lo más homogénea posible. La razón de que sea una nueva es que la primera se usó para desarrollar los algoritmos, extrayendo candidatos a marcadores de género que fueron añadidos manualmente, por lo que evaluar los algoritmos sobre esa misma base de datos daría resultados adulterados.


## Resultados

Partiendo de la muestra marcada a mano, hemos evaluado el rendimiento de los tres algoritmos, definimiendo de la siguiente forma cada uno de los casos:
 * True positive: el usuario tenía género marcado, y el algoritmo lo ha detectado correctamente.
 * True negative: el usuario no tenía género marcado, y el algoritmo no ha detectado ninguno.
 * False positive: el usuario tenía género marcado, y el algoritmo lo ha detectado erróneamente, o el usuario no tenía género y el algoritmo ha detectado alguno.
 * False negative: el usuario tenía género marcado y el algoritmo no ha detectado ninguno.

### Algoritmo A:

              Sin geolocalización       Con geolocalización en España       Todos
Accuracy:             62,50%                       57,00%                   60,60%
Recall:               47,66%                       50,31%                   51,44%
Precision:            88,41%                       94,25%                   90,36%


### Algoritmo B:

              Sin geolocalización       Con geolocalización en España       Todos
Accuracy:            49,00%                        45,00%                   48,80%
Recall:              37,90%                        40,38%                   41,64%
Precision:           63,28%                        78,75%                   74,62%


### Algoritmo C:

              Sin geolocalización       Con geolocalización en España       Todos
Accuracy:            72,00%                        71,00%                   70,40%
Recall:              66,41%                        72,96%                   68,98%
Precision:           86,73%                        88,55%                   87,37%


## Conclusiones

Como se puede ver en los resultados, el mejor algoritmo es la versión C, el que incluía diccionarios de marcadores de género para el nombre y la descripción.

Por otro lado, también se puede ver cómo todas las versiones mejoran sus datos al restringir los usuarios analizados a aquellos usuarios que están geolocalizados en España, debido a que el diccionario de nombres usado fue extraído del INE (Instituto Nacional de Estadística). Esto hace más adecuada si cabe la elección de usar este algoritmo en el proyecto, ya que nos estamos centrando en tweets geolocalizados en tiempo real en España o una zona de ella.

## Integración en el proyecto
