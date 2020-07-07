package CS_564.Metabolites;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactionRepo extends JpaRepository<Reaction, Integer> {

    @Query(value = " select * from reactions limit 1000 ;", nativeQuery = true)
    public List<Reaction> getReactions();

    @Query(value = "SELECT * FROM reactions r WHERE r.reactionid = :reactionid",  nativeQuery = true)
    public Reaction findByReactionID(@Param("reactionid") String reactionid);

    @Query(value = "SELECT * FROM reactions r WHERE r.reactionid LIKE :reactionid%",  nativeQuery = true)
    public List<Reaction> autoSearch(@Param("reactionid") String reactionid);

}


