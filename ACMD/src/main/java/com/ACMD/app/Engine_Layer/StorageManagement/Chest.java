                                           
package com.ACMD.app.Engine_Layer.StorageManagement;
/*
 * Implementare look/unlock della chest. La chest si sblocca se il player sconfigge il nemico
 */
public class Chest extends Storage
{
    boolean lock= true; //quindi bloccata

    public void lock()   {lock=false;}
    public void unlock() {lock=false;}
    public boolean getLock() {return lock;}
}

