package com.ACMD.app.Engine_Layer.Mappa;


import com.ACMD.app.Engine_Layer.Entita.Monster; 
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;


public class Stanza extends NODE
{
    Coordinates playerPosition = new Coordinates(1,19); 
    Monster monster; 
    Chest chest; 
    boolean isFree=false;
    boolean isRoom = true;

    public boolean isFree(){return isFree;}
    public void setFree(){isFree=true;}

    public void setChest(Chest c){this.chest=c;}
    public Chest getChest(){return chest;}
    public void setCoordinates(Coordinates c){this.coord=c;}
    public Coordinates getCoordinates(){return coord;}
    public Monster getMonster(){return monster;}
    @Override
    public boolean isRoom(){return isRoom;}
    @Override
    public boolean isStanza(){return isRoom;}
    public Coordinates getPlayerPosition(){return playerPosition;}

    
    /**
     * Costruttore con tutti i parametri 
     */
    public Stanza(Coordinates c, Monster m, Chest chest)
    {   
        this.monster = m; 
        this.coord = c; 
        this.chest = chest;  
    }
    public Stanza(Coordinates c, Coordinates player, Monster m, String path )
    {   
        this.monster = m; 
        this.coord = c; 
        this.playerPosition = player; 
        this.pathImg=path;   
    }
    public Stanza(Coordinates c, Coordinates player, Monster m, String path, Chest chest)
    {   
        this.monster = m; 
        this.coord = c; 
        this.playerPosition = player; 
        this.pathImg=path;   
        this.chest=chest;
    }

    public Stanza(Coordinates c, boolean isFree, Chest chest )
    {
        this.coord = c; 
        this.isFree = isFree; 
        this.chest = chest; 
    }

    /**
     * Empty constructor, only for good habits
     */
    public Stanza(){}
}