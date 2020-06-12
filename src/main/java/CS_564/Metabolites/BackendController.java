package CS_564.Metabolites;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//This class it used to interact directly with the frontend 
@Controller
public class BackendController {
    //Look at SimpleEntity.java and SimpleEntityRepo.java to see details about
    //this object
    @Autowired
    SimpleEntityRepo simpleRepo;
    
    /**
     * @GetMapping is used to map HTTP get request  to certain webpages
     * 
     * When you visit the url http://localhost:8080/homepage this method is 
     * called and the String "Text sent from backend" is sent to the html page
     * where is can be displayed
     */
    
    @GetMapping("/homepage")
    public String sendToFrontend(String name, Model model) {
        
        String sendFromBackendToFrontend = "Text sent from backend";
        
        //This method adds an attribute called sentFromBackend that can 
        //be used to access the text sent from the backend in the html
        //In this example you can access the text in the html like this:
        //<p th:text = "${sentFromBackend}"/> and it will be displayed on
        // the webpage
        model.addAttribute("sentFromBackend",sendFromBackendToFrontend);
        
        return "homepage";
        
    }
    
    /**
     * LOOK at the SimpleEntity and SimpleEntiyRepo files before you try and 
     * understand this method.
     * 
     * @PostMapping is used to map HTTP post request from webpages
     * 
     * This method illustrates how you can send info from the frontend to the
     * backend. When visiting the url http://localhost:8080/homepage you can type
     * in the second text input box and then send the text to the backend server
     * and it will be printed out in your console. See the homepage.html to see
     * how the data is sent. 
     * 
     */
    
    @PostMapping("/homepage")
    public void getTextFromFrontend(String textFromFrontend) {
        
        //This prints the text that you entered in the frontend on the console
        System.out.println(textFromFrontend);
        
        //Initializing a new SimpleEntity object
        SimpleEntity simple = new SimpleEntity();
        
        //Setting the text of the SimpleEntity to the string sent from the frontend
        simple.setText(textFromFrontend);
        
        //Utilizing the JpaRepository method save that will save the object simple
        //as a row in the table "test_table with columns "id" and "text"
        simpleRepo.save(simple);
        
        //Calling the method that I created in the SimpleEntiyRepo which 
        //interacts with MySQL and Returns all the information 
        //stored in the "test_table" table
        ArrayList <SimpleEntity> s = 
            (ArrayList<SimpleEntity>) simpleRepo.queryExample();
        
        //Printing out contents of the table "test_table"
        for(SimpleEntity index: s) {
            System.out.println("ID: " + index.getId()
            + " Text: " + index.getText());
        }
        
        }
    

}
