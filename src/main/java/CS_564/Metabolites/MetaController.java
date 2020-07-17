package CS_564.Metabolites;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MetaController {
    @Autowired
    MetaboliteRepo MetaboliteRepo;
    @Autowired
    CompoundRepo CompoundRepo;



    // create a template individual page of reaction without information. (can't find anything);
    // feel free to change the name, I think a better structure could be /
    @GetMapping("/info/metabolites")
    public String sendToFrontend(String name, Model model) {
        return "/info/metabolites";
    }



    // this function help you find the individual page of one reaction!
    // in the reactions.html, we can use
    //  <a th:text="${relation.getKey().reactionID}" th:href="@{'~/reactiontemp/'+${relation.getKey().reactionID}}" />
    // to direct the website of the individual page of certain reaction.
    // we can request the information of the reaction entity or stoichiometry entity in the reactiontemp.html;
    // by using  <p th:text="'This is the ID of ' + ${reaction.reactionID}"> </p>
    @GetMapping("info/metabolites/{id}")
    public String sendToFrontend(String name, Model model, @PathVariable String id) {
        Metabolite metabolite = MetaboliteRepo.getAMetabolite(id);
        Compound compound = CompoundRepo.findByMetaboliteID(id);
        if( metabolite != null ) {
            model.addAttribute("metabolite", metabolite);
            model.addAttribute( "compound", compound);
            return "info/metabolites";
        } else return null;
    }


}
