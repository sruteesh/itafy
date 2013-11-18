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

| Información 									| Categorías 						| Nivel procesamiento 	| Modelo 			 |
|:------------									|:-----------						|:--------------------	|:-------			 |
| Tweets posteados por el usuario 				| contenido, social 				| RAW 					| Tweet				 |
| Mensajes directos por y para el usuario 		| Contenido, social 				| RAW 					| Tweet 			 |
| Tweets del usuario retweeteados 				| contenido, social, interés 		| RAW 					| Tweet, Actividad 	 |
| Tweets marcados como favoritos por el usuario | contenido, social, interés 		| RAW 					| Tweet, Actividad   |
| Listas a las que está suscrito un usuario 	| social, interés 					| RAW 					| Actividad 		 |
| Usuarios que han retweetado un tweet 			| social, interés 					| RAW 					| Tweet, Usuario 	 |
| Buscar tweets por términos que contiene 		| contenido, interés 				| PROCESSED 			| Tweet 			 |
| Buscar usuarios recomendados para una cate. 	| social, interés 					| PROCESSED 			| Usuario, Actividad |
| Descripción de un usuario (modelo User)				|
| Número de veces marcado como favorito (modelo User)				|
| Tweets publicados por un usuario (modelo User)				|
| Url publicada por el usuario (modelo User)				|
| Tweets con usl (sensitive) (modelo Tweet)				|
| Usuarios bloqueados (dos niveles de bloqueo)				|
| Lista de amigos del usuario (following)				|
| Lista de followers				|
| Relación entre dos usuarios (following, followed_by, none)				|
| Buscar tweets por menciones a otros usuarios - popularidad				|
| Número de followers - friends (modelo User)				|
| Cadena de tweets (respuestas) (modelo Tweet)				|
| time zone y language del usuario (modelos User)				|
| Buscar tweets en un radio (geo)				|
| Top 10 de trending topics en un radio (geo) **Temporal**				|
| localización de un Tweet (modelo Tweet)				|
| Timeline de un usuario				|
| Buscar tweets en un rango de fechas				|
| Top 10 de trending topics en un radio (geo) **Localización**				|
