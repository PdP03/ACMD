package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.Item;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

public class InventarioProvvisorio {
    public byte getWeigth(){return 0;}
    public void addItem(Item i){}
    public boolean searchType(ItemType t){return false;}//true = elemento gia in inventario
    public boolean removeItem(Item i){return true;}//true = elemto rimosso, false = elemento non esiste
}
