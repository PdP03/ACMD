package com.ACMD.app.Engine_Layer.Entita;

import java.util.ArrayList;
import java.util.Vector;

import com.ACMD.app.Engine_Layer.MonsterValues;
import com.ACMD.app.Engine_Layer.RoomValues;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.NODE;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;

public class mainTest {
    public static void main(String[] args){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
        final String entityDir1 = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Entita\\";
        xmlReader reader = new xmlReader(entityDir, "ItemStackConfig.xml");

        Vector<ItemStack> items = reader.getAllItemStack();
    

        for(ItemStack item: items){
            System.out.println(item.getName()+" "+item.getType());
        }

        reader = new xmlReader(entityDir1, "MonsterConfig.xml");

        reader.getMonsterValues();
    

        

    }
    
}
