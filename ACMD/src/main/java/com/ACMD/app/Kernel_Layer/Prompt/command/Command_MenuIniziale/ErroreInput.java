package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import java.util.Vector;

public class ErroreInput implements Command
{
    @Override
    public BackStateGame_Enum execute(Vector<String> s)
    {
        return BackStateGame_Enum.ERROR_DIGIT;
    }
}
