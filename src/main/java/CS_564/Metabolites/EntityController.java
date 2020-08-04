package CS_564.Metabolites;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.PostMapping;

//This class it used to interact directly with the frontend 
@Controller
public class EntityController{    
      @Autowired
       GeneRepo genesRepo;
      
      @Autowired
       ReactionRepo reactionRepo;
      
      @Autowired
      MetaboliteRepo metaboliteRepo;
      
      @Autowired
      ModelRepo modelsRepo;
      
      @Autowired
      UserLoginRepo userRepo;
      
      @Autowired
      StoichiometryRepo stoichiometryRepo;
      
      @PersistenceContext protected EntityManager em;
              
      /**
         * @GetMapping is used to map HTTP get request  to certain webpages
         * 
         * When you visit the url http://localhost@ModelAttribute:8080/homepage this method is 
         * called and the String "Text sent from backend" is sent to thLet's say that we have an endpoint /api/foos that takes a query parame html page
         * where is can be displayed
         */
        
        @GetMapping("/login")
        public String loginToAdmin() 
        {           
            return "login";    
        }
        
//        @GetMapping("/accessdenied")
//        public String accessDenied() 
//        {
//            return "accessdenied";   
//        }
      
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost@ModelAttribute:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to thLet's say that we have an endpoint /api/foos that takes a query parame html page
     * where is can be displayed
     */
    
    @GetMapping("/update")
    public String sendToFrontend(Model model) 
    {
        return "update";      
    }
    
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/addgenes")
    public String getAddGenes(Model model)
    {
        ArrayList <Gene> list_of_genes = (ArrayList<Gene>) genesRepo.getListOfGenes();
        model.addAttribute("listofgenes", list_of_genes);
            
        return "addgenes";
    }
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/deletegenes")
    public String getDeleteGenes(Model model)
    {
        
        ArrayList <Gene> list_of_genes = (ArrayList<Gene>) genesRepo.getListOfGenes();
        model.addAttribute("listofgenes", list_of_genes);
                    
        return "deletegenes";  
    }
    
    @PostMapping("/deletegenes")
    public String deleteGenes(String genes_to_delete) throws ParseException
    {    
         Gene delete_gene_object = genesRepo.getAGene(genes_to_delete);
         genesRepo.delete(delete_gene_object);
                 
        return "deletegenes";
    }
    
    
    @PostMapping("/addgenes")
    public String postGenes(String gene_to_add) throws ParseException
    {
        JSONParser parser = new JSONParser();
        JSONObject convert_to_json = (JSONObject) parser.parse(gene_to_add); 
        
         String gene_geneID = ((String)convert_to_json.get("geneID"));
         String gene_name = ((String)convert_to_json.get("name"));
         String gene_ncbigi = ((String)convert_to_json.get("ncbigi"));
         String gene_refseq_name = ((String)convert_to_json.get("refseq_name"));
         String gene_sbo = ((String)convert_to_json.get("sbo"));
         String gene_model = ((String)convert_to_json.get("model"));

        if (genesRepo.getAGene(gene_geneID) != null)
        {
            Gene update_gene_object = genesRepo.getAGene(gene_geneID);
            
            if(!update_gene_object.name.equals(gene_name))
            {
                genesRepo.updateGenesName(gene_geneID, gene_name);
            }
            
            if(!update_gene_object.ncbigi.equals(gene_ncbigi))
            {
                genesRepo.updateGeneNcbigi(gene_geneID, gene_ncbigi);
            }
            
            if(!update_gene_object.refseq_name.equals(gene_refseq_name))
            {
                genesRepo.updateGeneRefseqName(gene_geneID, gene_refseq_name);
            }
            
            if(!update_gene_object.sbo.equals(gene_sbo))
            {
                genesRepo.updateGeneSbo(gene_geneID, gene_sbo);
            }
            
            if(!update_gene_object.model.equals(gene_model))
            {
                genesRepo.updateGeneModel(gene_geneID, gene_model);
            }
        }
        else
        {
             // create a gene object
             Gene addGeneObj = new Gene(gene_geneID, gene_name, gene_ncbigi, gene_refseq_name,
                                        gene_sbo, gene_model);
            genesRepo.save(addGeneObj);
        }
        
        return "addgenes";
    }
    
    
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/addreactions")
    public String getAddReactions(Model model)
    {
      javax.persistence.Query queryCompound = em.createQuery("Select r, s FROM Reaction r, Stoichiometry s where r.ReactionID = s.reactionID", Object[].class);
    	        
	  ArrayList<Object[]> results = (ArrayList<Object[]>) queryCompound.getResultList();
        
        
        ArrayList<Reaction> reaction_objects = new ArrayList<Reaction>();
        ArrayList<Stoichiometry> stoichiometry_objects = new ArrayList<Stoichiometry>();

        for (Object[] obj : results) {
        	reaction_objects.add((Reaction) obj[0]);
        	stoichiometry_objects.add((Stoichiometry) obj[1]);
        }            	
    	
       // ArrayList <Reaction> list_of_reactions = (ArrayList<Reaction>) reactionRepo.getListOfReactions();
        model.addAttribute("listofreactions", reaction_objects);
        model.addAttribute("listofstoichiometry", stoichiometry_objects);
        
        return "addreactions";
    }
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/deletereactions")
    public String getDeleteReactions(Model model)
    {
        ArrayList <Reaction> list_of_reactions = (ArrayList<Reaction>) reactionRepo.getListOfReactions();
        model.addAttribute("listofreactions", list_of_reactions);
        
        return "deletereactions";  
    }
    
    @PostMapping("/deletereactions")
    public String deleteReaction(String reactions_to_delete) throws ParseException
    {    
         Reaction delete_reaction_object = reactionRepo.getAReaction(reactions_to_delete);
         reactionRepo.delete(delete_reaction_object);
                 
        return "deletereactions";
    }
    
    @PostMapping("/addreactions")
    public String postReactions(String reaction_to_add) throws ParseException
    {
        JSONParser parser = new JSONParser();
        JSONObject convert_to_json = (JSONObject) parser.parse(reaction_to_add); 
        
         String reaction_reactionID = ((String)convert_to_json.get("ReactionID"));
         String reaction_name = ((String)convert_to_json.get("name"));
         String reaction_higher_bound = ((String)convert_to_json.get("HigherBound"));
         String reaction_lower_bound = ((String)convert_to_json.get("LowerBound"));
         String reaction_subsystem = ((String)convert_to_json.get("Subsystem"));
         String reaction_signature = ((String)convert_to_json.get("signature"));
         String reaction_rule = ((String)convert_to_json.get("gene_reaction_rule"));
         
         String stoichiometry_start_Metabolites = ((String)convert_to_json.get("startMetabolites"));
         String stoichiometry_end_Metabolites = ((String)convert_to_json.get("endMetabolites"));

        if (reactionRepo.getReactionID(reaction_reactionID) != null)
        {
            Reaction update_reaction_object = reactionRepo.getAReaction(reaction_reactionID);
            
            if(!update_reaction_object.name.equals(reaction_name))
            {
                reactionRepo.updateReactionName(reaction_reactionID, reaction_name);
            }
            
            if(!update_reaction_object.HigherBound.equals(reaction_higher_bound))
            {
                reactionRepo.updateReactionHigherBound(reaction_reactionID, reaction_higher_bound);
            }
            
            if(!update_reaction_object.LowerBound.equals(reaction_lower_bound))
            {
                reactionRepo.updateReactionsLowerBound(reaction_reactionID, reaction_lower_bound);
            }
            
            if(!update_reaction_object.Subsystem.equals(reaction_subsystem))
            {
                reactionRepo.updateReactionSubsystem(reaction_reactionID, reaction_subsystem);
            }
            
            if(!update_reaction_object.signature.equals(reaction_signature))
            {
                reactionRepo.updateReactionSignature(reaction_reactionID, reaction_signature);
            }
            
            if(!update_reaction_object.gene_reaction_rule.equals(reaction_rule))
            {
                reactionRepo.updateReactionRule(reaction_reactionID, reaction_rule);
            }
        }
        else
        {
             // create a reaction object
             Reaction addReactionObj = new Reaction(reaction_reactionID, reaction_name, reaction_higher_bound, 
                                                    reaction_lower_bound, reaction_subsystem, reaction_signature,
                                                    reaction_rule);
            reactionRepo.save(addReactionObj);
        }
        
        //
        if (stoichiometryRepo.queryExampleTwo(reaction_reactionID) != null)
        {
            Stoichiometry update_stoichiometry_object = stoichiometryRepo.queryExampleTwo(reaction_reactionID);
                        
            if(!update_stoichiometry_object.startMetabolites.equals(stoichiometry_start_Metabolites))
            {
            	stoichiometryRepo.updateStartMetabolites(reaction_reactionID, stoichiometry_start_Metabolites);
            }
            
            if(!update_stoichiometry_object.endMetabolites.equals(stoichiometry_end_Metabolites))
            {
            	System.out.println(stoichiometry_end_Metabolites);
            	stoichiometryRepo.updateEndMetabolites(reaction_reactionID, stoichiometry_end_Metabolites);
            }
        }
        else
        {
             // create a stoichiometry object
        	Stoichiometry addStiochiometryObj = new Stoichiometry(reaction_reactionID, stoichiometry_start_Metabolites,
        												stoichiometry_end_Metabolites);
        	stoichiometryRepo.save(addStiochiometryObj);
        }
        
        return "addreactions";
    }      
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/addmetabolites")
    public String getAddMetabolites(Model model)
    {
        ArrayList <Metabolite> list_of_metabolites = (ArrayList<Metabolite>) metaboliteRepo.getListOfMetabolites();
        ArrayList <String> list_of_compounds= (ArrayList<String>) metaboliteRepo.getListOfCompounds();      
        
        model.addAttribute("listofmetabolites", list_of_metabolites);
        model.addAttribute("listofcompounds", list_of_compounds);
        
        return "addmetabolites";
    }
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/deletemetabolites")
    public String getDeleteMetabolites(Model model)
    {
        ArrayList <Metabolite> list_of_metabolites = (ArrayList<Metabolite>) metaboliteRepo.getListOfMetabolites();
        model.addAttribute("listofmetabolites", list_of_metabolites);
                    
        return "deletemetabolites";  
    }
    
    @PostMapping("/deletemetabolites")
    public String deleteMetabolites(String metabolites_to_delete) throws ParseException
    {    
        Metabolite delete_metabolite_object = metaboliteRepo.getAMetabolite(metabolites_to_delete);
        metaboliteRepo.delete(delete_metabolite_object);
                 
        return "deletemetabolites";
    }
    
    @PostMapping("/addmetabolites")
    public String postMetabolites(String metabolites_to_add) throws ParseException
    {
        JSONParser parser = new JSONParser();
        JSONObject convert_to_json = (JSONObject) parser.parse(metabolites_to_add); 
        
         String metabolites_metaboliteID = ((String)convert_to_json.get("metaboliteID"));
         String metabolites_compartment = ((String)convert_to_json.get("compartment"));
         String metabolites_bigg_compoundID = ((String)convert_to_json.get("bigg_compoundID"));
         System.out.println("###############################" + metabolites_bigg_compoundID);


        if (metaboliteRepo.getMetabolitesID(metabolites_metaboliteID) != null)
        {
            Metabolite update_metabolite_object = metaboliteRepo.getAMetabolite(metabolites_metaboliteID);
            
            if(!update_metabolite_object.compartment.equals(metabolites_compartment))
            {
                metaboliteRepo.updateCompartment(metabolites_metaboliteID, metabolites_compartment);
            }
            
            if(!update_metabolite_object.bigg_compoundID.equals(metabolites_bigg_compoundID))
            {
                metaboliteRepo.updateCompound(metabolites_metaboliteID, metabolites_bigg_compoundID);
            }
        }
        else
        {
             // create a metabolite object
            Metabolite addMetaboliteObj = new Metabolite(metabolites_metaboliteID, metabolites_compartment, 
                                                            metabolites_bigg_compoundID);
            metaboliteRepo.save(addMetaboliteObj);
        }
        
        return "addmetabolites";
    }
    
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
//    @GetMapping("/addmodels")
//    public String getAddModel(Model model)
//    {
//        ArrayList<String> list_of_model = (ArrayList<String>) modelsRepo.getListOfModel();
//        
//        model.addAttribute("listofmodel", list_of_model);
//        
//        return "addmodels";
//    }
//    
//    /**
//     * @GetMapping is used to map HTTP get request  to certain webpages
//     * 
//     * When you visit the url http://localhost:8080/homepage this method is 
//     * called and the String "Text sent from backend" is sent to the html page
//     * where is can be displayed
//     */
//    
//    @GetMapping("/deletemodels")
//    public String getDeleteModel(Model model)
//    {
//        ArrayList <String> list_of_model = (ArrayList<String>) modelsRepo.getListOfModel();
//        model.addAttribute("listofmodel", list_of_model);
//                    
//        return "deletemodels";  
//    }
//    
//    @PostMapping("/deletemodels")
//    public String deleteModel(String models_to_delete) throws ParseException
//    {    
//        modelsRepo.deleteAModel(models_to_delete);
//                         
//        return "deletemodels";
//    }
//    
//    @PostMapping("/addmodels")
//    public String postModel(String models_to_add) throws ParseException
//    {
//        JSONParser parser = new JSONParser();
//        JSONObject convert_to_json = (JSONObject) parser.parse(models_to_add); 
//        
//         String models_nameID = ((String)convert_to_json.get("name"));
//         
//         if (modelsRepo.getModelID(models_nameID) == null)
//         {
//             modelsRepo.insertModel(models_nameID);
//         }
//                
//        return "addmodels";
//    }

}