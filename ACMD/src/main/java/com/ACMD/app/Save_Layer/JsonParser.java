package com.ACMD.app.Save_Layer;

import java.io.FileReader;
import java.io.FileWriter;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
