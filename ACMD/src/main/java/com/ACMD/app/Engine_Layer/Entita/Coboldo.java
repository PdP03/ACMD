package com.ACMD.app.Engine_Layer.Entita;

public class Coboldo extends Monster{
    final String story = "Io sono Coboldo e vengo dal reglio dei CAMPI VETTORIALI.\nQualcuno mi ha rubato un autovettore devo riprenderlo!!";
    
    /*
     * Costruttore senza parametri. Inizializzato con valori di default.
     */
    public Coboldo(){
        super(MType.COBOLDO, null);
        super.history = story;
    }

    /*
     * Costruttore con nome. Il resto dei parametri hanno i valori di default
     */
    public Coboldo(String name){
        super(MType.COBOLDO, name);
        super.history = story;
    }

    /**
     * Costruttore che inizializza a valori scelti
     * @param name String nome
     * @param health short vita iniziale del mostro
     * @param damage byte difesa iniziale del mostro
     * @param armor byte attacco iniziale del mostro
     */
    public Coboldo(String name, short health, byte damage, byte armor){
        super(MType.COBOLDO, name);
        super.history = story;

        super.maxHealth = health;
        super.health = health;
        super.damage = damage;
        super.armor = armor;
    }
}
