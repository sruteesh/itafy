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

Esta versión fue fruto de que llamó nuestra atención que la gente pone como nombre de Twitter modificaciones de su nombre real, y aunque la normalización estaba siendo efectiva, hacía falta algo más. Por ese motivo, construimos un analizador de la muestra, que cada vez que no éramos capaces de detectar el género de un candidato a nombre lo guardaba, y al acabar el análisis de toda la muestra nos mostraba una lista de estos ordenada por apariciones. De esta forma, fuimos capaces de crear dos nuevos diccionarios adicionales para cada género: uno con nombres que no estaban en la lista del INE (Mari, Manolo), y otro con palabras que denotan género (princesa, rey).

Por otro lado, usando el analizar de la muestra, obtuvimos una lista de las palabras más frecuentes en las biografías de los usuarios, de forma que pudiésemos crear diccionarios específicos para la biografía, y añadirlo al algoritmo. Estos diccionarios quedaron compuestos de palabras como orgullosa, o actor, y el proceso de detección usado fue el mismo que con el nombre: normalización del texto de la biografía, obtención de candidatos, y búsqueda en los diccionarios.


## Marcado de una muestra


## Resultados


## Integración en el proyecto
