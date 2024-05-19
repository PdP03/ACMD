package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.Item;

public abstract class Monster extends Entity{
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
        level = l;

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
