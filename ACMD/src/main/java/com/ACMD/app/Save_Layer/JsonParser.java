package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import com.google.gson.*;

public class JsonParser {

    private static final String FILE_NAME = "Savefile.json";

    static private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    /**
     * Save the object to a JSON file.
     * 
     * @param obj The object to save.
     */
    public static void save(Object obj) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(obj, writer);
            File file = new File(FILE_NAME);
            awsClient.Upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Read the object from the JSON file.
     * 
     * @param <T> The type of the object to read.
     * @param type The class of the object to read.
     * @return The object read from the JSON file.
     */

    public static <T> T read(Class<T> type) {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            T objFromJson = gson.fromJson(reader, type);
            return objFromJson;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}


