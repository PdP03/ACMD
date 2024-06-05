package com.ACMD.app.Engine_Layer.Mappa;

/**
 * Class that rappresents Coordinates
 */
public class Coordinates
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

    @Override 
    public boolean equals(Object cc)
    {
        Coordinates c= (Coordinates) cc;
        return c.getX()==this.getX() && c.getY()==this.getY();
    }
}
