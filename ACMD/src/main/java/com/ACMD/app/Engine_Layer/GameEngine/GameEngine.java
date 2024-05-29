package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.ArrayList;

import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;

public class GameEngine{
    Player p;
    MapGraph map;
    String buffer;

    /**
     * Costruttore di default
     */
    public GameEngine(){
        p = null;
        map = null;
        buffer = null;
    }

    /**
     * Metodo che fa partire il game engine inizializzato player, mappa
     */
    public void runSetup(String playerName){
        p = new Player(playerName);
        map = new MapGraph();
        buffer = "";

        //aggiunta degli observer(Monster) a player
        ArrayList<Monster> list = map.getAllMonster();
        for(Monster m: list){
            p.addObserver(m);
        }
    }

}