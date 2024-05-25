
package com.ACMD.app.Engine_Layer.StorageManagement;
    //import 

//import java.

/*
    -nelle classi che eredita, il nome cambia, ma la funzione è la stessa, in modo da rendere
     più comodo a chi usa la classe per capire cosa sta facendo

    es: getDanno e getDifesa fanno la stessa cosa, ma chiaro che uno è arma ed altro è armatura
*/

public /*abstract*/ class ItemStack
{
    String name;
    byte weight;
    byte quantity;           //oggetti si possono impilare
    byte value;
    ItemType tipology;
    String description;

    final int VALORE_DEFAULT_QUANTITA = 1;

//  ## costruttori ##

    /**                 ?per quale motivo se tolgo l'asterisco non capisce che è un parametro
    * @param w Peso
    */ 
    public ItemStack(String n, ItemType t, byte w)
    {
        name= n;
        weight= w;
        quantity= VALORE_DEFAULT_QUANTITA;
        tipology= t;

        exceptionLauncher();
    }
	  
    /**
     * @param w Peso
     * @param q Quantità
     */
    public ItemStack(String n, ItemType t, byte w, byte q)
    {
        name= n;
        weight= w;
        quantity= q;
        tipology= t;

        exceptionLauncher();
    }

    private ItemStack(String n, ItemType t, byte w, byte q, byte v, String descr)
    {//_per il clone
        name = n;
        tipology = t;
        weight = w;
        quantity = q;
        value = v;
        description = descr;
    }

//  ## Metodi Private ##

    private void exceptionLauncher()
    {
        if( weight<0)
         throw new IllegalArgumentException("Peso non può essere negativo");
        if(name=="" || name==null)
         throw new IllegalArgumentException("Il nome dell'oggetto non è valido");
        if(quantity <=0)
         throw new IllegalArgumentException("La quantità non è positiva");
            //anche se prima avevo messo queste condizioni nei singoli costruttori in modo da evitare il controllo sulla quantità che non serve.. e forse è più sensato perché non si tratta di un metodo che può essere utile all'esterno
    }


//  ## Metodi Public ##

    /**
     * @return Controlla tipologia, nome, valore, peso : no descrizione o quantità
     * Quindi possono esserci due oggetti uguali ma dove cambia solo la descrizione
     */
    public boolean equals(Object o)
     {
        ItemStack i= (ItemStack)o;
        return this.tipology==i.tipology && this.name==i.name && this.value==i.value && this.weight==i.weight;
//interessante che non segna errore su o anche se non esiste in Object la variabile tipology
     }

    public ItemStack clone() //#TESTARE
    {
        return new ItemStack(this.name,this.tipology,this.weight,this.quantity,this.value,this.description); //che bello che non servano i metodi, perché è dentro la stessa classe, quindi accetta
    }

    public void addQuantity()
     { ++quantity; }

    /**
     * @return Ritorna vero se si può eliminare
     */
    public boolean removeQuantity()
     {
        return (--quantity) == 0;
   //         else throw new noItem_Exception();
     }


//  ## Metodi variabili ##

    public int getQuantity()
     { return quantity; }
    public int getValue()
     { return value; }
    public ItemType getType()
     { return tipology;}
    public int getWeight()
     { return weight;}
    public String getName()
     {return name;}

    public String showDescription()
     {return description;}

/*    public String toString()
     { return nome; }*/
}
