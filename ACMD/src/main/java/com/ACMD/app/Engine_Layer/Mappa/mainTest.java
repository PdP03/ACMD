package com.ACMD.app.Engine_Layer.Mappa;

import java.util.ArrayList;

import com.ACMD.app.Engine_Layer.xmlReader;

public class mainTest {
    public static void main(String[] args){
        final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Mappa\\";
        xmlReader reader = new xmlReader(entityDir, "MappaConfig.xml");

        ArrayList<NODE> nodes = reader.getAllNode();
        Coordinates cord;
        for(NODE n: nodes){
            cord = n.getCoord();
            System.out.println("X: "+cord.getX()+" Y:"+cord.getY());
        }

    }
    
}
