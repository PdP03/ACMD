package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Test;
import com.ACMD.app.Engine_Layer.StorageManagement.Storage;

public class Storage_test {
    
    public Storage_test()
    {
        System.out.println( controlloCostruttori() ? "Superato" : "Non superato" );
        System.out.println(           add_remove() ? "Superato" : "Non superato" );
        System.out.println(            searchFor() ? "Superato" : "Non superato" );
    }

    @Test
    private boolean controlloCostruttori()
    {

        return true;
    }
    @Test
    private boolean add_remove()
    {
        return true;
    }
    @Test
    private boolean searchFor()
    {
        return true;
    }
}
