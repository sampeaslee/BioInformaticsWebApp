package CS_564.Metabolites;

public class MetaCompound {
    public String BiggmetaboliteID;

    public String cname;

    public  String charge;

    public String formula;

    public String inchi_key;

    public String kegg_compound;

    public String metanetx_chemical;
    
    public String metaboliteID;

    public String compartment;

    public String bigg_compoundID;
    
    public MetaCompound( String id, String cname, String charge,
        String formula, String inchi_key, String kegg_compound,
        String metanetx_chemical) {
    this.BiggmetaboliteID = id;
    this.cname = cname;
    this.charge = charge;
    this.formula = formula;
    this.inchi_key = inchi_key;
    this.kegg_compound = kegg_compound;
    this.metanetx_chemical = metanetx_chemical;

}
    
    
    
}
