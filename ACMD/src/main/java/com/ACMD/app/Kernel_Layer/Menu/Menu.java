package com.ACMD.app.Kernel_Layer.Menu;

import java.util.Map;
import java.util.Vector;        //comodità per ottenere i dati

import com.ACMD.app.Kernel_Layer.Prompt.CommandPattern.Command;

public abstract class Menu 
{
    private Map<MenuVoice, Command> commandMap; //chiave: nome comando ; valore: descrizione
  
    public Vector<String> getElement()
    {
        return null;
    }
    public String toString()
    {
        String s="";

        Object keys[]= (commandMap.keySet() ).toArray();

        for(int i=0; i<keys.length; i++)    //stupidi noi che vogliamo usare una mappa al posto di un array con 2 stringhe -> e infatti cosa abbiamo finito per fare? Una mappa ancora più complessa che usa una classe a 2 entrate
         s+= (String)(keys[i])+'\n';        //? perché sta volta non rompre per il cast
        return s;
    }

    private class MenuVoice
    {
        String voice, description;      //dei comandi
        public int hashCode()
        {
            return voice.hashCode();    //i comandi devono essere unici, quindi nessun problema di conflitti
        }
    }
}
