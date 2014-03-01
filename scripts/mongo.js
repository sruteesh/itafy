conn = new Mongo();
db = conn.getDB("itafy-benchmarks");


var twitterNames = [
    "Rosa Hita",
    "Mi amor roto",
    "тαмαяα †",
    "Albert Rosa",
    "Jeesuus Maartiin;)",
    "La Perra Vieja",
    "Los Montenegro ♔",
    "J. Rocamora Iniesta",
    "juan de la peña",
    "S.",
    "enganchada a salvame",
    "María S. Tur",
    "Ondho",
    "Chupito de nieve",
    "Francisco ",
    "Desconeguda BCN",
    "ana mar",
    "Tino Güemes",
    "Matías '  ",
    "@JJporlagloridemima",
    "alba cabrera meneses",
    "Alex Vidal Bralo",
    "Juanjo Jiménez",
    "jose maria beltran ",
    "URIAH.",
    "D@fnis",
    "Capitán Pincél",
    "estrella crespo ",
    "La Juani",
    "payasoJusticiero",
    "haya91",
    "Tino Montero ",
    "27 de enero ♥ ♡ ♥...",
    "Karulita",
    "Amaia Landa Serrano",
    "Oliver Blanco Salas",
    "David Bel",
    "vito scaleta",
    "El Objetivista ",
    "lopetito",
    "vargamelseñol",
    "     sαritα ",
    "Comunica2.0 Gandia",
    "Dani Escudero",
    "brian jose ",
    "Pilar ",
    "i'm loser L! (loool)",
    "oscaron",
    "belen",
    "Noemí Castillo. ",
    "Miguel Rebollo",
    "Juanjo ",
    "Tasty",
    "Manu",
    "sofia",
    "Coscolleta",
    "APAGA Y VAMONOS",
    "∞♥Pequeña Princesa♥∞",
    "Antonio D. Sparrow",
    "αη℮şţ℮sία",
    "Invierno.",
    "Mari puri en su sofá",
    "CELIA",
    "CristinaPedrocheFans",
    "eric chacon riquelme",
    "iris abadias ",
    "Ohi bok hayati",
    "Juanjo Vélez.",
    "↑☺sεяgισ  η∂↑☺",
    "§3G!O",
    "patrii",
    "Joaquín Tejada",
    "Maria Cosano Marmol",
    "Mohana #3",
    "YUUUP!™",
    "Smile ™♥",
    "Ana I. Agudo ✨",
    "María Recio",
    "Always (L) de TC",
    "Lucho xx",
    "SoriMadrid",
    "  MΣĎΙПΔψ",
    "Noe de Fran",
    "AVICII & HARDWELL",
    "Chusireben.",
    "David R.",
    "FitétúFitéyo ♧",
    "carmen ostalé",
    "Sergio Reverte",
    "Lucia Bentos Pereira",
    "amapola del valle",
    "Sálvame Deluxe",
    "Lidia Arjona ",
    "Chanli",
    "Jesús Suárez",
    "Lucy Lopez",
    "Elena♡",
    "garrapata feliz",
    "H",
    "---//ΠΠ~š¥ç~ΠΠ\\\\---",
    "Mr Plow",
    "Leire ♡",
    "Gonzalillo",
    "Soy un estegosaurio",
    "Joaquín Manciño",
    "chely",
    "josemuchobetis",
    "mari salazar",
    "   maji ",
    "yamauno",
    "Lola Menta ",
    "ABUELO ∞ TE QUIERO",
    "ALVARADOESMDP !",
    "Tecnicolor∞",
    "Amaaaiia",
    "TuH GiTaNaH VaSiLoNa",
    "Rosa Muñoz",
    "»castle«",
    "david blanco perez",
    "patricia liliana",
    "Melissa Ibarra",
    " Cristian.",
    "..ocho..",
    "What did we win? ",
    "Juani Hernandez",
    "«cassie»",
    "Leiree.",
    "leidyvanessa",
    "Cristina ToGa",
    "lola ossa-reyes",
    "Anitalabruja",
    "Lo Que Soy~",
    "Loli ",
    "BABY♥ ",
    "LosChuches",
    "Paulmg",
    "inmortadelo",
    "Salva_TE1",
    "javi ferreiro ",
    "Te queremos Iker♕♡",
    "ZOZO ALLAM",
    "Mrs. Payne ♔",
    "sexy chicas autofoto",
    "alexis cid",
    "Sara Martín",
    "Javi Arroyo",
    "◢◤Carl Ѵalderrey◢◤",
    "Luisa Montoya★",
    "rosendo navarro",
    "sonnyy3",
    "Ara!!",
    "capuchina",
    "88colores",
    "PEDRO",
    "sureño",
    "Lucía López",
    "☆B☆",
    "La Videoteka",
    "M B 16",
    "Alex Filloy",
    "Nacho Gil",
    "Disfruta TV",
    "Not lucky, Blessed †",
    "javier Sierra ",
    "Carlosruna",
    "Sonsoles Barroso ^.^",
    "jorge ",
    "Maria Jose Romera",
    "Vicent",
    "Mª José Moreno",
    "edu",
    "Lassandra Hade",
    "El Kantamañanas",
    "Me meo",
    "javier castro blanco",
    "Historias de la tele",
    "DOES HE NOW? :)",
    "manuel valero",
    "Spirox ",
    "Julian Serrano",
    "Pequeña.",
    "Zitas",
    "carmen omaña gil",
    "Judith",
    "Lady Purple",
    "Death.",
    "M. Grace Lancaster",
    "Ramón Manzano M.",
    "ILYC.ES",
    "Tanileth nailid",
    "mario vega sanchez",
    "linamar",
    "IN MY VEINS.",
    "Descubre Salamanca",
    "Ara CM",
    "Daniel Torija",
    "HomoZappingRTV",
    "Patrycia",
    "JAIRO ",
    "Josh (MPTLC)",
    "Dorleta Ayerra",
    "CreamCaramelChanel..",
    "joaquim barcelona",
    "Mercedes Renda Melón",
    "DeAsTurias",
    "christian flores",
    "Ramiro Rosa Nogués",
    "lucia novillo durang",
    "Chiara Cabrera ",
    "jose palacin",
    "Rebe",
    "Sparkle Black",
    "¿Capaz o incapaz?",
    "MikyGonka",
    "HUMBERTO GONZALEZ SP",
    "¿A que no lo sabías?",
    "Reich",
    "mushu",
    "Patricia Gonzalez",
    "Hell-chan.",
    "Maicol Soriano",
    "Jorge Fernández",
    "Ximena Navarrete ESP",
    "carla mendoza",
    "Charo Alvarez",
    "Unos",
    "María Tíscar ",
    "nerea flores quiles",
    "RosiCamino",
    "Naranjoyolé",
    "Bea WaterMelon :D",
    "Daniel M C",
    "LIlian-GN",
    "marta zamanillo gonz",
    "Godoy501",
    "TuPincheSocia",
    "fanta",
    "Sh(erlock)iru~.",
    "××",
    "death ends all",
    "Espejito",
    "El Raspe",
    "Enrique González",
    "Julia Gila ",
    "Carolina Cabana",
    "Arnold Exp. Afonso",
    "FALL IN LOVE.",
    "blankyhonrado",
    "marley",
    "natalia ",
    "Alba Santiago Moreno",
    "Cristina Garcia",
    "Javichu",
    "Eder Nuñez",
    "María Barroso ♥",
    "Mel",
    "Jose Jimenez Jimenez",
    "TechiTo  ツ",
    "Raven-Heart.com",
    "José F López",
    "Agent DeWitt",
    "Rubén Obis",
    "rafael",
    "PrincesaBiris",
    "Rubén Hernanz Díaz ",
    "Helena romero",
    "Pilar Torres",
    "Tsundere",
    "Martuuuss14",
    "Raúl Ernman Björk",
    "carmen corao ",
    "Maruja Pibón ",
    "Salva Te",
    "sandra_ tenerife",
    "Valentín xD",
    "♥PrincesaPastelita♥",
    "Pingu",
    "Adry Night",
    "Mia Wallace.",
    "Alvaro sanchez",
    "manuel gonzalez",
    "Mercedes Zambudio ",
    "¡CONVAGINACIÓN!",
    "I'm Alone, Bitch.",
    "SHEILA SAIZ",
    "Wolfo",
    "Vane Auryn",
    "ヘクター",
    "unconditionαlly",
    "lili garcia",
    "cristi",
    "Tributo Hyliana.",
    "TreceDeEnero",
    "WhiteTiger...∞",
    "Boy Toy Oriol",
    "Marina López~",
    "Barbara Perez :)",
    "★«Javîer»♣™ P&G",
    "Jorge Martínez",
    "Aroii",
    "Sandra CG",
    "López",
    "Mónica Naranjo Idolo",
    "pelopincho ♡",
    "Teleame RSS",
    "Always together BD",
    "LAAAURIKA ♥",
    "Inma Cerezo Molina",
    "Divergent.",
    "●Robeertuu●™",
    "Īńdïåńą  Jøńës  ",
    "SMILER CYRUS ",
    "*****",
    "Pa(u)tata.",
    "Smile",
    "Maria Azpicueta ",
    "Rollercoaster™",
    "بحر",
    "Anna",
    "Yoli Arjona  ✌",
    "Iván Simón Martínez",
    "ⓑ",
    "BEEEEEEEEEEEEEER.",
    "PerturbadoradeSueños",
    "          Clauuudia ",
    "Just Love Yourself.",
    "Nenuca;)",
    "Joaquin Cruz Romero",
    "Sändrä Lïvë Wïrë",
    "Mónica Naranjo 4.0",
    "Soundless.",
    "Calíope",
    "Limón.",
    "martin ostos",
    "Henry McOsom",
    "atroH solraC",
    "αιζπεδο",
    "Xuso Jones Murcia",
    "Cuando Dices Adios",
    "сяιsтιnα",
    "srt¤. warri¤r",
    "pigmientoverde",
    "Serkrey",
    "«diana»",
    "Leidy Riaño♡ ",
    "Cristina Granados",
    "Alex_hardtechno",
    "DiarioDeMujer.Net®",
    "Albertiiitoo.",
    "TLJ 2.0",
    "Lucía Regueiro",
    "∞",
    "Series Lab",
    "†Señorita Gerez.†",
    "Loly",
    "Pedro Menendez",
    "Caathii'♥",
    "ღ Gεмεℓιεя ღ ",
    "Damián Bravo",
    "Conchi Chapela",
    "Kafirpa",
    "Principes sin corona",
    "Mr. X",
    "Daniel Burgos ®",
    "jacob garcia",
    "Andrés Monroy",
    "AlvaritoDominguez ;)",
    "César Landaeta H.",
    "«ASUN» #volveremosRZ",
    "María  Suárez ",
    "MiriamFabra! :)",
    "Javi Bataller",
    "✖F*CK•$•MINGO✖",
    "She's not afraid ♥",
    "Pablo Masiá",
    "LAURA♥",
    "abouzainab andalusi",
    "@cazolococoba15",
    "Gema.",
    "Yεяαy Fεяиάиdεz'G ✌",
    "Patriii u.u",
    "Batman",
    "Michel Rrpp FABRIK",
    "astronaut",
    "Sofinilla.",
    "Jcayero",
    "Los Simpson ES",
    "Let Yourself Go ✘✘",
    "You me at topillo",
    "Seeing pass the life",
    "GemelierMalulera ♡",
    "I'm a grenade.",
    "Andre♡",
    "belen martin",
    "Christian Olmedo",
    "Гонсало Мартин",
    "Paradise.",
    "~CrazyMofo~",
    "Loquehayqueleer",
    "Víctor Pérez Sánchez",
    "juan bethencourt",
    "Oooh sii !!",
    "Ángela Pérez Jiménez",
    "Pequeña!P♥.",
    "martaa:)",
    "veves garciia;)",
    "Jose Vega ",
    "Paula Cohen Noguerol",
    "CLAUDIA.",
    "Rui Gonçalves",
    "MIGUEL✌",
    "Noelia Pérez ♥",
    "santiago blanco",
    "Pelo fiestaa!",
    "CARLOS DEL FRESNO ",
    "#Saritaa :)",
    "Juan Alcolea",
    "I♥DR I♡AURYN#GRACIAS",
    "Mapache Verde",
    "Tere :) ",
    "Carlos Valle ",
    "locodelacodeina",
    "marisol maza",
    "Sergio Espino",
    "Vale.",
    "SG",
    "Víctor Pelaz Abeledo",
    "retwitteó",
    "SLàzaro {$}",
    "lidia dengra",
    "Montse ",
    "Gianmartin Paz",
    "inmaculada represion",
    "maria aguila",
    "♧Miky♧",
    "♥ÉrikaPilar♡LQNSDM♥",
    "Juan Manuel Jimenez ",
    "Fitín Nueda",
    "Miss Lion.",
    "Arturo Mastache Peña",
    "Maria Dolores Ordoñe",
    "Maaaaaar.-",
    "MARTA CICUENDEZMarti",
    "Harto de Aguantar",
    "Maayte",
    "zonacharra",
    "calleatras",
    "jacc",
    "Pequeña Milindri♥",
    "Tim Bergling.",
    "Charly",
    "RAF[R]IKI",
    "La vita è bella",
    "XRIZ∞",
    "jesuslopezvillaverde",
    "MaribelCarmonaIIIRep",
    "SUSANA TÁRREGA",
    "Loving you is easy.",
    "mohues",
    "Isabel Gallego",
    "Enki Cazás",
    "pablo",
    "Raúl Rodríguez",
    "Debate 'Economia'",
    "Rojo Cabreado",
    "Libertad",
    "Cristina Leal Galbis",
    "jose javier",
    "DesafioTotal",
    "Aldana22",
    "Mari Lopez Linares",
    "Alfonso Roldán",
    "alberto matas",
    "David Román",
    "gustinforman",
    "Alba Sabine",
    "Fernando Guzón",
    "Mnesefilo",
    "GAGO ",
    "Mae",
    "xabo",
    "brandy dominguez 22M",
    "Gabriel Fernàndez ",
    "Rosa Triana",
    "fran mont",
    "Miguel A. Rodriguez",
    "Edraith Martell ",
    "Sdrikova",
    "marian sanabria",
    "María García Pérez"
];

for (var i = 0; i < twitterNames.length; i++) {
    db.twitter_names.insert({name: twitterNames[i]});
};
