
package com.ACMD.app.Engine_Layer.StorageManagement;

//import java.util.HashMap;     no perché perché voglio tipi di accesso differenti
import java.util.Vector;

public class ItemFactory
{
    static Vector<ItemStack> itemConfigurazione;
    static final String fileName = "";

    public ItemFactory()
    {
        itemConfigurazione= ( new LoaderStorageManagement(fileName) ).getInventory();
        //this.ordinaPerNome();     //se c'è tempo e voglia si può fare mergesort rispetto nome per ceracre nomi in binary-search
    }


    //  ## Metodi Pubblici ##

    public ItemStack getRandomItem()
    {
        return itemConfigurazione.get( (int)(Math.random() * itemConfigurazione.size() ) );
    }

    public ItemStack getItem(ItemType t)
     {
        for(int i=0; i<itemConfigurazione.size(); i++)
            if( t == (itemConfigurazione.get(i) ).getType() ) return itemConfigurazione.get(i);

        return null;
     }

    public ItemStack getItem(String name)
    {
        for(int i=0; i<itemConfigurazione.size(); i++)
            if( name == (itemConfigurazione.get(i) ).getName() ) return itemConfigurazione.get(i);

        return null;
    }

    /**
     * 
     * @param name
     * @param t     Tipologgia
     * @param value
     * @param weight
     * @param quantity  Così quando si istanzia si possono fare di più
     * @param description
     * @return Un oggetto inventanto. Non fa dei controlli perché verranno fatti da item una volta che si prova ad istanziare
     */
    public ItemStack createItem(String name, ItemType t, byte value, byte weight, byte quantity, String description)
    {
        itemConfigurazione.add( new ItemStack(name, t, weight, quantity, value, description) ); //ha completato tutta la signature da solo, assurdo
        return new ItemStack(name, t, weight, quantity, value, description);    //inutile che faccio di nuovo la ricerca
    }

     // ## Private ##
/* 
     private void ordinaPerNome()
     {
        
     }*/
}
