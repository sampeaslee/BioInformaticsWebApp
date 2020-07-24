package CS_564.Metabolites;

import javax.persistence.*;


@Entity
@Table(name = "comment")
public class Comment {
    @EmbeddedId
    CommentComb CommentComb;

    @Column(name = "content", nullable = false, length  = 10000)
    String content;

    public Comment() {
        //Need default constructor for JPA to function correctly
    }

    public Comment( CommentComb Commentid, String content) {
        this.CommentComb = Commentid;
        this.content = content;

    }

}