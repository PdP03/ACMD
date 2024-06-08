package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.DeathException;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.Prompt;


public class Attack implements Command
{
    private GameEngine gme;
    private GraphicAdapter gra;         //per aggiornare run time
    public Attack(GameEngine g, GraphicAdapter a)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
        gra= a;
    }

    @Override
    /**
     * Per avviare il combattimento automatico
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
        return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        if( ! gme.isPlayerInRoom() )  //controllo non necessario, ma se per qualche motivo accade c'è un problema
        {                           //tanto sarebbe una azione rara, quindi non importa per il controllo... se è per una cosa così critica  (altrimenti dovrei cercare di stare sempre in stati validi ed evitare if che possono cambiare)
            System.out.println("DEBUG: la voce è disponibile quando non si è in una stanza");
            System.exit(1);
        }

        if( gme.playerCanAttack() )
        {
            gra.fromBufferToGraphic( "Ti scagli all'attacco" );

            try{
                while( true )
                {
                    Thread.sleep(200);
                    gme.attack();
                    Prompt.updateBars(gme, gra);
                }
             }
            catch(InterruptedException e) { System.out.println( "Problemi con nella classe attacco" ); e.getStackTrace(); }
            catch(DeathException e) { /*#TERMINARE Schermata per aver perso */ }
            catch(IllegalArgumentException e) {}
        }
        else
         gra.fromBufferToGraphic( "La stanza è vuota" );


    


              //#TERMINARE : tutta la parte sui controlli sul fatto che deve attaccare: controllare se automatico e cosa accade in caso di morte
        return BackStateGame_Enum.COMBACT;
    }   
}
