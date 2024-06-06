package com.ACMD.app.Kernel_Layer.Menu;

import java.util.Set;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.NoCommandExistException;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze.*;
//import com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze.PlayerRemoveItemStack;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze.*;
//import com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze.LookInsideChest;

public class BattleMenu extends Menu
{
    private static final String fileName = "BattleMenu.xml";
    private boolean charged= false;     //evitare carichi una seconda volta

    public BattleMenu(GameEngine generateMethods)
    {
        super(fileName);
        if( !charged )
        {
            //: oridne alfabetico
            loadMethods("back",          new BackFromRoom(generateMethods));
            loadMethods("help",          new HelpStanze(generateMethods) );
           // loadMethods("look", new LookInsideChest(generateMethods));
            loadMethods("openchest",     new LookInsideChest(generateMethods));
           // loadMethods("showchest",c);
            loadMethods("showinventario",new ShowInventario(generateMethods));
            loadMethods("trashit",       new PlayerRemoveItem(generateMethods));
            loadMethods("trashstack",    new PlayerRemoveItemStack(generateMethods));

            if( commandMap.containsValue(null) )
             throw new NoCommandExistException("Esiste ancora una voce che non ha un comando associato");

            charged= true;
        }
    }

    @Override
    protected void loadMethods(String nomeComando, Command c)
    {
        if( ! commandMap.containsKey( new MenuValues(nomeComando,null) ) )
            {
                debugMethod();
                throw new MenuVoiceNotFound(nomeComando);
            }
            //per avere un debug ogni volta che si avvvia, che almeno tutte le voci che appaiano nel menù facciano qualcosa

           // Command cmd;
            /*
        switch (nomeComando) {
            case "help":        cmd= ; break;
            case "openchest":   cmd= ; break;
            case "take":        cmd= ; break;

            case "trashit":     cmd= ; break;
            case "back":        cmd= ; break;
            case "look":        cmd= ; break;

            case "showchest":       cmd= ; break;
            case "showinventario":  cmd= ; break;
        
            default:
            throw new MenuVoiceNotFound();
            //per avere un debug ogni volta che si avvvia, che almeno tutte le voci che appaiano nel menù facciano qualcosa
        }
*/
        commandMap.put( new MenuValues(nomeComando,null),  c);
        //hash non fatto sulla descrizione
    }


    private void debugMethod()
    {
        System.out.println( commandMap );
        System.out.println( commandMap.containsKey( new MenuValues("back","") ) );
       // System.out.println( commandMap.containsValue( "back" ) );

        System.out.println("    Set di chiavi: ");
        Set<MenuValues> keys = commandMap.keySet();
        System.out.println( keys.contains("back") ? "Trovata" : "Non trovata" );
        System.out.println( keys.contains( new MenuValues("back","") ) ? "Trovata" : "Non trovata" );

        System.out.println( keys );
        System.out.println( keys.hashCode() );
    }
}
