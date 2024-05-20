package com.ACMD.app.Engine_Layer.Entita;

public class Armatura extends Monster{
    final String story = "Io sono Armatura Fluttuante, non so come mai ma ora posso 'volare'. Ero tranquilla ad abbronzarmi sotto il sole"+
                        " quando ho sentito una forza, forse c'è qualcuno che sta creando dei campi magnetici.\n" +
                        "Anche se sono arrugginita troverò il colpevole e lo punirò! Cosi potrò continuare a rilassarmi.";
    
    /*
     * Costruttore senza parametri. Inizializzato con valori di default.
     */
    public Armatura(){
        super(MType.ARMATURA, null);
        super.history = story;
    }

    /*
     * Costruttore con nome. Il resto dei parametri hanno i valori di default
     */
    public Armatura(String name){
        super(MType.ARMATURA, name);
        super.history = story;
    }

    /**
     * Costruttore che inizializza a valori scelti
     * @param name String nome
     * @param health short vita iniziale del mostro
     * @param damage byte difesa iniziale del mostro
     * @param armor byte attacco iniziale del mostro
     */
    public Armatura(String name, short health, byte damage, byte armor){
        super(MType.ARMATURA, name, health, damage, armor);
        super.history = story;
    }
}
