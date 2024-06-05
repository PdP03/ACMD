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
    public void TestParseToPlayer() throws IOException {
        Player player = new Player("Lello");
        AdapterSalvataggio.save(player);

            Gson gson = new Gson();
            FileReader reader = new FileReader("Savefile.json");
            
            Player player2 = gson.fromJson("Savefile.json", Player.class);
            //JsonObject requestedObj = obj.getAsJsonObject("name");
            //Player player2 = new Player(requestedObj.get("name").toString());
            reader.close();
            Assert.assertEquals(player, player2);
        

    }

 
}
