package com.ACMD.app.Kernel_Layer.Prompt.command.Command_CorridoioStanze;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Showme implements Command
{
    private GameEngine gme;
    public Showme(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * Mostra il proprio peso e vita
     */
    public BackStateGame_Enum execute(Vector<String> nomeSalvataggio)
    {
        gme.addBuffer("Vita: "+ gme.getPlayerLife() +" su massimo"+gme.getPlayerMaxLife()+
        "\nPeso: "+gme.getPlayerWeight()+" su massimo "+gme.getPlayerMaxWeight()+"\n");
        
        return BackStateGame_Enum.UPDATE;
    }   

}
