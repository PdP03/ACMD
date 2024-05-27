package com.ACMD.app.Engine_Layer.Entita;

public class main {
    public static void main(String[] args){
        xmlReader reader = new xmlReader("ItemStackConfig.xml");

        reader.getAllItemStack();
    }
    
}
