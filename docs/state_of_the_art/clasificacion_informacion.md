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
