package CS_564.Metabolites;


import java.util.ArrayList;
import javax.transaction.Transactional;
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
}


