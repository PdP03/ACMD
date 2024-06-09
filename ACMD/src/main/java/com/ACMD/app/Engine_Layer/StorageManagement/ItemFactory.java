
package com.ACMD.app.Engine_Layer.StorageManagement;

//import java.util.HashMap;     no perché perché voglio tipi di accesso differenti
import java.util.Vector;

import com.ACMD.app.Engine_Layer.xmlReader;

//NOTA: non posso metterli come metodi statici, in quanto sarebbero relegati alla classe, e quindi non necessario richiamare
//  il costruttore. Però devo richiamare il costruttore altrimenti non potrei istanziarli.. peccato
// In realtà potrei farlo, ma poi se qualcuno non istanzia finisce per trovarsi delle eccezioni strane, e magari non capisce perché in quanto appunto quei metodi dovrebbero essere statici

public class ItemFactory
{

    final String StorageDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
    xmlReader reader;
    static Vector<ItemStack> itemConfigurazione;
    static final String fileName = "ItemStackConfig.xml";
    
    //private static Vector<ItemStack> itemConfigurazione;
   // private static final String fileName = "";
    public static final byte MAXVALUE = 100;    //tanto non è può causare danni.. a meno che qualcuno non conosca il limite!!
                                                //ora la uso dentro a ItemStack, come la si mette come variabile di package?

    public ItemFactory()
    {
        reader = new xmlReader(StorageDir, fileName);       //by Alex
        itemConfigurazione= reader.getAllItemStack();

       // itemConfigurazione= ( new LoaderStorageManagement(fileName) ).loadItems();    pensavo si facesse così
        exceptionLauncher(); //controllo tutto valido prima di qualsiasi altra cosa
//>>>>>>> 0dec8ea (messo private a variabili _ cambiato dove messo itemStack _ manca XML)
        //this.ordinaPerNome();     //se c'è tempo e voglia si può fare mergesort rispetto nome per ceracre nomi in binary-search
    }

    /*static */public int getSize()     //purtroppo non può essere staic in quanto è legato a dei dati interni dell'oggetto, altrimenti sarebbe strana come cosa.. anche se io lo farei. Ma forse sta cosa è una paranoia mia perché il codice non dovrebbe essere riprodotto per ogni oggetto
    {
        return itemConfigurazione.size();   //solo una comodità per i test
    }

    //  ## Metodi Pubblici ##

    public ItemStack getRandomItem()
    {
        return itemConfigurazione.get( (int)(Math.random() * itemConfigurazione.size() ) );
    }

    /**
     * 
     * Se ci sono più elementi dello stesso tipo, ne prende a caso
     * 
     * @param type
     * @return null se non trova nulla
     * 
     */
    public ItemStack getItem(ItemType type) //NOTA: sarebbe più efficiente con altra struttura dati in grado di distinguere già le tipologie
     {
        //_creo una lista con robe adatte
        Vector<Integer> itemListPosition= new Vector<Integer>();

        for(int i=0; i<itemConfigurazione.size(); i++)
            if( type == (itemConfigurazione.get(i) ).getType() ) itemListPosition.add( i );

        return itemListPosition.size() == 0 ?           //? questo controllo rispetto < o > di zero è più efficiente? Perché in teoria c'è già un flag in assembly che si occupa di questo, quindi magari non deve fare controlli sul sengno
               null :   //nessun oggetto
               itemConfigurazione.get(
                        itemListPosition.get(
                         (int)( Math.random() * itemListPosition.size()) )); //oggetto casuale da questa lista

     }

    public ItemStack getItem(String name)
    {
        for(int i=0; i<itemConfigurazione.size(); i++)
            if( name.equals( (itemConfigurazione.get(i) ).getName() ) ) return itemConfigurazione.get(i);

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
    public ItemStack createItemTemplate(String name, ItemType type, int value, int weight, int quantity, String description)
    {
        ItemStack t = new ItemStack(name, type, (byte)weight, (byte)quantity, (byte)value, description); // pià comodo oggetto perché riduce signature e numero di new
        exceptionLauncher(t);        //se controllo valido
        itemConfigurazione.add( t ); //aggiungo agli item posso avere     ;ha completato tutta la signature da solo, assurdo
        return t;                    //inutile che faccio di nuovo la ricerca
    }
    // nella realtà probabilmente per salvare meno spazio, avrei fatto un secondo array con tutti gli oggetti custom

     // ## Private ##

     private void exceptionLauncher()
     {
        ItemStack t;    //non servirebbe, ma per leggibilità.. spero che Java lo capisca e la cavi in compilazione
        for(int i=0; i<itemConfigurazione.size(); i++)  //mettendo qui il for lascio il costruttore più pulito e non devo fare tante chiamate con tanti parametri #OTTIMIZZATO
        {
            t = itemConfigurazione.get(i);

         if( t.getWeight()<=0 || t.getWeight()>MAXVALUE )         
          throw new IllegalArgumentException("Peso non valido (da 1 a 100)");
         if( t.getName()=="" || t.getName()==null)
          throw new IllegalArgumentException("Il nome dell'oggetto non esiste");
         if( t.getQuantity()<=0 || t.getQuantity()>MAXVALUE)
          throw new IllegalArgumentException("La quantità non valida (da 1 a 100)");
         if( t.getValue()<=0 || t.getValue()>MAXVALUE)
          throw new IllegalArgumentException("Valore non valido (da 1 a 100)");
         if( t.getType()==null)
          throw new IllegalArgumentException("Deve avere un tipo");
        }
     }
     private void exceptionLauncher(ItemStack t)
     {
        if( t.getWeight()<=0 || t.getWeight()>MAXVALUE )         
          throw new IllegalArgumentException("Peso non valido (da 1 a 100)");
         if( t.getName()=="" || t.getName()==null)
          throw new IllegalArgumentException("Il nome dell'oggetto non esiste");
         if( t.getQuantity()<=0 || t.getQuantity()>MAXVALUE)
          throw new IllegalArgumentException("La quantità non valida (da 1 a 100)");
         if( t.getValue()<=0 || t.getValue()>MAXVALUE)
          throw new IllegalArgumentException("Valore non valido (da 1 a 100)");
         if( t.getType()==null)
          throw new IllegalArgumentException("Deve avere un tipo");
     }

/* 
     private void ordinaPerNome()
     {
        
     }*/
}
