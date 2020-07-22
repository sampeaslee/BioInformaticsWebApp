package CS_564.Metabolites;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoichiometryRepo extends JpaRepository<Stoichiometry, Integer> {
    
    
    @Query(value = " select * from stoichometry ;", nativeQuery = true)
    public ArrayList<Stoichiometry> queryExample();
    
    @Query(value = " select * from stoichiometry  where reactionid = :id ;", nativeQuery = true)
    public Stoichiometry queryExampleTwo(@Param("id") String id);
}

