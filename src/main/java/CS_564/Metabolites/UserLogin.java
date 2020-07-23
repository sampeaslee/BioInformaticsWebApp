package CS_564.Metabolites;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//Mapping the entity to a table in MySQL names genes
@Table(name= "userlogin")
public class UserLogin 
{
	 @Id
	 public String user;
	    
	 public String password;
	 
	 public UserLogin() {
	        //Need default constructor for JPA to function correctly 
	    }
	    
	    
	    public UserLogin(String user, String password) 
	    {
	        
	        this.user = user;
	        this.password = password;

	    }
}
