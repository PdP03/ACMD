package com.ACMD.app.Engine_Layer.Entita;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;

/**
 * Classe che rappresenta il player sfrutta l'Observer Pattern implementato
 * tramite PropertyChangeSupport poiche java.util.Observer/Observable sono 
 * deprecati da Java 9 (https://stackoverflow.com/questions/46380073/observer-is-deprecated-in-java-9-what-should-we-use-instead-of-it)
 * 
 * Player ha un inventario e gli oggetti che contiene possono modificare i suoi valori
 */
public class Player extends Entity{
    //handler degli observer
    private PropertyChangeSupport observerHandler;
    
    //variabili del player
    protected InventarioProvvisorio inv = new InventarioProvvisorio();
    private final byte MAX_INVENTORY_WEIGHT = 10;
    private final byte BASE_DAMAGE = 1;
    private final byte BASE_ARMOR = 1;
    private final byte BASE_HEALTH = 5;
    private final byte BASE_LEVEL = 1;
    private final byte HEALTH_MULTIPLIER = 2;
    private final byte DAMAGE_MULTIPLIER = 1;
    private final byte ARMOR_MULTIPLIER = 1;
    final String story ="Tutti danno la colpa a me è mi odiano perchè uso il METODO SCIENTIFICO. Dimostrero a loro che si sbagliano!!!";

    /**
     * Inizializza un giocatore con i parametri di default
     * @param name nome del player
     */
    public Player(String name){
        super.level = BASE_LEVEL;
        super.maxHealth = BASE_HEALTH;
        super.health = BASE_HEALTH;
        super.damage = BASE_DAMAGE;
        super.armor = BASE_ARMOR;
        super.name = name;
        super.history = story;

        //inizializzazione del handler
        observerHandler = new PropertyChangeSupport(this);
    }

    /**
     * Aggiunge un nuovo listener(observer) a observerHandler, tutti gli
     * observer aggiunti verrano notificati in caso di cambiamento
     * @param listener oggetto PropertyChangeListener che vuole osservare player
     */
    public void addObserver(PropertyChangeListener listener) {
        observerHandler.addPropertyChangeListener(listener);
    }

    /**
     * Toglie un listener(observer) da observerHandler in questo modo
     * l'observer tolto NON verra più notificato. Se observer non è
     * presente in observerHandler il metodo removePropertyChangeListener()
     * non fa nulla.
     * @param listener oggetto PropertyChangeListener da rimuovere
     */
    public void removeObserver(PropertyChangeListener listener){
        observerHandler.removePropertyChangeListener(listener);
    }

    //imposta il nuovo livello e aggiorna la vita max
    public void setLv(byte l) throws IllegalArgumentException{
        byte val;
        if(l < 1)
            throw new IllegalArgumentException("Il lv. "+ l +" non esiste");
        
        maxHealth += (l-level)*HEALTH_MULTIPLIER;

        //check overflow di armor
        val = (byte)((l-level)*ARMOR_MULTIPLIER);
        if(val < 0 || Byte.MAX_VALUE - val < super.armor)
            throw new IllegalArgumentException("Overflow di armor (livello troppo alto):"+val);
        super.armor += val;
        
        //check overflow di damage
        val = (byte)((l-level)*DAMAGE_MULTIPLIER);
        if(val < 0 || Byte.MAX_VALUE - val < super.damage)
            throw new IllegalArgumentException("Overflow di damage (livello troppo alto): "+val);
        super.damage += val;
        
        //richiamat tutti gli observer che è avvenuto "levelChange" con oldValue=level e newValue=l
        observerHandler.firePropertyChange("levelChange", (Byte)level, (Byte)l);
        level = l;
    }

    /**
     * Controlla se un item si può inserire nel inventario oppure no
     * @param i Item da controllare
     * @return ritorna false se item lascia spazio nel inventario altrimenti true
     */
    public boolean doesFillInv(ItemStack i){
        return i.getWeight() + inv.getWeight() > MAX_INVENTORY_WEIGHT;
    }

    /**
     * Rimuove un item dall'inventario del player. Lancia IllegalArgumentException se l'inventario è vuoto
     * @param i item da eliminare
     * @return boolean true se è stato eliminato 
     */
    public boolean removeItem(ItemStack i) throws IllegalArgumentException{
        if(!inv.removeItem(i)){
            throw new IllegalArgumentException();
        }
        
        //il player può avere una sola arma è una sola armatura
        switch(i.getType()){
            case ARMA:
                super.safeDecrementDamage((byte)i.getValue());
                return true;

            case ARMATURA:
                super.safeDecrementArmor((byte)i.getValue());
                return true;

            default:        //neccessario poichè lo switch vuole tutti i case definiti nella enum
                return true;
        }
    }

    /**
     * Aggiunge un item nel inventario del player. Lancia IllegalArgumentException se l'inventario e pieno.
     * @param i item da aggiungere
     * @return boolean true se è stato inserito false se l'item è gia presente e non può essere inserito
     */
    public boolean addItem(ItemStack i) throws IllegalArgumentException{
        if(doesFillInv(i)){
            throw new IllegalArgumentException();
        }
        
        //il player può avere una sola arma è una sola armatura
        switch(i.getType()){
            case ARMA:
                if(inv.search(i.toString())){
                    return false;
                }
                super.safeIncrementDamage((byte)i.getValue());
                inv.addItem(i);
                return true;

            case ARMATURA:
                if(inv.quantityOf(i.toString()) > 1){
                    return false;
                }
                inv.addItem(i);
                super.safeIncrementArmor((byte)i.getValue());
                return true;

            default:        //neccessario poichè lo switch vuole tutti i case definiti nella enum
                inv.addItem(i);
                return true;
        }
    }

    /**
     * Restituisce l'inventario del entità
     * @return inv
     */
    public InventarioProvvisorio getInv(){
        return inv;
    }

    /**
     * Restituisce una stringa che rappresenta l'inventario
     * @return inv String
     */
    public String showInv(){
        return inv.toString();
    }
}
