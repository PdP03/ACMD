package com.ACMD.app.Engine_Layer.Entita;

import java.util.Random;

public class MonsterFactory {
    Random generator;
    MType[] types;

    public MonsterFactory(){
        generator = new Random(System.currentTimeMillis());
        types = MType.values();
    }

    /**
     * Crea un mostro di tipo t con parametri di default
     * @param t MType tipo di mostro
     * @return Monster
     */
    public Monster create(MType t){
        byte healthMul = (byte)1, damageMul = (byte)2, armorMul = (byte)1, baseLv = (byte)1, baseHealth=(byte)3,baseArmor=(byte)1,baseDamage=(byte)2;
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino(baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case COBOLDO:
                return new Coboldo(baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case BOSS_DRAGO:
                return new BossDrago(baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case ARMATURA:
                return new Armatura(baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case MAGO_OSCURO:
                return new MagoNero(baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            default:
                return null;
        }
    }

    /**
     * Crea un mostro di tipo t e nome scelto con parametri di default
     * @param t MType tipo di mostro
     * @param name nome del mostro
     * @return Monster
     */
    public Monster create(MType t, String name){
        byte healthMul = (byte)1, damageMul = (byte)2, armorMul = (byte)1, baseLv = (byte)1, baseHealth=(byte)3,baseArmor=(byte)1,baseDamage=(byte)2;
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino(name, baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case COBOLDO:
                return new Coboldo(name, baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case BOSS_DRAGO:
                return new BossDrago(name, baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case ARMATURA:
                return new Armatura(name, baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            case MAGO_OSCURO:
                return new MagoNero(name, baseHealth, baseDamage, baseArmor, baseLv, healthMul, damageMul, armorMul);
            default:
                return null;
        }
    }

    /**
     * Crea un mostro di tipo t, nome scelto con parametri scelti
     * @param t MType tipo di mostro
     * @param name nome del mostro
     * @return Monster
     */
    public Monster create(MType t, String name, short health, byte damage, byte armor){
        byte healthMul = (byte)1, damageMul = (byte)2, armorMul = (byte)1, baseLv = (byte)1;
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino(name, health, damage, armor, baseLv, healthMul, damageMul, armorMul);
            case COBOLDO:
                return new Coboldo(name, health, damage, armor, baseLv, healthMul, damageMul, armorMul);
            case BOSS_DRAGO:
                return new BossDrago(name, health, damage, armor, baseLv, healthMul, damageMul, armorMul);
            case ARMATURA:
                return new Armatura(name, health, damage, armor, baseLv, healthMul, damageMul, armorMul);
            case MAGO_OSCURO:
                return new MagoNero(name, health, damage, armor, baseLv, healthMul, damageMul, armorMul);
            default:
                return null;
        }
    }

    /**
     * Crea un mostro con valori di default scelto a caso dalla lista delle enum
     * @return Monster creato casualmente
     */
    public Monster createRandom(){
        int choiche = generator.nextInt(types.length);
        return create(types[choiche]);
    } 
}
