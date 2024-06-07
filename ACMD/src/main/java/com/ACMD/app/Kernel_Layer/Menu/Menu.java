package com.ACMD.app.Kernel_Layer.Menu;

import java.util.HashMap;
 import java.util.Set;          //ottenere le chiavi
import java.util.Vector;        //comodità per ottenere i dati

import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale.ErroreInput;


public abstract class Menu 
{
    protected HashMap<MenuValues, Command> commandMap; //chiave: nome comando, valore: il metodo da richiamare
    final String thisDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Kernel_Layer\\Menu\\";

    public Menu(String fileDati)
    {
        xmlReader readerMenu = new xmlReader(thisDir, fileDati);

        //_carica solo i nomi e le relative descrizioni ; ai comandi ci pensa la singola classe
        Vector<MenuValues> menuItems = readerMenu.getMenuItems();
        commandMap = new HashMap<MenuValues,Command>();
        
        final Command defaultVal = null;      //null perché non ha ancora un comando

        for(int i=0; i<menuItems.size(); i++)
         commandMap.put( menuItems.get(i), defaultVal); //carico nomi e descrizioni nelle mappe

         if( ! commandMap.containsKey( menuItems.get(0) ) ) { System.out.println("Fatale"); System.exit(1); }
          else
            {
                System.out.println("Menu"+menuItems.get(0)+"Menu");
                System.out.println( menuItems.get(0).equals( new MenuValues( "look","" ) ) );
            }
    }
  
    public Command checkInTheMap(String cmmdName)
    {
        Command cmd= commandMap.get( new MenuValues(cmmdName,null) );    //controllo solo tramite il nome e non la descrizione
        
        if(cmd==null)
         new ErroreInput(null);     //se non è stato trovato

        return cmd;
    }
    public String toString()
    {
        String s="";
        Set<MenuValues> keys = commandMap.keySet();

     /*    for(int i=0; i<keys.size(); i++)
         s+= keys..toString() +'\n';*/
        
        return keys.toString()+"\n";
    }

    // ## Metodi Private
    protected abstract void loadMethods(String nomeComando, Command c);
    //saranno le singole classi a pensare a caricare i comandi corrispondeti con degli switch
    //devo passare anche il Command su cui istanziare i metodi

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
