El websocket tiene sentido para la visualización de los tweets en el mapa, el contador de tweets totales y los datos de género, pero pierde su eficacia a la hora de alimentar a aquellos gráficos que están contextualizados en el tiempo. Parece mejor idea hacer un método en la API que genere un informe ad-hoc para todos los gráficos, con datos tageados en el tiempo para cada uno de los gráficos necesarios.

Cosas a considerar:
* ¿Una sola llamada a la API con todo el informe, o una llamada por gráfico? Primero me parecía mejor idea una sola llamada, ya que la idea es hacer una petición para el menor tiempo posible, y más peticiones serán más carga, pero pensándolo un poco, una petición por cada gráfico es más modular, más replicable, y nos permitirá responder a cada callback por separado.
* ¿Qué datos mostramos en el dashboard? Actualmente se me ocurren:
    * Mapa de tweets.
    * Contador de tweets, y tweets por minuto, segundo o lo que sea.
    * Gráfico de número de tweets por sexo, separado por categoría. Otra opción es en vez de poner el total, poner el porcentaje, pero se verá menos movimiento, parece que queda mejor la típica columna.
    * Grafíco de evolución de tweets. Tendrá que ser de una ventana de tiempo fija, que será el máximo que dará el servidor cuando se le pida ese dato.
    * Hashtags más frecuentes, con conteo de apariciones, en una ventana de tiempo fija. Digo hashtags y no palabras porque los hashtags vienen como hashtags entities en los envíos de Twitter, y eso nos soluciona el que haya demasiadas palabras por tweet. Además, los hashtags suelen ser contener bastante carga significativa.
    * Lo mismo para URLs.
    * ¿Quizá un feed en tiempo real?
* Hay que poner un botón para parar las actualizaciones, para que no se vuelva loco. De hecho, hay que evitar lo máximo posible que el explorador no se sobrecargue al llevar mucho tiempo abierto y generando datos. Estaría bien investigar si la sobrecarga actual es porque hay demasiados nodos en el DOM por culpa del feed en tiempo real, o si MapBox o los gráficos también son un problema.
* Hay que pensar la posibilidad de añadir una pestaña en la cual ya no ves los datos en tiempo real, sino que simplemente aprovecharíamos la API para pedir datos, pero esta vez en intervalos de tiempo. Habría que ampliar la API, pero no parece ser demasiado.
* Responsive. No parece importante, pero bootstrap debería hacerlo solo, y MapBox y Highchart tienen compatibilidad. Quizá con poco esfuerzo se puede dejar bien.
