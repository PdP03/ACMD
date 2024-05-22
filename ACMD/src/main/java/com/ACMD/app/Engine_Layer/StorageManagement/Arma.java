
package com.ACMD.app.Engine_Layer.StorageManagement;
//import Item.ItemType;

public class Arma extends ItemStack
{
    byte valore;

//      ## Costruttore ##

    public Arma(String n, int peso)
    {
        super(n,ItemType.ARMA,peso);
    }
	    	
    public Arma(String n, int peso, int quantita  )
    {
        super(n,ItemType.ARMA,peso,quantita);
    }

//      ## Metodi ##
    public byte getDamage()
     { return valore; }
}
