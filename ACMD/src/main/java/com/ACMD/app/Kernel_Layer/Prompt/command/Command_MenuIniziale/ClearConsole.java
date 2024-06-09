package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class ClearConsole implements Command
{
    private GameEngine gme;
    private GraphicAdapter gra;
    public ClearConsole(GameEngine g, GraphicAdapter a)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
        gra= a;
    }

    @Override
    /**
     * Per uscire dalla stanza
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        gra.clearScreen();

        return BackStateGame_Enum.UPDATE;
    }   
}
