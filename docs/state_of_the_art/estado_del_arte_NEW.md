# Estado del arte

Contenido, social, localización, temporal

## Contenido
Para este proyecto vamos a usar todo el contenido posible que podamos sacar de Twitter. Tenemos dos formas de consumir información de Twitter: su [Rest API](https://dev.twitter.com/docs/api/1.1) y su [Streaming API](https://dev.twitter.com/docs/streaming-apis).

### Rest API
Por definición, una Rest API (1) permite interactuar con un sistema de forma agnóstica a la arquitectura de dicho sistema, mediante peticiones web. En el caso de la Twitter Rest API, desde ella podemos realizar todas las acciones a las que tenemos acceso desde el cliente web o móvil. De todas estas posibilidades, estaremos interesados en las siguientes:  

* Búsqueda de tweets por término (2). Búsqueda por palabras clave, que nos devuelven tweets de usuarios desconocidos. Estos resultados pueden ser filtrados por geolocalización (presente o no, o área), idioma del tweet o fecha.  
* Búsqueda de tweets por usuario (3). Búsqueda por usuario, ya sea mediante su Twitter ID o mediante su nombre de usuario (menos idóneo, ya que es modificable por el usuario). En esta búsqueda podemos excluir aquellos tweets que sean respuestas a otros tweets, aquellos que sean retweets, así como aquellos que no cumplan restricciones de tiempo u orden (útiles para avanzar por la lista de tweets). Este método tiene un máximo de 3200 tweets, alcanzables gracias a la paginación. Tweets anteriores a ese número son imposibles de conseguir mediante la Twitter Rest API.
* Datos del usuario (4). Detalles públicos de un usuario definido por su Twitter ID o por su nombre. De esta llamada, lo datos más interesantes nos parecen su nombre completo, su biografía, el lenguaje que utiliza, su localización y su huso horario.
* Listas de followers (5)(6) y friends (7)(8). Dado un usuario (definido por su Twitter ID o por su nombre de usuario), podemos obtener su lista de followers y su lista de friends, muy útil para poder dibujar las relaciones dentro de Twitter, y analizar la composición y evolución de la misma. En la Twitter Rest API tenemos varias llamadas para cada lista, además de sus diferentes parámetros, para iterar a través de ellas, o conseguir más detalles de cada relación.
* Listas a las que pertenece un usuario (9). Dado un usuario, definido por su Twitter ID o su nombre, gracias a esta llamada podemos obtener el número de listas a las que ha sido añadido, y cuáles. Nos parece muy interesante el usar ese número como un posible índice de relevancia del usuario. Además, al disponer del nombre de esas listas, se pueden asignar al usuario una serie de palabras clave, útiles para perfilarlo.

## Social
scm
## Localización

Análisis temporal de la evolución de una marca, término, etc.

## Temporal










Webs de análisis de Twitter:


   * https://bluefinlabs.com/

   * http://secondsync.com/


Servicios que proveen tweets:

   * topsy.com

   * http://simplymeasured.com/ (muy interesantes los free reports, ahí hay muy buenas ideas)


http://www.tweriod.com/ Best time to tweet
 
 
1 http://en.wikipedia.org/wiki/Representational_state_transfer
2 https://dev.twitter.com/docs/api/1.1/get/search/tweets
3 https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline
4 https://dev.twitter.com/docs/api/1.1/get/users/show
5 https://dev.twitter.com/docs/api/1.1/get/followers/ids
6 https://dev.twitter.com/docs/api/1.1/get/followers/list
7 https://dev.twitter.com/docs/api/1.1/get/friends/ids
8 https://dev.twitter.com/docs/api/1.1/get/friends/list
9 https://dev.twitter.com/docs/api/1.1/get/lists/memberships
