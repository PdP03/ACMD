
package com.ACMD.app.Kernel_Layer.Prompt;

import java.util.HashMap;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.*;
import com.ACMD.app.Kernel_Layer.Prompt.CommandPattern.Command;


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

    boolean engineLinked = false;

    //## Costruttore ##

    public Prompt(GameEngine gme, StartMenu firstMenu)
    {
        //istanzia una sola volta la grafica di input
    }



    //## Metodi Public ##

    public BackStateGame_Enum waitInput()
    {//:richiama la grafica di input, aspetta il comando, fa le verifiche necessarie 

        return null;
    }

    public void linkEngine(GameEngine g)
    {

    }
    


    //## Metodi Private ##

    private String checkSintax(String str)
    {
        return null;
    }

    private void changeCommand(Command cmmd)
    {
        
    }

    //## Metodi Command-Pattern ##
}
