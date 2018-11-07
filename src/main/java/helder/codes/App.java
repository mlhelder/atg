package helder.codes;

import com.opencsv.CSVReader;

import org.jgrapht.graph.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class App {
	private static final String SAMPLE_CSV_FILE_PATH3 = "/home/helder/codes/atg/resource/quest3TST.csv";
	private static final String SAMPLE_CSV_FILE_PATH2 = "/home/helder/codes/atg/resource/quest2TST.csv";
	private static final String SAMPLE_CSV_FILE_PATH4 = "/home/helder/codes/atg/resource/quest4TST.csv";
    

	@SuppressWarnings("deprecation")
	public static List<MyUserQ2> createQuest2() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(SAMPLE_CSV_FILE_PATH2), ',');
		
		HeaderColumnNameMappingStrategy<MyUserQ2> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ2>();
		beanStrategy.setType(MyUserQ2.class);
		
		CsvToBean<MyUserQ2> csvToBean = new CsvToBean<MyUserQ2>();
		List<MyUserQ2> graph2 = csvToBean.parse(beanStrategy, reader);
		
		SimpleWeightedGraph<MyUserQ2, DefaultWeightedEdge>  graph = new SimpleWeightedGraph<MyUserQ2, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
		for (MyUserQ2 myUserQ2 : graph2) {
			graph.addVertex(myUserQ2);
			for (MyUserQ2 myUserQ22 : graph2) {
				if (myUserQ2.getFormaleducation() == myUserQ22.getFormaleducation() && myUserQ2.getJobsearchstatus() == myUserQ22.getJobsearchstatus() && myUserQ2.getAge() == myUserQ22.getAge() && !myUserQ2.equals(myUserQ22)) {
					graph.addEdge(myUserQ2, myUserQ22);
				}
			}
		}
		reader.close();
		
		return graph2;	   
	}

	@SuppressWarnings("deprecation")
	public static List<MyUserQ3> createQuest3() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(SAMPLE_CSV_FILE_PATH3), ',');
		
		HeaderColumnNameMappingStrategy<MyUserQ3> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ3>();
		beanStrategy.setType(MyUserQ3.class);
		
		CsvToBean<MyUserQ3> csvToBean = new CsvToBean<MyUserQ3>();
		List<MyUserQ3> graph3 = csvToBean.parse(beanStrategy, reader);
		
		SimpleWeightedGraph<MyUserQ3, DefaultWeightedEdge>  graph = new SimpleWeightedGraph<MyUserQ3, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
		for (MyUserQ3 myUserQ3 : graph3) {
			graph.addVertex(myUserQ3);
			for (MyUserQ3 myUserQ32 : graph3) {
				if (myUserQ3.getFormaleducation() == myUserQ32.getFormaleducation() && myUserQ3.getCompanysize() == myUserQ32.getCompanysize() && !myUserQ3.equals(myUserQ32)) {
					graph.addEdge(myUserQ3, myUserQ32);
				}
			}
		}
		reader.close();
		
		return graph3;	   
	}
	
	@SuppressWarnings("deprecation")
	public static List<MyUserQ4> createQuest4() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(SAMPLE_CSV_FILE_PATH4), ',');
		
		HeaderColumnNameMappingStrategy<MyUserQ4> beanStrategy = new HeaderColumnNameMappingStrategy<MyUserQ4>();
		beanStrategy.setType(MyUserQ4.class);
		
		CsvToBean<MyUserQ4> csvToBean = new CsvToBean<MyUserQ4>();
		List<MyUserQ4> graph4 = csvToBean.parse(beanStrategy, reader);
		
		SimpleWeightedGraph<MyUserQ4, DefaultWeightedEdge>  graph = new SimpleWeightedGraph<MyUserQ4, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
		for (MyUserQ4 myUserQ4 : graph4) {
			graph.addVertex(myUserQ4);
			for (MyUserQ4 myUserQ42 : graph4) {
				if (myUserQ4.getCareersatisfaction() == myUserQ42.getCareersatisfaction() && myUserQ4.getYearscoding() == myUserQ42.getYearscoding() && myUserQ4.getCountry() == myUserQ42.getCountry() && !myUserQ4.equals(myUserQ42)) {
					graph.addEdge(myUserQ4, myUserQ42);
				}
			}
		}
		reader.close();
		
		return graph4;	   
	}
	

    public static void main(String[] args) throws IOException {
    	createQuest3();
    	createQuest4();
    	createQuest2();  
 
    }

}