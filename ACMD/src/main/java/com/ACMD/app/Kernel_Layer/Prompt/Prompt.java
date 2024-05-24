
package com.ACMD.app.Kernel_Layer.Prompt;

import java.util.HashMap;

import com.ACMD.app.Engine_Layer.GameEngine.*;
import com.ACMD.app.Kernel_Layer.Menu.Menu_Enum;
//import com.ACMD.app.Kernel_Layer.Prompt.CommandPattern.Command;


/*
    Idea Iniziale: fare un controllo sulla sintassi del comando ed utilizzare una entry che lega il comando con la stringa
    Idea Nuova: utilizzare la hashmap su una entry che lega il nome della stringa ed la funzione da richiamre. Se il comando non si trova lo ci penserà la hashamp
    
    Tramite enumerazioni si può comunicare con la classe Game
        -eventi per ritorno
        -quali voci del menù si possono usare

    #PROBLEMA: una volta che mi viene passato il menù delle operazioni che possono eseguire, come faccio a legarlo al metodo?
*/

public class Prompt
{

    //## Costruttore ##

    public Prompt(GameEngine gme, Menu_Enum firstMenu)
    {
        //istanzia una sola volta la grafica di input
    }



    //## Metodi Public ##

    public Menu_Enum waitInput()
    {//:richiama la grafica di input, aspetta il comando, fa le verifiche necessarie 


    }

    


    //## Metodi Private ##

    private String checkSintax(String str)
    {
        return null;
    }

    private void changeCommand(Command cmmd)
     {}

    //## Metodi Command-Pattern ##
}
