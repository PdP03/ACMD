package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class JsonParser {

    static private final String FILENAME = "Savefile.json";
    static private Gson gson = new GsonBuilder()
        .setPrettyPrinter()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
    
    public static void WriteSavefile(Object obj){

        try {
            FileWriter writer = new FileWriter(FILENAME);
            gson.toJson(obj, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }

    }

    //Fare nell'adapter un metodo che parsa il json object nell'object desiderato?
    public static JsonObject ReadSavefile(String objtype) throws IOException{
            FileReader reader = new FileReader(FILENAME);
            JsonObject obj = gson.fromJson(FILENAME, JsonObject.class);
            JsonObject requestedObj = obj.getAsJsonObject(objtype);
            reader.close();
            return requestedObj;
    }

}
