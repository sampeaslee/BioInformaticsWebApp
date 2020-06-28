package CS_564.Metabolites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReactionModel implements Serializable  {
    @Column(name = "reactionID", nullable = false)
    String reactionID;
    @Column(name = "modelName", nullable = false)
    String modelName;
    
    public ReactionModel() {
        
    }
    public ReactionModel(String id, String model) {
        this.reactionID = id;
        this.modelName = model;
    }
    
    
}
