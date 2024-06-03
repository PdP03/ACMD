
package com.ACMD.app.Engine_Layer.StorageManagement;

public class Inventario extends Storage
{
    private int totalWeight=0;

//  ## Costruttori ##

public Inventario()
{
    super();
}

//  ## OverLoad Public ##


public boolean add(ItemStack t)
{
    if(super.add(t) )
    {
        totalWeight+= t.getWeight();
        return true;
    }
        // if(totalWeight<0) throw new IllegalArgumentException("Il peso lanciato è troppo");

        return false;
    }

/**
 * @return False se non è trovato in inventario
 */
public boolean remove(ItemStack t)
{
    if( super.remove(t) )
    {
        totalWeight-= t.getWeight();
        return true;
    }

    return false;
}


//  ## Metodi return variabili ##

    public int getTotalWeight()
     {return totalWeight;}
    public int quantyOf(String item)
     {
        ItemStack t = this.searchFor(item); //metodo ereditato da Storage
        return t.getQuantity();
     }

}
