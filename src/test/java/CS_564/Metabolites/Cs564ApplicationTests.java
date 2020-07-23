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
    
    @Test 
    void sma(){
        String metaId = "12dgr140183n3_c";
        
        javax.persistence.Query allData = em.createNativeQuery(" select hs.model, "
            + "hs.reaction, m.metaboliteid, m.bigg_compoundid, m.compartment, c.charge, "
            + "c.inchi_key, c.formula, c.name, c.metanetx_chemical  from compounds c, metabolites m, "
            + "(select h.reactionid reaction, h.model_name model from has h, stoichiometry "
            + "s where s.reactionid = h.reactionid and s.start_metabolites like :likes) "
            + "hs where c.biggmetaboliteid = m.bigg_compoundid and m.metaboliteid = :equals ;"); 
        allData.setParameter("likes", "%" + metaId + "%");
        allData.setParameter("equals", metaId);
        ArrayList<Object[]> sam = (ArrayList<Object[]>) allData.getResultList();
       System.out.println("sam" + sam.size());
       Object[] test =  sam.get(0);
       String metaid = "" +  test[2];
       String big = "" + test[3];
       String comparment = "" + test[4];
       String charge = "" + test[5];
       String inchi = "" + test[6];
       String formula = "" + test[7];
       String compound_name = "" + test[8];
       String metanetx = "" + test[9];
       for(int i = 0; i < test.length; i ++) {
           System.out.println(test[i]);
       }
       
       HashMap<String, String> model_reaction = new HashMap<String, String>();
       
       for(Object[] obj: sam) {
           model_reaction.put(" " + obj[0], " " + obj[1]);
       }
       System.out.println("Has map sizeL" + model_reaction.size());
       
       if(!test[1].equals("ETHAPT_MYRS_ALNA_c") || !test[0].equals("iLB1027_lipid")) {
           System.out.println("Test[0] " + test[0]);
           System.out.println("Test[1] " + test[1]);
           fail();
       }
       
       if(!metaid.equals("12dgr140183n3_c") || !big.equals("12dgr140183n3") || !charge.
           equals("0") || !inchi.equals("null") || !formula.equals("C35H62O5") 
           || !compound_name.equals("1,2-Diacyl-sn-glycerol(14:0/18:3(9Z,12Z,15Z))")
           || !metanetx.equals("null")) {
           fail();
           
       }else {
           System.out.println("All metabolite information found is correct. "
               + "Test passed!");
       }
       
           
       
       
       
       
    }
    



}
