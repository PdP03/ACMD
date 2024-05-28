package test.java.com.ACMD.app.Engine_Layer;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.ConnectionValues;
import com.ACMD.app.Engine_Layer.MonsterValues;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Mappa.NODE;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

public class TestXMLreader {
    @Test
    public void testGetMonsterConfig(){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Entita\\";
        xmlReader reader = new xmlReader(entityDir, "MonsterConfig.xml");

        Vector<MonsterValues> values = reader.getMonsterValues();

        // ---------- TEST MOSTRO MARINO ----------
        Assert.assertEquals(3, values.get(MType.MOSTRO_MARINO.getId()).health);
        Assert.assertEquals(1, values.get(MType.MOSTRO_MARINO.getId()).armor);
        Assert.assertEquals(2, values.get(MType.MOSTRO_MARINO.getId()).healthMul);

        // ---------- TEST DRAGO ----------
        Assert.assertEquals(3, values.get(MType.BOSS_DRAGO.getId()).health);
        Assert.assertEquals(1, values.get(MType.BOSS_DRAGO.getId()).armor);
        Assert.assertEquals(2, values.get(MType.BOSS_DRAGO.getId()).healthMul);

        // ---------- TEST ARMATURA ----------
        Assert.assertEquals(3, values.get(MType.ARMATURA.getId()).health);
        Assert.assertEquals(1, values.get(MType.ARMATURA.getId()).armor);
        Assert.assertEquals(2, values.get(MType.ARMATURA.getId()).healthMul);

        // ---------- TEST COBOLDO ----------
        Assert.assertEquals(3, values.get(MType.COBOLDO.getId()).health);
        Assert.assertEquals(1, values.get(MType.COBOLDO.getId()).armor);
        Assert.assertEquals(2, values.get(MType.COBOLDO.getId()).healthMul);

        // ---------- TEST MAGO ----------
        Assert.assertEquals(3, values.get(MType.MAGO_OSCURO.getId()).health);
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
        }

        // ---------- TEST LETTURA DELLE CONNESSIONE DA FILE ----------
        ArrayList<ConnectionValues> connections = reader.getAllConnection();
        for(ConnectionValues c: connections){
            Assert.assertTrue(c.direction1 instanceof Direction);
            Assert.assertTrue(c.direction2 instanceof Direction);
            Assert.assertTrue((Object)c.x instanceof Integer);
            Assert.assertTrue(((Object)c.y) instanceof Integer);
            Assert.assertTrue(((Object)c.x2) instanceof Integer);
            Assert.assertTrue(((Object)c.y2) instanceof Integer);

        }
    } 
}
