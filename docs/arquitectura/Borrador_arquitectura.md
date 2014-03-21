# Arquitectura del proyecto

Párrafo introductorio


* [Play!](http://www.playframework.com/) framework basado en java [^play]
* Basado en la API de Twitter, tanto REST como Streaming
* [Akka](http://akka.io/) para manejo de hilos 
* Amazon, con una instancia de C1 (instancias optimizadas para informática) por horas, o sino una micro sin pararla [http://aws.amazon.com/es/ec2/instance-types/]. (Calculadora de precios de amazon)[http://calculator.s3.amazonaws.com/calc5.html]
* Base de datos en MongoDB, y (Mongoid como gema que abstraiga el driver)[http://mongoid.org/en/mongoid/index.html]
* (d3 para la visualización)[http://d3js.org/]
* (Hadoop para el procesado de datos)[http://hadoop.apache.org/]

[^play]: [Play!](http://www.playframework.com/) puede basarse tanto en Scala como en Java 

![Esquemático de la arquitectura](https://docs.google.com/drawings/d/1BWEYdXQHGMJgDdOXOXhsPMQN0cmBlwLstOy5w0vbXzg/pub?w=1010&h=724)
***arquitectura del proyecto***

***

Otros párrafos redactados pero inconexos

Para este proyecto vamos a usar todo el contenido posible que podamos sacar de Twitter. Tenemos dos formas de consumir información de Twitter: su [Rest API](https://dev.twitter.com/docs/api/1.1) y su [Streaming API](https://dev.twitter.com/docs/streaming-apis).

### Rest API

Por definición, una Rest API (1) permite interactuar con un sistema de forma agnóstica a la arquitectura de dicho sistema, mediante peticiones web. En el caso de la Twitter Rest API, desde ella podemos realizar todas las acciones a las que tenemos acceso desde el cliente web o móvil. De todas estas posibilidades, estaremos interesados en las siguientes:  

* Búsqueda de tweets por término (2). Búsqueda por palabras clave, que nos devuelven tweets de usuarios desconocidos. Estos resultados pueden ser filtrados por geolocalización (presente o no, o área), idioma del tweet o fecha.  
* Búsqueda de tweets por usuario (3). Búsqueda por usuario, ya sea mediante su Twitter ID o mediante su nombre de usuario (menos idóneo, ya que es modificable por el usuario). En esta búsqueda podemos excluir aquellos tweets que sean respuestas a otros tweets, aquellos que sean retweets, así como aquellos que no cumplan restricciones de tiempo u orden (útiles para avanzar por la lista de tweets). Este método tiene un máximo de 3200 tweets, alcanzables gracias a la paginación. Tweets anteriores a ese número son imposibles de conseguir mediante la Twitter Rest API.
* Datos del usuario (4). Detalles públicos de un usuario definido por su Twitter ID o por su nombre. De esta llamada, lo datos más interesantes nos parecen su nombre completo, su biografía, el lenguaje que utiliza, su localización y su huso horario.
* Listas de followers (5)(6) y friends (7)(8). Dado un usuario (definido por su Twitter ID o por su nombre de usuario), podemos obtener su lista de followers y su lista de friends, muy útil para poder dibujar las relaciones dentro de Twitter, y analizar la composición y evolución de la misma. En la Twitter Rest API tenemos varias llamadas para cada lista, además de sus diferentes parámetros, para iterar a través de ellas, o conseguir más detalles de cada relación.
* Listas a las que pertenece un usuario (9). Dado un usuario, definido por su Twitter ID o su nombre, gracias a esta llamada podemos obtener el número de listas a las que ha sido añadido, y cuáles. Nos parece muy interesante el usar ese número como un posible índice de relevancia del usuario. Además, al disponer del nombre de esas listas, se pueden asignar al usuario una serie de palabras clave, útiles para perfilarlo.

FALTA COMENTAR EL SISTEMA DE RESTRICCIONES DE LLAMADAS Y PERMISOS

### Streaming API

Twitter también dispone de lo que se denomina Streaming API. En este modo de acceso, al hacer la petición inicial, Twitter abre una conexión entre nosotros y su servidor, y va enviando por ahí tweets que cumplan los filtros dados al inicio. Es importante notar que mediante la Streaming API solo     recibiremos tweets creados posteriormente a nuestra petición inicial, y que iremos recibiendo tweets hasta que decidamos cerrar la conexión. Debido a esto, es el método adecuado para tratar con grandes cantidades de tweets en tiempo real.

También es importante tener en cuenta que tenemos una limitación a la hora de utilizar la Streaming API: sean cuales sean los filtros que pongamos, nunca recibiremos más del 1% del volumen total de todo Twitter en ese momento. Es por esto que es importante tener claro qué vamos a buscar, de forma que no sea demasiado genérico y nos topemos con el límite.

***

Otro párrafo

### Opciones de visualización

* http://www.leafletjs.com. Proyecto open source. Usa Open Street Map.
* http://www.mapbox.com. Basado en Leaflet, es de pago pero el plan gratuito nos valdría (3000 visualizaciones al mes).
* https://developers.google.com/maps.

### Ejemplo: http://users.humboldt.edu/mstephens/hate/hate_map.html

Mapa de "odio" de Estados Unidos, generados mediante tweets geolocalizados. Permite filtrar por categoría o por palabra despectiva, así que todo hace indicar que lo que han hecho es monitorizar cada opción un tiempo razonable mediante la Streaming API, y luego han representado los datos con la API de Google Maps.
