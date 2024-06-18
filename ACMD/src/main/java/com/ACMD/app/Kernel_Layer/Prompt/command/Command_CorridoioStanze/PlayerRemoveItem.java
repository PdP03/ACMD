package com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Engine_Layer.StorageManagement.noItem_Exception;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class PlayerRemoveItem implements Command
{
    private GameEngine gme;
    public PlayerRemoveItem(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Nella chest:
     *  rimuovere oggetti 
     */
    public BackStateGame_Enum execute(Vector<String> strument)
    {
        if( strument.size() > 2 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        if( strument.size() ==1 )
         {
            gme.addBuffer("Serve inserire il nome dell'oggetto");
            return BackStateGame_Enum.ERROR_DIGIT;
         }

        try { gme.playerRemove( strument.get(1) ); }
        catch(IllegalArgumentException e) { return BackStateGame_Enum.ERROR_DIGIT; }
        catch(noItem_Exception e)         { gme.addBuffer("Oggetto non trovato in inventario"); }

        return BackStateGame_Enum.UPDATE_STORAGE;
    }   
}
