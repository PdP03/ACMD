package com.ACMD.app.Engine_Layer.Entita;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import com.ACMD.app.Engine_Layer.StorageManagement.Item;

/**
 * Monster sfrutta l'Observer Pattern implementato tramite PropertyChangeSupport 
 * poiche java.util.Observer/Observable sono deprecati da Java 9 
 * (https://stackoverflow.com/questions/46380073/observer-is-deprecated-in-java-9-what-should-we-use-instead-of-it)
 * @see Player
 * 
 */
public abstract class Monster extends Entity implements PropertyChangeListener {
    private MType type;
    private final byte BASE_DAMAGE = 2;
    private final byte BASE_ARMOR = 1;
    private final byte BASE_HEALTH = 3;
    private final byte BASE_LEVEL = 1;
    private final byte HEALTH_MULTIPLIER = 2;
    private final byte DAMAGE_MULTIPLIER = 1;
    private final byte ARMOR_MULTIPLIER = 1;

    /**
     * Costruttore che pemette di selezionare il tipo
     * @param t Mtype
     */
    public Monster(MType t, String name){
        type = t;
        super.level = BASE_LEVEL;
        super.maxHealth = BASE_HEALTH;
        super.health = BASE_HEALTH;
        super.damage = BASE_DAMAGE;
        super.armor = BASE_ARMOR;

        if(name == null){
            super.name = selectName(t);
        }
        else{
            super.name = name;
        }
    }

    /**
     * Costruttore che permette di modificare i valori di partenza di un mostro
     * @param t tipo mostro
     * @param name nome 
     * @param health vita iniziale
     * @param damage danno iniziale
     * @param armor armatura iniziale
     */
    public Monster(MType t, String name, short health, byte damage, byte armor){
        if(health <= 1 || damage <= 0 || armor <= 0){
            throw new IllegalArgumentException("Valore health, damage, armor non corretto");
        }

        type = t;
        super.level = BASE_LEVEL;
        super.maxHealth = health;
        super.health = health;
        super.damage = damage;
        super.armor = armor;
    }

    /**
     * Metodo richiamato in automatico da PropertyChangeSupport in player in caso
     * di modifiche alla classe (vedi Player.java)
     * @param event tipo di modifica subita da Player
     */
    public void propertyChange(PropertyChangeEvent event){
        if(event.getPropertyName() == "levelChange"){
            byte b = (Byte)event.getNewValue();
            setLv(b);
        }
    }

    /**
     * Metodo di appoggio per selezionare il nome
     * @param t MType tipo di mostro
     * @return String nome in base a MType
     */
    private String selectName(MType t){
        switch(t){
            case MOSTRO_MARINO:
                return "Mostro Marino";
            case COBOLDO:
                return "Coboldo";
            case BOSS_DRAGO:
                return "DRAGO";
            case ARMATURA:
                return "Armatura Fluttuante";
            case MAGO_OSCURO:
                return "Mago Oscuro";
            default:
                return "Non_Definito";
        }
    }

    /**
     * Ottiene la tipologia di mostro istanziato
     * @return Mtype enum
     */
    public MType getType(){
        return type;
    }

    //modifica il livello di monster
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

        level = l;
    }

    //aggiunta del elemento se non esiste lancia IllegalArgumentException
    public boolean addItem(Item i){
        inv.addItem(i);
        return true;
    }

    //rimozione del elemento se non esiste lancia IllegalArgumentException
    public boolean removeItem(Item i) throws IllegalArgumentException{
        if(!inv.removeItem(i)){
            throw new IllegalArgumentException();
        }

        return true;
    }
    
}
