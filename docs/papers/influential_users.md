influencial users
=================

Intro
-----

72% de los usuarios cumplen que el 80% de lo que siguen son gente que les sigue a ellos. => reciprocidad debido a la *homophily*

homophily: tendencia a asociarse con otros individuos de la misma clase.

Objetivo: medir en un ranking la influencia de los usuarios.

1. podríamos ordenar la real-time web según la influencia del autor.
2. targeting influential users will increase the efficiency of a marketing campaign

Estado del arte: influencia = número de seguidores.

Tenemos a la vez gente que sigue por compromiso y gente que efectivamente está siguiendo algo que le interesa => homophily

---

1. Analizar contenido de los tweets => interesting topic for each user.
2. Se crea una estructura de relaciones entre usuarios por topics
3. PageRank algorithm

Dataset: S = top 1000 twitters
S' = S {|s| s.ers + s.ees}
|S'| = 6748
T = {tweets}
|T| = 1.000.000

S' - no publicadores - robots

Cifras estadísticas: 15% no ha publicado jamás un tweet
La media de tweets publicados => 179,57
¿Limitación de la API? 3200 tweets dado un usuario.

En el conjunto S en el que vamos a movernos hay 50.000 relaciones 
hay 1000 sin seguir a nadie y 2000 sin nadie que les siga. 


El punto del paper está en: desde el punto de vista de intereses:
 si A ==> B implica que tienen intereses comunes ?
 si A <==> B implica que tienen intereses comunes ?
 
 Necesitamos saber de qué hablan
 
### Topic Distillation
 
 Saber los topics en los que están interesados. Usa LDA Latent Dirichlet Allocation (LDA) model 
 
 Partimos de una bolsa de palabras. each topic is represented as a probabil- ity distribution over a number of words.
 
 Cada documento es un vector-conteo de palabras.  each document is represented as a probability distribution over some topics
 

 