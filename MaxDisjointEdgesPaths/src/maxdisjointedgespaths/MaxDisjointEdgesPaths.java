/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxdisjointedgespaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class MaxDisjointEdgesPaths {
    
    static int nodeQuant;
    static Node nodes[];
    static Graph g;
    static int sink;
    static List<Node> firstPath;
    static List<Node> secondPath;

    public static void main(String[] args) {
        //Djikstra djikstra = new Djikstra();
        
        readFile("grafo.txt");
        g = Djikstra.calculateShortestPathFromSource(g,nodes[findSource()]);
        firstPath = getPath(nodes[findSink()]);
        removePath(nodes[findSink()]);
        
        for(int i=0; i < nodeQuant; i++){
            nodes[i].clearPath();
        }
        //nodes[findSink()].clearPath();
        g = Djikstra.calculateShortestPathFromSource(g,nodes[findSource()]);
        secondPath = getPath(nodes[findSink()]);
    }
    
    static int findSink(){
        for(int i=0; i < nodeQuant; i++){
            if(nodes[i].spec=='t')
                return i;
        }
        return -1;
    }
    
    static int findSource(){
        for(int i=0; i < nodeQuant; i++){
            if(nodes[i].spec=='s')
                return i;
        }
        return -1;
        
    }
    
    static List<Node> getPath(Node last){
        List<Node> path = nodes[findSink()].getShortestPath();
        List<Node> newPath = new LinkedList<>();
        for(int i=0; i < path.size(); i++){
            newPath.add(path.get(i).clone());
        }
        return newPath; 
    }
    
    static void readFile(String fName){
        try {
            g = new Graph();
            File file = new File(fName);
            
            Scanner input = new Scanner(file);
            
            //quantity of nodes
            nodeQuant = input.nextInt();
            input.nextLine();

                    
            nodes = new Node[nodeQuant];
            String line;
            String tmp[];
            
            //read source and sink
            line = input.nextLine();
            tmp = line.split(",");
            
            for(int i=0; i < nodeQuant; i++){
                nodes[i] = new Node(i, tmp[i].charAt(0));
            }
            
            for(int i=0; i < nodeQuant; i++){
                line = input.nextLine();
                tmp = line.split(",");
                
                
                for(int j=0; j < nodeQuant; j++){
                    if(Integer.parseInt(tmp[j])!=0){
                        System.out.println(nodes[i]);
                        System.out.println(nodes[j]);
                        nodes[i].addDestination(nodes[j], Integer.parseInt(tmp[j]));
                    }
                    
                }
                g.addNode(nodes[i]);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaxDisjointEdgesPaths.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void removePath(Node last){
        List<Node> tmp = last.getShortestPath();
        Node tmp2;
        
        for(int i=0; i < tmp.size()-1; i++){
            if(i==tmp.size()-2)
                tmp.get(i+1).removeDestination(nodes[findSink()]);
            tmp.get(i).removeDestination(tmp.get(i+1));
        }
        
    }
    
}
