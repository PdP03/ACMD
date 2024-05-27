package com.ACMD.app.Engine_Layer.Mappa;

import java.util.NoSuchElementException;

import com.ACMD.app.Engine_Layer.StorageManagement.noItem_Exception;

/**
 * Class that rappresents Coordinates
 */
class Coordinates
{
    private int x,y;

    /**
     * 
     * @return the x value
     */
    public int getX() {
        return x;
    }
    /**
     * 
     * @param x sets the x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * @return the y value
     */    
    public int getY() {
        return y;
    }
    /**
     * 
     * @param y the y value 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return The format of the toString is ( x,y )
     * 
     */
    public String toString(){return "( "+x+","+y+" )";}

    /**
     * Sets the coordinates (x,y)
     * @param x the x value
     * @param y the y value
     */
    public Coordinates(int x,int y){this.x=x; this.y=y;}
    /**
     * Empty constructor for good habits 
     *  */ 
    public Coordinates(){}
}

/**
 * Classe che implementa un generico nodo della mappa. Di default i campi north, south, east e west sono null 
 * Nel momento in cui si usano i relativi getter e setter sono invece settati (ovviamente)
 */
public class Node{
    private Coordinates coord, north=null, south=null, east=null, west=null;
    private Stanza s; //Stanza associata al nodo
    public Node(int x,int y){this.coord = new Coordinates(x,y); }

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
    /**
     * 
     * @return Il nodo a nord rispetto a quello considerato 
     * @throws NoSuchElementException se il nodo non è presente
     */
    public Coordinates getNorth() throws NoSuchElementException{
        if (north==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
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
        if (south==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
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
        if (east==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
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
        if (west==null) throw new NoSuchElementException("Non è possibile dato che il nodo è nullo");
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
}
