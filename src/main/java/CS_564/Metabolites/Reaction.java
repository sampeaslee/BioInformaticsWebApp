package CS_564.Metabolites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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


    
    @Column(columnDefinition = "MEDIUMTEXT", length  = 80000)
    public String signature;
    @Column(columnDefinition = "MEDIUMTEXT", length  = 80000)
    public String gene_reaction_rule;
    
    public Reaction() {
        //Need default constructor for JPA to function correctly
    }


    public String getReactionID() {
        return ReactionID;
    }

    public String getName() {
        return name;
    }

    public String getHigherBound() {
        return HigherBound;
    }

    public String getLowerBound() {
        return LowerBound;
    }

    public String getSubsystem() {
        return Subsystem;
    }

    public void setReactionID(String reactionID) {
        ReactionID = reactionID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHigherBound(String higherBound) {
        HigherBound = higherBound;
    }

    public void setLowerBound(String lowerBound) {
        LowerBound = lowerBound;
    }

    public void setSubsystem(String subsystem) {
        Subsystem = subsystem;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setGene_reaction_rule(String gene_reaction_rule) {
        this.gene_reaction_rule = gene_reaction_rule;
    }

    public String getSignature() {
        return signature;
    }

    public String getGene_reaction_rule() {
        return gene_reaction_rule;
    }

    public Reaction(String ReactionID, String name , String HigherBound,
                    String LowerBound, String Subsystem,
                    String signature, String gene_reaction_rule) {

        this.ReactionID = ReactionID;
        this.name = name;
        this.HigherBound = HigherBound;
        this.LowerBound = LowerBound;
        this.Subsystem = Subsystem;

        this.signature = signature;
        this.gene_reaction_rule = gene_reaction_rule;
    }
}
