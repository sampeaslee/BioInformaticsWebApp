package CS_564.Metabolites;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.util.stream.Collectors.toCollection;
public class ReactionGraph {




public class Metabolite {
        /**
         * Private inner class to store a vertex(Metabolite) of a graph
         */
        String name;// Data associated with a vertex
        //List of vertex that you can reach from this vertex
        ArrayList<Metabolite> succesors;
        //Stores the reaction associated with reaching the vertex from 
        //this vertex 
        //{"metaID": "reactionID"}
        Hashtable<String, String> reactions;
        Metabolite shorestPathPred;
    

        /**
         * Constructor to create new Metabolite
         * @param data
         */
        Metabolite() {
            succesors = new ArrayList<Metabolite>();
            reactions = new Hashtable<String, String>();

        }

        /**
         * Constructor to create new Metabolite
         * @param data
         */
        Metabolite(String data) {
            this.name = data;
            succesors = new ArrayList<Metabolite>();
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
        private ArrayList<Metabolite> getSuccessors() {
            return succesors;
        }

        /**
         * Adds a successor to the Metabolite's succesors ArrayList
         * @param neighboor
         */
        private void addSuccessor(Metabolite succesor) {
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
    ArrayList<Metabolite> vertices  =
        new ArrayList<Metabolite>(0);
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
        Metabolite v = new Metabolite(vertex);
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
        Metabolite vRemoved = search(vertex);
        //Vertex is not in the graph do not remove anything
        if(vRemoved == null) return;
        //Go through list of edges for each vertex in graph
        //If vertex has a edge with vRemoved, removed the edge from 
        //the graph
        List<Metabolite> n;
        for(Metabolite v: vertices) {
            n = v.getSuccessors();
            if(n.contains(vertex)) {
                n.remove(vertex);
                numEdges--;
                v.getReactions().remove(vertex);
            }
        }
       // Need to decrease the number of edges by one for each
       // outgoing edge of B
       for(Metabolite adj: vRemoved.getSuccessors()){
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
        Metabolite v1 = search(vertex1);
        Metabolite v2 = search(vertex2);
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
       for(Metabolite n: v1.getSuccessors()){ 
           if(n.name.equals(vertex2)) {
               return;
           }
       }
       //No edge exist between v1 and v2 so new edge can be created
       v1.addSuccessor(v2);
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
        Metabolite v1 = search(vertex1);
        Metabolite v2 = search(vertex2);
        //IF either vertix is not in Graph do not remove anything 
        if(v1 == null | v2 == null) return;
        // Get list of adjacent vertices of vertex1
        // If vertex2 is in the list remove it and 
        // decrease the number of edges in the graph
        List<Metabolite> neighbors = v1.getSuccessors();
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
///////////////////////////////////////////////////////////////////////////////
    //find_pathway

    public ArrayList<Metabolite> getShortestPath(String vertex1, String vertex2) {

        boolean isPath = false;
        Metabolite v1 = search(vertex1);
        Metabolite v2 = search(vertex2);
        if (v1 == null || v2 == null) {
            // If a vertex is not in the graph return null
         
            return null;
        }

        // Mark all vertices unvisited and set their predecessor to null
        ArrayList<String> visited = new ArrayList<String>();
        // Queue to implement Breadth First Search
        Queue<Metabolite> q = new LinkedList<Metabolite>();
        // Mark v2 visited and add to queue

        visited.add(v1.name);
        q.add(v1);
        // Normal Breadth First Search, with a little extra code to properly
        // set the correct predecessor for each vertex and stop searching if
        // v1 is reached
        while (!q.isEmpty()) {
            Metabolite current = q.poll();
            for (Metabolite k : current.succesors) {
    
                if (!visited.contains(k.name)) {

                    // Set the vertex as visited
                    visited.add( k.name);
                    // Set it's predecessor to the vertex that its visited from
                    k.shorestPathPred = (current);
                    // Add vertex to queue
                    q.add(k);
                    // Exit the while loop if v1 is reached
                    // There is a path so set isPath to TRUE
                    if (k.name.equals(vertex2)) {
                        isPath = true;
                        break;
                    }
                }
            }
        }

        if (isPath) {
            // List to store the names of users in the shortest path
            // from v1 to v2
            ArrayList<Metabolite> shortestPath = new ArrayList<>();
            // Add name at v1 to list
            shortestPath.add( v2);
            // Add name at v1's predecessor to list
            shortestPath.add( v2.shorestPathPred);
            // New queue to get correct order of the shortest path
            Queue<Metabolite> q2 = new LinkedList<>();
            // Add v1's predecessor to the queue
            q2.add(v2.shorestPathPred);
            while (!q2.isEmpty()) {
                // Grab and remove the front of the queue
                Metabolite n = q2.poll();
                // If the vertex has a predecessor
                // Add the predecessor to the shortestPath list
                // and to the queue
                if (n.shorestPathPred != null) {
                    shortestPath.add( n.shorestPathPred);
                    q2.add(n.shorestPathPred);
                }
            }
            return shortestPath;
        } else {
            // If there's no path between the two vertexes return null
            System.out.println("SHIT");
            return null;
        }
    }
///////////////////////////////////////////////////////////////////////////////
//    public ArrayList<ArrayList<Metabolite>> getPathsWithinCertainDistance(String vertex1, int d) {
//        if(d == 0) {
//            return null;
//        }
//
//        Metabolite v1 = search(vertex1);
//        if (v1 == null ) {
//            // If a vertex is not in the graph return null
//         
//            return null;
//        }
//
//        // Mark all vertices unvisited and set their predecessor to null
//        ArrayList<String> visited = new ArrayList<String>();
//        // Queue to implement Breadth First Search
//        Queue<Metabolite> q = new LinkedList<Metabolite>();
//        
//        // Mark v2 visited and add to queue
// 
//        visited.add(v1.name);
//        q.add(v1);
//        // Normal Breadth First Search, with a little extra code to properly
//        // set the correct predecessor for each vertex and stop searching if
//        // depth d is reached
//        int currentDepth = 0; 
//        int nodeToDepthIncrease  = 1;
//        boolean depthIncrease = false;
//        while (!q.isEmpty()) {
//            nodeToDepthIncrease--;
//            Metabolite current = q.poll();
//            if(nodeToDepthIncrease == 0) {
//                currentDepth++;
//                System.out.println(currentDepth);
//                depthIncrease = true;
//            }
//
//            for (Metabolite k : current.succesors) {
//                    if(depthIncrease) {
//                        nodeToDepthIncrease = current.succesors.size();
//                        depthIncrease = false;
//                    }
//            
//                    // Set the vertex as visited
//                    if(visited.contains(k.name)) {
//                        continue;
//                    }else {
//                        visited.add( k.name);
//                        k.shorestPathPred = (current);
//                      
//                        q.add(k);
//                    }
//                
//            }    
//            System.out.println();
//           if(d <= currentDepth) {
//               break;
//           }
//
//
//        }
//        ArrayList<ArrayList<Metabolite>> allPaths = new ArrayList<>();
//      
//        while (!q.isEmpty()) {
//            Metabolite current = q.poll();
//            // List to store the names of users in the shortest path
//            // from v1 to v2
//            ArrayList<Metabolite> path = new ArrayList<>();
//            // Add name at v1 to list
//            path.add( current);
//            // Add name at v1's predecessor to list
//            path.add( current.shorestPathPred);
//            // New queue to get correct order of the shortest path
//            Queue<Metabolite> q2 = new LinkedList<>();
//            // Add v1's predecessor to the queue
//            q2.add(current.shorestPathPred);
//            while (!q2.isEmpty()) {
//                // Grab and remove the front of the queue
//                Metabolite n = q2.poll();
//                System.out.println(n.name);
//                // If the vertex has a predecessor
//                // Add the predecessor to the shortestPath list
//                // and to the queue
//                if (n.shorestPathPred != null) {
//            
//                    path.add( n.shorestPathPred);
//                    if(visited.contains(n.shorestPathPred.name)) {
//                        continue;
//                    }
//                    q2.add(n.shorestPathPred);
//                }
//            }
//            allPaths.add(path);
//            
//        }
//        return allPaths;
//
//    }
////////////////////////////////////////////////////////////////////////////////
    /*Private methods*/    
    /**
     * Searches for a specified vertex in the graph and returns the 
     * Metabolite if he vertex is in the graph, otherwise return null
     * @param data
     * @return Metabolite or null
     */
    private Metabolite search(String data) {
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
            Metabolite currV = vertices.get(i);
            ArrayList<Metabolite> neighbors = currV.getSuccessors();
            System.out.print(
                "Metabolite: " + currV.getData()+ 
                " Successors: ");
            if (neighbors.size() == 0) {
                System.out.println("None");
            }
            for (int j = 0; j < neighbors.size(); j++) {
                if (j != neighbors.size() - 1) {
                    System.out.print("" + neighbors.get(j).name + ", ");
                } else {
                    System.out.println(neighbors.get(j).name);
                }
            }
            System.out.println("Reaction: " + 
                currV.getReactions() );
        }
    }

       
     
}