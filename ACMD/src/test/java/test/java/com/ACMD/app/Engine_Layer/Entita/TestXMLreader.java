package test.java.com.ACMD.app.Engine_Layer.Entita;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.MonsterValues;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Entita.MType;

public class TestXMLreader {
    @Test
    public void XMLReader(){
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
    
}
