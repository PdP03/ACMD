package com.ACMD.app.Engine_Layer.Entita;

public class Coboldo extends Monster{
    final String story = "Io sono Coboldo e vengo dal regno dei CAMPI VETTORIALI a più dimensioni.\nMi stavo divertendo a dilatare e restringere "+
                        "gli oggetti ma qualcuno mi ha rubato un autovettore e non posso piu farlo :( !!! Troverò il colpevole";
    
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
        super(MType.COBOLDO, name, health, damage, armor);
        super.history = story;
    }
}
