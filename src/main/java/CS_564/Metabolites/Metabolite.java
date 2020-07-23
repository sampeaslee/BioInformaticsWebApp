package CS_564.Metabolites;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity

@Table(name= "metabolites")
public class Metabolite {

    @Id
    public String metaboliteID;

    public String compartment;

    public String bigg_compoundID;


    public Metabolite() {
        //Need default constructor for JPA to function correctly
    }

    public Metabolite( String id, String bigg_compoundID, String compartment) {
        
        this.metaboliteID = id;
       
        this.compartment = compartment;

        this.bigg_compoundID = bigg_compoundID;

    }

    @Override
    public String toString() {
        return String.format(
                "Metabolite[id=%s, compartment='%s', compound='%s']",
                metaboliteID, compartment, bigg_compoundID);
    }

    public String getId() {
        return metaboliteID;
    }

    public void SetID(String id) {
        this.metaboliteID = id;
    }


}