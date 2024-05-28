package com.ACMD.app.Engine_Layer.Mappa;

import java.io.IOException;
import java.util.NoSuchElementException;

import com.ACMD.app.Engine_Layer.Mappa.MapGraph.DIRECTION;
import com.ACMD.app.Engine_Layer.StorageManagement.noItem_Exception;

/**
 * Classe che implementa un generico nodo della mappa. Di default i campi north, south, east e west sono null 
 * Nel momento in cui si usano i relativi getter e setter sono invece settati (ovviamente)
 */
public class NODE{
    private Coordinates coord, north=null, south=null, east=null, west=null;
    private Stanza s; //Stanza associata al nodo
    private String pathImg;
    private boolean isRoom;
    public NODE(int x,int y){this.coord = new Coordinates(x,y); pathImg = ""; isRoom = false;}

    public Coordinates getCoord() {
        return coord;
    }
    /**
     * 
     * @return Il formato del return è (x , y) attenzione agli spazi tra x e , 
     */
    public String getStringCoord() {
        return "("+this.coord.getX()+" , "+this.getCoord().getY()+")";
    }
    /**
     * Setta il valore delle coordinate fornite 
     * @param coord le coordinate fornite
     */
    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public void setPathImage(String s) {
        pathImg = s;
    }

    public void setIsRoom(boolean b) {
        isRoom = b;
    }

    public boolean isRoom(){
        return isRoom;
    }

    public String getPathImage(){
        return pathImg;
    }

    /**
     * 
     * @return Il nodo a nord rispetto a quello considerato 
     * @throws NoSuchElementException se il nodo non è presente
     */
    public Coordinates getNorth() throws NoSuchElementException{
        return north;
    }
    /**
     * 
     * @param north Il nodo a nord rispetto a quello considerato 
     */
    public void setNorth(Coordinates north) {
        this.north = north;
    }
    /**
     * 
     * @return  Il nodo a sud rispetto a quello considerato 
     * @throws NoSuchElementException se il nodo non è presente
     */
    public Coordinates getSouth() throws NoSuchElementException{
     //   if (south==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
        return south;
    }
    /**
     * 
     * @param south  Il nodo a nord rispetto a quello considerato 
     */
    public void setSouth(Coordinates south) {
        this.south = south;
    }
        /**
         *   
         * @return Il nodo a est rispetto a quello considerato
         * @throws NoSuchElementException se il nodo non è presente
         */
    public Coordinates getEast() throws NoSuchElementException {
      //  if (east==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
        return east;
    }
    /**
     * 
     * @param east Il nodo a est rispetto a quello considerato
     */
    public void setEast(Coordinates east) {
        this.east = east;
    }
    /**
     * 
     * @return Il nodo a ovest rispetto a quello considerato
     * @throws NoSuchElementException se il nodo non è presente
     */
    public Coordinates getWest() throws NoSuchElementException{
       // if (west==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
        return west;
    }
    /**
     * 
     * @param west Il nodo a ovest rispetto a quello considerato
     * @throws NoSuchElementException se il nodo non è presente
     */
    public void setWest(Coordinates west) {
        this.west = west;
    }
    /**
     * Prints all the non null nodes the node can go to 
     * */ 
    public void printAllDirection()
    {
        if(north!=null) System.out.println("Nord: "+getNorth().toString());
        if(south!=null) System.out.println("Sud: "+getSouth().toString());
        if(east!=null) System.out.println("Est: "+getEast().toString());
        if(west!=null) System.out.println("Ovest: "+getWest().toString());
    }
    /**
     * 
     * @param direction The direction
     * @return the node at the direction 
     * @throws IOException thrown if direction is not N/S/E/W/O O stands for "Ovest"
     */
    public Coordinates getDirection(DIRECTION direction) throws IOException, NoSuchElementException{
        switch (direction) {
         case NORTH:
            if (north==null){ System.out.println("North null"); throw new NoSuchElementException("North is null");} 
            return north;        
         case SOUTH: 
         if (south==null) {System.out.println("S null");throw new NoSuchElementException("South is null"); }
            return south; 
        case EAST: 
        if (east==null) { System.out.println("E null"); throw new NoSuchElementException("East is null");} 
        return east; 
        case WEST:
        if (west==null) {System.out.println("W null");throw new NoSuchElementException("West is null"); }
            return west; 
        default: 
        throw new IOException("Input non valido"); 
        }
     }
     
}
