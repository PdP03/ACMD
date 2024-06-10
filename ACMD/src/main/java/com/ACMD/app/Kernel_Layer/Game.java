package com.ACMD.app.Kernel_Layer;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Prompt;

/**
 
 * Questa classe fa partire il gioco richiamando prompt il quale andrà a gesire il 
 * gameEngine in base ai comandi scritti dal utente.
 * 
 * IMPORTANTE: lo scopo di Game è quello di fare il main loop richiamando continuamente prompt 
 * che va a gestire i comandi inseriti dal utente, il main loop termina quando l'utente scrive 
 * exit e quindi prompt restituisce QUIT
 */
public class Game{
    private static GameEngine engine = new GameEngine(false);

    public static void main(String[] args){
        
        Prompt p = new Prompt(engine);
        Boolean exit = false;

        while(!exit){
            BackStateGame_Enum ret = p.waitInput();
            if(ret == BackStateGame_Enum.QUIT){
                exit = true;
            }
            else if(ret == BackStateGame_Enum.RESTART){
                p = new Prompt(engine);
            }
        }
    }
}