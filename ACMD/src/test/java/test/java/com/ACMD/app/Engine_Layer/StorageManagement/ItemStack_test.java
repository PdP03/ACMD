package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Test;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;


public class ItemStack_test {
    public ItemStack_test()
    {
            System.out.println( controlloCostruttori() ? "Superato" : "Non superato" );
            System.out.println(      controlloEquals() ? "Superato" : "Non superato" );
            System.out.println(           add_remove() ? "Superato" : "Non superato" );
    }

    @Test
    private boolean controlloCostruttori()
    {

        return true;
    }
    @Test
    private boolean controlloEquals()
    {

        return true;
    }
    @Test
    private boolean add_remove()
    {

        return true;
    }
}
