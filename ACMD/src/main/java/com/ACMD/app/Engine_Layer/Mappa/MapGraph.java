package com.ACMD.app.Engine_Layer.Mappa;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.jgrapht.graph.*;

import com.ACMD.app.Engine_Layer.RoomValues;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.MonsterFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;



public class MapGraph {
    private SimpleDirectedWeightedGraph<NODE, DefaultWeightedEdge> map;
    private Coordinates[] directions;
    private static ArrayList<NODE> nodes;
    private ArrayList<Stanza> chambers;
    private ArrayList<Coordinates> playerRoute; 
    private Coordinates PlayerPosition =new Coordinates(1,19); //default player postion  

    /**
     * Costruttore che prende direttamente l'xml
     */
    public MapGraph()
    {   
        chambers = new ArrayList<>();
        MonsterFactory factory = new MonsterFactory();
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Mappa\\";
        xmlReader reader = new xmlReader(entityDir, "MappaConfig.xml");

        nodes = reader.getAllNode();
        ArrayList<RoomValues> rooms = reader.getAllRoom(); 
       
        for(RoomValues r: rooms)
        {    
            Stanza s = new Stanza( new Coordinates(r.x, r.y), new Coordinates(r.posx, r.posy), factory.create(r.mtype), r.path);
            
            System.out.print(s);
            
            chambers.add(s);
            //String s = r.path;
            System.out.println(r.x+ " "+ r.y);
        }

        for(RoomValues r:rooms)
        {
            chambers.add(new Stanza( new Coordinates(r.x, r.y), new Coordinates(r.posx, r.posy), factory.create(r.mtype), r.path)); //Coordinate, 
            System.out.println("add");
        }
        if(rooms != null)
            System.out.println(rooms.size());
        Coordinates cord;

        //Aggiunta NODI
        for(NODE n: nodes){
            cord = n.getCoord();
        }
        //Aggiunta STANZE
        for(RoomValues r:rooms)
        {
            
            nodes.add(new Stanza( new Coordinates(r.x, r.y), new Coordinates(r.posx, r.posy), new MonsterFactory().create(r.mtype), r.path  )); //Coordinate, 
        }
        for(NODE n:nodes){
            //n.printAllDirection();
        }

    }

    public Coordinates getPlayerPos(){
        return PlayerPosition;
    }


    //TODO: metodo che setta a true la variabile freeRoom nella staza a cordinate c
    public void setFreeRoomAt(Coordinates c){

    }
    /*
     * TODO: implementare un metodo che restituisce true o false se la stanza
     * è stata liberata dal mostro
     * nella stanza a cordinate c
     * 
     */
    public boolean isFreeRoomAt(Coordinates c){
        return false;
    }

    /*
     * TODO: metodo che cambia le cordinate del Player
     */
    public void setPlayerPos(Coordinates c){

    }

    /*
     * TODO: metodo che sposta il player verso la direzione specificata partendo
     * dalla posizione attuale del player. Lancia un eccezzione se Direction non è
     * una direzione possibile del nodo
     */
    public void movePlayerTo(Direction dir) throws IllegalArgumentException{

    }

    /*
     * TODO: metodo che restituisce una lista di Direction che contengon l'enum
     * es ArrayList può contenere Direction.NORD, Direction.SUD
     */
    public ArrayList<Direction> validDirectionOf(Coordinates n){
        return new ArrayList<Direction>(4);
    }




    /**
     * Initialize the graph "map" based on the informations of the given file 
     * @param path the path of the file
     * note: E' oblsoleto 
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
                System.out.println("File non trovato oppure non sono presenti i dati nel formato corretto.");

            } 
        } 
    
    public ArrayList<Monster> getAllMonster(){
        ArrayList<Monster> list = new ArrayList<Monster>();
        for(Stanza s: chambers)
            {
                list.add(s.getMonster());
            }

        return list;
    }



    public Coordinates[] getDirections(Coordinates coord) throws NoSuchElementException{

        for(NODE s:nodes)
        {
           // System.out.println("s:" + s.getCoord().getX() +" " + s.getCoord().getY() + " coord vale " + coord.getX() + " " + coord.getY());
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
    public void setNodes(ArrayList<NODE> nodes) {
        this.nodes = nodes;
    }
   

    public boolean isValidDirectionTo(Coordinates c, Direction dir) throws IOException
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

    public static String getIconOf(Coordinates coord) throws NoSuchElementException
    {
        for(NODE s : nodes)
        {
            System.out.println(s.getCoord().getX() + " "+ s.getCoord().getY());
            if(s.getCoord().getX() == coord.getX() && s.getCoord().getY() == coord.getY() ) return s.getPathImage();
        }
        throw new NoSuchElementException("Elemento non trovato");
    }
    public boolean isStanza(Coordinates coord) throws NoSuchElementException
    {
        for(NODE s : nodes)
        {
            
            if(s.getCoord().getX() == coord.getX() && s.getCoord().getY() == coord.getY() ) return s.isRoom();
        }
        throw new NoSuchElementException("Elemento non trovato");
    }
    public static void printAllIcons()
    {
        for(NODE s: nodes ) System.out.println("Il nodo" + s.getCoord()+ " ha "+ s.pathImg);
    }

    
}

