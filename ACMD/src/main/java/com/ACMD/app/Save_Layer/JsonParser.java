package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;

import com.google.gson.*;

public class JsonParser {

    private static final String FILE_NAME = "Savefile.json";
    private static Gson gson;

    /**
     * Save the object to a JSON file.
     * 
     * @param obj The object to save.
     */
    public static void save(Player player, MapGraph map) {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Player.class, new PlayerSerializer())
                .registerTypeAdapter(MapGraph.class, new MapGraphSerializer())
                .create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            JsonObject jsonObj = new JsonObject();

            jsonObj.add(player.getClass().getSimpleName(), gson.toJsonTree(player));
            jsonObj.add(map.getClass().getSimpleName(), gson.toJsonTree(map));

            gson.toJson(jsonObj, writer);
            File file = new File(FILE_NAME);
            awsClient.Upload(file);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read the object from the JSON file.
     * 
     * @param <T>  The type of the object to read.
     * @param type The class of the object to read.
     * @return The object read from the JSON file.
     */

    public static <T> T read(Class<T> type) {
        gson = new Gson();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);
            T obj = gson.fromJson(jsonObj.get(type.getSimpleName()), type);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class PlayerSerializer implements JsonSerializer<Player> {
        @Override
        public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject object = new JsonObject();
            object.addProperty("name", player.getName());
            object.addProperty("level", player.getLv());
            object.addProperty("life", player.getLife());
            object.addProperty("attack", player.getAttack());
            object.addProperty("armor", player.getArmor());
            object.addProperty("inventory", player.showInv());
            return object;
        }
    }

    private static class MapGraphSerializer implements JsonSerializer<MapGraph> {
        @Override
        public JsonElement serialize(MapGraph map, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject object = new JsonObject();
            object.addProperty("coordinates", map.getPlayerPos().toString());
            object.addProperty("chambers", map.getChambers().toString());
            return object;
        }
    }

}
