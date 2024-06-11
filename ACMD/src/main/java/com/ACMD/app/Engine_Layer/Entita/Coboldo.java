package com.ACMD.app.Engine_Layer.Entita;

public class Coboldo extends Monster{
    final String story = "Io sono Coboldo e vengo dal regno dei CAMPI VETTORIALI a più dimensioni.\nMi stavo divertendo a dilatare e restringere "+
                        "gli oggetti ma qualcuno mi ha rubato un autovettore e non posso piu farlo :( !!! Troverò il colpevole";
    
    /*
     * Costruttore senza nome.
     */
    public Coboldo(short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.COBOLDO, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }

    /*
     * Costruttore con nome
     */
    public Coboldo(String name, short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.COBOLDO, name, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }
}
