package com.ACMD.app.Engine_Layer.GameEngine;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.ParsePath;
import java.io.File;

public class main4 {
    public static void main(String[] args) {
        System.out.println(ParsePath.getPath("\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\", "ItemStackConfig.xml"));
        File file = new File(ParsePath.getPath("\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\", "ItemStackConfig.xml"));   
        System.out.println(file.exists());
        xmlReader reader = new xmlReader("\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\", "ItemStackConfig.xml");
        System.out.println(reader.getAllItemStack()); 
    }
}
