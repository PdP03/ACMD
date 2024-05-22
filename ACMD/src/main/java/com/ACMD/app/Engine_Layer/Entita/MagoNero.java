package com.ACMD.app.Engine_Layer.Entita;

public class MagoNero extends Monster{
    final String story = "Io sono Mago Nero, studiando i circuiti elettrici mi sono accorto che possono essere scritti tramite funzioni di trasferimento.\n"+
                        "Stavo cercando di trovare la frequenza di taglio di un filtro analizzando l'uscita al variare del ingresso, tuttavia "+
                        "qualcuno mi ha rubato il condensatore INACCETTABILE se ti trovo verrai punito!";
    
    /*
     * Costruttore senza parametri. Inizializzato con valori di default.
     */
    public MagoNero(){
        super(MType.MAGO_OSCURO, null);
        super.history = story;
    }

    /*
     * Costruttore con nome. Il resto dei parametri hanno i valori di default
     */
    public MagoNero(String name){
        super(MType.MAGO_OSCURO, name);
        super.history = story;
    }

    /**
     * Costruttore che inizializza a valori scelti
     * @param name String nome
     * @param health short vita iniziale del mostro
     * @param damage byte difesa iniziale del mostro
     * @param armor byte attacco iniziale del mostro
     */
    public MagoNero(String name, short health, byte damage, byte armor){
        super(MType.MAGO_OSCURO, name, health, damage, armor);
        super.history = story;
    }
}