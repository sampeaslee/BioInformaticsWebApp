package CS_564.Metabolites;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
<<<<<<< Updated upstream
=======
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> Stashed changes

public interface StoichiometryRepo extends JpaRepository<Stoichiometry, Integer> {
    
    
    @Query(value = " select * from stoichometry ;", nativeQuery = true)
    public ArrayList<Stoichiometry> queryExample();
<<<<<<< Updated upstream
    
=======

    @Query(value = " select * from stoichiometry  where reactionid = :id ;", nativeQuery = true)
    public Stoichiometry queryExampleTwo(@Param("id") String id);
    
    @Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE stoichiometry SET start_metabolites = :start_Metabolites WHERE reactionID = :reaction_ID",  nativeQuery = true)
    public void updateStartMetabolites(@Param("reaction_ID") String reaction_ID, @Param("start_Metabolites") String start_Metabolites);
    
    @Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE stoichiometry SET end_metabolites = :end_Metabolites WHERE reactionID = :reaction_ID",  nativeQuery = true)
    public void updateEndMetabolites(@Param("reaction_ID") String reaction_ID, @Param("end_Metabolites") String end_Metabolites);
>>>>>>> Stashed changes
}

