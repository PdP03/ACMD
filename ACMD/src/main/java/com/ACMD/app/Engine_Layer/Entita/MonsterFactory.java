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
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino();
            case COBOLDO:
                return new Coboldo();
            case BOSS_DRAGO:
                return new BossDrago();
            case ARMATURA:
                return new Armatura();
            case MAGO_OSCURO:
                return new MagoNero();
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
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino(name);
            case COBOLDO:
                return new Coboldo(name);
            case BOSS_DRAGO:
                return new BossDrago(name);
            case ARMATURA:
                return new Armatura(name);
            case MAGO_OSCURO:
                return new MagoNero(name);
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
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino(name, health, damage, armor);
            case COBOLDO:
                return new Coboldo(name, health, damage, armor);
            case BOSS_DRAGO:
                return new BossDrago(name, health, damage, armor);
            case ARMATURA:
                return new Armatura(name, health, damage, armor);
            case MAGO_OSCURO:
                return new MagoNero(name, health, damage, armor);
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
