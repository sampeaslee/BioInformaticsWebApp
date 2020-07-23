package CS_564.Metabolites;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity

@Table(name= "linked")
public class Linked {
    @EmbeddedId
    MetaModel metaModel;
    
    public Linked() {
    
    }
    
    public Linked(MetaModel metaModel) {
        this.metaModel = metaModel;
    }
}
