                                           
package com.ACMD.app.Engine_Layer.StorageManagement;
/*
 * Implementare look/unlock della chest. La chest si sblocca se il player sconfigge il nemico
 */
public class Chest extends Storage
{
    private boolean lock= true; //quindi bloccata

    public void lock()   {lock= true;}
    public void unlock() {lock=false;}
    public boolean isClosed() {return lock;}
}

