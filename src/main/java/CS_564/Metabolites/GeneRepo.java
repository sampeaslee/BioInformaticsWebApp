package CS_564.Metabolites;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GeneRepo extends JpaRepository<Gene, Integer> {

	@Query(value = "select * from genes;", nativeQuery = true)
    public List<Gene> getListOfGenes();
 
	@Query(value = "select geneID from genes WHERE geneID = :gene_ID", nativeQuery = true)
 	public String getGeneID(@Param("gene_ID") String gene_ID);
 
	@Query(value = "SELECT * FROM genes WHERE geneID = :gene_ID",  nativeQuery = true)
    public Gene getAGene(@Param("gene_ID") String gene_ID);

 	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE genes SET name = :name_ID WHERE geneID = :gene_ID",  nativeQuery = true)
    public void updateGenesName(@Param("gene_ID") String gene_ID, @Param("name_ID") String name_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE genes SET ncbigi = :ncbigi_ID WHERE geneID = :gene_ID",  nativeQuery = true)
    public void updateGeneNcbigi(@Param("gene_ID") String gene_ID, @Param("ncbigi_ID") String ncbigi_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE genes SET refseq_name = :refseq_name_ID WHERE geneID = :gene_ID",  nativeQuery = true)
    public void updateGeneRefseqName(@Param("gene_ID") String gene_ID, @Param("refseq_name_ID") String refseq_name_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE genes SET sbo = :sbo_ID WHERE geneID = :gene_ID",  nativeQuery = true)
    public void updateGeneSbo(@Param("gene_ID") String gene_ID, @Param("sbo_ID") String sbo_ID);
 
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE genes SET model = :model_ID WHERE geneID = :gene_ID",  nativeQuery = true)
    public void updateGeneModel(@Param("gene_ID") String gene_ID, @Param("model_ID") String model_ID);


	@Query(value = " select * from genes limit 1000 ;", nativeQuery = true)
	public List<Gene> getGenes();

	@Query(value = "SELECT * FROM genes g WHERE g.geneid = :geneid",  nativeQuery = true)
	public Gene findByGeneID(@Param("geneid") String geneid);

	@Query(value = "SELECT * FROM genes g WHERE g.geneid LIKE :geneid%",  nativeQuery = true)
	public List<Gene> autoSearch(@Param("geneid") String geneid);

	@Query(value = "Call searchgenes(:geneid)", nativeQuery = true)
	public List<Gene> geneProcedure(@Param("geneid") String geneid);

	

}


