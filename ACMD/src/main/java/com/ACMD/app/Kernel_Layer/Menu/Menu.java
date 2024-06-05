package com.ACMD.app.Kernel_Layer.Menu;

import java.util.Map;
import java.util.Vector;

public abstract class Menu 
{
    private Map<String, String> commandMap; //chiave: nome comando ; valore: descrizione
  
    public Vector<String> getElement()
    {
        return null;
    }
    public String toString()
    {
        String s="";

        Object keys[]= (commandMap.keySet() ).toArray();

        for(int i=0; i<keys.length; i++)    //stupidi noi che vogliamo usare una mappa al posto di un array con 2 stringhe
         s+= (String)(keys[i])+'\n';        //? perché sta volta non rompre per il cast
        return s;
    }
}
