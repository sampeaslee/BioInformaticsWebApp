package CS_564.Metabolites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity

@Table(name= "compounds")
public class Compound {

    @Id
    public String biggmetaboliteID;

    public String name;

    public  String charge;

    public String formula;

    public String inchi_key;

    public String kegg_compound;

    public String metanetx_chemical;

    @Column(length  = 2000)
    public String SMILES;
    
    @Column(length  = 2000)
    public String PRIME;

    public Compound() {
        //Need default constructor for JPA to function correctly
    }

    public Compound( String id, String name, String charge,
            String formula, String inchi_key, String kegg_compound,
            String metanetx_chemical, String SMILES, String PRIME) {
        this.biggmetaboliteID = id;
        this.name = name;
        this.charge = charge;
        this.formula = formula;
        this.inchi_key = inchi_key;
        this.kegg_compound = kegg_compound;
        this.metanetx_chemical = metanetx_chemical;
        this.SMILES = SMILES;
        this.PRIME = PRIME;
    }




}