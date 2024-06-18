package com.ACMD.app.Engine_Layer.GameEngine;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Save_Layer.JsonParser;

public class testParser {
    public static void main(String[] args){
        MapGraph m = new MapGraph();
        Player p = new Player("fa");
        //JsonParser.save(p, m);
        JsonParser.read(MapGraph.class);
    }
    
}
