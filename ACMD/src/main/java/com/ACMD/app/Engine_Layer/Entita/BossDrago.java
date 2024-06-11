package com.ACMD.app.Engine_Layer.Entita;

public class BossDrago extends Monster{
    final String story = "Io sono il DRAGO, odio tutti perchè non mi crede nessuno argh! IO voglio dimostrare che il MOTO PERPETUO ESISTE per farlo "+
                        "userò te, verrai bruciato in un forno è ricaverò tutta la tua energia mediante delle turbine che non si fermeranno MAI"+
                        " ahahah!!! Nessuno potrà fermarmi";
    
    /*
     * Costruttore senza nome.
     */
    public BossDrago(short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.BOSS_DRAGO, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }

    /*
     * Costruttore con nome
     */
    public BossDrago(String name, short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.BOSS_DRAGO, name, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }
}
