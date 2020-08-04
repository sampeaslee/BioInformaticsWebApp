package CS_564.Metabolites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReactController {
    @Autowired
    ReactionRepo ReactionRepo;
    @Autowired
    StoichiometryRepo StoichiometryRepo;
    @Autowired
    CommentRepo CommentRepo;

    @GetMapping("/tryBS")
    public String sendToFrontend2(String name, Model model) {

        return "tryBS";

    }


    @GetMapping("/reactions")
    public String senTo(@RequestParam(value = "name", defaultValue = "")
                                String name, Model model) {


        if( name.equals("") ) {
            model.addAttribute("test", "No Search Specifed: Showing All Reactions");
            HashMap<Reaction, String> relation = new HashMap();
            ArrayList <Reaction> s1 =
                    (ArrayList<Reaction>) ReactionRepo.getReactions();
            for (Reaction r : s1) {
                Stoichiometry s = StoichiometryRepo.findByReactionID(r.ReactionID);

                String StoichiometryString = s.toString();

                relation.put(r, StoichiometryString);
            }
            model.addAttribute("reactions", relation);
        }else {
            ArrayList <Reaction> s1 =
                    (ArrayList<Reaction>) ReactionRepo.autoSearch(name);
            HashMap<Reaction, String> relation = new HashMap();
            for (Reaction r : s1) {
                Stoichiometry s = StoichiometryRepo.findByReactionID(r.ReactionID);

                String StoichiometryString = s.toString();

                relation.put(r, StoichiometryString);
            }
            System.out.println(s1.size());
            String search = "Current Search: " + name;
            model.addAttribute("test", search);
            model.addAttribute("reactions", relation);
        }

        return "reactions";

    }


    // create a template individual page of reaction without information. (can't find anything);
    // feel free to change the name, I think a better structure could be /
    @GetMapping("/info/reactions")
    public String sendToFrontend(String name, Model model) {
        return "/info/reactions";
    }



    // this function help you find the individual page of one reaction!
    // in the reactions.html, we can use
    //  <a th:text="${relation.getKey().reactionID}" th:href="@{'~/reactiontemp/'+${relation.getKey().reactionID}}" />
    // to direct the website of the individual page of certain reaction.
    // we can request the information of the reaction entity or stoichiometry entity in the reactiontemp.html;
    // by using  <p th:text="'This is the ID of ' + ${reaction.reactionID}"> </p>
    @GetMapping("info/reactions/{id}")
    public String sendToFrontend(String name, Model model, @PathVariable String id) {
        Reaction reaction = ReactionRepo.findByReactionID(id);
        Stoichiometry stoichiometry = StoichiometryRepo.findByReactionID(id);
        if( reaction != null ) {
            model.addAttribute("reaction", reaction);
            model.addAttribute( "stoichiometry", stoichiometry);



            List<Comment> comment2 = CommentRepo.findAllComment(id);

            List<CommentToString> commentToString = new ArrayList();
            for (Comment c : comment2) {
                CommentToString temp = new CommentToString(c.CommentComb.name, c.CommentComb.email,c.content);
                commentToString.add(temp);
            }
            model.addAttribute("message", commentToString);
            return "info/reactions";
        } else return null;



    }


}

