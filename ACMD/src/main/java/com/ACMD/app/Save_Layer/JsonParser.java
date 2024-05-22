package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonParser {

    private final String FILENAME = "Savefile.json";
    private Gson gson = new Gson();
    
    public void WriteSavefile(Object obj){

        try {
            FileWriter writer = new FileWriter(FILENAME);
            gson.toJson(obj, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }

    }

    //Fare nell'adapter un metodo che parsa il json object nell'object desiderato?
    public JsonObject ReadSavefile(String objtype) throws IOException{
            FileReader reader = new FileReader(FILENAME);
            JsonObject obj = gson.fromJson(FILENAME, JsonObject.class);
            JsonObject requestedObj = obj.getAsJsonObject(objtype);
            reader.close();
            return requestedObj;
    }
}
