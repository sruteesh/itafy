## Índice

> [1 Niveles de clasificación de la información](title-1)  
>  [1.1 Clasificación categórica](title-1-1)
>  [1.2 Clasificación por nivel de procesamiento](title-1-2)
>  [1.3 Clasificación por modelo](title1-3) 
> [2 Información extraíble de twitter](title-2)  
> [3 Clasificación de la información extraíble de twitter](title-3)  
> [4 Análisis de webs](title-4) 
>  [4.1 SecondSync](title-4-1)
>  [4.2 Bluefin Labs](title-4-2)
>  [4.3 Brand Riders](title-4-3)
>  [4.4 Hoot Suite](title-4-4) 
>  [4.5 Follower Wonk](title-4-5)
 
## <a name="title-1"/> 1 Niveles de clasificación de la información

Definimos tres niveles de clasificación para la información: por categoría, por nivel de procesamiento y por modelo al que pertenece dicha información.

### <a name="title-1-1"> 1.1 Clasificación categórica de la información 

Definimos las siguientes cinco posibles categorías:

 1. **Contenido**. Aquella información que es introducida por los usuarios voluntariamente, ya sea textual o audiovisual.  
 Ejemplos: un tweet, un mensaje directo.  
 Anotación: la información de perfil relativa a la biografía introducida por el propio usuario también se incluye en esta clasificación.
 2. **Social**. Aquella información que está que está relacionada de alguna forma con otra información, generando una red social alrededor de un contenido o de un usuario.  
 Ejemplos: un tweet que contiene una mención, los miembros de una lista.
 3. **Localización**. Aquella información de la que disponemos de datos geográficos. Habitualmente estos datos serán relativos al lugar de creación de dicha información, pero también está la posibilidad de obtener diferentes datos procesados según la ubicación.  
 Ejemplos: tweet con geolocalizado, trending topics por ubicación.
 4. **Temporal**. Aquella información que puede cambiar según el momento en el cual sea consultada. Obviamente la gran mayoría de la información es susceptible de cambiar (listas o tweets añadidos o borrados, relaciones creadas o destruidas, etc.), pero sólo la categorizamos como temporal si este cambio es por definición.  
 Ejemplos: recomendaciones de Twitter, trending topis.
 5. **Interés - Actividad**. Aquella información que incluye datos sobre la relevancia de ésta ya sea dentro de toda la red o para cierto número de personas.  
 Ejemplos: número de retweets o favoritos de un tweet.

> Nota: estas categorías no son excluyentes.

### <a name="title-1-2"/> Clasificación por nivel de procesamiento de la información 

Existen dos posibles niveles de procesamiento de la información:

 1. **Raw data**. Información que Twitter nos devuelve sin procesar, solamente nos la sirve. Constituye la gran mayoría de la información que nos ofrece.
 2. **Processed data**. Información que Twitter nos devuelve una vez que ha sido generada, como por ejemplo los *Trending Topic* y las recomendaciones.
 A diferencia de la información sin procesar, no sabemos de qué forma la obtiene, y por lo tanto no sabemos su fiabilidad o precisión.

> Nota: estas categorías son excluyentes.

### <a name="title-1-3"/> Clasificación según el modelo al que pertence

Abstractamente definimos tres modelos de información.

 1. **Usuario**. Integrante de la red que tiene una identidad, y que puede generar contenido y relacionarse con el resto de usuarios. El usuario no tiene porqué ser una persona física: un usuario puede ser la representación de una empresa o de un producto, por ejemplo.
 2. **Tweet**. Unidad básica de contenido de Twitter, formada por hasta un máximo de 140 caracteres, y que puede contener texto, información audiovisual o enlaces externos o internos a Twitter.
 3. **Actividad (relación)**. Definimos como actividad aquella información que establece una relación dentro de Twitter. Por ejemplo: una mención, un retweet o marcar un tweet como favorito.

> Nota: los tres modelos son no excluyentes.

## <a name="title-2"/> 2 - Información extraíble de Twitter

***Incluir aquí archivo + breve explicación (POR HACER)***

## <a name="title-3"/> 3- Clasificación de la información según los 3 niveles contemplados

1. **Tweets posteados por el usuario**: pertenece exclusivamente a la categoría de *contenido*; abstraemos el hecho de que sea posible que existan retweets, menciones y tweets marcados como favoritos en la lista de tweets (determinando así que no incluímos las categorías social e interés o actividad). Abstraemos también que el volumen de tweets cambie a lo largo del tiempo (no pertenecerá a la categoría temporal) y la posibilidad de que estos tweets sean geolocalizados al existir una llamda (23) para tratar expresamente este asunto (no pertenecerá tampoco a la categoría localización).   
Es información sin procesar por parte de Twitter y pertenece al modelo Tweet.

> Ver: [tweets del usuario](https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline)

2. **Mensajes directos por y para el usuario**: el hecho de mandar/recibir un mensaje directo a/de otro usuario, establece implícitamente una relación *social* entre A y B. El mensaje en sí mismo lo categorizamos como *contenido*, por lo tanto, pertenece a las categoráis *contenido* y *social*.  
La información no está procesada por parte de Twitter y, salvando las distancias, consideramos a los mensajes privados pertenecientes al modelo Tweet.
 
> Ver: [mensajes enviados](https://dev.twitter.com/docs/api/1.1/get/direct_messages/sent); [mensajes recibidos](https://dev.twitter.com/docs/api/1.1/get/direct_messages)

3. **Tweets del usuario retweeteados**: a pesar de que el objeto sobre el que estamos interactuando es un tweet (y por tanto estamos hablando de contenido), consideramos que un retweet no genera nuevo contenido a la red, sino que establece una relación de interés o actividad. Al igual que para el punto (1), abstraemos una serie de puntos: la posibilidad de menciones a otros usuarios, el cambio de volumen temporal y la geolocalización. Por ello pertenece únicamente a la categoría *interés - actividad*.  
La información no está procesada por parte de Twitter y existen dos modelos actuando sobre esta relación: Tweet y Actividad.
 
> Ver: [tweets retweeteados](https://dev.twitter.com/docs/api/1.1/get/statuses/retweets_of_me)

4. **Tweets marcados como favoritos por parte del usuario**: podemos aplicar los mismos criterios que el punto anterior (3); el objetivo central de este punto es la relevancia con la que ha dotado un usuario a un cierto contenido, por lo que la categoría será *interés - actividad*.  
La información no está procesada por parte de Twitter y los dos modelos que actúan son Tweet y Actividad. 

> Ver: [favoritos](https://dev.twitter.com/docs/api/1.1/get/favorites/list)

5. **Listas a las que está suscrito un usuario**: las categorías involucradas son *social* ya que partiendo del usuario podemos establecer una red alrededor del usuario, y *interés - actividad* al tratarse de una relación dentro de Twitter. 
La información no está pre procesada y los modelos que actúan son Tweet y Actividad. 

> Ver: [listas](https://dev.twitter.com/docs/api/1/get/lists)

6. **Usuarios que han retweeteado un tweet**: el razonamiento volverá a ser parecido que en el punto (3); a pesar de que los objetos sobre los que interactuamos son tweets (contenido) y usuarios (relación directa con información de perfil), la información principal de este punto es de *interés o actividad* por parte de N usuarios sobre 1 contenido concreto.   
La información no está procesada por parte de Twitter y considereamos los tres modelos involucrados: Usuario, Tweet y Actividad

> Ver: [usuarios interesados en un tweet](https://dev.twitter.com/docs/api/1.1/get/statuses/retweeters/ids)

7. **Buscar tweets**: Twitter permite un abanico muy grande de criterios de búsqueda de tweets; antes de determinar las categorías que podemos aplicar, diremos que algunas de las posibildiades a la hora de formular la query cuentan con un alto grado de procesamiento por parte de Twitter. Los puntos más destacables para las búsquedas son:

 - contienen una lista de términos (&&, ||)
 - contienen exáctamente una cadena
 - contienen texto con una actitud positiva
 - contienen texto con una actitud negativa
 - contienen una pregunta
 - lengua en la que están escritos
 - contienen un hashtag
 - contienen un @screen_name (una mención a otro usuario)
 - contienen contenido "sensitive" (contienen alguna URL)
 - son "populares" (no explica qué es popular)
 - publicados hasta una fecha
 - publicados antes de una fecha
 - son "recientes" en el tiempo
 - fueron publicados en un área concreta (geolocalización)

Debido a estas posibildiades, hemos decidido dividir la búsqueda de tweets en varios puntos.

> Ver: [búsqueda de tweets](https://dev.twitter.com/docs/api/1.1/get/search/tweets); [búsqueda avanzada](https://dev.twitter.com/docs/using-search)

7. A) **Buscar tweets por contenido** engloba las posibildiades de búsqueda relacionadas con el *contenido*: lista de términos, cadena exacta, texto con actitud positiva, negativa, contienen una pregunta, lengua en la que están escritos.

7. B) **Buscar tweets por interacción social** engloba las posibilidades de búsqueda relacionadas con las interacciones *sociales*: contienen un hashtag, contienen una mención a otro usuario, contienen alguna URL, son "populares".

7. C) **Buscar tweets por temporalidad** engloba las posibilidades de búsqueda relacionadas con la *temporalidad* de los tweets: publicados hasta / antes de una fecha, son "recientes" en el tiempo.

7. D) **Buscar tweets por localización** engloba las posiblidades de búsqueda relacionadas con la *localización* de los tweets: área geográfica de publicación

8. **Buscar usuarios recomendados (general)**: Sistema de recomendación de usuarios de Twitter. Pertenece a la categoría *interés - actividad* ya que las recomendaciones están hechas en función de los intereses mostrados por los usuarios. Tal y como explica en la documentación de Twitter, esta información tiene una fuerte dependencia *temporal*.  
La información está pre-procesada por Twitter y considera al modelo usuario

> Ver: [usuarios recomendados](https://dev.twitter.com/docs/api/1.1/get/users/suggestions)

8. **Buscar usuarios recomendados para una categoría**: Twitter determina ciertos usuarios claves para una serie de categorías definidas previamente por el propio Twitter; estamos hablando de información procesada por Twitter. Pertence principalmente a la categoría *interés - actividad* ya que se supone que estos usuarios son "lideres" en su ámbito; sin embargo esta vez determinamos que pertenece también a la categoría *temporal* ya que este "status de liderazgo" es completamente temporal tal y como se expresa en la documentación de twitter.  
Pertenece únicamente al modelo usuario.
 
> Ver: [usuarios recomendados por categoría](https://dev.twitter.com/docs/api/1/get/users/suggestions/%3Aslug)

9. **Biografía y descripción de un usuario**: la información de perfil relativa a la biografía introducida por el propio usuario, *contenido*.  
La información no está procesada por Twitter. El modelo abstracto de datos es el usuario

> Ver: [información extraible de un usuario](https://dev.twitter.com/docs/platform-objects/users)

11. **Url publicada por el usuario**: información de perfil introducida por el propio usuario. El motivo por el que hemos separado la información extraíble de un usuario (usuario entendido como entidad definida por Twitter) es que podemos clasificar distintos campos con diferentes categorías. La url publicada por el usuario pertenece a la categoría *social* en cuanto a que está creando una pequeña red de información externa en torno al usuario.  
La información no está procesada. El modelo es actividad.

> Ver: [información extraible de un usuario](https://dev.twitter.com/docs/platform-objects/users)

10. **Número de veces marcado como favorito**: Número de veces que los tweets publicados por el usuario han sido marcados como favoritos. Esta información puede ser entendida como un reflejo de la relevancia o *interés* que este usuario suscita dentro de la red.  
Es información sin procesar y consideramos a los modelos usuario y actividad. 
 
> Ver: [información extraible de un usuario](https://dev.twitter.com/docs/platform-objects/users)

12. **Tweets con contenido "sensitive"** (información extraída del propio modelo Tweet): 

13. **Usuario bloqueados (varios niveles)**

14. **Lista de amigos del usuario**

15. **Lista de followers**

16. **Relación entre dos usuarios**

18. **Número de followers - friends**

19. **Cadena de respuestas a un tweet**

> Ver: [Información extraíble de un tweet](https://dev.twitter.com/docs/platform-objects/tweets)


22. **Trending topics geográficamente**

> Ver: [lista de trending topics por área](https://dev.twitter.com/docs/api/1.1/get/trends/place); 

23. **Localización de un tweet**

24. **Timeline de un usuario**

A continuación, expresamos de forma concisa la clasificación según los tres niveles.

| #   | Información 								| Categorías 				  | Procesamiento | Modelo 			 		|
|:---:|:------------								|:-----------				  |:------------- |:-------			 		|
| 1   | Tweets posteados por el usuario 			| contenido 				  | RAW 		  | Tweet				 	|
| 2   | Mensajes directos por y para el usuario 	| contenido, social 		  | RAW 		  | Tweet 			 		|
| 3   | Tweets del usuario retweeteados 			| interés - actividad 		  | RAW 		  | Tweet, Actividad 	 	|
| 4   | Tweets marcados como favoritos 				| interés - actividad		  | RAW 		  | Tweet, Actividad   		|
| 5   | Listas a las que está suscrito un usuario 	| social, interés - actividad | RAW 		  | Usuario, Actividad 		|
| 6   | Usuarios que han retweetado un tweet 		| interés - actividad 		  | RAW 		  | Tweet, Usuario, Actividad |
| 7 A | Búsqueda de Tweets por contenido 			| contenido 				  | PROCESSED     | Tweet 			 		|
| 7 B | Búsqueda de Tweets por interacción social   | social 					  | PROCESSED     | Tweet, Actividad 		|
| 7 C | Búsqueda de Tweets por temporalidad 		| temporal 					  | PROCESSED     | Tweet 			 		|
| 7 D | Búsqueda de Tweets por localización 		| localización 				  | PROCESSED     | Tweet 			 		|
| 8   | Usuarios recomendados (general) 			| interés, temporal 		  | PROCESSED 	  | Usuario  				|
| 8   | Usuarios recomendados para una categoría 	| interés, temporal 		  | PROCESSED 	  | Usuario  				| 
| 9   | Biografía y descripción de un Usuario 		| contenido 				  | RAW 		  | Usuario 			 	|
| 10  | Número de veces marcado como favorito 		| interés - actividad 		  | RAW 		  | Usuario, Actividad 		|
| 11  | Url publicada por el usuario 				| social 		 			  | RAW 		  | Actividad 			 	|

| 12  | Tweets con contenido "sensitive" 			| social 					| RAW 		  	| Actividad 			 	|
| 13  | Usuarios bloqueados (varios niveles)		| social, interés 			| RAW 		  	| Usuario, Actividad 	|
| 14  | Lista de amigos del usuario 				| social, interés  	 		| RAW 			| Usuario, Actividad 	|
| 15  | Lista de followers							| social, interés   		| RAW 			| usuario, Actividad 	|
| 16  | Relación entre dos usuarios 				| social, interés   		| RAW 			| Usuario, Actividad 	|
| 18  | Número de followers - friends (User)		| social, interés 			| RAW 			| Usuario 				|
| 19  | Cadena de respuestas a un Tweet				| social, interés 			| RAW 			| Tweet, Actividad 		|
| 22  | Trending Topics geográficamente 			| social, interés, geo, temporal | PROCESSED 	| Tweet, Actividad 		|
| 23  | localización de un Tweet (Tweet)			| contenido, geo 				| RAW 			| Tweet 				|
| 24  | Timeline de un usuario						| contenido, social, temporal 	| RAW 			| Tweet, Actividad 		|

## <a name="title-4"/> 4 - Análisis de webs

Para los siguientes 5 servicios, hacemos un análisis de las funciones que ofrecen; las funciones que no son "directas" se marcan en negrita

### <a name="title-4-1"/> 4.1 [SecondSync](http://secondsync.com/)

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

### <a name="title-4-2"/> 4.2 [Bluefin labs](https://bluefinlabs.com/solutions/network/)

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

### <a name="title-4-3"/> 4.3 [BrandRiders](http://blog.thebrandriders.com/)

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

### <a name="title-4-4"/> 4.4 [Hoot Suite](https://hootsuite.com/)

> [Features de la aplicación](https://hootsuite.com/features/custom-analytics)

1 Tracking

  - Followers,
  - Following,
  - Lists,
  - Mentions
  - **Keywords comparing over time and Twitter sentiment**

### <a name="title-4-5"/> 4.5 [Follower wonk](https://followerwonk.com)

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

### wonk social authority

 - Basado en retweets
 - Accesible via SDK => [social authority SDK](https://github.com/seomoz/Social-Authority-SDK)
 - Explicación detallada de cómo se calcula =>  [Cómo calcula la social authority](http://moz.com/blog/social-authority)

**¿Cómo se calcula?**

1. **Tasa de retweets**: se utilizan aproximadamente los 100 últimos tweets del usuario que no contengan *@mention* a otros usuarios. La razón por la que se filtran los tweets con menciones es que existe una gran correlación entre el número de retweets que consigue un usuario con la tasa de menciones (en torno al 80%).
Otro dato interesante en este punto es la relación entre la tasa de tweets retweteados y los tweets que contienen URLs - entorno al 70%.

2. **Lo reciente que es el retweet**: Se favorece a los tweets a corto plazo contra tweets antigüos. Lo que se intenta es destacar el contenido que tiene impacto en twitter en cada instante.
Se define la vida media de un tweet en 18 minutos.

3. **Número de followers** Este tercer factor es el que menos peso tiene y se optimiza mediante un modelo de regresión "trained to retweet rate"; el objetivo de este modelo es suavizar los picos ocasionales de retweets de un usuario y, además, tiene en cuenta que hay una relación directa entre el número de seguidores y el porcetage de tweets retweeteados. Hasta el punto que a partir de los 10.000 seguidores se consigue una tasa de retweets del 25%

***

Extracto del blog de *moz* defendiendo el varemo de social authority en retweets mejor que en followers (tratado)

> @autocorrects is retweeted 7% more than @BarackObama, yet has 14 times fewer followers! As you can see, Social Authority surfaces a completely different set of top users: those that are extremely effective in engaging their followers. (…) They’ve discovered (these accounts) content that gets their audiences’ attention, whether we like it or not, and prompts action in terms of retweets and traffic.

