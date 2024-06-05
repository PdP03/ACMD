package com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class PlayerRemoveItem implements Command
{
    private GameEngine gme;
    public PlayerRemoveItem(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    /**
     * @Override
     * Nella chest:
     *  rimuovere oggetti 
     */
    public BackStateGame_Enum execute(Vector<String> struments)
    {
        if( struments.size() > 2 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        try { gme.playerRemove( struments.get(1) ); }
        catch(IllegalArgumentException e) { return BackStateGame_Enum.ERROR_DIGIT; }

        return null;
    }   
}
