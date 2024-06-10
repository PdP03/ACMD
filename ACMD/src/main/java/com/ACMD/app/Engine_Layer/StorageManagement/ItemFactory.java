
package com.ACMD.app.Engine_Layer.StorageManagement;

import java.util.Vector;
import com.ACMD.app.Engine_Layer.xmlReader;

public class ItemFactory
{

    final String StorageDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
    xmlReader reader;
    static Vector<ItemStack> itemConfigurazione;
    static final String fileName = "ItemStackConfig.xml";
    
    public static final byte MAXVALUE = 100;

    public ItemFactory()
    {
        reader = new xmlReader(StorageDir, fileName);       //by Alex
        itemConfigurazione= reader.getAllItemStack();

        exceptionLauncher(); //controllo tutto valido prima di qualsiasi altra cosa
    }

    public int getSize()
     { return itemConfigurazione.size();}   //solo una comodità per i test

    //  ## Metodi Pubblici ## 

    public ItemStack getRandomItem()
    {
        int pos = (int)(Math.random() * itemConfigurazione.size()); //per facilitare la lettura
        return (ItemStack)( itemConfigurazione.get(pos).clone() );
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
        if(type==null)
         return null;

        //_creo una lista con robe adatte
        Vector<Integer> itemListPosition= new Vector<Integer>();

        for(int i=0; i<itemConfigurazione.size(); i++)
            if( type == (itemConfigurazione.get(i) ).getType() ) itemListPosition.add( i );

        ItemStack facilitaLettura;
        facilitaLettura= itemListPosition.size() == 0 ? 
               null :   //nessun oggetto
               itemConfigurazione.get(
                        itemListPosition.get(
                         (int)( Math.random() * itemListPosition.size()) )); //oggetto casuale da questa lista

        return facilitaLettura.clone();
     }

    public ItemStack getItem(String name)
    {
        if(name==null)
         return null;

        for(int i=0; i<itemConfigurazione.size(); i++)
            if( name.equals( (itemConfigurazione.get(i) ).getName() ) ) return itemConfigurazione.get(i).clone();

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
        ItemStack t = new ItemStack(name, type, (byte)weight, (byte)quantity, (byte)value, description); // più comodo oggetto perché riduce signature e numero di new
        exceptionLauncher(t);        //se controllo valido
        itemConfigurazione.add( t ); //aggiungo agli item posso avere     ;ha completato tutta la signature da solo, assurdo
        return t;                    //inutile che faccio di nuovo la ricerca
    }

     // ## Private ##

     private void exceptionLauncher()
     {
        ItemStack t;    //leggibilità
        for(int i=0; i<itemConfigurazione.size(); i++)
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

}
