package CS_564.Metabolites;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//Mapping the entity to a table in MySQL names genes
@Table(name= "stoichiometry")
public class Stoichiometry {
    @Id
    public String reactionID;
    
    @Column(length  = 3000)
    public String startMetabolites;
    @Column(length  = 3000)
    public String endMetabolites;
    
    
    
    public Stoichiometry() {
        
    }
    
    
    public Stoichiometry(String reactionID, String startMetabolites,
        String endMetabolites) {
        this.reactionID = reactionID;
        this.startMetabolites = startMetabolites;
        this.endMetabolites = endMetabolites;
    }
    @Override
    public String toString() {
        String stoichiometryString = "";
        stoichiometryString += startMetabolites;
        stoichiometryString += " -> ";
        stoichiometryString += endMetabolites;
        String a = stoichiometryString.replaceAll("#", " ");
        String b = a.replaceAll(",", " + ");
        return b;

    }
}
