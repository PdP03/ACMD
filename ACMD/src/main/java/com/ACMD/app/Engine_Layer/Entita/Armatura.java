package com.ACMD.app.Engine_Layer.Entita;

public class Armatura extends Monster{
    final String story = "Io sono Armatura Fluttuante, non so come mai ma ora posso 'volare'. Ero tranquilla ad abbronzarmi sotto il sole"+
                        " quando ho sentito una forza, forse c'è qualcuno che sta creando dei campi magnetici.\n" +
                        "Anche se sono arrugginita troverò il colpevole e lo punirò! Cosi potrò continuare a rilassarmi.";
    
    /*
     * Costruttore senza nome.
     */
    public Armatura(short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.ARMATURA, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }

    /*
     * Costruttore con nome
     */
    public Armatura(String name, short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.ARMATURA, name, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }
}
