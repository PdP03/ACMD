
/*
    Per aumentare l'efficienza si potrebbe fare un bucket che divide per le varie categorie
     di tipo di arma.
    Poi all'interno del bucket fare una sorta di merge sort.. magari con un albero

    Ricorda che:
        -la classe Item rappresenta uno stack di oggetti, quindi inventario deve contenere
        tutte istanze di oggetti diversi : nel test posso fare questo controllo per vedere
        se per qualche motivo creare più istanze dello stesso oggetto
*/

// TERMINARE : dover mettere che togliere il peso quando fa remove
// TERMINARE : forse ciò che sto facendo non funziona, perché io non salvo direttamente l'elemento, quindi non posso ritrovarmelo nella lista
// TERMINARE : se salva più oggetti di stesso tipo in inventario

import StorageManagement;

public class Inventario extends Storage
{
//  ## Variabili ##

    byte pesoAttuale =0;                //inventario conosce solo il suo peso, non quanto può portarne il personaggio
    byte maxOggetti, contOggetti =0;

//  ## Costruttore ##
    public Inventario(int maxOggetti)
    {
        if( maxOggetti <=0 ) throw new IllegalArgumentException();

        super();
        this.maxOggetti = maxOggetti;
    }

//  ## Metodi ##

    public boolean addItem( Item t )
    {
        if( ++contOggetti == maxOggetti )       // NOTA: in realtà avrei dovuto fare un metodo che controlla se è full, e poi usare quello; inoltre avrei dovuto lanciare una eccezione.. ma così secondo me è più efficiente
        {
            --contOggetti;              //torna a stato valido
            //throw new InventoryOutOfBound("Non ci sono posti per altri tipi di oggetti");
            return false;
        }

        LinkedList<Item> items = super.showStorage();
        int pos = items.indexOf(t);

        if( pos < 0 ) items.add(t);     //aggiungo oggetto che non è in inventario
         else
            {
                t = items.get(pos);
                t.add();
                pesoAttuale += t.getPeso();
            }

        return true;
    }
    
    public getWeigth()
     { return pesoAttuale; }

    public int quantityOf( Item t )         //restituisce la quantità di un determinato oggetto
    {
        LinkedList<Item> items = super.showStorage();
        /*int pos = item.indexOf(t);
        t = items.get( pos<0 ? return 0; : pos );   //non esiste un metodo che mi permette di crea
        return t.getQuantity(); */

        items.contains(t) ? return t.getQuantity(); : return 0;
    }

}