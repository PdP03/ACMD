package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class ClearConsole implements Command
{
    private GraphicAdapter gra;
    public ClearConsole(GraphicAdapter a)
    {
        if(a==null) throw new IllegalArgumentException();
        gra= a;
    }

    @Override
    /**
     * Pulire lo schermo
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        gra.clearScreen();

        return BackStateGame_Enum.UPDATE;
    }   
}
