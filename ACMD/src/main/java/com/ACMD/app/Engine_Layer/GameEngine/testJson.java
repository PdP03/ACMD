package com.ACMD.app.Engine_Layer.GameEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class testJson {
    public static void main(String[] args){
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Player.class, new PlayerAdapter())
        .setPrettyPrinting()
        .create();
        Player p = new Player("Ciccio");
        p.setLv((byte)1);
        ItemFactory factory = new ItemFactory();
        p.addItem(factory.getItem(ItemType.ARMA));
        p.addItem(factory.getItem(ItemType.ARMATURA));
        try{
            String str = gson.toJson(p);
            FileWriter writer = new FileWriter("Test.json");
            writer.write(str);
            writer.close();
            System.out.println("---------------------- STRINGA SCRITTA NEL FILE JSON ----------------------");
            System.out.println(str);

            Player p1 = gson.fromJson(str, Player.class);
            System.out.println("---------------------- VALORI DEL PLAYER DOPO ESSERE CARICATI DAL FILE JSON ----------------------");
            System.out.println("name: "+p1.getName()+"\nlife: "+p1.getLife()+"\nattack: "+p1.getAttack());
            System.out.println(p1.showInv());
        }
        catch(Exception e){e.printStackTrace();}
    }
}

class PlayerAdapter extends TypeAdapter<Player>{
    @Override 
    public Player read(JsonReader reader) throws IOException { 
        String name = null;
        Inventario inv = null;
        byte lv = -1;
        short health = -1;
        
        reader.beginObject();
        while(reader.hasNext()){
            String tName = getTokenName(reader);

            switch(tName){
                case "name":
                    name = reader.nextString();
                    break;
                case "Life":
                    health = (short)reader.nextInt();
                    break;
                    
                case "Level":
                    lv = (byte)reader.nextInt();
                    break;
                case "Inventory":
                    inv = readInventario(reader);
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        if(name != null && lv > 0 && health > 0){
            return new Player(name, lv, health, inv);
        }

        throw new IllegalArgumentException("errore nella lettura mancano dei parametri di player");
    }

    /**
     * Permette di ottenere il nome del token
     * @param reade
     * @return String nome del token
     * @throws IllegalArgumentException lanciata se il reader non ha un Nome ma un valore di una filed
     */
    private String getTokenName(JsonReader reade) throws IllegalArgumentException{
        try{
            if(reade.peek().equals(JsonToken.NAME)){
                return reade.nextName();
            }
        }
        catch(Exception e){throw new IllegalArgumentException("Errore nella lettura");}

        throw new IllegalArgumentException("Questo non è un nome ma un valore");
    }

    /**
     * Metodo che legge il token inventario dal file json
     * @param reader JsonReader
     * @return Inventario inizializzato con gli oggetti letti
     * @throws IOException
     */
    private Inventario readInventario(JsonReader reader) throws IOException{
        String name = null;
        ItemType type = null;
        int weight = -1, quantity = -1, value = -1;
        Inventario inv = new Inventario();
        while(reader.hasNext()){
            if(reader.peek().equals(JsonToken.BEGIN_OBJECT)){
                reader.beginObject();
                name = null;
                type = null;
                weight=-1;
                quantity=-1;
                value=-1;
            }
            if(reader.peek().equals(JsonToken.NAME)){
                String tName = reader.nextName();
                switch(tName){
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

            if(reader.peek().equals(JsonToken.END_OBJECT)){
                ItemStack it = new ItemStack(name, type, (byte)weight, (byte)quantity, (byte)value, "");
                inv.add(it);
                reader.endObject();
            }
        }
        return inv;
    }

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
     * @param writer
     * @param inv Inventario da scrivere nel json
     * @throws IOException nel caso di errore IO
     */
    private void writeInvetory(JsonWriter writer, Inventario inv) throws IOException { 
        writer.beginObject();
        writeStorageItems(writer, inv.showStorage());
        writer.endObject();
    } 

    /**
     * Metodo di utilità che scrive nel Json tutti gli elementi di Storage
     * @param writer
     * @param storage LinkedList<ItemStack> 
     */
    private void writeStorageItems(JsonWriter writer, LinkedList<ItemStack> storage) throws IOException {
        
        for(ItemStack item: storage){
            writer.name("Item");
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
