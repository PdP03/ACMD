package com.ACMD.app.Engine_Layer.Entita;

import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

/**
 * Questa classe in realta è una struct serve solo a memorizzare i valori di un item
 */
public class ItemValues {
    String description;
    ItemType type;
    byte weight, quantity, value;
}
