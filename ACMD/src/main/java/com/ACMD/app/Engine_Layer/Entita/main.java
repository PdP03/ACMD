package com.ACMD.app.Engine_Layer.Entita;

import java.util.ArrayList;

import com.ACMD.app.Engine_Layer.Mappa.NODE;

public class main {
    public static void main(String[] args){
        xmlReader reader = new xmlReader("MappaConfig.xml");

        ArrayList<NODE> list = reader.getAllNode();
        for(NODE n: list){
            System.out.println(n.getStringCoord());
        }

        ArrayList<ConnectionValues> list1 =reader.getAllConnection();
        for(ConnectionValues val: list1){
            System.out.println(val.x+", "+val.y+", "+val.x2+", "+val.y2+", "+val.direction1+", "+val.direction2);
        } 
    }
    
}
