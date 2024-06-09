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
    public HelpCorridoio(HashMap<MenuValues,Command> commandMap)
    {
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

        Object[] ary = mapMem.keySet().toArray();
        String s="";
        for(int i=0; i<ary.length; i++)
            s+= ary[i].toString()+"\n";
        gme.addBuffer( s );
       
        return BackStateGame_Enum.UPDATE;
    }   
}
