package benchmarks;

import java.util.Arrays;
import java.util.HashSet;

public class DescriptionsUtils {
	private static final HashSet<String> manualMaleDescriptions = new HashSet<String>(Arrays.asList("enamorado",
			"amigo", "padre", "hijo", "venezolano", "espanol", "ingeniero", "orgulloso", "enamorado", "sonador", "boy",
			"perfecto", "chico", "hermano", "abogado", "jugador", "disenador", "novio", "aficionado", "musico",
			"bailarin", "tecnico", "principe", "fotografo", "papa", "sevillano", "malagueno", "psicologo", "enfermero",
			"medico", "informatico", "arquitecto", "apasionado", "profesor", "senior", "locutor", "seguidor",
			"sincero", "divertido", "campeon", "guerrero", "actor", "rubio", "moreno", "licenciado", "emprendedor",
			"comunicador", "tonto", "trabajador", "mexicano", "experto", "presidente", "becario", "compositor",
			"celoso", "viejo", "editor", "melomano", "dominicano", "defensor", "critico", "latino", "director",
			"emprendedor", "sincero", "consultor", "seguidor", "portero", "cinefilo", "lector", "agradecido",
			"veterinario", "colombiano", "experto", "universitario", "empresario", "egresado", "positivo",
			"entrenador", "politico", "creativo", "romantico", "ciudadano", "risueno", "revolucionario", "justo",
			"tranquilo", "maestro", "mecanico", "opositor", "caprichoso", "colaborador", "coordinador", "comprometido",
			"republicano", "argentina", "dispuesto", "futbolero", "latinoamericano", "nadador", "directo", "pelirojo"));

	private static final HashSet<String> manualFemaleDescriptions = new HashSet<String>(Arrays.asList("enamorada",
			"amiga", "madre", "directioner", "belieber", "hija", "venezolana", "espanola", "ingeniera", "orgullosa",
			"enamorada", "sonadora", "girl", "perfecta", "chica", "hermana", "abogada", "jugadora", "justinbieber",
			"disenadora", "novia", "aficionada", "bailarina", "tecnica", "princesa", "fotografa", "mama", "sevillana",
			"auryn", "malaguena", "psicologa", "enfermera", "medica", "arquitecta", "apasionada", "profesora",
			"locutora", "seguidora", "sincera", "divertida", "campeona", "guerrera", "actriz", "rubia", "morena",
			"licenciada", "emprendendora", "comunicadora", "tonta", "trabajadora", "mexicana", "enana", "experta",
			"presidenta", "becaria", "compositora", "celosa", "vieja", "editora", "melomana", "dominicana",
			"defensora", "critica", "latina", "directora", "emprendedora", "sincera", "consultora", "seguidora",
			"portera", "cinefila", "lectora", "agradecida", "veterinaria", "colombiana", "experta", "universitaria",
			"empresaria", "egresada", "positiva", "entrenadora", "politica", "creativa", "romantica", "ciudadana",
			"risuena", "revolucionaria", "justa", "tranquila", "maestra", "mecanica", "opositora", "caprichosa",
			"colaboradora", "coordinadora", "comprometida", "republicana", "argentina", "dispuesta", "futbolera",
			"latinoamericana", "nadadora", "directa", "pelirroja"));

	private static final HashSet<String> maleCommunityGentilics = new HashSet<String>(Arrays.asList("gallego",
			"galego", "asturiano", "cantabro", "vasco", "navarro", "aragones", "catalan", "castellanoleones",
			"madrileno", "valenciano", "murciano", "extremeno", "andaluz", "castellanomanchego", "manchego", "canario"));
	private static final HashSet<String> femaleCommunityGentilics = new HashSet<String>(
			Arrays.asList("gallega", "galega", "asturiana", "cantabra", "vasca", "navarra", "aragonesa", "catalan",
					"castellanoleonesa", "madrilena", "valenciana", "murciana", "extremena", "andaluza",
					"castellanomanchega", "manchega", "canaria"));

	private static final HashSet<String> maleCityGentilics = new HashSet<String>(Arrays.asList("alicantino",
			"albaceteno", "asturiano", "barcelones", "burgales", "cacereno", "gaditano", "cantabro", "ciudadrealeno",
			"cordobes", "corunes", "granadino", "guadalajareno", "guipuzcano", "leones", "logrones", "madrileno",
			"malagueno", "murciano", "navarro", "orensano", "palentino", "pontevedres", "salmantino", "segoviano",
			"sevillano", "soriano", "toledano", "valenciano", "vallisoletano", "pucelano", "vizcaino", "zamorano",
			"zaragozano"));
	private static final HashSet<String> femaleCityGentilics = new HashSet<String>(Arrays.asList("alicantina",
			"albacetena", "asturiana", "barcelonesa", "burgalesa", "cacerena", "gaditana", "cantraba", "ciudadrealena",
			"cordobesa", "corunesa", "granadina", "guadalajarena", "guipuzcana", "leonesa", "logronesa", "madrilena",
			"malaguena", "murciana", "navarra", "orensana", "palentina", "pontevedresa", "salmantina", "segoviana",
			"sevillana", "soriana", "toledana", "valenciana", "vallisoletana", "pucelana", "vizcaina", "zamorana",
			"zaragozana"));

	public static boolean manualMaleDescriptionsContains(String name) {
		return manualMaleDescriptions.contains(name) || maleCityGentilics.contains(name)
				|| maleCommunityGentilics.contains(name);
	}

	public static boolean manualFemaleDescriptionsContains(String name) {
		return manualFemaleDescriptions.contains(name) || femaleCityGentilics.contains(name)
				|| femaleCommunityGentilics.contains(name);
	}
}
