package test.java.com.ACMD.app.Save_Layer;


import java.io.FileReader;
import java.io.IOException;


import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.google.gson.*;


public class SalvataggioTest {

    static private Gson gson = new GsonBuilder()
    .setPrettyPrinting()
    .excludeFieldsWithoutExposeAnnotation()
    .create();

    @Test
    public void ParsePlayer() throws IOException {
        Player player = new Player("Lello");
        SaveAdapter.save(player);

            FileReader reader = new FileReader("Savefile.json");
            
            Player player2 = gson.fromJson(reader, Player.class);
            
            reader.close();
            Assert.assertEquals(player.getName(), player2.getName());

    }

    //Chiedere a Matteo cosa devo salvare di mappa

    @Test
    public void ParseMap() throws IOException {
        MapGraph map = new MapGraph();
        SaveAdapter.save(map);

        FileReader reader = new FileReader("Savefile.json");
            
        MapGraph map2 = gson.fromJson(reader, MapGraph.class);
        reader.close();
        Assert.assertEquals();

    }

 
}
