package CS_564.Metabolites;


import java.util.List;
import javax.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MetaboliteRepo extends JpaRepository<Metabolite, Integer> {
	@Query(value = "select * from metabolites;", nativeQuery = true)
    public List<Metabolite> getListOfMetabolites();
	
	@Query(value = "select biggmetaboliteID from compounds;", nativeQuery = true)
    public List<String> getListOfCompounds();
 
	@Query(value = "select metaboliteID from metabolites WHERE metaboliteID = :metabolite_ID", nativeQuery = true)
 	public String getMetabolitesID(@Param("metabolite_ID") String metabolite_ID);
 
	@Query(value = "SELECT * FROM metabolites WHERE metaboliteID = :metabolite_ID",  nativeQuery = true)
    public Metabolite getAMetabolite(@Param("metabolite_ID") String metabolite_ID);
 	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE metabolites SET compartment = :compartment_ID WHERE metaboliteID = :metabolite_ID",  nativeQuery = true)
    public void updateCompartment(@Param("metabolite_ID") String metabolite_ID, @Param("compartment_ID") String compartment_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE metabolites SET bigg_compoundID = :compoundID WHERE metaboliteID = :metabolite_ID",  nativeQuery = true)
    public void updateCompound(@Param("metabolite_ID") String metabolite_ID, @Param("compoundID") String compoundID);
}