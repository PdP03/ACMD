package com.ACMD.app.Engine_Layer.StorageManagement;


import java.util.LinkedList;

/*
    Si tratta del gestore degli oggetti.
    Viene passato il nome dell'oggetto e poi si arrangia a memorizzarlo.

Idea1:      Si può utilizzare add che si occupa sia di inserire un oggetto che non esiste, se però esiste allora incrementa la sua quantità
Problema:   Non detto che tutti gli oggetti possono essere accatastati, magari alcuni possono essere unici
            _ma non è un vero problema in quanto questo può essere relegato ad altri, queta classe si occupa di solo storage
*/


public abstract class Storage
{


    private LinkedList<ItemStack> items;

//  ## Costruttori ##

    public Storage()
     { items = new LinkedList<ItemStack>(); }

//  ## Metodi Public ##

    /**
     * @return Ritorna false se era pieno e quindi non ha potuto inserire
     */
    public boolean add(ItemStack t)
    {
        //_sfrutto che si usa la shallow copy
        int pos = items.indexOf(t) ;

        return    (pos<0) ?
             items.add(t) :     //true
           !(items.get(pos)).addQuantity();//ottengo la posizione, mi faccio dare l'elemento e aggiungo uno
                                           //ottengo true se pieno, quindi restituisco negato
    }

    /**
     * Se la quantità si azzera viene automaticamente rimosso
     * @return False se non è stato trovato
     */
    public boolean remove(ItemStack t)
    {//_true se la rimozione si è potuta fare, false per evitare di lanciare un'eccezione
        int pos;
        if( (pos= items.indexOf(t)) <0) return false;   //numero negativo se non trovato

        //_l'oggetto esiste, ora se è l'ultimo devo eliminarlo
        t = items.get(pos);
        if( t.removeQuantity() )    //rimuovo la quantità
            items.remove(pos);      //se vale 0 allora lo devo rimuovere
        
        return true;
    }

    /**
     * @return Ritorna primo oggetto di quel tipo. Null se non trova nulla
     */
    public ItemStack searchFor(ItemType tip)
    {//_ritorna solo il primo oggetto di quel tipo

        for(int i=0; i< items.size(); i++)
         if( (items.get(i)).getType() == tip ) return (items.get(i)).clone();

        return null;
    }

    public ItemStack searchFor(String name)
    {
        for(int i=0; i< items.size(); i++)
         if( (items.get(i)).getName().equals(name) ) return (items.get(i)).clone();

        return null;
    }

    /**
     * @return Non ritorna la vera lista, ma una deep copy
     */
    @SuppressWarnings("unchecked")
    public LinkedList<ItemStack> showStorage()
    {
        Object obj= items.clone();
        return (LinkedList<ItemStack>)obj;
    }

    public boolean existItem(ItemStack t)
     { return items.contains(t); }

//  ## Metodi return variabili ##
    public String toString()
    {
        String s="";
        ItemStack it;
        for(int i=0; i<items.size(); i++)
        {
            it= items.get(i);
            s+= it.getName()+
                ":\n  Peso: "+it.getWeight()+
                 "\n  Valore: "+it.getValue()+
                 "\n Quantità:"+it.getQuantity()+
                 //"\n  Descrizione: "+it.getDescription() +
                 "\n";
        }

        return s;
    }
}

