package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.Vector;
import java.util.HashMap;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Menu.MenuValues;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class HelpStart implements Command
{
    private GameEngine gme;
    private HashMap<MenuValues,Command> memMap;
    public HelpStart(GameEngine g, HashMap<MenuValues,Command> memMap)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
        this.memMap = memMap;
    }

    @Override
    /**
     * Lista dei comandi utilizzabili e informazioni
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        
        gme.addBuffer( memMap.toString() );
        return BackStateGame_Enum.UPDATE;
    }   
}
