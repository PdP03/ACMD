package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio;

import java.util.Vector;
import java.util.HashMap;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Menu.MenuValues;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class HelpCorridoio implements Command
{
    private GameEngine gme;
    private HashMap<MenuValues,Command> mapMem;
    public HelpCorridoio(GameEngine g, HashMap<MenuValues,Command> commandMap)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
        mapMem= commandMap;
    }

    @Override
    /**
     * Per uscire dalla stanza
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        gme.addBuffer( mapMem.keySet().toString() );
       
        return BackStateGame_Enum.UPDATE_MAP;
    }   
}
