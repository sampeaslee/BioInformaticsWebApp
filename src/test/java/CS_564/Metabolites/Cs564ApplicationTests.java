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
    HasRepo has_repo;
    
    @Autowired
    ReactionRepo react_repo;
    
    @Autowired
    StoichiometryRepo sto_repo; 
    
    @Autowired 
    MetaboliteRepo meta_repo;
    
    
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
    void geneLikeSearch() {
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
    }	
    
    @Test 
    void meta_cmp_has_rect_query(){
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

       
       HashMap<String, String> model_reaction = new HashMap<String, String>();
       
       for(Object[] obj: sam) {
           model_reaction.put(" " + obj[0], " " + obj[1]);
       }
       System.out.println("Has map sizeL" + model_reaction.size());
       
       if(!test[1].equals("DAGAH_MYRS_ALNA_c") || !test[0].equals("iLB1027_lipid")) {
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
    
    
    @Test
    void joinCompoundMetabolitesMetaID() {
        
        String meta = "sam";
        
        javax.persistence.Query queryCompound = em.createQuery(""
            + "Select distinct  c, m FROM Metabolite m, Compound c where c.biggmetaboliteID = m.bigg_compoundID "
            + "AND m.metaboliteID LIKE :search", Object[].class);
        queryCompound.setParameter("search", meta + "%");
        
        @SuppressWarnings("unchecked")
        ArrayList<Object[]> results = (ArrayList<Object[]>) queryCompound.getResultList();

        Compound c = (Compound) results.get(0)[0];
        Metabolite m = (Metabolite) results.get(0)[1];

        if(!m.bigg_compoundID.equals("samprot") || !m.metaboliteID.equals("samprot_c") 
            || !c.name.equals("S-Aminomethyldihydrolipoylprotein")) {
            fail();
        }
         
        
        System.out.println("All metabolite and compound information found is correct. "
            + "Test passed!");
               
    }
    @Test
    void joinCompoundMetabolitesLikeSearchCompoundName() {
        
        String meta = "sam";

        javax.persistence.Query queryCompound = em.createQuery(""
            + "Select distinct  c, m FROM Metabolite m, Compound c where c.biggmetaboliteID = m.bigg_compoundID "
            + "AND c.name LIKE :search", Object[].class);
        queryCompound.setParameter("search", "%"+ meta + "%");

        
        ArrayList<Object[]> results = (ArrayList<Object[]>) queryCompound.getResultList();

        Compound c = (Compound) results.get(0)[0];
        Metabolite m = (Metabolite) results.get(0)[1];
        
        if(!m.bigg_compoundID.equals("3_rhma13unaga")){
            fail();
        }
        if(!m.metaboliteID.equals("3_rhma13unaga_c")){ 
            fail();
        }
          if(!c.name.equals("3n Rhamnosyl alfa 1  3   N acetyl glucosamine undecaprenyl diphosphate")) {
            fail();
        }
        
        
        System.out.println("All metabolite and compound information found is correct. "
            + "Test passed!");
               
    }
    @Test
    void addGene() {
        
        Gene gene = new Gene("newGeneID","newName","newNcbigi","newRefseq_name","newSbo","newModel");
        
        gene_repo.save(gene);
        
        Gene new_gene = gene_repo.getAGene("newGeneID");
        System.out.println(new_gene.geneID);
        
        if(!new_gene.geneID.equals("newGeneID")|| !new_gene.model.equals("newModel") ||
            !new_gene.name.equals("newName")||!new_gene.ncbigi.equals("newNcbigi")
            ||!new_gene.sbo.equals("newSbo")|| !new_gene.refseq_name.equals("newRefseq_name")) {
            fail();
        }
        gene_repo.delete(gene);
            
        
        
    }
    
    @Test
    void deleteGene() {
        
        Gene gene = new Gene("sam","sam","sam","sam","sam","sam");
        
        gene_repo.save(gene);
        
        Gene new_gene = gene_repo.getAGene("sam");
        
        if(!new_gene.geneID.equals("sam")|| !new_gene.model.equals("sam") ||
            !new_gene.refseq_name.equals("sam")||!new_gene.ncbigi.equals("sam")
            ||!new_gene.sbo.equals("sam")|| !new_gene.refseq_name.equals("sam")) {
            fail();
        }
        gene_repo.delete(gene);
        
        
        Gene after_delete  = gene_repo.getAGene("sam");
        
        if(after_delete != null) {
            fail();
        }
            
        
        
    }
    
    @Test
    void updateGene() {
        
        Gene gene = new Gene("sam","sam","sam","sam","sam","sam");
        
        gene_repo.save(gene);
          
        gene_repo.updateGenesName("sam", "SALMO");
        
        gene_repo.updateGeneNcbigi("sam", "SALMO");
        
        gene_repo.updateGeneRefseqName("sam", "SALMO");
        
        gene_repo.updateGeneSbo("sam", "SALMO");
        
        gene_repo.updateGeneModel("sam", "SALMO");
        
        Gene new_gene = gene_repo.getAGene("sam");
        
        if(!new_gene.geneID.equals("sam")|| !new_gene.model.equals("SALMO") ||
            !new_gene.refseq_name.equals("SALMO")||!new_gene.ncbigi.equals("SALMO")
            ||!new_gene.sbo.equals("SALMO")|| !new_gene.refseq_name.equals("SALMO")) {
            fail();
        }
        
        gene_repo.delete(new_gene);
        
    }
    
    @Test
    void addMetabolites() {
        
        
        Metabolite meta = new Metabolite("sam","10fthf", "sam" );
        
        meta_repo.save(meta);
        
        Metabolite new_meta = meta_repo.getAMetabolite("sam");
        
        meta_repo.delete( new_meta);
        
        if(!new_meta.metaboliteID.equals("sam") || 
            !new_meta.bigg_compoundID.equals("10fthf") || !new_meta.compartment.equals("sam") ) {
            
            fail();
        
        }
        
    }
    
    
    @Test
    void deleteMetabolite() {
        Metabolite meta = new Metabolite("sam","10fthf", "sam" );        
        
        meta_repo.save(meta);
        
        meta_repo.delete( meta);
        
        Metabolite new_meta = meta_repo.getAMetabolite("sam");
        
        if(new_meta != null) {
            fail();
        }
      
    }
    
    @Test
    void updateMetabolite() {
        
        Metabolite meta = new Metabolite("sam","10fthf", "sam" );        
        
        meta_repo.save(meta);
        
        meta_repo.updateCompartment("sam", "SALMO");
        
        meta_repo.updateCompound("sam","10fthf5glu");
        
        Metabolite new_meta = meta_repo.getAMetabolite("sam");
        meta_repo.delete( meta);
        
        
        if(!new_meta.metaboliteID.equals("sam") || 
            !new_meta.bigg_compoundID.equals("10fthf5glu") || !new_meta.compartment.equals("SALMO") ) {
            
            fail();
        
        }

        
    }
    
    @Test
    void updateMetaForgeinKeyDoesNotExitsInCompounds() {
        
        Metabolite meta = new Metabolite("sam","SALMOSAM", "sam" );          
       try{
           meta_repo.save(meta);
           fail();
       }catch(Exception e) {
           System.out.println("Forgien Key Constraint worked");
       }
       
    
    
    }
    
    
    @Test
    void getMetaID() {
        
        String metaid = meta_repo.getMetabolitesID("10fthf_c");
  
        if(!metaid.equals("10fthf_c")){
            fail();
        }
    
    }
    
    
    @Test
    void getReactionID() {
        
        String reactid = react_repo.getReactionID("10FTHF5GLUtl");
  
        if(!reactid.equals("10FTHF5GLUtl")){
            fail();
        }
    
    }
    

    @Test
    void getReaction() {

        Reaction react = react_repo.getAReaction("10FTHF5GLUtl");

        if (!react.ReactionID.equals("10FTHF5GLUtl")) {
            System.out.println(react.ReactionID);
            fail();
        }
        if (react.HigherBound.equals("100000.0 ")) {
            System.out.println(react.HigherBound);
            fail();
        }
        if (!react.LowerBound.equals("0.0")) {
            System.out.println(react.LowerBound);
            fail();
        }

        if (!react.Subsystem.equals("Transport, Lysosomal")) {
            System.out.println(react.Subsystem);
            fail();

        }
        if (!react.gene_reaction_rule.equals("")) {

            System.out.println(react.gene_reaction_rule);
            fail();
        }
        if (!react.name.equals("5-glutamyl-10FTHF transport, lysosomal")) {
            System.out.println(react.name);
            fail();
        }
        if (!react.signature.equals("NA")) {
            System.out.println(react.signature);
            fail();
        }
    }
     
    @Test
    void addReaction() {
        Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
            "SALMO","SALMO","SALMO");
        react_repo.save(new_react);
        Reaction react = react_repo.getAReaction("SALMO");
        react_repo.delete(new_react);
        if (!react.ReactionID.equals("SALMO")) {
            System.out.println(react.ReactionID);
            fail();
        }
        if (react.HigherBound.equals("SALMO ")) {
            System.out.println(react.HigherBound);
            fail();
        }
        if (!react.LowerBound.equals("SALMO")) {
            System.out.println(react.LowerBound);
            fail();
        }

        if (!react.Subsystem.equals("SALMO")) {
            System.out.println(react.Subsystem);
            fail();

        }
        if (!react.gene_reaction_rule.equals("SALMO")) {

            System.out.println(react.gene_reaction_rule);
            fail();
        }
        if (!react.name.equals("SALMO")) {
            System.out.println(react.name);
            fail();
        }
        if (!react.signature.equals("SALMO")) {
            System.out.println(react.signature);
            fail();
        }
    }
     
    @Test
    void deleteReaction() {
        Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
            "SALMO","SALMO","SALMO");
        react_repo.save(new_react);
        react_repo.delete(new_react);
        Reaction react = react_repo.getAReaction("SALMO");
        if (react != null) {
            fail();
        }

    }
     
    @Test
    void updateReaction() {
        Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
            "SALMO","SALMO","SALMO");
        react_repo.save(new_react);
        react_repo.updateReactionHigherBound("SALMO", "SAM");
        react_repo.updateReactionName("SALMO", "SAM");
        react_repo.updateReactionRule("SALMO", "SAM");
        react_repo.updateReactionSignature("SALMO", "SAM");
        react_repo.updateReactionsLowerBound("SALMO", "SAM");
        react_repo.updateReactionSubsystem("SALMO", "SAM");
        Reaction react = react_repo.getAReaction("SALMO");
        react_repo.delete(new_react);
        if (!react.ReactionID.equals("SALMO")) {
            System.out.println(react.ReactionID);
            fail();
        }
        if (react.HigherBound.equals("SAM ")) {
            System.out.println(react.HigherBound);
            fail();
        }
        if (!react.LowerBound.equals("SAM")) {
            System.out.println(react.LowerBound);
            fail();
        }

        if (!react.Subsystem.equals("SAM")) {
            System.out.println(react.Subsystem);
            fail();

        }
        if (!react.gene_reaction_rule.equals("SAM")) {

            System.out.println(react.gene_reaction_rule);
            fail();
        }
        if (!react.name.equals("SAM")) {
            System.out.println(react.name);
            fail();
        }
        if (!react.signature.equals("SAM")) {
            System.out.println(react.signature);
            fail();
        }
    }
     
    
   @Test
   void reactionLIKESearch() {
      ArrayList<Reaction> reacts = (ArrayList<Reaction> ) react_repo.autoSearch("sam");
      ArrayList<String> expected = new ArrayList<String>();
      expected.add("SAMHISTAe");
      expected.add("SAMU3");
      expected.add("SAM24MT");
      expected.add("SAM_MT");
      expected.add("SAMHISTA");
      
      for(Reaction r: reacts) {
          if(!expected.contains(r.ReactionID)) {
              fail();
          }
      }
   }
    

   @Test
   void getStoichiometryByID() {
       Stoichiometry sto = sto_repo.findByReactionID("10FTHF5GLUtl");

       if (!sto.reactionID.equals("10FTHF5GLUtl")) {
           fail();
       }

       if (sto.endMetabolites.equals("#10fthf5glu_l:1.0 ")) {
           fail();
       }
       if (sto.startMetabolites.equals("#10fthf5glu_c:-1.0 ")) {
           fail();
       }
   }

   @Test 
   void insertNewStoichimetry() {
       Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
           "SALMO","SALMO","SALMO");
       react_repo.save(new_react);
       Stoichiometry new_sto = new Stoichiometry("SALMO","#thf5glu_l:2.0 ", "#10fthf5glu_c:-1.0 ");
       sto_repo.save(new_sto);
       Stoichiometry sto = sto_repo.queryExampleTwo("SALMO");
      
       sto_repo.delete(sto);
      
       react_repo.delete(new_react);
      
       if (!sto.reactionID.equals("SALMO")) {
           fail();
       }

       if (sto.endMetabolites.equals("#thf5glu_l:2.0 ")) {
           fail();
       }
       if (sto.startMetabolites.equals("#10fthf5glu_c:-1.0 ")) {
           fail();
       }
       
   }
   
   
   @Test
   void insertStoichiometryForgeinKeyDoesNotExitsInReactions() {
       
       Stoichiometry new_sto = new Stoichiometry
           ("SALMO","#thf5glu_l:2.0 ", "#10fthf5glu_c:-1.0 ");     
      try{
          sto_repo.save(new_sto);
          fail();
      }catch(Exception e) {
          System.out.println("Forgien Key Constraint worked");
      }
      
   
   
   }
   

   @Test 
   void deleteReactionCheckStochiomeryDeletedAsWell() {
       Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
           "SALMO","SALMO","SALMO");
       react_repo.save(new_react);
       Stoichiometry new_sto = new Stoichiometry("SALMO","#thf5glu_l:2.0 ", "#10fthf5glu_c:-1.0 ");
       sto_repo.save(new_sto);
       react_repo.delete(new_react);
       Stoichiometry sto = sto_repo.queryExampleTwo("SALMO");
      if(sto != null) {
          fail();
      }

       
   }
   
   

   @Test 
   void deleteReactionCheckHasDeletedAsWell() {
       Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
           "SALMO","SALMO","SALMO");
       react_repo.save(new_react);
       ReactionModel rm = new ReactionModel("SALMO","SALMO");
       Has h = new Has(rm);
       has_repo.save(h);
       react_repo.delete(new_react);
       ArrayList<Has> results = has_repo.queryExample();

       
      if(results.size() != 0) {
          fail();
      }

       
   }
   
   
   @Test 
   void updateStoichimetry() {
       Reaction new_react = new Reaction("SALMO","SALMO","SALMO","SALMO",
           "SALMO","SALMO","SALMO");
       react_repo.save(new_react);
       Stoichiometry new_sto = new Stoichiometry("SALMO","#thf5glu_l:2.0 ", "#10fthf5glu_c:-1.0 ");
       sto_repo.save(new_sto);
       sto_repo.updateStartMetabolites("SALMO", "UPDATED_START");
       sto_repo.updateEndMetabolites("SALMO", "UPDATED_END");
       
       Stoichiometry sto = sto_repo.queryExampleTwo("SALMO");
     
       react_repo.delete(new_react);
       
       if (!sto.reactionID.equals("SALMO")) {
           fail();
       }

       if (sto.endMetabolites.equals("UPDATED_START")) {
           fail();
       }
       if (sto.startMetabolites.equals("UPDATED_END")) {
           fail();
       }
       
       
   }
   
   @Test 
   void updateStoichimetryEntryNotInTable() {
      
       sto_repo.updateStartMetabolites("SALMO", "UPDATED_START");
       sto_repo.updateEndMetabolites("SALMO", "UPDATED_END");
       
       Stoichiometry sto = sto_repo.queryExampleTwo("SALMO");
       if(sto != null) {
           fail();
       }
       
       
   }
}
