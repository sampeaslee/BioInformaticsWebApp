package CS_564.Metabolites;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReactionRepo extends JpaRepository<Reaction, Integer> {
	@Query(value = "select * from reactions;", nativeQuery = true)
    public List<Reaction> getListOfReactions();
 
	@Query(value = "select ReactionID from reactions WHERE reactionid = :reaction_ID", nativeQuery = true)
 	public String getReactionID(@Param("reaction_ID") String reaction_ID);
 
	@Query(value = "SELECT * FROM reactions WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public Reaction getAReaction(@Param("reaction_ID") String reaction_ID);
 	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE reactions SET name = :name_ID WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public void updateReactionName(@Param("reaction_ID") String reaction_ID, @Param("name_ID") String name_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE reactions SET higher_bound = :higherbound_ID WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public void updateReactionHigherBound(@Param("reaction_ID") String reaction_ID, @Param("higherbound_ID") String higherbound_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE reactions SET lower_Bound = :lowerbound_ID WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public void updateReactionsLowerBound(@Param("reaction_ID") String reaction_ID, @Param("lowerbound_ID") String lowerbound_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE reactions SET subsystem = :subsystem_ID WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public void updateReactionSubsystem(@Param("reaction_ID") String reaction_ID, @Param("subsystem_ID") String subsystem_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE reactions SET signature = :signature_ID WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public void updateReactionSignature(@Param("reaction_ID") String reaction_ID, @Param("signature_ID") String signature_ID);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE reactions SET gene_reaction_rule = :rule_ID WHERE reactionid = :reaction_ID",  nativeQuery = true)
    public void updateReactionRule(@Param("reaction_ID") String reaction_ID, @Param("rule_ID") String rule_ID);

	@Query(value = " select * from reactions limit 1000 ;", nativeQuery = true)
	public List<Reaction> getReactions();

	@Query(value = "SELECT * FROM reactions r WHERE r.reactionid = :reactionid",  nativeQuery = true)
	public Reaction findByReactionID(@Param("reactionid") String reactionid);

	@Query(value = "SELECT * FROM reactions r WHERE r.reactionid LIKE :reactionid%",  nativeQuery = true)
	public List<Reaction> autoSearch(@Param("reactionid") String reactionid);


}


