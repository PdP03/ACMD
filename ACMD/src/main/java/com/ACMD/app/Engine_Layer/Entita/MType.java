package com.ACMD.app.Engine_Layer.Entita;

public enum MType {
    MOSTRO_MARINO(0),
    COBOLDO(1),
    BOSS_DRAGO(2),
    ARMATURA(3),
    MAGO_OSCURO(4);

    private int id;
    //------- costruttore richiamato in automatico dal compilatore--------
    MType(int index){
        id = index;
    }

    /**
     * Restituisce l'id di una enum
     * @return
     */
    public int getId(){
        return id;
    }
}
