package CS_564.Metabolites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentComb implements Serializable {



    @Column(name = "id", nullable = false)
    String id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "email", nullable = false)
    String email;



    public CommentComb() {

    }

    public CommentComb( String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;


    }

}

