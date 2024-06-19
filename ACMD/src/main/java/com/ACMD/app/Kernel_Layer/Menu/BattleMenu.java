package com.ACMD.app.Kernel_Layer.Menu;

import java.util.Set;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.NoCommandExistException;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze.*;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale.ClearConsole;
import com.ACMD.app.Debug;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze.*;

public class BattleMenu extends Menu
{
    private static final String fileName = "BattleMenu.xml";
    private boolean charged= false;     //evitare carichi una seconda volta

    public BattleMenu(GameEngine generateMethods, GraphicAdapter gra)
    {
        super(fileName);

        if( generateMethods == null || gra == null )
         throw new IllegalArgumentException( "Non si stanno inserendo dei valori nel menù Battle" );

        if( !charged )
        {
            //: oridne alfabetico
            //loadMethods("", new );
            loadMethods("attack",        new Attack(generateMethods, gra));
            loadMethods("back",          new BackFromRoom(generateMethods));
            loadMethods("clear",         new ClearConsole(gra));
            loadMethods("help",          new HelpStanze(generateMethods,commandMap) );
                    // loadMethods("look", new );
                    // loadMethods("openchest",     new LookInsideChest(generateMethods));
            loadMethods("look",     new LookInsideChest(generateMethods) );
            loadMethods("showme",        new Showme(generateMethods));
            loadMethods("showinventario",new ShowInventario(generateMethods));
            loadMethods("take",          new TakeFromChest(generateMethods));
            loadMethods("trashitem",     new PlayerRemoveItem(generateMethods));
            loadMethods("trashstack",    new PlayerRemoveItemStack(generateMethods));
            loadMethods("use",           new PlayerUse(generateMethods,gra));

            if( super.commandMap.containsValue(null) )
            {
                System.out.println( commandMap+"\n" );
                throw new NoCommandExistException("BattleMenu: Esiste ancora una voce che non ha un comando associato");
            }
            charged= true;
        }
    }

    @Override
    protected void loadMethods(String nomeComando, Command c)
    {
        
        MenuValues voceCercare = new MenuValues(nomeComando,null);
        if( ! super.commandMap.containsKey( voceCercare ) )
            {
               // System.out.println("a"+nomeComando+"a");    //vedere se ha vicino caratteri strani
               // debugMethod();
                throw new MenuVoiceNotFound("Comando non trovato in file xml: "+nomeComando);
            }
            //per avere un debug ogni volta che si avvvia, che almeno tutte le voci che appaiano nel menù facciano qualcosa

        super.commandMap.put( new MenuValues(nomeComando,null),  c);
        //hash non fatto sulla descrizione
    }
    
    @Debug
    private void debugMethod()
    {
       // System.out.println( commandMap );
       // System.out.println( commandMap.containsKey( new MenuValues("back","") ) );
       MenuValues mn1 = new MenuValues("back"," ") ,
                  mn2 = new MenuValues("back","descrizione");
        System.out.println( mn1 == mn2 );
        System.out.println( mn1.equals(mn2) );

        System.out.println( mn1.hashCode() );
        System.out.println( mn2.hashCode() );

        System.out.println("    Set di chiavi: ");
        Set<MenuValues> keys = super.commandMap.keySet();
      //  System.out.println( keys.contains("back") ? "Trovata" : "Non trovata" );
      //  System.out.println( keys.contains( new MenuValues("back",null) ) ? "Trovata" : "Non trovata" );
      //  System.out.println( keys.contains( new MenuValues("back","Uscire da stanza") ) ? "Trovata" : "Non trovata" );

        System.out.println( keys );
       // System.out.println( keys.contains("back") );
       // System.out.println( keys.contains("look") );
        System.out.println( keys.contains( new MenuValues("back",null) ) );
        System.out.println( keys.contains( new MenuValues("back","DESCRIZIONE MANCANTE look") ) );

        System.out.println( super.commandMap.containsValue(null) );
     //   System.out.println( this.checkInTheMap( "back" ) );
     //   System.out.println( this.checkInTheMap( "non esiste" ) );
        //System.out.println( ( (String)null ) );
    }
}
