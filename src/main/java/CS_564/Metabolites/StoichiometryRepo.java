package CS_564.Metabolites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoichiometryRepo extends JpaRepository<Stoichiometry, Integer> {
    
    
    @Query(value = " select * from stoichiometry limit 1000 ;", nativeQuery = true)
    public ArrayList<Stoichiometry> getStoichiometry();

    @Query(value = "SELECT * FROM stoichiometry s WHERE s.reactionid = :reactionid",  nativeQuery = true)
    public Stoichiometry findByReactionID(@Param("reactionid") String reactionid);

    @Query(value = "SELECT * FROM stoichiometry s WHERE s.startMetabolites LIKE %:Metaboliteid% or  s.endMetabolites LIKE %:Metaboliteid%",  nativeQuery = true)
    public ArrayList<Stoichiometry> autoSearch(@Param("Metaboliteid") String Metaboliteid);
}

