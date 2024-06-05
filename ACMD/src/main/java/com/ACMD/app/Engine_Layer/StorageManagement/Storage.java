    //#REFACTORING
package com.ACMD.app.Engine_Layer.StorageManagement;

//import java.util.HashMap;
//import java.util.HashMap;
import java.util.LinkedList;

    //NOTA: a causa di vari cambiamenti, è rimasto che gestisce stack di oggetti, e non oggetti     #FUTURO: pensare prima a classi che possono rappresentare oggetti del mondo reale, e quelle sono solo come le strutture in C, contenitori per dati

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
 * 
 * 
*/


public abstract class Storage
{

//    final int n = 10;
    private LinkedList<ItemStack> items;

//  ## Costruttori ##

    public Storage()
    {
        items = new LinkedList<ItemStack>();
    }

//  ## Metodi Private ##

//  ## Metodi Public ##

    /**
     * @return Ritorna sempre true
     */
    public boolean add(ItemStack t)
    {
        //_sfrutto che si usa la shallow copy
        int pos = items.indexOf(t) ;

        return !( (pos<0) ?     // ottengo true se pieno, quindi restituisco negato
             items.add(t) :
            (items.get(pos)).addQuantity() );// ottengo la posizione, mi faccio dare l'elemento e aggiungo uno

        //return true;        //#CHIEDERE : perché avevamo messo booleano : ora lo so, per l'eccesso
    }
    //possibilità sottoclassi di scegliere se esiste un valore massimo alla quantità di oggetti che si possono portare <- invece no, perché basta richiamare con super

    /**
     * @return False se non è stato trovato
     */
    public boolean remove(ItemStack t)
    {//_true se la rimozione si è potuta fare, false per evitare di lanciare un'eccezione <- non accade nulla, non è così grave da bloccarsi.. a meno che non restituisca anche il dato
        int pos;
        if( (pos= items.indexOf(t)) <0) return false;   //numero negativo se non trovato
/*
        for(int i=0; i<items.size(); i++)
        {
                System.out.println( items.get(i) );
        }*/

          //  System.out.println(this);

        //_l'oggetto esiste, ora se è l'ultimo devo eliminarlo
        t = items.get(pos);
        if( t.removeQuantity() )    //rimuovo la quantità
            items.remove(pos);      //se vale 0 allora lo devo rimuovere
        
        return true;
    }

    /**
     * @return Ritorna primo oggetto di quel tipo. Null se non trova nulla
     */
    public /*final*/ ItemStack searchFor(ItemType tip)
    {//_ritorna solo il primo oggetto di quel tipo
        //ma se io faccio la copia in questo modo in realtà è una deep copy, quindi sto facendo passare gli oggetti veri.. no bene, devo passare delle copie
        //soluzione: non faccio alcuna copia e li passo la stessa lista, ma come costante

        for(int i=0; i< items.size(); i++)
         if( (items.get(i)).getType() == tip ) return (items.get(i)).clone();

        return null;
    }

    public /*final*/ ItemStack searchFor(String name)
    {

        for(int i=0; i< items.size(); i++)
         if( (items.get(i)).getName() == name ) return (items.get(i)).clone();

        return null;
    }

    /**
     * @return Non ritorna la vera lista, ma una deep copy
     */
    public LinkedList<ItemStack> showStorage()
    {
        Object obj= items.clone();
        return (LinkedList<ItemStack>)obj;      //? si può dirgli di fidarsi : potrei fare un metodo clone anche qua in modo che torni una linkedList, ma dopo riceverei insulti perché ho reimplementato qualcosa che già esiste nelle librerie. Quindi rimarrà il warning
    }

    public boolean existItem(ItemStack t)
    //?? perché non si fa direttamente che se l'elemento non c'è ritorna false il remove? Questi programmatori di Java che sono fissati con le loro eccezioni
    {
        return items.contains(t);
   //? esattamente come funziona se viene fatto il cast ad oggetto? Non può controllare il valore hash di java perché è diverso per ogni oggetto, allora come riesce a capire? Fa un cast forzato?
    }

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



/*
 *    public boolean add(ItemStack t)
    {
        int pos = items.indexOf(t);
        if( pos<0 )// ? items.add(t); //: (items.get() ).
         (items.get(pos)).addQuantity();  //#FARE : se la copia fatta sugli elementi è deep e non shallow, allora si può prendere t e modificare quello senza fare una doppia ricerca
                                          //#FARE : anzi, faccio la ricerca per oggetto, e poi aggiungo all'oggetto trovato
                                          //#TESTARE : se facendo get toglie l'oggetto, fa una deep copy o una shallow copy.. in base a questo dovrò adeguarmi
                                            //_deep, quindi ho uso .clone() o ritorno final
        else items.add(t);

        return true;        //#CHIEDERE : perché avevamo messo booleano
    }
 */
/*
 public boolean remove(ItemStack t)
 {//_true se la rimozione si è potuta fare, false per evitare di lanciare un'eccezione <- non accade nulla, non è così grave da bloccarsi.. a meno che non restituisca anche il dato
     int pos;
     if( (pos= items.indexOf(t)) <0)
      throw new noItem_Exception("Oggetto in inventario non esiste");   // in genere preferisco lanciare un numero piuttosto che una eccezione, ma dato che lo scopo è la chiarezza e storage è un facade per gli oggetti, ok l'eccezione

     t = items.get(pos);     //???deep copy     #TERMINARE
     if( t.removeQuantity() )
         items.remove(pos);

     return true;
 }*/