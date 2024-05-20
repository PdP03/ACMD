package test.java.com.ACMD.app.Engine_Layer.Entita;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.Entita.MagoNero;
import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.Player;

/**
 * Nota: non è neccessario testare singolarmete le classi dei mostri poichè non implementano metodi aggiuntivi rispetto a Monster.java
 */
public class TestMonster {
    Monster m;

    @Before
    public void resetPlayer(){
        m = new MagoNero("1");
    }

    @Test
    public void testHistory(){
        String excepted = "Io sono Mago Nero, studiando i circuiti elettrici mi sono accorto che possono essere scritti tramite funzioni di trasferimento.\nStavo cercando di trovare la frequenza di taglio di un filtro analizzando l'uscita al variare del ingresso, tuttavia qualcuno mi ha rubato il condensatore INACCETTABILE se ti trovo verrai punito!";

        Assert.assertEquals(excepted, m.getHistory());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLv(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(1, m.getLv());

        //---- TEST VALORE GRANDE ----
        m.setLv((byte)127);
        Assert.assertEquals(127, m.getLv());

        //---- TEST VALORE NON VALIDO(lancia eccezzione) ----
        m.setLv((byte)128);
    } 

    @Test
    public void testArmor(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(1, m.getArmor());

        //---- TEST CON LV. 2 ----
        m.setLv((byte)2);
        Assert.assertEquals(2, m.getArmor());

        //---- TEST CON LV. MAX ----
        m.setLv((byte)126);
        Assert.assertEquals(126, m.getArmor());

    }

    @Test(expected=IllegalArgumentException.class)
    public void testDamage(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(2, m.getDamage());

        //---- TEST CON LV. 2 ----
        m.setLv((byte)2);
        Assert.assertEquals(3, m.getDamage());

        //---- TEST CON LV. MAX (lancia eccezzione damage va in overflow)----
        m.setLv(Byte.MAX_VALUE);
        Assert.assertEquals(127, m.getDamage());

    }

    @Test(expected=IllegalArgumentException.class)
    public void testHealth(){    
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(3, m.getLife());

        //---- TEST CON LV. 2 ----
        m.setLv((byte)2);
        m.changeHealth((short)2);
        Assert.assertEquals(5, m.getLife());

        //---- TEST CON LV. MAX (lancia eccezzione damage va in overflow)----
        m.setLv(Byte.MAX_VALUE);
        m.changeHealth((short)1000);
        Assert.assertEquals(257, m.getLife());
    }

    /*
     * Test per verificare che se il Player modifica il suo livello questo
     * venga notificato anche a Monster
     */
    @Test
    public void testChangeLevel(){
        Player p = new Player("Pippo");
        //---- TEST INCREMENTO LV PLAYER ----
        //aggiungo il mostro come osservatore
        p.addObserver(m);
        //Cambio lv. solo a player
        p.setLv((byte)2);

        Assert.assertEquals(p.getLv(), m.getLv());

        //---- TEST INCREMENTO LV PLAYER DOPO RIMOZINE DI MONSTER ----
        p.removeObserver(m);
        p.setLv((byte)3);

        Assert.assertNotEquals(p.getLv(), m.getLv());

        //---- TEST INCREMENTO LV MAX ----
        p.addObserver(m);
        p.setLv((byte)(Byte.MAX_VALUE-1));//-1 è neccessario poiche manderebbe in overflow damage di monster
        
        Assert.assertEquals(p.getLv(), m.getLv());
    }

    //TODO: da implementare
    @Test
    public void testRemoveItem(){

    }

    //TODO: da implementare
    @Test
    public void testAddItem(){

    }

    //TODO: da implementare
    @Test
    public void testGetInv(){

    }
}
