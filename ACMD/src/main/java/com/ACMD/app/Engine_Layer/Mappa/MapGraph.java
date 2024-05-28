package com.ACMD.app.Engine_Layer.Mappa;



import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.jgrapht.graph.*;

import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;




//How about mettere statica nodes? 

public class MapGraph {
    public enum DIRECTION{NORTH, SOUTH, EAST, WEST}; 
    private SimpleDirectedWeightedGraph<NODE, DefaultWeightedEdge> map;
    private Coordinates[] directions;
    private List<NODE> nodes;
    private ArrayList<Stanza> chambers;
    /**
     * Initialize the graph "map" based on the informations of the given file 
     * @param path the path of the file
     */
    public MapGraph(String path){
            map = new SimpleDirectedWeightedGraph<NODE, DefaultWeightedEdge>(DefaultWeightedEdge.class); //Creazione di una mappa di nodi 
            directions = new Coordinates[4]; //{N,S,E;W} 
            nodes= new ArrayList<NODE>();
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
                        nodes.add(new NODE(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
                        map.addVertex(nodes.get(count++));
                    }
                    else
                    {
                        String theLine = file.nextLine();
                        String line[] = theLine.split(",|\\:|\\>"); //Split on "," or ":" or ">" 
                        //line ={x,y, x2, y2, direction1, direction2} where direction = north or south or east or west
                        int i=0,j=0;
                        boolean foundI=false,foundJ=false;
                        for(NODE n:nodes)
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
                                directions[0]=new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY());
                                break;
                            case "S": 
                                nodes.get(i).setSouth(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                directions[1]=new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY());
                                break;
                            case "E": 
                                nodes.get(i).setEast(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                directions[2]=new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY());
                                break;
                            case "O": 
                            case "W":
                                nodes.get(i).setWest(new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY()));
                                directions[3]=new Coordinates(nodes.get(j).getCoord().getX() , nodes.get(j).getCoord().getY());
                                break;
                        }
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
                            case "W":
                                nodes.get(j).setWest(new Coordinates(nodes.get(i).getCoord().getX() , nodes.get(i).getCoord().getY()));
                                break;
                        }
                        
                    }
                    //file.close();
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                System.out.println("File non trovato oppure non sono presenti i dati nel formato corretto.");

            } 
        }
    /**
     * If Harry Potter had this, we would't have the second movie.... 
     */
    public void inizalizeChambers()
    {
        chambers = new ArrayList<Stanza>(); 
        
    } 
    public Coordinates[] getDirections(Coordinates coord) throws NoSuchElementException{

        for(NODE s:nodes)
        {
            System.out.println("s:" + s.getCoord().getX() +" " + s.getCoord().getY() + " coord vale " + coord.getX() + " " + coord.getY());
            if(s.getCoord().getX() ==coord.getX() && s.getCoord().getY() == coord.getY() )
            {
               Coordinates[] a = new Coordinates[4]; 
               a[0] = s.getNorth();
               a[1] = s.getSouth();
               a[2] = s.getEast();  
               a[3] = s.getWest();
               return a;   
            }
            
        }
        throw new NoSuchElementException("Non esiste un nodo con quelle coordinate"); 
    }
    public SimpleDirectedWeightedGraph<NODE, DefaultWeightedEdge> getMap() {
        return map;
    }
    /**
     *  @return All the nodes 
     */
    public List<NODE> getNodes() {
        return nodes;
    }
    public void setMap(SimpleDirectedWeightedGraph<NODE, DefaultWeightedEdge> map) {
        this.map = map;
    }
    public void setDirections(Coordinates[] directions) {
        this.directions = directions;
    }
    public void setNodes(List<NODE> nodes) {
        this.nodes = nodes;
    }
   

    public boolean isValidDirectionTo(Coordinates c, DIRECTION dir) throws IOException
     {
        for(NODE s:nodes)
        {
            if(s.getCoord().getX() ==c.getX() && s.getCoord().getY() == c.getY() )
            {
                try{if(s.getDirection(dir) == null) return false; else{return true;}
                   }catch(IOException e ){throw new IOException("Input non valido");}
                   catch(NoSuchElementException e){return false;}
            }
        }
        return false; 
     }

    public Monster getMonsterAt(Coordinates cord){
            for(Stanza s:chambers)
            {
                if(s.getCoordinates() == cord ) return s.getMonster();
            }
            return null;
    }
    public Chest getChestAt(Coordinates cord){
        for(Stanza s:chambers)
        {
            if(s.getCoordinates() == cord ) return s.getChest();
        }
        return null;
    }
    public boolean isInChamber(Coordinates cord ){return true; }
        
    public void printAllNodes(){
        for(NODE s : nodes) System.out.println(s.getCoord());
    }

    
}

