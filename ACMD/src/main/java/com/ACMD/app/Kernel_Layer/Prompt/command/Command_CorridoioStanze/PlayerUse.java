package com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.DeathException;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.Prompt;

public class PlayerUse implements Command
{
    private GameEngine gme;
    private GraphicAdapter gra;
    public PlayerUse(GameEngine g, GraphicAdapter gra)
    {
        if(g==null || gra==null)
         throw new IllegalArgumentException();
        gme= g;
        this.gra= gra;
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

        //:può essere che l'oggetto abbia arrecato danno -> conotrollo con try-catch se player morto

        if( gme.playerHave(strument.get(1)) )
        {
            try
                {
                    gme.playerUse( strument.get(1) );
                    gme.addBuffer("Hai appena utilizzato l'oggetto: "+ strument.get(1).toString() );
                }
            catch( DeathException e )
            {
                gme.addBuffer("Ti sei arrecato del danno, e..\n");
                Prompt.deatchCase(gme, gra);
                return BackStateGame_Enum.QUIT;
            }
        }
         else gme.addBuffer("Non trovo questo oggetto");

        return BackStateGame_Enum.UPDATE_STORAGE;
    }  
}
