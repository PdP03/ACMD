package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.ParsePath;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Kernel_Layer.Menu.MenuValues;

public class main2 {
    public static void main(String[] args){
        final String MenuDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Kernel_Layer\\Menu\\";
        Vector<String> fileNames = ParsePath.getFilesNameIn(MenuDir);
       
        xmlReader reader;
        for(String name: fileNames){
           
            if(name.contains(".xml") && name.contains("Menu")){
                //System.out.println(name);
                reader = new xmlReader(MenuDir, name);

                Vector<MenuValues> values = reader.getMenuItems();
                //System.out.println(values.size());
                for(MenuValues menuItem: values){

                    System.out.println(menuItem.cmdDescription);
                    System.out.println(menuItem.cmdName);
                }
            }
        }

    }
    
}
