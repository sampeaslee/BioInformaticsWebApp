package CS_564.Metabolites;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ModelRepo extends JpaRepository<Model, Integer> {

	@Query(value = "select * from models;", nativeQuery = true)
	public List<String> getListOfModel();
	

    
    @Modifying(clearAutomatically = true)
    @Transactional
    //@Query(value = "DELETE FROM models WHERE name = :name_ID",  nativeQuery = true)
    @Query(value = "CALL deleteAModel(:name_ID);", nativeQuery = true)
    public void deleteAModel(@Param("name_ID") String name_ID);
    
 
    @Query(value = "select name from models WHERE name = :name_ID", nativeQuery = true)
    public String getModelID(@Param("name_ID") String name_ID);
 
    
    @Modifying(clearAutomatically = true)
    @Transactional
    //@Query(value = "INSERT INTO models(name) VALUES(:name_ID)",  nativeQuery = true)
    @Query(value = "CALL insertModel(:name_ID);", nativeQuery = true)
    public void insertModel(@Param("name_ID") String name_ID);
			 

}