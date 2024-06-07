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

        Direction d;

        String dir = direction.get(1).toLowerCase();
        switch( dir )
        {
            case "nord":
            case "north":
                d = Direction.NORTH;
             break;

            case "sud":
            case "south":
                d = Direction.SOUTH;
            break;

            case "est":         // destra
            case "east":
                d = Direction.EAST;
            break;

            case "ovest":       // sinistra
            case "west":
                d = Direction.WEST;
           break;

            default: return BackStateGame_Enum.ERROR_DIGIT;
        }

        if( gme.canPlayerGo(d) )
         gme.movePlayer(d);
       // else          #FORSE: necesario mettere in buffer errore

        return BackStateGame_Enum.MOVE;
    }
}
