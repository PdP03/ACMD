package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio;

import java.util.Vector;

import com.ACMD.app.Debug;
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
        if( direction.size() == 1 )
         return (new LookAround(gme)).execute(direction);       //non importa cosa gli passo, l'importante abbia un solo valore il vector

        Direction d;
        String dir = direction.get(1).toLowerCase();

        switch( dir )
        {
            case "nord":
            case "north":
            case "n":
                d = Direction.NORTH;
             break;

            case "sud":
            case "south":
            case "s":
                d = Direction.SOUTH;
            break;

            case "est":         // destra
            case "east":
            case "e":
                d = Direction.EAST;
            break;

            case "ovest":       // sinistra
            case "west":
            case "w":
            case "o":
                d = Direction.WEST;
           break;

            default: return BackStateGame_Enum.ERROR_DIGIT;
        }

        if( gme.canPlayerGo(d) )
         gme.movePlayer(d);
        else gme.addBuffer("Direzione non valida");

        return BackStateGame_Enum.MOVE;
    }

    @Debug
    private void debug()
    {
        if(gme.isPlayerInRoom())
       {
        System.out.println("DEBUG: Entrato nella stanza");
         //return BackStateGame_Enum.UPDATE_MAP;
        }
        else System.out.println("DEBUG: non ancora in una stanza");
    }
}
