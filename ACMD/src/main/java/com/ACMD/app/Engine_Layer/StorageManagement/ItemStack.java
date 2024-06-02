
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

    /**                 ?per quale motivo se tolgo l'asterisco non capisce che è un parametro
    * @param w Peso
    *
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
     *
    public ItemStack(String n, ItemType t, byte w, byte q)
    {
        name= n;
        weight= w;
        quantity= q;
        tipology= t;

        exceptionLauncher();
    }*/

    public ItemStack(String n, ItemType t, byte w, byte q, byte v, String descr) //perché ora c'è il factory, quindi non è più item a pigliarsi la roba
    {//_era per il clone

       
            name = n;
        tipology = t;
          weight = w;
        quantity = q;
           value = v;
        description = descr;

//        exceptionLauncher(); SPOSTATA NEL FACTORY, QUINDI NON VENGONO PIÙ FATTI CONTROLLI QUI
                            //vantaggio anche ora chi usa può non impazzire passando degli int, ma poi sarà il factory ad usare byte
        //si potrebbe per rendere più efficiente l'esecuzione fare questi controlli dentro il factory, al massimo andrebbe a rendere più pesante il caricamento: però si può pensare di fare i controlli solo sugli oggetti
        //che sono istanziati da salvataggi e che non si trovano nella hashtable
    }

//  ## Metodi Private ##
/*
    private void exceptionLauncher()
    {
        if( weight<=0)
         throw new IllegalArgumentException("Peso non può essere negativo o nullo");
        if(name=="" || name==null)
         throw new IllegalArgumentException("Il nome dell'oggetto non è valido");
        if(quantity <=0)
         throw new IllegalArgumentException("La quantità non è positiva");
        if(value<=0)
         throw new IllegalArgumentException("Il valore deve essere positivo");
        if(tipology==null)
         throw new IllegalArgumentException("Deve avere un tipo");
            //anche se prima avevo messo queste condizioni nei singoli costruttori in modo da evitare il controllo sulla quantità che non serve.. e forse è più sensato perché non si tratta di un metodo che può essere utile all'esterno
    }
*/

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

    public ItemStack clone()
    {
        return new ItemStack(this.name,this.tipology,this.weight,this.quantity,this.value,this.description); //che bello che non servano i metodi, perché è dentro la stessa classe, quindi accetta
                // non passo per il facotry perché sto copiando un oggetto che deve essere già in stato valido
    }

    /**
     * @return true se finito lo spazio ; false se non ci sono problemi
     * In questo modo se qualcuno da fuori deve fare un controllo in caso di errore, può usare il valore direttamente
     *  senza negare il return
     */
    public boolean addQuantity()   //true finito spazio
     {
        if( quantity> ItemFactory.MAXVALUE ) return true;
 
        ++quantity;
        return false;
     }

    /**
     * @return Ritorna vero se si può eliminare definitivamente
     */
    public boolean removeQuantity()
     {
       // return (--quantity) == 0;         no perché essendo che modifica continuando a richiamare può andare in overflow
   //         else throw new noItem_Exception();

      /*  if( quantity > 1 )      //si elimina quando è l'ultimo, quindi 1 no 0
        {
            --quantity;
            return false;
        }

        return true;        funziona ma rimane 1*/

        --quantity;
        if(quantity==0) return true;
        if(quantity<0) ++quantity;      //in teoria non si avvera mai, e non servirebbe, ma previene overflow da --quantity
                                        //questo INSEGNA che per fare le cose fatte bene ci sono le classi che rappresentano gli oggetti che non dovrebbero fare sti controlli a volte, e ci dovrebbe essere un gestore ad occuparsene
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

/*    public String toString()
     { return nome; }*/
}
