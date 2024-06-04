package com.ACMD.app.Kernel_Layer.Menu;

import java.util.Map;
 import java.util.Set;
import java.util.Vector;        //comodità per ottenere i dati

import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public abstract class Menu 
{
    protected Map<MenuValues, Command> commandMap; //chiave: nome comando, valore: il metodo da richiamare
    final String thisDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Kernel_Layer\\Menu\\";

    public Menu(String fileDati)
    {
        xmlReader readerMenu = new xmlReader(thisDir, fileDati);

        //_carica solo i nomi e le relative descrizioni ; ai comandi ci pensa la singola classe
        Vector<MenuValues> menuItems = readerMenu.getMenuItems();
        Command defaultVal = null;      //null perché non ha ancora un comando

        for(int i=0; i<menuItems.size(); i++)
         commandMap.put( menuItems.get(i), defaultVal); //carico nomi e descrizioni nelle mappe
    }
  
   /* public Vector<String> getElement()
    {
        return null;
    }*/
    public String toString()
    {
        String s="";
        Set<MenuValues> keys = commandMap.keySet();

        for(int i=0; i<keys.size(); i++)
         s+= keys.toString() +'\n';
        
        return s;
    }

    // ## Metodi Private
    protected abstract void loadMethods();
    //saranno le singole classi a pensare a caricare i comandi corrispondeti con degli switch

    // ## classe privata per comodità
/* 
    private class MenuVoice
    {
        String voice, description;      //dei comandi
        public int hashCode()
        {
            return voice.hashCode();    //i comandi devono essere unici, quindi nessun problema di conflitti
        }
        public String toString()
        {
            return voice + "\n  " + description; 
        }
    }*/
}
