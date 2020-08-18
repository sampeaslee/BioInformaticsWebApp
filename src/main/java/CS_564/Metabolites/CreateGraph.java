package CS_564.Metabolites;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CreateGraph {
    public ReactionGraph graph;
    
    public CreateGraph() throws FileNotFoundException, IOException, ParseException {
        List<String> list = Arrays.asList("coa_c","nadp_c","nadph_c","nadh_c","nad_c","accoa_c",
            "ac_c","ac_e","ac_p","h2o_c","h2o_e","h2o_p","h_c","h_e","h_p","pi_c","ppi_c","atp_c","h_m",
            "h_h", "adp_x","amp_c","adp_c","adp_h","h_v","h_r","pi_r","h_g","h2o_r","h_x","pi_h","oh1_c",
            "o2_c","nh4_c","ppi_r","co2_p","h_n","co2_c","pi_m","nad_r","h2o_h","fdxox_c","biomass_pro_c",
            "udpg_c","udp_c","utp_c","q8_c","q8h2_c","itp_c","idp_c","datp_c","dadp_c","cdp_c","ctp_c","");



    List<String> list2 = Arrays.asList("BIOMASS_pro_c","nadp_c","nadph_c","nadh_c","nad_c","accoa_c",
            "ac_c","ac_e","ac_p","h2o_c","h2o_e","h2o_p","h_c","h_e","h_p","pi_c","ppi_c","atp_c","h_m",
            "h_h", "adp_x","amp_c","adp_c","adp_h","h_v","h_r","pi_r","h_g","h2o_r","h_x","pi_h","oh1_c",
            "o2_c","nh4_c","ppi_r","co2_p","h_n","co2_c","pi_m","nad_r","h2o_h","fdxox_c","biomass_pro_c",
            "udpg_c","udp_c","utp_c","q8_c","q8h2_c","itp_c","idp_c","datp_c","dadp_c","cdp_c","ctp_c","BIOMASS_WT_lumped");
        
        Object obj = new JSONParser().parse(new FileReader
            ("JSON\\final_reactions_from_bigg.json"));
        JSONObject jsonObj = (JSONObject) obj;
        //keySet() returns a set of all the keys in the JSON 
        Set<String> ReactionsIDs = jsonObj.keySet();
        ReactionGraph g = new ReactionGraph();
        int i = 0;
        for(String idKey: ReactionsIDs) {
            i++;
            if(i == 2500) {
                break;
            }
              
            JSONObject reactData =(JSONObject)  jsonObj.get(idKey);
            ArrayList<String> st = new ArrayList<String>();
            ArrayList<String> e = new ArrayList<String>();
            reactData  =  (JSONObject) reactData.get("stoichometry");
            Set<String> metaIDs = reactData.keySet();
            for(String s: metaIDs) {
                Double coef = (Double) reactData.get(s);
               if(list.contains(s) || list2.contains(s)) {
               }else {
               
                if(coef < 0) {
                   st.add(s);
                }else {
                    e.add(s);
                }
                for(String sp: st) {
                    for(String en: e) {
                        g.addEdge(sp,en, idKey);
                    }
                  
                }

                
            }
            }
        }
        this.graph = g;
     
    }
}
