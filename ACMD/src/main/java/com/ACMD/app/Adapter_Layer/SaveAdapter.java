package com.ACMD.app.Adapter_Layer;

import java.io.IOException;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Save_Layer.awsClient;
import com.ACMD.app.Save_Layer.JsonParser;
import com.google.gson.JsonObject;

/**
 * This class is used to save and retrieve the player and the map from the Json file.
 */

public class SaveAdapter
{
    /**
     * This method saves the player to the Json file.
     * @param player The player to save.
     */

    public static void save(Player player){
        JsonParser.save(player);
    }

    /**
     * This method saves the map to the Json file.
     * @param map The map to save.
     */

    public static void save (MapGraph map){
        JsonParser.save(map);
    }
    
    /**
     * This method retrieves the player from the Json file.
     * @return The player.
     */

    public static Player retrivePlayer(){
        Player player = JsonParser.read(Player.class);
        return player;
    }
    
    
    /**
     * This method retrieves the map from the Json file.
     * @return The map.
     */
    public static MapGraph retriveMap(){
        MapGraph map = JsonParser.read(MapGraph.class);
        return map;
    }
    /**
     * This method retrieves the name of the files uploaded to the S3 bucket.
     * @return The list of the names of the files uploaded to the S3 bucket.
     */

    public static List<String> GetUploadedFiles(){
        return awsClient.GetUploadedFiles();
    }
}
