BUZZER
======

Buzzer es un sistema de recomendación por contenidos porque para hacer el ranking de articulos, usamos términos que salgan en RSS y Twitter. 

Intro
------

1. Problema a solucionar: identificar *topical news stories*  
Los recomendadores actuales no identifican los artículos sobre nuevos topics ya que para reconocer un artículo esperan a que sea consumdo por una gran cantidad de usuarios

2. Propone: sistema de recomendación de artículos usando Twitter como fuente de *current topical news*  => Buzzer

3. Resumen de Buzzer: Usan Twitter para identificar los news topics, lo que llama breaking events. A parte, tenemos un RSS (yo lo entiendo como un google reader) y usamos la info de twitter para actualizar en tiempo real el ranking del google reader. Por detrás de esto están usando Lucene.

Buzzer 
-------

### 1. Estado del arte de Buzzer

 - Digg.com : servicio que se basa en el contenido y en técnicas de filtrado colaborativas.
 - Krakatoa Chronicle : los perfiles de los usuarios son un vector con pesos de los terminos que aparecen en los articulos que han marcado los usuarios como que les gustan. Este vector de peso lo contrstamos con un nuevo conjunto de articulos 
 - News dude: se basa en el contenido también. Es como su "rival"

### 2. Arquitectura de Buzzer

  - WEB: interfaz con el usuario en el que facilita su twitter y una lista de RSS que le mola
  - Lucene: el que hace el trabajo de indexar
  - Recomendation engine: procesamiento

### 3. Funcionamiento

1. Sacamos los últimos artículos R y los últimos tweets T (* aclaración)
2. Para cada conjunto, hacemos un indexado por separado con Lucene, es decir tenemos 2 lucene indexes del que sacamos dos vectores: MR y MT
3. Hacemos la intersección de MR y MT y obtenemos el conjunto de términos de interés t 
4. Usamos ti € t como query para sacar el conjunto A de artículos que contienen t. Cada articulo ai € A tiene una puntuación IDF
5. Tenemos para cada ti una lista de artículos Ai. Cada artículo Aij tiene una puntuación => hacemos el sumatorio de puntuaciones para cada Aij
6. Actualizamos el ranking de artículos
 
**Aclaración**: Se permite elegir los tweets

- Time line
- Amigos del usuario (no aclara en ningún momento qué entiende por amigos del usuario)

### 4. ¿Para qué ha servido twitter?

> In this way, articles which contain many tweet terms with high TF-IDF scores are preferred to articles that contain fewer tweet terms with lower TF-IDF scores.


### 5. Prueba de Buzzer

Grupo de 10 personas de prueba durante 5 días. Cada usuario daba la lista de los 10 RSS feed favoritos. 

**Conclusiones**: que mola mucho usar twitter y que mejor el tema de los tweets de los amigos que usando el time line del usuario.
