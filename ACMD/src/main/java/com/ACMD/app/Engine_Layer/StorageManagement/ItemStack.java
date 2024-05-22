
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
    int valore;             //#TERMINARE
    ItemType tipologia;
    String description;     //#TERMINARE

    final int VALORE_DEFAULT_QUANTITA = 1;

//  ## costruttori ##

    public ItemStack(String n, ItemType t, int p)
    {
        nome= n;
        peso= p;
        quantita= VALORE_DEFAULT_QUANTITA;
        tipologia= t;

        exceptionLauncher();
    }
	    	
    public ItemStack(String n, ItemType t, int p, int q  )
    {
        nome= n;
        peso= p;
        quantita= q;
        tipologia= t;

        exceptionLauncher();
    }

//  ## Metodi Private ##

    private void exceptionLauncher()
    {
        if( peso<0)
         throw new IllegalArgumentException("Peso non può essere negativo");
        if(nome=="" || nome==null)
         throw new IllegalArgumentException("Il nome dell'oggetto non è valido");
        if(quantita <=0)
         throw new IllegalArgumentException("La quantità non è positiva");
    }


//  ## Metodi Public ##

    public void addQuantity()
     { ++quantita; }

    public boolean removeQuantity()
     {
        if( quantita>0 ) return --quantita != 0;
         else throw new noItem_Exception();
     }


//  ## Metodi variabili ##

    public int getQuantity()
     { return quantita; }
    public int getValue()
     { return valore; }
    public ItemType getType()
     { return tipologia;}
    public int getWeight()
     { return peso;}
    public String getName()
     {return nome;}

    public String showDescription()
     {return description;}

/*    public String toString()
     { return nome; }*/
}
