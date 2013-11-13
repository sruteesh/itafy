API
===

1: Entrada = usuario
--------------------

1. Últimos 20 tweets que contienen una mención al usuario @screen_name 

> [GET statuses/mentions_timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/mentions_timeline)  
> Modelo: Tweet

2. Los últimos tweets posteados por el usuario

> [GET statuses/user_timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline)    
> Modelo: Tweet  

3. Time line de un usuario

> [GET statuses/home_timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/home_timeline)    
> Modelo: Tweets

4. Últimos tweets del usuario que han sido retweeteados.

> [GET statuses/retweets_of_me](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets_of_me)  
> Modelo: Tweets  

5. 20 últimos mensajes directos enviados al usuario

> [GET direct_messages](https://dev.twitter.com/docs/api/1.1/get/direct_messages)  
> Modelo: Tweets  

6. 20 últimos mensajes directos enviados por el usuario 

> [GET direct_messages/sent](https://dev.twitter.com/docs/api/1.1/get/direct_messages/sent)  
> Modelo: Tweets  

7. Colección de usuarios de los que el usuario no quiere recibir retweets

> [GET friendships/no_retweets/ids](https://dev.twitter.com/docs/api/1.1/get/friendships/no_retweets/ids)  
> Modelo: Usuarios  

8. Lista de following (usuarios a los que sigue el usuario) - ordenados como una pila

> [GET friends/ids ; GET friends/list](https://dev.twitter.com/docs/api/1.1/get/friends/ids)  
> Modelo: User  

9. Lista de followers (usuarios que siguen al usaurio) - ordenados como una pila

> [GET followers/ids ; GET followers/list](https://dev.twitter.com/docs/api/1.1/get/followers/ids)  
> Modelo: User  

10. Relación entre el usuario y una lista de usuarios lu de máximo 100 elementos.
La relación puede ser following, following_requested, followed_by, none.

> [GET friendships/lookup](https://dev.twitter.com/docs/api/1.1/get/friendships/lookup)  
> Modelo: Ninguno

11. Settings de la cuenta del usuario, alguna de la información que podría ser interesante:     
 - geo_enabled 
 - language
 - time_zone

> [GET account/settings](https://dev.twitter.com/docs/api/1.1/get/account/settings)  
> Modelo: Ninguno  

12. Lista de usuarios que el usuario está bloqueando

> [GET blocks/list ; GET blocks/ids](https://dev.twitter.com/docs/api/1.1/get/blocks/list)  
> Modelo: User

13. Últimos 20 tweets marcados como favoritos por el usuario 

> [GET favorites/list](https://dev.twitter.com/docs/api/1.1/get/favorites/list)  
> Modelo: Tweet

14. Listas a las que está suscrito un usuario

> GET lists/memberships ; GET lists/list ; GET lists/subscriptions	
> Modelo: List


2: Entrada= tweet
---------------

1. Los 100 últimos retweets del tweet especificado 

> [GET statuses/retweets/:id](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets/%3Aid)  
> Modelo: Tweet  

2. Los 100 últimos usuarios que hayan retweetado el tweet especificado

> [GET statuses/retweeters/ids](https://dev.twitter.com/docs/api/1.1/get/statuses/retweeters/ids)  
> Modelo: User  


3: Entrada = Query
--------------

1. Colección de Tweets como resultado a una query.

Posibilidades existentes para la query:
 - contiene una lista de términos (y, o)
 - contiene exáctamente la cadena
 - contiene un hashtag
 - contiene un @screen_name
 - publicado desde una fecha
 - publicado antes de una fecha
 - contiene alguna URL
 - proviene de un TwitterFeed
 - geolocalización (radio)

Posibilidades especiales para la query:
 - actitud positiva
 - actitud negativa
 - hace una pregunta
 - lenguaje
 - los tweets son recientes / populares / mixtos

> [GET search/tweets](https://dev.twitter.com/docs/using-search ; https://dev.twitter.com/docs/api/1.1/get/search/tweets)  
> Modelo: Tweet  

2. Colección de usuarios como resultado a una query. La query es un string simple y no permite operaciones como la búsqueda de tweets 

> [GET users/search](https://dev.twitter.com/docs/api/1.1/get/users/search)  
> Modelo: User 

3. La lista de ***Twitter suggested user list***

> [GET users/suggestions](https://dev.twitter.com/docs/api/1.1/get/users/suggestions)  
> Modelo: ninguno

4. Usuarios recomendados para una categoría concreta (slug) de ***Twitter suggested user list"***  

> GET users/suggestions/:slug ; GET users/suggestions/:slug/members  
> Modelo: User

5. Información conocida sobre un lugar

> [GET geo/id/:place_id](https://dev.twitter.com/docs/api/1.1/get/geo/id/%3Aplace_id)  
> Modelo: Places

6. El top-10 de trending topics para una localización WOEID concreta. ¡Se puede concatenar con la Twitter search!
> [GET trends/place](https://dev.twitter.com/docs/api/1.1/get/trends/place)  
> Modelo: ninguno

7. Lista de localizaciones de las que Twitter tiene trending topic information y podemos especificar una zona de búsqueda

> GET trends/available ; GET trends/closest  
> Modelo: Place


5: Entrada = Lista
-------------------

1. Timeline de tweets publicados por cualquier usaurio perteneciente a una lista 

> [GET lists/statuses](https://dev.twitter.com/docs/api/1.1/get/lists/statuses)  
> Modelo: Tweets

2. Miembros (suscritores) de la lista especificada

> GET lists/members ; GET lists/subscribers
> Modelo: User

3. ¿El usuario está suscrito a una lista concreta?

> [GET lists/subscribers/show](https://dev.twitter.com/docs/api/1.1/get/lists/subscribers/show)  
> Modelo: ninguno


***

Modelo User
===========

[Toda la información de un usuario](https://dev.twitter.com/docs/platform-objects/users)

 - **created_at**: The UTC datetime that the user account was created on Twitter.
 - **description**:	The user-defined UTF-8 string describing their account.
 - **entities**: Entities which have been parsed out of the url or description fields defined by the user.
 - **favourites_count**: The number of tweets this user has favorited in the account's lifetime
 - **followers_count**:	The number of followers this account currently has.
 - **friends_count**: The number of users this account is following 
 - **geo_enabled**:	When true, indicates that the user has enabled the possibility of geotagging their Tweets.
 - **lang**: The BCP 47 code for the user's self-declared user interface language.
 - **listed_count**: The number of public lists that this user is a member of.
 - **location**: The user-defined location for this account's profile. Not necessarily a location nor parseable.
 - **protected**: When true, indicates that this user has chosen to protect their Tweets.
 - **statuses_count**: The number of tweets (including retweets) issued by the user.
 - **time_zone**: A string describing the Time Zone this user declares themselves within.
 - **url**:	A URL provided by the user in association with their profile.

***

Modelo: Tweet
=============

[Toda la información de un tweet](https://dev.twitter.com/docs/platform-objects/tweets)

 - **coordinates**: Represents the geographic location of this Tweet as reported by the user or client application. 
 - **created_at**: UTC time when this Tweet was created.
 - **current_user_retweet**: when set to true. Details the Tweet ID of the user's own retweet (if existent) of this Tweet.
 - **entities**: Entities which have been parsed out of the text of the Tweet.
 - **favorite_count**: Indicates approximately how many times this Tweet has been "favorited" by Twitter users.
 - **favorited**: Indicates whether this Tweet has been favorited by the authenticating user.
 - **in_reply_to_screen_name**: If the Tweet is a reply, this field will contain the screen name of the original Tweet's author.
 - **in_reply_to_status_id**: If the represented Tweet is a reply, this field will contain the integer representation of the original Tweet's ID.
 - **lang**: When present, indicates a BCP 47 language identifier corresponding to the machine-detected language of the Tweet text
 - **place**: When present, indicates that the tweet is associated (but not necessarily originating from) a Place.
 - **possibly_sensitive**. This field only surfaces when a tweet contains a link.
 - **retweet_count**: Number of times this Tweet has been retweeted.
 - **retweeted**: Indicates whether this Tweet has been retweeted by the authenticating user. 
 - **text**: the Tweet
 - **user**: the user who posted this Tweet. 

***

Esquemático
===========

1) Contenido
---------

1. Tweets posteados por el usuario
2. Mensajes directos por y para el usuario
3. Tweets del usuario retweeteados
4. Tweets marcados como favoritos por el usuario
5. Listas a las que está suscrito un usuario
6. Usuarios que han retweetado un tweet
7. Buscar tweets por términos que contiene - lenguaje - actitud
8. Buscar usuarios recomendados para una categoría de twitter (slug)
9. Descripción de un usuario (modelo User)
10. Número de veces marcado como favorito (modelo User)
11. Tweets publicados por un usuario (modelo User)
12. Url publicada por el usuario (modelo User)
13. Tweets con usl (sensitive) (modelo Tweet)

2) Social
------

1. Usuarios bloqueados (dos niveles de bloqueo)
2. Lista de amigos del usuario (following)
3. Lista de followers
4. Relación entre dos usuarios (following, followed_by, none)
5. Buscar tweets por menciones a otros usuarios - popularidad
6. Número de followers - friends (modelo User)
7. Cadena de tweets (respuestas) (modelo Tweet)

3) Localizacion
------------

1. time zone y language del usuario (modelos User)
2. Buscar tweets en un radio (geo)
3. Top 10 de trending topics en un radio (geo) **Temporal**
4. localización de un Tweet (modelo Tweet)
5. Localización de un 

4) Temporal
-----------

1. Timeline de un usuario
2. Buscar tweets en un rango de fechas
3. Top 10 de trending topics en un radio (geo) **Localización**
