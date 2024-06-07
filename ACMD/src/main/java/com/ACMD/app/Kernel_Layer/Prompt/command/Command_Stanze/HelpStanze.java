package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze;

import java.util.HashMap;
import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Menu.MenuValues;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class HelpStanze implements Command
{
    private GameEngine gme;
    private HashMap<MenuValues,Command> memMap;
    public HelpStanze(GameEngine g,HashMap<MenuValues,Command> map)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
        memMap = map;
    }

    @Override
    /**
     * Per uscire dalla stanza
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

       
        return BackStateGame_Enum.UPDATE_MAP;
    }   
}
