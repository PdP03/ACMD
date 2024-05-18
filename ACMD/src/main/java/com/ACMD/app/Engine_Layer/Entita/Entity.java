package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.Item;

/**
 * Classe che rappresenta una generica entità (Player o Mostro). Viene rappresentata dai parametri:
 * - health, vita del entità
 * - damage, danno del entità
 * - level, livello del entità
 * - name, nome del entità
 * - inv, inventario (insieme degli item trasportati)
 */
public abstract class Entity {
    protected InventarioProvvisorio inv = new InventarioProvvisorio();
    protected String name;
    protected String history;//probabilmente va spostata nelle effettive implementazioni dei mostri
    protected byte level;
    protected short health;
    protected byte damage;
    protected byte armor;

    /**
     * Restituisce il livello attuale
     * @return level
     */
    public byte getLv(){
        return level;
    }

    /**
     * Imposta il livello ad un valore desiderato
     * @param l livello
     */
    public void setLv(byte l){
        level = l;
    }

    /**
     * Valore della vita
     * @return health
     */
    public short getLife(){
        return health;
    }

    /**
     * Valore del danno che fa ad un altra entity
     * @return damage
     */
    public byte getDamage(){
        return damage;
    }

    /**
     * Valore della difesa del player
     * @return armor
     */
    public byte getArmor(){
        return armor;
    }

    /**
     * Nome del entità
     * @return name
     */
    public String getName(){
        return name;
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
    
    /**
     * Cambia la vita del entità aggiungendo value. 
     * Lancia IllegalArgumentException se health va in overflow
     * @param value quantita da aggiungere
     * @return health nuovo valore impostato
     */
    public short changehealth(short value) throws IllegalArgumentException{
        if(value > Short.MAX_VALUE - health){
            throw new IllegalArgumentException("Il valore: " + health + "+" + value +" supera il valore max " + Short.MAX_VALUE);
        }
        health += value;

        return health;
    }

    /**
     * Aggiugne un singolo item nel inventario
     * @param i item da inserire 
     * @return boolean true se è stato aggiunto false altrimenti
     */
    abstract boolean addItem(Item i);

    /**
     * Rimuove un singolo item dal inventario
     * @param i item da eliminare
     * @return boolean true se è stato rimosso false altrimenti
     */
    abstract boolean removeItem(Item i);

}
