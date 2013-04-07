# ITAFY

Twitter es una red social muy potente, pero que hoy por hoy es demasiado caótica y con demasiado ruido. Nuestra idea es hacer una herramienta que permita seguir disfrutando de Twitter, pero que a la vez fomente una forma más inteligente de consumir información.


## Qué

Un cliente de Twitter inteligente, que permita leer Twitter de una forma más eficiente. Para ello, la herramienta clasificará a la gente a la que sigues para formar listas temáticas, que sirvan de “canales” de lectura. De esta forma, el usuario decide qué canal quiere leer realmente, sin tener que ver mezclados tweets personales, de informática, de música, etc. Además, estas listas podrían ser interesantes para otros usuarios, por lo que habría la posibilidad de compartirlas y votarlas.

Por otro lado, la herramienta permitirá descubrir contenido, ya sea en tu timeline o en las listas anteriormente creadas. Analizando los tweets de cualquiera de las fuentes, generará una lista de contenido interesante, indicando la fuente, quién lo tuiteó originalmente, quién te lo hizo llegar, qué texto le acompañaba, la categoría, el número de RTs y FAVs, etc. Esto permitirá limpiar Twitter de comentarios personales, de texto superfluo, para llegar directamente al contenido. Además, la herramienta irá mejorando con el uso, sabiendo por ejemplo qué fuentes sueles leer siempre, o que categoría habitualmente te interesa más, priorizando esos tweets en futuros filtrados.


## Cómo

La herramienta será web, y usará el login de Twitter [_[1]_][loginTW], para de esta forma tener la autorización del usuario para usar sus credenciales. Cuando se use por primera vez, la herramienta analizará uno a uno a la gente que sigue ese usuario, y que son quiénes generan su timeline. Esta categorización se guarda de forma global, no local, por si en el futuro alguien pide analizar el mismo usuario, y así evitar repetir el trabajo en el futuro.

Para analizar a un usuario, la idea principal es analizar el contenido que comparte en Twitter y, más secundariamente, el contenido que le llega. Más concretamente, nuestra idea es analizar las keywords y otros metadatos de los links de ese contenido. Estos metadatos, definidos por los administradores de las páginas, indican a los buscadores las temáticas de las que trata la web en concreto, por lo que gracias a ellos podemos saber sobre qué temáticas trata el contenido que comparte el usuario. De esta forma, si comparte webs de economía, podemos darle la categoría "Economía", entre otras, y mostrar los tweets de este usuario cuando uno de sus seguidores quiera leer sobre economía. Por ejemplo, estos son las keywords de marca.com, que vemos que definen a la perfección la página:

`<meta name="keywords" content="MARCA.com, marca.com, diario MARCA, radio MARCA, deporte, sport, España, fútbol,baloncesto, ACB, NBA, Euroliga, balonmano, fórmula 1, F1, motociclismo, rallies, raids, tenis, atletismo, golf, ciclismo, vela, rugby, internet, news, resultados, clasificaciones, blogs, opinion, juegos, videos, graficos, fotografias " />`

Por otro lado, podemos sacar la biografía [_[2]_][biography] de cada usuario. Esta biografía está escrita por él mismo, y en un porcentaje bastante alto de las veces está compuesta por palabras sueltas, ya que no hay casi espacio para describirte. Gracias a esto, podemos encontrar palabras clave que nos den más datos, y nos ayuden a categorizar al usuario. Por ejemplo, entre la gente a la que yo sigo podemos encontrar muchas veces las palabras "estudiante" o "informático".

Una vez categorizados estos usuarios, la herramienta los agrupará por temáticas y creará listas con las temáticas más comunes entre ellos, usando las llamadas de la API de Twitter relativas a listas [_[3]_][listsApi]. El usuario podrá decidir si estas listas son públicas o privadas. Para la visualización, cuando el usuario elija ver una lista, se hará una llamada a la API para sacar el timeline de esa lista [_[4]_][timelineLists]. Aprovechando que se sacan los tweets para la visualización, esos tweets se guardarán, para que poco a poco el servidor vaya analizándolos, para de esta forma ir adelantando trabajo para la zona de descubrir contenido.

En la zona de descubrir contenido, el proceso es parecido. Igual que en la visualización de listas, se sacan todos los tweets, pero esta vez primero se analizan. Este análisis modifica los registros globales: si el link ya se había analizado antes, se sacan datos de uso, importancia, etc. para que el algoritmo evalúe su posición en la lista. Si no existe, se crea un registro para el link. Así, la zona de descubrir contenido irá mejorando de forma colaborativa, pudiendo ofrecer los links más compartidos de temáticas relacionadas, o los más vistos, por ejemplo.

Finalmente, explicar que en las dos secciones de la herramienta habría la posibilidad de hacer retweets o hacer favorito, ya que esto permitirá que no haya que ir al propio Twitter para hacer esto, y además es un dato muy valioso para saber la importancia y relevancia del contenido.


## Competencia

Esta es una lista de servicios que se centran en la gestión de Twitter, en descubrir contenido, o similares, y que pueden ser interesantes para analizarlos y aprender de ellos:

* **[Delicio.us](http://www.delicio.us)**. Sirve para guardar y organizar tus enlaces mediante etiquetas. Como es un sitio popular, es probable que mucha gente haya guardado ese enlace, y puedes ver cuáles son las etiquetas más usadas, en una especie de "categorizador" colaborativo. Además, puedes ver los links más guardados por temática, o los links de un usuario en su feed.
* **[Socialbro.com](http://www.socialbro.com)**. Es un analizador de Twitter, que da métricas sobre casi cualquier cosa: qué usuarios sigues que tuitean más, quién te sigue que es influyente, etc. Para nosotros es interesante la parte en la que da una nube de etiquetas con las biografías de la gente que sigues, y de la gente a la que sigues. Es bastante preciso a la hora de definir tus gustos e intereses. También permite organizar tus listas.
* **[TweetBe.at](http://tweetbe.at/)**. Se centra en organizar listas, y se vale de filtros para ello. Tiene una vista con el formato "Nombre - Ubicación - Biografía", y puedes ir agregando o quitando usuarios a las listas. Puedes filtrar por parámetros como menciones, retuits, % de follow back, etc.


## Referencias

* _[1]_ [Documentación sobre cómo usar el Sign in de Twitter.](https://dev.twitter.com/docs/auth/sign-twitter)
* _[2]_ [Todos los datos de un usuario, incluida su biografía.](https://dev.twitter.com/docs/api/1.1/get/account/verify_credentials)
* _[3]_ [Métodos sobre listas disponibles en la API de Twitter.](https://dev.twitter.com/docs/api/1.1#99)
* _[4]_ [Método de la API para conseguir el timeline de una lista en concreto.](https://dev.twitter.com/docs/api/1.1/get/lists/statuses)

[loginTW]: https://dev.twitter.com/docs/auth/sign-twitter "Login con Twitter"
[biography]: https://dev.twitter.com/docs/api/1.1/get/account/verify_credentials "Biografía en la API de Twitter"
[listsApi]: https://dev.twitter.com/docs/api/1.1#99 "Listas en la API de Twitter"
[timelineLists]: https://dev.twitter.com/docs/api/1.1/get/lists/statuses "TL de una lista en la API de Twitter"