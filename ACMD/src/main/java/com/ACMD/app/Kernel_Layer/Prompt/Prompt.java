
package com.ACMD.app.Kernel_Layer.Prompt;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Graphic_Layer.GUI.GameFrame;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Menu.BattleMenu;
import com.ACMD.app.Kernel_Layer.Menu.Menu;
import com.ACMD.app.Kernel_Layer.Menu.MovementMenu;
import com.ACMD.app.Kernel_Layer.Menu.StartMenu;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale.ClearConsole;


    /**
     * @Singleton
     */
public class Prompt
{

    Menu mn;
    GameEngine gme;
    Command cmmd;

    GameFrame gmf;
    GraphicAdapter graphA;

    //## Costruttore ##

    public Prompt(GameEngine engine)
    {
        gmf= new GameFrame();
        gmf.setVisible(true);
        gme= engine;

        graphA = new GraphicAdapter(gmf);
        graphA.hideBars();  //per dire che non è presente un mostro
        mn = new StartMenu(gme,graphA);

        graphA.fromBufferToGraphic("Digita \"help\" per avere aiuto\n");
    }


    //## Metodi Public ##

    public BackStateGame_Enum waitInput()
    {//_richiama la grafica di input, aspetta il comando, fa le verifiche necessarie 

      String s = graphA.busyWaitInput();
                                                    //DEBUG if(s.equals("")) return BackStateGame_Enum.UPDATE;
        //_split comando da parametri
        Vector<String> ary= removeDoubleSpaces(s);  
            //STATO: input è vector di stringhe non vuote e toglie anche \n
        changeCommand(ary.get(0));
        BackStateGame_Enum mem = 
                cmmd != null ?
                cmmd.execute(ary) :   //passo tutti i parametri, in quanto non so cosa gli serva
                BackStateGame_Enum.ERROR_DIGIT;

        //_dopo aver eseguito, faccio gli aggiornamenti necessari
        graphA.fromBufferToGraphic( gme.getBuffer() );  //stampa tutti i messaggi
        chooseUpdate(mem);

        return mem;
    }
    
    /**
     * Statico per dare la possibilità ai metodi del command patter di aggiornare la grafica
     * @param gme       GameEngine
     * @param graphA    Graphic Adapter
     */
    public static void updateEntityBars(GameEngine gme, GraphicAdapter graphA)
    {
        graphA.reScaleEnemyBar( gme.getMonsterLife(), gme.getMonsterMaxLife() );
        graphA.reScaleLifeBar(  gme.getPlayerLife(), gme.getPlayerMaxLife() );
    }

    //## Metodi Private ##

    private static Vector<String> removeDoubleSpaces(String str)
    {
        String[] sAry = str.split(" ");             //possono rimanere spazi doppi : allora locazione array vuoto
        Vector<String> s2= new Vector<String>();

        //_rimuovo tutte istanze vuote
        for(int i=0; i<sAry.length; i++)
        {
            //se non vuoto, allora posso aggiungere
            if( sAry[i] != "" && sAry[i] != "\n" ) s2.add( sAry[i] );
        }
        return s2;
    }

    /**
     * Istanzia il menù con i nuovi comandi
     */
    private void changeMenu(Menu mn)
    {//: utile per passare da quello di start, a quello delle stanze a quello dei combattimenti

        if( mn==null ) throw new IllegalArgumentException("Menu non esiste");
        this.mn = mn;
    }

    /**
     * uno switch per controllare le varie enum e decidere cosa aggiornare a schermo, ed i menù
     */
    private void chooseUpdate(BackStateGame_Enum state)
    {
        switch(state)
        {
            case COMBACT:
            case LOAD:
            //nessun break
            case UPDATE_MAP:
            case MOVE:
                if( gme.isPlayerInRoom() )
                   { 
                    changeMenu( new BattleMenu(gme,graphA) );
                   }
                else
                   { changeMenu( new MovementMenu(gme,graphA) ); }  //cambio il menù
            //nessun break: continua eseguendo MOVE:
            
                graphA.move( gme.getPlayerCord() );
            break;
            case UPDATE_ENTITY:
                updateEntityBars(gme, graphA);
            break;
            case ERROR_DIGIT:
                gme.addBuffer("Comando non riconosciuto");
            break;
            case UPDATE_STORAGE:
                graphA.reScaleWeightBar( gme.getPlayerWeight() , gme.getPlayerMaxWeight());
                graphA.reScaleLifeBar( gme.getPlayerLife(), gme.getPlayerMaxLife());    //per pozione di cura \
            break;
            case START:
                changeMenu( new MovementMenu(gme,graphA) );
            break;
            case RESTART:
                try{ Thread.sleep(750); }
                catch(InterruptedException e)
                { System.out.println( "Problemi con nella classe attacco" ); e.getStackTrace(); }
            case QUIT:
                gmf.dispose();
            break;
            
            default: break;
        }

        graphA.fromBufferToGraphic( gme.getBuffer() );
    }
    
    public static void deatchCase(GameEngine gme, GraphicAdapter gra)
    {//così gestisce solo una volta i tempi per uscire

        gra.reScaleEnemyBar( gme.getMonsterLife(), gme.getMonsterMaxLife() );
        gra.reScaleLifeBar(0);      //non è bello, ma almeno si evitano problemi di appossimazione
        gra.reScaleWeightBar(gme.getPlayerWeight(),gme.getPlayerMaxWeight());


        Vector<String> nothing = new Vector<String>();
        (new ClearConsole(gra)).execute(nothing);
        gra.fromBufferToGraphic( gme.getBuffer() );

        gra.fromBufferToGraphic("HAI PERSO");
        try{ Thread.sleep(1600); } catch(InterruptedException e2)
         { System.out.println( "Problemi con nella classe attack o playerUse" ); e2.getStackTrace(); }
    }


    //## Metodi Command-Pattern ##
    private void changeCommand(String input)
    {
        cmmd= mn.checkInTheMap(input);
       // graphA.fromBufferToGraphic( gme.getBuffer() );
    }

    //## Metodo di DEBUG per dare accesso ai test per la divisione delle stringhe
    public static Vector<String> DEBUG(String in)
    {
        return removeDoubleSpaces(in);
    }
}
