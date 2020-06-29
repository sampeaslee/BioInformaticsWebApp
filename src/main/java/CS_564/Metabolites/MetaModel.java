package CS_564.Metabolites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MetaModel implements Serializable  {
    @Column(name = "metaboliteID", nullable = false)
    String metaboliteID;
    @Column(name = "modelName", nullable = false)
    String modelName;
    
    public MetaModel() {
        
    }
    public MetaModel(String id, String model) {
        this.metaboliteID = id;
        this.modelName = model;
    }
    
    
}
