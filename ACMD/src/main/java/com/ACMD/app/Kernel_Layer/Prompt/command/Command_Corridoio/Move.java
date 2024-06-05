package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio;

import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Move implements Command
{
    private GameEngine gme;
    public Move(GameEngine g)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
    }

    @Override
    /**
     * NORD/N, SUD/S, EST/E, OVEST/W
     */
    public BackStateGame_Enum execute(Vector<String> direction)
    {
        if( direction.size() > 2 ) 
         return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        switch( direction.get(1) )
        {           //#TERMINARE : manca il controllo se la direzione è possibile
            case "Nord":
            case "N":
            case "NORD":
                gme.movePlayer(Direction.NORTH);
             break;

            case "Sud":
            case "S":
            case "SUD":
                gme.movePlayer(Direction.SOUTH);
            break;

            case "Est":         // destra
            case "E":
            case "EST":
                gme.movePlayer(Direction.EAST);
            break;

            case "Ovest":       // sinistra
            case "O":
            case "OVEST":
            case "West":
            case "W":
            case "WEST":
                gme.movePlayer(Direction.WEST);
            break;

            default: return BackStateGame_Enum.ERROR_DIGIT;
        }

        return BackStateGame_Enum.MOVE;
    }
}
