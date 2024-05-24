
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
    int weight;
    int quantity;           //oggetti si possono impilare
    int value;             //#TERMINARE
    ItemType tipology;
    String description;     //#TERMINARE

    final int VALORE_DEFAULT_QUANTITA = 1;

//  ## costruttori ##

    public ItemStack(String n, ItemType t, int w)
    {
        name= n;
        weight= w;
        quantity= VALORE_DEFAULT_QUANTITA;
        tipology= t;

        exceptionLauncher();
    }
	    	
    public ItemStack(String n, ItemType t, int w, int q)
    {
        name= n;
        weight= w;
        quantity= q;
        tipology= t;

        exceptionLauncher();
    }

    private ItemStack(String n, ItemType t, int w, int q, int v, String descr)
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

    public boolean removeQuantity()
     {
        return (--quantity) == 0;    //ritorna vero se si può eliminare : non fa controlli se negativo
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
