package com.ACMD.app.Save_Layer;

import java.io.IOException;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.google.gson.JsonObject;


public class AdapterSalvataggio {
    public static void Save(Player player){
        JsonParser.save(player);
    }

    public static void Save (MapGraph map){
        JsonParser.save(map);
    }

    /*public void Save(Map mappa){
        JsonParser.WriteSavefile(mappa)
    }*/

    public static Player setPlayerFromFile() throws IOException{
        JsonObject j = JsonParser.ReadSavefile("Player");
        Player p = new Player(j.get("name").toString());
        return p;
    }
}
