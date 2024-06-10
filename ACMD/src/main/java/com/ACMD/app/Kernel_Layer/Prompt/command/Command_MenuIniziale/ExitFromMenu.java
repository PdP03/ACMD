package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import java.util.Vector;

public class ExitFromMenu implements Command
{
    public ExitFromMenu()
     {}

    @Override
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
         return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        return BackStateGame_Enum.QUIT;
    }
}
