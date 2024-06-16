package com.ACMD.app.Kernel_Layer.Menu;

import java.util.HashMap;
 import java.util.Set;          //ottenere le chiavi
import java.util.Vector;        //comodità per ottenere i dati

import com.ACMD.app.Debug;
import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

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

         //debugMethod( menuItems );
    }
  
    public Command checkInTheMap(String cmmdName)
    {
        return commandMap.get( new MenuValues(cmmdName,null) );
            //controllo solo tramite il nome e non la descrizione
    }
    public String toString()
    {
        Set<MenuValues> keys = commandMap.keySet();
        return keys.toString()+"\n";
    }

    // ## Metodi Private
    protected abstract void loadMethods(String nomeComando, Command c);
    //saranno le singole classi a pensare a caricare i comandi corrispondeti con degli switch
    //devo passare anche il Command su cui istanziare i metodi
    @Debug
    private void debugMethod( Vector<MenuValues> menuItems )
    {
        if( ! commandMap.containsKey( menuItems.get(0) ) ) { System.out.println("Fatale"); System.exit(1); }
          else
            {
                System.out.println("Menu"+menuItems.get(0)+"Menu");
                System.out.println( menuItems.get(0).equals( new MenuValues( "look","" ) ) );
                System.out.println( (menuItems.get(0).cmdName).length() );
                System.out.println( (menuItems.get(0).cmdName).matches( "look" ) );

            }
    }
}
