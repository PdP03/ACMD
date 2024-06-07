package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import java.util.Vector;

public class ErroreInput implements Command
{
    private GameEngine gme;
    public ErroreInput(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    public BackStateGame_Enum execute(Vector<String> s)
    {
        if(true) throw new IllegalAccessError("Queta classe non deve più essere usata");
        gme.addBuffer("Comando non valido");
        return BackStateGame_Enum.ERROR_DIGIT;
    }
}
