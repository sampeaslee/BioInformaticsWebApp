package CS_564.Metabolites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MetaReaction implements Serializable  {
    @Column(name = "metaboliteID", nullable = false)
    String metaboliteID;
    @Column(name = "ReactionID", nullable = false)
    String reactionID;

    public MetaReaction() {

    }
    public MetaReaction(String id, String model) {
        this.metaboliteID = id;
        this.reactionID = model;
    }


}
