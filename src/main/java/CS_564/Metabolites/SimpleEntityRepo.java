package CS_564.Metabolites;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

//Spring will automatically implements this repository interface 
public interface SimpleEntityRepo extends JpaRepository<SimpleEntity, Integer>{
    /**
     * You can declare your own methods here to interact with the database and 
     * also use the methods inherited by JpaRepositoy. For example in the 
     * BackendController files i use the save method to save a SimpleEnity 
     * object to the database. See the getTextFromFrontend method in that file 
     * for the details.
     */
    
    /*
     * Example of a method that does an SQL query and returns a list of all the
     * entities in the test_table. Look at getTextFromFrontend method in the
     * BackendContoller.java to see the how you can use the function.
     * 
     * You can define more complex queries as well. 
     */
    
    @Query(value = " select * from test_table ;", nativeQuery = true)
    public List<SimpleEntity> queryExample();
}
