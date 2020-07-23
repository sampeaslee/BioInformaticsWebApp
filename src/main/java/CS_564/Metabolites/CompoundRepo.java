package CS_564.Metabolites;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CompoundRepo extends JpaRepository<Compound, Integer> {
	
	 @Query(value = "select biggmetaboliteID from compounds;", nativeQuery = true)
	    public List<String> getListOfCompound();
	 
	 @Query(value = "DELETE FROM compounds WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void deleteACompound(@Param("compound_ID") String compound_ID);
	 
	 @Query(value = "select biggmetaboliteID from compounds WHERE biggmetaboliteID = :compound_ID", nativeQuery = true)
	 	public String getCompoundID(@Param("compound_ID") String compound_ID);
	 
	 @Query(value = "SELECT * FROM compounds WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public Compound getACompound(@Param("compound_ID") String compound_ID);
	 	
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET name = :name_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundName(@Param("compound_ID") String compound_ID, @Param("name_ID") String name_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET formula = :formula_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundFormula(@Param("compound_ID") String compound_ID, @Param("formula_ID") String formula_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET charge = :charge_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundCharge(@Param("compound_ID") String compound_ID, @Param("charge_ID") String charge_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET inchi_key = :inchi_key_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundInchikey(@Param("compound_ID") String compound_ID, @Param("inchi_key_ID") String inchi_key_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET kegg_compound = :kegg_compound_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundkeggCompoundID(@Param("compound_ID") String compound_ID, @Param("kegg_compound_ID") String kegg_compound_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET metanetx_chemical = :metanetx_chemical_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundMetanetxChemical(@Param("compound_ID") String compound_ID, @Param("metanetx_chemical_ID") String metanetx_chemical_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET smiles = :smiles_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundSmiles(@Param("compound_ID") String compound_ID, @Param("smiles_ID") String smiles_ID);
	 
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value = "UPDATE compounds SET prime = :prime_ID WHERE biggmetaboliteID = :compound_ID",  nativeQuery = true)
	    public void updateCompoundprime(@Param("compound_ID") String compound_ID, @Param("prime_ID") String prime_ID);


	@Query(value = "SELECT * FROM compounds c WHERE c.biggmetaboliteid = (SELECT bigg_compoundid FROM  metabolites m WHERE m. metaboliteid = :metaboliteid)",  nativeQuery = true)
	public Compound findByMetaboliteID(@Param("metaboliteid") String metabolite_ID);

}