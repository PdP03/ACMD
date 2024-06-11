package com.ACMD.app.Engine_Layer.Entita;

public class MostroMarino extends Monster{
    final String story = "Io sono Mostro Marino, mi stavo divertendo ad alzare il livello dell' acqua con un controllore PID."+
                        " Ma ad un certo punto l'acqua non rispondeva più al controllo ed è diventata BIBO instabile non"+
                        " riesco a controllarla argh!!!\nDevo trovare il colpevole.";
    
    /*
     * Costruttore senza nome.
     */
    public MostroMarino(short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.MOSTRO_MARINO, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }

    /*
     * Costruttore con nome
     */
    public MostroMarino(String name, short baseHealth, byte baseDamage, byte baseArmor, byte baseLv , byte healthMul, byte damageMul, byte armorMul) throws IllegalArgumentException{
        super(MType.MOSTRO_MARINO, name, baseHealth, baseDamage, baseArmor, baseLv , healthMul, damageMul, armorMul);
        super.history = story;
    }
}
