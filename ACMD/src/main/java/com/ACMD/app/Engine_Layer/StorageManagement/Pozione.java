
package com.ACMD.app.Engine_Layer.StorageManagement;

public class Pozione extends Item
{
    byte valore;

//      ## Costruttore ##

    public Pozione(String n, int peso)
    {
        super(n,ItemType.POZIONE,peso);
    }
	    	
    public Pozione(String n, int peso, int quantita  )
    {
        super(n,ItemType.POZIONE,peso,quantita);
    }

//      ## Metodi ##
    public byte getEffect()
     { return valore; }
}
