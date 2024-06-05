
package com.ACMD.app.Kernel_Layer.Prompt;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import java.util.Vector;

public interface Command{

    public BackStateGame_Enum execute(Vector<String> parametriAggiuntivi);
}
