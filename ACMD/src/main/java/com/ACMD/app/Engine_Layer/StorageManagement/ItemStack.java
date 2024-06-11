
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
    private String name;
    private byte weight;
    private byte quantity;           //oggetti si possono impilare
    private byte value;
    private ItemType tipology;
    private String description;

    final int VALORE_DEFAULT_QUANTITA = 1;

//  ## costruttori ##

    public ItemStack(String n, ItemType t, byte w, byte q, byte v, String descr)
        //perché ora c'è il factory, quindi non è più item a pigliarsi la roba
    {  
            name = n;
        tipology = t;
          weight = w;
        quantity = q;
           value = v;
        description = descr;

//        exceptionLauncher(); SPOSTATA NEL FACTORY, QUINDI NON VENGONO PIÙ FATTI CONTROLLI QUI
    }

//  ## Metodi Public ##

    @Override
    /**
     * @return Controlla tipologia, nome, valore, peso : no descrizione o quantità
     * Quindi possono esserci due oggetti uguali ma dove cambia solo la descrizione
     */
    public boolean equals(Object o)
     {
        ItemStack i= (ItemStack)o;
        return this.tipology==i.tipology && this.name.equals(i.name) && this.value==i.value && this.weight==i.weight;
     }

    @Override
    public ItemStack clone()
    {
        return new ItemStack(this.name,this.tipology,this.weight,this.quantity,this.value,this.description);
         // non passa per il facotry perché sto copiando un oggetto che deve essere già in stato valido
    }

    /**
     * @return true se finito lo spazio ; false se non ci sono problemi
     * In questo modo se qualcuno da fuori deve fare un controllo in caso di errore, può usare il valore direttamente
     *  senza negare il return
     */
    public boolean addQuantity()
     {
        if( quantity>= ItemFactory.MAXVALUE ) return true;
 
        ++quantity; //nota: lo si vede "in ritardo" l'incremento: al possimo controllo  <- se a 100 devo controllare prima, motivo del >= e non >
        return false;
     }

    /**
     * @return Ritorna vero se si può eliminare definitivamente
     */
    public boolean removeQuantity()
     {
        --quantity;
        if(quantity==0) return true;
        if(quantity<0)      //in teoria non si avvera mai, e non servirebbe, ma previene overflow da --quantity
        {
            ++quantity;
            return true;        //modificato poco prima di release, prima non faceva return
        }

        return false;
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

}
