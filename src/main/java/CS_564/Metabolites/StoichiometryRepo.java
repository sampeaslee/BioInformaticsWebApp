package CS_564.Metabolites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoichiometryRepo extends JpaRepository<Stoichiometry, Integer> {
    
    
    @Query(value = " select * from stoichometry limit 1000 ;", nativeQuery = true)
    public ArrayList<Stoichiometry> getStoichiometry();

    @Query(value = "SELECT * FROM stoichometry s WHERE s.startMetabolites LIKE %:Metaboliteid% or  s.endMetabolites LIKE %:Metaboliteid%",  nativeQuery = true)
    public ArrayList<Stoichiometry> autoSearch(@Param("Metaboliteid") String Metaboliteid);
}

