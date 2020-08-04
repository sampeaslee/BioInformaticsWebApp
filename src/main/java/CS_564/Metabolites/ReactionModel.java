package CS_564.Metabolites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

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
