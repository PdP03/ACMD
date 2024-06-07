package com.ACMD.app.Kernel_Layer.Prompt.command.Command_Stanze;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

//import ; //altrimenti non si ha il tempo di vedere le barre aggiornarsi

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

        if( gme.isPlayerInRoom() )  //controllo non necessario, ma se per qualche motivo accade c'è un problema
        {                           //tanto sarebbe una azione rara, quindi non importa per il controllo... se è per una cosa così critica  (altrimenti dovrei cercare di stare sempre in stati validi ed evitare if che possono cambiare)
            System.out.println("DEBUG: la voce è disponibile quando non si è in una stanza");
            System.exit(1);
        }

        try
        { Thread.sleep(200); }
        catch(InterruptedException e) { System.out.println( "Problemi con l'attacco" ); e.getStackTrace(); }

        gme.attack();       //#TERMINARE : tutta la parte sui controlli sul fatto che deve attaccare
        return BackStateGame_Enum.UPDATE_MAP;
    }   
}