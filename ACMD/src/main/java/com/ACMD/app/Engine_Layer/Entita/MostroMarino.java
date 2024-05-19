package com.ACMD.app.Engine_Layer.Entita;

public class MostroMarino extends Monster{
    final String story = "Io sono Mostro Marino, il livello del acqua è BIBO disabile non riesco a controllarla!!!\nDevo trovare il colpevole.";
    
    /*
     * Costruttore senza parametri. Inizializzato con valori di default.
     */
    public MostroMarino(){
        super(MType.MOSTRO_MARINO, null);
        super.history = story;
    }

    /*
     * Costruttore con nome. Il resto dei parametri hanno i valori di default
     */
    public MostroMarino(String name){
        super(MType.MOSTRO_MARINO, name);
        super.history = story;
    }

    /**
     * Costruttore che inizializza a valori scelti
     * @param name String nome
     * @param health short vita iniziale del mostro
     * @param damage byte difesa iniziale del mostro
     * @param armor byte attacco iniziale del mostro
     */
    public MostroMarino(String name, short health, byte damage, byte armor){
        super(MType.MOSTRO_MARINO, name);
        super.history = story;

        super.maxHealth = health;
        super.health = health;
        super.damage = damage;
        super.armor = armor;
    }
}
