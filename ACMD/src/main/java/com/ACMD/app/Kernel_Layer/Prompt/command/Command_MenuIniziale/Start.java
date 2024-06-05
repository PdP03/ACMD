package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Start implements Command
{
    private GameEngine gme;
    public Start(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Carica il menù dei corridoi ed inizializza la mappa
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
         return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

         //ci pensa Game a cambiare lo stato

        return BackStateGame_Enum.START;
    }   
}
