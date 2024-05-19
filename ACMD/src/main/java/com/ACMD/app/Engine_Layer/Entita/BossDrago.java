package com.ACMD.app.Engine_Layer.Entita;

public class BossDrago extends Monster{
    final String story = "Io sono il DRAGO, odio tutti perchè non mi crede nessuno agh! IO voglio dimostrare che il MOTO PERPETUO ESISTE per farlo userò te. Verrai bruciato in un forno è ricavero tutta la tua energia!!!";
    
    /*
     * Costruttore senza parametri. Inizializzato con valori di default.
     */
    public BossDrago(){
        super(MType.BOSS_DRAGO, null);
        super.history = story;
    }

    /*
     * Costruttore con nome. Il resto dei parametri hanno i valori di default
     */
    public BossDrago(String name){
        super(MType.BOSS_DRAGO, name);
        super.history = story;
    }

    /**
     * Costruttore che inizializza a valori scelti
     * @param name String nome
     * @param health short vita iniziale del mostro
     * @param damage byte difesa iniziale del mostro
     * @param armor byte attacco iniziale del mostro
     */
    public BossDrago(String name, short health, byte damage, byte armor){
        super(MType.BOSS_DRAGO, name);
        super.history = story;

        super.maxHealth = health;
        super.health = health;
        super.damage = damage;
        super.armor = armor;
    }
}
