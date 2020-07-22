package CS_564.Metabolites;

import java.util.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncController {
    @Autowired
    ReactionRepo ReactionRepo;
    @Autowired
    StoichiometryRepo StoichiometryRepo;
    @Autowired
    StartMetaReactionRepo StartMetaReactionRepo;
    @Autowired
    EndMetaReactionRepo EndMetaReactionRepo;
    @Autowired
    MetaboliteRepo MetaboliteRepo;

    // this part give a ruleout set to get rid of the bad effect on some necessary cofactor or big reactions.
    List<String> list = Arrays.asList("coa_c","nadp_c","nadph_c","nadh_c","nad_c","accoa_c",
            "ac_c","ac_e","ac_p","h2o_c","h2o_e","h2o_p","h_c","h_e","h_p","pi_c","ppi_c","atp_c","h_m",
            "h_h", "adp_x","amp_c","adp_c","adp_h","h_v","h_r","pi_r","h_g","h2o_r","h_x","pi_h","oh1_c",
            "o2_c","nh4_c","ppi_r","co2_p","h_n","co2_c","pi_m","nad_r","h2o_h","fdxox_c","biomass_pro_c",
            "udpg_c","udp_c","utp_c","q8_c","q8h2_c","itp_c","idp_c","datp_c","dadp_c","cdp_c","ctp_c","");

    Set<String> cofacter = new HashSet(list);

    List<String> list2 = Arrays.asList("BIOMASS_pro_c","nadp_c","nadph_c","nadh_c","nad_c","accoa_c",
            "ac_c","ac_e","ac_p","h2o_c","h2o_e","h2o_p","h_c","h_e","h_p","pi_c","ppi_c","atp_c","h_m",
            "h_h", "adp_x","amp_c","adp_c","adp_h","h_v","h_r","pi_r","h_g","h2o_r","h_x","pi_h","oh1_c",
            "o2_c","nh4_c","ppi_r","co2_p","h_n","co2_c","pi_m","nad_r","h2o_h","fdxox_c","biomass_pro_c",
            "udpg_c","udp_c","utp_c","q8_c","q8h2_c","itp_c","idp_c","datp_c","dadp_c","cdp_c","ctp_c","BIOMASS_WT_lumped");

    Set<String> knockout = new HashSet(list2);

//
//    @GetMapping("/functionality/first")
//    public String senTo(@RequestParam(value = "name", defaultValue = "")
//                                String name, Model model) {
////        ArrayList <Metabolite> list_of_metabolites = (ArrayList<Metabolite>) MetaboliteRepo.getListOfMetabolites();
////        ArrayList <String> list_of_compounds= (ArrayList<String>) MetaboliteRepo.getListOfCompounds();
////
////        model.addAttribute("listofmetabolites", list_of_metabolites);
////        model.addAttribute("listofcompounds", list_of_compounds);
//
//        if( name.equals("") ) {
//            model.addAttribute("test", "No Search Specifed");
//
//        }else {
//                HashMap<String, String[]> nextMetabolites = new LinkedHashMap();
//                ArrayList<String[]>  m =  (ArrayList<String[]>) StartMetaReactionRepo.findByMetaboliteId(name);
//                for (String[] m1 : m) {
//                    ArrayList<String> nextMetaboliteslist = ( ArrayList<String>) StartMetaReactionRepo.findnextMetabolite(m1[0], m1[1]);
//                    for (String nextMeta : nextMetaboliteslist) {
//                        if (!cofacter.contains(nextMeta)) {
//                            Stoichiometry s = StoichiometryRepo.findByReactionID(m1[1]);
//                            String StoichiometryString = s.toString();
//                            String[] reactinfo = new String[2];
//                            reactinfo[0] = m1[1];
//                            reactinfo[1] = StoichiometryString;
//                            nextMetabolites.put(nextMeta, reactinfo);
//                        }
//                    }
//                    model.addAttribute("nextMetabolites", nextMetabolites);
//            }
//
//        }
//
//        return "functionality/first";
//
//    }



    // this part of the code is using a BFS algortihm to search an end node with the certain distance with the start nodes.
    // the cofactor / knock out set is used to get rid of the bad effect of some node and edge with too much connect.
    // initial part is just a starter, find the edge of the start nodes (actually not necessarily, could be integrated with the second part).
    @GetMapping("/functionality/find_product")
    public String senTo2(@RequestParam(value = "name", defaultValue = "")
                                String name, Model model, @RequestParam(value = "steps", defaultValue = "")
            String times) {
//        ArrayList <Metabolite> list_of_metabolites = (ArrayList<Metabolite>) MetaboliteRepo.getListOfMetabolites();
//        ArrayList <String> list_of_compounds= (ArrayList<String>) MetaboliteRepo.getListOfCompounds();
//
//        model.addAttribute("listofmetabolites", list_of_metabolites);
//        model.addAttribute("listofcompounds", list_of_compounds);
//
        if( name.equals("") ) {
            model.addAttribute("test", "No Search Specifed");
        }else {
            /// Initial the search bar;
            ArrayList<String[]>  m =  (ArrayList<String[]>) StartMetaReactionRepo.findByMetaboliteId(name);
            HashMap<String[], ArrayList<String>> seen = new HashMap<>();
            HashSet<String> set = new HashSet();
            Queue<String[]> nextMeta = new LinkedList<>();
            for (String[] m1 : m) {
                String[] step = new String[3];
                step[0] = m1[0];
                step[1] = m1[1];
                step[2] = "0";
                if (m1[0]!= null && m1[1]!= null) {
                    if (!seen.containsKey(step)) {
                        ArrayList<String> pathway = new ArrayList<String>();
//                        System.out.println("this is initialition" + step[0]);
                        seen.put(step,pathway);
//                        System.out.println("This is providing:   " + step[0]+ "    " + step[1]+"  times  "+step[2]);
                        nextMeta.offer(step);

                    }
                }
            }
            /// Start searching,
            HashMap<String[], String> nextMetabolites = new HashMap();
            Set<String> target = new HashSet();

//          continue searching
            while (nextMeta.peek() != null) {
                String[] Meta = nextMeta.poll();
//                System.out.println("the time in loop" + count1);
//                System.out.println("total time" + count2);
//                System.out.println("This is providing:   " + Meta[0]+ "    " + Meta[1]+"  times  "+Meta[2]);
                    if (  StartMetaReactionRepo.findnextMetabolite(Meta[0], Meta[1]) != null) {
//                        System.out.println("This is findByMetaboliteId:   " + Meta[0]+ "    " + Meta[1]+"  times  "+Meta[2]);
                        ArrayList<String>  m2 =  (ArrayList<String>) StartMetaReactionRepo.findnextMetabolite(Meta[0], Meta[1]);
                        for (String nextM : m2) {
                            if (!set.contains(nextM)) {
                                ArrayList<String[]> nextMetaboliteslist = ( ArrayList<String[]>) StartMetaReactionRepo.findByMetaboliteId(nextM);
                                set.add(nextM);
                                for (String[] next : nextMetaboliteslist) {
                                    if (!cofacter.contains(next[0])){
                                        String[] step = new String[3];
                                        step[0] = next[0];
                                        step[1] = next[1];
                                        step[2] = String.valueOf(Integer.valueOf(Meta[2])+1);
//                                                        System.out.println("This is providing:   " + step[0]+ "    " + step[1]+"  times  "+step[2]);

                                        if (!seen.containsKey(step)) {
//                                                        System.out.println("This is using:   " + Meta[0]+ "    " + Meta[1]+"  times  "+Meta[2]);
//                                                        System.out.println("This is using:   " + seen.get(Meta));
                                            ArrayList<String> pathway = new ArrayList<String>(seen.get(Meta));
                                            pathway.add(Meta[1]);
                                            seen.put(step,pathway);
//                                            System.out.println("This is offering:   " + seen.get(step));
                                            if (step[2].equals(times)) {
                                                if (!target.contains(step[0])) {
                                                    target.add(step[0]);
                                                    String path = step[2] +" steps pathway is : " + this.ArrayListtoString(pathway);
                                                    nextMetabolites.put(step, path);
                                                }
                                            } else {
                                                nextMeta.offer(step);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                }
            }
            int n = 0;
            while (n < 5) {
                System.out.println("Finished############");
                System.out.println("Finished############");
                n ++;
            }

            model.addAttribute("nextMetabolites", nextMetabolites);
            model.addAttribute("name", name);
            model.addAttribute("steps", times);
        }

       return "functionality/find_product";

    }


    // this part of the code is using a BFS algortihm to search a pathway between two nodes.
    // the cofactor / knock out set is used to get rid of the bad effect of some node and edge with too much connect.
    // initial part is just a starter, find the edge of the start nodes (actually not necessarily).
    @GetMapping("/functionality/find_pathway")
    public String senTo3(@RequestParam(value = "start", defaultValue = "")
                                 String start, Model model, @RequestParam(value = "end", defaultValue = "")
                                 String end ,@RequestParam(value = "knock", defaultValue = "") String knock) {
        ArrayList <Metabolite> list_of_metabolites = (ArrayList<Metabolite>) MetaboliteRepo.getListOfMetabolites();
        ArrayList <String> list_of_compounds= (ArrayList<String>) MetaboliteRepo.getListOfCompounds();

        model.addAttribute("listofmetabolites", list_of_metabolites);
        model.addAttribute("listofcompounds", list_of_compounds);


        if( start.equals("") ) {
            model.addAttribute("test", "No Search Specifed");
        }else {
            /// Initial the search bar;
            ArrayList<String[]>  m =  (ArrayList<String[]>) StartMetaReactionRepo.findByMetaboliteId(start);
            HashMap<String[], ArrayList<String>> seen = new HashMap<>();
            HashSet<String> set = new HashSet();
            Queue<String[]> nextMeta = new LinkedList<>();
            for (String[] m1 : m) {
                String[] step = new String[3];
                step[0] = m1[0];
                step[1] = m1[1];
                step[2] = "0";
                if (m1[0]!= null && m1[1]!= null) {
                    if (!seen.containsKey(step)) {
                        ArrayList<String> pathway = new ArrayList<String>();
//                        System.out.println("this is initialition" + step[0]);
                        seen.put(step,pathway);
//                        System.out.println("This is providing:   " + step[0]+ "    " + step[1]+"  times  "+step[2]);
                        nextMeta.offer(step);

                    }
                }
            }
            /// Start searching,
            HashMap<String[], String> nextMetabolites = new HashMap();
            knockout.add(knock);
            {
                out:
                {
                while (nextMeta.peek() != null) {
                    String[] Meta = nextMeta.poll();
                    if (!knockout.contains(Meta[1])) {
                        if (StartMetaReactionRepo.findnextMetabolite(Meta[0], Meta[1]) != null) {
                            ArrayList<String> m2 = (ArrayList<String>) StartMetaReactionRepo.findnextMetabolite(Meta[0], Meta[1]);
                            for (String nextM : m2) {
                                if (!set.contains(nextM)) {
                                    ArrayList<String[]> nextMetaboliteslist = (ArrayList<String[]>) StartMetaReactionRepo.findByMetaboliteId(nextM);
                                    set.add(nextM);
                                    for (String[] next : nextMetaboliteslist) {

                                        if (!cofacter.contains(next[0])) {
                                            String[] step = new String[3];
                                            step[0] = next[0];
                                            step[1] = next[1];
                                            step[2] = String.valueOf(Integer.valueOf(Meta[2]) + 1);
                                            if (!seen.containsKey(step)) {
                                                ArrayList<String> pathway = new ArrayList<String>(seen.get(Meta));
                                                pathway.add(Meta[1]);
                                                seen.put(step, pathway);
                                                if (step[0].equals(end)) {
                                                    String path = step[2] + " steps pathway is : " + this.ArrayListtoString(pathway);
                                                    nextMetabolites.put(step, path);
                                                    break out;
                                                } else {
                                                    nextMeta.offer(step);
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                    if (nextMeta.peek() == null) {
                        model.addAttribute("test", "No Search Specifed");
                        return "functionality/find_pathway";
                    }
                }

            }
            int n = 0;
            while (n < 5) {
                System.out.println("Finished############");
                System.out.println("Finished############");
                n ++;
            }

            model.addAttribute("nextMetabolites", nextMetabolites);
            model.addAttribute("start", start);
            model.addAttribute("end", end);
        }

        return "functionality/find_pathway";

    }
    public String ArrayListtoString(ArrayList<String> pathway) {
        String res = "";
        for (String step : pathway) {
            res += step;
            res += " -> ";
        }
        res = res.substring(0, res.length() - 4);
        return res;
    }


}

