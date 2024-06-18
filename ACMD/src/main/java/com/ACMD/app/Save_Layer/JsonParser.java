package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.MonsterFactory;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Engine_Layer.Mappa.Stanza;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;
import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * The JsonParser class provides methods to save and read objects to/from a JSON file.
 * It uses the Gson library for JSON serialization and deserialization.
 */
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
                .registerTypeAdapter(Player.class, new PlayerAdapter())
                .registerTypeAdapter(MapGraph.class, new MapGraphAdapter())
                .create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            JsonObject jsonObj = new JsonObject();

            jsonObj.add(player.getClass().getSimpleName(), gson.toJsonTree(player));
            jsonObj.add(map.getClass().getSimpleName(), gson.toJsonTree(map));

            gson.toJson(jsonObj, writer);
            writer.close();
            File file = new File(FILE_NAME);
            awsClient.Upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel salvataggio del file");
        } catch (NullPointerException e) {
            System.err.println("MapGraph or Player object is null");
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
        gson = new GsonBuilder()
        .registerTypeAdapter(Player.class, new PlayerAdapter())
        .registerTypeAdapter(MapGraph.class, new MapGraphAdapter())
        .create();
        try (JsonReader reader = new JsonReader(new FileReader(FILE_NAME))) {
            T obj = gson.fromJson(reader, type);
            reader.close();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//---------------------------------- Player TypeAdapter ----------------------------------

    private static class PlayerAdapter extends TypeAdapter<Player> {
        /**
         * Reads a Player object from the provided JsonReader.
         *
         * @param reader The JsonReader object to read from.
         * @return The Player object read from the JsonReader.
         * @throws IOException If an I/O error occurs while reading from the JsonReader.
         * @throws IllegalArgumentException If the required parameters for creating a Player object are missing.
         */
        @Override
        public Player read(JsonReader reader) throws IOException {
            String name = null;
            Inventario inv = null;
            byte lv = -1;
            short health = -1;

            reader.beginObject();
            while (reader.hasNext()) {
                String tName = getTokenName(reader);
                if(tName.equals("MapGraph")){
                    reader.skipValue();
                    continue;
                }
                if(reader.peek().equals(JsonToken.BEGIN_OBJECT)){
                    reader.beginObject();

                }
                switch (tName) {
                    case "name":
                        name = reader.nextString();
                        break;
                    case "Life":
                        health = (short) reader.nextInt();
                        break;

                    case "Level":
                        lv = (byte) reader.nextInt();
                        break;
                    case "Inventory":
                        inv = readInventario(reader);
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
            if (name != null && lv > 0 && health > 0) {
                return new Player(name, lv, health, inv);
            }
        
            throw new IllegalArgumentException("errore nella lettura mancano dei parametri di player");
        }

        /**
         * Permette di ottenere il nome del token
         * 
         * @param reader
         * @return String nome del token
         * @throws IllegalArgumentException lanciata se il reader non ha un Nome ma un
         *                                  valore di una filed
         */
        private String getTokenName(JsonReader reader) throws IllegalArgumentException {
            try {
                if (reader.peek().equals(JsonToken.NAME)) {
                    return reader.nextName();
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Errore nella lettura");
            }

            throw new IllegalArgumentException("Questo non è un nome ma un valore");
        }

        /**
         * Metodo che legge il token inventario dal file json
         * 
         * @param reader JsonReader
         * @return Inventario inizializzato con gli oggetti letti
         * @throws IOException
         */
        private Inventario readInventario(JsonReader reader) throws IOException {
            String name = null;
            ItemType type = null;
            int weight = -1, quantity = -1, value = -1;
            Inventario inv = new Inventario();
            reader.beginArray();
            while (reader.hasNext()) {
                if (reader.peek().equals(JsonToken.BEGIN_OBJECT)) {
                    reader.beginObject();
                }
                if (reader.peek().equals(JsonToken.NAME)) {
                    String tName = reader.nextName();
                    switch (tName) {
                        case "name":
                            name = reader.nextString();
                            break;
                        case "tipology":
                            type = ItemType.valueOf(reader.nextString());
                            break;
                        case "weight":
                            weight = reader.nextInt();
                            break;
                        case "quantity":
                            quantity = reader.nextInt();
                            break;
                        case "value":
                            value = reader.nextInt();
                            break;
                    }
                }

                if (reader.peek().equals(JsonToken.END_OBJECT)) {
                    ItemStack it = new ItemStack(name, type, (byte) weight, (byte) quantity, (byte) value, "");
                    inv.add(it);
                    reader.endObject();
                }
            }
            reader.endArray();
            return inv;
        }

        /**
         * Writes the player object to a JSON writer.
         *
         * @param writer The JSON writer to write the player object to.
         * @param player The player object to be written.
         * @throws IOException If an I/O error occurs while writing to the JSON writer.
         */
        @Override
        public void write(JsonWriter writer, Player player) throws IOException {
            writer.beginObject();
            writer.name("name");
            writer.value(player.getName());
            writer.name("Life");

            writer.value(player.getLife());
            writer.name("Level");
            writer.value(player.getLv());
            writer.name("Inventory");
            writeInvetory(writer, player.getInv());
            writer.endObject();
        }

        /**
         * Metodo di utilita che scrive nel Json gli oggetti del Inventario
         * 
         * @param writer
         * @param inv    Inventario da scrivere nel json
         * @throws IOException nel caso di errore IO
         */
        private void writeInvetory(JsonWriter writer, Inventario inv) throws IOException {
            writer.beginArray();
            writeStorageItems(writer, inv.showStorage());
            writer.endArray();
        }

        /**
         * Metodo di utilità che scrive nel Json tutti gli elementi di Storage
         * 
         * @param writer
         * @param storage LinkedList<ItemStack>
         */
        private void writeStorageItems(JsonWriter writer, LinkedList<ItemStack> storage) throws IOException {

            for (ItemStack item : storage) {
                //writer.name("Item");
                writer.beginObject();
                writer.name("name");
                writer.value(item.getName());
                writer.name("tipology");
                writer.value(item.getType().toString());
                writer.name("weight");
                writer.value(item.getWeight());
                writer.name("quantity");
                writer.value(item.getQuantity());
                writer.name("value");
                writer.value(item.getValue());
                writer.endObject();
            }
        }
    }
//---------------------------------- MapGraph TypeAdapter ----------------------------------

    private static class MapGraphAdapter extends TypeAdapter<MapGraph> {
        /**
         * Reads a JSON representation of a MapGraph from the given JsonReader.
         * 
         * @param reader The JsonReader to read the JSON from.
         * @return The MapGraph object read from the JSON.
         * @throws IOException If an I/O error occurs while reading the JSON.
         * @throws IllegalArgumentException If the JSON is invalid or missing required parameters for MapGraph.
         */
        @Override
        public MapGraph read(JsonReader reader) throws IOException {
            int keys = -1;
            ArrayList<Stanza> chambers = null;

            reader.beginObject();
            while (reader.hasNext()) {
                String tName = getTokenName(reader);
                if(tName.equals("Player")){
                    reader.skipValue();
                    continue;
                }
                if(reader.peek().equals(JsonToken.BEGIN_OBJECT)){
                    reader.beginObject();
                }
                switch (tName) {    
                    case "keys":
                        keys = reader.nextInt();
                        break;
                    case "chambers":
                        chambers = readChambers(reader);
                        break;
                }
            }
            reader.skipValue();
            
            if (keys != -1 && chambers != null) {
                return new MapGraph(keys, chambers);
            }
    
            throw new IllegalArgumentException("errore nella lettura mancano dei parametri di MapGraph");
        }
    
        private String getTokenName(JsonReader reader) throws IllegalArgumentException {
            try {
                if (reader.peek().equals(JsonToken.NAME)) {
                    return reader.nextName();
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Errore nella lettura");
            }
    
            throw new IllegalArgumentException("Questo non è un nome ma un valore");
        }
    
        /**
         * Reads the chambers from a JSON file using a JsonReader.
         * 
         * @param reader The JsonReader object used to read the JSON file.
         * @return An ArrayList of Stanza objects representing the chambers read from the JSON file.
         * @throws IOException If an I/O error occurs while reading the JSON file.
         */
        private ArrayList<Stanza> readChambers(JsonReader reader) throws IOException {
            MonsterFactory factory = new MonsterFactory();
            ArrayList<Stanza> chambers = new ArrayList<>();
            String path = null;
            int roomX = -1, roomY = -1;
            int playerX = -1, playerY = -1;
            Monster monster = null;
            Chest chest = null;
            boolean monsterState=false;
            reader.beginArray();
            while(reader.hasNext()){
                if(reader.peek().equals(JsonToken.BEGIN_OBJECT)){
                    reader.beginObject();
                }
                if(reader.peek().equals(JsonToken.NAME)){
                    String tName = reader.nextName();
                    
                    switch(tName){
                        case "Monster Dead":
                            monsterState = reader.nextBoolean();
                            break;
                        case "roomX":
                            roomX = reader.nextInt();
                            break;
                        case "roomY":
                            roomY = reader.nextInt();
                            break;
                        case "playerX":
                            playerX = reader.nextInt();
                            break;
                        case "playerY":
                            playerY = reader.nextInt();
                            break;
                        case "mType":
                            monster = factory.create(MType.valueOf(reader.nextString()));
                            break;
                        case "path":
                            path = reader.nextString();
                            break;
                        case "Chest:":
                            chest = readChest(reader);
                            break;
                    }
                }
                if(reader.peek().equals(JsonToken.END_OBJECT)){
                    Stanza chamber = new Stanza(new Coordinates(roomX, roomY), new Coordinates(playerX, playerY), monster, path, chest);
                    if(monsterState)
                    {                        
                        path=path.substring(0,path.length()-4);
                        path+="Open.png";
                        chamber = new Stanza(new Coordinates(roomX, roomY), new Coordinates(playerX, playerY), monster, path, chest);
                        chamber.setFree();    
                    }  
                    chambers.add(chamber);
                    path = null;
                    chest = null;
                    monster = null;
                    playerX = -1;
                    playerY = -1;
                    roomX = -1;
                    roomY = -1;
                    monsterState=false;
                    reader.endObject();
                }
            }
           
            reader.skipValue();
            
            return chambers;
        }

        private Chest readChest(JsonReader reader) throws IOException {
            Vector<ItemStack> items;
            Chest chest = null;
            boolean locked = false;
            reader.beginObject();
            while(reader.hasNext()){
                String tName = reader.nextName();
                switch(tName){
                    case "Chest Locked":
                        chest = new Chest(reader.nextBoolean());
                        break;
                    case "Chest Inventory:":
                        items = readChestItems(reader);
                        for(ItemStack item: items){
                            chest.add(item);
                        }
                        break;
                }
            }
            reader.endObject();
            
            return chest;
        }

        private Vector<ItemStack> readChestItems(JsonReader reader) throws IOException {
            Vector<ItemStack> vec = new Vector<ItemStack>();
            String name = null;
            ItemType type = null;
            int weight = -1, quantity = -1, value = -1;
            reader.beginArray();
            
            while (reader.hasNext()) {
                if (reader.peek().equals(JsonToken.BEGIN_OBJECT)) {
                    reader.beginObject();
                }
                if (reader.peek().equals(JsonToken.NAME)) {
                    
                    String tName = reader.nextName();
                    switch (tName) {
                        case "name":
                            name = reader.nextString();
                            break;
                        case "tipology":
                            type = ItemType.valueOf(reader.nextString());
                            break;
                        case "weight":
                            weight = reader.nextInt();
                            break;
                        case "quantity":
                            quantity = reader.nextInt();
                            break;
                        case "value":
                            value = reader.nextInt();
                            break;
                    }
                }

                if (reader.peek().equals(JsonToken.END_OBJECT)) {
                    ItemStack it = new ItemStack(name, type, (byte) weight, (byte) quantity, (byte) value, "");
                    vec.add(it);
                    reader.endObject();
                }
            }
            
            reader.skipValue();
            return vec;
        }
    
        /**
         * Writes the given MapGraph object to a JSON file using the provided JsonWriter.
         * The MapGraph object is serialized as a JSON object with two properties: "keys" and "chambers".
         * The "keys" property contains the keys of the MapGraph, and the "chambers" property contains
         * the chambers of the MapGraph.
         *
         * @param writer The JsonWriter used to write the JSON data.
         * @param map The MapGraph object to be written.
         * @throws IOException If an I/O error occurs while writing the JSON data.
         */
        @Override
        public void write(JsonWriter writer, MapGraph map) throws IOException {
            writer.beginObject();
            writer.name("keys");
            writer.value(map.getKeys());
            writer.name("chambers");
            writeChambers(writer, map.getChambers());
            writer.endObject();
        }
    
        private void writeChambers(JsonWriter writer, ArrayList<Stanza> chambers) throws IOException {
            writer.beginArray();
            for (Stanza chamber : chambers) {
                writeChamberValues(writer, chamber);
            }
            writer.endArray();
        }

        /**
         * Writes the chamber values to the JSON writer.
         *
         * @param writer the JSON writer to write the values to
         * @param chamber the chamber object containing the values to be written
         * @throws IOException if an I/O error occurs while writing to the JSON writer
         */
        private void writeChamberValues(JsonWriter writer, Stanza chamber) throws IOException {
            writer.beginObject();
            Coordinates c = chamber.getCoord();
            writer.name("roomX");
            writer.value(c.getX());
            writer.name("roomY");
            writer.value(c.getY());
            c = chamber.getPlayerCoordinates();
            writer.name("playerX");
            writer.value(c.getX());
            writer.name("playerY");
            writer.value(c.getY());
            writer.name("mType");
            writer.value(chamber.getMonster().getType().toString());
            writer.name("path");
            writer.value(chamber.getPathImage());
            writer.name("Monster Dead");
            writer.value(chamber.isFree());
            writer.name("Chest:");
            writer.beginObject();
            writer.name("Chest Locked");
            writer.value(chamber.getChest().isClosed());
            writer.name("Chest Inventory:");
            writeInvetory(writer, chamber.getChest().showStorage());
            writer.endObject();
            writer.endObject();

        }

        private void writeInvetory(JsonWriter writer, LinkedList<ItemStack> storage) throws IOException {
            writer.beginArray();
            for (ItemStack item : storage) {
                writer.beginObject();
                writer.name("name");
                writer.value(item.getName());
                writer.name("tipology");
                writer.value(item.getType().toString());
                writer.name("weight");
                writer.value(item.getWeight());
                writer.name("quantity");
                writer.value(item.getQuantity());
                writer.name("value");
                writer.value(item.getValue());
                writer.endObject();
            }
            writer.endArray();
        }
    }

}
