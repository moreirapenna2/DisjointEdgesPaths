/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxdisjointedgespaths;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
     
    private int id;
     
    char spec;
    
    private List<Node> shortestPath = new LinkedList<>();
     
    private Integer distance = Integer.MAX_VALUE;
     
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
 
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
        //getAdjacentNodes().put(destination, distance);
    }
    
    public void setDestinations(Map<Node, Integer> adjacentNodes){
        this.adjacentNodes = adjacentNodes;
    }
    
    public void clearPath(){
        this.shortestPath.clear();
        this.distance = Integer.MAX_VALUE;
    }
  
    public void removeDestination(Node destination){
        adjacentNodes.remove(destination);
    }
    
    public Node(int id, char spec) {
        this.id = id;
        this.spec = spec;
    }
     
    //if its a source returns 1, if its a sink returns -1, if its nothing, returns 0
    public int isSpec(){
        if(this.spec == 's')
            return 1;
        else if(this.spec == 't')
            return -1;
        return 0;
    }
    // getters and setters
    
    public int getDist(){
        return this.distance;
    }
    
    public void setDist(int dist){
        this.distance = dist;
    }

    /**
     * @return the shortestPath
     */
    public List<Node> getShortestPath() {
        return shortestPath;
    }

    /**
     * @param shortestPath the shortestPath to set
     */
    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    /**
     * @return the adjacentNodes
     */
    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

   
    @Override
    public Node clone(){
        Node tmp = new Node(this.id, this.spec);
        tmp.setDestinations(this.adjacentNodes);
        tmp.shortestPath = this.shortestPath;
        tmp.distance = this.distance;
        return tmp;
    }
}
