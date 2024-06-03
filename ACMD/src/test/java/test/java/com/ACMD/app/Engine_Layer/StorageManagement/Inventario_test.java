package test.java.com.ACMD.app.Engine_Layer.StorageManagement;


import org.junit.Test;
import org.junit.Assert;

import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;

// PER IL FUTURO : se ci sono problemi con i test, prima vai a vedere le classi che utilizza (in questo caso avevano problemi Storage ed ItemStack)

public class Inventario_test {

    @Test
    public void controlloPesoCorretto()
    {
        final int nElementiGenerare = ItemFactory.MAXVALUE/2;   //sono sicuro di non sforare la quanità massima

        //_controllo se la somma di tutti i pesi a mano è quella corretta
        ItemFactory generator= new ItemFactory();
        ItemStack[] aryT = new ItemStack[nElementiGenerare];
        Inventario inv = new Inventario();

        int totalWeight = 0;

        for(int i=0; i< aryT.length ; i++)
        {
            aryT[i]= generator.getRandomItem();
            inv.add(aryT[i]);
            totalWeight+= aryT[i].getWeight();

            Assert.assertEquals(totalWeight,inv.getTotalWeight());
                //dovrebbero essere sincronizzati
        }


        //_controllo se togliendo elementi a caso il peso continua a rimanere corretto
        controlloPerditaPeso(aryT,inv,totalWeight);
    }

    private void controlloPerditaPeso( ItemStack[] aryT,Inventario inv,int totalWeight )
    {
        int posMem;
        ItemStack mem;

        for(int i=aryT.length-1; i>=0; i--)
        {
            posMem= (int)(Math.random() *i);
            mem= aryT[i];
            aryT[i]= aryT[posMem];
            aryT[posMem]= mem;

            inv.remove(aryT[i]);
            totalWeight-= aryT[i].getWeight();
            Assert.assertEquals(totalWeight, inv.getTotalWeight()); //dovrebbero essere sincronizzati
        }
    }

    @Test
    public void addNonAggiungePeso()
    {//_in caso di tanti add non deve aggiungere peso

        ItemFactory factory= new ItemFactory();
        Inventario inv = new Inventario();
        ItemStack t = factory.getRandomItem();

        for(int i=0; i<ItemFactory.MAXVALUE*2; i++) //sono sicuro di sforare
            inv.add(t);
         
        Assert.assertEquals( true, inv.getTotalWeight() < t.getWeight() * ItemFactory.MAXVALUE*2 );
            //il peso totale ottenuto deve essere minore del peso possibile teorico
    }
}
