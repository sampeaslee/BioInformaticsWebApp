package CS_564.Metabolites;



import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HasRepo extends JpaRepository<Has, ReactionModel> {
    
    @Query(value = " select * from has where reactionid = 'SALMO' ;", nativeQuery = true)
    public ArrayList<Has> queryExample();

}