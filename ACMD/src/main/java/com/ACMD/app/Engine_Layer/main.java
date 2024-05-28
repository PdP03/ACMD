package com.ACMD.app.Engine_Layer;

import java.util.ArrayList;
import java.util.Vector;

import com.ACMD.app.Engine_Layer.Mappa.NODE;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;

public class main {
    public static void main(String[] args){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
        xmlReader reader = new xmlReader(entityDir, "ItemStackConfig.xml");

        Vector<ItemStack> list = reader.getAllItemStack();
        for(ItemStack n: list){
            System.out.println(n.showDescription()+ " quantity:"+ n.getQuantity() +" weight:"+ n.getWeight()+" type:"+n.getType());
        }

    }
    
}
