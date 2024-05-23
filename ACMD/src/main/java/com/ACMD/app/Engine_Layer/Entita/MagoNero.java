package com.ACMD.app.Engine_Layer.Entita;

public class MagoNero extends Monster{
    final String story = "Io sono Mago Nero, studiando i circuiti elettrici mi sono accorto che possono essere scritti tramite funzioni di trasferimento.\n"+
                        "Stavo cercando di trovare la frequenza di taglio di un filtro analizzando l'uscita al variare del ingresso, tuttavia "+
                        "qualcuno mi ha rubato il condensatore INACCETTABILE se ti trovo verrai punito!";
    
    /*
     * Costruttore senza nome.
     */
    public MagoNero(short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.MAGO_OSCURO, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }

    /*
     * Costruttore con nome
     */
    public MagoNero(String name, short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.MAGO_OSCURO, name, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }
}
