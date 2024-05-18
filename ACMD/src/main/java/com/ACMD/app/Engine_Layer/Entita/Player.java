package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.Arma;
import com.ACMD.app.Engine_Layer.StorageManagement.Armatura;
import com.ACMD.app.Engine_Layer.StorageManagement.Item;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

/**
 * Classe che rappresenta il player
 */
public class Player extends Entity{
    final byte MAX_INVENTORY_WEIGTH = 10;
    final byte BASE_DAMAGE = 1;
    final byte BASE_ARMOR = 1;
    final byte BASE_HEALTH = 5;
    final byte BASE_LEVEL = 1;

    /**
     * Inizializza un giocatore con i parametri di default
     * @param name nome del player
     */
    public Player(String name){
        super.level = BASE_LEVEL;
        super.health = BASE_HEALTH;
        super.damage = BASE_DAMAGE;
        super.armor = BASE_ARMOR;
        super.name = name;
    }

    boolean removeItem(Item i){
        if(inv.removeItem(i)){
            throw new IllegalArgumentException();
        }
        
        //il player può avere una sola arma è una sola armatura
        switch(i.getType()){
            case ARMA:
                super.damage -= ((Arma)i).getDamage();
                return true;

            case ARMATURA:
                super.armor -= ((Armatura)i).getDifense();
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
                super.damage += ((Arma)i).getDamage();
                inv.addItem(i);
                return true;

            case ARMATURA:
                if(inv.searchType(i.getType())){
                    return false;
                }
                inv.addItem(i);
                super.armor += ((Armatura)i).getDifense();
                return true;

            default:        //neccessario poichè lo switch vuole tutti i case definiti nella enum
                return true;
        }
    }
}
