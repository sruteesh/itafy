twittomender: Recomendación de usuarios
=====================

#### Objetivo

Dado un usuario U, recomendar nuevos ***U's followees*** basándonos en el contenido y usando real-time.

#### Términos clave

- Collaborative filtering
- Content based recommendation

#### Qué nos interesa de este paper

1. Diferentes técnicas para el *profiling* 
2. Implementación de un recomendador basado en contenido y refinando con filtros colaborativos (collaborative filtering style approaches)

Twittomender
------------

### 1) Descripción del sistema

Dos modos de uso:
 - Introducionendo una query 
 - Usando el perfil del propio usuario

### 1) Arquitectura

Información para hacer el perfil de un usuario (usando la API de tweeter)

1. Tweets
2. Followers
3. Followees

### 3) Profiling users

1. Usando los últimos tweets del usuario
tweets(Ut) = {t1, t2, … tk}
2. Usando los tweets de sus followees
followees(Ut) = {f1, … fm}
followee_tweets(Ut) = U(tweets(fi))
3. tweets de sus followers
4. ids de sus followees => collaborative filtering
5. ids de sus followers => collaborative filtering

> Entiende los "últimos" tweets como los últimos 100 tweets

Entiende el perfil de cada usuario como un documento que podemos indexar usando Lucene y así obtener un vector ordenado por pesos de los términos. 
Tenemos para cada usuario Ut profile(Ut) un vector con pesos :termino => peso

La fuente para generar el perfil de un usaurio es cualquiera de los 5 abnteriores.

El peso de cada término, se puede elegir, en este caso usa el TF-IDF de cada término: proporcional a la frecuencia de aparición en el perfil del usuario e inversamente proporcional a la frecuencia en el resto de perfiles. 
Es decir, si cogemos el perfil [1] un peso alto significa que es algo común en el perfil del usuario pero inusual en el resto de la población. 

### Probando

Tenemos dos conjuntos de usuarios
los test user = 1.000
El training set = 19.000 users (partiendo de un núcleo cogemos los *ers* y *ees*)

El perfil de los usuarios lo hacen con 9 posibilidades (combinando lo de arriba)

Para cada usuario cogen de lo que le devuelve lucene el top 5-20 y lo comprueban con los que ya sabes que están siguiendo. 

La medida que toman es de precisión.

1. Funciona mejor las colaborativas. 
2. La que se espera mejor (tweets de los *ees*) funciona mal.
3. La que tenía pinta de ir peor (tweets de los *ers*) funciona bien.
4. Gana Id's de los followees

Tomando como medida la effectiveness (position de una relevant recommendation)

1. Aquellas que hablando de precisión lo hacen mal, tienen buenos resultados hablando de efectividad y viceversa; sobretodo si tomamos una k alta

¿Problema hasta ahora?
La medida de acierto no es buena. Puede que sí me esté dando cosas que me interesan (considero bueno si ya era un followee). Se pone en práctica con 34 usuarios durante 1 mes.

Acada uno se le presenta 30 recomendaciones (filtrando los que no son nuevos). y el sistema acierta 6.9.

Si dejamos al usuario meter palabras la cifra de aciertos baja a 4.9

