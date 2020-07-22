package CS_564.Metabolites;

import static org.junit.Assert.fail;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Cs564ApplicationTests {
    @Autowired
    GeneRepo gene_repo;
    
    @PersistenceContext protected EntityManager em;
	
    @Autowired
    HasRepo hasrepo;
    
    @Autowired
    ReactionRepo reactionrepo;
    
    @Autowired
    StoichiometryRepo storepo; 
    
    @Test
	void findGeneByID() {
	    System.out.println("Sam");
	    Gene gene = gene_repo.findByGeneID("10FTHF5GLUtm");

	      
	      
	    if(!gene.geneID.equals("10FTHF5GLUtm")) {
	        fail();
	    }else {
	        System.out.println("Correct Gene Found!!! Test Passed");
	    }
        System.out.println("#############################################");
	}
	
    @Test
    void test_gene_procedure() {
        ArrayList<Gene> procedure = (ArrayList<Gene>)gene_repo.geneProcedure("dad");
        
        ArrayList<String> expected_geneids = new ArrayList<String>();
        expected_geneids.add("DADA");
        expected_geneids.add("DADAe");
        expected_geneids.add("DADK");
        expected_geneids.add("DADK_copy1");
        expected_geneids.add("DADK3");
        expected_geneids.add("DADNK");
        expected_geneids.add("DADNKn");
        expected_geneids.add("DADNt2pp");
        expected_geneids.add("DADNK_1");
        expected_geneids.add("DADNKn");
        expected_geneids.add("DADNt2");
        expected_geneids.add("DADNt4");
        expected_geneids.add("DADNtex");
        expected_geneids.add("DADPtn");

        for(int i = 0; i < procedure.size(); i ++) {
            if(!expected_geneids.contains(procedure.get(i).geneID)) {
                System.out.println("Procedure: " + procedure.get(i).geneID);
                fail();
            }else {
                System.out.println("Procedure: " + procedure.get(i).geneID);
            }
        }
        System.out.println("Correct Genes Found with LIKE SQL Statment!!! Test Passed");
        System.out.println("#############################################");
        
        System.out.println();
    }	
    
    



}
