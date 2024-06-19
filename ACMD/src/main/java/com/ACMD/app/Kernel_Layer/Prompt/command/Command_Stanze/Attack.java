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
        {
            System.out.println("DEBUG: la voce è disponibile quando non si è in una stanza");
            System.exit(1);
        }

        if( gme.playerCanAttack() )
        {
            gra.showEnemyBar();
            gra.fromBufferToGraphic( "Ti scagli all'attacco" );

            try{
                Thread.sleep(100);  //essere sicuro appaia la barra

                do
                {
                    Thread.sleep(350);
                    Prompt.updateEntityBars(gme, gra);    //invertito per non avere i problemi che cancellava gli oggetti
                    gme.attack();
                }while( gme.playerIsAlive() && gme.playerCanAttack() );
                //gra.fromBufferToGraphic( "Sconfitto" );

                Prompt.updateEntityBars(gme, gra);
                gra.hideEnemyBar();
             }
            catch(InterruptedException e)
                { System.out.println( "Problemi con nella classe attacco" ); e.getStackTrace(); }
            catch(DeathException e)
            {     
                gra.reScaleEnemyBar( gme.getMonsterLife(), gme.getMonsterMaxLife() );           
                gme.addBuffer("Tramortito, cadi a terra\n");
                Prompt.deatchCase(gme, gra);
                return BackStateGame_Enum.QUIT;
            }
            catch(IllegalArgumentException e) {}
        }
        else
         gra.fromBufferToGraphic( "La stanza è vuota" );


        return BackStateGame_Enum.COMBACT;
    }   

}
