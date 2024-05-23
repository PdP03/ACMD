    
package com.ACMD.app.Engine_Layer.StorageManagement;

//import java.util.HashMap;
//import java.util.HashMap;
import java.util.LinkedList;

/*
    Si tratta del gestore degli oggetti.
    Viene passato il nome dell'oggetto e poi si arrangia a memorizzarlo.

Idea1:      Si può utilizzare add che si occupa sia di inserire un oggetto che non esiste, se però esiste allora incrementa la sua quantità
Problema:   Non detto che tutti gli oggetti possono essere accatastati, magari alcuni possono essere unici

Idea2:      Un metodo per aggiungere (add) ed un metodo per incrementare (increase)
            In modo da poter decidere da gameEngine e dall'oggetto se si può incrementare
Problema:   Dover controllare da esterno tutto quanto -> scomodo e ridondante
            Dover controllare che non si facciano add su cose che già esistono

Meglio idea1 e se bisogna apportare delle modifiche fare un override sugli oggeti in modo che add non possa esistere
*/

/*
Funzionamento:
    Le possibilità sono o passare il nome dell'Item e poi sarà inventario ad occuparsi di allocare;
     oppure è gameEngine che si occupa di istanziare e poi passare direttamente Item ad inventario.
    Alex preferisce la seconda, quindi si fa così   [io sono solo quello che deve rendere comoda la creazione degli oggetti, se preferisce così apposto]

Problema: comunque se viene istanziato un oggetto e poi passato non sarà uguale a quello dentro a Storage
    -soluzione1: fare un metodo per controllare se due oggetti sono uguali (tipo compareTo); ma non deve controllare le quantità
    -soluzione2: fare che vengano passate solo le stringhe
*/

/*
Motivazione uso HashMap :
    Unsando una hashmap si può avere un sistema che potenzialmente può essere molto veloce per la ricerca di tanti
     oggetti, ma è vero che dipende dalla velocità dell'algoritmo.
    Essendo delle stringhe, la funzione hash della classe String fa la somma di tutti i suoi bit.. dubito della efficienza.

    Essendo quindi una limitata quantità di dati, meglio le linkedlist in quanto diventa anche facile poter
     mettere e togliere degli elementi.
    Però utilizzando gli iteratori posso rendere tutto trasparente

Però:
    essendo che dall'esterno non devono lavorare con gli item, devo trovare un modo per associare
     il nome all'oggetto, quindi forse è meglio una hastable
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
        //items.contatins(t) ? items.add(t); : (items.get() ).;
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
        return null;
    }

    public boolean existItem(ItemStack t)
    //?? perché non si fa direttamente che se l'elemento non c'è ritorna false il remove? Questi programmatori di Java che sono fissati con le loro eccezioni
    {
        return items.contains(t);
   //? esattamente come funziona se viene fatto il cast ad oggetto? Non può controllare il valore hash di java perché è diverso per ogni oggetto, allora come riesce a capire? Fa un cast forzato?
    }

//  ## Metodi return variabili ##

}
