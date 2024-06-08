package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class BackFromRoom implements Command
{
    private GameEngine gme;
    public BackFromRoom(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Per uscire dalla stanza
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        if( ! gme.isPlayerInRoom() )  //controllo non necessario, ma se per qualche motivo accade c'è un problema
        {                           //tanto sarebbe una azione rara, quindi non importa per il controllo... se è per una cosa così critica  (altrimenti dovrei cercare di stare sempre in stati validi ed evitare if che possono cambiare)
            
            System.out.println("DEBUG: la voce non è disponibile quando si è in una stanza");
            System.exit(1);
        }
//#TERMINARE : bisogna fare il controllo se era l'ultimo boss per aprire il cancello; e controllare se si ha sconfitto il boss finale
        gme.movePlayerBack();
        return BackStateGame_Enum.UPDATE_MAP;
    }   
}
