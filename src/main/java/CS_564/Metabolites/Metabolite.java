package CS_564.Metabolites;

import javax.persistence.*;import javax.persistence.Entity;
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

    public String name;

    public  String charge;

    public String compartment;

    public String formula;

    public  String model;

    public String bigg_compoundID;

    public String inchi_key;

    public String kegg_compound;

    public String metanetx_chemical;

    @Column(length  = 2000)
    public String SMILES;
    
    @Column(length  = 2000)
    public String PRIME;

    public Metabolite() {
        //Need default constructor for JPA to function correctly
    }

    public Metabolite( String id, String name, String charge, String compartment,
            String formula, String model, String bigg_compoundID, String inchi_key, String kegg_compound,
            String metanetx_chemical, String SMILES, String PRIME) {
        this.metaboliteID = id;
        this.name = name;
        this.charge = charge;
        this.compartment = compartment;
        this.formula = formula;
        this.model = model;
        this.bigg_compoundID = bigg_compoundID;
        this.inchi_key = inchi_key;
        this.kegg_compound = kegg_compound;
        this.metanetx_chemical = metanetx_chemical;
        this.SMILES = SMILES;
        this.PRIME = PRIME;
    }

    @Override
    public String toString() {
        return String.format(
                "Metabolite[id=%s, model='%s', compartment='%s', compound='%s']",
                metaboliteID, model, compartment, bigg_compoundID);
    }

    public String getId() {
        return metaboliteID;
    }

    public void SetID(String id) {
        this.metaboliteID = id;
    }


}