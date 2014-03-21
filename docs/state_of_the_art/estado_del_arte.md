# 1. <a name="state-of-the-art"></a> Estado del arte

## <a name="index-state-of-the-art"></a> Índice

> 1. [Estado del arte](#state-of-the-art)</>
>   1.1. [Abstracto](#abstract)</br>
>   1.2. [Introducción](#intro)</br>
>   1.3. [Análisis de otros proyectos](#other-projects-analysis)</br>
>     1.3.1. [Twittomender](#twittomender)</br>
>     1.3.2. [Twitter properties analysis](#twitter-properties-analysis)</br>
>     1.3.3. [Buzzer](#buzzer)</br>
>     1.3.4. [Influential users](#influential-users)</br>
>     1.3.5. [Homophily in social networks](#homophily-in-social-networks)</br>
>     1.3.6. [Conclusiones](#other-projects-conclutions)</br>
>   1.4. [Clasificación de la información](#info-clasification) </br>
>     1.4.1. [Información extraíble de tiwtter](#info-from-twitter)</br>
>     1.4.2. [Niveles de clasificación de la información](#info-levels-definition)</br>
>       1.4.2.1. [Clasificación categórica](#info-category)</br>
>       1.4.2.2. [Clasificación por nivel de procesamiento](#info-level-of-prepoces)</br>
>       1.4.2.3. [Clasificación por modelo](#info-model-definition)</br>
>     1.4.3. [Clasificación de la información extraíble de Twitter](#info-last-clasification)</br>
>   1.5. [Análisis de aplicaciones relacionadas](#related-apps-analysis)</br>
>     1.5.1. [SecondSync](#secondsync)
>     1.5.2. [Bluefin Labs](#bluefin-labs)
>     1.5.3. [Brand Riders](#brand-riders)
>     1.5.4. [Follower Wonk](#follower-wonk)

***

## 1.1. <a name="abstract"></a> Abstracto

  La información disponible en la web está evolucionando hacia la posibilidad de acceder a ella en tiempo real. Los ejemplos más claros de ello son las redes sociales, y en concreto Twitter[^Twitter] y Facebook[^Facebook]. Creemos que estos medios proporcionan un gran potencial para la extración de información en tiempo real.

  Este proyecto se centra en la creación de una herramienta capaz de extraer *datos* publicados en la red social [^Twitter] y en la conversión de esos datos en información procesada; la *información* será accesible a través de la propia aplicación. </br>
  Por último, se desarrollará un aplicación web como ejemplo de un posible cliente de la herramienta principal.

  [^Twitter]: [Twitter © 2014]("https://twitter.com/")
  [^Facebook]: [Facebook © 2014]("https://www.facebook.com/")


## 1.2. <a name="intro"></a> Introducción

  (Poner bonito el apartado 1.2 )

  - Hemos visto que había publicado relacionado, los analizamos en otro proyectos.
  - Hemos *clasificado* / *analizado* la información extraible a través de Twitter, la clasificación está en clasificación.
  - Hemos analizado otras webs en funcion de nuestra clasificación
  - Hemos determinado dónde podíamos atacar => ***género***, ***sentimiento***, ***clasificación por temática*** y lo hemos implementado
  - Hemos construido una API para acceder a todo esto
  - Hemos implementado una aplicación ejemplo para ilustrar cómo utilizar la herramienta.

  (Falta nexo con el siguiente párrafo sobre las posibilidades que nos abre la geolocalización ¿tendría que ir en un título aparte tal vez?)

  De los tweets que obtengamos de Twitter habrá un porcentaje de ellos que estén geolocalizados. Por lo tanto, de uno de esos tweets sabremos lo siguiente:

  1. **Quién**: la persona que lo escribió (o hizo retweet, no lo pasemos por alto), y sus datos públicos que antes comentábamos: nombre completo, localización, lenguaje, etc.
  2. **Qué**: el contenido del tweet, lo cual incluye no solo el texto del mensaje, sino también los links, menciones, o contenido multimedia de éste.
  3. **Cuándo**: fecha y hora de publicación, y gracias al perfil del usuario, una buena estimación de en qué huso horario, útil para analizar patrones de publicación o uso.
  4. **Dónde**: coordenadas exactas de la ubicación desde donde fue tuiteado.

  Al tener tantos datos tan diferentes junto a una ubicación exacta, se nos abre todo un abanico de posibilidades de análisis por localización, y su correspondiente visualización.

  No hay consenso (ni datos oficiales) respecto a qué porcentaje de los tweets publicados contienen información sobre su geolocalización. Un porcentaje muy pequeño de estos tweets solo sería problema si estuviesemos buscando algo muy específico y necesitásemos resultados inmediatos. Sea como sea, es algo que tendremos que tener en cuenta si decidimos enfocar el proyecto en monitorizar temas o términos, ya que puede afectar a las conclusiones que se saquen.

  Links hablando de porcentaje de posicionamiento. Nótese la fecha, son antiguos, debería haber subido (podemos y debemos medirlo cuando empecemos):

  - https://www.quora.com/Twitter-User-Behavior/What-of-Twitter-users-have-the-geo-location-function-turned-on
  - http://thenextweb.com/2010/01/15/twitter-geofail-023-tweets-geotagged/


## 1.3. <a name="other-projects"> Otros proyectos

  En esta sección hacemos un análisis de papers de interés para el proyecto. En cada apartado, destacamos los puntos de interés, las herramientas utilizadas y los datos de entrada utilizados en esos proyectos.

  En una perspectiva general tenemos: (¿tal vez esta tabla moverla a 1.3.6?)

  | paper                       | Nos es de utilidad          | Tecnología | Datos de entrada                 |
  |:-----------------------     |:----------------------      |:---------- |:----------------                 |
  | Twittomender: Rec. usuarios | Profiling de usuarios       | Lucene     | ts + ers + ers.ts + ees + ees.ts |
  | Prop. analysis: grafos      | Conclusiones sobre topología| Ta. grafos | ts + ers + ees                   |
  | Buzzer: Rec. artículos      | Identificar breaking events | Lucene     | ts                               |
  | Influential users; LDA Model| find usuarios influyenyes   | Propia     | top users + ts + ers + ees       |
  | Homophily                   | 7 tipos de relaciones       | Teórico    | teórico                          |


### 1.3.1. <a name="twittomender"></a> Twittomender [^paper-twittomender]

  [^paper-twittomender]: [Twittomender](https://github.com/raulmarcosl/Itafy/tree/master/docs/papers/twittomender.pdf)

  - **Resumen** Sistema de recomendación de usuarios basado en contenido y con filtros colaborativos.
  - **Nos es de utilidad…** técnicas para profiling de usuarios
  - **Herramienta principal** Lucene
  - **Perfil de usuario** documento para indexar con Lucene => genera un vector con pesos {word: weight}
  - **Datos de entrada** Tweets + ers + ers.tweets + ees + ees.tweets

  #### Puntos interesantes

   1. El peso de cada término en el documento para el perfilado de usuarios es el **TF-IDF** propocionado por Lucene: proporcional a la frecuencia de aparición en el perfil del usuario e inversamente propocional a la frecuencia en el resto de perfiles; un peso alto implica que se trata de algo común en el perfil del usuario pero inusual en el resto de la población.

   2. Sistema de entrenamiento con casos de prueba de los que se conoce la solución.

    - Tomando como medida la *precisión*: la mejor opción es tomar los perfiles de los ees y la peor opción los tweets de los ees
    - Tomando como medida la *efectividad*: funciona bien los tweets de los ees pero tomando una K muy alta

   3. Falla la medida de acierto (sólo se considera buena solución si ya era un folowee)

  #### Cifras concretas

   - "Últimos tweets del usuario" => últimos 100 tweets
   - Trainig set de usuarios: 19.000
   - Conjunto de usuarios para testear el sistema (conocemos su solución): 1.000
   - Tiempo de prueba real: 1 mes
   - Usuarios de prueba reales: 34
   - Resultados reales: 6.9 tasa de acierto


### 1.3.2. <a name="twitter-properties-analysis"></a> Twitter properties analysis [^paper-twitter-prop-analysis]

  [^paper-twitter-prop-analysis]: [Twitter properties analysis](https://github.com/raulmarcosl/Itafy/tree/master/docs/papers/properties_analysis.pdf)

  - **Resumen** Topología de twitter: teoría de grafos. Métricas para twitter
  - **Nos es de utilidad…** conclusiones relacionadas con la topología de twitter
  - **Topología** Usuarios = Nodos ; Relaciones = Aristas dirigidas (contrarías al flujo de información).
  - **Datos de entrada** Tweets + ers + ees

  #### Puntos interesantes

  1. Dos términos interesantes: ***Dynamics of the network*** (cambios en la estructura) y ***Dynamics on the network*** (interacción entre nodos y condicionamiento por vecinos)

  2. ***Following ratio*** (followers / following)

  - __≃ 0__: spider coleccionando información sobre trending topics
  - __< 1__: buscamos colectar información
  - __1__: standard
  - __1__: generador de contendio apreciado por sus propia comunidad
  - __10+__: nodos jefes Huge impact around general media

  3. Los bots aparecen y desaparecen según las tendencias temporales.

  4. Al llegar a 600 ers, la cifra se dispara a 100.000 ers.

  5. **Rapidez en recibir la información** 30% le llegará la información en periodo t (casi instantáneo). La media está entre 0.22 y 0.3 de closeness

  #### Cifras concretas

  - Recrea el grafo con más de 14.000 nodos (usuarios)
  - 80% usuarios han hecho 1500 tweets. La media es 9Tweets / día / persona
  - 25% usaurios tiene 50 ers.
  - 50% tiene un rating 1:1


### 1.3.3. <a name="buzzer"></a> Buzzer [^paper-buzzer]

  [^paper-buzzer]: [Buzzer](https://github.com/raulmarcosl/Itafy/tree/master/docs/papers/buzzer.pdf)

  - **Resumen** Sistema de recomendación de artículos por contenido
  - **Nos es de utilidad** Estado del arte de Buzzer; identificar *topical news stories*
  - **Tecnología principal** Lucene
  - **RSS** Identificar en twitter los breaking events para modificar una RSS
  - **Datos de entrada** Últimos tweets generados

  #### Puntos de interés

  1. Decripción detallada de la arquitectura y el funcionamiento de buzzer:

  - Dos conjuntos: artículos R y tweets T
  - Cada conjunto se indexa por separado con Lucene: MR y MT
  - Intersección t = (MR X MT)
  - Usamos ti € t como query para sacar el conjunto A de artículos que contienen t.
  - Cada articulo ai € A tiene una puntuación IDF
  - Sumatorio de puntuaciones para cada Aij
  - Resultado: actualizar los artículos de RSS

  2. Estado del arte de Buzzer: *Digg.com* ; *Krakatoa Chronicle* ; *News dude*

  #### Cifras concretas

  1. Usuarios reales para las pruebas: 10


### 1.3.4. <a name="influential-users"></a> Influential users [^paper-influential-users]

  [^paper-influential-users]: [Influential Users](https://github.com/raulmarcosl/Itafy/tree/master/docs/papers/influential_users.pdf)

  - **resumen** técnicas para localizar los usuarios influyentes
  - **Técnica usada Latent Dirichlet Allocation (LDA) Model** Partimos de una bolsa de palabras. each topic is represented as a probabil- ity distribution over a number of words; cada documento es un vector-conteo de palabras.  each document is represented as a probability distribution over some topics

  #### Puntos interesantes

  1. El 72% de los usuarios cumplen que el 80% de sus ees son debido a la reciprocidad.

  ```
  Dataset: S = top 1000 twitters
  S' = S {|s| s.ers + s.ees}
  |S'| = 6748
  T = {tweets}
  |T| = 1.000.000
  S' - no publicadores - robots
  ```

  2. En el conjunto S en el que vamos a movernos hay 50.000 relaciones


### 1.3.5. <a name="homophily-in-social-networks"></a> Homophily in social networks [^paper-homophily]

  [^paper-homophily]: [Homophily in social networks](http://www.jstor.org/discover/10.2307/2678628?uid=3737952&uid=2134&uid=2475461167&uid=2&uid=70&uid=3&uid=60&uid=2475461157&sid=21102809388337.com)

  #### Puntos de interés

  1. La información en la red se queda concentra en ciertos sub-grafos.

  2. Clasificación de las relaciones personales entre usuarios
  - marriage
  - discussing important matters
  - friendship
  - career support at work
  - contact
  - knowing about
  - appearing with them in a public place

  3. Clasifciación de *homophily*

  - *status*: race, ethnicity, sex, age, religion, education, occupation & social class.
  - *values, attitudes and beliefs*: internal states presumed to shape our orientation toward futurre behaviour

  #### Cifras concretas

  1. Ninguna (teórico)


### 1.3.6. <a name="other-projects-conclutions"> Conclusiones

  Ni siquiera sé si este apartado tiene que existir; tal vez se pueda mover aquí la tabla de 1.3; "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."


## 1.4. <a name="abstract"></a>Clasificación de la información

  (Poner bonito: apartado 1.4)

  Por pasos vamos a poner en lista la información de la que disponemos a porrón (1.4.1), después vamos a inventarnos una clasificación teórica (1.4.2) y porúltimo vamos a clasficar la lista del primer punto en base a esa clasificación (punto por punto explicando y en una tabla resumen).


## 1.4.1 <a name="info-from-twitter"></a> Información extraíble de Twitter

  Esto es lo que tenemos disponible haciendo llamadas a la API pública de Twitter

  1. Tomando como entrada un usuario
    1. Últimos 20 tweets que contienen una mención al usuario [^api-1]
    2. Los últimos tweets posteados por el usuario [^api-2]
    3. Time line de un usuario [^api-3]
    4. Últimos tweets del usuario que han sido retweeteados [^api-4]
    5. 20 últimos mensajes directos enviados al usuario [^api-5]
    6. 20 últimos mensajes directos enviados por el usuario [^api-6]
    7. Colección de usuarios de los que el usuario no quiere recibir retweets [^api-7]
    8. Lista de following (usuarios a los que sigue el usuario) - ordenados como una pila [^api-8]
    9. Lista de followers (usuarios que siguen al usaurio) - ordenados como una pila  [^api-9]
    10. Relación entre el usuario y una lista de usuarios (máximo 100 elementos)  [^api-10]
    11. Settings de la cuenta del usuario, como ***geo_enabled***, ***language***, ***time_zone***  [^api-11]
    12. Lista de usuarios que el usuario está bloqueando [^api-12]
    13. Últimos 20 tweets marcados como favoritos por el usuario [^api-13]
    14. Listas a las que está suscrito un usuario [^api-14]
  2. Tomando como entrada un tweet
    1. Los 100 últimos retweets del tweet especificado [^api-15]
    2. Los 100 últimos usuarios que hayan retweetado el tweet especificado [^api-16]
  3. Usando una *query de contenido* como entrada ([^api-17] y [^api-18]) Posibilidades existentes para la query
    - contiene una lista de términos (y, o)
    - contiene exáctamente la cadena
    - contiene un hashtag
    - contiene un @screen_name
    - publicado desde una fecha
    - publicado antes de una fecha
    - contiene alguna URL
    - proviene de un TwitterFeed
    - geolocalización (radio)
    - actitud positiva
    - actitud negativa
    - hace una pregunta
    - lenguaje
    - los tweets son recientes / populares / mixtos
  4. Usando otras *queries*
    1. Colección de usuarios como resultado a una query. La query es un string simple y no permite operaciones como la búsqueda de tweets [^api-19]
    2. La lista de ***Twitter suggested user list*** [^api-20]
    3. Usuarios recomendados para una categoría concreta (slug) de ***Twitter suggested user list*** [^api-21]
    4. Información conocida sobre un lugar [^api-22]
    5. El top-10 de trending topics para una localización WOEID concreta [^api-23]
    6. Lista de localizaciones de las que Twitter tiene trending topic information y podemos especificar una zona de búsqueda [^api-24] y [^api-25]
  5. Tomando como entrada una lista
    1. Timeline de tweets publicados por cualquier usaurio perteneciente a una lista [^api-26]
    2. Miembros (suscritores) de la lista especificada [^api-27] y [^api-28]
    3. ¿El usuario está suscrito a una lista concreta? [^api-29]
  6. Información contenida en las respuestas "usuario" [^api-user]
    - **created_at**: The UTC datetime that the user account was created on Twitter.
    - **description**: The user-defined UTF-8 string describing their account.
    - **entities**: Entities which have been parsed out of the url or description fields defined by the user.
    - **favourites_count**: The number of tweets this user has favorited in the account's lifetime
    - **followers_count**: The number of followers this account currently has.
    - **friends_count**: The number of users this account is following
    - **geo_enabled**: When true, indicates that the user has enabled the possibility of geotagging their Tweets.
    - **lang**: The BCP 47 code for the user's self-declared user interface language.
    - **listed_count**: The number of public lists that this user is a member of.
    - **location**: The user-defined location for this account's profile. Not necessarily a location nor parseable.
    - **protected**: When true, indicates that this user has chosen to protect their Tweets.
    - **statuses_count**: The number of tweets (including retweets) issued by the user.
    - **time_zone**: A string describing the Time Zone this user declares themselves within.
    - **url**: A URL provided by the user in association with their profile.
  7. Información contenida en las respuestas "tweet" [^api-tweet]
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

  [^api-1]:  [GET statuses/mentions_timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/mentions_timeline)
  [^api-2]:  [GET statuses/user_timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline)
  [^api-3]:  [GET statuses/home_timeline](https://dev.twitter.com/docs/api/1.1/get/statuses/home_timeline)
  [^api-4]:  [GET statuses/retweets_of_me](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets_of_me)
  [^api-5]:  [GET direct_messages](https://dev.twitter.com/docs/api/1.1/get/direct_messages)
  [^api-6]:  [GET direct_messages/sent](https://dev.twitter.com/docs/api/1.1/get/direct_messages/sent)
  [^api-7]:  [GET friendships/no_retweets/ids](https://dev.twitter.com/docs/api/1.1/get/friendships/no_retweets/ids)
  [^api-8]:  [GET friends/ids ; GET friends/list](https://dev.twitter.com/docs/api/1.1/get/friends/ids)
  [^api-9]:  [GET followers/ids ; GET followers/list](https://dev.twitter.com/docs/api/1.1/get/followers/ids)
  [^api-10]: [GET friendships/lookup](https://dev.twitter.com/docs/api/1.1/get/friendships/lookup)
  [^api-11]: [GET account/settings](https://dev.twitter.com/docs/api/1.1/get/account/settings)
  [^api-12]: [GET blocks/list](https://dev.twitter.com/docs/api/1.1/get/blocks/list)
  [^api-13]: [GET favorites/list](https://dev.twitter.com/docs/api/1.1/get/favorites/list)
  [^api-14]: [GET lists/list](https://dev.twitter.com/docs/api/1.1/get/lists/list)
  [^api-15]: [GET statuses/retweets/:id](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets/%3Aid)
  [^api-16]: [GET statuses/retweeters/ids](https://dev.twitter.com/docs/api/1.1/get/statuses/retweeters/ids)
  [^api-17]: [GET search/tweets](https://dev.twitter.com/docs/using-search)
  [^api-18]: [GET search/tweets](https://dev.twitter.com/docs/api/1.1/get/search/tweets)
  [^api-19]: [GET users/search](https://dev.twitter.com/docs/api/1.1/get/users/search)
  [^api-20]: [GET users/suggestions](https://dev.twitter.com/docs/api/1.1/get/users/suggestions)
  [^api-21]: [GET users/suggestions/:slug]
  [^api-22]: [GET geo/id/:place_id](https://dev.twitter.com/docs/api/1.1/get/geo/id/%3Aplace_id)
  [^api-23]: [GET trends/place](https://dev.twitter.com/docs/api/1.1/get/trends/place)
  [^api-24]: [GET trends/available]()
  [^api-25]: [GET trends/closest]()
  [^api-26]: [GET lists/statuses](https://dev.twitter.com/docs/api/1.1/get/lists/statuses)
  [^api-27]: [GET lists/members]()
  [^api-28]: [GET lists/subscribers]()
  [^api-29]: [GET lists/subscribers/show](https://dev.twitter.com/docs/api/1.1/get/lists/subscribers/show)
  [^api-user]:  [Toda la información de un usuario](https://dev.twitter.com/docs/platform-objects/users)
  [^api-tweet]: [Toda la información de un tweet](https://dev.twitter.com/docs/platform-objects/tweets)


### 1.4.2 <a name="info-levels-definition"></a> Niveles de clasificación de la información

  Definimos tres niveles de clasificación para la información: por categoría, por nivel de procesamiento y por modelo al que pertenece dicha información.


#### 1.4.2.1. <a name="info-category"></a> Clasificación categórica

  Definimos las siguientes cinco posibles categorías:

  1. **Contenido**. Aquella información que es introducida por los usuarios voluntariamente, ya sea textual o audiovisual. Por ejemplo un tweet o un mensaje directo; una anotación: la información de perfil relativa a la biografía introducida por el propio usuario también se incluye en esta clasificación.
  2. **Social**. Aquella información que está que está relacionada de alguna forma con otra información, generando una red social alrededor de un contenido o de un usuario; por ejemplo: un tweet que contiene una mención, los miembros de una lista.
  3. **Localización**. Aquella información de la que disponemos de datos geográficos. Habitualmente estos datos serán relativos al lugar de creación de dicha información, pero también está la posibilidad de obtener diferentes datos procesados según la ubicación; ejemplos: tweet con geolocalizado, trending topics por ubicación.
  4. **Temporal**. Aquella información que puede cambiar según el momento en el cual sea consultada. Obviamente la gran mayoría de la información es susceptible de cambiar (listas o tweets añadidos o borrados, relaciones creadas o destruidas, etc.), pero sólo la categorizamos como temporal si este cambio es por definición; ejemplos: recomendaciones de Twitter, trending topis.
  5. **Interés - Actividad**. Aquella información que incluye datos sobre la relevancia de ésta ya sea dentro de toda la red o para cierto número de personas; ejemplos: número de retweets o favoritos de un tweet.

  > Nota: estas categorías no son excluyentes.


#### 1.4.2.2. <a name="info-level-of-prepoces"></a> Clasificación por nivel de procesamiento

  Existen dos posibles niveles de procesamiento de la información:

  1. **Raw data**. Información que Twitter nos devuelve sin procesar, solamente nos la sirve. Constituye la gran mayoría de la información que nos ofrece.
  2. **Processed data**. Información que Twitter nos devuelve una vez que ha sido generada, como por ejemplo los *Trending Topic y las recomendaciones. A diferencia de la información sin procesar, no sabemos de qué forma la obtiene, y por lo tanto no sabemos su fiabilidad o precisión.

  > Nota: estas categorías son excluyentes.


#### 1.4.2.3. <a name="info-model-definition"></a> Clasificación por modelo

  Abstractamente definimos tres modelos de información.

  1. **Usuario**. Integrante de la red que tiene una identidad, y que puede generar contenido y relacionarse con el resto de usuarios. El usuario no tiene porqué ser una persona física: un usuario puede ser la representación de una empresa o de un producto, por ejemplo.
  2. **Tweet**. Unidad básica de contenido de Twitter, formada por hasta un máximo de 140 caracteres, y que puede contener texto, información audiovisual o enlaces externos o internos a Twitter.
  3. **Actividad (relación)**. Definimos como actividad aquella información que establece una relación dentro de Twitter. Por ejemplo: una mención, un retweet o marcar un tweet como favorito.

  > Nota: los tres modelos son no excluyentes.


### 1.4.3. <a name="info-last-clasification"></a> Clasificación de la información extraíble de Twitter

  1. [Tweets posteados por el usuario](https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline): Pertenece exclusivamente a la categoría de *contenido*; abstraemos el hecho de que sea posible que existan retweets, menciones y tweets marcados como favoritos en la lista de tweets (determinando así que no incluímos las categorías social e interés o actividad). Abstraemos también que el volumen de tweets cambie a lo largo del tiempo (no pertenecerá a la categoría temporal) y la posibilidad de que estos tweets sean geolocalizados al existir una llamda para tratar expresamente este punto (no pertenecerá tampoco a la categoría localización). Es información sin procesar por parte de Twitter y pertenece al modelo Tweet.
  2. [Timeline de un usuario](https://dev.twitter.com/docs/api/1.1/get/statuses/home_timeline): es *contenido*, *interés - actividad*, RAW y modelo: Tweet
  3. [mensajes enviados](https://dev.twitter.com/docs/api/1.1/get/direct_messages/sent); [mensajes recibidos](https://dev.twitter.com/docs/api/1.1/get/direct_messages): El hecho de mandar/recibir un mensaje directo a/de otro usuario, establece implícitamente una relación *social* entre A y B. El mensaje en sí mismo lo categorizamos como *contenido*, por lo tanto, pertenece a las categoráis *contenido* y *social*. La información no está procesada por parte de Twitter y, salvando las distancias, consideramos a los mensajes privados pertenecientes al modelo Tweet.
  4. [tweets retweeteados](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets_of_me): A pesar de que el objeto sobre el que estamos interactuando es un tweet (y por tanto estamos hablando de contenido), consideramos que un retweet no genera nuevo contenido a la red, sino que establece una relación de interés o actividad. Al igual que para el punto (^), abstraemos una serie de puntos: la posibilidad de menciones a otros usuarios, el cambio de volumen temporal y la geolocalización. Por ello pertenece únicamente a la categoría *interés - actividad*.
  La información no está procesada por parte de Twitter y existen dos modelos actuando sobre esta relación: Tweet y Actividad.
  5. [Tweets marcados como favoritos por el usuario](https://dev.twitter.com/docs/api/1.1/get/favorites/list): Podemos aplicar los mismos criterios que el punto anterior (^); el objetivo central de este punto es la relevancia con la que ha dotado un usuario a un cierto contenido, por lo que la categoría será *interés - actividad*. La información no está procesada por parte de Twitter y los dos modelos que actúan son Tweet y Actividad.
  6. [tweets con mención al usuario](https://dev.twitter.com/docs/api/1.1/get/statuses/mentions_timeline): Categoría: *social*; Procesamiento: RAW; Modelos: Usuario, Tweet, Actividad
  7. [Lista de amigos del usuario](https://dev.twitter.com/docs/api/1.1/get/friends/ids): Categoría: *social*, *interés - actividad*; Procesamiento: RAW; Modelos: Usuario, Actividad
  8. [Lista de followers](https://dev.twitter.com/docs/api/1.1/get/followers/ids): Categoría: *social*, *interés - actividad*; Procesamiento: RAW; Modelos: Usuario, Actividad
  9. [Relación entre usuarios](https://dev.twitter.com/docs/api/1.1/get/friendships/lookup): Categoría: *social*, *interés - actividad*; Procesamiento: RAW; Modelos: Usuario, Actividad
  10. [Número de followers - friends](https://dev.twitter.com/docs/platform-objects/users): Categoría: *interés - actividad*, *social*; Procesamiento: RAW; Modelos: Usuario, Actividad
  11. [usuarios bloqueados](https://dev.twitter.com/docs/api/1.1/get/blocks/list) y [usuarios sin posibilidad de retweet](https://dev.twitter.com/docs/api/1.1/get/friendships/no_retweets/ids): Categoría: *social* *interés - actividad*; Procesamiento: RAW; Modelos: Usuarios, Actividad
  12. [Biografía y descripción de un usuario](https://dev.twitter.com/docs/platform-objects/users) e información de configuración de la cuenta](https://dev.twitter.com/docs/api/1.1/get/account/settings): La información de perfil relativa a la biografía introducida por el propio usuario, *contenido*. La información no está procesada por Twitter. El modelo abstracto de datos al que pertenece es usuario.
  13. [Url publicada por el usuario](https://dev.twitter.com/docs/platform-objects/users): Información de perfil introducida por el propio usuario. El motivo por el que hemos separado la información extraíble de un usuario (usuario entendido como entidad definida por Twitter) es que podemos clasificar distintos campos con diferentes categorías. La url publicada por el usuario pertenece a la categoría *social* en cuanto a que está creando una pequeña red de información externa en torno al usuario.
  La información no está procesada. El modelo es actividad.
  14. [Número de veces marcado como favorito](https://dev.twitter.com/docs/platform-objects/users): Número de veces que los tweets publicados por el usuario han sido marcados como favorito. Esta información puede ser entendida como un reflejo de la relevancia o *interés - actividad* que este usuario suscita dentro de la red.
  Es información sin procesar y consideramos a los modelos usuario y actividad.
  15. [Listas a las que está suscrito el usuario](https://dev.twitter.com/docs/api/1/get/lists) las categorías involucradas son *social* ya que partiendo del usuario podemos establecer una red alrededor del usuario, y *interés - actividad* al tratarse de una relación dentro de Twitter. La información no está pre procesada y actúa sobre el modelo Actividad.
  16. [Usuarios que han retweeteado un tweet](https://dev.twitter.com/docs/api/1.1/get/statuses/retweeters/ids): El razonamiento volverá a ser parecido que en el punto (*); a pesar de que los objetos sobre los que interactuamos son tweets (contenido) y usuarios (relación directa con información de perfil), la información principal de este punto es de *interés o actividad* por parte de N usuarios sobre 1 contenido concreto. La información no está procesada por parte de Twitter y considereamos los modelos involucrados Usuario y Actividad
  17. [retweets de un tweet](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets/%3Aid): Categoría: *interés - actividad*; *temporal*; Procesamiento: RAW; Modelos: Tweet, Actividad
  18. [Cadena de respuestas a un tweet](https://dev.twitter.com/docs/platform-objects/tweets): Categoría: *social*; Pocesamiento: RAW; Modelos: Tweet, Actividad
  19. [Localización de un tweet](https://dev.twitter.com/docs/platform-objects/tweets): Categoría: *localización*; Procesamiento: PROCESSED; Modelos: Tweet
  20. [Búsqueda de usuarios](https://dev.twitter.com/docs/api/1.1/get/users/search): Es posible la búsqueda de perfiles; sin embargo la única query con la que podemos jugar es el nombre o el nick lo que no nos aporta información útil. La categorización es de *contenido* ya que el nombre no deja de ser información que ha aportado un usuario a la red.
  Existe un mínimo procesamiento por parte de twitter y el modelo involucrado es el de usuario.
  21. [Búsqueda de tweets](https://dev.twitter.com/docs/api/1.1/get/search/tweets) y [búsqueda avanzada de tweets](https://dev.twitter.com/docs/using-search): A diferencia que para la búsqueda de usuarios, Twitter permite un abanico muy grande de criterios de búsqueda de tweets; antes de determinar las categorías que podemos aplicar, diremos que algunas de las posibildiades a la hora de formular la query cuentan con un alto grado de procesamiento por parte de Twitter. Los puntos más destacables para las búsquedas son:</br>
    1. contienen una lista de términos (&&, ||)</br>
    2. contienen exáctamente una cadena</br>
    3. contienen texto con una actitud positiva</br>
    4. contienen texto con una actitud negativa</br>
    5. contienen una pregunta</br>
    6. lengua en la que están escritos</br>
    7. contienen un hashtag</br>
    8. contienen un @screen_name (una mención a otro usuario)</br>
    9. contienen contenido "sensitive" (contienen alguna URL)</br>
    10. son "populares" (no explica qué es popular)</br>
    11. publicados hasta una fecha</br>
    12. publicados antes de una fecha</br>
    13. son "recientes" en el tiempo</br>
    14. fueron publicados en un área concreta (geolocalización)</br>
  21. Buscar tweets por contenido: Engloba las posibildiades de búsqueda relacionadas con el *contenido*: lista de términos, cadena exacta, texto con actitud positiva, negativa, contienen una pregunta, lengua en la que están escritos.
  22. Buscar tweets por interacción social: Engloba las posibilidades de búsqueda relacionadas con las interacciones *sociales*: contienen un hashtag, contienen una mención a otro usuario, contienen alguna URL, son "populares".
  23. Buscar tweets por temporalidad: Engloba las posibilidades de búsqueda relacionadas con la *temporalidad* de los tweets: publicados hasta / antes de una fecha, son "recientes" en el tiempo.
  24. Buscar tweets por localización: Engloba las posiblidades de búsqueda relacionadas con la *localización* de los tweets: área geográfica de publicación
  25. [Buscar usuarios recomendados](https://dev.twitter.com/docs/api/1.1/get/users/suggestions): Sistema de recomendación de usuarios de Twitter. Pertenece a la categoría *interés - actividad* ya que las recomendaciones están hechas en función de los intereses mostrados por los usuarios. Tal y como explica en la documentación de Twitter, esta información tiene una fuerte dependencia *temporal*.
  La información está pre-procesada por Twitter y considera al modelo usuario
  26. [Buscar usuarios recomendados para una categoría](https://dev.twitter.com/docs/api/1/get/users/suggestions/%3Aslug): Twitter determina ciertos usuarios claves para una serie de categorías definidas previamente por el propio Twitter; estamos hablando de información procesada por Twitter. Pertence principalmente a la categoría *interés - actividad* ya que se supone que estos usuarios son "lideres" en su ámbito; sin embargo esta vez determinamos que pertenece también a la categoría *temporal* ya que este "status de liderazgo" es completamente temporal tal y como se expresa en la documentación de twitter. Pertenece únicamente al modelo usuario.
  27. [Tweets con contenido "sensitive"](https://dev.twitter.com/docs/platform-objects/tweets): Categoría: *contenido*, *social*;
  Procesamiento: RAW; Modelos: Tweet
  28. [Lista de lugares conocidos por Twitter](https://dev.twitter.com/docs/api/1/get/trends/available)
  29. [Información de un lugar](https://dev.twitter.com/docs/api/1.1/get/geo/id/%3Aplace_id) Categoría: *contenido*; Procesamiento: PROCESSED; Modelo: Ninguno
  30. [Lista de trending topics por localización](https://dev.twitter.com/docs/api/1.1/get/trends/place): Categoría: *interés - actividad* *temoral* *localización*; Procesamiento: PROCESSED; Modelo: Actividad

  A continuación, expresamos de forma concisa la clasificación según los tres niveles.

  | #   | Información                           | Categorías                  | Procesamiento | Modelo              |
  |:---:|:------------                          |:-----------                 |:------------- |:-------             |
  | 1   | Tweets posteados por el usuario       | contenido                   | RAW           | Tweet               |
  | 2   | Timeline de un usuario                | contenido, interés - actividad | RAW        | Tweet, Actividad    |
  | 3   | Mensajes directos                     | contenido, social           | RAW           | Tweet               |
  | 4   | Tweets del usuario retweeteados       | interés - actividad         | RAW           | Tweet, Actividad    |
  | 5   | Tweets marcados como favoritos        | interés - actividad         | RAW           | Tweet, Actividad    |
  | 6   | Tweets con mención al usuario         | social                      | RAW           | Usuario, Tweet, Actividad |
  | 7   | Lista de amigos del usuario           | social, interés - actividad | RAW           | Usuario, Actividad  |
  | 8   | Lista de followers                    | social, interés - actividad | RAW           | Usuario, Actividad  |
  | 9   | Relación entre dos usuarios           | social, interés - actividad | RAW           | Usuario, Actividad  |
  | 10  | Número de followers - friends (User)  | social, interés - actividad | RAW           | Usuario, Actividad  |
  | 11  | Usuarios bloqueados (varios niveles)  | social, interés - actividad | RAW           | Usuario, Actividad  |
  | 12  | Biografía y descripción de un Usuario | contenido                   | RAW           | Usuario             |
  | 13  | Url publicada por el usuario          | social                      | RAW           | Actividad           |
  | 14  | Número de veces marcado como favorito | interés - actividad         | RAW           | Usuario, Actividad  |
  | 15  | Listas a las que está suscrito un usuario | social, interés - actividad | RAW       | Usuario, Actividad  |
  | 16  | Usuarios que han retweetado un tweet  | interés - actividad           | RAW         | Usuario, Actividad  |
  | 17  | Retweets de un tweet                  | interés - actividad           | RAW         | Tweet, Actividad    |
  | 18  | Cadena de respuestas a un Tweet       | social                        | RAW         | Tweet, Actividad    |
  | 19  | localización de un tweet              | localización                  | PROCESSED   | Tweet               |
  | 20  | Buscar usuarios                       | contenido                     | PROCESSED   | Usuario             |
  | 21  | Búsqueda de Tweets por contenido      | contenido                     | PROCESSED   | Tweet               |
  | 22  | Búsqueda de Tweets por interacción social | social                    | PROCESSED   | Tweet, Actividad    |
  | 23  | Búsqueda de Tweets por temporalidad   | temporal                      | PROCESSED   | Tweet               |
  | 24  | Búsqueda de Tweets por localización   | localización                  | PROCESSED   | Tweet               |
  | 25  | Usuarios recomendados (general)       | interés - actividad, temporal | PROCESSED   | Usuario             |
  | 26  | Usuarios recomendados para una categoría | interés - actividad, temporal | PROCESSED| Usuario             |
  | 27  | Tweets con contenido "sensitive"      | contenido, social             | RAW         | Tweet               |
  | 28  | Lista de lugares conocidos por twitter| contenido                     | PROCESSED   | Ninguno             |
  | 29  | Información conocida de un lugar      | contenido                     | PROCESSEd   | Ninguno             |
  | 30  | Trending Topics por localización      | interés, temporal, localización | PROCESSED | Actividad           |


## 1.5. <a name="related-apps-analysis"></a> Análisis de aplicaciones relacionadas

  Para los siguientes 5 servicios, hacemos un análisis de las funciones que ofrecen; las funciones que no son "directas" se marcan en negrita


### 1.5.1. <a name="secondsync"></a> SecondSync [^web-secondsync]

  [^web-secondsync]: [Página web de SecondSync](http://secondsync.com/)

  SecondSync es una empresa británica especializada en el análisis de Twitter enfocada únicamente a la televisión (la llamada "Social TV"). Su servicio consiste en un dashboard dónde las cadenas de televisión o similares pueden ver un análisis detallado de sus programas, ya sea por emisión, o en general. En este análisis las empresas pueden no solo consultar datos relativos al volumen de tweets que generan sus programas, sino también si lo que se dice de ellos es bueno o malo, el perfil medio de quién lo comenta, y saber qué perfiles influyentes de Twitter han hablado sobre ellos.

  Dicho análisis está basado sobretodo en la categoría de contenido, ya que está basado en la cantidad de tweets que se han generado sobre la televisión social, y qué se dice en ellos. Estos tweets son extraídos por SecondSync mediante la Twitter Streaming API o mediante opciones más avanzadas (y de pago) como algún proveedor autorizado de Twitter, o la Twitter Firehose.

  Después de recopilar estos tweets y analizarlos para ofrecer las estadísticas básicas de audiencia social, SecondSync se apoya en algunas de las otras categorías que hemos definido para ofrecer algunas estadísticas adicionales:

  - **Perfil del usuario.** Usado junto con el contenido de los tweets para ofrecer estadísticas relativas al sexo de la audiencia. Además, el saber si es una cuenta verificada les permite detectar perfiles muy influyentes.
  - **Social.** Usado principalmente para extraer información sobre las impresiones potenciales generadas por los tweets que hablan de los programas analizados, pero también útil para reconocer perfiles influyentes que estén hablando de estos.


### 1.5.2. <a name="bluefin-labs"></a> Bluefin Labs [^web-bluefin]

  [^web-bluefin]: [Página web de Bluefin Labs](https://bluefinlabs.com/solutions/network/)

  Bluefin se centra en la televisión, pero con un enfoque diferente al de SecondSync. Si bien SecondSync se centraba básicamente en el contenido para ofrecer una serie de estadísticas, Bluefin se centra en intentar saber qué dice la gente sobre un programa o un anuncio de la televisión, y cómo afecta a estos. Es decir, no solo ofrecen datos cuantitativos sobre los programas o anuncios, sino que relacionan estos datos con otros para ofrecer estadísticas sobre rendimiento de campañas publicitarias, listas de términos más repetidos, relaciones del tipo "a los usuarios que les gusta este programa le gusta este otro", etc.

  Para realizar este tipo de análisis, Bluefin está también centrada en la categoría contenido con pequeñas pinceladas de perfil y social, pero a diferencia de SecondSync, los datos que ofrece están más procesados, y no se centran en ofrecer estadísticas de volúmenes y ratios, sino que usan machine learning para ir un paso más allá.


### 1.5.3. <a name="brand-riders"></a> Brand Riders [^web-brand-riders]

  [^web-brand-riders]: [Página web de BrandRiders](http://blog.thebrandriders.com/)

  BrandRiders es una empresa española que ofrece una herramienta para gestionar una o más redes sociales a la vez. Si bien está pensada tanto para Facebook como para Twitter, y además incorpora un analizador de feeds RSS, nosotros nos centraremos en su motor de fidelización y sus informes, todo ello centrado en Twitter.

  Su motor de fidelización consiste en una serie de sugerencias de acciones dentro de Twitter que ellos te proponen porque piensan que son beneficiosas para tu cuenta de Twitter: puede ser porque es muy probable que esa acción conlleve un retweet, o porque alguien te puede empezar a seguir por ello. Para ello, se basan en cálculos de afinidad entre cuentas, en el ratio de "followback" que tienen esas cuentas, y en categorización de contenido.

  Esta sección está basada en el contenido, ya que te dice qué acciones tienes que hacer sobre qué contenido, pero por otro lado está enfocada a conseguir mejoras en la categoría de social.

  [Captura motor de fidelización](http://blog.thebrandriders.com/es/wp-content/uploads/2013/11/Engagement.png)

  Por otro lado, la herramienta también tiene una sección de analítica y reportes. En esta sección se puede ver información cuantitativa sobre la cuenta de Twitter que se esté monitorizando, como número de seguidores y amigos, historial de interacciones de generadas o audiencia potencial.

  Esta sección está basada en las categorías social e interés, ya que se basa en cómo esa cuenta de Twitter está relacionada con el resto de usuarios de su red, y cómo ha cambiado en el tiempo.

  [Captura de pantalla analítica](http://blog.thebrandriders.com/es/wp-content/uploads/2013/11/Analitica.png)


### 1.5.4. <a name="follower-wonk"> Follower Wonk [^web-follower-wonk]

  [^web-follower-wonk]: [Página web de Follower Wonk](https://followerwonk.com)

  El punto más interesante de esta aplicación es lo que llama ***wonk social authority*** que es un medidor de autoridad de los usuarios dentro de la red Twitter. En lineas generales:

  - Basado en retweets
  - Accesible via SDK => [social authority SDK](https://github.com/seomoz/Social-Authority-SDK)
  - Explicación detallada de cómo se calcula =>  [Cómo calcula la social authority](http://moz.com/blog/social-authority)

  En el blog de los desarrolladores tenemos un aproximamiento de cómo se calcula este valor:

  1. **Tasa de retweets**: se utilizan aproximadamente los 100 últimos tweets del usuario que no contengan *@mention* a otros usuarios. La razón por la que se filtran los tweets con menciones es que existe una gran correlación entre el número de retweets que consigue un usuario con la tasa de menciones (en torno al 80%). Otro dato interesante en este punto es la relación entre la tasa de tweets retweteados y los tweets que contienen URLs - entorno al 70%.
  2. **Lo reciente que es el retweet**: Se favorece a los tweets a corto plazo contra tweets antigüos. Lo que se intenta es destacar el contenido que tiene impacto en twitter en cada instante. Se define la vida media de un tweet en 18 minutos.
  3. **Número de followers** Este tercer factor es el que menos peso tiene y se optimiza mediante un modelo de regresión "trained to retweet rate"; el objetivo de este modelo es suavizar los picos ocasionales de retweets de un usuario y, además, tiene en cuenta que hay una relación directa entre el número de seguidores y el porcetage de tweets retweeteados. Hasta el punto que a partir de los 10.000 seguidores se consigue una tasa de retweets del 25%

  Extracto del blog de *moz* defendiendo el varemo de social authority en retweets mejor que en followers (tratado)

  > @autocorrects is retweeted 7% more than @BarackObama, yet has 14 times fewer followers! As you can see, Social Authority surfaces a completely different set of top users: those that are extremely effective in engaging their followers. (…) They’ve discovered (these accounts) content that gets their audiences’ attention, whether we like it or not, and prompts action in terms of retweets and traffic.


### 1.6. Párrafo fantasma

  ¿Tiene que existir un último párrafo?


***

(Todos estos links hay que ponerlos bien, pero no puedo más, menuda matada me he pegado)

1. https://www.quora.com/Twitter-User-Behavior/What-of-Twitter-users-have-the-geo-location-function-turned-on
2. http://thenextweb.com/2010/01/15/twitter-geofail-023-tweets-geotagged/
3. http://www.leafletjs.com. Proyecto open source. Usa Open Street Map.
4. http://www.mapbox.com. Basado en Leaflet, es de pago pero el plan gratuito nos valdría (3000 visualizaciones al mes).
5. https://developers.google.com/maps.
6. http://users.humboldt.edu/mstephens/hate/hate_map.html
7. https://bluefinlabs.com/
8. http://secondsync.com/
9. http://topsy.com
10. http://simplymeasured.com/ (muy interesantes los free reports, ahí hay muy buenas ideas)
11. http://www.tweriod.com/ Best time to tweet
12. http://en.wikipedia.org/wiki/Representational_state_transfer Rest on Wikipedia:
13. [Página web de Hoot Suite](https://hootsuite.com/)
14. [Features de la aplicación](https://hootsuite.com/features/custom-analytics)
