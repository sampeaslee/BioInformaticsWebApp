package CS_564.Metabolites;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoichiometryRepo extends JpaRepository<Stoichiometry, Integer> {
    
    
    @Query(value = " select * from stoichometry limit 1000 ;", nativeQuery = true)
    public ArrayList<Stoichiometry> getStoichiometry();

    @Query(value = "SELECT * FROM stoichometry s WHERE s.startMetabolites LIKE %:Metaboliteid% or  s.endMetabolites LIKE %:Metaboliteid%",  nativeQuery = true)
    public ArrayList<Stoichiometry> autoSearch(@Param("Metaboliteid") String Metaboliteid);
    @Query(value = " select * from stoichometry ;", nativeQuery = true)
    public ArrayList<Stoichiometry> queryExample();

    @Query(value = " select * from stoichiometry  where reactionid = :id ;", nativeQuery = true)
    public Stoichiometry queryExampleTwo(@Param("id") String id);
}

