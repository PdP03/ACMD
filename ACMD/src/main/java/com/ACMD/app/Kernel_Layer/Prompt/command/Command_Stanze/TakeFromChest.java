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
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non va

       
        if( gme.canPlayerTake(struments.elementAt(1)) )
         {
            gme.playerTake(struments.get(1));
            System.out.println("An element has beed added to the Inv.");
            gme.addBuffer("Hai inserito in inventario "+ struments.get(1));
         }
        

        return BackStateGame_Enum.UPDATE_STORAGE;
    }   
}
