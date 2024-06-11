package com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class PlayerUse implements Command
{
    private GameEngine gme;
    public PlayerUse(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Utilizzare degli oggetti
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

        if( gme.playerHave(strument.get(1)) )
        {
            gme.playerUse( strument.get(1) );
            gme.addBuffer("Hai appena utilizzato l'oggetto: "+ strument.get(1).toString() );
        }

        return BackStateGame_Enum.UPDATE_STORAGE;
    }  
}
