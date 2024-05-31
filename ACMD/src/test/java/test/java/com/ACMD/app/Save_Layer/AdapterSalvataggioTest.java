package test.java.com.ACMD.app.Save_Layer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Save_Layer.AdapterSalvataggio;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AdapterSalvataggioTest {

    @Test
    public void TestParsetoPlayer() throws IOException {
        Player player = new Player("Lello");
        AdapterSalvataggio.Save(player);

            Gson gson = new Gson();
            FileReader reader = new FileReader("Savefile.json");
            JsonObject obj = gson.fromJson("Savefile.json", JsonObject.class);
            JsonObject requestedObj = obj.getAsJsonObject("Player");
            Player player2 = new Player(requestedObj.get("name").toString());
            reader.close();
            Assert.assertEquals(player, player2);
        

    }
}
