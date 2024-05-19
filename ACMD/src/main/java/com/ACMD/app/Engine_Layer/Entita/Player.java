package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.Arma;
import com.ACMD.app.Engine_Layer.StorageManagement.Armatura;
import com.ACMD.app.Engine_Layer.StorageManagement.Item;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

/**
 * Classe che rappresenta il player
 */
public class Player extends Entity{
    private final byte MAX_INVENTORY_WEIGTH = 10;
    private final byte BASE_DAMAGE = 1;
    private final byte BASE_ARMOR = 1;
    private final byte BASE_HEALTH = 5;
    private final byte BASE_LEVEL = 1;
    private final byte HEALTH_MULTIPLIER = 2;
    private final byte DAMAGE_MULTIPLIER = 1;
    private final byte ARMOR_MULTIPLIER = 1;

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
    }

    //imposta il nuovo livello e aggiorna la vita max
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

    /**
     * Rimuove un item dall'inventario del player. Lancia IllegalArgumentException se l'inventario è vuoto
     * @param i item da eliminare
     * @return boolean true se è stato eliminato 
     */
    public boolean removeItem(Item i) throws IllegalArgumentException{
        if(!inv.removeItem(i)){
            throw new IllegalArgumentException();
        }
        
        //il player può avere una sola arma è una sola armatura
        switch(i.getType()){
            case ARMA:
                super.safeDecrementDamage(((Arma)i).getDamage());
                return true;

            case ARMATURA:
                super.safeDecrementArmor(((Armatura)i).getDifense());
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
    public boolean addItem(Item i) throws IllegalArgumentException{
        if(i.getWeight() + inv.getWeigth() > MAX_INVENTORY_WEIGTH){
            throw new IllegalArgumentException();
        }
        
        //il player può avere una sola arma è una sola armatura
        switch(i.getType()){
            case ARMA:
                if(inv.searchType(i.getType())){
                    return false;
                }
                super.safeIncrementDamage(((Arma)i).getDamage());
                inv.addItem(i);
                return true;

            case ARMATURA:
                if(inv.searchType(i.getType())){
                    return false;
                }
                inv.addItem(i);
                super.safeIncrementArmor(((Armatura)i).getDifense());
                return true;

            default:        //neccessario poichè lo switch vuole tutti i case definiti nella enum
                return true;
        }
    }
}
