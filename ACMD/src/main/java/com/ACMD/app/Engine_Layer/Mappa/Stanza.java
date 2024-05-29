package com.ACMD.app.Engine_Layer.Mappa;


import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Entita.Monster; 
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;


public class Stanza extends NODE
{
    Coordinates playerPosition; 
    MType monster; 
    Chest chest; 
    

    public void setChest(Chest c){this.chest=c;}
    public Chest getChest(){return chest;}
    public void setCoordinates(Coordinates c){this.coord=c;}
    public Coordinates getCoordinates(){return coord;}
    public MType getMonster(){return monster;}

    
    /**
     * Costruttore con tutti i parametri 
     */
    public Stanza(Coordinates c, MType m, Chest chest)
    {   
        this.monster = m; 
        this.coord = c; 
        this.chest = chest;  
    }
    public Stanza(Coordinates c, Coordinates player, MType m, String path )
    {   
        this.monster = m; 
        this.coord = c; 
        this.playerPosition = player; 
        this.pathImg=path;   
    }
    /**
     * Empty constructor, only for good habits
     */
    public Stanza(){}
}