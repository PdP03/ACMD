package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Exit implements Command
{
    public BackStateGame_Enum execute(String nothing)
    {
        return BackStateGame_Enum.QUIT;
    }
}
