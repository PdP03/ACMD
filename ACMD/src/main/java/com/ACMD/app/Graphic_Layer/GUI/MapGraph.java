package com.ACMD.app.Graphic_Layer.GUI;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.graph.*;




//How about mettere statica nodes? 

public class MapGraph {
    SimpleDirectedWeightedGraph<Node, DefaultWeightedEdge> map;
    /**
     * Initialize the graph "map" based on the informations of the given file 
     * @param path the path of the file
     */
    public MapGraph(String path){

            map = new SimpleDirectedWeightedGraph<Node, DefaultWeightedEdge>(
            DefaultWeightedEdge.class); //Creazione di una mappa di nodi 

           List<Node> nodes = new ArrayList<Node>();
            try {
                FileReader f = new FileReader(path);
                Scanner file = new Scanner(f);
                boolean addingMode=true;
                int count=0;
                while(file.hasNextLine())
                {
                    if(addingMode)
                    {
                        String theLine = file.nextLine();
                        if(theLine.equals("Connessioni")){addingMode=false; continue;}
                        String line[] = theLine.split(","); 
                        nodes.add(new Node(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
                        map.addVertex(nodes.get(count++));
                    }
                    else
                    {
                        String theLine = file.nextLine();
                        String line[] = theLine.split(",|\\:|\\>"); //Split on "," or ":" or ">" 
                        //line ={x,y, x2, y2, direction1, direction2} where direction = north or south or east or west
                        int i=0,j=0;
                        boolean foundI=false,foundJ=false;
                        for(Node n:nodes)
                        {
                        if(n.getCoord().getX()==Integer.parseInt(line[0]) && n.getCoord().getY()==Integer.parseInt(line[1]) && !foundI) {foundI=true;}
                        else{ if(foundI==false) i++;}
                        if(n.getCoord().getX()==Integer.parseInt(line[2]) && n.getCoord().getY()==Integer.parseInt(line[3] ) && !foundJ) {foundJ=true;}
                        else{  if(foundJ==false)j++;}
                        }

                        map.addEdge(nodes.get(i),nodes.get(j));
                        map.addEdge(nodes.get(j),nodes.get(i));
                        switch(line[4])
                        {
                            case "N": 
                                nodes.get(i).setNorth(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                break;
                            case "S": 
                                nodes.get(i).setSouth(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                break;
                            case "E": 
                                nodes.get(i).setEast(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                break;
                            case "O": 
                                nodes.get(i).setWest(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                break;
                        }
                        System.out.println(line[5]);
                        switch(line[5])
                        {
                            case "N": 
                                nodes.get(j).setNorth(new Coordinates(nodes.get(i).getCoord().getX() , nodes.get(i).getCoord().getY()));
                                break;
                            case "S": 
                                nodes.get(j).setSouth(new Coordinates(nodes.get(i).getCoord().getX() , nodes.get(i).getCoord().getY()));
                                break;
                            case "E": 
                                nodes.get(j).setEast(new Coordinates(nodes.get(i).getCoord().getX() , nodes.get(i).getCoord().getY()));
                                break;
                            case "O": 
                                nodes.get(j).setWest(new Coordinates(nodes.get(i).getCoord().getX() , nodes.get(i).getCoord().getY()));
                                break;
                        }
                        
                    }
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                System.out.println("File non trovato oppure non sono presenti i dati nel formato corretto.");

            } 
           
            System.out.println(" ");
            System.out.println("The following nodes are used: " + "\n");
        
            for (Node s : nodes) {
                map.addVertex(s);
                System.out.println(s.getStringCoord()); // prints the array elements
            }
        
            System.out.print("");
            for(DefaultWeightedEdge e : map.edgeSet()){
                System.out.println(map.getEdgeSource(e).getStringCoord() + " --> " + map.getEdgeTarget(e).getStringCoord()); //Print all the connections
            }
            System.out.println("Stampa delle direzioni sensate");
            for(Node e : nodes){
                System.out.println(e.getStringCoord() +" è il nodo considerato: ");
               e.printAllDirection();
            }

            
        }

        
}
