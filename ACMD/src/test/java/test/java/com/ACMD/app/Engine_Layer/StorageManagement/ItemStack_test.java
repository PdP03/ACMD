package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Assert;
import org.junit.Test;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;


public class ItemStack_test {
 /*   public ItemStack_test()
    {
            System.out.println( controlloCostruttori() ? "Superato" : "Non superato" );
            System.out.println(      controlloEquals() ? "Superato" : "Non superato" );
            System.out.println(           add_remove() ? "Superato" : "Non superato" );
    }
*/
   /*
    Controlli spostati in item factory
   
   @Test(expected=IllegalArgumentException.class)
    //? perché before e non before all?
    private void controlloCostruttori_peso()
    {
        //? se il primo lancia una eccezione, riesce a capire che deve continuare? Meglio non rischiare mettendo tutto in un unico metodo
        new ItemStack("nome", null, (byte)0, (byte)1, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    private void controlloCostruttori_nomeVuoto()
    {
        new ItemStack("", null, (byte)1, (byte)1, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    private void controlloCostruttori_nomeNull()
    {
        new ItemStack(null, null, (byte)1, (byte)1, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    private void controlloCostruttori_quantita()
    {
        new ItemStack("nome", null, (byte)1, (byte)0, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    private void controlloCostruttori_quanitaEccesso()
    {
        new ItemStack("nome", null, (byte)1, (byte)300, (byte)1, null);
    }
*/

//??? si può fare in modo che alcune righe di codice partano solo per il test.. per esempio fare che quelle righe partano anche con eccezzioni e controlli molto stringenti, ma poi tipo con ifdef si tolgono

    @Test
    private void controlloAdd()
    {
        ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 1, (byte) 1, null);
        t.addQuantity();
        Assert.assertEquals(2,t.getQuantity());

    }
    @Test
    private void controlloAddEccesso()
    {
       // ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 255, (byte) 1, null);
       // non come sopra perché cambio valore alla quantità massima non funziona più

       ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 1, (byte) 1, null);
       for(int i=0; ! t.addQuantity() && i<10000000; i++) ;  //continuo ad aumentare fino ad un valore impossibile

        //se arrivo qui sicuramente sbagliato: lancia errore
        Assert.assertEquals(true,false);
    }
    @Test
    private void controlloRemove()
    {
        ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 2, (byte) 1, null);
        t.removeQuantity();
        Assert.assertEquals(1,t.getQuantity());
    }
    @Test
    private void controlloRemoveEccesso()
    {
        ItemStack t = new ItemStack("prova",ItemType.ARMA, (byte) 1, (byte) 1, (byte) 1, null);
        Assert.assertEquals(true,t.removeQuantity());
    }
    

}
