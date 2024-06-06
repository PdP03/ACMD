package com.ACMD.app.Save_Layer;

import java.io.IOException;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.google.gson.JsonObject;


public class AdapterSalvataggio {
    public static void save(Player player){
        JsonParser.save(player);
    }

    public static void save (MapGraph map){
        JsonParser.save(map);
    }

    public static Player retrivePlayer(){
        Player player = JsonParser.read(Player.class);
        return player;
    }

    public static MapGraph retriveMap(){
        MapGraph map = JsonParser.read(MapGraph.class);
        return map;
    }

}
