package CS_564.Metabolites;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//This class it used to interact directly with the frontend
@Controller
public class CommentController {

    @Autowired
    CommentRepo CommentRepo;

    @Autowired
    ReactionRepo ReactionRepo;

    @Autowired
    StoichiometryRepo StoichiometryRepo;

    @Autowired
    GeneRepo GeneRepo;

    @Autowired
    MetaboliteRepo MetaRepo;

    @PersistenceContext
    protected EntityManager em;



    @PostMapping("info/reactions/{id}")
    public String sentTOBack(String add_comment, Model model, @PathVariable String id) throws ParseException {
        System.out.println("start!");
        JSONParser parser = new JSONParser();
        JSONObject convert_to_json = (JSONObject) parser.parse(add_comment);


        String comment = ((String) convert_to_json.get("comment"));
        System.out.println("comment is " + comment);
        String commentid = id;
        System.out.println("id is " + id);
        String name = ((String) convert_to_json.get("name"));
        System.out.println("name is " + name);
        String email = ((String) convert_to_json.get("email"));
        System.out.println("email is " + email);

        CommentComb comments = new CommentComb(commentid, name, email);
        Comment addcomment = new Comment(comments, comment);
        CommentRepo.save(addcomment);
        Reaction reaction = ReactionRepo.findByReactionID(id);
        Stoichiometry stoichiometry = StoichiometryRepo.findByReactionID(id);
        if (reaction != null) {
            model.addAttribute("reaction", reaction);
            model.addAttribute("stoichiometry", stoichiometry);

            List<Comment> comment2 = CommentRepo.findAllComment(id);

            List<CommentToString> commentToString = new ArrayList();
            for (Comment c : comment2) {
                CommentToString temp = new CommentToString(c.CommentComb.name, c.CommentComb.email,c.content);
                commentToString.add(temp);
            }
            model.addAttribute("message", commentToString);
            System.out.println("SUCCESS");
            return "info/reactions";
        } else return null;
    }

    @PostMapping("info/metabolites/{id}")
    public String sentTOBack2(String add_comment, Model model, @PathVariable String id) throws ParseException {
        System.out.println("start!");
        JSONParser parser = new JSONParser();
        JSONObject convert_to_json = (JSONObject) parser.parse(add_comment);


        String comment = ((String) convert_to_json.get("comment"));
        System.out.println("comment is " + comment);
        String commentid = id;
        System.out.println("id is " + id);
        String name = ((String) convert_to_json.get("name"));
        System.out.println("name is " + name);
        String email = ((String) convert_to_json.get("email"));
        System.out.println("email is " + email);

        CommentComb comments = new CommentComb(commentid, name, email);
        Comment addcomment = new Comment(comments, comment);
        CommentRepo.save(addcomment);


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

            HashMap<String, String> model_reaction = new HashMap<String, String>();

            meta.put("meta", "" + data[2]);
            meta.put("big", "" + data[3]);
            meta.put("compartment", "" + data[4]);
            meta.put("charge", "" + data[5]);
            meta.put("inchi", "" + data[6]);
            meta.put("formula", "" + data[7]);
            meta.put("compound_name", "" + data[8]);
            meta.put("metanetx", "" + data[9]);
            for (Object[] obj : query_results) {
                model_reaction.put("" + obj[0], " " + obj[1]);
            }

            for (Map.Entry<String, String> entry : meta.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + " " + value);
                if (value.equals("null")) {
                    meta.put(key, "NA");
                }

            }
            model.addAllAttributes(meta);
            model.addAttribute("model_reaction", model_reaction);
            model.addAttribute("reaction_text", " Reactions the metabolite can "
                    + "take part in and the model the reaction is associated with.");

        } else {
            javax.persistence.Query queryCompound = em.createQuery(""
                    + "Select distinct  c, m FROM Metabolite m, Compound c where c.biggmetaboliteID = m.bigg_compoundID "
                    + "AND m.metaboliteID = :search", Object[].class);
            queryCompound.setParameter("search", id);
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
                if (value.equals("null")) {
                    meta.put(key, "NA");
                }

            }
            model.addAllAttributes(meta);
            model.addAttribute("reaction_text", "Metabolite is not part of any reactions.");



            List<Comment> comment2 = CommentRepo.findAllComment(id);

            List<CommentToString> commentToString = new ArrayList();
            for (Comment c2 : comment2) {
                CommentToString temp = new CommentToString(c2.CommentComb.name, c2.CommentComb.email,c2.content);
                commentToString.add(temp);
            }
            model.addAttribute("message", commentToString);
        }

        return "info/metabolites";

    }

    

}