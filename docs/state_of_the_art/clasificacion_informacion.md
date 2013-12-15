# Clasificación de información

## Índice

> [1 - Niveles de clasificación de la información](title-1)
> [2 - Tabla clasificatoria](title-2)
> [3 - Análisis de webs](title-3)
> [4 - wonk social authority](title-4)

## 1 - Niveles de clasificación de la información

Definimos 3 niveles de clasificación para la información: por categoria, por nivel de procesamiento y por modelo al que
pertenece

### Clasificación categórica de la información (categorías no excluyentes)

 - **Contenido**. Aquella información que es introducida por los usuarios voluntariamente, ya sea textual o audiovisual.
 Ejemplos: un tweet, un mensaje directo.
 - **Social**. Aquella información que está que está relacionada de alguna forma con otra información, generando una red
 social alrededor de un contenido o de un usuario. Ejemplos: un tweet que contiene una mención, los miembros de una lista.
 - **Localización**. Aquella información de la que disponemos de datos geográficos. Habitualmente estos datos serán
 relativos al lugar de creación de dicha información, pero también está la posibilidad de obtener diferentes datos
 procesados según la ubicación. Ejemplos: tweet con geolocalizado, trending topics por ubicación.
 - **Temporal**. Aquella información que puede cambiar según el momento en el cual sea consultada. Obviamente la gran
 mayoría de la información es susceptible de cambiar (listas o tweets añadidos o borrados, relaciones creadas o destruidas,
  etc.), pero solo la categorizamos como temporal si este cambio es por definición. Ejemplo: recomendaciones de Twitter,
  trending topis.
 - **Interés - Actividad**. Aquella información que incluye datos sobre la relevancia de ésta ya sea dentro de toda la
 red o para cierto número de personas. Ejemplos: número de retweets o favoritos de un tweet.
 - **Perfil de usuario**. Aquella información relativa a la biografía del usuario, que ha sido introducida por el propio
 usuario. Ejemplo: web del usuario, o su foto.

### Clasificación por nivel de procesamiento de la información (categorías excluyentes)

 - **Raw data**. Información que Twitter nos devuelve sin procesar, solamente nos la sirve. Constituye la gran mayoría
 de la información que nos ofrece.
 - **Processed data**. Información que Twitter nos devuelve una vez que ha sido generada, como por ejemplo los Trending
 Topic y las recomendaciones. A diferencia de la información sin procesar, no sabemos de qué forma la obtiene, y por lo
 tanto no sabemos su fiabilidad o precisión.

### Clasificación según el modelo al que pertence (categorías no excluyentes)

 - **Usuario**. Integrante de la red que tiene una identidad, y que puede generar contenido y relacionarse con el resto
 de usuarios. El usuario no tiene porqué ser una persona física: un usuario puede ser la representación de una empresa o
 de un producto, por ejemplo.
 - **Tweet**. Unidad básica de contenido de Twitter, formada por hasta un máximo de 140 caracteres, y que puede contener
 texto, información audiovisual o enlaces externos o internos a Twitter.
 - **Actividad (relación)**. Definimos como actividad aquella información que establece una relación dentro de Twitter.
 Por ejemplo: una mención, un retweet o un favorito.


## 2 - Tabla clasificatoria

Clasificación de las 25 llamadas estudiadas a la API de twitter según los 3 niveles de clasificación contemplados

| # | Información 									| Categorías 						| Nivel procesamiento 	| Modelo 			 	|
|:--:  |:------------								|:-----------						|:--------------------	|:-------			 	|
| 1 | Tweets posteados por el usuario 				| contenido, social 				| RAW 					| Tweet				 	|
| 2 | Mensajes directos por y para el usuario 		| Contenido, social 				| RAW 					| Tweet 			 	|
| 3 | Tweets del usuario retweeteados 				| contenido, social, interés 		| RAW 					| Tweet, Actividad 	 	|
| 4 | Tweets marcados como favoritos por el usuario | contenido, social, interés 		| RAW 					| Tweet, Actividad   	|
| 5 | Listas a las que está suscrito un usuario 	| social, interés 					| RAW 					| Actividad 		 	|
| 6 | Usuarios que han retweetado un tweet 			| social, interés 			 		| RAW 					| Tweet, Usuario 	 	|
| 7 | Buscar tweets por términos que contiene 		| contenido, interés 				| RAW       			| Tweet 			 	|
| 8 | Buscar usuarios recomendados para una cate. 	| social, interés, temporal 		| PROCESSED 			| Usuario, Actividad 	|
| 9 | Descripción de un usuario (User)				| perfil 							| RAW 					| Usuario 			 	|
| 10 | Número de veces marcado como favorito (User)	| social, interés 					| RAW 					| Usuario, Actividad 	|
| 11 | Url publicada por el usuario (User)			| perfil 							| RAW 					| Usuario 			 	|
| 12 | Tweets con contenido "sensitive" (Tweet)		| contenido 						| RAW 					| Tweet 			 	|
| 13 | Usuarios bloqueados (dos niveles de bloqueo)	| social, interés 					| RAW 					| Usuario, Actividad 	|
| 14 | Lista de amigos del usuario (following)		| social, interés  	 				| RAW 					| Usuario, Actividad 	|
| 15 | Lista de followers							| social, interés   				| RAW 					| usuario, Actividad 	|
| 16 | Relación entre dos usuarios 					| social, interés   				| RAW 					| Usuario, Actividad 	|
| 17 | Buscar tweets por menciones a otros usuarios | contenido, social 				| RAW 					| Usuario, Tweet, Actividad |
| 18 | Número de followers - friends (User)			| social, interés 					| RAW 					| Usuario 				|
| 19 | Cadena de tweets (respuestas) (Tweet)		| social, interés 					| RAW 					| Tweet, Actividad 		|
| 20 | time zone y language del usuario (User)		| Perfil, geo 						| RAW 					| Usuario 				|
| 21 | Buscar tweets en un radio (geo)				| contenido, geo 					| RAW 					| Tweet 				|
| 22 | Trending Topics curados por persona o sitio 	| social, interés, geo, temporal 	| PROCESSED 			| Tweet, Actividad 		|
| 23 | localización de un Tweet (Tweet)				| contenido, geo 					| RAW 					| Tweet 				|
| 24 | Timeline de un usuario						| contenido, social, temporal 		| RAW 					| Tweet, Actividad 		|
| 25 | Buscar tweets en un rango de fechas			| contenido, temporal 				| RAW         			| Tweet 				|

## 3 - Análisis de webs

Para los siguientes 5 servicios, hacemos un análisis de las funciones que ofrecen; las funciones que no son "directas" se marcan en negrita

### 1) [SecondSync](http://secondsync.com/)

SecondSync es una empresa británica especializada en el análisis de Twitter enfocada únicamente a la televisión (la llamada "Social TV"). Su servicio consiste en un dashboard dónde las cadenas de televisión o similares pueden ver un análisis detallado de sus programas, ya sea por emisión, o en general. En este análisis las empresas pueden no solo consultar datos relativos al volumen de tweets que generan sus programas, sino también si lo que se dice de ellos es bueno o malo, el perfil medio de quién lo comenta, y saber qué perfiles influyentes de Twitter han hablado sobre ellos.

Dicho análisis está basado sobretodo en la categoría de contenido, ya que está basado en la cantidad de tweets que se han generado sobre la televisión social, y qué se dice en ellos. Estos tweets son extraídos por SecondSync mediante la Twitter Streaming API o mediante opciones más avanzadas (y de pago) como algún proveedor autorizado de Twitter, o la Twitter Firehose.

Después de recopilar estos tweets y analizarlos para ofrecer las estadísticas básicas de audiencia social, SecondSync se apoya en algunas de las otras categorías que hemos definido para ofrecer algunas estadísticas adicionales:

  - **Perfil del usuario.** Usado junto con el contenido de los tweets para ofrecer estadísticas relativas al sexo de la audiencia. Además, el saber si es una cuenta verificada les permite detectar perfiles muy influyentes.
  - **Social.** Usado principalmente para extraer información sobre las impresiones potenciales generadas por los tweets que hablan de los programas analizados, pero también útil para reconocer perfiles influyentes que estén hablando de estos.

> [Data provided by secondsync](http://secondsync.com/services/how-we-work-with-you.html)

1 Transmission metrics

  - Total Tweet volume
  - Unique users
  - Tweets per minute
  - Average TPM
  - Peak TPM
  - Share by time
  - Share by day
  - Potential impressions
  - Gender
  - Twitter client
  - Hashtag analysis
  - Verified accounts
  - Tweet data

2 Series metrics

  - Total tweet volume
  - Average TX Volume
  - Tweets per day
  - Tweets per TX
  - Average TPM
  - Peak TPM
  - Average peak TPM
  - Average gender

3 Channel metrics

  - Average daily volume
  - Average TPM
  - Peak TPM
  - Tweets per day
  - Average gender
  - Transmissions

### 2) [Bluefin labs](https://bluefinlabs.com/solutions/network/)

Bluefin se centra en la televisión, pero con un enfoque diferente al de SecondSync. Si bien SecondSync se centraba básicamente en el contenido para ofrecer una serie de estadísticas, Bluefin se centra en intentar saber qué dice la gente sobre un programa o un anuncio de la televisión, y cómo afecta a estos. Es decir, no solo ofrecen datos cuantitativos sobre los programas o anuncios, sino que relacionan estos datos con otros para ofrecer estadísticas sobre rendimiento de campañas publicitarias, listas de términos más repetidos, relaciones del tipo "a los usuarios que les gusta este programa le gusta este otro", etc.

Para realizar este tipo de análisis, Bluefin está también centrada en la categoría contenido con pequeñas pinceladas de perfil y social, pero a diferencia de SecondSync, los datos que ofrece están más procesados, y no se centran en ofrecer estadísticas de volúmenes y ratios, sino que usan machine learning para ir un paso más allá.

1 Measure the social activity of any TV show

  - Tweets
  - Authors
  - Gender
  - **Sentiment**
  - Timeline activity
  - **Key terms (popular terms) in social netwroks (word cloud)**
  - Top @mentions
  - hashtags

2 Gráficos interesante sobre gustos a modo de "sistema solar"

  - People who tweet about Glee also socually engage with...
  - List of lifestyle affinites for this show. Lifestyle - show

### 3) [BrandRiders](http://blog.thebrandriders.com/)

BrandRiders es una empresa española que ofrece una herramienta para gestionar una o más redes sociales a la vez. Si bien está pensada tanto para Facebook como para Twitter, y además incorpora un analizador de feeds RSS, nosotros nos centraremos en su motor de fidelización y sus informes, todo ello centrado en Twitter.

Su motor de fidelización consiste en una serie de sugerencias de acciones dentro de Twitter que ellos te proponen porque piensan que son beneficiosas para tu cuenta de Twitter: puede ser porque es muy probable que esa acción conlleve un retweet, o porque alguien te puede empezar a seguir por ello. Para ello, se basan en cálculos de afinidad entre cuentas, en el ratio de "followback" que tienen esas cuentas, y en categorización de contenido.

Esta sección está basada en el contenido, ya que te dice qué acciones tienes que hacer sobre qué contenido, pero por otro lado está enfocada a conseguir mejoras en la categoría de social.

[Captura motor de fidelización](http://blog.thebrandriders.com/es/wp-content/uploads/2013/11/Engagement.png)

Por otro lado, la herramienta también tiene una sección de analítica y reportes. En esta sección se puede ver información cuantitativa sobre la cuenta de Twitter que se esté monitorizando, como número de seguidores y amigos, historial de interacciones de generadas o audiencia potencial.

Esta sección está basada en las categorías social e interés, ya que se basa en cómo esa cuenta de Twitter está relacionada con el resto de usuarios de su red, y cómo ha cambiado en el tiempo.

> [Captura de pantalla analítica](http://blog.thebrandriders.com/es/wp-content/uploads/2013/11/Analitica.png)

1 Estatus general

  - Siguiendo
  - Seguidores
  - Audiencia potencial
  - tweets
  - listas
  - seguidores / siguiendo

2 **Interacciones generadas** (porcentajes)

  - Informativas: tweets sobre un hecho
  - Contendio propio: Posiblmenete busque términos cómo I've just created (está a 0)
  - Sectorial: ¿?
  - Respuestas: tweets como respuestas
  - Sugerencias: ¿?

3 Variaciones en el tiempo (tracking)

  - Seguidores
  - Siguiendo
  - Audencia potencial
  - Menciones
  - Retweets
  - Tweets

### 4) [Hoot Suite](https://hootsuite.com/)

> [Features de la aplicación](https://hootsuite.com/features/custom-analytics)

1 Tracking

  - Followers,
  - Following,
  - Lists,
  - Mentions
  - **Keywords comparing over time and Twitter sentiment**

### 5) [Follower wonk](https://followerwonk.com)

1 Twitter bios

  - Search users looking for exact match words in their bio.

2 **Compare users**

  - Me gusta mucho esta idea. Para 3 usuarios, cogemos sus followeers y hacemos el gráfico de discreta.

3 Analyze followers

  - Mapped locations of users who follow / are followed by the user
  - ***Social Authority***: it ranges from 1 to 100, where higher scores indicate a person with greater influential activity.
  - Gender percentage of useres who follow / are followed by the user.
  - Number of followers for those users who follow / are followed by the user
  - Number of following for those users who follow / are followes by the user
  - Number of tweets for those users...
  - account ages of users...
  - Last time the users... use them account
  - Langugaes of users...
  - percentage of tweets of users...
  - Retweets as a percent of timelines of users...
  - Tweets with @contact as a percent of timeline of users...
  - Most active hours for users...
  - **Most frequency words as a cloud**
  - **Most frequency two-words as a cloud**
  - **Most frequency location-word as a cloud**

4 Track Followers

  - No interesante

5 Sort Followers

  - No interesante, es un index

<a name="title-4">4 - wonk social authority</a>
---------------------------

  - Basado en retweets
  - Accesible via SDK => [social authority SDK](https://github.com/seomoz/Social-Authority-SDK)
  - Explicación detallada de cómo se calcula =>  [Cómo calcula la social authority](http://moz.com/blog/social-authority)

### ¿Cómo se calcula?

  1. **Tasa de retweets**: se utilizan aproximadamente los 100 últimos tweets del usuario que no contengan *@mention* a otros usuarios. La razón por la que se filtran los tweets con menciones es que existe una gran correlación entre el número de retweets que consigue un usuario con la tasa de menciones (en torno al 80%).
 Otro dato interesante en este punto es la relación entre la tasa de tweets retweteados y los tweets que contienen URLs - entorno al 70%.

  2. **Lo reciente que es el retweet**: Se favorece a los tweets a corto plazo contra tweets antigüos. Lo que se intenta es destacar el contenido que tiene impacto en twitter en cada instante.
  Se define la vida media de un tweet en 18 minutos.

  3. **Número de followers** Este tercer factor es el que menos peso tiene y se optimiza mediante un modelo de regresión "trained to retweet rate"; el objetivo de este modelo es suavizar los picos ocasionales de retweets de un usuario y, además, tiene en cuenta que hay una relación directa entre el número de seguidores y el porcetage de tweets retweeteados. Hasta el punto que a partir de los 10.000 seguidores se consigue una tasa de retweets del 25%

***

Extracto del blog de *moz* defendiendo el varemo de social authority en retweets mejor que en followers (tratado)

> @autocorrects is retweeted 7% more than @BarackObama, yet has 14 times fewer followers! As you can see, Social Authority surfaces a completely different set of top users: those that are extremely effective in engaging their followers. (…) They’ve discovered (these accounts) content that gets their audiences’ attention, whether we like it or not, and prompts action in terms of retweets and traffic.

