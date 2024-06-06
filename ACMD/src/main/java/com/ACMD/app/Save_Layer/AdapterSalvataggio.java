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

    /*public void Save(Map mappa){
        JsonParser.WriteSavefile(mappa)
    }*/

    public static Player retrivePlayer() throws IOException{
        Player p;
        JsonObject j = JsonParser.read(p);
        p.setName(j.get("name").toString());
        return p;
    }
}
