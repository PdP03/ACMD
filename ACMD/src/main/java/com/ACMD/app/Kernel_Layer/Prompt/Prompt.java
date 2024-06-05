
package com.ACMD.app.Kernel_Layer.Prompt;

import java.util.HashMap;
import java.util.Vector;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Graphic_Layer.GUI.GameFrame;
import com.ACMD.app.Kernel_Layer.Menu.*;


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

    //## Costruttore ##
/*
    public void linkEngine(GameEngine g)
    {
        if( g==null ) throw new IllegalArgumentException("Serve un riferimento");

        engineLinked = true;
        gme = g;
    }

    public Prompt(GameEngine gme, StartMenu firstMenu, GameFrame gmf)
    {
        //istanzia una sola volta la grafica di input
        linkEngine(gme);
        
        if( firstMenu == null || gmf == null) throw new IllegalArgumentException();
         mn = firstMenu;
         gmf = this.gmf;
    }
    public Prompt(StartMenu firstMenu, GameFrame gmf)
    {
         if( firstMenu == null || gmf == null) throw new IllegalArgumentException();
         mn = firstMenu;
         gmf = this.gmf;

    }*/

    //_istanzio anche
    public Prompt()
    {
        mn= new StartMenu();
        gmf= new GameFrame();
        gme= new GameEngine();
    }


    //## Metodi Public ##

    public BackStateGame_Enum waitInput()
    {//:richiama la grafica di input, aspetta il comando, fa le verifiche necessarie 

      //  if(!engineLinked) throw new RuntimeException("Non è collegato ad alcun engine");

        String s;
        while( (s= gmf.textInput()) == null );

        //:split comando da parametri
        Vector<String> ary= removeDoubleSpaces(s);
            //_input è vector di stringhe non vuote
        changeCommand(ary.get(0));

        return cmmd.execute(ary);   //passo tutti i parametri
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
            if( sAry[i] != "" ) s2.add( sAry[i] );            //#TESTARE : fatto in locale, posso metterlo nel test con tutti i casi
        }
        return s2;
    }

    //## Metodi Command-Pattern ##
    private void changeCommand(String input)
    {
        cmmd= mn.checkInTheMap(input);
    }
}
