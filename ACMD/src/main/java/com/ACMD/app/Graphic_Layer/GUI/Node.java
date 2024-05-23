package com.ACMD.app.Graphic_Layer.GUI;

/**
 * Class that rappresents Coordinates
 */
class Coordinates
{
    private int x,y;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return The format of the toString is ( x,y )
     * 
     */
    public String toString(){return "( "+x+","+y+" )";}

    public Coordinates(int x,int y){this.x=x; this.y=y;}
    public Coordinates(){}
}


public class Node{
    private Coordinates coord, north=null, south=null, east=null, west=null;
    private String icon; //Percorso del file con l'icona 
    public Node(int x,int y){this.coord = new Coordinates(x,y); }

    public Coordinates getCoord() {
        return coord;
    }
    /**
     * 
     * @return (x , y)
     */
    public String getStringCoord() {
        return "("+this.coord.getX()+" , "+this.getCoord().getY()+")";
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public Coordinates getNorth() {
        return north;
    }

    public void setNorth(Coordinates north) {
        this.north = north;
    }

    public Coordinates getSouth() {
        return south;
    }

    public void setSouth(Coordinates south) {
        this.south = south;
    }

    public Coordinates getEast() {
        return east;
    }

    public void setEast(Coordinates east) {
        this.east = east;
    }

    public Coordinates getWest() {
        return west;
    }

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
