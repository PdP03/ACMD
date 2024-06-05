package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class JsonParser {
    /*
     * static private final String FILENAME = "Savefile.json";
     * static private Gson gson = new GsonBuilder()
     * .setPrettyPrinting()
     * .excludeFieldsWithoutExposeAnnotation()
     * .create();
     * 
     * public static void WriteSavefile(Object obj){
     * 
     * try {
     * FileWriter writer = new FileWriter(FILENAME);
     * gson.toJson(obj, writer);
     * writer.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * 
     * }
     * 
     * }
     * 
     * //Fare nell'adapter un metodo che parsa il json object nell'object
     * desiderato?
     * public static JsonObject ReadSavefile(String objtype) throws IOException{
     * FileReader reader = new FileReader(FILENAME);
     * JsonObject obj = gson.fromJson(FILENAME, JsonObject.class);
     * JsonObject requestedObj = obj.getAsJsonObject(objtype);
     * reader.close();
     * return requestedObj;
     * }
     */

    private static final String FILE_NAME = "Savefile.json";

    /**
     * Save the object to a JSON file.
     * 
     * @param obj The object to save.
     * @throws IOException If an I/O error occurs.
     */
    public static void save(Object obj) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(obj, writer);
        }
    }

    /**
     * Read and update the object from a JSON file.
     * 
     * @param obj The object to update.
     * @return The updated object.
     * @throws IOException If an I/O error occurs.
     */
    public static Object read(Object obj) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Object objFromJson = gson.fromJson(reader, obj.class);
            // Update other fields if necessary
            return obj;
        }
    }

}
