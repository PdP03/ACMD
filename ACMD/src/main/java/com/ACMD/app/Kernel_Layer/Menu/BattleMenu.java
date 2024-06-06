package com.ACMD.app.Kernel_Layer.Menu;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.NoCommandExistException;
import com.ACMD.app.Kernel_Layer.Prompt.command.*;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze.BackFromRoom;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze.LookInsideChest;

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
            loadMethods("back", new BackFromRoom(generateMethods));
            loadMethods("help", null );     //TERMINARE
           // loadMethods("look", new LookInsideChest(generateMethods));
            loadMethods("openchest",new LookInsideChest(generateMethods));
           // loadMethods("showchest",c);
            loadMethods("showinventario",);
            loadMethods("trashit",c);

            if( commandMap.containsValue(null) )
             throw new NoCommandExistException("Esiste ancora una voce che non ha un comando associato");

            charged= true;
        }
    }

    @Override
    protected void loadMethods(String nomeComando, Command c)
    {
        if( ! commandMap.containsKey( new MenuValues(nomeComando,null) ) )
            throw new MenuVoiceNotFound();
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

}
