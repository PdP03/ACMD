    
package com.ACMD.app.Engine_Layer.StorageManagement;

//import java.util.HashMap;
import java.util.LinkedList;

/*
    Unsando una hashmap si può avere un sistema che potenzialmente può essere molto veloce per la ricerca di tanti
     oggetti, ma è vero che dipende dalla velocità dell'algoritmo.
    Essendo delle stringhe, la funzione hash della classe String fa la somma di tutti i suoi bit.. dubito della efficienza.

    Essendo quindi una limitata quantità di dati, meglio le linkedlist in quanto diventa anche facile poter
     mettere e togliere degli elementi.
    Però utilizzando gli iteratori posso rendere tutto trasparente
*/

public abstract class Storage
{

//    final int n = 10;
    private LinkedList<ItemStack> items;        //Bruh la linked list può avere un tipo solo al suo interno, o è Item o è String.
                                                    // Ecco cosa succede ad non usare un IDE che ti segna gli errori quando si scrive codice

                                                    // A sì? Diventerò così forte da scrivere codice che compila al primo colpo anche senza IDE. Compila è, non funziona

//  ## Costruttori ##

    public Storage()
    {
        items = new LinkedList<ItemStack>();
    }

//  ## Metodi Private ##

//  ## Metodi Public ##
    public boolean add(ItemStack t)
    {
        items.add(t);
        return true;        //#CHIEDERE : perché avevamo messo booleano
    }
    //possibilità sottoclassi di scegliere se esiste un valore massimo alla quantità di oggetti che si possono portare <- invece no, perché basta richiamare con super

    public boolean remove(ItemStack t)
    {//_ritorna true se esistono ancora oggetti, false se era l'ultimo
        int pos;
        if( (pos= items.indexOf(t)) <0)
         throw new noItem_Exception("Oggetto in inventario non esiste");   // in genere preferisco lanciare un numero piuttosto che una eccezione, ma dato che lo scopo è la chiarezza e storage è un facade per gli oggetti, ok l'eccezione

        t = items.get(pos);     //???deep copy     #TERMINARE
        if( t.removeQuantity() )
         {
            items.remove(pos);
            return false;
         }

        return true;
    }

    public String showStorage()
    {
        return null;    //??? ritorna una copia della lista, o la lista stessa? Nel caso c'è un metodo per ritornare una lista
    }

    public boolean existItem(ItemStack t)    //?? perché non si fa direttamente che se l'elemento non c'è ritorna false il remove? Questi programmatori di Java che sono fissati con le loro eccezioni
    {
        return items.contains(t);   //? esattamente come funziona se viene fatto il cast ad oggetto? Non può controllare il valore hash di java perché è diverso per ogni oggetto, allora come riesce a capire? Fa un cast forzato?
    }

//  ## Metodi return variabili ##

}
