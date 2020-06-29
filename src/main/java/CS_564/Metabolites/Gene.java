package CS_564.Metabolites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//Mapping the entity to a table in MySQL names genes
@Table(name= "genes")
public class Gene {
    @Id
    public String geneID;
    
    public String name;
    
    public  String ncbigi;
    
    public String refseq_name;
    
    public String sbo;
    
    public  String model;
    
    public Gene() {
        //Need default constructor for JPA to function correctly 
    }
    
    
    public Gene(String id, String name ,String ncbigi,
    String refseq_name,String sbo,String model) {
        
        this.geneID = id;
        this.name = name;
        this.ncbigi = ncbigi;   
        this.refseq_name = refseq_name;
        this.sbo = sbo;    
        this.model = model;
    }
}
