package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.Item;

public class InventarioProvvisorio {
    public byte getWeight(){return 0;}
    public void addItem(Item i){}
    public boolean search(String name){return false;}//true = elemento gia in inventario
    public boolean removeItem(Item i){return true;}//true = elemto rimosso, false = elemento non esiste
}
