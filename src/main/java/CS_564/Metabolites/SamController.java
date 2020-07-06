package CS_564.Metabolites;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SamController {
    @Autowired
    GeneRepo GeneRepo;

    @GetMapping("/home")
    public String se(String name, Model model) {
        
        String sendFromBackendToFrontend = "SAM";
        
        //Calling the method that I created in the SimpleEntiyRepo which 
        //interacts with MySQL and Returns all the information 
        //stored in the "test_table" table

        


        //This method adds an attribute called sentFromBackend that can 
        //be used to access the text sent from the backend in the html
        //In this example you can access the text in the html like this:
        //<p th:text = "${sentFromBackend}"/> and it will be displayed on
        // the webpage
        model.addAttribute("sentFromBackend",sendFromBackendToFrontend);
        
        return "test";
        
    }
    
    /*@GetMapping("/searchTemplate")
    public String sendTo(String name, Model model) {
        


      
        
        ArrayList <Gene> s = 
            (ArrayList<Gene>) GeneRepo.getGenes();
        
        Gene g = s.get(0);
        
        for(int i = 0; i < s.size(); i ++) {
            s.get(i).geneID = s.get(i).geneID.trim();
        }
        
        String [] gene = {g.geneID, g.model, g.name, g.ncbigi, g.refseq_name
            , g.sbo};
        
        //Printing out contents of the table "test_table"
        
        
        
        model.addAttribute("genes",s);
        
        model.addAttribute("g",s.get(0));
        
        Gene test = GeneRepo.findByGeneID("10FTHFtl");
        
        System.out.println(test.geneID + " " + test.model + " " + test.name);
        
        return "searchTemplate";
        
    }
    

    @PostMapping("/bootstrap")
    public String getsearch(String textFromFrontend,@ModelAttribute("welcome_text") String welcome, Model model) {
        System.out.println(textFromFrontend);
        welcome = textFromFrontend;
        
        ArrayList <Gene> s = 
            (ArrayList<Gene>) GeneRepo.autoSearch(textFromFrontend);
        System.out.println(s.size());
        
        model.addAttribute("genes", s);
        return "gene";
    }*/
    
    
    @GetMapping("/gene")
    public String senTo(@RequestParam(value = "name", defaultValue = "")
    String name, Model model) {
               
                
                if( name.equals("") ) {
                    model.addAttribute("test", "No Search Specifed: Showing All Genes");
                    ArrayList <Gene> s = 
                        (ArrayList<Gene>) GeneRepo.getGenes();
                    model.addAttribute("genes", s);
                }else {
                    ArrayList <Gene> s = 
                        (ArrayList<Gene>) GeneRepo.autoSearch(name);
                    System.out.println(s.size());
                    String search = "Current Search: " + name;
                    model.addAttribute("test", search);
                    model.addAttribute("genes", s);    
                    }
       
        return "gene";
        
    }
    

    
    
    

}
