package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Test;
import org.junit.Assert;

import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.ACMD.app.Engine_Layer.StorageManagement.Storage;

public class Storage_test {     // utilizzando inventario perché storage è astratta
   /* 
    public Storage_test()
    {
        System.out.println( controlloCostruttori() ? "Superato" : "Non superato" );
        System.out.println(           add_remove() ? "Superato" : "Non superato" );
        System.out.println(            searchFor() ? "Superato" : "Non superato" );
    }
*/
    @Test
    public void add()
    {
        ItemStack t   = new ItemStack("prova", ItemType.ARMA,(byte)1, (byte)1, (byte)1, null);
        Storage items = new Inventario(); //NON POSSO PERCHÉ ASTRATTA

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

        System.out.println("VALORE DI I: "+i);
        Assert.assertEquals(true, i< 1000 && i>0);  //controllare almeno una volta viene eseguito
    }
    @Test
    public void remove_removeEccesso()
    {
        ItemStack t   = new ItemStack("prova", ItemType.ARMA,(byte)1, (byte)1, (byte)1, null);
        Storage items = new Inventario();

        items.add(t);      //  items.add(t);

        Assert.assertEquals(1, t.getQuantity());
        Assert.assertEquals(true, items.remove(t));         //remove andato a buon fine
        Assert.assertEquals(false, items.remove(t));        //remove non possibile
    }

}
