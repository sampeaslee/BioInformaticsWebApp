package CS_564.Metabolites;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReactionGraph {




class Metabolite<String> {
        /**
         * Private inner class to store a vertex(Metabolite) of a graph
         */
        String name;// Data associated with a vertex
        //List of vertex that you can reach from this vertex
        ArrayList<String> succesors;
        //Stores the reaction associated with reaching the vertex from 
        //this vertex 
        //{"metaID": "reactionID"}
        Hashtable<String, String> reactions;
    

        /**
         * Constructor to create new Metabolite
         * @param data
         */
        Metabolite() {
            succesors = new ArrayList<String>();
            reactions = new Hashtable<String, String>();

        }

        /**
         * Constructor to create new Metabolite
         * @param data
         */
        Metabolite(String data) {
            this.name = data;
            succesors = new ArrayList<String>();
            reactions = new Hashtable<String, String>();
        }

        /**
         * Retrieve name of metabolite  associated with a GraphNode
         * @return data
         */
        private String getData() {
            return name;
        }
        /**
         * Retrieves the ArrayList of successors for the Metabolite
         * @return neighbors
         */
        private ArrayList<String> getSuccessors() {
            return succesors;
        }

        /**
         * Adds a successor to the Metabolite's succesors ArrayList
         * @param neighboor
         */
        private void addSuccessor(String succesor) {
            succesors.add(succesor);
        }
        private Hashtable getReactions() {
            return reactions;
        }

        /**
         * Adds a successor to the Metabolites succesors ArrayList
         * @param neighboor
         */
        private void addReaction(String meta, String reaction) {
            reactions.put(meta, reaction);
        }
    }//End of inner Metabolite class
    
    //List of Metabolites in the graph
    ArrayList<Metabolite<String>> vertices  =
        new ArrayList<Metabolite<String>>(0);
    // Set to hold all vertex in the graph
    Set<String> allVertices =  new HashSet<java.lang.String>();
    int numVertices = 0;//Number of Vertices
    int numEdges = 0;// Number of edges 

    /*
     * Default no-argument constructor
     */ 
    public ReactionGraph(){

    }
    
    /**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
    public void addVertex(String vertex) {
        //Vertex is null do not add
        if(vertex == null) {
            return;}
        //Vertex is already in the graph do no add
        if(search(vertex) != null) return;
        //Vertex is not in graph so add it
        Metabolite<String> v = new Metabolite<String>(vertex);
        vertices.add(v);
        allVertices.add(vertex);
        numVertices++;
        
    }

    /**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
    public void removeVertex(String vertex) {
        //Vertex is null do not add
        if(vertex == null) return;
        Metabolite<String> vRemoved = search(vertex);
        //Vertex is not in the graph do not remove anything
        if(vRemoved == null) return;
        //Go through list of edges for each vertex in graph
        //If vertex has a edge with vRemoved, removed the edge from 
        //the graph
        List<String> n;
        for(Metabolite<String> v: vertices) {
            n = v.getSuccessors();
            if(n.contains(vertex)) {
                n.remove(vertex);
                numEdges--;
                v.getReactions().remove(vertex);
            }
        }
       // Need to decrease the number of edges by one for each
       // outgoing edge of B
       for(String adj: vRemoved.getSuccessors()){
           numEdges--;
           
       }
        //Remove vertex from graph 
        vertices.remove(vRemoved);
        allVertices.remove(vertex);
        numVertices--;
  
    }

    /**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * add vertex, and add edge, no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
     */
    public void addEdge(String vertex1, String vertex2, String reaction) {
        //Do not add edge if either vertex is null
        if(vertex1 == null | vertex2 == null) return;
        Metabolite<String> v1 = search(vertex1);
        Metabolite<String> v2 = search(vertex2);
        if(v1 == null) {           
            addVertex(vertex1);
            v1 = search(vertex1);
        }
        if(v2 == null) {
            addVertex(vertex2);
            v2 = search(vertex2);
        }
        //Iterate through adjacent nodes for v1
        //If v2 is already an adjacent node the do not add new edge
       for(String n: v1.getSuccessors()){ 
           if(n.equals(vertex2)) {
               return;
           }
       }
       //No edge exist between v1 and v2 so new edge can be created
       v1.addSuccessor(vertex2);
       v1.addReaction(vertex2, reaction);
       numEdges++;
       
    }
    /**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
    public void removeEdge(String vertex1, String vertex2) {
        Metabolite<String> v1 = search(vertex1);
        Metabolite<String> v2 = search(vertex2);
        //IF either vertix is not in Graph do not remove anything 
        if(v1 == null | v2 == null) return;
        // Get list of adjacent vertices of vertex1
        // If vertex2 is in the list remove it and 
        // decrease the number of edges in the graph
        List<String> neighbors = v1.getSuccessors();
        if(neighbors.contains(vertex2)) {
            neighbors.remove(vertex2);
            numEdges--;
            v1.getReactions().remove(vertex2);
        }
        
    }   

    /**
     * Returns a Set that contains all the vertices
     * 
     */
    public Set<String> getAllVertices() {
        return allVertices;
    }

    
    /**
     * Returns the number of edges in this graph.
     */
    public int size() {
        return numEdges;
    }

    /**
     * Returns the number of vertices in this graph.
     */
    public int order() {
        return numVertices;
    }
    
////////////////////////////////////////////////////////////////////////////////
    /*Private methods*/    
    /**
     * Searches for a specified vertex in the graph and returns the 
     * Metabolite if the vertex is in the graph, otherwise return null
     * @param data
     * @return Metabolite or null
     */
    private Metabolite<String> search(String data) {
        for(int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).getData().equals(data)) {
                return vertices.get(i);
            }
        }
        return null;
    }
    /**
     * Prints Graph
     */
    public void printGraph() {
        System.out.println("Printing Graph......");
        System.out.println("List of All Vertexes: ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices.get(i).getData() + ", ");
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            Metabolite<String> currV = vertices.get(i);
            ArrayList<String> neighbors = currV.getSuccessors();
            System.out.print(
                "Metabolite: " + currV.getData()+ 
                " Successors: ");
            if (neighbors.size() == 0) {
                System.out.println("None");
            }
            for (int j = 0; j < neighbors.size(); j++) {
                if (j != neighbors.size() - 1) {
                    System.out.print("" + neighbors.get(j) + ", ");
                } else {
                    System.out.println(neighbors.get(j));
                }
            }
            System.out.println("Reaction: " + 
                currV.getReactions() );
        }
    }
    
       public static void main(String[] args) throws FileNotFoundException, IOException, ParseException { 
           Object obj = new JSONParser().parse(new FileReader
               ("C:\\Users\\peasl\\SalmoPrograms\\Java_Files\\CS_564\\JSON\\final_reactions_from_bigg.json"));
           JSONObject jsonObj = (JSONObject) obj;
           //keySet() returns a set of all the keys in the JSON 
       
           
          JSONObject j =  (JSONObject) jsonObj.get("10M3OACPO");
        
          j  =  (JSONObject) j.get("stoichometry");
          Set<String> metaIDs = j.keySet();
          System.out.println(metaIDs.contains("3hoddecACP_c"));
          
          String start = "";
          String end = "";

          
          for(String s: metaIDs) {
              Double coef = (Double) j.get(s);
             
              if(coef < 0) {
                  start = start + s + ", " ; 
              }else {
                  end = end  +s + ", " ;
              }
          }
          
         start =  start.substring(0, start.length() - 1);
         end =  end.substring(0, end.length() - 1);
          

           ReactionGraph g = new ReactionGraph();
           
           
           g.addEdge("A","B", "R");
           g.printGraph();
           g.removeVertex("B");
           g.addEdge("A","B", "R");
           g.addVertex("a");
           
           

           g.printGraph();


       }
    
}