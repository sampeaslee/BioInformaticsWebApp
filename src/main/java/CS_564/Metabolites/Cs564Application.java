package CS_564.Metabolites;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	
	/**
	 * In order to load in all the data, You will have to run each method below 
	 * once. You have to run them one at a time, so uncomment one and start the 
	 * application up. Once the data for that method is done loading a message 
	 * will be printed to the screen. Then stop the application comment the method
	 * out again and then uncomment the next method and repeat.
	 * 
	 * There are 5 method that need to be run to get all the data
	 * 
	 * 
	 * NOTE: It does take a couple minutes for the metabolite and reaction data
	 * to be loaded in. As long as no error pops up, which I dont think will 
	 * happen, the code should be working. 
	 */
	
	
	
	//FIRST METHOD TO RUN (THIS ONE IS QUICK)
	/*
	@Bean
    public String CommandLineRunner(ModelRepo repository) throws Exception{

        //Object obj = new JSONParser().parse(new FileReader("exampleJSON.json"));
        Object reaction_json = new JSONParser().parse(new FileReader
            ("/home/charasi/eclipse-web/CS_564/JSON/final_compounds_from_bigg.json"));
        Object compound_json = new JSONParser().parse(new FileReader
            ("/home/charasi/eclipse-web/CS_564/JSON/final_reactions_from_bigg.json"));
        Object gene_json = new JSONParser().parse(new FileReader
            ("/home/charasi/eclipse-web/CS_564/JSON/genes_from_bigg.json"));
        
        ArrayList<String> allModels = new ArrayList<String>();
        


        
        JSONObject jsonObj = (JSONObject) gene_json;
        //keySet() returns a set of all the keys in the JSON 
        Set<String> GeneIDs = jsonObj.keySet();
        
        //Iterate through the set of keys and parse the JSON data associated 
        //with each key(GeneID).
        for(String idKey: GeneIDs) {

            JSONObject geneData =(JSONObject)  jsonObj.get(idKey);

            JSONArray model = (JSONArray) geneData.get("model"); 
            String modelName;
      
            Iterator itr = model.iterator();
            modelName = "" + itr.next(); 
            
            if(!allModels.contains(modelName)) {
                allModels.add(modelName);
            }


        }
        
        
        JSONObject jsonObj2 = (JSONObject) compound_json;
        //keySet() returns a set of all the keys in the JSON
        Set<String> MetaIDs = jsonObj.keySet();

        //Iterate through the set of keys and parse the JSON data associated
        //with each key(MetaID).
        for(String idKey: MetaIDs) {
            //Parsing the JSON
            JSONObject metaData =(JSONObject)  jsonObj.get(idKey);


            JSONArray model = (JSONArray) metaData.get("model");
            String modelName = "";

            Iterator itr = model.iterator();
            while (itr.hasNext()) {
                
                modelName = "" + itr.next();
                
                if(!allModels.contains(modelName)) {
                    allModels.add(modelName);
                }
            }
        }

        JSONObject jsonObjThree = (JSONObject) gene_json;
        //keySet() returns a set of all the keys in the JSON
        Set<String> ReactionsIDs = jsonObj.keySet();

        //Iterate through the set of keys and parse the JSON data associated
        //with each key(ReactionsID).
        for(String idKey: ReactionsIDs) {
            //Parsing the JSON
            JSONObject reactData =(JSONObject)  jsonObj.get(idKey);

            JSONArray model = (JSONArray) reactData.get("model");
            String modelName = "";

            Iterator itr = model.iterator();
            while (itr.hasNext()) {
                modelName = "" + itr.next();
                
                if(!allModels.contains(modelName)) {
                    allModels.add(modelName);
                }
            }

        }
        
        for(String s: allModels){
            repository.save(new Model(s));
        }
        
        System.out.println("DONE LOADING IN MODEL DATA!!!");
        
        return "";
        
    }*/
	
	//SECOND METHOD TO RUN (THIS ONE IS QUICK)
	
	
//    @Bean
//    public String CommandLineRunner(GeneRepo repository) throws Exception{
//        //Reading in the JSON File 
//        Object obj = new JSONParser().parse(new FileReader
//            ("JSON\\genes_from_bigg.json"));
//        //Casting to JSONObject
//        JSONObject jsonObj = (JSONObject) obj;
//        //keySet() returns a set of all the keys in the JSON 
//        Set<String> GeneIDs = jsonObj.keySet();
//        
//        //Iterate through the set of keys and parse the JSON data associated 
//        //with each key(GeneID).
//        for(String idKey: GeneIDs) {
//            //Parsing the JSON
//            JSONObject geneData =(JSONObject)  jsonObj.get(idKey);
//            JSONObject annotation = (JSONObject) geneData.get("annotation");
//            String ncbigi = "" + annotation.get("ncbigi");
//            String refseq_name = "" + annotation.get("refseq_name");
//            String sbo = "" + annotation.get("sbo");
//            JSONArray model = (JSONArray) geneData.get("model"); 
//            String modelName;
//      
//            Iterator itr = model.iterator();
//            modelName = "" + itr.next(); 
//
//            String name = "" + geneData.get("name");
//            
//            //Create a Gene Object to store the data 
//            Gene gene = new Gene(idKey, name, ncbigi, refseq_name, 
//                sbo, modelName);
//            
//            //Send that data to MySQL
//            repository.save(gene);
//
//        }
//        System.out.println("DONE LOADING IN GENE DATA!!!");
//        return "Has to have non void return type";
//    }
	
//    @Bean
//    public String CommandLineRunner(UserLoginRepo repository) throws Exception
//    {
//
//            //Create a UserLogin Object to store the data
//            UserLogin userlogin = new UserLogin("BioInformatics", "Victory");
//            //Send that data to MySQL
//            repository.save(userlogin);
//
//        System.out.println("DONE LOADING IN User DATA!!!");
//        return "Has to have non void return type";
//    }


// this is for creating a comment repo in the mysql;
/*	@Bean
	public String CommandLineRunner(CommentRepo repository) throws Exception
	{

		//Create a UserLogin Object to store the data
		CommentComb CommentComb = new CommentComb("","","");
		Comment comment = new Comment(CommentComb,"");
		//Send that data to MySQL
		repository.save(comment);

		System.out.println("DONE LOADING IN Comment DATA!!!");
		return "Has to have non void return type";
	}


/


	//THIRD METHOD TO RUN (THIS ONE TAKES LONGER)
	
	/*
	@Bean
	public String CommandLineRunner(StoichiometryRepo repository) throws Exception{
        
        Object obj = new JSONParser().parse(new FileReader
            ("/home/charasi/eclipse-web/CS_564/JSON/final_reactions_from_bigg.json"));
        JSONObject jsonObj = (JSONObject) obj;
        //keySet() returns a set of all the keys in the JSON 
        
        Set<String> reactionIDs = jsonObj.keySet();
        
        for(String key: reactionIDs) {
            
            JSONObject j =  (JSONObject) jsonObj.get(key);
            j  =  (JSONObject) j.get("stoichometry");
            Set<String> metaIDs = j.keySet();
            String start = "";
            String end= "";
            for(String s: metaIDs) {
                Double coef = (Double) j.get(s);
                if(coef < 0) {
                        start = start + s + ":"+ coef + ",#" ;
                }else {
                        end = end + s + ":"+ coef + ",#" ;
                }               
            }
            
            
            if(start.length() != 0) {
                start = "#" + start;

                start = start.substring(0, start.length() - 2);

            }
            
            if(end.length() != 0) {
                end = "#" + end;

                end = end.substring(0, end.length() - 2);
            }
            
            
            Stoichiometry stoichometry = new Stoichiometry(key, start,end);
            repository.save(stoichometry);
            
   
        }
       
       System.out.println("DONE LOADING IN STOICHOMETRY DATA!!!");
	   return "Has to have non void return type";
	}
	*/
    /** Only one of the @Bean can be run in each time. So it needs three times to store all of json files into a json file.
     * For some reasons, it takes minutes to run the metabolites and reactions. I assume it may be caused by the iterator.
     * */

	//FOURTH METHOD TO RUN (THIS ONE TAKES A COUPLE MINS)
 /*
 @Bean
 public String CommandLineRunner(CompoundRepo repository,
     MetaboliteRepo metaRepo, LinkedRepo linkRepo) throws Exception{
     //Reading in the JSON File
     Object obj = new JSONParser().parse(new FileReader
             ("/home/charasi/eclipse-web/CS_564/JSON/final_compounds_from_bigg.json"));
     //Casting to JSONObject
     JSONObject jsonObj = (JSONObject) obj;
     //keySet() returns a set of all the keys in the JSON
     Set<String> MetaIDs = jsonObj.keySet();

     //Iterate through the set of keys and parse the JSON data associated
     //with each key(MetaID).
     for(String idKey: MetaIDs) {
         //Parsing the JSON
         JSONObject metaData =(JSONObject)  jsonObj.get(idKey);
         JSONObject annotation = (JSONObject) metaData.get("annotation");
         String bigg_compoundID = "" + annotation.get("bigg.metabolite");
         String inchi_key = "" + annotation.get("inchi_key");
         String metanetx_chemical = "" + annotation.get("metanetx.chemical");
         String kegg_compound = "" + annotation.get("kegg.compound");

         JSONArray model = (JSONArray) metaData.get("model");
         String modelName = "";

         Iterator itr = model.iterator();
         while (itr.hasNext()) {
            modelName = "" + itr.next();
            MetaModel metamodel = new MetaModel(idKey, modelName);
            Linked link = new Linked(metamodel);
            linkRepo.save(link);
            
         }


         String compartment = "" + metaData.get("compartment");
         String charge =  "" + metaData.get("charge");
         String name = "" + metaData.get("name");
         String formula = "" + metaData.get("formula");


         // I haven't figure out how to set up the long String datatype, so I just put the "" into the database instead.
         JSONObject signature = (JSONObject) metaData.get("signature");
         String SMILES = "" + signature.get("SMILES");
         
         if(SMILES.compareTo("NA") == 0){
             SMILES = "null";
         }
         
         String PRIME = "" + signature.get("PRIME");

         //Create a Metabolite Object to store the data
         Compound compound = new Compound(bigg_compoundID, name, charge, formula,
                 inchi_key, kegg_compound, metanetx_chemical, SMILES, PRIME);


         //Send that data to MySQL
         repository.save(compound);
         
         Metabolite m = new Metabolite(idKey,bigg_compoundID, compartment );
         
         metaRepo.save(m);
         
     }
     System.out.println("DONE LOADING IN METABOLITE DATA!!!");
     return "Has to have non void return type";
 }*/


//FITH METHOD TO RUN(THIS ONE TAKES A COUPLE MINUTES AS WELL)
 /*
 @Bean
 public String CommandLineRunner(ReactionRepo repository, HasRepo hasRepo) throws Exception{
     //Reading in the JSON File
     Object obj = new JSONParser().parse(new FileReader
             ("/home/charasi/eclipse-web/CS_564/JSON/final_reactions_from_bigg.json"));
     //Casting to JSONObject
     JSONObject jsonObj = (JSONObject) obj;
     //keySet() returns a set of all the keys in the JSON
     Set<String> ReactionsIDs = jsonObj.keySet();

     //Iterate through the set of keys and parse the JSON data associated
     //with each key(ReactionsID).
     for(String idKey: ReactionsIDs) {
         //Parsing the JSON
         JSONObject reactData =(JSONObject)  jsonObj.get(idKey);


         String name = "" + reactData.get("name");
         String lowerbound = "" + reactData.get("lower_bound");
         String subsystem = "" + reactData.get("subsystem");
         String higherbound = "" + reactData.get("upper_bound");

         JSONArray model = (JSONArray) reactData.get("model");
         String modelName = "";

         Iterator itr = model.iterator();
         while (itr.hasNext()) {
             modelName = "" + itr.next();
             ReactionModel metamodel = new ReactionModel(idKey, modelName);
             Has has  = new Has(metamodel);
             hasRepo.save(has);
         }

         // I haven't figure out how to set up the long String datatype, so I just put the "" into the database instead.
         String signature = "" +  reactData.get("signature");
         //String stoichiometry = "";// + reactData.get("stoichometry");
         String gene_reaction_rule = "" +  reactData.get("gene_reaction_rule");

         
         //Create a Reaction Object to store the data
         Reaction reaction = new Reaction(idKey, name, higherbound, lowerbound, subsystem,
          signature, gene_reaction_rule);

         //Send that data to MySQL BIOMASS_Ec_iJO1366_WT_53p95M
             repository.save(reaction);
         
     }
     System.out.println("DONE LOADING IN REACTION DATA!!!");
     return "Has to have non void return type";
 }*/


//Sixth METHOD TO RUN(THIS ONE TAKES 20 MINUTES AS WELL)
//	@Bean
//	public String CommandLineRunner(StartMetaReactionRepo startRepo,  EndMetaReactionRepo endRepo) throws Exception{
//
//		Object obj = new JSONParser().parse(new FileReader
//				("JSON\\final_reactions_from_bigg.json"));
//		JSONObject jsonObj = (JSONObject) obj;
//		//keySet() returns a set of all the keys in the JSON
//
//		Set<String> reactionIDs = jsonObj.keySet();
//		for(String key: reactionIDs) {
//
//			JSONObject j =  (JSONObject) jsonObj.get(key);
//			j  =  (JSONObject) j.get("stoichometry");
//			Set<String> metaIDs = j.keySet();
//			String start = "";
//			String end= "";
//			for(String s: metaIDs) {
//				Double coef = (Double) j.get(s);
//				if(coef < 0) {
//					start = start + s + ":"+ coef + ",#" ;
//					MetaReaction metaReaction = new MetaReaction(s, key);
//					StartMetaReaction startMetaReaction = new StartMetaReaction(metaReaction);
//					startRepo.save(startMetaReaction);
//				}else {
//					end = end + s + ":"+ coef + ",#" ;
//					MetaReaction metaReaction = new MetaReaction(s, key);
//					EndMetaReaction endMetaReaction = new EndMetaReaction(metaReaction);
//					endRepo.save(endMetaReaction);
//				}
//
//			}
////			Stoichiometry stoichometry = new Stoichiometry(key, start,end);
////			startRepo.save(start);
//
//
//		}
//
//		System.out.println("DONE LOADING IN STOICHOMETRY DATA!!!");
//		return "Has to have non void return type";
//	}




}
