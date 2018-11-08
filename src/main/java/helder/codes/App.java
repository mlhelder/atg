package helder.codes;

import org.jgrapht.graph.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;


// TODO: Auto-generated Javadoc
/**
 * The Main class of atg project.
 */
public class App {
	
	/** The Constant SAMPLE_CSV_FILE_PATH3. */
	private static final String SAMPLE_CSV_FILE_PATH3 = "/home/helder/codes/atg/resource/quest3TST.csv";
	
	/** The Constant SAMPLE_CSV_FILE_PATH2. */
	private static final String SAMPLE_CSV_FILE_PATH2 = "/home/helder/codes/atg/resource/quest2TST.csv";
	
	/** The Constant SAMPLE_CSV_FILE_PATH4. */
	private static final String SAMPLE_CSV_FILE_PATH4 = "/home/helder/codes/atg/resource/quest4TST.csv";
	
	/** The Constant SAMPLE_CSV_FILE_PATH1. */
	private static final String SAMPLE_CSV_FILE_PATH1 = "/home/helder/codes/atg/resource/quest1TST.csv";
    
	/**
	 *	Question: Analisar a situação de trabalho dos brasileiros graduados ou com algum estudo e sem diploma que não conseguiram emprego.
	 *	Vértice: Profissionais brasileiros.
     *  Aresta: Situação atual de trabalho (em busca, sem interesse, aberto a oportunidades)
	 *	Ordenação: Não ordenado.
	 *	Peso: Não possui.
	 *	Critério: Situação atual de trabalho + formal education + age
	 *
	 * @return the graph for question 2
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ2, DefaultWeightedEdge> createQuest2() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH2));
		
		HeaderColumnNameMappingStrategy<MyUserQ2> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ2>();
		beanStrategy.setType(MyUserQ2.class);
		
        CsvToBean<MyUserQ2> csvToBean = new CsvToBeanBuilder<MyUserQ2>(reader)
                .withType(MyUserQ2.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

		List<MyUserQ2> nodeList = csvToBean.parse(beanStrategy, reader);
		
		Pseudograph<MyUserQ2, DefaultWeightedEdge>  graph = new Pseudograph<MyUserQ2, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
		
		for (MyUserQ2 node : nodeList) {
			graph.addVertex(node);
		}
		
		for (MyUserQ2 node : nodeList) {
			for (MyUserQ2 nodeAux : nodeList) {
				if(node.getFormaleducation() == nodeAux.getFormaleducation() && node.getJobsearchstatus() == nodeAux.getJobsearchstatus() && (node.getAge() == nodeAux.getAge())) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();	
		return graph;	   
	}

	/**
	 * Question: Analisar, dentre diversos tamanhos de empresas, se empregados na área de TI sem graduação são contratados por companhias de grande porte.
	 * Vértice: Pessoas empregadas na área de TI sem graduação.
	 * Aresta: Empresas de acordo com número de empregados.
	 * Ordenação: Não ordenado.
	 * Peso: Não possui.
	 * Critério: Companhias de grande porte que possuem acima de 10.000 empregados.
	 *
	 * @return the graph for question 3
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ3, DefaultWeightedEdge> createQuest3() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH3));
		
		HeaderColumnNameMappingStrategy<MyUserQ3> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ3>();
		beanStrategy.setType(MyUserQ3.class);
		
        CsvToBean<MyUserQ3> csvToBean = new CsvToBeanBuilder<MyUserQ3>(reader)
                .withType(MyUserQ3.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

		List<MyUserQ3> nodeList = csvToBean.parse(beanStrategy, reader);
		
		Pseudograph<MyUserQ3, DefaultWeightedEdge>  graph = new Pseudograph<MyUserQ3, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
			
		for (MyUserQ3 node : nodeList) {
			graph.addVertex(node);
		}
		for (MyUserQ3 node : nodeList) {
			for (MyUserQ3 nodeAux : nodeList) {
				if (node.getFormaleducation() == nodeAux.getFormaleducation() && node.getCompanysize() == nodeAux.getCompanysize()) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();
		
		return graph;	   
	}
	
	/**
	 * Question: Classificar a semelhança entre as mulheres empregadas na área de TI dentre os pesquisados.
	 * Vértice: Mulheres empregadas na área de TI.
	 * Aresta: Grau de semelhança.
	 * Ordenação: Não ordenado.
	 * Peso: Não possui.
	 * Critério: Semelhança = país de atuação + satisfação na carreira + anos codificando.
	 *
	 * @return the graph for question 4
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ4, DefaultWeightedEdge> createQuest4() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH4));
		
		HeaderColumnNameMappingStrategy<MyUserQ4> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ4>();
		beanStrategy.setType(MyUserQ4.class);
		
        CsvToBean<MyUserQ4> csvToBean = new CsvToBeanBuilder<MyUserQ4>(reader)
                .withType(MyUserQ4.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

		List<MyUserQ4> nodeList = csvToBean.parse(beanStrategy, reader);
		
		Pseudograph<MyUserQ4, DefaultWeightedEdge>  graph = new Pseudograph<MyUserQ4, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
			
		for (MyUserQ4 node : nodeList) {
			graph.addVertex(node);
		}
		for (MyUserQ4 node : nodeList) {
			for (MyUserQ4 nodeAux : nodeList) {
				if (node.getCareersatisfaction() == nodeAux.getCareersatisfaction() && node.getYearscoding() == nodeAux.getYearscoding() && node.getCountry() == nodeAux.getCountry()) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();
		return graph;	   
	}
	
	/**
	 * Question: Investigar quais são as linguagens de programação mais utilizadas entre os profissionais na área de TI mais bem pagos.
	 * Vértice: Pessoas empregadas na área de TI mais bem pagas.
	 * Aresta: Linguagens de programação em comum.
	 * Ordenação: Não ordenado.
	 * Peso: Não possui.
	 *
	 * @return the graph for question 4
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ1, DefaultWeightedEdge> createQuest1() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH1));
		
		HeaderColumnNameMappingStrategy<MyUserQ1> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ1>();
		beanStrategy.setType(MyUserQ1.class);
		
        CsvToBean<MyUserQ1> csvToBean = new CsvToBeanBuilder<MyUserQ1>(reader)
                .withType(MyUserQ1.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

		List<MyUserQ1> nodeList = csvToBean.parse(beanStrategy, reader);
		
		Pseudograph<MyUserQ1, DefaultWeightedEdge>  graph = new Pseudograph<MyUserQ1, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
			
		for (MyUserQ1 node : nodeList) {
			graph.addVertex(node);
		}
		for (MyUserQ1 node : nodeList) {
			for (MyUserQ1 nodeAux : nodeList) {
				if (testLanguages(node,nodeAux)) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();
		return graph;	   
	}
	
	/**
	 * Aux function to Test languages.
	 *
	 * @param for node
	 * @param second node
	 * @return true, if contains similar language
	 */
	private static boolean testLanguages(MyUserQ1 node ,MyUserQ1 aux) {
		String[] nodeLanguages = node.getLanguageworkedwith().split(";");
		String[] auxLanguages = aux.getLanguageworkedwith().split(";");
		for (String language : nodeLanguages) {
			for (String auxLanguage : auxLanguages) {
				if(language.equals(auxLanguage)) {
					return true;
				}				
			}
		}
		return false;
	}
	
    /**
     * The main method to create all questions graphs.
     *
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {
    	Pseudograph<MyUserQ3, DefaultWeightedEdge> graph3 = createQuest3();
    	Pseudograph<MyUserQ4, DefaultWeightedEdge> graph4 = createQuest4();
    	Pseudograph<MyUserQ2, DefaultWeightedEdge> graph2 = createQuest2();
    	Pseudograph<MyUserQ1, DefaultWeightedEdge> graph1 = createQuest1();
    
        assert(graph1.vertexSet().size() != 0);
        assert(graph2.vertexSet().size() != 0);
        assert(graph3.vertexSet().size() != 0);
        assert(graph4.vertexSet().size() != 0);
        
        assert(graph1.edgeSet().size() != 0);
        assert(graph2.edgeSet().size() != 0);
        assert(graph3.edgeSet().size() != 0);
        assert(graph4.edgeSet().size() != 0);

        System.out.println("Número de arestas questao 1 = " + graph1.edgeSet().size());
        System.out.println("Lista de arestas questao 2 = " + graph2.edgeSet());
        System.out.println("Lista de arestas questao 3 = " + graph3.edgeSet());
        System.out.println("Lista de arestas questao 4 = " +  graph4.edgeSet());

        System.out.println("Número de vértices questao 1 = " +  graph1.vertexSet().size());
        System.out.println("Lista de vértices questao 2 = " +  graph2.vertexSet());
        System.out.println("Lista de vértices questao 3 = " +  graph3.vertexSet());
        System.out.println("Lista de vértices questao 4 = " +  graph4.vertexSet());

    }
}