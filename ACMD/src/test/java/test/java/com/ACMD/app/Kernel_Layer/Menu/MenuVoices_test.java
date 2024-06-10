package test.java.com.ACMD.app.Kernel_Layer.Menu;

import org.junit.Assert;
import org.junit.Test;

import com.ACMD.app.Kernel_Layer.Menu.MenuValues;
import java.util.HashMap;

public class MenuVoices_test
{
    @Test
    public void testHash()
    {
        MenuValues mn,mn2;
            mn= new MenuValues("chiave","valore");
            mn2=new MenuValues("chiave","valore");
        Assert.assertEquals( mn.hashCode() , mn2.hashCode() );
            mn= new MenuValues("chiave","valore");
            mn2=new MenuValues("chiave","");
        Assert.assertEquals( mn.hashCode() , mn2.hashCode() );
            mn= new MenuValues("chiave","valore");
            mn2=new MenuValues("chiave"," ");
        Assert.assertEquals( mn.hashCode() , mn2.hashCode() );
            mn= new MenuValues("chiave","valore");
            mn2=new MenuValues("chiave2","valore");
        Assert.assertNotEquals( mn.hashCode() , mn2.hashCode() );

    }
    @Test 
    public void equals()
    {
        Assert.assertEquals( new MenuValues("chiave","valore") , new MenuValues("chiave","valore") );
        Assert.assertEquals( new MenuValues("chiave","valore") , new MenuValues("chiave","") );
        Assert.assertEquals( new MenuValues("chiave","valore") , new MenuValues("chiave","Altro") );
  
        Assert.assertNotEquals( new MenuValues("chiave","valore") , new MenuValues("chiave2","valore") );

    }
    @Test
    public void testMapOk()
    {   //_controllo se funziona per le mappa perché ci serve
        MenuValues mn= new MenuValues("chiave","valore");
        HashMap<MenuValues,Integer> map = new HashMap<MenuValues,Integer>();

        map.put(mn,10);
        Assert.assertEquals( true , map.containsKey(mn));
        Assert.assertEquals( true , map.containsKey( new MenuValues("chiave"," ") ));
        Assert.assertEquals( false ,map.containsKey( new MenuValues("cinque"," ") ));

        //_controllo se con oggetto più strano funziona : uno che non ha sovrascritto
        MenuValues mn2= new MenuValues("chiave","valore");
        HashMap<MenuValues,NuovoOggetto> map2 = new HashMap<MenuValues,NuovoOggetto>();

        map2.put(mn2, new NuovoOggetto(3, 5) );
        Assert.assertEquals( true , map.containsKey(mn));
        Assert.assertEquals( true , map.containsKey( new MenuValues("chiave"," ") ));
        Assert.assertEquals( false ,map.containsKey( new MenuValues("cinque"," ") ));
    }

    class NuovoOggetto
    {
        int a,b;
        char c;
        String s1,s2;
        public NuovoOggetto(int a, int b)
         { this.a=a; this.b=b; }
    }
}
