package CS_564.Metabolites;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SamController {
    @Autowired
    GeneRepo GeneRepo;
    
    @Autowired
    MetaboliteRepo MetaRepo;

    @Autowired
    CommentRepo CommentRepo;



    
    @PersistenceContext protected EntityManager em;

    @GetMapping("/home")
    public String se(String name, Model model) {
        //home page of our app
        return "home";
        
    }
    /**
     * Function that runs when you go to gene search page
     * @param name
     * @param model
     * @return
     */
    
    @GetMapping("/genes")
    public String Gene(@RequestParam(value = "name", defaultValue = "") String name, Model model) {


        if (name.equals("")) {
            model.addAttribute("test", "No Search Specifed: Showing All Genes");
            ArrayList<Gene> s = (ArrayList<Gene>) GeneRepo.getGenes();
            model.addAttribute("genes", s);
        } else {
            ArrayList<Gene> s = (ArrayList<Gene>) GeneRepo.geneProcedure(name);
            System.out.println(s.size());
            String search;
            if (s.size() == 0) {
                search = "No Gene IDs Start With : " + name;
            } else {
                search = "Current Search: " + name;

                model.addAttribute("genes", s);
            }
            model.addAttribute("test", search);
        }

        return "genes";

    }
    /**
     * Function that runs when you go to metabolites search page
     * @param name
     * @param model
     * @return
     */

    @GetMapping("/metabolites")
    public String Metabolites(@RequestParam(value = "name", defaultValue = "") String name,
        Model model) {

        System.out.println("-- executing query --");


        /*
         * This code uses javax.persisstence framework to make an sql query that that then maps to
         * an arraylist of ob
         */
        javax.persistence.Query queryCompound = em.createQuery(""
            + "Select distinct  c, m FROM Metabolite m, Compound c where c.biggmetaboliteID = m.bigg_compoundID "
            + "AND m.metaboliteID LIKE :search", Object[].class);
        queryCompound.setParameter("search", name + "%");
        //queryCompound.setMaxResults(1000);
        ArrayList<Object[]> results = (ArrayList<Object[]>) queryCompound.getResultList();



        if (results.size() > 0) {
            javax.persistence.Query queryCompound2 = em.createQuery(""
                + "Select distinct  c, m FROM Metabolite m, Compound c where c.biggmetaboliteID = m.bigg_compoundID "
                + "AND c.name LIKE :search", Object[].class);
        queryCompound2.setParameter("search", "%"+ name + "%");
        results.addAll(queryCompound2.getResultList());
            Compound c = (Compound) results.get(0)[0];
            ArrayList<Metabolite> meta = new ArrayList<Metabolite>();
            ArrayList<Compound> compound = new ArrayList<Compound>();

            for (Object[] obj : results) {
                compound.add((Compound) obj[0]);
                meta.add((Metabolite) obj[1]);

            }
            if(name.equals( "")) {
                model.addAttribute("metas", meta);
                model.addAttribute("compounds", compound);
                String search = "No Search Specified: Showing All Metabolites";
                model.addAttribute("current", search);
                
            }else {
                model.addAttribute("metas", meta);
                model.addAttribute("compounds", compound);
                String search = "Current Search: " + name;
                model.addAttribute("current", search);
                
            }

        } else {
            String search = "No Metabolite IDs Start With: " + name;
            model.addAttribute("current", search);
        }


        return "metabolites";

    }

    
 ////////////////////////////////////////////////////////////////////////////////   
    /*
     * Function for displaying detailed data for a metabolite 
     */

    @GetMapping("/info/metabolites")
    public String sendToFrontend(String name, Model model) {
        return "/info/metabolites";
    }

    @GetMapping("info/metabolites/{id}")
    public String sendToFrontend(String name, Model model, @PathVariable String id) {
       /*
        * This code uses javax.persisstence framework to make an sql query that
        * that then maps to an arraylist of ob
        */
        javax.persistence.Query allData = em.createNativeQuery(" select hs.model, "
            + "hs.reaction, m.metaboliteid, m.bigg_compoundid, m.compartment, c.charge, "
            + "c.inchi_key, c.formula, c.name, c.metanetx_chemical  from compounds c, metabolites m, "
            + "(select h.reactionid reaction, h.model_name model from has h, stoichiometry "
            + "s where s.reactionid = h.reactionid and s.start_metabolites like :likes) "
            + "hs where c.biggmetaboliteid = m.bigg_compoundid and m.metaboliteid = :equals ;"); 
        allData.setParameter("likes", "%" + id + "%");
        allData.setParameter("equals", id);
        ArrayList<Object[]> query_results = (ArrayList<Object[]>) allData.getResultList();
        HashMap<String, String> meta = new HashMap<String, String>();
        if (query_results.size() != 0) {
            Object[] data = query_results.get(0);
            System.out.println("##############################################" + query_results.size());
            HashMap<String, String> model_reaction = new HashMap<String, String>();
     
            meta.put("meta", "" + data[2]);
            meta.put("big", "" + data[3]);
            meta.put("compartment", "" + data[4]);
            meta.put("charge", "" + data[5]);
            meta.put("inchi", "" + data[6]);
            meta.put("formula", "" + data[7]);
            meta.put("compound_name", "" + data[8]);
            meta.put("metanetx", "" + data[9]);
            for(Object[] obj: query_results) {
                model_reaction.put( " " + obj[1],"" + obj[0]);
            }
  
            for (Map.Entry<String, String> entry : meta.entrySet()) {
                
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + " " + value);
                if(value.equals("null")) {
                    meta.put(key, "NA");
                }
                
            }
            model.addAllAttributes(meta);
            model.addAttribute("model_reaction", model_reaction);
            model.addAttribute("reaction_text", " Reactions the metabolite can "
                + "take part in and the model the reaction is associated with.");

        }else {
            javax.persistence.Query queryCompound = em.createQuery(""
                + "Select distinct  c, m FROM Metabolite m, Compound c where c.biggmetaboliteID = m.bigg_compoundID "
                + "AND m.metaboliteID = :search", Object[].class);
            queryCompound.setParameter("search", id  );
            ArrayList<Object[]> results = (ArrayList<Object[]>) queryCompound.getResultList();
            Object[] data = results.get(0);
            Compound c = (Compound) data[0];
            Metabolite m = (Metabolite) data[1];
            
            meta.put("meta", "" + m.metaboliteID);
            meta.put("big", "" + m.bigg_compoundID);
            meta.put("compartment", "" + m.compartment);
            meta.put("charge", "" + c.charge);
            meta.put("inchi", "" + c.inchi_key);
            meta.put("formula", "" + c.formula);
            meta.put("compound_name", "" + c.name);
            meta.put("metanetx", "" + c.metanetx_chemical);

            for (Map.Entry<String, String> entry : meta.entrySet()) {
    
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + " " + value);
                if(value.equals("null")) {
                    meta.put(key, "NA");
                }
                
            }
            model.addAllAttributes(meta);
            model.addAttribute("reaction_text", "Metabolite is not part of any reactions.");




        }
        List<Comment> comment2 = CommentRepo.findAllComment(id);

        List<CommentToString> commentToString = new ArrayList();
        for (Comment c2 : comment2) {
            CommentToString temp = new CommentToString(c2.CommentComb.name, c2.CommentComb.email,c2.content);
            commentToString.add(temp);
        }
        model.addAttribute("message", commentToString);


        return "info/metabolites";
    }



}
