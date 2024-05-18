package test.java.com.ACMD.app.Engine_Layer.Entita;

import org.junit.Assert;
import org.junit.Test;
import com.ACMD.app.Engine_Layer.Entita.Player;

public class TestPlayer {

    @Test(expected=IllegalArgumentException.class)
    public void testLv(){
        Player p = new Player("1");
        
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
        Player p = new Player("1");
        
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
        Player p = new Player("1");
        
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
        Player p = new Player("1");
        
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
    
}
