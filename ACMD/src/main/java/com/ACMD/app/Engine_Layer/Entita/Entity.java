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
    protected String history;
    protected short health, maxHealth;
    protected byte damage, armor, level;

    /**
     * Restituisce il livello attuale
     * @return level
     */
    public byte getLv(){
        return level;
    }

    /**
     * Valore della vita
     * @return health
     */
    public short getLife(){
        return health;
    }

    /**
     * Restituisce il valore massimo di vita del player
     * @return maxHealth
     */
    public short getMaxLife(){
        return maxHealth;
    }

    /**
     * Restituisce la 'storia' di un entita che descrive il personaggio
     * @return String descrizione del entità
     */
    public String getHistory(){
        return history;
    }

    /**
     * Nome del entità
     * @return name
     */
    public String getName(){
        return name;
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

    // ---- INCREMENTO CON CONTROLLO OVERFLOW ----
    protected void safeIncrementHealth(short amount)
    {
        if(amount < 0 || Short.MAX_VALUE < health + amount)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'health'(overflow)");
        
        health += amount;
    }

    protected void safeIncrementDamage(byte amount)
    {
        if(amount < 0 || Byte.MAX_VALUE < damage + amount)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'damage'(overflow)");
        
        damage += amount;
    }

    protected void safeIncrementArmor(byte amount)
    {
        if(amount < 0 || Byte.MAX_VALUE < armor + amount)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'armor'(overflow)");
        
        armor += amount;
    }

    // ---- DECREMENTO CON CONTROLLO OVERFLOW ----
    protected void safeDecrementHealth(short amount)
    {
        if(amount < 0 || health - amount < Short.MIN_VALUE)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'health'(overflow)");
        
        health -= amount;
    }

    protected void safeDecrementDamage(byte amount)
    {
        if(amount < 0 || damage - amount < Short.MIN_VALUE)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'damage'(overflow)");
        
        damage -= amount;
    }

    protected void safeDecrementArmor(byte amount)
    {
        if(amount < 0 || armor - amount < Short.MIN_VALUE)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'armor'(overflow)");
        
        armor -= amount;
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
    public short changeHealth(short value) throws IllegalArgumentException{
        if(value + health > Short.MAX_VALUE){
            throw new IllegalArgumentException("Il valore: " + health + "+" + value +" supera il valore max: " + Short.MAX_VALUE + "(overflow)");
        }
        else if(value + health < Short.MIN_VALUE){
            throw new IllegalArgumentException("Il valore: " + health + "+" + value +" è più piccolo del valore min: " + Short.MIN_VALUE + "(overflow)");
        }

        if(value + health <= maxHealth){
            health += value;
        }
        else{
            health = maxHealth;
        }

        return health;
    }

    // -------- METODI ASTRATTI --------
    /**
     * Imposta il livello ad un valore desiderato. Lancia IllegalArgumentException se il livello non è valido
     * Si noti che cambiano anche i valori: health, damage, armor.
     * @param l livello
     */
    public abstract void setLv(byte l) throws IllegalArgumentException;

    /**
     * Aggiugne un singolo item nel inventario
     * @param i item da inserire 
     * @return boolean true se è stato aggiunto false altrimenti
     */
    public abstract boolean addItem(Item i);

    /**
     * Rimuove un singolo item dal inventario
     * @param i item da eliminare
     * @return boolean true se è stato rimosso false altrimenti
     */
    public abstract boolean removeItem(Item i);

}
