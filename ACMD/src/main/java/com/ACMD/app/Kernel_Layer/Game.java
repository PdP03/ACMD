package com.ACMD.app.Kernel_Layer;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Prompt;

/**
 * Questa classe fa partire il gioco richiamando prompt il quale andrà a gesire il 
 * gameEngine in base ai comandi scritti dal utente
 * 
 */
public class Game{
    public static void main(String[] args){
        Prompt p = new Prompt();
        Boolean exit = false;

        while(!exit){
            BackStateGame_Enum ret = p.waitInput();

            if(ret == BackStateGame_Enum.QUIT){
                exit = true;
            }
        }
    }
}