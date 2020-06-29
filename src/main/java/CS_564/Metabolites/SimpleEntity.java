package CS_564.Metabolites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * This is one way of interacting with the database. Its sort of confusing so we 
 * do not have to use it if we do not want to. I have demonstrated another
 * way of interacting with  MySQL in the MySQLExample.java files.
 * 
 * Using @Entity declares the class as an Entity that will be stored in the MySQL
 * database in the table "test_table" with columns "id" and "text".
 * 
 * Also if there is no table in your database named "test_table" it will create 
 * a new table with that name. NOTEL: this behavior can be changed if we want 
 * to change it. 
 * 
 * When you declare a class an Entity you then MUST create an interface that 
 * extends the JpaRepository Interface, I have done this in the SimpleEntityRepo
 * You can then use this interface to interact with MySQL
 */



//Declaring the class as an entity 
@Entity
//Mapping the entity to a table in MySQL
@Table(name= "test_table")
public class SimpleEntity {
    
    //Auto generate a unique ID for the entity
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    //String field to store in the database 
    private String text;
    
    //Setter and Getters Methods
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }

}
