package com.ACMD.app.Engine_Layer.Entita;

/**
 * Classe che rappresenta una generica entità (Player o Mostro). Viene rappresentata dai parametri:
 * - health, vita del entità
 * - attack, danno del entità
 * - level, livello del entità
 * - name, nome del entità
 */
public abstract class Entity {
    protected String name;
    protected String history;
    protected short health, maxHealth;
    protected byte attack, armor, level;

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
     * @return attack
     */
    public byte getAttack(){
        return attack;
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
        if(amount < 0 || Byte.MAX_VALUE < attack + amount)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'attack'(overflow)");
        
        attack += amount;
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
        if(amount < 0 || attack - amount < Short.MIN_VALUE)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'attack'(overflow)");
        
        attack -= amount;
    }

    protected void safeDecrementArmor(byte amount)
    {
        if(amount < 0 || armor - amount < Short.MIN_VALUE)
            throw new IllegalArgumentException("Il valore "+ amount +" è troppo alto/basso per 'armor'(overflow)");
        
        armor -= amount;
    }
    
    /**
     * Cambia la vita del entità aggiungendo value. La vita può diventare negativa
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
     * Si noti che cambiano anche i valori: health, attack, armor.
     * @param l livello
     */
    public abstract void setLv(byte l) throws IllegalArgumentException;
}
