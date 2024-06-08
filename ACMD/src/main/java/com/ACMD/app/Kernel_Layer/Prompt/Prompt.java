
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



/*
    Idea Iniziale: fare un controllo sulla sintassi del comando ed utilizzare una entry che lega il comando con la stringa
    Idea Nuova: utilizzare la hashmap su una entry che lega il nome della stringa ed la funzione da richiamre. Se il comando non si trova lo ci penserà la hashamp
    
    Tramite enumerazioni si può comunicare con la classe Game
        -eventi per ritorno
        -quali voci del menù si possono usare

    #PROBLEMA: una volta che mi viene passato il menù delle operazioni che possono eseguire, come faccio a legarlo al metodo?
    #PROBLEMA: essendo enum diverse, ci vogliono signature differenti, è brutto
 
    Odio l'idea di booleano a caso.
    Capisco che non posso fare una classe a parte.. cioè, lo accetto solo perché dall'esterno di prompt la cosa si complica.
    Però se trovo una idea che sta dentro a prompt io la faccio. Tipo creare un metodo che riallochi l'oggetto.
    Altrimenti.. anche se non si farà.. una classe SmallPrompt che è in grado solo di comunicare con Game e non GameEngine:
        quindi che è solo in grado di interfacciarsi con l'utente e non di far partire il gioco
    */

    /**
     * @Singleton
     */
public class Prompt
{

   // boolean engineLinked = false;
    Menu mn;
    GameFrame gmf;
    GameEngine gme;
    Command cmmd;

    GraphicAdapter graphA;
int cont=0;
    //## Costruttore ##

    public Prompt(GameEngine engine)
    {
        gmf= new GameFrame();
        gmf.setVisible(true);;
        gme= engine;
        gme.runSetup("Brico");
        mn = new StartMenu(gme);

        graphA = new GraphicAdapter(gmf);
        //System.out.println("Creato prompt"); gme.addBuffer("Prompt creato"); graphA.fromBufferToGraphic( gme.getBuffer() );//DEBUG: vedere se creato
    }


    //## Metodi Public ##

    public BackStateGame_Enum waitInput()
    {//_richiama la grafica di input, aspetta il comando, fa le verifiche necessarie 

      //  if(!engineLinked) throw new RuntimeException("Non è collegato ad alcun engine");
cont++;
      String s = graphA.busyWaitInput();
System.out.println(cont+" "+s);
        //_split comando da parametri
        Vector<String> ary= removeDoubleSpaces(s);

            //STATO: input è vector di stringhe non vuote e toglie anche \n
        changeCommand(ary.get(0));
        BackStateGame_Enum mem = 
                cmmd != null ?
                cmmd.execute(ary) :   //passo tutti i parametri, in quanto non so cosa gli serva
                BackStateGame_Enum.ERROR_DIGIT;

System.out.println( mem );

        //_dopo aver eseguito, faccio gli aggiornamenti necessari
        chooseUpdate(mem);
       // gme.addBuffer( mn.toString() );//DEBUG
        graphA.fromBufferToGraphic( gme.getBuffer() );//stampa tutti i messaggi

        return mem;
    }
    
    /**
     * Statico per dare la possibilità ai metodi del command patter di aggiornare la grafica
     * @param gme       GameEngine
     * @param graphA    Graphic Adapter
     */
    public static void updateBars(GameEngine gme, GraphicAdapter graphA)
    {
        graphA.reScaleEnemyBar( gme.getMonsterLife(), gme.getMonsterMaxLife() );
        graphA.reScaleLifeBar(  gme.getPlayerLife(), gme.getPlayerMaxLife() );
        graphA.reScaleWeightBar(gme.getPlayerWeight(), gme.getPlayerMaxWeight());
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
            if( sAry[i] != "" && sAry[i] != "\n" ) s2.add( sAry[i] );            //#TESTARE : fatto in locale, posso metterlo nel test con tutti i casi
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
            case UPDATE_MAP:
                if( gme.isPlayerInRoom() )
                    changeMenu( new BattleMenu(gme,graphA) );
                else
                    changeMenu( new MovementMenu(gme) );    //cambio il menù
            //nessun break: continua eseguendo move
            case MOVE:
                graphA.move( gme.getPlayerCord() );
            break;
            case UPDATE_ENTITY:
                updateBars(gme, graphA);
            break;
            case ERROR_DIGIT:
                gme.addBuffer("Comando non riconosciuto");
            break;
            case START:
                changeMenu( new MovementMenu(gme) );
            break;
            case QUIT:
                gmf.dispose();
            break;

            default: break;
        }
    }
    

    //## Metodi Command-Pattern ##
    private void changeCommand(String input)
    {
        cmmd= mn.checkInTheMap(input);
        graphA.fromBufferToGraphic( gme.getBuffer() );
    }
}
