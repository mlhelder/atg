package helder.codes;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
//import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

/**
 * The Main class of atg project.
 */
public class App {

	// Q1
	private static ArrayList<String> languages = new ArrayList<String>();
	private static ArrayList<Integer> mostWorkedWith = new ArrayList<Integer>();

	// Q2
	private static List<Set<MyUserQ2>> stronglyConnectedSubgraphsQ2;

	// Q3
	private static ArrayList<String> sizes = new ArrayList<String>();
	private static ArrayList<Integer> sizesQuantity = new ArrayList<Integer>();

	// Q4
	private static List<Set<MyUserQ4>> stronglyConnectedSubgraphs;

	/** The Constant SAMPLE_CSV_FILE_PATH1. */
	private static final String SAMPLE_CSV_FILE_PATH1 = "resource/quest1TST.csv";

	/** The Constant SAMPLE_CSV_FILE_PATH2. */
	private static final String SAMPLE_CSV_FILE_PATH2 = "resource/quest2TST.csv";

	/** The Constant SAMPLE_CSV_FILE_PATH3. */
	private static final String SAMPLE_CSV_FILE_PATH3 = "resource/quest3TST.csv";

	/** The Constant SAMPLE_CSV_FILE_PATH4. */
	private static final String SAMPLE_CSV_FILE_PATH4 = "resource/quest4TST.csv";

	/**
	 * Question: Investigar quais são as linguagens de programação mais utilizadas
	 * entre os profissionais na área de TI mais bem pagos. Vértice: Pessoas
	 * empregadas na área de TI mais bem pagas. Aresta: Linguagens de programação em
	 * comum. Ordenação: Não ordenado. Peso: Não possui.
	 *
	 * @return the graph for question 1
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ1, DefaultWeightedEdge> createQuest1() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH1));

		HeaderColumnNameMappingStrategy<MyUserQ1> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ1>();
		beanStrategy.setType(MyUserQ1.class);

		CsvToBean<MyUserQ1> csvToBean = new CsvToBeanBuilder<MyUserQ1>(reader).withType(MyUserQ1.class)
				.withIgnoreLeadingWhiteSpace(true).build();

		List<MyUserQ1> nodeList = csvToBean.parse(beanStrategy, reader);

		Pseudograph<MyUserQ1, DefaultWeightedEdge> graph = new Pseudograph<MyUserQ1, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		for (MyUserQ1 node : nodeList) {
			graph.addVertex(node);
		}
		for (MyUserQ1 node : nodeList) {
			for (MyUserQ1 nodeAux : nodeList) {
				if (testLanguagesQ1(node, nodeAux) && !node.equals(nodeAux)) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();
		return graph;
	}

	public static void dfsQ1(Pseudograph<MyUserQ1, DefaultWeightedEdge> graph) {

		GraphIterator<MyUserQ1, DefaultWeightedEdge> iterator = new DepthFirstIterator<MyUserQ1, DefaultWeightedEdge>(
				graph);

		while (iterator.hasNext()) {
			String[] nodeLanguages = iterator.next().getLanguageworkedwith().split(";");
			for (String language : nodeLanguages) {
				if (!languages.contains(language)) {
					languages.add(languages.size(), language);
					mostWorkedWith.add(mostWorkedWith.size(), 0);
				} else {
					for (int i = 0; i < languages.size(); i++) {
						if (languages.get(i).equals(language)) {
							mostWorkedWith.set(i, mostWorkedWith.get(i) + 1);
						}
					}
				}
			}

		}
	}

	/**
	 * Aux function to Test languages.
	 *
	 * @param        for node
	 * @param second node
	 * @return true, if contains similar language
	 */

	private static boolean testLanguagesQ1(MyUserQ1 node, MyUserQ1 aux) {
		String[] nodeLanguages = node.getLanguageworkedwith().split(";");
		String[] auxLanguages = aux.getLanguageworkedwith().split(";");
		for (String language : nodeLanguages) {
			for (String auxLanguage : auxLanguages) {
				if (language.equals(auxLanguage)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Question: Analisar a situação de trabalho dos brasileiros graduados ou com
	 * algum estudo e sem diploma que não conseguiram emprego. Vértice:
	 * Profissionais brasileiros. Aresta: Situação atual de trabalho (em busca, sem
	 * interesse, aberto a oportunidades) Ordenação: Não ordenado. Peso: Não possui.
	 * Critério: Situação atual de trabalho + formal education + age
	 *
	 * @return the graph for question 2
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ2, DefaultWeightedEdge> createQuest2() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH2));

		HeaderColumnNameMappingStrategy<MyUserQ2> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ2>();
		beanStrategy.setType(MyUserQ2.class);

		CsvToBean<MyUserQ2> csvToBean = new CsvToBeanBuilder<MyUserQ2>(reader).withType(MyUserQ2.class)
				.withIgnoreLeadingWhiteSpace(true).build();

		List<MyUserQ2> nodeList = csvToBean.parse(beanStrategy, reader);

		Pseudograph<MyUserQ2, DefaultWeightedEdge> graph = new Pseudograph<MyUserQ2, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		for (MyUserQ2 node : nodeList) {
			graph.addVertex(node);
		}

		for (MyUserQ2 node : nodeList) {
			for (MyUserQ2 nodeAux : nodeList) {
				if (node.getFormaleducation().equals(nodeAux.getFormaleducation())
						&& node.getJobsearchstatus().equals(nodeAux.getJobsearchstatus())
						&& (node.getAge().equals(nodeAux.getAge())) && !node.equals(nodeAux)) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();
		return graph;
	}

	/**
	 * Question: Analisar, dentre diversos tamanhos de empresas, se empregados na
	 * área de TI sem graduação são contratados por companhias de grande porte.
	 * Vértice: Pessoas empregadas na área de TI sem graduação. Aresta: Empresas de
	 * acordo com número de empregados. Ordenação: Não ordenado. Peso: Não possui.
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

		CsvToBean<MyUserQ3> csvToBean = new CsvToBeanBuilder<MyUserQ3>(reader).withType(MyUserQ3.class)
				.withIgnoreLeadingWhiteSpace(true).build();

		List<MyUserQ3> nodeList = csvToBean.parse(beanStrategy, reader);

		Pseudograph<MyUserQ3, DefaultWeightedEdge> graph = new Pseudograph<MyUserQ3, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		for (MyUserQ3 node : nodeList) {
			graph.addVertex(node);
		}
		for (MyUserQ3 node : nodeList) {
			for (MyUserQ3 nodeAux : nodeList) {
				if (node.getFormaleducation().equals(nodeAux.getFormaleducation())
						&& node.getCompanysize().equals(nodeAux.getCompanysize()) && !node.equals(nodeAux)) {
					graph.addEdge(node, nodeAux);
				}
			}
		}
		reader.close();
		return graph;
	}

	public static void dfsQ3(Pseudograph<MyUserQ3, DefaultWeightedEdge> graph) {

		GraphIterator<MyUserQ3, DefaultWeightedEdge> iterator = new DepthFirstIterator<MyUserQ3, DefaultWeightedEdge>(
				graph);

		while (iterator.hasNext()) {
			String src = iterator.next().getCompanysize();
			if (!sizes.contains(src)) {
				sizes.add(sizes.size(), src);
				sizesQuantity.add(sizesQuantity.size(), 0);
			} else {
				for (int i = 0; i < sizes.size(); i++) {
					if (sizes.get(i).equals(src)) {
						sizesQuantity.set(i, sizesQuantity.get(i) + 1);
					}
				}
			}
		}
	}

	/**
	 * Question: Classificar a semelhança entre as mulheres empregadas na área de TI
	 * dentre os pesquisados. Vértice: Mulheres empregadas na área de TI. Aresta:
	 * Grau de semelhança. Ordenação: Não ordenado. Peso: Não possui. Critério:
	 * Semelhança = país de atuação + satisfação na carreira + anos codificando.
	 *
	 * @return the graph for question 4
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	public static Pseudograph<MyUserQ4, DefaultWeightedEdge> createQuest4() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH4));

		HeaderColumnNameMappingStrategy<MyUserQ4> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ4>();
		beanStrategy.setType(MyUserQ4.class);

		CsvToBean<MyUserQ4> csvToBean = new CsvToBeanBuilder<MyUserQ4>(reader).withType(MyUserQ4.class)
				.withIgnoreLeadingWhiteSpace(true).build();

		List<MyUserQ4> nodeList = csvToBean.parse(beanStrategy, reader);

		Pseudograph<MyUserQ4, DefaultWeightedEdge> graph = new Pseudograph<MyUserQ4, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		for (MyUserQ4 node : nodeList) {
			graph.addVertex(node);
		}
		for (MyUserQ4 node : nodeList) {
			for (MyUserQ4 nodeAux : nodeList) {
				if (node.getCareersatisfaction().equals(nodeAux.getCareersatisfaction())
						&& node.getYearscoding().equals(nodeAux.getYearscoding())
						&& node.getCountry().equals(nodeAux.getCountry()) && !node.equals(nodeAux)) {
					graph.addEdge(node, nodeAux);
				}
			}
		}

		reader.close();
		return graph;

	}


	public static void defineSetsOfSubGraphs(Pseudograph<MyUserQ4, DefaultWeightedEdge> graph) {

		@SuppressWarnings("deprecation")
		StrongConnectivityAlgorithm<MyUserQ4, DefaultWeightedEdge> scAlg = new KosarajuStrongConnectivityInspector<>(
				graph);
		stronglyConnectedSubgraphs = scAlg.stronglyConnectedSets();
	}

	public static void defineSetsOfSubGraphsQ2(Pseudograph<MyUserQ2, DefaultWeightedEdge> graph) {

		@SuppressWarnings("deprecation")
		StrongConnectivityAlgorithm<MyUserQ2, DefaultWeightedEdge> scAlg2 = new KosarajuStrongConnectivityInspector<>(
				graph);
		stronglyConnectedSubgraphsQ2 = scAlg2.stronglyConnectedSets();
	}
	
	public static void showQ4() {
		System.out.println();
		System.out.println("Questão 4:");
		System.out.println("Strongly connected components:");
		for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {

			if (stronglyConnectedSubgraphs.get(i).size() > 1) {
				for (MyUserQ4 myUserQ4 : stronglyConnectedSubgraphs.get(i)) {
					System.out.println("Womans satisfaction = " + myUserQ4.getCareersatisfaction() + " ,from country = "
							+ myUserQ4.getCountry() + " ,with years coding = " + myUserQ4.getYearscoding());
				}
				System.out.println();
			}

		}
	}
	
	public static void showQ2() {
		System.out.println();
		System.out.println("Questão 2:");
		System.out.println("Strongly connected components:");
		for (int i = 0; i < stronglyConnectedSubgraphsQ2.size(); i++) {

			if (stronglyConnectedSubgraphsQ2.get(i).size() > 1) {
				for (MyUserQ2 myUserQ2 : stronglyConnectedSubgraphsQ2.get(i)) {
					System.out.println("Formal education = " + myUserQ2.getFormaleducation() + " ,with age = "
							+ myUserQ2.getAge() + " ,with job search status = " + myUserQ2.getJobsearchstatus());
				}
				System.out.println();
			}

		}
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

		assert (graph1.vertexSet().size() != 0);
		assert (graph2.vertexSet().size() != 0);
		assert (graph3.vertexSet().size() != 0);
		assert (graph4.vertexSet().size() != 0);

		assert (graph1.edgeSet().size() != 0);
		assert (graph2.edgeSet().size() != 0);
		assert (graph3.edgeSet().size() != 0);
		assert (graph4.edgeSet().size() != 0);

		// remove comments to see some information about graphs
//        System.out.println("Número de arestas questao 1 = " + graph1.edgeSet().size());
//        System.out.println("Lista de arestas questao 2 = " + graph2.edgeSet());
//        System.out.println("Lista de arestas questao 3 = " + graph3.edgeSet());
//        System.out.println("Lista de arestas questao 4 = " +  graph4.edgeSet());
//        
//        System.out.println("Número de vértices questao 1 = " +  graph1.vertexSet().size());
//        System.out.println("Lista de vértices questao 2 = " +  graph2.vertexSet());
//        System.out.println("Lista de vértices questao 3 = " +  graph3.vertexSet());
//        System.out.println("Lista de vértices questao 4 = " +  graph4.vertexSet());
		
		Set<MyUserQ4> totalInterviewed = graph4.vertexSet();
		Set<MyUserQ2> totalInterviewedQ2 = graph2.vertexSet();
		
		dfsQ1(graph1);
		defineSetsOfSubGraphsQ2(graph2);
		dfsQ3(graph3);
		defineSetsOfSubGraphs(graph4);

		System.out.println("Questão 1:");
		for (int i = 0; i < languages.size(); i++) {
			System.out.println(
					mostWorkedWith.get(i) + " profissionais classificados como bem pagos usam " + languages.get(i));
		}

		System.out.println();
		System.out.println("Questão 3:");
		for (int i = 0; i < sizes.size(); i++) {
			System.out.println(sizesQuantity.get(i) + " trabalham para empresa de porte " + sizes.get(i).toString().replaceAll("employees", "empregados"));
		}
		System.out.println();


		showQ4();
		System.out.println("/******   Das " + totalInterviewed.size() + " mulheres entrevistadas "
				+ (totalInterviewed.size() - stronglyConnectedSubgraphs.size())
				+ " conjuntos de conexões foram formados.   *****/"); 
		
		showQ2();
		
		System.out.println("/******  Das " + totalInterviewedQ2.size() + " pessoas entrevistadas "
				+ (totalInterviewedQ2.size() - stronglyConnectedSubgraphsQ2.size())
				+ " conjuntos de conexões foram formados.   *****/");

	}
}
