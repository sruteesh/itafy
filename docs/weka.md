## Corpus final

Instances: 708
Actualidad: 543
Deportes: 165
Attributes: 2428

## Tree

### 1. BFtree

Sin memoria en la pila

### 2. J48

árbol muy pequeño

```
Madrid <= 0
|   equipo <= 0: ACTUALIDAD (644.0/125.0)
|   equipo > 0
|   |   blanco <= 0
|   |   |   ofensivo <= 0: ACTUALIDAD (20.0/4.0)
|   |   |   ofensivo > 0: DEPORTES (2.0)
|   |   blanco > 0: DEPORTES (3.0)
Madrid > 0
|   Akiyama <= 0
|   |   Aguirre <= 0: DEPORTES (34.0/3.0)
|   |   Aguirre > 0: ACTUALIDAD (2.0)
|   Akiyama > 0: ACTUALIDAD (3.0)

Number of Leaves  : 	7

Size of the tree : 	13
```


### 3. J48 Graft

No va a servir

```
Madrid <= 0
|   equipo <= 0: ACTUALIDAD (644.0/125.0)
|   equipo > 0
|   |   blanco <= 0
|   |   |   ofensivo <= 0: ACTUALIDAD (20.0/4.0)
|   |   |   ofensivo > 0: DEPORTES (2.0)
|   |   blanco > 0: DEPORTES (3.0)
Madrid > 0
|   Akiyama <= 0
|   |   Aguirre <= 0: DEPORTES (34.0/3.0)
|   |   Aguirre > 0
|   |   |   Real <= 0.5
|   |   |   |   montara <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   montara > 0.5
|   |   |   |   |   mitin <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   |   mitin > 0.5
|   |   |   |   |   |   expresidente <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   |   |   expresidente > 0.5
|   |   |   |   |   |   |   cosa <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   |   |   |   cosa > 0.5
|   |   |   |   |   |   |   |   asegura <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   |   |   |   |   asegura > 0.5
|   |   |   |   |   |   |   |   |   Esperanza <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   |   |   |   |   |   Esperanza > 0.5
|   |   |   |   |   |   |   |   |   |   Ahora <= 0.5: DEPORTES (0.0|34.0/3.0)
|   |   |   |   |   |   |   |   |   |   Ahora > 0.5: ACTUALIDAD (2.0)
|   |   |   Real > 0.5: DEPORTES (0.0|12.0)
|   Akiyama > 0: ACTUALIDAD (3.0)

Number of Leaves  : 	15

Size of the tree : 	29

```

### 4. FT

```
N0#1 <= 0.450156: FT_1:15/30 (131)
N0#1 > 0.450156
|   N0#3 <= 0.790447: Class=1
|   N0#3 > 0.790447: Class=0

Number of Leaves  : 	3

Size of the Tree : 	5
FT_N0#1:
Class 0 :
0.99 + 
[Barcelona] * -1.12 +
[Madrid] * -1.19 +
[Valencia] * -1.12 +
[final] * -1.28 +
[Ancelotti] * -1.27 +
[Chelsea] * -1.36 +
[Clippers] * -1.52 +
[James] * -1.51 +
[NBA] * -1.36 +
[blanco] * -1.26 +
[deporte] * -1.37 +
[futbol] * -1.28 +
[organismo] * -1.51 +
[tecnico] * -1.34 +
[titulo] * -1.09

Class 1 :
-0.99 + 
[Barcelona] * 1.12 +
[Madrid] * 1.19 +
[Valencia] * 1.12 +
[final] * 1.28 +
[Ancelotti] * 1.27 +
[Chelsea] * 1.36 +
[Clippers] * 1.52 +
[James] * 1.51 +
[NBA] * 1.36 +
[blanco] * 1.26 +
[deporte] * 1.37 +
[futbol] * 1.28 +
[organismo] * 1.51 +
[tecnico] * 1.34 +
[titulo] * 1.09

FT_1:
Class 0 :
-0.38 + 
[Aguirre] * 1.68 +
[Akiyama] * 1.74 +
[Andorra] * 1.15 +
[Barcelona] * -1.12 +
[Europa] * 1.56 +
[Madrid] * -0.56 +
[Martinez] * 1.53 +
[Paris] * 1.54 +
[Valencia] * -1.12 +
[director] * 2.53 +
[euros] * 1.36 +
[final] * -1.28 +
[musico] * 1.53 +
[pasado] * 1.54 +
[tarde] * 1.54 +
[Ancelotti] * -1.27 +
[Chelsea] * -1.36 +
[Clippers] * -1.52 +
[James] * -1.51 +
[NBA] * -1.36 +
[acusado] * 2.73 +
[blanco] * -1.26 +
[deporte] * -1.37 +
[futbol] * -1.28 +
[organismo] * -1.51 +
[tecnico] * -1.34 +
[titulo] * -1.09

Class 1 :
0.38 + 
[Aguirre] * -1.68 +
[Akiyama] * -1.74 +
[Andorra] * -1.15 +
[Barcelona] * 1.12 +
[Europa] * -1.56 +
[Madrid] * 0.56 +
[Martinez] * -1.53 +
[Paris] * -1.54 +
[Valencia] * 1.12 +
[director] * -2.53 +
[euros] * -1.36 +
[final] * 1.28 +
[musico] * -1.53 +
[pasado] * -1.54 +
[tarde] * -1.54 +
[Ancelotti] * 1.27 +
[Chelsea] * 1.36 +
[Clippers] * 1.52 +
[James] * 1.51 +
[NBA] * 1.36 +
[acusado] * -2.73 +
[blanco] * 1.26 +
[deporte] * 1.37 +
[futbol] * 1.28 +
[organismo] * 1.51 +
[tecnico] * 1.34 +
[titulo] * 1.09

FT_N0#3:
Class 0 :
1.42 + 
[Barcelona] * -1.12 +
[Madrid] * -1.19 +
[Valencia] * -1.12 +
[final] * -1.28 +
[mejor] * -1.27 +
[Alves] * -1.51 +
[Ancelotti] * -1.27 +
[Bayern] * -1.51 +
[Brasil] * -1.51 +
[Celta] * -3.03 +
[Chelsea] * -1.36 +
[Clippers] * -1.52 +
[James] * -1.51 +
[NBA] * -1.36 +
[badminton] * -1.53 +
[blanco] * -1.26 +
[club] * -1.56 +
[deporte] * -1.37 +
[empate] * -1.52 +
[futbol] * -1.28 +
[goles] * -1.25 +
[jornada] * -1.51 +
[lanzamiento] * -1.25 +
[organismo] * -1.51 +
[piloto] * -1.52 +
[respecto] * -1.25 +
[tecnico] * -1.34 +
[titulo] * -1.09 +
[toda] * -1.66

Class 1 :
-1.42 + 
[Barcelona] * 1.12 +
[Madrid] * 1.19 +
[Valencia] * 1.12 +
[final] * 1.28 +
[mejor] * 1.27 +
[Alves] * 1.51 +
[Ancelotti] * 1.27 +
[Bayern] * 1.51 +
[Brasil] * 1.51 +
[Celta] * 1.51 +
[Chelsea] * 1.36 +
[Clippers] * 1.52 +
[James] * 1.51 +
[NBA] * 1.36 +
[badminton] * 1.53 +
[blanco] * 1.26 +
[club] * 1.56 +
[deporte] * 1.37 +
[empate] * 1.52 +
[futbol] * 1.28 +
[goles] * 1.25 +
[jornada] * 1.51 +
[juegan] * 1.52 +
[lanzamiento] * 1.25 +
[organismo] * 1.51 +
[piloto] * 1.52 +
[respecto] * 1.25 +
[tecnico] * 1.34 +
[titulo] * 1.09 +
[toda] * 1.66

```
***

### DTNB 

no

***

### Decision Table

```
Decision Table:

Number of training instances: 706
Number of Rules : 29
Non matches covered by Majority class.
	Best first.
	Start set: no attributes
	Search direction: forward
	Stale search after 5 node expansions
	Total number of subsets evaluated: 59083
	Merit of best subset found:   91.501
Evaluation (for feature selection): CV (leave one out) 
Feature set: 154,656,721,1370,1371,1427,1431,1463,1500,1591,1631,1718,1725,1850,1886,1987,2054,2073,2146,2181,1

```

### Conjuntive Rule

```
(Madrid <= 0.5) => Class = ACTUALIDAD

Class distributions:
Covered by the rule:
ACTUALIDAD	DEPORTES	
0.796421	0.203579	

Not covered by the rule:
ACTUALIDAD	DEPORTES	
0.208333	0.791667
```

=== Run information ===

Scheme:weka.classifiers.rules.NNge -G 5 -I 5
Relation:     categories-model-weka.filters.unsupervised.attribute.StringToWordVector-R1-W1000-prune-rate-1.0-N0-stemmerweka.core.stemmers.NullStemmer-M1-tokenizerweka.core.tokenizers.WordTokenizer -delimiters " \r\n\t.,;:\'\"()?!"
Instances:    706
Attributes:   2376
[list of attributes omitted]
Test mode:6-fold cross-validation

=== Classifier model (full training set) ===


NNGE classifier

Rules generated :
	class DEPORTES IF : ADN=0.0 ^ Adversarios=0.0 ^ Africa=0.0 ^ Agricultura=0.0 ^ Aires=0.0 ^ Akif=0.0 ^ Akiyama=0.0 ^ AlSalmond=0.0 ^ Alan=0.0 ^ Alemania=0.0 ^ Ambiente=0.0 ^ Amundsen=0.0 ^ Analisis=0.0 ^ Andalucia=0.0 ^ Andorra=0.0 ^ AntartiAustralia=0.0 ^ Antartiadentra=0.0 ^ Antonio=0.0 ^ Aqui=0.0 ^ Argentina=0.0 ^ Arias=0.0 ^ Arkansas=0.0 ^ Artico=0.0 ^ Asad=0.0 ^ Assange=0.0 ^ Asuntos=0.0 ^ Asymptote=0.0 ^ Ayuntamiento=0.0 ^ Aznar=0.0 ^ Badia=0.0 ^ Baldeweg=0.0 ^ Barcelona=0.0 ^ Barrow=0.0 ^ Bauza=0.0 ^ Benedicto=0.0 ^ Berchtesgaden=0.0 ^ Best=0.0 ^ Beta=0.0 ^ Biologica=0.0 ^ BirdLife=0.0 ^ Bogota=0.0 ^ Bohigas=0.0 ^ British=0.0 ^ Buenos=0.0 ^ Bunge=0.0 ^ Burrell=0.0 ^ Cables=0.0 ^ Cairo=0.0 ^ Cambridge=0.0 ^ Can=0.0 ^ Canete=0.0 ^ Carlos=0.0 ^ Castelnuovo=0.0 ^ Cataluna=0.0 ^ Celler=0.0 ^ Celulas=0.0 ^ Cervantes=0.0 ^ Ciencia=0.0 ^ Cientificos=0.0 ^ Comunidad=0.0 ^ Consejo=0.0 ^ Contemporanea=0.0 ^ Convento=0.0 ^ Corea=0.0 ^ Creanza=0.0 ^ Cruz=0.0 ^ Culmina=0.0 ^ Curie=0.0 ^ Dani=0.0 ^ Desafortunadamente=0.0 ^ Dickens=0.0 ^ Dio=0.0 ^ Donana=0.0 ^ EE=0.0 ^ EEUU=0.0 ^ Eduardo=0.0 ^ Educacion=0.0 ^ Egipto=0.0 ^ Elisabeth=0.0 ^ Emilio=0.0 ^ Emma=0.0 ^ Espacial=0.0 ^ Espana=0.0 ^ Espanola=0.0 ^ Estacion=0.0 ^ Europa=0.0 ^ Europeo=0.0 ^ Exteriores=0.0 ^ FRAGUAS=0.0 ^ Faena=0.0 ^ Falange=0.0 ^ Feria=0.0 ^ Fernando=0.0 ^ Ferran=0.0 ^ Foster=0.0 ^ Francia=0.0 ^ Franco=0.0 ^ Futuro=0.0 ^ Galeano=0.0 ^ Gallego=0.0 ^ Garcia=0.0 ^ Geun=0.0 ^ Gironell=0.0 ^ Gobierno=0.0 ^ Graham=0.0 ^ Gran=0.0 ^ Greco=0.0 ^ Greenpeace=0.0 ^ Hablamos=0.0 ^ Hague=0.0 ^ Harper=0.0 ^ HarperCollins=0.0 ^ Henares=0.0 ^ Hermandad=0.0 ^ Historia=0.0 ^ Holocausto=0.0 ^ Hugo=0.0 ^ Huici=0.0 ^ II=0.0 ^ ISS=0.0 ^ Iglesia=0.0 ^ Instituto=0.0 ^ Internacional=0.0 ^ Investigadores=0.0 ^ Iowa=0.0 ^ It=0.0 ^ Jan=0.0 ^ Jin=0.0 ^ John=0.0 ^ Jose=0.0 ^ Juan=0.0 ^ Julian=0.0 ^ Junta=0.0 ^ Junto=0.0 ^ Kalhammer=0.0 ^ Karol=0.0 ^ Karski=0.0 ^ Koolhaas=0.0 ^ La=0.0 ^ Larramendi=0.0 ^ Lazaro=0.0 ^ Lee=0.0 ^ Leon=0.0 ^ Libro=0.0 ^ Liridas=0.0 ^ Llosa=0.0 ^ Londres=0.0 ^ Luis=0.0 ^ Luna=0.0 ^ MUNDO=0.0 ^ Macknik=0.0 ^ Madero=0.0 ^ Madrid=0.0 ^ Mapleton=0.0 ^ March=0.0 ^ Marie=0.0 ^ Mario=0.0 ^ Marte=0.0 ^ Marti=0.0 ^ Martin=0.0 ^ Martinez=0.0 ^ Mastracchio=0.0 ^ Medio=0.0 ^ Mexico=0.0 ^ Mezclo=0.0 ^ Miguel=0.0 ^ Ministerio=0.0 ^ Moneo=0.0 ^ MultiViral=0.0 ^ Mundial=0.0 ^ Museo=0.0 ^ Museum=0.0 ^ NASA=0.0 ^ Nacional=0.0 ^ Navarro=0.0 ^ Neolitico=0.0 ^ Ningun=0.0 ^ Nobel=0.0 ^ Noma=0.0 ^ Norte=0.0 ^ Noruego=0.0 ^ Nunca=0.0 ^ OMS=0.0 ^ ONG=0.0 ^ OSCE=0.0 ^ Obama=0.0 ^ Oklahoma=0.0 ^ Oportunidades=0.0 ^ Oriol=0.0 ^ Oro=0.0 ^ Oscar=0.0 ^ Osservatore=0.0 ^ PP=0.0 ^ PPE=0.0 ^ PSOE=0.0 ^ Pablo=0.0 ^ Paco=0.0 ^ Pacto=0.0 ^ Palma=0.0 ^ Papa=0.0 ^ Paris=0.0 ^ Park=0.0 ^ Parlamento=0.0 ^ Parque=0.0 ^ Partido=0.0 ^ Patrizia=0.0 ^ Pedro=0.0 ^ Penal=0.0 ^ Peru=0.0 ^ Phoenix=0.0 ^ Pictoris=0.0 ^ Pilar=0.0 ^ Pino=0.0 ^ Pirincci=0.0 ^ Pitxot=0.0 ^ Pla=0.0 ^ Polar=0.0 ^ Popular=0.0 ^ Prehistoria=0.0 ^ Premio=0.0 ^ Primero=0.0 ^ Pritzker=0.0 ^ Proyecto=0.0 ^ Pueden=0.0 ^ Puerto=0.0 ^ RAFAEL=0.0 ^ Rachewiltz=0.0 ^ Rafael=0.0 ^ Ramon=0.0 ^ Recibio=0.0 ^ Recuerautor=0.0 ^ Redzepi=0.0 ^ Reino=0.0 ^ Rene=0.0 ^ Reserva=0.0 ^ Responsabilidad=0.0 ^ Rey=0.0 ^ Reyes=0.0 ^ Rick=0.0 ^ Riesgos=0.0 ^ Roca=0.0 ^ Rodriguez=0.0 ^ Roja=0.0 ^ Romano=0.0 ^ SEO=0.0 ^ SOS=0.0 ^ Sala=0.0 ^ San=0.0 ^ Sanidad=0.0 ^ Schnabel=0.0 ^ Sebastian=0.0 ^ Sewol=0.0 ^ Silvio=0.0 ^ Simio=0.0 ^ Sol=0.0 ^ Soportaran=0.0 ^ Sosa=0.0 ^ SpaceX=0.0 ^ Stephen=0.0 ^ Steve=0.0 ^ Stone=0.0 ^ Superior=0.0 ^ Sustituye=0.0 ^ Svalvard=0.0 ^ Swanson=0.0 ^ Taishan=0.0 ^ Texas=0.0 ^ Tierra=0.0 ^ Tokio=0.0 ^ Trinitarias=0.0 ^ Tutankamon=0.0 ^ UE=0.0 ^ UPyD=0.0 ^ UU=0.0 ^ Unicef=0.0 ^ Unido=0.0 ^ Unidos=0.0 ^ Universidad=0.0 ^ VOX=0.0 ^ Valencia=0.0 ^ Valladolid=0.0 ^ Valle=0.0 ^ Valverde=0.0 ^ Vargas=0.0 ^ Victor=0.0 ^ Victoria=0.0 ^ WWF=0.0 ^ Wagner=0.0 ^ William=0.0 ^ Wojtyla=0.0 ^ World=0.0 ^ XVI=0.0 ^ XX=0.0 ^ XXIII=0.0 ^ Yolanmillones=0.0 ^ abandono=0.0 ^ abaratar=0.0 ^ abogan=0.0 ^ abre=0.0 ^ abrio=0.0 ^ acentuandose=0.0 ^ acoge=0.0 ^ acogiendose=0.0 ^ activos=0.0 ^ acto=0.0 ^ actuacion=0.0 ^ actualizacion=0.0 ^ adecuados=0.0 ^ admite=0.0 ^ admitido=0.0 ^ adolescentes=0.0 ^ aduladora=0.0 ^ adulto=0.0 ^ afectando=0.0 ^ afirma=0.0 ^ afirmo=0.0 ^ agencia=0.0 ^ agrava=0.0 ^ agresion=0.0 ^ agresiva=0.0 ^ agua=0.0 ^ albergues=0.0 ^ album=0.0 ^ alcalde=0.0 ^ alerta=0.0 ^ alta=0.0 ^ alternativa=0.0 ^ alzheimer=0.0 ^ amor=0.0 ^ amplio=0.0 ^ analisis=0.0 ^ animales=0.0 ^ ano=0.0 ^ anterior=0.0 ^ antiguedad=0.0 ^ antropologia=0.0 ^ anuncia=0.0 ^ aparentemente=0.0 ^ apenas=0.0 ^ aplicado=0.0 ^ apoteosis=0.0 ^ apuestan=0.0 ^ apunta=0.0 ^ arana=0.0 ^ argentino=0.0 ^ arma=0.0 ^ arqueologos=0.0 ^ arquitectura=0.0 ^ arraigado=0.0 ^ arrasa=0.0 ^ arregla=0.0 ^ arte=0.0 ^ articulo=0.0 ^ artista=0.0 ^ artistas=0.0 ^ asciende=0.0 ^ asegura=0.0 ^ asiatico=0.0 ^ asignar=0.0 ^ asociado=0.0 ^ asteroides=0.0 ^ astronautas=0.0 ^ astronomico=0.0 ^ ataca=0.0 ^ atmosfera=0.0 ^ atrevimiento=0.0 ^ audicion=0.0 ^ aumento=0.0 ^ australicomprobado=0.0 ^ autor=0.0 ^ autores=0.0 ^ autoridades=0.0 ^ autoriza=0.0 ^ ayudar=0.0 ^ ayugubernamental=0.0 ^ ayutodavia=0.0 ^ babelica=0.0 ^ barceloneses=0.0 ^ barco=0.0 ^ base=0.0 ^ beneficiarse=0.0 ^ bien=0.0 ^ biografia=0.0 ^ biomedicina=0.0 ^ bloque=0.0 ^ blues=0.0 ^ brillantes=0.0 ^ britanica=0.0 ^ britanico=0.0 ^ britanicos=0.0 ^ buen=0.0 ^ bulle=0.0 ^ busca=0.0 ^ buscara=0.0 ^ busquerestos=0.0 ^ cabeza=0.0 ^ cadaver=0.0 ^ cae=0.0 ^ cambia=0.0 ^ cambio=0.0 ^ cambios=0.0 ^ caminata=0.0 ^ campana=0.0 ^ campesinos=0.0 ^ cancer=0.0 ^ candidato=0.0 ^ candidatos=0.0 ^ cantante=0.0 ^ capaces=0.0 ^ capitalidad=0.0 ^ cara=0.0 ^ cargo=0.0 ^ cartuchos=0.0 ^ casi=0.0 ^ casos=0.0 ^ catalan=0.0 ^ causando=0.0 ^ caza=0.0 ^ cebos=0.0 ^ celebracion=0.0 ^ celebre=0.0 ^ celulas=0.0 ^ centenar=0.0 ^ central=0.0 ^ cepas=0.0 ^ cerca=0.0 ^ cerebro=0.0 ^ ceremonia=0.0 ^ certamen=0.0 ^ cesion=0.0 ^ chimpances=0.0 ^ chino=0.0 ^ cien=0.0 ^ ciencia=0.0 ^ cientifico=0.0 ^ cientificos=0.0 ^ cientos=0.0 ^ cifras=0.0 ^ cine=0.0 ^ cional=0.0 ^ circense=0.0 ^ circunstancias=0.0 ^ ciudad=0.0 ^ ciudadalemanes=0.0 ^ ciudadanos=0.0 ^ clase=0.0 ^ clasico=0.0 ^ clave=0.0 ^ clima=0.0 ^ climatico=0.0 ^ clinica=0.0 ^ clonacion=0.0 ^ cobayas=0.0 ^ cocineros=0.0 ^ coclear=0.0 ^ cohete=0.0 ^ colaboraciones=0.0 ^ coleccion=0.0 ^ comedia=0.0 ^ comercio=0.0 ^ cometa=0.0 ^ comienzo=0.0 ^ comision=0.0 ^ companera=0.0 ^ compania=0.0 ^ comparacion=0.0 ^ comparapresunta=0.0 ^ compararlas=0.0 ^ compra=0.0 ^ comprender=0.0 ^ comprometiendose=0.0 ^ compromisos=0.0 ^ comunica=0.0 ^ comunicacion=0.0 ^ comunidad=0.0 ^ concelebraFrancisco=0.0 ^ concepto=0.0 ^ concienciar=0.0 ^ condena=0.0 ^ condiciones=0.0 ^ confirmado=0.0 ^ conjunto=0.0 ^ conoce=0.0 ^ conocer=0.0 ^ conocido=0.0 ^ conociinnovadores=0.0 ^ conocimiento=0.0 ^ consecuencias=0.0 ^ conservacionista=0.0 ^ consigue=0.0 ^ construir=0.0 ^ consumo=0.0 ^ contemporaneos=0.0 ^ contentos=0.0 ^ continuan=0.0 ^ contrastar=0.0 ^ conversaciones=0.0 ^ convierte=0.0 ^ copiarla=0.0 ^ copula=0.0 ^ cosmonauta=0.0 ^ costara=0.0 ^ costes=0.0 ^ creacion=0.0 ^ cree=0.0 ^ creia=0.0 ^ creo=0.0 ^ crias=0.0 ^ critica=0.0 ^ criticas=0.0 ^ critico=0.0 ^ cuaderno=0.0 ^ cuadrados=0.0 ^ cuales=0.0 ^ cualificados=0.0 ^ cuarta=0.0 ^ cuenta=0.0 ^ cuentas=0.0 ^ cuerpo=0.0 ^ cuerpos=0.0 ^ cultura=0.0 ^ cultural=0.0 ^ cuyo=0.0 ^ danes=0.0 ^ dano=0.0 ^ dar=0.0 ^ dara=0.0 ^ datos=0.0 ^ debaten=0.0 ^ debe=0.0 ^ debera=0.0 ^ debido=0.0 ^ decadas=0.0 ^ decapasarevelo=0.0 ^ decia=0.0 ^ decide=0.0 ^ decir=0.0 ^ declarar=0.0 ^ deducen=0.0 ^ defensa=0.0 ^ define=0.0 ^ defoliantes=0.0 ^ dejamos=0.0 ^ dejo=0.0 ^ delante=0.0 ^ demas=0.0 ^ demostramos=0.0 ^ den=0.0 ^ denominaciones=0.0 ^ denuncia=0.0 ^ denunciar=0.0 ^ derechos=0.0 ^ derivacion=0.0 ^ desaparicion=0.0 ^ desarrollados=0.0 ^ descarrilamiento=0.0 ^ descartan=0.0 ^ desclasificados=0.0 ^ desconoce=0.0 ^ describe=0.0 ^ descubren=0.0 ^ descubridor=0.0 ^ desecaFranco=0.0 ^ deshielo=0.0 ^ desprendido=0.0 ^ destaca=0.0 ^ destacados=0.0 ^ destina=0.0 ^ destrozos=0.0 ^ desvela=0.0 ^ desvelado=0.0 ^ detectado=0.0 ^ detener=0.0 ^ detenidos=0.0 ^ deterioro=0.0 ^ determina=0.0 ^ determinacion=0.0 ^ devoran=0.0 ^ dia=0.0 ^ diabetes=0.0 ^ dias=0.0 ^ dicen=0.0 ^ dictador=0.0 ^ diferentes=0.0 ^ digital=0.0 ^ dinastia=0.0 ^ director=0.0 ^ dirigentes=0.0 ^ disco=0.0 ^ disenan=0.0 ^ disminuido=0.0 ^ disponible=0.0 ^ distintos=0.0 ^ divulgativo=0.0 ^ domesticos=0.0 ^ duo=0.0 ^ dura=0.0 ^ durentable=0.0 ^ ecologistas=0.0 ^ economia=0.0 ^ economica=0.0 ^ economico=0.0 ^ ecuatoriano=0.0 ^ edicion=0.0 ^ edificio=0.0 ^ edificios=0.0 ^ editorial=0.0 ^ educacion=0.0 ^ efectos=0.0 ^ eficaz=0.0 ^ egiptologia=0.0 ^ ejemplares=0.0 ^ elecciones=0.0 ^ electronicos=0.0 ^ em=0.0 ^ emigrantes=0.0 ^ emperador=0.0 ^ empieza=0.0 ^ emplearan=0.0 ^ empobrecidos=0.0 ^ emprender=0.0 ^ empresariales=0.0 ^ empresas=0.0 ^ encargados=0.0 ^ enciende=0.0 ^ encima=0.0 ^ encuentra=0.0 ^ encuentran=0.0 ^ energia=0.0 ^ energias=0.0 ^ enferma=0.0 ^ enfermedad=0.0 ^ engendrar=0.0 ^ enlaza=0.0 ^ ensena=0.0 ^ ensenanza=0.0 ^ enterraAlcala=0.0 ^ enterrado=0.0 ^ enterramientos=0.0 ^ entidad=0.0 ^ entonces=0.0 ^ entorno=0.0 ^ entradas=0.0 ^ entrega=0.0 ^ entrevista=0.0 ^ envenenados=0.0 ^ eolica=0.0 ^ epistolar=0.0 ^ epoca=0.0 ^ equipo=0.0 ^ error=0.0 ^ escBruselas=0.0 ^ escala=0.0 ^ escanea=0.0 ^ escritor=0.0 ^ esfuerzos=0.0 ^ espaciales=0.0 ^ espacio=0.0 ^ espacios=0.0 ^ espanol=0.0 ^ espanola=0.0 ^ espanoles=0.0 ^ especialistas=0.0 ^ especie=0.0 ^ espectacular=0.0 ^ espectaculo=0.0 ^ esplendor=0.0 ^ estadistica=0.0 ^ estadounidense=0.0 ^ estadounidenses=0.0 ^ estrechez=0.0 ^ estrellas=0.0 ^ estrena=0.0 ^ estudiantes=0.0 ^ estudio=0.0 ^ eucaliptos=0.0 ^ europeas=0.0 ^ europeos=0.0 ^ euros=0.0 ^ evitar=0.0 ^ exacta=0.0 ^ excarcelacion=0.0 ^ excavaciones=0.0 ^ excepto=0.0 ^ exhumar=0.0 ^ existe=0.0 ^ existen=0.0 ^ exitosa=0.0 ^ exitoso=0.0 ^ expedicion=0.0 ^ expedientes=0.0 ^ experiencia=0.0 ^ experimentado=0.0 ^ experimento=0.0 ^ experimentos=0.0 ^ expertos=0.0 ^ explica=0.0 ^ explicar=0.0 ^ exploracion=0.0 ^ explorador=0.0 ^ explosiones=0.0 ^ expoliadas=0.0 ^ exporta=0.0 ^ expresidente=0.0 ^ exterior=0.0 ^ extraordinarios=0.0 ^ facsimil=0.0 ^ fallecido=0.0 ^ falta=0.0 ^ familia=0.0 ^ familiares=0.0 ^ faraon=0.0 ^ febrero=0.0 ^ feria=0.0 ^ festival=0.0 ^ fetichismo=0.0 ^ fiasco=0.0 ^ ficcion=0.0 ^ filme=0.0 ^ final=0.0 ^ finales=0.0 ^ financiadores=0.0 ^ fiscalia=0.0 ^ fisica=0.0 ^ forenses=0.0 ^ forma=0.0 ^ formacion=0.0 ^ forman=0.0 ^ foto=0.0 ^ fotografia=0.0 ^ frances=0.0 ^ fraude=0.0 ^ frente=0.0 ^ fuego=0.0 ^ funciones=0.0 ^ fundacion=0.0 ^ fusiona=0.0 ^ futuras=0.0 ^ galardon=0.0 ^ ganaderia=0.0 ^ genera=0.0 ^ generaLINEAR=0.0 ^ genes=0.0 ^ genetica=0.0 ^ georradar=0.0 ^ gira=0.0 ^ giro=0.0 ^ grabado=0.0 ^ gracias=0.0 ^ gran=0.0 ^ grandes=0.0 ^ grave=0.0 ^ graves=0.0 ^ gris=0.0 ^ grupo=0.0 ^ guia=0.0 ^ guitarrista=0.0 ^ habitabilidad=0.0 ^ habitantes=0.0 ^ habitos=0.0 ^ habla=0.0 ^ hace=0.0 ^ hacen=0.0 ^ hacer=0.0 ^ hacia=0.0 ^ hallazgo=0.0 ^ helado=0.0 ^ hembras=0.0 ^ herbicidas=0.0 ^ hermana=0.0 ^ heroe=0.0 ^ hijo=0.0 ^ historia=0.0 ^ hito=0.0 ^ hombre=0.0 ^ hombres=0.0 ^ homenaje=0.0 ^ homosexuales=0.0 ^ hora=0.0 ^ horas=0.0 ^ horizontes=0.0 ^ hortalizas=0.0 ^ humana=0.0 ^ humanos=0.0 ^ hye=0.0 ^ iPS=0.0 ^ iba=0.0 ^ ideales=0.0 ^ ideas=0.0 ^ identicas=0.0 ^ identidades=0.0 ^ identificacion=0.0 ^ identificar=0.0 ^ iglesia=0.0 ^ ilegal=0.0 ^ iluminado=0.0 ^ imagen=0.0 ^ impacto=0.0 ^ impar=0.0 ^ impensable=0.0 ^ impidio=0.0 ^ implante=0.0 ^ imponible=0.0 ^ importante=0.0 ^ importantes=0.0 ^ impredecible=0.0 ^ impresionistas=0.0 ^ impulsa=0.0 ^ imputados=0.0 ^ inaugura=0.0 ^ inauguracion=0.0 ^ incentivar=0.0 ^ incertidumbres=0.0 ^ incidencia=0.0 ^ incluye=0.0 ^ independencia=0.0 ^ independiente=0.0 ^ indicios=0.0 ^ infantil=0.0 ^ informado=0.0 ^ informe=0.0 ^ ingles=0.0 ^ inicia=0.0 ^ injerto=0.0 ^ inmigrantes=0.0 ^ institucional=0.0 ^ instituciones=0.0 ^ instrumentos=0.0 ^ insulina=0.0 ^ intangibles=0.0 ^ intelectual=0.0 ^ intentar=0.0 ^ intercambio=0.0 ^ internacional=0.0 ^ internacionales=0.0 ^ internet=0.0 ^ intimo=0.0 ^ intolerable=0.0 ^ inversion=0.0 ^ investigacion=0.0 ^ investigadores=0.0 ^ investigan=0.0 ^ invitado=0.0 ^ isla=0.0 ^ italiana=0.0 ^ japones=0.0 ^ japonesa=0.0 ^ jefe=0.0 ^ joven=0.0 ^ jovenes=0.0 ^ judios=0.0 ^ jueves=0.0 ^ juez=0.0 ^ julio=0.0 ^ junio=0.0 ^ juntos=0.0 ^ jurado=0.0 ^ kilometros=0.0 ^ lado=0.0 ^ lanza=0.0 ^ largas=0.0 ^ lectura=0.0 ^ leen=0.0 ^ legado=0.0 ^ levanta=0.0 ^ libre=0.0 ^ libro=0.0 ^ libros=0.0 ^ lider=0.0 ^ liderar=0.0 ^ lideres=0.0 ^ limpias=0.0 ^ lista=0.0 ^ literaria=0.0 ^ llamamos=0.0 ^ llegaDallas=0.0 ^ llena=0.0 ^ lluvia=0.0 ^ lluvias=0.0 ^ locales=0.0 ^ localizar=0.0 ^ logra=0.0 ^ logrado=0.0 ^ logran=0.0 ^ logro=0.0 ^ lucha=0.0 ^ luego=0.0 ^ lugar=0.0 ^ lugares=0.0 ^ lunes=0.0 ^ luz=0.0 ^ macho=0.0 ^ machos=0.0 ^ madre=0.0 ^ madrilena=0.0 ^ maduraciones=0.0 ^ maestra=0.0 ^ maestros=0.0 ^ magia=0.0 ^ magma=0.0 ^ mago=0.0 ^ maltrato=0.0 ^ mandato=0.0 ^ manera=0.0 ^ mantiene=0.0 ^ mantienen=0.0 ^ mar=0.0 ^ marcha=0.0 ^ marido=0.0 ^ marino=0.0 ^ marisma=0.0 ^ matado=0.0 ^ mate=0.0 ^ matematicas=0.0 ^ matematico=0.0 ^ materia=0.0 ^ matrimonio=0.0 ^ mayo=0.0 ^ mayor=0.0 ^ mayores=0.0 ^ media=0.0 ^ medica=0.0 ^ medicamentos=0.0 ^ medicos=0.0 ^ medidas=0.0 ^ medipiden=0.0 ^ mejor=0.0 ^ mejora=0.0 ^ mejorar=0.0 ^ mejores=0.0 ^ menor=0.0 ^ menores=0.0 ^ meses=0.0 ^ meteoros=0.0 ^ metodos=0.0 ^ metros=0.0 ^ miembro=0.0 ^ miembros=0.0 ^ miercoles=0.0 ^ millon=0.0 ^ millones=0.0 ^ mini=0.0 ^ minimo=0.0 ^ ministro=0.0 ^ mision=0.0 ^ misiones=0.0 ^ mismo=0.0 ^ modelo=0.0 ^ modificados=0.0 ^ mole=0.0 ^ momento=0.0 ^ monto=0.0 ^ monumental=0.0 ^ mortalidad=0.0 ^ mostro=0.0 ^ movil=0.0 ^ muerte=0.0 ^ muerto=0.0 ^ muertos=0.0 ^ muestra=0.0 ^ muestran=0.0 ^ muestras=0.0 ^ mujeres=0.0 ^ mundial=0.0 ^ mundo=0.0 ^ murio=0.0 ^ musica=0.0 ^ musico=0.0 ^ necesita=0.0 ^ necropolis=0.0 ^ neocarniceros=0.0 ^ neoyorquino=0.0 ^ neuro=0.0 ^ neurologica=0.0 ^ niega=0.0 ^ nieta=0.0 ^ niveles=0.0 ^ noche=0.0 ^ nonagenaria=0.0 ^ notas=0.0 ^ novela=0.0 ^ novelista=0.0 ^ nuclear=0.0 ^ nueva=0.0 ^ nuevas=0.0 ^ nuevo=0.0 ^ nuevos=0.0 ^ numero=0.0 ^ nunca=0.0 ^ obra=0.0 ^ obras=0.0 ^ observadas=0.0 ^ observar=0.0 ^ obsesiones=0.0 ^ obtenidas=0.0 ^ oceano=0.0 ^ ocurran=0.0 ^ odio=0.0 ^ oficiales=0.0 ^ ofrece=0.0 ^ ofrecio=0.0 ^ ojos=0.0 ^ oportunidades=0.0 ^ oposicion=0.0 ^ orbita=0.0 ^ organizacion=0.0 ^ origen=0.0 ^ original=0.0 ^ osos=0.0 ^ paciente=0.0 ^ pactan=0.0 ^ padecen=0.0 ^ padres=0.0 ^ pais=0.0 ^ paises=0.0 ^ palacios=0.0 ^ palestinos=0.0 ^ pantallas=0.0 ^ paraiso=0.0 ^ paraje=0.0 ^ parlamentarios=0.0 ^ paron=0.0 ^ participacion=0.0 ^ participan=0.0 ^ partidos=0.0 ^ partir=0.0 ^ pasado=0.0 ^ paso=0.0 ^ paz=0.0 ^ pederastas=0.0 ^ pedido=0.0 ^ pedigri=0.0 ^ peinado=0.0 ^ pelicula=0.0 ^ peliculas=0.0 ^ peligro=0.0 ^ pena=0.0 ^ pensaban=0.0 ^ pensadores=0.0 ^ pensando=0.0 ^ perdihielo=0.0 ^ perimetro=0.0 ^ periodista=0.0 ^ permite=0.0 ^ permitido=0.0 ^ permitiria=0.0 ^ persona=0.0 ^ personajes=0.0 ^ personalidad=0.0 ^ personas=0.0 ^ pertenecer=0.0 ^ pertenecientes=0.0 ^ perversa=0.0 ^ pescadores=0.0 ^ piezas=0.0 ^ pinos=0.0 ^ pintor=0.0 ^ pintura=0.0 ^ plan=0.0 ^ planeta=0.0 ^ planta=0.0 ^ plantacion=0.0 ^ plastica=0.0 ^ plena=0.0 ^ poder=0.0 ^ podran=0.0 ^ poesia=0.0 ^ poetas=0.0 ^ polar=0.0 ^ polares=0.0 ^ polemica=0.0 ^ politico=0.0 ^ pone=0.0 ^ popular=0.0 ^ populares=0.0 ^ portavoz=0.0 ^ posesion=0.0 ^ posible=0.0 ^ posibles=0.0 ^ posicion=0.0 ^ posmodernismo=0.0 ^ posturas=0.0 ^ preantibioticos=0.0 ^ precariedad=0.0 ^ precio=0.0 ^ premia=0.0 ^ premio=0.0 ^ prensa=0.0 ^ presenta=0.0 ^ presente=0.0 ^ presidente=0.0 ^ presuntos=0.0 ^ pretende=0.0 ^ prima=0.0 ^ primer=0.0 ^ primera=0.0 ^ primero=0.0 ^ primeros=0.0 ^ princesas=0.0 ^ principal=0.0 ^ principe=0.0 ^ principes=0.0 ^ prision=0.0 ^ probable=0.0 ^ probar=0.0 ^ problema=0.0 ^ problemas=0.0 ^ procedentes=0.0 ^ procedimiento=0.0 ^ proceso=0.0 ^ proclamados=0.0 ^ producen=0.0 ^ producira=0.0 ^ productoras=0.0 ^ profesionales=0.0 ^ profesor=0.0 ^ profunda=0.0 ^ programa=0.0 ^ programacion=0.0 ^ programas=0.0 ^ prohibe=0.0 ^ pronuncie=0.0 ^ propios=0.0 ^ prorrusos=0.0 ^ prostitucion=0.0 ^ protagonista=0.0 ^ protesta=0.0 ^ provisionales=0.0 ^ provocando=0.0 ^ proximo=0.0 ^ proyecto=0.0 ^ proyectos=0.0 ^ prueba=0.0 ^ pruebas=0.0 ^ publica=0.0 ^ publicacion=0.0 ^ publicado=0.0 ^ publico=0.0 ^ pudo=0.0 ^ puedan=0.0 ^ puede=0.0 ^ pueden=0.0 ^ puertorriqueno=0.0 ^ puesto=0.0 ^ quedarian=0.0 ^ queja=0.0 ^ quiere=0.0 ^ quimica=0.0 ^ radiaccion=0.0 ^ ratones=0.0 ^ razones=0.0 ^ realidad=0.0 ^ realiza=0.0 ^ realizado=0.0 ^ realizar=0.0 ^ recabaran=0.0 ^ rechaza=0.0 ^ recien=0.0 ^ recogido=0.0 ^ reconocido=0.0 ^ reconstruye=0.0 ^ recorrer=0.0 ^ recrea=0.0 ^ recuperado=0.0 ^ reduccion=0.0 ^ reducir=0.0 ^ referendum=0.0 ^ refleja=0.0 ^ reflejan=0.0 ^ reflejo=0.0 ^ reforma=0.0 ^ refugio=0.0 ^ regenerativa=0.0 ^ regimen=0.0 ^ registraron=0.0 ^ reivindica=0.0 ^ rememora=0.0 ^ renovar=0.0 ^ repentina=0.0 ^ reporterismo=0.0 ^ representantes=0.0 ^ reproches=0.0 ^ reproducirse=0.0 ^ resalta=0.0 ^ reses=0.0 ^ residencia=0.0 ^ responde=0.0 ^ responden=0.0 ^ responsabilidad=0.0 ^ restaurante=0.0 ^ resultan=0.0 ^ retrato=0.0 ^ reune=0.0 ^ reutilizable=0.0 ^ revela=0.0 ^ revelan=0.0 ^ revolucion=0.0 ^ riesgo=0.0 ^ riesgos=0.0 ^ roedores=0.0 ^ rostro=0.0 ^ rra=0.0 ^ rrador=0.0 ^ rrar=0.0 ^ rueprensa=0.0 ^ ruso=0.0 ^ rutas=0.0 ^ sabe=0.0 ^ saga=0.0 ^ salas=0.0 ^ salvado=0.0 ^ salvo=0.0 ^ sancionadores=0.0 ^ sanitarios=0.0 ^ sanos=0.0 ^ santidad=0.0 ^ santos=0.0 ^ satirica=0.0 ^ secretario=0.0 ^ secreto=0.0 ^ sectores=0.0 ^ secuenciacion=0.0 ^ sediciosa=0.0 ^ segundo=0.0 ^ segunpelicula=0.0 ^ seguridad=0.0 ^ semanas=0.0 ^ senala=0.0 ^ sentencia=0.0 ^ sentenciados=0.0 ^ separatistas=0.0 ^ siempre=0.0 ^ siento=0.0 ^ siglo=0.0 ^ siguen=0.0 ^ similares=0.0 ^ 0.0<=simple<=1.0 ^ sintetiza=0.0 ^ social=0.0 ^ socialdemocratas=0.0 ^ sociedad=0.0 ^ sofistificacion=0.0 ^ solucionar=0.0 ^ sondeos=0.0 ^ sordera=0.0 ^ sortean=0.0 ^ sostenible=0.0 ^ subyace=0.0 ^ sudafricano=0.0 ^ sufre=0.0 ^ sufren=0.0 ^ sufrido=0.0 ^ sufrimiento=0.0 ^ suite=0.0 ^ supremo=0.0 ^ supuestamente=0.0 ^ surcoreana=0.0 ^ sustancia=0.0 ^ taiwanes=0.0 ^ taquillera=0.0 ^ tardaran=0.0 ^ teatral=0.0 ^ teatro=0.0 ^ tebeo=0.0 ^ tecnica=0.0 ^ tecnicas=0.0 ^ tecnologias=0.0 ^ television=0.0 ^ temas=0.0 ^ temores=0.0 ^ temperaturas=0.0 ^ tension=0.0 ^ tercer=0.0 ^ termografico=0.0 ^ termografo=0.0 ^ testigo=0.0 ^ testigos=0.0 ^ testimonio=0.0 ^ texto=0.0 ^ tiempo=0.0 ^ tifon=0.0 ^ tipo=0.0 ^ titular=0.0 ^ todas=0.0 ^ tomaran=0.0 ^ total=0.0 ^ trabajando=0.0 ^ trabajar=0.0 ^ trabajara=0.0 ^ trabajo=0.0 ^ traficantes=0.0 ^ traidora=0.0 ^ tramite=0.0 ^ transferencia=0.0 ^ transmisora=0.0 ^ trata=0.0 ^ tratamiento=0.0 ^ trate=0.0 ^ trato=0.0 ^ tribunal=0.0 ^ tripulado=0.0 ^ tripulantes=0.0 ^ triunfado=0.0 ^ tumba=0.0 ^ tumores=0.0 ^ tural=0.0 ^ turalista=0.0 ^ turcoaleman=0.0 ^ turistas=0.0 ^ ultimo=0.0 ^ ultimos=0.0 ^ unen=0.0 ^ unico=0.0 ^ universo=0.0 ^ urbanistico=0.0 ^ usaron=0.0 ^ uso=0.0 ^ utiliza=0.0 ^ utilizados=0.0 ^ valores=0.0 ^ van=0.0 ^ vanguardias=0.0 ^ varias=0.0 ^ varios=0.0 ^ varones=0.0 ^ vaticano=0.0 ^ veces=0.0 ^ vehiculo=0.0 ^ vejatorio=0.0 ^ venta=0.0 ^ ventas=0.0 ^ verdadera=0.0 ^ versiones=0.0 ^ viables=0.0 ^ viaje=0.0 ^ victimas=0.0 ^ vida=0.0 ^ vidas=0.0 ^ videojuegos=0.0 ^ videos=0.0 ^ vidiaria=0.0 ^ vientos=0.0 ^ violacion=0.0 ^ vipueblo=0.0 ^ visible=0.0 ^ visita=0.0 ^ visitar=0.0 ^ visperas=0.0 ^ vista=0.0 ^ vitalidad=0.0 ^ vive=0.0 ^ viven=0.0 ^ vivencias=0.0 ^ viviendas=0.0 ^ volver=0.0 ^ votacion=0.0 ^ votos=0.0 ^ voz=0.0 ^ vuelo=0.0 ^ vuelve=0.0 ^ zis=0.0 ^ Adam=0.0 ^ Adelante=0.0 ^ Ademas=0.0 ^ Adria=0.0 ^ Ajax=0.0 ^ Allen=0.0 ^ Alonso=0.0 ^ Alves=0.0 ^ Ancelotti=0.0 ^ Andres=0.0 ^ Anfield=0.0 ^ Angeles=0.0 ^ Antoni=0.0 ^ Arena=0.0 ^ Arsenal=0.0 ^ Aschwin=0.0 ^ Asociacion=0.0 ^ Aspanion=0.0 ^ Aston=1.0 ^ Atenas=0.0 ^ Atlanta=0.0 ^ Atleti=0.0 ^ Atletico=0.0 ^ Audiencia=0.0 ^ Aun=0.0 ^ Australia=0.0 ^ Ayrton=0.0 ^ BBC=0.0 ^ BBVA=0.0 ^ BC=0.0 ^ Bale=0.0 ^ Barca=0.0 ^ Basket=0.0 ^ Bayern=0.0 ^ Belfast=0.0 ^ Bellkasen=0.0 ^ Bernabeu=0.0 ^ Bommel=0.0 ^ Borussia=0.0 ^ Bosque=0.0 ^ Brasil=0.0 ^ Bridge=0.0 ^ Brooklyn=0.0 ^ Bulls=0.0 ^ Bundesliga=0.0 ^ CFB=0.0 ^ Calderon=0.0 ^ Campeones=0.0 ^ Carletto=0.0 ^ Carlo=0.0 ^ Carlota=0.0 ^ Carrona=0.0 ^ Casillas=0.0 ^ Casimiro=0.0 ^ Catedral=0.0 ^ Cazorla=0.0 ^ Cech=0.0 ^ Celta=0.0 ^ Celtic=0.0 ^ Cesar=0.0 ^ Cesc=0.0 ^ Champions=0.0 ^ Charles=0.0 ^ Charlotte=0.0 ^ Chelsea=0.0 ^ Chicago=0.0 ^ Cholo=0.0 ^ City=0.0 ^ Clippers=0.0 ^ Coates=0.0 ^ Codigo=0.0 ^ Colonia=0.0 ^ Comite=0.0 ^ Competicion=0.0 ^ Concretamente=0.0 ^ Confederaciones=0.0 ^ Conquistar=0.0 ^ Copa=0.0 ^ Copas=0.0 ^ Costa=0.0 ^ Cristiano=0.0 ^ Criticas=0.0 ^ Dallas=0.0 ^ David=0.0 ^ Deberia=0.0 ^ Decia=0.0 ^ Departamento=0.0 ^ Desgraciadamente=0.0 ^ Di=0.0 ^ Diego=0.0 ^ Dinamarca=0.0 ^ Doellman=0.0 ^ Dortmund=0.0 ^ Durant=0.0 ^ 0.0<=Dzeko<=1.0 ^ Eden=0.0 ^ El=0.0 ^ Espanyol=0.0 ^ Eto=0.0 ^ Etoo=0.0 ^ Eurocup=0.0 ^ Euroliga=0.0 ^ FIFA=0.0 ^ Faus=0.0 ^ Fe=0.0 ^ Felipao=0.0 ^ Ferrari=0.0 ^ Final=0.0 ^ Formula=0.0 ^ Fuenlabraderbi=0.0 ^ GP=0.0 ^ Gabi=0.0 ^ Gabriel=0.0 ^ Genny=0.0 ^ Gerrard=0.0 ^ Giggs=0.0 ^ Giraldo=0.0 ^ Golden=0.0 ^ Grizzlies=0.0 ^ Guaita=0.0 ^ Guardiola=0.0 ^ HacienPublica=0.0 ^ Ham=1.0 ^ Hazard=0.0 ^ Holanfutbol=0.0 ^ Honintervenido=0.0 ^ Honsufria=0.0 ^ Hospital=0.0 ^ Hyeres=0.0 ^ Ibaka=0.0 ^ Imola=0.0 ^ Indiana=0.0 ^ Iniesta=0.0 ^ Integridad=0.0 ^ Inter=0.0 ^ Ire=0.0 ^ Ivanovic=0.0 ^ James=0.0 ^ Jarkko=0.0 ^ Javier=0.0 ^ Jesucristo=0.0 ^ Jordan=0.0 ^ Juanfran=0.0 ^ Juanito=0.0 ^ Julio=0.0 ^ Justin=0.0 ^ Juventus=0.0 ^ Kazan=0.0 ^ Kevin=0.0 ^ LC=0.0 ^ LFP=0.0 ^ Lakers=0.0 ^ Lanxess=0.0 ^ Larguero=0.0 ^ Laso=0.0 ^ LeBron=0.0 ^ LeMans=0.0 ^ Levante=0.0 ^ Lieja=0.0 ^ Liga=0.0 ^ Ligas=0.0 ^ Lisboa=0.0 ^ Liverpool=0.0 ^ Llull=0.0 ^ Lopetegui=0.0 ^ Lopez=0.0 ^ MVP=0.0 ^ Madrigal=0.0 ^ Maicon=0.0 ^ Malaga=0.0 ^ Manchester=0.0 ^ Manucho=0.0 ^ Marcaron=0.0 ^ Marcelo=0.0 ^ Maria=0.0 ^ Martino=0.0 ^ Masia=0.0 ^ Matthaus=0.0 ^ Memphis=0.0 ^ Messi=0.0 ^ Milan=0.0 ^ Miles=0.0 ^ Minnesota=0.0 ^ Mitrovic=0.0 ^ Monaco=0.0 ^ Montezemolo=0.0 ^ Motivador=0.0 ^ Mourinho=0.0 ^ Munich=0.0 ^ Musacchio=0.0 ^ Mutua=0.0 ^ NBA=0.0 ^ Nadal=0.0 ^ Necesitan=0.0 ^ Nieminen=0.0 ^ Ninos=0.0 ^ No=0.0 ^ Nolito=0.0 ^ Nunez=0.0 ^ Nyom=0.0 ^ Olaf=0.0 ^ Open=0.0 ^ Oporto=0.0 ^ Optimista=0.0 ^ Osorio=0.0 ^ Pacers=0.0 ^ Parejo=0.0 ^ Parker=0.0 ^ Pau=0.0 ^ Paulista=0.0 ^ 0.0<=Pellegrini<=1.0 ^ Pep=0.0 ^ Pepe=0.0 ^ Perasovic=0.0 ^ Pide=0.0 ^ Portland=0.0 ^ Premier=0.0 ^ Primera=0.0 ^ Quieren=0.0 ^ Rafa=0.0 ^ Ramires=0.0 ^ Ramos=0.0 ^ Raul=0.0 ^ Real=0.0 ^ Reina=0.0 ^ RemontaBarcelona=0.0 ^ Renovacion=0.0 ^ Repasa=0.0 ^ Repase=0.0 ^ Responde=0.0 ^ Ribery=0.0 ^ Rio=0.0 ^ Roig=0.0 ^ Rossich=0.0 ^ Rusia=0.0 ^ Ruz=0.0 ^ Ryan=0.0 ^ Santi=0.0 ^ Santiago=0.0 ^ Schwarzer=0.0 ^ Scolari=0.0 ^ Senna=0.0 ^ Serge=0.0 ^ Sergio=0.0 ^ Sevilla=0.0 ^ Silver=0.0 ^ Simeone=0.0 ^ Spurs=0.0 ^ Stamford=0.0 ^ State=0.0 ^ Sterling=0.0 ^ Submari=0.0 ^ Sudafrica=0.0 ^ Sueno=0.0 ^ Terry=0.0 ^ Thunder=0.0 ^ Timberwolves=0.0 ^ Tito=0.0 ^ Toni=0.0 ^ Torres=0.0 ^ UEFA=0.0 ^ Unamuno=0.0 ^ Unics=0.0 ^ United=0.0 ^ Universitari=0.0 ^ Van=0.0 ^ Vicente=0.0 ^ Vidal=0.0 ^ Vilanova=0.0 ^ Villa=1.0 ^ Villareal=0.0 ^ Villarreal=0.0 ^ Vota=0.0 ^ Warriors=0.0 ^ Washington=0.0 ^ 0.0<=West<=1.0 ^ Xabi=0.0 ^ Zaragoza=0.0 ^ abajo=0.0 ^ abierto=0.0 ^ abismo=0.0 ^ abonado=0.0 ^ abrir=0.0 ^ abriria=0.0 ^ abruma=0.0 ^ 0.0<=acaba<=1.0 ^ acaricia=0.0 ^ acceso=0.0 ^ accidente=0.0 ^ acompanado=0.0 ^ actividad=0.0 ^ actos=0.0 ^ actual=0.0 ^ acusa=0.0 ^ acusado=0.0 ^ adaptado=0.0 ^ adelanta=0.0 ^ adelantan=0.0 ^ adelante=0.0 ^ ademas=0.0 ^ aferra=0.0 ^ aficion=0.0 ^ aficionados=0.0 ^ afincado=0.0 ^ agente=0.0 ^ agitado=0.0 ^ agonica=0.0 ^ agosto=0.0 ^ agresivo=0.0 ^ alcohol=0.0 ^ alemana=0.0 ^ alma=0.0 ^ alto=0.0 ^ amarillo=0.0 ^ anade=0.0 ^ analiza=0.0 ^ andaluz=0.0 ^ anillo=0.0 ^ animico=0.0 ^ anonima=0.0 ^ ansiaEuroliga=0.0 ^ antebrazo=0.0 ^ aparto=0.0 ^ apela=0.0 ^ apercibido=0.0 ^ apertura=0.0 ^ aplauden=0.0 ^ aplicar=0.0 ^ apoyado=0.0 ^ apretado=0.0 ^ aprovechar=0.0 ^ apuestas=0.0 ^ arboles=0.0 ^ argentina=0.0 ^ armas=0.0 ^ armonia=0.0 ^ asegurar=0.0 ^ aseguro=0.0 ^ asesinar=0.0 ^ asistencias=0.0 ^ asistentes=0.0 ^ asociacion=0.0 ^ asociaciones=0.0 ^ aspiraciones=0.0 ^ aspirantes=0.0 ^ asuntos=0.0 ^ asusta=0.0 ^ ataque=0.0 ^ atencion=0.0 ^ aterriza=0.0 ^ atribuyen=0.0 ^ ausente=0.0 ^ austeridad=0.0 ^ australiano=0.0 ^ azulgrana=0.0 ^ badminton=0.0 ^ balear=0.0 ^ baloncesto=0.0 ^ bavara=0.0 ^ bavaro=0.0 ^ belga=0.0 ^ billete=0.0 ^ blanco=0.0 ^ blancos=0.0 ^ blanquiazul=0.0 ^ boca=0.0 ^ borde=0.0 ^ bote=0.0 ^ brasil=0.0 ^ brasileno=0.0 ^ brillo=0.0 ^ bueno=0.0 ^ bufanafirma=0.0 ^ cabezazo=0.0 ^ caer=0.0 ^ califica=0.0 ^ cambiar=0.0 ^ camiseta=0.0 ^ camisetas=0.0 ^ campeon=0.0 ^ campeona=0.0 ^ campeones=1.0 ^ canasta=0.0 ^ canastas=0.0 ^ cancerEl=0.0 ^ cancha=0.0 ^ cansancio=0.0 ^ cantidad=0.0 ^ capital=0.0 ^ capitan=0.0 ^ capitulo=0.0 ^ caracterizaron=0.0 ^ caramelo=0.0 ^ carga=0.0 ^ carioca=0.0 ^ carne=0.0 ^ carnet=0.0 ^ carrera=0.0 ^ casa=0.0 ^ casas=0.0 ^ caso=0.0 ^ cataclismo=0.0 ^ catedral=0.0 ^ causa=0.0 ^ cauto=0.0 ^ celebres=0.0 ^ centro=0.0 ^ cfbf=0.0 ^ chilena=0.0 ^ ciento=0.0 ^ cierto=0.0 ^ 0.0<=citizens<=1.0 ^ clasificacion=0.0 ^ clausura=0.0 ^ club=0.0 ^ clubes=0.0 ^ coautor=0.0 ^ cocina=0.0 ^ colaboraba=0.0 ^ colacion=0.0 ^ coloca=0.0 ^ colombiana=0.0 ^ color=0.0 ^ comentarios=0.0 ^ comisionado=0.0 ^ comodidad=0.0 ^ compartimental=0.0 ^ compartir=0.0 ^ competencia=0.0 ^ competicion=0.0 ^ completa=0.0 ^ completado=0.0 ^ completo=0.0 ^ compresion=0.0 ^ comunicado=0.0 ^ concentrar=0.0 ^ condenas=0.0 ^ conducir=0.0 ^ conmovedor=0.0 ^ conquista=0.0 ^ conquisto=0.0 ^ conseguiria=0.0 ^ considerado=0.0 ^ constato=0.0 ^ contener=0.0 ^ continental=0.0 ^ continua=0.0 ^ contrato=0.0 ^ control=0.0 ^ controvertido=0.0 ^ convencido=0.0 ^ convocatoria=0.0 ^ corona=0.0 ^ corredores=0.0 ^ corresponderle=0.0 ^ cosas=0.0 ^ creatorno=0.0 ^ cruciales=0.0 ^ cuadro=0.0 ^ cualidades=0.0 ^ cuerdas=0.0 ^ cuestiones=0.0 ^ cura=0.0 ^ curado=0.0 ^ curso=0.0 ^ dan=0.0 ^ dd=0.0 ^ deberia=0.0 ^ debia=0.0 ^ debuta=0.0 ^ decepcion=0.0 ^ decidio=0.0 ^ decima=0.0 ^ decimo=0.0 ^ decisivos=0.0 ^ defender=0.0 ^ defensivamente=0.0 ^ defiende=0.0 ^ deja=0.0 ^ dejan=0.0 ^ dejara=0.0 ^ delantero=0.0 ^ delito=0.0 ^ demoledora=0.0 ^ denominado=0.0 ^ depende=0.0 ^ depenmismo=0.0 ^ deporte=0.0 ^ deportes=0.0 ^ deportivo=0.0 ^ depurado=0.0 ^ derecha=0.0 ^ derecho=0.0 ^ derrota=0.0 ^ desafio=0.0 ^ descarta=0.0 ^ descenso=0.0 ^ desequilibrio=0.0 ^ desfiguran=0.0 ^ desquita=0.0 ^ deuclubes=0.0 ^ devuelve=0.0 ^ dijo=0.0 ^ dio=0.0 ^ direccion=0.0 ^ dirige=0.0 ^ discriminacion=0.0 ^ disputara=0.0 ^ distinta=0.0 ^ 0.0<=doblete<=1.0 ^ dolares=0.0 ^ 0.0<=domingo<=1.0 ^ duelo=0.0 ^ dueno=0.0 ^ ecuatoriana=0.0 ^ ejemplarizantes=0.0 ^ ejercicio=0.0 ^ elegido=0.0 ^ eliminar=0.0 ^ eliminatoria=0.0 ^ elogiar=0.0 ^ emocionada=0.0 ^ emocionan=0.0 ^ 0.0<=empate<=1.0 ^ empuja=0.0 ^ empujar=0.0 ^ encarcelado=0.0 ^ encomienbloque=0.0 ^ encuentro=0.0 ^ encuentros=0.0 ^ enfadado=0.0 ^ enfermos=0.0 ^ enfrenta=0.0 ^ enorme=0.0 ^ enormes=0.0 ^ enredado=0.0 ^ entiendo=0.0 ^ entrar=0.0 ^ entrenado=0.0 ^ entrenador=0.0 ^ entrenamiento=0.0 ^ entro=0.0 ^ episodio=0.0 ^ equipos=0.0 ^ escabechina=0.0 ^ escaparse=0.0 ^ escuela=0.0 ^ esfuerzo=0.0 ^ especialmente=0.0 ^ espectaculares=0.0 ^ espera=0.0 ^ esperan=0.0 ^ esperanza=0.0 ^ espontanea=0.0 ^ 0.0<=estadio<=1.0 ^ estelar=0.0 ^ esterilidad=0.0 ^ estilo=0.0 ^ estrategia=0.0 ^ estrella=0.0 ^ estudiar=0.0 ^ etapas=0.0 ^ europeo=0.0 ^ evitan=0.0 ^ excluye=0.0 ^ exhibicion=0.0 ^ exjugador=0.0 ^ expensas=0.0 ^ expresa=0.0 ^ extradeportivas=0.0 ^ extramotivar=0.0 ^ extranjeras=0.0 ^ extranjero=0.0 ^ fabulosa=0.0 ^ facil=0.0 ^ fallo=0.0 ^ fascismo=0.0 ^ fatal=0.0 ^ favorito=0.0 ^ fervor=0.0 ^ ficha=0.0 ^ fichaje=0.0 ^ figura=0.0 ^ figuras=0.0 ^ filosofia=0.0 ^ financiar=0.0 ^ financiero=0.0 ^ finlandes=0.0 ^ firmar=0.0 ^ fisicos=0.0 ^ flotando=0.0 ^ fondos=0.0 ^ fortaleza=0.0 ^ francesa=0.0 ^ franquicia=0.0 ^ frases=0.0 ^ frenada=0.0 ^ frenarlos=0.0 ^ fuerza=0.0 ^ fuerzas=0.0 ^ fundamental=0.0 ^ fundamentales=0.0 ^ funeral=0.0 ^ futbol=0.0 ^ futbolista=0.0 ^ futbolistas=0.0 ^ futbolistico=0.0 ^ futuro=0.0 ^ galas=0.0 ^ gana=0.0 ^ ganado=0.0 ^ ganador=0.0 ^ ganan=0.0 ^ ganar=0.0 ^ ganara=0.0 ^ ganas=0.0 ^ gano=0.0 ^ general=0.0 ^ gesta=0.0 ^ gol=0.0 ^ goleaMunich=0.0 ^ goleador=0.0 ^ golean=0.0 ^ 0.0<=golear<=1.0 ^ goles=0.0 ^ golpeo=0.0 ^ grados=0.0 ^ grupos=0.0 ^ guarmejor=0.0 ^ habil=0.0 ^ hijos=0.0 ^ hincha=0.0 ^ hipica=0.0 ^ hispanobrasileno=0.0 ^ historica=0.0 ^ holandes=0.0 ^ holandesa=0.0 ^ homenajes=0.0 ^ humillacion=0.0 ^ humo=0.0 ^ iCaldeon=0.0 ^ iCalderon=0.0 ^ iEvita=0.0 ^ ida=0.0 ^ igual=0.0 ^ iguala=0.0 ^ igualdad=0.0 ^ imagenes=0.0 ^ imagino=0.0 ^ impiasistir=0.0 ^ impone=0.0 ^ improvisacion=0.0 ^ imputara=0.0 ^ incidente=0.0 ^ includo=0.0 ^ incluso=0.0 ^ incognito=0.0 ^ incomparable=0.0 ^ incumplir=0.0 ^ indARG=0.0 ^ indBRA=0.0 ^ indBelgica=0.0 ^ indMEX=0.0 ^ indPER=0.0 ^ indPortugal=0.0 ^ indTUR=0.0 ^ indignacion=0.0 ^ individual=0.0 ^ inedito=0.0 ^ infantiles=0.0 ^ inglesa=0.0 ^ iniciara=0.0 ^ inicio=0.0 ^ inoperante=0.0 ^ insconsciente=0.0 ^ instantaneas=0.0 ^ intervencion=0.0 ^ intervino=0.0 ^ investiga=0.0 ^ ironiza=0.0 ^ italiano=0.0 ^ 0.0<=jornaWest<=1.0 ^ jornada=0.0 ^ jornadas=0.0 ^ jornatrabajo=0.0 ^ 0.0<=juega<=1.0 ^ juegan=0.0 ^ juego=0.0 ^ juegos=0.0 ^ jugaBusquets=0.0 ^ jugadas=0.0 ^ jugador=0.0 ^ jugadores=0.0 ^ jugados=0.0 ^ jugar=0.0 ^ jugara=0.0 ^ juridica=0.0 ^ juzgado=0.0 ^ lacras=0.0 ^ lamenta=0.0 ^ lanzamiento=0.0 ^ larga=0.0 ^ lateral=0.0 ^ lejos=0.0 ^ levantar=0.0 ^ leyendo=0.0 ^ liberacion=0.0 ^ libertades=0.0 ^ liderado=0.0 ^ liderato=0.0 ^ liga=0.0 ^ ligas=0.0 ^ liguero=0.0 ^ linea=0.0 ^ llegan=0.0 ^ llevo=0.0 ^ llover=0.0 ^ local=0.0 ^ luchar=0.0 ^ mPolicia=0.0 ^ madridista=0.0 ^ magnate=0.0 ^ mala=0.0 ^ maltrata=0.0 ^ manClippers=0.0 ^ mandatario=0.0 ^ mantenerse=0.0 ^ mantenido=0.0 ^ marca=0.0 ^ marcador=0.0 ^ marcapresencia=0.0 ^ marcaron=0.0 ^ marco=0.0 ^ martes=0.0 ^ maxima=0.0 ^ maximo=0.0 ^ medalla=0.0 ^ mediocre=0.0 ^ medira=0.0 ^ menisco=0.0 ^ mental=0.0 ^ mentales=0.0 ^ merma=0.0 ^ mexicana=0.0 ^ miedo=0.0 ^ milagro=0.0 ^ minuto=0.0 ^ minutos=0.0 ^ mirando=0.0 ^ molestias=0.0 ^ motivos=0.0 ^ moviliza=0.0 ^ multa=0.0 ^ musculares=0.0 ^ musculo=0.0 ^ necesidad=0.0 ^ 0.0<=necesitan<=1.0 ^ negociando=0.0 ^ nervios=0.0 ^ ninguna=0.0 ^ ninguno=0.0 ^ ninos=0.0 ^ nivel=0.0 ^ no=0.0 ^ nombrado=0.0 ^ novedosa=0.0 ^ objetivo=0.0 ^ observadores=0.0 ^ obtener=0.0 ^ octava=0.0 ^ octavos=0.0 ^ ocurre=0.0 ^ ofensas=0.0 ^ ofensivo=0.0 ^ oficiado=0.0 ^ oficinas=0.0 ^ olimpica=0.0 ^ oncologicos=0.0 ^ onubense=0.0 ^ opciones=0.0 ^ operado=0.0 ^ oportunidad=0.0 ^ optan=0.0 ^ organismo=0.0 ^ oro=0.0 ^ pacto=0.0 ^ padre=0.0 ^ pagar=0.0 ^ papel=0.0 ^ par=0.0 ^ parecen=0.0 ^ parecer=0.0 ^ pareja=0.0 ^ participar=0.0 ^ partido=0.0 ^ pasara=0.0 ^ pase=0.0 ^ patinan=0.0 ^ pedia=0.0 ^ peineta=0.0 ^ pelea=0.0 ^ pelean=0.0 ^ peligra=0.0 ^ pendiente=0.0 ^ pensaramos=0.0 ^ penultima=0.0 ^ peores=0.0 ^ perdido=0.0 ^ perdio=0.0 ^ permanencia=0.0 ^ permita=0.0 ^ personales=0.0 ^ peruana=0.0 ^ pesar=0.0 ^ pese=0.0 ^ picardia=0.0 ^ pide=0.0 ^ pierden=0.0 ^ piloto=0.0 ^ pisar=0.0 ^ pisoton=0.0 ^ pivot=0.0 ^ plagado=0.0 ^ plantilla=0.0 ^ platano=0.0 ^ plazas=0.0 ^ poderio=0.0 ^ podio=0.0 ^ podrian=0.0 ^ polemizar=0.0 ^ policia=0.0 ^ portero=0.0 ^ portugues=0.0 ^ portuguesa=0.0 ^ poseia=0.0 ^ posibilidades=0.0 ^ potencia=0.0 ^ precavido=0.0 ^ precedio=0.0 ^ preconvocados=0.0 ^ pregonan=0.0 ^ presentado=0.0 ^ prestar=0.0 ^ presunto=0.0 ^ primas=0.0 ^ principales=0.0 ^ privilegiado=0.0 ^ producirles=0.0 ^ prohibido=0.0 ^ promesa=0.0 ^ promover=0.0 ^ propia=0.0 ^ prorroga=0.0 ^ provoquen=0.0 ^ proxima=0.0 ^ publicas=0.0 ^ pucelano=0.0 ^ puerta=0.0 ^ puertas=0.0 ^ puerto=0.0 ^ pulso=0.0 ^ punta=0.0 ^ 0.0<=punto<=1.0 ^ puntos=0.0 ^ pupilo=0.0 ^ quedan=0.0 ^ quedo=0.0 ^ quemar=0.0 ^ quirofano=0.0 ^ racha=0.0 ^ racismo=0.0 ^ racista=0.0 ^ racistas=0.0 ^ rapidos=0.0 ^ reabre=0.0 ^ reafirma=0.0 ^ realizaran=0.0 ^ recalco=0.0 ^ rechazan=0.0 ^ recueraccion=0.0 ^ recupera=0.0 ^ recuperar=0.0 ^ reds=0.0 ^ reducido=0.0 ^ reducidos=0.0 ^ refirio=0.0 ^ reflexiones=0.0 ^ regata=0.0 ^ regreso=0.0 ^ relacionaequipo=0.0 ^ relativamente=0.0 ^ relativo=0.0 ^ releva=0.0 ^ renovacion=0.0 ^ renta=0.0 ^ repiten=0.0 ^ 0.0<=resistencia<=1.0 ^ respecto=0.0 ^ respondio=0.0 ^ responsabilidades=0.0 ^ restaba=0.0 ^ retirado=0.0 ^ retiro=0.0 ^ retrasos=0.0 ^ revelado=0.0 ^ reves=0.0 ^ rico=0.0 ^ ridiculo=0.0 ^ rival=0.0 ^ rivales=0.0 ^ rodilla=0.0 ^ rojiblanca=0.0 ^ rojiblancas=0.0 ^ rojiblanco=0.0 ^ ronitaliana=0.0 ^ rosa=0.0 ^ rusa=0.0 ^ saca=0.0 ^ sacarlo=0.0 ^ sancion=0.0 ^ sancionado=0.0 ^ sancionados=0.0 ^ secuelas=0.0 ^ seguidores=0.0 ^ seguira=0.0 ^ seguro=0.0 ^ seleccionador=0.0 ^ sella=0.0 ^ semifinal=0.0 ^ semifinales=0.0 ^ sendos=0.0 ^ sentirse=0.0 ^ serie=0.0 ^ sesion=0.0 ^ siete=0.0 ^ sindrome=0.0 ^ sitio=0.0 ^ socio=0.0 ^ solidario=0.0 ^ sombra=0.0 ^ sprint=0.0 ^ sub=0.0 ^ sufrieron=0.0 ^ sufrio=0.0 ^ suman=0.0 ^ supera=0.0 ^ superar=0.0 ^ suplentes=0.0 ^ sustituyo=0.0 ^ tactica=0.0 ^ tarjetas=0.0 ^ tecnico=0.0 ^ temporaNBA=0.0 ^ temporada=0.0 ^ temporadas=0.0 ^ temporaentrevista=0.0 ^ temporaliguera=0.0 ^ temporamillones=0.0 ^ temporaplantilla=0.0 ^ temporasiento=0.0 ^ tercera=0.0 ^ terceros=0.0 ^ tg=0.0 ^ tibieza=0.0 ^ titulo=0.0 ^ tocados=0.0 ^ todavia=0.0 ^ toinformacion=0.0 ^ tontos=0.0 ^ toplantilla=0.0 ^ torneo=0.0 ^ tramo=0.0 ^ tratamientos=0.0 ^ trayectoria=0.0 ^ tributar=0.0 ^ triunfo=0.0 ^ turaleza=0.0 ^ turca=0.0 ^ turco=0.0 ^ tutelo=0.0 ^ 0.0<=ultima<=1.0 ^ 0.0<=ultradefensivo<=1.0 ^ un=0.0 ^ unanimidad=0.0 ^ une=0.0 ^ va=0.0 ^ valentia=0.0 ^ vasco=0.0 ^ veloz=0.0 ^ vence=0.0 ^ vencen=0.0 ^ ventaja=0.0 ^ vertigo=0.0 ^ vestuario=0.0 ^ viaja=0.0 ^ viajando=0.0 ^ viajar=0.0 ^ vicepresidente=0.0 ^ victima=0.0 ^ victoria=0.0 ^ victorias=0.0 ^ viento=0.0 ^ vigoroso=0.0 ^ violencia=0.0 ^ vipropietario=0.0 ^ visibles=0.0 ^ vistio=0.0 ^ volvia=0.0 ^ volvio=0.0 ^ vuelta=0.0 ^ vulnerar=0.0 ^ windsurfista=0.0 ^ xenofobia=0.0 ^ zurdo=0.0  (2)
```

M = 0,7

Model build
Building classifier
rules done
table done
tree done
Classifer ready
Att: 2
Classes: 2
Instances: 706

```
POLITIC
-----------------------
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: NINGUNO =? ACTUALIDAD (expected)
GOT: DEPORTES =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? ACTUALIDAD (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: NINGUNO =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: DEPORTES =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: NINGUNO =? DEPORTES (expected)
GOT: DEPORTES =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: NINGUNO =? DEPORTES (expected)
GOT: DEPORTES =? DEPORTES (expected)
GOT: DEPORTES =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: NINGUNO =? DEPORTES (expected)
GOT: NINGUNO =? DEPORTES (expected)
GOT: NINGUNO =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
GOT: ACTUALIDAD =? DEPORTES (expected)
UNCATEGORIZED
------------------

GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: NINGUNO =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
GOT: ACTUALIDAD =? NADA (expected)
HITS42
MISS58
NO - NEWS0
NO - SPORT0
END
```


Model build
Building classifier
rules done
table done
tree done
Classifer ready
Att: 2
Classes: 2
Instances: 706

POLITIC
-----------------------
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Desahuciada Hacienda http   www elmundo es espana                c      e ec    b   b html cid SMBOSO      s kw twitter via  elmundoes elmundo espana ec cid SMBOSO kw twitter
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Confirmado  Magdalena Alvarez  imputada  http   huff to  kI Me  via  ElHuffPost huff kI
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	agencias publicidad controlan ves oyes   Una mosca botella Coca Cola   la tuerka http   youtu be SIDrAfArRMY youtu be SIDrAfArRMY
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Jefe Aconfesional asiste canonizacion papas 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Tanta Constitucion hablan luego pasan Aconfesional mismisimos    http   m huffpost com es entry         utm hp ref tw huffpost com entry utm hp ref tw
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	ultras asaltaron Blanquerna presentan elecciones  Democratico  creo  http   www publico es espana        los ultras que asaltaron la libreria blanquerna se presentan a las elecciones via  publico es publico espana ultras asaltaron libreria blanquerna presentan elecciones
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	 objetivoPobrezaInfantil Vergonzoso millones medio ninos vivan gracias caridad  Estado 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Espana segundo pais UE pobreza infantil  Datos irrefutables  admiten controversias   objetivoPobrezaInfantil
{DEPORTES=0.34734618784266647, ACTUALIDAD=0.6526538121573334}
GOT: DEPORTES =? ACTUALIDAD (expected) 	Abogado Gurtel imputado secuestro expresidente Valencia http   m europapress es nacional noticia cuarto imputado intento secuestro expresidente valencia abogado francisco correa                html europapress nacional noticia cuarto imputado intento secuestro expresidente valencia abogado francisco correa
{DEPORTES=0.9217107886709787, ACTUALIDAD=0.07828921132902149}
GOT: DEPORTES =? ACTUALIDAD (expected) 	Ahora votar blanco servira votas partido  Escanos blanco  http   escanos org  p   via  escanosenblanco escanos org
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	 SotmartaSot roban politicos pueden  temo roban principios  sino pueden
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	fin Espana gana positivo  Grado aceptacion homosexualidad paises  pic twitter com mr  auAdFS
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	perdido  RajoyEnLaSER imagino seguira cumpliendo programa electoral escrupulosamente ahora  eso  tal 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Esperanza Aguirre entrego permiso circulacion seguro  ayuntamiento http   huff to  iGbzGy huff iGbzGy
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Investigado profesor huido Navarra liderar supuestamente escision activa ETA http   www elmundo es espana                c  f   e ea    b   e html cid SMBOSO      s kw twitter via  elmundoes elmundo espana ea cid SMBOSO kw twitter
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	payasa quiere representar UE     Siento verguenza ajena    
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	acoso politico degrada democracia Cataluna http   www cronicaglobal com es notices         el acoso politico degrada la democracia en cataluna      php  U jiBjR  i  twitter  Seny  AfavordeEspana cronicaglobal com notices acoso politico degrada democracia cataluna php jiBjR twitter
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	sindicatos defendiendo derecho trabajadores recibir educacion lengua materna  pida 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	PP PSOE pactado Asturias Granada defender ciudadanos  Claro no  defenderse privilegios 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Canete  ganar elecciones socialistas vuelvan hacer dano  Canete      http   www pp es actualidad noticia canete hay que ganar las elecciones que socialistas no vuelvan hacer dano pp actualidad noticia canete ganar elecciones socialistas vuelvan hacer dano
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Simpson cumplen    anosConLosSimpson
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Viendo  Secretos universo Morgan Freeman  Existe particula Dios   http   vuqio com   universomax vuqio com
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	dragon azul  moluscos extranos mundo 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Hacer manualidades  tejer pintar  cocinar  puede ayudar sufren ansiedad  depresion dolor cronico http   cnn it  hzJL a cnn it hzJL
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	profundizar tesis  juanrallo  ObjetivoSeVende recomendamos  Una revolucion liberal Espana  http   m planetadelibros com una revolucion liberal para espana libro        html planetadelibros com revolucion liberal espana libro
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Bastante chula peli VHS
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Marvel deberia sacar saga gustaria empezar leerlos sabemos donde  Rollo  IRON MAN PRINCIPIANTES  
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	buena coleccion estrategias hacer mejores emails marketing  apoyadas estudios  http   buff ly  m X Ly buff ly Ly
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Amazon ofrecera empleados dejen trabajo  Increible  sentido vez explicado  http   time com       amazon will pay you      to quit your job  time com amazon will pay you quit your job
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	fin increible curso formacion emprendedores  Gracias  samuelgil  Miguel Lobon  raulmarcosl  AntonioParra 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Facebook compra Oculus Rift billones O O Mark Zuckerberg sacado carte ultimamente  http   time com       facebook oculus rift  time com facebook oculus rift
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	confesar publicidad haciendo Coldplay disco nuevo super original 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	pintura dias Demetrio Reigada  Emile Friant  maestro pintores Naturalismo http   wp me pxLsw  Mm wp pxLsw Mm
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	hallazgo  arqueologos daneses encontraron isla Dinamarca  tesoro vikingos monedas oro      y  cosas
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Via Lactea refleja gran lago Colorado  Foto Matt Payne  pic twitter com MFHnwQBhpT
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	caida WhatsApp provoca Telegram que captaba usuarios segundo http   bit ly  eh MS  pic twitter com  GGU hnIjI bit ly eh MS
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Orquesta instrumentos reciclados   Increible humano capaz hacer gracias musica   D http   www upsocl com mundo mira los    primeros segundos eso es todo lo que pido te enganchara despues de eso te lo juro     upsocl com mundo mira primeros segundos pido enganchara juro
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	 SocialGeek Co   FueNoticia  Samsung quiere deshacer Android cuanto http   bit ly  kwoYle bit ly kwoYle
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Dormir horas hace cerebro envejezca  estudios http   www blogsaludydescansopikolin com blog poco descanso envejece el cerebro  blogsaludydescansopikolin com blog descanso envejece cerebro
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? ACTUALIDAD (expected) 	Muere Irrational Games http   www meristation com pc reportaje irrational games homenaje a un legado de exito            cierran hacedores Obras Maestras  Lloramos perdida  IGLevine pic twitter com u agF m ml meristation com pc reportaje irrational games homenaje legado exito
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	Copa Rey supera MILLON comentarios   elclasicoenvuqio  FinalCopa  HalaMadridCopa      FinalCopaDelRey
{DEPORTES=0.3692365568668476, ACTUALIDAD=0.6307634431331524}
GOT: DEPORTES =? DEPORTES (expected) 	FCB GET  FT  Senores  liga terminado Barca  Madrid  pueden ir vallando Neptuno 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	Marquez intratable  Maldito pequeno genio  Casi medio segundo delante Lorenzo   MotoGP
{DEPORTES=0.9296757134289452, ACTUALIDAD=0.0703242865710549}
GOT: DEPORTES =? DEPORTES (expected) 	RT crees final Champions League Madrid Atletico  EncuestaMrChip  RMAvsATM
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	partidazo Atleti         cara Mourinho         disfrute  oyes 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	Pues alto cesped  Messi visto
{DEPORTES=0.25, ACTUALIDAD=0.75}
GOT: DEPORTES =? DEPORTES (expected) 	goles deberian valer dos 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	Desahuciada Hacienda http   www elmundo es espana                c      e ec    b   b html cid SMBOSO      s kw twitter via  elmundoes elmundo espana ec cid SMBOSO kw twitter
{DEPORTES=0.3622931442080379, ACTUALIDAD=0.6377068557919622}
GOT: DEPORTES =? DEPORTES (expected) 	equipo entrena Majadahonda dividido grupos preparar importante partido Malaga  AupaAtleti
{DEPORTES=0.9413433601633625, ACTUALIDAD=0.05865663983663746}
GOT: DEPORTES =? DEPORTES (expected) 	Gabi   Tenemos trabajando  domingo final  Confio equipo  http   ow ly wsWJE  LevanteAtleti ow ly wsWJE
{DEPORTES=0.6844181141728903, ACTUALIDAD=0.31558188582710966}
GOT: DEPORTES =? DEPORTES (expected) 	Simeone   Hoy toco perder quedan semanas fantasticas alta intensidad  emocion futbol puro   LevanteAtleti
{DEPORTES=0.3622931442080379, ACTUALIDAD=0.6377068557919622}
GOT: DEPORTES =? DEPORTES (expected) 	 Cristiano Ronaldo marcado ultimos partidos Liga http   bit ly  g mtkO  ValladolidRealMadrid bit ly mtkO
{DEPORTES=0.9296757134289452, ACTUALIDAD=0.0703242865710549}
GOT: DEPORTES =? DEPORTES (expected) 	Real Madrid llevo cabo sorteo entradas final Champions League http   bit ly  g mbdz bit ly mbdz
{DEPORTES=0.8808257209983846, ACTUALIDAD=0.11917427900161541}
GOT: DEPORTES =? DEPORTES (expected) 	Real Madrid recorta punto lucha Liga http   bit ly  rSZzTl  RealMadridValencia  halamadrid bit ly rSZzTl
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	Cesc piensa moverse Barca http   goo gl C Tf f  md  goo gl Tf
{DEPORTES=0.6111111111111112, ACTUALIDAD=0.3888888888888889}
GOT: DEPORTES =? DEPORTES (expected) 	Cristiano Ronaldo   Neymar puede mejor jugador mundo  http   goo gl UQJAiQ  md  goo gl UQJAiQ
{DEPORTES=0.8808257209983846, ACTUALIDAD=0.11917427900161541}
GOT: DEPORTES =? DEPORTES (expected) 	Madrid jueves  Atletico  viajara Lisboa  Aqui informacion http   ow ly wziul pic twitter com ie  ILSLkK ow ly wziul
{DEPORTES=0.3622931442080379, ACTUALIDAD=0.6377068557919622}
GOT: DEPORTES =? DEPORTES (expected) 	LeBron lidera Heat ganar Nets poner serie http   ow ly wzaZm pic twitter com ehVL n aJ  ow ly wzaZm
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? DEPORTES (expected) 	Messi quiere fichaje Aguero continuidad Pinto http   ow ly wyfLh ow ly wyfLh
{DEPORTES=0.3622931442080379, ACTUALIDAD=0.6377068557919622}
GOT: DEPORTES =? DEPORTES (expected) 	Atletico facilita Malaga solo entradas a    http   ow ly wy nh ow ly wy nh
UNCATEGORIZED
------------------

{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	unicornios encantan  unicornios encantan  uni uni unicornios  encantan
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	  Las Bragas Vicky   Suena vulgar   Victoria s Secret    Lo petamos 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Jo  tia  pone mil  taaan moderado  Ya  tia  super estoico racional flipas 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	buen puente rodeado amijos  vida facil
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	pudiera elegir lugar morir  sofa 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	feliz ahora mismo 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	aburrido dia  eh 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Alguien sabe debo recojan animal peligro extincion  zorra  URGE 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Ahora da miedo mirar  cualquier cosa 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Nuse yo    bueno  llevo hora solo bola 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Nunca entendere gente lleva gafas sol Metro  cuestion anonimato luz cegadora fluorescentes 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Ahora mismo pegaria paliza 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	 Tocador  duda alguna eufemismo estupido oido nunca 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	parece estupendo pongas lechuga  llames ensalada 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	siento integrada 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	casa tarde madre pregunte  De vienes   responderle   Del mono  Darwin  
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	 Introduzcase oferta laboral remunerada orificio rectal 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	convertir GinTonic bebida masculina  Coge GinTonic Tiralo fregadero Pide cerveza
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	mejor decision tomado llevo vida 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Ganas matar aumentando 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	obstaculos pongan  decidimos tirar toalla  sigo adelante pase pase 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	quiero volver hablar princesas buscan tipos coleccionar pies cama 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	joder  querias Mac 
{DEPORTES=0.5474923876650513, ACTUALIDAD=0.45250761233494874}
GOT: DEPORTES =? NINGUNO (expected) 	guiris viajen proximamente avion Madrid practican delante espejo   I go Aropueto Adoulfo Suares Madrit Baraha  
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	llores alguien merece lagrimas  mates llorar funeral levantar sospechas 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	super divertido juego amarte dia siguiente existes
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Pero    solo tomado cerveza  preciosa puedo preciosa  OK  solo tomado cerveza  senor agente
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	QUIERO RESPIRAR      aaahhhhh      
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Espero hija llegue virgen matrimonio  Espero hija llegue virgen comunion 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Operacion mayo  asume fracasado come remordimientos 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	cabron vecino Pues pone puerta loco manana  Casi cae taladro susto 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	fallado  dejame reconquistarte  Oiga  usted es  Bien  quieres jugar  Empecemos cero 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Carino  ganas comerte rabo ahora mismo  Eh Si  No  captar atencion  bajes basura 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Papa  Recuerdas dicho salia tranqui  Pues mi  Cristobal  llaman America  liada 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Oye  tal examen  digo verdad  Si  puta 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Ir gimnasio luego hartarte dulces  hamburguesas cubatas  equilibro  Fuerzas opuestas  GYM NAM 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Macalister  homicidios  tenemos  Varon  anos  vestimenta hipster  gafas cristales  Punaladas  pocas fueron 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	encuentro hijo  Tranquilicese describamelo  anos  gafas pasta  polo rosa pantalones cuadros  quiere encontrarlo 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	guapa quieras  dime    mundo ciego  cuanta gente impresionarias 
{DEPORTES=0.028959810874704492, ACTUALIDAD=0.9710401891252954}
GOT: ACTUALIDAD =? NINGUNO (expected) 	Acabo descubrir cuchillos mango pesado hoja amputarte pie cae encimera wuay 
HITS51
MISS9
NO - NEWS39
NO - SPORT1
END
