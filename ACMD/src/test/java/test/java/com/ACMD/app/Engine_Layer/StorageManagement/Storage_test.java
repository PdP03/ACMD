package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Test;

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

    }
    @Test
    public void addEccesso()
    {

    }
    @Test
    public void remove()
    {

    }
    @Test
    public void removeEccesso()
    {

    }

}
