package CS_564.Metabolites;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneRepo extends JpaRepository<Gene, Integer> {
    
    @Query(value = " select * from genes limit 1000;", nativeQuery = true)
    public List<Gene> getGenes();
    
    @Query(value = "SELECT * FROM genes g WHERE g.geneid = :geneid",  nativeQuery = true)
    public Gene findByGeneID(@Param("geneid") String geneid);
    
    @Query(value = "Call searchgenes(:geneid)", nativeQuery = true)
    public List<Gene> geneProcedure(@Param("geneid") String geneid);

    
}


