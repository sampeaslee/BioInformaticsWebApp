
package CS_564.Metabolites;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "StartMetaReaction")
public class StartMetaReaction {
    @EmbeddedId
    MetaReaction metaReaction;

    public StartMetaReaction() {

    }

    public StartMetaReaction(MetaReaction metaReaction) {
        this.metaReaction = metaReaction;
    }
}
