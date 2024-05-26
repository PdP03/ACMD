package com.ACMD.app.Engine_Layer.Entita;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Monster sfrutta l'Observer Pattern implementato tramite PropertyChangeSupport 
 * poiche java.util.Observer/Observable sono deprecati da Java 9 
 * (https://stackoverflow.com/questions/46380073/observer-is-deprecated-in-java-9-what-should-we-use-instead-of-it)
 * @see Player
 * 
 */
public abstract class Monster extends Entity implements PropertyChangeListener {
    private MType type;
    private byte HEALTH_MULTIPLIER;
    private byte DAMAGE_MULTIPLIER;
    private byte ARMOR_MULTIPLIER;

    /**
     * Costruttore che pemette di selezionare il tipo
     * @param t Mtype
     * @param baseHealth vita iniziale
     * @param baseDamage danno iniziale
     * @param baseArmor armatura iniziale
     * @param baseLv armatura iniziale
     * @param healthMul moltiplicatore vita
     * @param damageMul moltiplicatore damage
     * @param armorMul moltiplicatore armor
     */
    public Monster(MType t, short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        if(baseHealth <= 1 || baseDamage <= 0 || baseArmor <= 0 || baseLv <= 0 || healthMul <= 0 || damageMul <= 0 || armorMul <= 0){
            throw new IllegalArgumentException("Valori di inizializzazione non corretti");
        }

        type = t;
        super.level = baseLv;
        super.maxHealth = baseHealth;
        super.health = baseHealth;
        super.attack = baseDamage;
        super.armor = baseArmor;
        super.name = Monster.selectName(t);

        //inizializzazione dei moltiplicatori
        HEALTH_MULTIPLIER = healthMul;
        DAMAGE_MULTIPLIER = damageMul;
        ARMOR_MULTIPLIER = armorMul;
    }

    /**
     * Costruttore che permette di modificare i valori di partenza di un mostro
     * @param t tipo mostro
     * @param name nome 
     * @param baseHealth vita iniziale
     * @param baseDamage danno iniziale
     * @param baseArmor armatura iniziale
     * @param baseLv armatura iniziale
     * @param healthMul moltiplicatore vita
     * @param damageMul moltiplicatore damage
     * @param armorMul moltiplicatore armor
     */
    public Monster(MType t, String name, short baseHealth, byte baseAttack, byte baseArmor, byte baseLv, byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        if(baseHealth <= 1 || baseAttack <= 0 || baseArmor <= 0 || baseLv <= 0 || healthMul <= 0 || damageMul <= 0 || armorMul <= 0){
            throw new IllegalArgumentException("Valori di inizializzazione non corretti");
        }

        type = t;
        super.level = baseLv;
        super.maxHealth = baseHealth;
        super.health = baseHealth;
        super.attack = baseAttack;
        super.armor = baseArmor;
        super.name = name;

        //inizializzazione dei moltiplicatori
        HEALTH_MULTIPLIER = healthMul;
        DAMAGE_MULTIPLIER = damageMul;
        ARMOR_MULTIPLIER = armorMul;
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
    private static String selectName(MType t){
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
        
        if((l-level)*HEALTH_MULTIPLIER < 0)
        throw new IllegalArgumentException("Overflow di armor (livello troppo alto):");
        maxHealth += (l-level)*HEALTH_MULTIPLIER;

        //check overflow di armor
        val = (byte)((l-level)*ARMOR_MULTIPLIER);
        if(val < 0 || Byte.MAX_VALUE - val < super.armor)
            throw new IllegalArgumentException("valore non corretto (livello troppo alto/basso):"+val);
        super.armor += val;
        
        //check overflow di damage
        val = (byte)((l-level)*DAMAGE_MULTIPLIER);
        if(val < 0 || Byte.MAX_VALUE - val < super.attack)
            throw new IllegalArgumentException("valore non corretto (livello troppo alto/basso): "+val);
        super.attack += val;

        level = l;
    }
}
