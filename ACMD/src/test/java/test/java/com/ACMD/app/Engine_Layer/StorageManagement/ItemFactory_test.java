package test.java.com.ACMD.app.Engine_Layer.StorageManagement;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

public class ItemFactory_test {
    
    @Before
    public void controlloCostante()
    {
        Assert.assertEquals(true, 2<ItemFactory.MAXVALUE);  //almeno 2 deve essere, altrimenti non ha senso che sia uno stack
    }

    //  ## TEST PER CONTROLLO ECCEZIONE ##

    @Test(expected=IllegalArgumentException.class)
    //? perché before e non before all?
    public void controlloCostruttori_peso()
    {
        //? se il primo lancia una eccezione, riesce a capire che deve continuare? Meglio non rischiare mettendo tutto in un unico metodo
        ItemFactory factory= new ItemFactory();
        factory.createItemTemplate("nome", null, (byte)0, (byte)1, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    public void controlloCostruttori_nomeVuoto()
    {
        ItemFactory factory= new ItemFactory();
        factory.createItemTemplate("", null, (byte)1, (byte)1, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    public void controlloCostruttori_nomeNull()
    {
        ItemFactory factory= new ItemFactory();
        factory.createItemTemplate(null, null, (byte)1, (byte)1, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    public void controlloCostruttori_quantita()
    {
        ItemFactory factory= new ItemFactory();
        factory.createItemTemplate("nome", null, (byte)1, (byte)0, (byte)1, null);
    }
    @Test(expected=IllegalArgumentException.class)
    public void controlloCostruttori_quanitaEccesso()
    {
        ItemFactory factory= new ItemFactory();
        factory.createItemTemplate("nome", null, (byte)1, (byte)300, (byte)1, null);
    }

    // ## ALTRI METODI ##

    @Test
    public void randomTest()
    {//_ SE FALLISCE RIPROVALO PERCHÉ RANDOM

        ItemFactory factory= new ItemFactory();
        //più oggetti ci sono più è improbabile 2 di seguito
        final int nTentativiMAX = ItemFactory.MAXVALUE/10 +1;   //almeno uno deve
        int nTentativi=0;

        ItemStack[] mem= new ItemStack[2];
        mem[0]= factory.getRandomItem();

        for(int i=1; i<factory.getSize(); i++)
        {
            mem[i%2]= factory.getRandomItem();  //così si danno il cambio
            if( mem[0] == mem[1] )              //controllo se è uguale al precedente
            {
                Assert.assertNotEquals(nTentativi,nTentativiMAX);
                ++nTentativi;   //dopo altrimenti si avvera subito
            }
            else nTentativi =0;
        }
    }
    @Test
    public void getPerNome()
    {
        ItemFactory factory = new ItemFactory();
        ItemStack t;

        //_se esiste
        for(int i=0; i<factory.getSize(); i++)
        {
            t= factory.getRandomItem();
           Assert.assertEquals( factory.getItem( t.getName() ).getName() , t.getName() );    //dovrebbero essere sempre uguali, ed unici
        }
            
        //_se non esiste
        Assert.assertEquals( factory.getItem( "" ) , null );
    }
    @Test
    public void getPerTipo()
    {
        ItemFactory factory = new ItemFactory();
        ItemStack t;

        //_se esiste            e se oggetti dello stesso tipo non hanno il nome uguale
        int cont=0;
        for(int i=0; i<factory.getSize(); i++)
        {
            t= factory.getRandomItem();
           Assert.assertEquals( factory.getItem( t.getType() ).getType() , t.getType() );
            //controlla che siano dello stesso tipo

           if( t != factory.getItem( t.getType() ) ) ++cont;
            //controlla se sono stesso oggetto: il che può essere, ma non sempre
        }
        
        Assert.assertEquals(true,cont>0);   //quindi è riuscito a trovare dei casi in cui avevano stesso tipo, ma erano diversi
                                                     //RANDOM, RIPROVARE PER VEDERE SE FALLISCE PIÙ VOLTE

        //_se non esiste
        Assert.assertEquals( factory.getItem( (ItemType)null ) , null );  //non dovrebbe trovarlo  
    }


}
