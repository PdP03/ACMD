package com.ACMD.app.Engine_Layer.Mappa;


import com.ACMD.app.Engine_Layer.Entita.Monster; 
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;


public class Stanza
{
    Coordinates coord; 
    Monster monster; 
    Chest chest; 
    String imagePath;
    boolean randomize; 

    public void setChest(Chest c){this.chest=c;}
    public Chest getChest(){return chest;}
    public void setCoordinates(Coordinates c){this.coord=c;}
    public Coordinates getCoordinates(){return coord;}
    public Monster getMonster(){return monster;}
    public void setImagePath(String s){imagePath=s;}
    /**
     * Costruttore con tutti i parametri 
     */
    public Stanza(Coordinates c, Monster m, Chest chest, String path)
    {
        this.monster = m; 
        this.coord = c; 
        this.chest = chest; 
        this.imagePath = path; 
    }
    /**
     * Empty constructor, only for good habits
     */
    public Stanza(){}
    /**
     * 
     * @param randomize To create a random chamber 
     */
    public Stanza(boolean randomize){}



}