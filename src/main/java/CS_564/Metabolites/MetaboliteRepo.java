package CS_564.Metabolites;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MetaboliteRepo extends JpaRepository<Metabolite, Integer> {
    
    /*@Query(value = "SELECT * FROM compounds c, metabolites m WHERE m.bigg_compoundid = c.biggmetaboliteid limit 1",  nativeQuery = true)
    public List<Metabolite> getAll();

    @Query(value = "SELECT * FROM compounds c, metabolites m WHERE m.bigg_compoundid = c.biggmetaboliteid and m.metaboliteid LIKE :metaid%",  nativeQuery = true)
    public List<Metabolite> getMeta(@Param("metaid") String metaid);
    */
}