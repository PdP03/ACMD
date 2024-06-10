package test.java.com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.MonsterFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestMonsterFactory {
    MonsterFactory factory = new MonsterFactory();

    @Test
    public void testCreate(){
        //---- TEST CREAZIONE ARMATURA ----
        Monster mons = factory.create(MType.ARMATURA);
        Assert.assertEquals(MType.ARMATURA, mons.getType());

        //---- TEST CREAZIONE DRAGO ----
        mons = factory.create(MType.BOSS_DRAGO);
        Assert.assertEquals(MType.BOSS_DRAGO, mons.getType());

        //---- TEST CREAZIONE COBOLDO ----
        mons = factory.create(MType.COBOLDO);
        Assert.assertEquals(MType.COBOLDO, mons.getType());

        //---- TEST CREAZIONE MOSTRO MARINO ----
        mons = factory.create(MType.MOSTRO_MARINO);
        Assert.assertEquals(MType.MOSTRO_MARINO, mons.getType());

        //---- TEST CREAZIONE MAGO ----
        mons = factory.create(MType.MAGO_OSCURO);
        Assert.assertEquals(MType.MAGO_OSCURO, mons.getType());
    }

    @Test
    public void testCreateWithName(){
        //---- TEST CREAZIONE ARMATURA ----
        Monster mons = factory.create(MType.ARMATURA, "Luigi");
        Assert.assertEquals("Luigi", mons.getName());

        //---- TEST CREAZIONE DRAGO ----
        mons = factory.create(MType.BOSS_DRAGO, "Bowser");
        Assert.assertEquals("Bowser", mons.getName());

        //---- TEST CREAZIONE COBOLDO ----
        mons = factory.create(MType.COBOLDO, "Mario");
        Assert.assertEquals("Mario", mons.getName());

        //---- TEST CREAZIONE MARINO ----
        mons = factory.create(MType.MOSTRO_MARINO, "Peach");
        Assert.assertEquals("Peach", mons.getName());

        //---- TEST CREAZIONE MAGO ----
        mons = factory.create(MType.MAGO_OSCURO, "Toad");
        Assert.assertEquals("Toad", mons.getName());
    }

    @Test
    public void testCreateCustom(){//solo per un MType
        //---- TEST VITA ----
        Monster mons = factory.create(MType.BOSS_DRAGO, "Bowser", (byte)100, (byte)20, (byte)20);
        Assert.assertEquals(100, mons.getLife());

        //---- TEST DANNO ----
        Assert.assertEquals(20, mons.getAttack());

        //---- TEST ARMATURA ----
        Assert.assertEquals(20, mons.getArmor());

        /*
         * ---- TEST CON INCREMENTO LV. 3----
         * i valori di default sono:
         * BASE_LEVEL = 1;
         * HEALTH_MULTIPLIER = 2;
         * DAMAGE_MULTIPLIER = 1;
         * ARMOR_MULTIPLIER = 1;
         */
        mons.setLv((byte)3);
        //---- TEST VITA ----
        mons.changeHealth((short)100);
        Assert.assertEquals(110, mons.getLife());
 
        //---- TEST DANNO ----
        Assert.assertEquals(24, mons.getAttack());
 
        //---- TEST ARMATURA ----
        Assert.assertEquals(24, mons.getArmor());
    }

    @Test
    public void testCreateRandom(){
        Monster mons = factory.createRandom();
        MType[] types = MType.values();

        for(MType t : types){
            if(mons.getType() == t){
                return;
            }
        }

        Assert.fail("Il mostro non appartiene alla enum");
    }
    
}
