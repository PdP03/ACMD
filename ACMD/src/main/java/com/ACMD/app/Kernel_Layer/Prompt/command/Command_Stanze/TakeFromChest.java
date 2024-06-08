package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class TakeFromChest implements Command
{
    private GameEngine gme;
    public TakeFromChest(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    /**
     * Da dentro la stanza
     */
    @Override
    public BackStateGame_Enum execute(Vector<String> struments)
    {
        if( struments.size() > 2 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        if( ! gme.isPlayerInRoom() )  //controllo non necessario, ma se per qualche motivo accade c'è un problema
        {                           //tanto sarebbe una azione rara, quindi non importa per il controllo... se è per una cosa così critica  (altrimenti dovrei cercare di stare sempre in stati validi ed evitare if che possono cambiare)
            System.out.println("DEBUG: la voce è disponibile quando non si è in una stanza");
            System.exit(1);
        }
       
        if( gme.canPlayerTake(struments.elementAt(1)) )
         {
            gme.playerTake(struments.get(1));
            gme.addBuffer("Hai inserito in inventario "+ struments.get(1));
         }
        

        return BackStateGame_Enum.UPDATE_STORAGE;
    }   
}
