package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;


public class Save implements Command
{
        private GameEngine gme;
    public Save(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Tutti i metodi necessari per comunicare con layer 
     */
    public BackStateGame_Enum execute(Vector<String> nomeSalvataggio)
    {
        save();
        return BackStateGame_Enum.SAVE;
    }   

    private void save()
    {
        SaveAdapter.save( gme.getPlayer() );
                     System.out.println(gme.getPlayer().getName());//DEBUG
        SaveAdapter.save( gme.getMap() );

        gme.addBuffer("Partita salvata");
    }
}
