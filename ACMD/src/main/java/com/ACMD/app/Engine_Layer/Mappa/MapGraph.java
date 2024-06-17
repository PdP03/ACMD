package com.ACMD.app.Engine_Layer.Mappa;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import org.jgrapht.graph.*;

import com.ACMD.app.Engine_Layer.RoomValues;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.MonsterFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;


public class MapGraph {
    // =========
    // Variabili
    // =========
    Random randomGen;
    ItemFactory itemFacry;
    private SimpleDirectedWeightedGraph<NODE, DefaultWeightedEdge> map;
    private Coordinates[] directions;
    private static ArrayList<NODE> nodes;
    private static ArrayList<Stanza> chambers; //TODO: Attenzione che lo ho messo statico 
    private Coordinates PlayerPosition =new Coordinates(1,19); //default player postion  
    public static int keys=0;
    public static final int dragonX=12; //sono obbligato a usare static perchè il metodo che aggiunge il drago è static 
    public static final int dragonY=18;

    private static final int toTheDragonX=8; //sono obbligato a usare static perchè il metodo che aggiunge il drago è static 
    private static final int toTheDragonY=13;
    /**
     * 
     * @param of The node you want the path image of (note: pathimage is not from the root. It is just the name of the file in the "imges" folder)
     * @return The path 
     * @throws NoSuchElementException if that node is not in the map 
     */
    public static String getPathImage(Coordinates of) throws NoSuchElementException
    {
        for(NODE s: nodes)
        {
            if(s.getCoord().getX() == of.getX() && s.getCoord().getY()==of.getY())
            return s.pathImg;
        }
        throw new NoSuchElementException("Immagine del nodo" +of.getX()+","+of.getY()+"non trovata");
    }
    /**
     * Ritorna la posizione del player in una stanza
     * @param c Le coordinate
     * @return La posizione del player
     */
    public static Coordinates getPlayerPositionOf(Coordinates c)
    {
        for(Stanza s:chambers) 
        {
            if(s.getPlayerPosition().getX() == c.getX() && s.getPlayerPosition().getY() == c.getY() )
                return s.playerPosition;
          
        }
        //throw new NoSuchElementException("Non verrò mai lanciata");
        return c; 
    }
    /**
     * Costruttore che prende direttamente l'xml
     */
    public MapGraph()
    {   
        itemFacry = new ItemFactory();
        randomGen = new Random(System.currentTimeMillis());
        chambers = new ArrayList<>();
        MonsterFactory factory = new MonsterFactory();
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Mappa\\";
        xmlReader reader = new xmlReader(entityDir, "MappaConfig.xml");

        
        // ========================
        //      Aggiunta NODI
        // ========================
        nodes = reader.getAllNode();
        ArrayList<RoomValues> rooms = reader.getAllRoom(); 

        //Aggiunta STANZE
        Chest ches;
        for(RoomValues r:rooms)
        {
            ches = getRandomChest();
            // Coordinate stanza , coord player, mostro, percorso
            nodes.add(new Stanza( new Coordinates(r.StanzaX, r.StanzaY), new Coordinates(r.PlayerX, r.PlayerY), factory.create(r.mtype), r.path, ches)); //Coordinate, 
            chambers.add(new Stanza( new Coordinates(r.StanzaX, r.StanzaY), new Coordinates(r.PlayerX, r.PlayerY), factory.create(r.mtype), r.path, ches));
        }
       // prinAllisRoom();
    }

    public MapGraph(int key, ArrayList<Stanza> chambers){
        this();             //Calls the default constructor
        MapGraph.keys = key;
        nodes.removeAll(MapGraph.chambers);
        MapGraph.chambers = chambers;
        nodes.addAll(chambers);
    }

    /**
     * Crea una chest con elementi random al max contine 3 elementi
     */

    private Chest getRandomChest(){
        Chest c = new Chest();
        int maxItem = randomGen.nextInt(3);
        /* c.add(itemFacry.getItem(ItemType.POZIONE_CURA)); //TODO: DA TOGLIERE SOLO PER TEST
        c.add(itemFacry.getItem(ItemType.POZIONE_FORZA)); //TODO: DA TOGLIERE SOLO PER TEST */
        for(int i=0; i<maxItem; i++){
            c.add(itemFacry.getRandomItem());
        }

        return c;
    }

    public int getKeys(){
        return keys;
    }

    public Coordinates getPlayerPos(){
        //System.out.println("GetPlayer position è stato chiamato con "+PlayerPosition);
        return PlayerPosition;
    }

    public void setFreeRoomAt(Coordinates c){
        for(NODE n:nodes)
        {
            if(n.getCoord().getX() == c.getX() && n.getCoord().getY()==c.getY())
            setBeaten(new Coordinates(c.getX(), c.getY())); 
        }
    }
    /*
     * è stata liberata dal mostro
     * nella stanza a cordinate c
     * 
     */
    public boolean isFreeRoomAt(Coordinates c){
        for(NODE s: nodes)
        {
            if(s.getCoord().getX() == c.getX() && s.getCoord().getY() == c.getY())
                return s.getPathImage().contains("Open"); 
        }
        throw new NoSuchElementException("Non esiste il nodo in cui invochi isFreeRoomAt");
    }

    /*

     */
    public void setPlayerPos(Coordinates c){
        PlayerPosition = c; 
    }

    /*
     * 
     * dalla posizione attuale del player. Lancia un eccezzione se Direction non è
     * una direzione possibile del nodo
     */
    public void movePlayerTo(Direction dir) throws IllegalArgumentException{
        for(NODE n: nodes)
        {
            
            if(n.getCoord().getX() == PlayerPosition.getX() && n.getCoord().getY() == PlayerPosition.getY())
            {
                
                switch (dir) {
                    case NORTH:
                        PlayerPosition = n.getNorth();
                        return;
                    case SOUTH:
                        PlayerPosition = n.getSouth();
                        return;
                    case EAST:
                        PlayerPosition = n.getEast();
                        return;
                    case WEST:
                        PlayerPosition = n.getWest();
                        return;
                    default:
                        break;
                }
            }
        }

    }

    /*
     * TODO: metodo che restituisce una lista di Direction che contengon l'enum
     * es ArrayList può contenere Direction.NORD, Direction.SUD
     */
    public ArrayList<Direction> validDirectionOf(Coordinates n){
        ArrayList<Direction> dir=new ArrayList<Direction>(4); 
        for(NODE s: nodes)
        {
            if(n.getX() == s.getCoord().getX() && n.getY()==s.getCoord().getY())
            {
                if(s.getNorth()!=null) dir.add(Direction.NORTH);
                if(s.getSouth()!=null) dir.add(Direction.SOUTH);
                if(s.getEast()!=null) dir.add(Direction.EAST);
                if(s.getWest()!=null) dir.add(Direction.WEST);          
            }
            }
            return dir;        
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
            //====================
            //  LETTURA DEL FILE
            //====================
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
                        if(theLine.equals("Connessioni")){addingMode=false; continue;} // Finiti i nodi 
                        String line[] = theLine.split(",");  //Separa
                        nodes.add(new NODE(Integer.parseInt(line[0]), Integer.parseInt(line[1]))); //Aggiungi
                        map.addVertex(nodes.get(count++)); //Aggiungi il vertice
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
                        // ================================
                        //  SETTING DELLE DIREZIONI A-> B
                        // =================================
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
                         // ================================
                        //  SETTING DELLE DIREZIONI B->A
                        // =================================
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
                    file.close();
                }
            } catch (IOException e1) {
                System.out.println("File non trovato oppure non sono presenti i dati nel formato corretto.");

            } 
        } 
    /**
     * 
     * @return Tutti i mostri
     */
    public ArrayList<Monster> getAllMonster(){
        ArrayList<Monster> list = new ArrayList<Monster>();
        for(Stanza s: chambers)
            {
                list.add(s.getMonster());
            }

        return list;
    }


    /**
     * 
     * @param coord Le coordinate del player
     * @return Array di coordinate dove può muoversi
     * @throws NoSuchElementException Se non c'è il nodo
     */
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
   
    /**
     * 
     * @param c Il nodo
     * @param dir La direzione
     * @return Se la direzione è disponibile
     * @throws IOException
     */
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

     /**
      * 
      * @param cord le coordinate
      * @return Il mostro lì 
      */
    public Monster getMonsterAt(Coordinates cord){
        Coordinates c;    
        for(Stanza s:chambers)
            {
                c = s.getCoordinates();
                if(c.getX() == cord.getX() && c.getY() == cord.getY() ) return s.getMonster();
            }
            return null;
    }
    /**
     * 
     * @param cord Le coordinate
     * @return La cassa
     */
    public Chest getChestAt(Coordinates cord){
        Coordinates c; 
        for(Stanza s:chambers)
        {
            c = s.getCoordinates();
            if(c.getX() == cord.getX() && c.getY() == cord.getY()){ 
                return s.getChest();
            }
        }
        return null;
    }
    /**
     * Usato per praticità in fase di test
     * @param cord 
     * @return
     */
    public boolean isInChamber(Coordinates cord ){return true; }
    /**
     * Metodo usato per praticità durante dei test, di questo metodo non è presente il relativo @test */     
    public void printAllNodes(){
        for(NODE s : nodes) System.out.println(s.getCoord());
    }

    /**
     * 
     * @param coord Il nodo
     * @return il percorso della sua immagine
     * @throws NoSuchElementException Se il nodo non è presente
     */
    public static String getIconOf(Coordinates coord) throws NoSuchElementException
    {
        for(NODE s : nodes)
        {
            if(s.getCoord().getX() == coord.getX() && s.getCoord().getY() == coord.getY() ) return s.getPathImage();
        }
        throw new NoSuchElementException("Elemento non trovato");
    }
    /**
     * 
     * @param coord Le coordiante
     * @return Se il nodo è una stanza
     */
    public static boolean isStanza(Coordinates coord) //throws NoSuchElementException
    {
       
        for(Stanza s : chambers)
        {
            
            if(s.getPlayerPosition().getX() == coord.getX() && s.getPlayerPosition().getY() == coord.getY() || s.getCoord().getX() == coord.getX() && s.getCoord().getY() == coord.getY() ) return s.isStanza();
        }
        return false;
       // throw new NoSuchElementException("Nodo non presente");
    }

    public ArrayList<Stanza> getChambers(){return chambers;}
    
    /**
     * Usato in fase di test
     */
    public static void printAllIcons()
    {
        for(NODE s: nodes ) System.out.println("Il nodo" + s.getCoord()+ " ha "+ s.pathImg);
    }
    /**
     * Usato in fase di test
     * @return
     */
    public static String getAllIcons()
    {
        String string="";
        for(NODE s: nodes ) { string+="Il nodo" + s.getCoord()+ " ha "+ s.pathImg+"\n";}
        return string;
    }
    /**
     * Aggiorna la mappa aggiungendo "Open" al suo percorso
     * @param coord Il nodo di riferimento 
     */
    public static void setBeaten(Coordinates coord)
    {
       for(NODE s : nodes)
        {
            if(s.getCoord().getX() == coord.getX() && s.getCoord().getY() == coord.getY() )
            {
                if(s.pathImg.contains("Open")) return;                                     
                s.pathImg=s.pathImg.substring(0,s.pathImg.length()-4);
                s.pathImg+="Open.png";
                keys++; 
                //System.out.println(s.pathImg);
            }
        }
    }
    /**
     * Aggiorna la mappa mettendo il cancello aperto e permettendo di sfidare il drago */ 
    public static void setDragon()
    {
        //Cambia tutti i percorsi della mappa mettendo quelli con il drago
        for(NODE s:nodes)
        {
            if(s.pathImg.contains("Definitiva"))
            {
                s.pathImg="Mappa_Drago.png";
            }                
        
        }
         //Aggiungo il drago
         for(NODE s:nodes)
         {
            if(s.getCoord().getX() == toTheDragonX && s.getCoord().getY() == toTheDragonY)
            {
                s.setSouth(new Coordinates(dragonX, dragonY));
            }
         }
    }
    /**
     * Usato in fase di test
     */
    public static void printAllDirection()
    {
        for(NODE n: nodes)
        {
            System.out.println("NODO"+n.getCoord());
            if(n.getNorth()!=null) System.out.println("\tA nord ha"+n.getNorth());
            if(n.getSouth()!=null) System.out.println("\tA sud ha"+n.getSouth());
            if(n.getEast()!=null) System.out.println("\tA est ha"+n.getEast());
            if(n.getWest()!=null) System.out.println("\tA West ha"+n.getWest());
        }
    }
    /**
     * Usato in fase di test
     */
    public void printAllPlayerPosition()
    {
        for(Stanza n: chambers)
        {
            System.out.println("Il nodo " + n.getCoord().getX() +" " +n.getCoord().getY() + " ha " + n.playerPosition.getX() + " "+n.playerPosition.getY());
        }
    }
    /**
     * Usato in fase di test
     */
    public void prinAllisRoom()
    {
        for(NODE n:nodes)
        {
            System.out.println("Il nodo"+ n.getCoord().toString()+" è stanza: "+n.isStanza());
        }
    }
    
}

