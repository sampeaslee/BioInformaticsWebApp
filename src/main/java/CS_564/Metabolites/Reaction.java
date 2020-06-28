package CS_564.Metabolites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
//Mapping the entity to a table in MySQL names genes
@Table(name= "reactions")
public class Reaction {
    @Id
    public String ReactionID;

    public String name;

    public  String HigherBound;

    public String LowerBound;

    public String Subsystem;

    public  String model;
    
    @Column(columnDefinition = "MEDIUMTEXT", length  = 80000)
    public String signature;
    @Column(columnDefinition = "MEDIUMTEXT", length  = 80000)
    public String gene_reaction_rule;
    
    public Reaction() {
        //Need default constructor for JPA to function correctly
    }


    public Reaction(String ReactionID, String name ,String HigherBound,
                String LowerBound,String Subsystem,String model,
                String signature,String gene_reaction_rule) {

        this.ReactionID = ReactionID;
        this.name = name;
        this.HigherBound = HigherBound;
        this.LowerBound = LowerBound;
        this.Subsystem = Subsystem;
        this.model = model;
        this.signature = signature;
        this.gene_reaction_rule = gene_reaction_rule;
    }
}
