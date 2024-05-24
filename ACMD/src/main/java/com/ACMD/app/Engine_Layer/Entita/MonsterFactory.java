package com.ACMD.app.Engine_Layer.Entita;

import java.util.Random;
import java.util.Vector;

public class MonsterFactory {
    final String fileName = "MonsterConfig.xml";
    Random generator;
    Vector<MonsterValues> defaultValues;
    MType[] types;

    public MonsterFactory(){
        generator = new Random(System.currentTimeMillis());
        types = MType.values();

        xmlReader reader = new xmlReader(fileName);
        defaultValues = reader.getMonsterValues();
    }

    /**
     * Crea un mostro di tipo t con parametri di default
     * @param t MType tipo di mostro
     * @return Monster
     */
    public Monster create(MType t){
        switch(t){
            case MOSTRO_MARINO:
                return new MostroMarino(defaultValues.get(MType.MOSTRO_MARINO.getId()).health, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).damage, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).armor, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).level, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).healthMul, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).damageMul, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).armorMul);
            case COBOLDO:
                return new Coboldo(defaultValues.get(MType.COBOLDO.getId()).health, 
                                    defaultValues.get(MType.COBOLDO.getId()).damage, 
                                    defaultValues.get(MType.COBOLDO.getId()).armor, 
                                    defaultValues.get(MType.COBOLDO.getId()).level, 
                                    defaultValues.get(MType.COBOLDO.getId()).healthMul, 
                                    defaultValues.get(MType.COBOLDO.getId()).damageMul, 
                                    defaultValues.get(MType.COBOLDO.getId()).armorMul);
            case BOSS_DRAGO:
                return new BossDrago(defaultValues.get(MType.BOSS_DRAGO.getId()).health, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).damage, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).armor, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).level, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).healthMul, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).damageMul, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).armorMul);
            case ARMATURA:
                return new Armatura(defaultValues.get(MType.ARMATURA.getId()).health, 
                                    defaultValues.get(MType.ARMATURA.getId()).damage, 
                                    defaultValues.get(MType.ARMATURA.getId()).armor, 
                                    defaultValues.get(MType.ARMATURA.getId()).level, 
                                    defaultValues.get(MType.ARMATURA.getId()).healthMul, 
                                    defaultValues.get(MType.ARMATURA.getId()).damageMul, 
                                    defaultValues.get(MType.ARMATURA.getId()).armorMul);
            case MAGO_OSCURO:
                return new MagoNero(defaultValues.get(MType.MAGO_OSCURO.getId()).health, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).damage, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).armor, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).level, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).healthMul, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).damageMul, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).armorMul);
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
                return new MostroMarino(name, defaultValues.get(MType.MOSTRO_MARINO.getId()).health, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).damage, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).armor, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).level, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).healthMul, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).damageMul, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).armorMul);
            case COBOLDO:
                return new Coboldo(name, defaultValues.get(MType.COBOLDO.getId()).health, 
                                    defaultValues.get(MType.COBOLDO.getId()).damage, 
                                    defaultValues.get(MType.COBOLDO.getId()).armor, 
                                    defaultValues.get(MType.COBOLDO.getId()).level, 
                                    defaultValues.get(MType.COBOLDO.getId()).healthMul, 
                                    defaultValues.get(MType.COBOLDO.getId()).damageMul, 
                                    defaultValues.get(MType.COBOLDO.getId()).armorMul);
            case BOSS_DRAGO:
                return new BossDrago(name, defaultValues.get(MType.BOSS_DRAGO.getId()).health, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).damage, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).armor, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).level, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).healthMul, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).damageMul, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).armorMul);
            case ARMATURA:
                return new Armatura(name, defaultValues.get(MType.ARMATURA.getId()).health, 
                                    defaultValues.get(MType.ARMATURA.getId()).damage, 
                                    defaultValues.get(MType.ARMATURA.getId()).armor, 
                                    defaultValues.get(MType.ARMATURA.getId()).level, 
                                    defaultValues.get(MType.ARMATURA.getId()).healthMul, 
                                    defaultValues.get(MType.ARMATURA.getId()).damageMul, 
                                    defaultValues.get(MType.ARMATURA.getId()).armorMul);
            case MAGO_OSCURO:
                return new MagoNero(name, defaultValues.get(MType.MAGO_OSCURO.getId()).health, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).damage, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).armor, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).level, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).healthMul, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).damageMul, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).armorMul);
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
                return new MostroMarino(name, health, damage, armor, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).level, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).healthMul, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).damageMul, 
                                        defaultValues.get(MType.MOSTRO_MARINO.getId()).armorMul);
            case COBOLDO:
                return new Coboldo(name, health, damage, armor,
                                    defaultValues.get(MType.COBOLDO.getId()).level, 
                                    defaultValues.get(MType.COBOLDO.getId()).healthMul, 
                                    defaultValues.get(MType.COBOLDO.getId()).damageMul, 
                                    defaultValues.get(MType.COBOLDO.getId()).armorMul);
            case BOSS_DRAGO:
                return new BossDrago(name, health, damage, armor,
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).level, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).healthMul, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).damageMul, 
                                    defaultValues.get(MType.BOSS_DRAGO.getId()).armorMul);
            case ARMATURA:
                return new Armatura(name, health, damage, armor, 
                                    defaultValues.get(MType.ARMATURA.getId()).level, 
                                    defaultValues.get(MType.ARMATURA.getId()).healthMul, 
                                    defaultValues.get(MType.ARMATURA.getId()).damageMul, 
                                    defaultValues.get(MType.ARMATURA.getId()).armorMul);
            case MAGO_OSCURO:
                return new MagoNero(name, health, damage, armor,
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).level, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).healthMul, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).damageMul, 
                                    defaultValues.get(MType.MAGO_OSCURO.getId()).armorMul);
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
