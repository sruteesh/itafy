Clasificación de información
===========

Definimos 3 niveles de clasificación para la información

1) Clasificación categórica de la información 

 - **Contenido**
 - **Social**
 - **Localización**
 - **Temporal**
 - **Interés - Actividad**
 - **Perfil de usuario**

> Nota: la clasificación por categorías no es excluyente.

2) Clasificación por nivel de procesamiento de la información

 - **Raw data**
 - **Processed data**

> Nota: la clasificación por nivel de procesamiento sí es excluyente.

3) Clasificación según el modelo al que pertence

 - **Usuario**
 - **Tweet**
 - **Actividad (relación)**

> Nota: La clasificación por nivel de procesamiento no es excluyente.

***

| Información 									| Categorías 						| Nivel procesamiento 	| Modelo 			 	|
|:------------									|:-----------						|:--------------------	|:-------			 	|
| Tweets posteados por el usuario 				| contenido, social 				| RAW 					| Tweet				 	|
| Mensajes directos por y para el usuario 		| Contenido, social 				| RAW 					| Tweet 			 	|
| Tweets del usuario retweeteados 				| contenido, social, interés 		| RAW 					| Tweet, Actividad 	 	|
| Tweets marcados como favoritos por el usuario | contenido, social, interés 		| RAW 					| Tweet, Actividad   	|
| Listas a las que está suscrito un usuario 	| social, interés 					| RAW 					| Actividad 		 	|
| Usuarios que han retweetado un tweet 			| social, interés 			 		| RAW 					| Tweet, Usuario 	 	|
| Buscar tweets por términos que contiene 		| contenido, interés 				| PROCESSED 			| Tweet 			 	|
| Buscar usuarios recomendados para una cate. 	| social, interés, temporal 		| PROCESSED 			| Usuario, Actividad 	|
| Descripción de un usuario (User)				| perfil 							| RAW 					| Usuario 			 	|
| Número de veces marcado como favorito (User)	| social, interés 					| RAW 					| Usuario, Actividad 	| 
| Url publicada por el usuario (User)			| perfil, 							| RAW 					| Usuario 			 	|
| Tweets con contenido "sensitive" (Tweet)		| contenido 						| RAW 					| Tweet 			 	| 
| Usuarios bloqueados (dos niveles de bloqueo)	| social, interés 					| RAW 					| Usuario, Actividad 	|
| Lista de amigos del usuario (following)		| social, interés, 	 				| RAW 					| Usuario, Actividad 	|
| Lista de followers							| social, interés,  				| RAW 					| usuario, Actividad 	|
| Relación entre dos usuarios 					| social , interés, 				| RAW 					| Usuario, Actividad 	|
| Buscar tweets por menciones a otros usuarios  | contenido, social 				| RAW 					| Usuario, Tweet, Actividad |
| Número de followers - friends (User)			| social, interés 					| RAW 					| Usuario 				|
| Cadena de tweets (respuestas) (Tweet)			| social, interés 					| RAW 					| Tweet, Actividad 		|
| time zone y language del usuario (User)		| Perfil, geo 						| RAW 					| Usuario 				|
| Buscar tweets en un radio (geo)				| contenido, geo 					| RAW 					| Tweet 				|
| Top 10 de trending topics en un radio (geo) 	| social, interés, geo, temporal 	| PROCESSED 			| Tweet, Actividad 		|
| localización de un Tweet (Tweet)				| contenido, geo 					| RAW 					| Tweet 				|
| Timeline de un usuario						| contenido, social, temporal 		| RAW 					| Tweet, Actividad 		|
| Buscar tweets en un rango de fechas			| contenido, temporal 				| PROCESSEd 			| Tweet 				|

***

Análisis de webs
-----------

### [secondsync](http://secondsync.com/)

> [Data provided by secondsync](http://secondsync.com/services/how-we-work-with-you.html)
 
 1. Transmission metrics

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

2. Series metrics

 - Total tweet volume
 - Average TX Volume
 - Tweets per day
 - Tweets per TX
 - Average TPM
 - Peak TPM
 - Avarage peak TPM
 - Average gender

3. Channel metrics

 - Average daily volume
 - Average TPM
 - Peak TPM
 - Tweets per day
 - Average gender
 - Transmissions

### [Blue fin labs](https://bluefinlabs.com/solutions/network/)

 1. Measure the social activity of any TV show

  - Tweets
  - Authors
  - Gender
  - **Sentiment**
  - Timeline activity
  - **Key terms (popular terms) in social netwroks (word cloud)**
  - Top @mentions
  - hashtags

  2. Gráficos interesante sobre gustos a modo de "sistema solar"

   - People who tweet about Glee also socually engage with...
   - List of lifestyle affinites for this show. Lifestyle - show

### [BrandRiders](http://blog.thebrandriders.com/)

> [Caputa de pantalla](http://blog.thebrandriders.com/es/wp-content/uploads/2013/11/Analitica.png)

 1. Estatus general 

  - Siguiendo
  - Seguidores
  - Audiencia potencial
  - tweets
  - listas
  - seguidores / siguiendo

  2. **Interacciones generadas** (porcentajes)

   - Informativas: tweets sobre un hecho
   - Contendio propio: Posiblmenete busque términos cómo I've just created (está a 0) 
   - Sectorial: ¿?
   - Respuestas: tweets como respuestas
   - Sugerencias: ¿?

  3. Variaciones en el tiempo (tracking)

   - Seguidores
   - Siguiendo
   - Audencia potencial
   - Menciones
   - Retweets
   - Tweets

### [Hoot Suite](https://hootsuite.com/)

 > [hootsuite features](https://hootsuite.com/features/custom-analytics)

 1. Tracking 

 - Followers, 
 - Following, 
 - Lists, 
 - Mentions
 - **Keywords comparing over time and Twitter sentiment**

### [Follower wonk](https://followerwonk.com)

1. Twitter bios
 
 - Search users looking for exact match words in their bio.

2. **Compare users**

 - Me gusta mucho esta idea. Para 3 usuarios, cogemos sus followeers y hacemos el gráfico de discreta.

3. Analyze followers
 
 - Mapped locations of users who follow / are followed by the user
 - **Social Authority** (rating of a user's influence and engagement on Twitter). It ranges from 1 to 100, where higher scores indicate a person with greater influential activity.
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

4. Track Followers
 - No interesante

5. Sort Followers
 - Es un index


***

#### wonk social authority 

 - Basado en retweets
 - **Accesible via SDK** => [social authority SDK](https://github.com/seomoz/Social-Authority-SDK)
 - Explicación detallada de cómo se calcula =>  [Cómo calcula la social authority](http://moz.com/blog/social-authority)

En resumen, tiene en cuenta

 1. *The retweet rate* of a few hundred of the measured user’s last non-@mention tweets
 2. *Recency of those tweets*: A time decay to favor recent activity versus ancient history
 3. Other data for each user (such as follower count, friend count, and so on) that are optimized via a *regression model* trained to retweet rate
