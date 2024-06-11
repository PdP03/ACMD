package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Test;
import org.junit.Assert;

import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.ACMD.app.Engine_Layer.StorageManagement.Storage;

public class Storage_test {     // utilizzando inventario perché storage è astratta

    @Test
    public void add()
    {
        ItemStack t   = new ItemStack("prova", ItemType.ARMA,(byte)1, (byte)1, (byte)1, null);
        Storage items = new Inventario(); //NON POSSO ISTANZIARE STORAGE PERCHÉ ASTRATTA

        Assert.assertEquals( null,items.searchFor("tentativo") ) ;
        items.add(t);
        Assert.assertEquals( t,items.searchFor("prova") ) ;

        //_anche che aggiunga in quantità
        items.add(t);
        Assert.assertEquals( t,items.searchFor("prova") ) ;
        Assert.assertEquals( 2, ( items.searchFor("prova") ).getQuantity() ) ;

    }
    @Test
    public void addEccesso()
    {
        ItemStack t   = new ItemStack("prova", ItemType.ARMA,(byte)1, (byte)1, (byte)1, null);
        Storage items = new Inventario();

        int i;
        for(i=0; items.add(t) && i<100000; i++);

        System.out.println("VALORE DI I: "+i+" "+ItemFactory.MAXVALUE);
        Assert.assertEquals(true, i<=ItemFactory.MAXVALUE && i>0);  //controllare almeno una volta viene eseguito
    }
    @Test
    public void remove_removeEccesso()
    {
        ItemStack t   = new ItemStack("prova", ItemType.ARMA,(byte)1, (byte)1, (byte)1, null);
        Storage items = new Inventario();

        items.add(t);

        Assert.assertEquals(1, t.getQuantity());
        Assert.assertEquals(true, items.remove(t));         //remove andato a buon fine
        Assert.assertEquals(false, items.remove(t));        //remove non possibile
    }

}
