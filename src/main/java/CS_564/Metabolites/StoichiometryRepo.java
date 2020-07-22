package CS_564.Metabolites;
import java.util.List;

import java.util.ArrayList;

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
    @Query(value = " select * from stoichometry ;", nativeQuery = true)
    public ArrayList<Stoichiometry> queryExample();

    @Query(value = " select * from stoichiometry  where reactionid = :id ;", nativeQuery = true)
    public Stoichiometry queryExampleTwo(@Param("id") String id);
}

