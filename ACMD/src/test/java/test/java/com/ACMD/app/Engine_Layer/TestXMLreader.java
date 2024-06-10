package test.java.com.ACMD.app.Engine_Layer;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.RoomValues;
import com.ACMD.app.Engine_Layer.MonsterValues;
import com.ACMD.app.Engine_Layer.ParsePath;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Mappa.NODE;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.ACMD.app.Kernel_Layer.Menu.MenuValues;

public class TestXMLreader {
    @Test
    public void testGetMonsterConfig(){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Entita\\";
        xmlReader reader = new xmlReader(entityDir, "MonsterConfig.xml");

        Vector<MonsterValues> values = reader.getMonsterValues();

        // ---------- TEST MOSTRO MARINO ----------
        Assert.assertEquals(3, values.get(MType.MOSTRO_MARINO.getId()).health);
        Assert.assertEquals(1, values.get(MType.MOSTRO_MARINO.getId()).armor);
        Assert.assertEquals(4, values.get(MType.MOSTRO_MARINO.getId()).healthMul);

        // ---------- TEST DRAGO ----------
        Assert.assertEquals(3, values.get(MType.BOSS_DRAGO.getId()).health);
        Assert.assertEquals(1, values.get(MType.BOSS_DRAGO.getId()).armor);
        Assert.assertEquals(5, values.get(MType.BOSS_DRAGO.getId()).healthMul);

        // ---------- TEST ARMATURA ----------
        Assert.assertEquals(3, values.get(MType.ARMATURA.getId()).health);
        Assert.assertEquals(1, values.get(MType.ARMATURA.getId()).armor);
        Assert.assertEquals(2, values.get(MType.ARMATURA.getId()).healthMul);

        // ---------- TEST COBOLDO ----------
        Assert.assertEquals(3, values.get(MType.COBOLDO.getId()).health);
        Assert.assertEquals(1, values.get(MType.COBOLDO.getId()).armor);
        Assert.assertEquals(2, values.get(MType.COBOLDO.getId()).healthMul);

        // ---------- TEST MAGO ----------
        Assert.assertEquals(4, values.get(MType.MAGO_OSCURO.getId()).health);
        Assert.assertEquals(1, values.get(MType.MAGO_OSCURO.getId()).armor);
        Assert.assertEquals(2, values.get(MType.MAGO_OSCURO.getId()).healthMul);
    }

    @Test
    public void testGetAllItemStack(){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
        xmlReader reader = new xmlReader(entityDir, "ItemStackConfig.xml");

        Vector<ItemStack> list = reader.getAllItemStack();
        for(ItemStack n: list){
            Assert.assertTrue(n.getName() instanceof String);
            Assert.assertTrue(n.showDescription() instanceof String);
            Assert.assertTrue((Object)n.getType() instanceof ItemType);
            Assert.assertTrue(((Object)n.getQuantity()) instanceof Integer);
            Assert.assertTrue(((Object)n.getValue()) instanceof Integer);
            Assert.assertTrue(((Object)n.getWeight()) instanceof Integer);
        }

    }

    @Test
    public void testGetAllNode(){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Mappa\\";
        xmlReader reader = new xmlReader(entityDir, "MappaConfig.xml");

        // ---------- TEST LETTURA DEI NODI DA FILE ----------
        ArrayList<NODE> nodes = reader.getAllNode();
        Coordinates cord;
        for(NODE n: nodes){
            cord = n.getCoord();
            Assert.assertTrue((Object)cord.getX() instanceof Integer);
            Assert.assertTrue((Object)cord.getY() instanceof Integer);
            Assert.assertTrue((Object)n.isRoom() instanceof Boolean);
            Assert.assertTrue((Object)n.getPathImage() instanceof String);
        }

        // ---------- TEST LETTURA DELLE CONNESSIONE DA FILE ----------
        ArrayList<RoomValues> rooms = reader.getAllRoom();
        for(RoomValues c: rooms){
            Assert.assertTrue(c.mtype instanceof MType);
            Assert.assertTrue((Object)c.PlayerX instanceof Integer);
            Assert.assertTrue((Object)c.PlayerY instanceof Integer);
            Assert.assertTrue((Object)c.StanzaX instanceof Integer);
            Assert.assertTrue((Object)c.StanzaY instanceof Integer);
        }
    }
    
    @Test
    public void testGetMenuItems(){
        final String MenuDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Kernel_Layer\\Menu\\";
        Vector<String> fileNames = ParsePath.getFilesNameIn(MenuDir);

        xmlReader reader;
        for(String name: fileNames){
            if(name.contains(".xml") && name.contains("Menu")){
                reader = new xmlReader(MenuDir, name);

                Vector<MenuValues> values = reader.getMenuItems();
                for(MenuValues menuItem: values){
                    Assert.assertTrue(menuItem.cmdDescription instanceof String);
                    Assert.assertTrue(menuItem.cmdName instanceof String);
                }
            }
        }
         
    }
}
