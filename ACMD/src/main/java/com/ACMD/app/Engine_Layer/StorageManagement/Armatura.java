
package com.ACMD.app.Engine_Layer.StorageManagement;

public class Armatura extends ItemStack
{
    byte valore;

//      ## Costruttore ##

    public Armatura(String n, int peso)
    {
        super(n,ItemType.ARMATURA,peso);
    }
	    	
    public Armatura(String n, int peso, int quantita  )
    {
        super(n,ItemType.ARMATURA,peso,quantita);
    }

//      ## Metodi ##
    public byte getDifense()
     { return valore; }
}
