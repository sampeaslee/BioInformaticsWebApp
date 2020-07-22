
package CS_564.Metabolites;
        import javax.persistence.EmbeddedId;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.IdClass;
        import javax.persistence.Table;

@Entity
@Table(name = "EndMetaReaction")
public class EndMetaReaction {
    @EmbeddedId
    MetaReaction metaReaction;

    public EndMetaReaction() {

    }

    public EndMetaReaction(MetaReaction metaReaction) {
        this.metaReaction = metaReaction;
    }
}
