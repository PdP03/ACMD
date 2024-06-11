package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;


public class ExitAndSave implements Command
{
    private GameEngine gme;
    private GraphicAdapter gra;
    public ExitAndSave(GameEngine g,GraphicAdapter gra)
    {
        if(g==null || gra==null) throw new IllegalArgumentException();
        gme= g;
        this.gra= gra;
    }

    @Override
    /**
     * Chiede di uscire dalla stana
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
         return BackStateGame_Enum.ERROR_DIGIT;

        gra.fromBufferToGraphic("Hai deciso di uscire, vuoi anche salvare? (Y/n)");
        String Yn= gra.busyWaitInput();

        if( ! Yn.equals("n") )
         save();

        return BackStateGame_Enum.QUIT;
    }

    private void save()
    {
        SaveAdapter.save( gme.getPlayer(), gme.getMap() );    //System.out.println( gme.getPlayer().getName() );//DEBUG

        gme.addBuffer("Partita salvata");
    }
}

