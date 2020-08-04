package CS_564.Metabolites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name= "models")
public class Model{
    
    @Id
    String name;
    
    public Model() {
        
    }
    
    
    public Model(String name) {
        this.name = name;
    }
    
}
