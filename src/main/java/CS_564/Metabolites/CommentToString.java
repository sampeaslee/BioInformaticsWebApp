package CS_564.Metabolites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

public class CommentToString{
    public String name;
    public String email;
    public String comment;

    public CommentToString() {
    }

    public CommentToString(String name, String email, String comment) {
        this.name = name;
        this.email = email;
        this.comment = comment;
    }


}