# Arquitectura del proyecto

Párrafo introductorio



* [Play!](http://www.playframework.com/) framework basado en java [^play]
* Basado en la API de Twitter, tanto REST como Streaming
* [Akka](http://akka.io/) para manejo de hilos 
* Amazon, con una instancia de C1 (instancias optimizadas para informática) por horas, o sino una micro sin pararla [http://aws.amazon.com/es/ec2/instance-types/]. (Calculadora de precios de amazon)[http://calculator.s3.amazonaws.com/calc5.html]
* Base de datos en MongoDB, y (Mongoid como gema que abstraiga el driver)[http://mongoid.org/en/mongoid/index.html]
* (d3 para la visualización)[http://d3js.org/]
* (Hadoop para el procesado de datos)[http://hadoop.apache.org/]

[^play]: [Play!](http://www.playframework.com/) puede basarse tanto en Scala como en Java 

![Esquemático de la arquitectura](https://docs.google.com/drawings/d/1BWEYdXQHGMJgDdOXOXhsPMQN0cmBlwLstOy5w0vbXzg/pub?w=1010&h=724)
***arquitectura del proyecto***
