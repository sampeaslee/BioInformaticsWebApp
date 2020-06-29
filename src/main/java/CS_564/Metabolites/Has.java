package CS_564.Metabolites;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Has")
public class Has {
    @EmbeddedId
    ReactionModel reactionmodel;
    
    public Has() {
        
    }
    
    public Has(ReactionModel reactionmodel) {
        this.reactionmodel = reactionmodel;
    }
}
