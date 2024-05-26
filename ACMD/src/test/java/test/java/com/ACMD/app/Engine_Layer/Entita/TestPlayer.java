package test.java.com.ACMD.app.Engine_Layer.Entita;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

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
    public void testAttack(){
        //---- TEST VALORE DI DEFAULT ----
        Assert.assertEquals(1, p.getAttack());

        //---- TEST CON LV. 2 ----
        p.setLv((byte)2);
        Assert.assertEquals(2, p.getAttack());

        //---- TEST CON LV. MAX ----
        p.setLv(Byte.MAX_VALUE);
        Assert.assertEquals(127, p.getAttack());

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

    @Test(expected=IllegalArgumentException.class)
    public void testRemoveItem(){
        ItemStack item = new ItemStack("Spada", ItemType.ARMA, (byte)3, (byte)1, (byte)2, "Spada di roccia");

        //---- TEST RIMOZIONE DI UN ELEMENTO ESISTENTE PER ATTACK(attacco)----
        byte attackValue = p.getAttack();
        p.addItem(item);
        p.removeItem(item);
        Assert.assertEquals(attackValue, p.getAttack());

        //---- TEST RIMOZIONE DI UN ELEMENTO ESISTENTE PER ARMOR(difesa)----
        ItemStack itemD = new ItemStack("Stivali", ItemType.ARMATURA, (byte)1, (byte)1, (byte)1, "Stivali in pelle");
        ItemStack itemD1 = new ItemStack("Pettorina", ItemType.ARMATURA, (byte)3, (byte)1, (byte)3, "Stivali in pelle");
        byte armorInit = p.getArmor();
        p.addItem(itemD);
        Assert.assertNotEquals(armorInit, p.getArmor());
        p.addItem(itemD1);
        byte armorValueD1 = p.getArmor();
        Assert.assertNotEquals(armorInit, p.getArmor());
        p.removeItem(itemD1);
        Assert.assertEquals(armorInit + (byte)itemD.getValue(), p.getArmor());
        Assert.assertEquals(armorValueD1 - (byte)itemD1.getValue(), p.getArmor());

        //---- TEST RIMOZIONE DI UN ELEMENTO NON ESISTENTE (lancia eccezione)----
        p.removeItem(item);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddItem(){
        //---- TEST AGGIUNTA DI UN ELEMENTO ----
        ItemStack item = new ItemStack("Stivali", ItemType.ARMATURA, (byte)8, (byte)1, (byte)1, "Stivali in pelle");
        p.addItem(item);
        Assert.assertTrue(p.getInv().existItem(item));

        //---- TEST AGGIUNTA DI UN ARMA ----
        ItemStack itemA = new ItemStack("Spada", ItemType.ARMA, (byte)1, (byte)1, (byte)1, "Spada di legno");
        byte attackValue = p.getAttack();
        p.addItem(itemA);
        Assert.assertNotEquals(attackValue,p.getAttack());

        //---- TEST AGGIUNTA DI UN ELEMENTO CHE NON PUO STARE NEL INVENTARIO(lancia eccezione)----
        p.addItem(item);
        

    }

    @Test
    public void testGetInv(){
        ItemStack itemC = new ItemStack("Pozione", ItemType.POZIONE_CURA, (byte)7, (byte)1, (byte)1, "cura");
        p.addItem(itemC);
        ItemStack itemD = new ItemStack("Pozione", ItemType.POZIONE_DANNO, (byte)2, (byte)1, (byte)1, "danno");
        p.addItem(itemD);
        ItemStack itemF = new ItemStack("Pozione", ItemType.POZIONE_FORZA, (byte)1, (byte)1, (byte)1, "forza");
        p.addItem(itemF);

        Inventario inv = p.getInv();
        //---- TEST ESISTE POZIONE CURA ----
        Assert.assertTrue(inv.existItem(itemC));
        //---- TEST ESISTE POZIONE DANNO ----
        Assert.assertTrue(inv.existItem(itemD));
        //---- TEST ESISTE POZIONE FORZA ----
        Assert.assertTrue(inv.existItem(itemF));
    }

    //TODO: da finire manca implementazione di toString in Inventario
    @Test
    public void testShowInv(){
        ItemStack itemC = new ItemStack("Pozione", ItemType.POZIONE_CURA, (byte)7, (byte)1, (byte)1, "cura");
        p.addItem(itemC);

        Assert.assertNotEquals("",p.showInv());

    }
}
