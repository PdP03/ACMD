package com.ACMD.app.Graphic_Layer.GUI;

/**
 * Class that rappresents Coordinates
 */
public class Coordinates
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


