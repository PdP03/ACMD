package test.java.com.ACMD.app.Engine_Layer.Entita;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ACMD.app.Engine_Layer.Entita.Player;

public class TestPlayer {
    Player p;

    @Before
    public void resetPlayer(){
        p = new Player("1");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLv(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(1, p.getLv());

        //---- TEST VALORE GRANDE ----
        p.setLv((byte)127);
        Assert.assertEquals(127, p.getLv());

        //---- TEST VALORE NON VALIDO(lancia eccezzione) ----
        p.setLv((byte)128);

    } 

    @Test
    public void testArmor(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(1, p.getArmor());

        //---- TEST CON LV. 2 ----
        p.setLv((byte)2);
        Assert.assertEquals(2, p.getArmor());

        //---- TEST CON LV. MAX ----
        p.setLv(Byte.MAX_VALUE);
        Assert.assertEquals(127, p.getArmor());

    }

    @Test
    public void testDamage(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(1, p.getDamage());

        //---- TEST CON LV. 2 ----
        p.setLv((byte)2);
        Assert.assertEquals(2, p.getDamage());

        //---- TEST CON LV. MAX ----
        p.setLv(Byte.MAX_VALUE);
        Assert.assertEquals(127, p.getDamage());

    }

    @Test
    public void testHealth(){    
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(5, p.getLife());

        //---- TEST CON LV. 2 ----
        p.setLv((byte)2);
        p.changeHealth((short)2);
        Assert.assertEquals(7, p.getLife());

        //---- TEST CON LV. MAX ----
        p.setLv(Byte.MAX_VALUE);
        p.changeHealth((short)1000);
        Assert.assertEquals(257, p.getLife());
    }

    @Test
    public void testChangeHealth(){
        //---- TEST HEALT GIA A FULL VITA ----
        p.changeHealth((short)1);
        Assert.assertEquals(5, p.getLife());

        //---- TEST HEALT A LV. 2 ----
        p.setLv((byte)2);
        p.changeHealth((short)2);
        Assert.assertEquals(7, p.getLife());

        //---- TEST HEALT DECREMENTO ----
        p.changeHealth((short)-5);
        Assert.assertEquals(2, p.getLife());

         //---- TEST HEALT MORTE DEL PLAYER ----
         p.changeHealth((short)-5);
         Assert.assertEquals(-3, p.getLife());
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

    //TODO: da implementare
    @Test
    public void testShowInv(){

    }
}
