package CS_564.Metabolites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StartMetaReactionRepo extends JpaRepository<StartMetaReaction, Integer> {


    @Query(value = "SELECT * FROM start_meta_reaction s WHERE s.metaboliteid = :metaboliteid",  nativeQuery = true)
    public List<String[]> findByMetaboliteId(@Param("metaboliteid") String metaboliteid);


    @Query(value = "SELECT DISTINCT e.metaboliteid FROM start_meta_reaction s INNER JOIN  end_meta_reaction e ON e.ReactionID = s.ReactionID  WHERE s.metaboliteid = :metaboliteid and s.ReactionID = :reactionID ORDER BY e.ReactionID",  nativeQuery = true)
    public List<String> findnextMetabolite(@Param("metaboliteid") String metaboliteid, @Param("reactionID") String reactionID);
}
