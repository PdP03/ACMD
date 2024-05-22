
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
    String nome;
    int peso;
    int quantita;           //oggetti si possono impilare
    ItemType tipologia;

    final int VALORE_DEFAULT_QUANTITA = 1;

//  ## costruttori ##

    public ItemStack(String n, ItemType t, int p)
    {
        if(p<0)
         throw new IllegalArgumentException("Peso non può essere negativo");  //compilatore mette condition guards come false in cache di default?
        if(n=="" || n==null)
         throw new IllegalArgumentException("Il nome dell'oggetto non è valido");

        nome= n;
        peso= p;
        quantita= VALORE_DEFAULT_QUANTITA;
        tipologia= t;
    }
	    	
    public ItemStack(String n, ItemType t, int p, int q  )
    {
        if( p<0)
         throw new IllegalArgumentException("Peso non può essere negativo");
        if(n=="" || n==null)
         throw new IllegalArgumentException("Il nome dell'oggetto non è valido");
        if(q <=0)
         throw new IllegalArgumentException("La quantità non è positiva");

        nome= n;
        peso= p;
        quantita= q;
        tipologia= t;
    }


//  ## Metodi inline ##

    public int getQuantity()
     { return quantita; }

    public void addItem()
     { ++quantita; }

    public ItemType getType()
     { return tipologia;}

    public boolean removeItem()
     {
        if( quantita>0 ) return --quantita != 0;
         else throw new noItem_Exception();
     }

    public int getWeight()
     { return peso;}

    public String toString()
     { return nome; }
}
