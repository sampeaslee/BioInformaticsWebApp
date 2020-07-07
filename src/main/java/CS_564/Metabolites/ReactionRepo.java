package CS_564.Metabolites;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReactionRepo extends JpaRepository<Reaction, Integer> {

    @Query(value = " select * from reactions where reactionid = :id ;", nativeQuery = true)
    public Reaction queryExample(@Param("id") String id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " delete from reactions where reactionid = :id ;", nativeQuery = true)
    public void delete(@Param("id") String id);

    @Query(value = " select * from reactions limit 1000 ;", nativeQuery = true)
    public List<Reaction> getReactions();

    @Query(value = "SELECT * FROM reactions r WHERE r.reactionid = :reactionid",  nativeQuery = true)
    public Reaction findByReactionID(@Param("reactionid") String reactionid);

    @Query(value = "SELECT * FROM reactions r WHERE r.reactionid LIKE :reactionid%",  nativeQuery = true)
    public List<Reaction> autoSearch(@Param("reactionid") String reactionid);
}


