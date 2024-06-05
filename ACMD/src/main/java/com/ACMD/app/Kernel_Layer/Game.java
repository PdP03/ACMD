package com.ACMD.app.Kernel_Layer;

import com.ACMD.app.Kernel_Layer.Prompt.Prompt;

/**
 * Questa classe fa partire il gioco richiamando prompt il quale andrà a gesire il 
 * gameEngine in base ai comandi scritti dal utente
 * 
 */
public class Game{
    public static void main(String[] args){
        Prompt p = new Prompt();
        
        while(!p.exit()){
            p.waitInput();
        }
    }
}