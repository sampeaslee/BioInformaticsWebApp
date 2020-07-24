package CS_564.Metabolites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity

@Table(name= "comment")
public class Comment {

    @Id
    public String id;

    public String email;

    public String name;

    @Column(length  = 10000)
    public String content;

    public int score;

    public Comment() {
        //Need default constructor for JPA to function correctly
    }

    public Comment( String id, String name, String email,
                     String content, int score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
        this.score = score;
    }

}