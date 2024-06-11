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
    public BackStateGame_Enum execute(Vector<String> strument)
    {
        if( strument.size() > 2 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non va

        if( strument.size() ==1 )
        {
           gme.addBuffer("Serve inserire il nome dell'oggetto");
           return BackStateGame_Enum.ERROR_DIGIT;
        }

        if( ! gme.isPlayerInRoom() )  //controllo non necessario, ma se per qualche motivo accade c'è un problema
        {
            System.out.println("DEBUG: la voce è disponibile quando non si è in una stanza");
            System.exit(1);
        }
       
        if( gme.canPlayerTake(strument.elementAt(1)) )
         {
            gme.playerTake(strument.get(1));//System.out.println("An element has beed added to the Inv.");//DEBUG
            gme.addBuffer("Hai inserito in inventario "+ strument.get(1));
         }
         //else
        

        return BackStateGame_Enum.UPDATE_STORAGE;
    }   
}
