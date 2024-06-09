
package com.ACMD.app.Kernel_Layer.Prompt;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import java.util.Vector;

public interface Command{

    public BackStateGame_Enum execute(Vector<String> parametriAggiuntivi);
}

/*
 * NOTA: tutte le voci del menù sono istanziate in maniera uguale
 * NOTA: tutti quelli che implementano l'interfaccia Command devono avere lo stesso metodo con la stessa signature
 *      ma posso ancora istanziare in maniera diversa     <- ANCORA DI SALVEZZA
 */