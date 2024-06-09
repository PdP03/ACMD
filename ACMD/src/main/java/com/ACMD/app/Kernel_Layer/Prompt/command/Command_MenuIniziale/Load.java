package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.List;
import java.util.Vector;

import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Load implements Command
{
    private GameEngine gme;
    public Load(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Tutti i metodi necessari per comunicare con layer di salvataggio
     */
    public BackStateGame_Enum execute(Vector<String> nomeSalvataggio)
    {
        //_ottengo i nomi
        List<String> salvataggi= SaveAdapter.GetUploadedFiles();

        if( chooseFile() )
        {
            //_carico tutto
            gme.loadPlayer( SaveAdapter.retrievePlayer() );
            gme.loadMap(    SaveAdapter.retrieveMap() );
        }

        return BackStateGame_Enum.SAVE;
    }

    private boolean chooseFile()
    {
        return false;
    }
}
