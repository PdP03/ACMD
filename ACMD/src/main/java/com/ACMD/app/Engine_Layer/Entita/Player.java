package com.ACMD.app.Engine_Layer.Entita;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.InventoryOutOfBound_Exception;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.google.gson.annotations.Expose;


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
    @Expose
    protected Inventario inv = new Inventario();

    
    private final byte MAX_INVENTORY_WEIGHT = 10;
    private final byte BASE_ATTACK = 2;
    private final byte BASE_ARMOR = 1;
    private final byte BASE_HEALTH = 5;
    private final byte BASE_LEVEL = 1;
    private final byte HEALTH_MULTIPLIER = 2;
    private final byte DAMAGE_MULTIPLIER = 1;
    private final byte ARMOR_MULTIPLIER = 1;
    final String story ="Tutti danno la colpa a me è mi odiano perchè uso il METODO SCIENTIFICO. Dimostrero a loro che si sbagliano!!!";

    /**
     * inizializza l'Handler per gli observer
     */
    public void initHandler(){
        observerHandler = new PropertyChangeSupport(this);
    }

    /**
     * Inizializza un giocatore con i parametri di default
     * @param name nome del player
     */
    
    public Player(String name){
        super.level = BASE_LEVEL;
        super.maxHealth = BASE_HEALTH;
        super.health = BASE_HEALTH;
        super.attack = BASE_ATTACK;
        super.armor = BASE_ARMOR;
        super.name = name;
        super.history = story;

        //inizializzazione del handler
        initHandler();
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

    /**
     * Imposta il livello al player aggiornando maxHealt, armor, damage e notifica
     * tutti gli observer. Lancia IllegalArgumentException se il livello non è valido
     * @param l livello da impostare
     */
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
        if(val < 0 || Byte.MAX_VALUE - val < super.attack)
            throw new IllegalArgumentException("Overflow di damage (livello troppo alto): "+val);
        super.attack += val;
        
        //segnala tutti gli observer che è avvenuto "levelChange" con oldValue=level e newValue=l
        observerHandler.firePropertyChange("levelChange", (Byte)level, (Byte)l);
        level = l;
    }

    /**
     * Controlla se un item si può inserire nel inventario oppure no
     * @param i Item da controllare
     * @return ritorna true se l'item riempie l'invenatio altrimenti false
     */
    public boolean doesFillInv(ItemStack i){
        return i.getWeight() + inv.getTotalWeight() > MAX_INVENTORY_WEIGHT;
    }

    /**
     * Rimuove un item dall'inventario del player.
     * @param i item da eliminare
     * @return boolean true se è stato eliminato false se l'item non esiste
     */
    public boolean removeItem(ItemStack i){
        if(!inv.remove(i)){
            return false;
        }
        
        switch(i.getType()){
            case ARMA:
                super.safeDecrementDamage((byte)i.getValue());
                return true;

            case ARMATURA:
                super.safeDecrementArmor((byte)i.getValue());
                return true;

            default:
                return true;
        }
    }

    /**
     * Aggiunge un item nel inventario del player. Lancia InventoryOutOfBound_Exception se l'item non può
     * stare nel inventario perchè raggiunge MAX_INVENTORY_WEIGHT.
     * Il player può portare una sola arma mentre può portare più pezzi di armatura purchè abbiano nome
     * diverso
     * @param i item da aggiungere
     * @return boolean true se è stato inserito false se l'item è gia presente e non può essere inserito
     */
    public boolean addItem(ItemStack i) throws InventoryOutOfBound_Exception{
        if(doesFillInv(i)){
            throw new InventoryOutOfBound_Exception();
        }
        
        switch(i.getType()){
            case ARMA:
                if(inv.searchFor(ItemType.ARMA) != null){
                    return false;
                }
                super.safeIncrementDamage((byte)i.getValue());
                inv.add(i);
                return true;

            case ARMATURA:
                if(inv.searchFor(i.getName()) != null){
                    return false;
                }
                inv.add(i);
                super.safeIncrementArmor((byte)i.getValue());
                return true;

            default:
                inv.add(i);
                return true;
        }
    }

    /**
     * Restituisce l'inventario del entità
     * @return inv
     */
    public Inventario getInv(){
        return inv;
    }

    /**
     * Restituisce true se il player ha quel tipo di oggetto nel inventario
     * @param type
     * @return
     */
    public boolean have(ItemType type){
        return inv.searchFor(type) != null;
    }

    /**
     * Restituisce una stringa che rappresenta l'inventario
     * @return inv String
     */
    public String showInv(){
        return inv.toString();
    }

    /**
     * restituisce il peso massimo del player
     * @return byte peso
     */
    public byte getMaxWeight(){
        return MAX_INVENTORY_WEIGHT;
    }

    /**
     * Restituisce il peso del inventario di player
     * @return byte peso
     */
    public byte getWeight(){
        return (byte)inv.getTotalWeight();
    }
}
