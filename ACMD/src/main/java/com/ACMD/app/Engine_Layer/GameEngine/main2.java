package com.ACMD.app.Engine_Layer.GameEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import com.ACMD.app.Engine_Layer.ParsePath;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Kernel_Layer.Menu.MenuValues;

public class main2 {
 /*  public static void main(String[] args){
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

                    //System.out.println(menuItem.cmdDescription);
                    //System.out.println(menuItem.cmdName);
                }
            }
        }

        String dir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Entita\\";
        String thisDir = ParsePath.getPath(dir, "");
        Vector<String> files = ParsePath.getFilesNameIn(dir);

        for(String file: files){
            //System.out.println(file);
            //System.out.println((new File(thisDir+file)).exists());
        }

        HashMap<entry, Integer> map = new HashMap<>();
        
        entry e = new entry("cio","cio");
        entry e2 = new entry("ciao","ciao");

        map.put(e, 1);
        map.put(e2, 2);

        System.out.println(map.containsKey(new entry("cio","cio")));
        System.out.println(map.containsKey(new entry("ciao","cia")));

        System.out.println(e.hashCode());
        System.out.println(e2.hashCode());

    }
    
}

class entry{
    public String s1;
    public String s2;

    public entry(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean equals(Object o){
        entry e = (entry)o;
        return s1 == e.s1;
    }

    @Override
    public int hashCode(){
        return s1.hashCode();
    }*/
}
