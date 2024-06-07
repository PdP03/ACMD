package test.java.com.ACMD.app.Save_Layer;


import java.io.FileReader;
import java.io.IOException;


import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.google.gson.*;




/**
 * This class contains unit tests for the SaveAdapter class.
 */
public class SalvataggioTest {

    /**
     * This method tests the ParsePlayer method of the SaveAdapter class.
     * It creates a new Player object, saves it using SaveAdapter.save method,
     * reads the saved player from a file using Gson library, and asserts that
     * the name of the original player and the retrieved player are equal.
     *
     * @throws IOException if an I/O error occurs while reading the file.
     */
    @Test
    public void ParsePlayer() throws IOException {
        Gson gson = new Gson();
        Player player = new Player("Lello");
        SaveAdapter.save(player);

        FileReader reader = new FileReader("Savefile.json");

        Player player2 = gson.fromJson(reader, Player.class);

        reader.close();
        Assert.assertEquals(player.getName(), player2.getName());
    }

    /**
     * This method tests the SaveAdapter.savePlayer method of the SaveAdapter class.
     * It creates a new Player object, saves it using SaveAdapter.savePlayer method,
     * retrieves the saved player using SaveAdapter.retrievePlayer method, and asserts
     * that the name of the original player and the retrieved player are equal.
     */
    @Test
    public void SaveAdapterTest() {
        Player player = new Player("Lello");
        SaveAdapter.save(player);
        Player player2 = SaveAdapter.retrievePlayer();
        Assert.assertEquals(player.getName(), player2.getName());
    }

        /**
         * This method tests the SaveAdapter.savePlayer method of the SaveAdapter class
         * when a null player object is passed as input. It saves the null player using
         * SaveAdapter.savePlayer method, retrieves the saved player using SaveAdapter.retrievePlayer
         * method, and asserts that the retrieved player is null.
         */
        @Test
        public void savePlayerNullTest() {
            Player player = null;
            SaveAdapter.savePlayer(player);
            Player savedPlayer = SaveAdapter.retrievePlayer();
            Assert.assertNull(savedPlayer);
        }

        /**
         * This method tests the SaveAdapter.savePlayer method of the SaveAdapter class
         * when multiple players are saved. It creates two Player objects, saves them
         * using SaveAdapter.savePlayer method, retrieves the saved player using SaveAdapter.retrievePlayer
         * method, and asserts that the name of the retrieved player is equal to the name
         * of the second player.
         */
        @Test
        public void savePlayerMultipleTimesTest() {
            Player player1 = new Player("Lello");
            Player player2 = new Player("Mario");
            SaveAdapter.save(player1);
            SaveAdapter.save(player2);
            Player savedPlayer = SaveAdapter.retrievePlayer();
            Assert.assertEquals(player2.getName(), savedPlayer.getName());
        }

        /**
         * This method tests the SaveAdapter.retrievePlayer method of the SaveAdapter class
         * when no player is saved. It retrieves the saved player using SaveAdapter.retrievePlayer
         * method and asserts that the retrieved player is null.
         */
        @Test
        public void retrievePlayerNullTest() {
            Player savedPlayer = SaveAdapter.retrievePlayer();
            Assert.assertNull(savedPlayer);
        }

        /**
         * This method tests the SaveAdapter.saveMap method of the SaveAdapter class.
         * It saves a map using SaveAdapter.saveMap method and asserts the expected behavior.
         */
        @Test
        public void saveMapTest() {
            // Write your test for the saveMap method here
        }

        /**
         * This method tests the SaveAdapter.saveMap method of the SaveAdapter class
         * with null input. It saves a null map using SaveAdapter.saveMap method and
         * asserts the expected behavior.
         */
        @Test
        public void saveMapNullTest() {
            // Write your test for the saveMap method with null input here
        }

        /**
         * This method tests the SaveAdapter.retrieveMap method of the SaveAdapter class.
         * It retrieves a saved map using SaveAdapter.retrieveMap method and asserts the
         * expected behavior.
         */
        @Test
        public void retrieveMapTest() {
            // Write your test for the retrieveMap method here
        }

        /**
         * This method tests the SaveAdapter.retrieveMap method of the SaveAdapter class
         * when no map is saved. It retrieves a map using SaveAdapter.retrieveMap method
         * and asserts the expected behavior.
         */
        @Test
        public void retrieveMapNullTest() {
            // Write your test for the retrieveMap method when no map is saved here
        }
    
}
