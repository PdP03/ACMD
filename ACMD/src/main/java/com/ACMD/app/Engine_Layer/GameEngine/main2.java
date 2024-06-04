package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.MenuValues;
import com.ACMD.app.Engine_Layer.xmlReader;

public class main2 {
    public static void main(String[] args){
        final String thisDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Kernel_Layer\\Menu\\";
         xmlReader readerMenu = new xmlReader(thisDir, "test.xml");

         Vector<MenuValues> menuItems = readerMenu.getMenuItems();
        for(MenuValues m: menuItems){
            System.out.println(m.cmdName);
            System.out.println(m.cmdDescription);
        }

    }
    
}
