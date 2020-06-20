package CS_564.Metabolites;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class Cs564Application {

	public static void main(String[] args) {
		SpringApplication.run(Cs564Application.class, args);
	}
	
	/*
	 * The below code will read in the JSON file containing the information
	 * about Genes and then add the data to a table called "genes". 
	 * If you uncomment it and then start the application it will run and the 
	 * table will be created and updated with data. Once the data is in the
	 * database you can comment out the code again, you only need to run this 
	 * code once.
	 * 
	 * I made a Gene.java and GeneRepo.java interface that are using similar
	 * logic to the SimpleEntity.java and SimpleEnityRepo.java files 
	 * 
	 * @Bean - this notation is sort of confusing, basically Spring Boot is 
	 * built from the Spring Framework and in Spring you have to declare
	 * a bean in an xml file in order to initialize an object. I have not used
	 * Spring much so my knowledge is limited. Spring Boot is 
	 * easier to use because it does all the bean configuration behind the scenes 
	 * so you dont have to worry about XML files besides the pom.xml file. 
	 * 
	 * CommandLineRunner - is an object that can be used to automatically 
	 * run the code once the application is launched
	 * 
	 * 
	 * Here is the tutorial i looked at while writing the code to send the data
	 * to MySQL
	 * https://spring.io/guides/gs/accessing-data-jpa/
	 * 
	 * This is a useful linked to see how to parse JSON in Java
	 * https://www.geeksforgeeks.org/parse-json-java/
	 * 
	 */
	
	/*
	@Bean
	public String CommandLineRunner(GeneRepo repository) throws Exception{
	    //Reading in the JSON File 
        Object obj = new JSONParser().parse(new FileReader
            ("JSON\\genes_from_bigg.json"));
        //Casting to JSONObject
        JSONObject jsonObj = (JSONObject) obj;
        //keySet() returns a set of all the keys in the JSON 
        Set<String> GeneIDs = jsonObj.keySet();
        
        //Iterate through the set of keys and parse the JSON data associated 
        //with each key(GeneID).
        for(String idKey: GeneIDs) {
            //Parsing the JSON
            JSONObject geneData =(JSONObject)  jsonObj.get(idKey);
            JSONObject annotation = (JSONObject) geneData.get("annotation");
            String ncbigi = "" + annotation.get("ncbigi");
            String refseq_name = "" + annotation.get("refseq_name");
            String sbo = "" + annotation.get("sbo");
            JSONArray model = (JSONArray) geneData.get("model"); 
            String modelName;
      
            Iterator itr = model.iterator();
            modelName = "" + itr.next(); 

            String name = "" + geneData.get("name");
            
            //Create a Gene Object to store the data 
            Gene gene = new Gene(idKey, name, ncbigi, refseq_name, 
                sbo, modelName);
            
            //Send that data to MySQL
            repository.save(gene);
        }
    
	   return "Has to have non void return type";
	}*/
}
