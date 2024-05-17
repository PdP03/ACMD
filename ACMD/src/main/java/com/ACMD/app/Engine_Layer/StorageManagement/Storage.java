
package com.ACMD.app.Engine_Layer.StorageManagement;


import java.util.HashMap;

public abstract class Storage
{

//    final int n = 10;
    private LinkedList<String, Item> items;

    public Storage()
    {
        items = new LinkedList<String, Item>();
    }

    public abstract boolean addItem(Item t);   //si dà la possibilità di scegliere se esiste un valore massimo alla quantità di oggetti che si possono portare

    public boolean removeItem(Item t)
    {
        return items.remove(t);        
    }

    public LinkedList<Item> showStorage()
    {
        return items;    //??? ritorna una copia della lista, o la lista stessa? Nel caso c'è un metodo per ritornare una lista
    }

    public boolean existItem(Item t)    //?? perché non si fa direttamente che se l'elemento non c'è ritorna false il remove? Questi programmatori di Java che sono fissati con le loro eccezioni
    {
        return items.contains(t);   //? esattamente come funziona se viene fatto il cast ad oggetto? Non può controllare il valore hash di java perché è diverso per ogni oggetto, allora come riesce a capire? Fa un cast forzato?
    }

}
