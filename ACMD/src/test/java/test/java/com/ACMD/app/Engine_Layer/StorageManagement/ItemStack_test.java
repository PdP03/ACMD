package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;


public class ItemStack_test {


    @Test
    public void controlloAdd()
    {
        ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 1, (byte) 1, null);
        t.addQuantity();
        Assert.assertEquals(2,t.getQuantity());

    }
    @Test
    public void controlloAddEccesso()
    {
       // ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 255, (byte) 1, null);
       // non scrivere come sopra perché cambio valore alla quantità massima non funziona più

       ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 1, (byte) 1, null);
       int i;
       for(i=1; ! t.addQuantity() && i<10000000; i++) ;
       //continuo ad aumentare fino ad un valore impossibile
       //parte da 1 perché il primo deve essere quello appena inserito

        //System.out.println("Valore di i "+i+" "+ItemFactory.MAXVALUE);
        Assert.assertEquals(true,i<=ItemFactory.MAXVALUE);
        Assert.assertEquals( ItemFactory.MAXVALUE, t.getQuantity() );
    }
    @Test
    public void controlloRemove()
    {
        ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 2, (byte) 1, null);
        t.removeQuantity();
        Assert.assertEquals(1,t.getQuantity());
    }
    @Test
    public void controlloRemoveEccesso()
    {
        ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 1, (byte) 1, null);
        Assert.assertEquals(true,t.removeQuantity());   //mi aspetto che mi segni che possono eliminare
        Assert.assertEquals(true,t.removeQuantity());
    }
    

}
