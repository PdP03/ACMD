package test.java.com.ACMD.app.Save_Layer;


import java.io.FileReader;
import java.io.IOException;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.google.gson.*;




/**
 * This class contains unit tests for the SaveAdapter class.
 */
public class SalvataggioTest {
    
    Player p = null;
    MapGraph m = null;
    /**
     * This method tests the SaveAdapter.savePlayer method of the SaveAdapter class.
     * It creates a new Player object, saves it using SaveAdapter.savePlayer method,
     * retrieves the saved player using SaveAdapter.retrievePlayer method, and asserts
     * that the attributes of the original player and the retrieved player are equal.
     */
    @Test
    public void SaveAdapterTest() {
        p = new Player("Lello"); 
        ItemStack item = new ItemStack("Pozione", ItemType.POZIONE_CURA, (byte)7, (byte)1, (byte)1, "cura");
        p.addItem(item);
        item = new ItemStack("Pozione", ItemType.POZIONE_DANNO, (byte)2, (byte)1, (byte)1, "danno");
        p.addItem(item);
        SaveAdapter.save(p, null);
        Player player2 = SaveAdapter.retrievePlayer();
        Assert.assertEquals(p.getName(), player2.getName());
        Assert.assertEquals(p.getLv(), player2.getLv());
        Assert.assertEquals(p.getInv(), player2.getInv());
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
            SaveAdapter.save(player, null);
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

    
}
