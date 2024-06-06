package com.ACMD.app.Kernel_Layer;

import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Menu.BattleMenu;
import com.ACMD.app.Kernel_Layer.Menu.Menu;
import com.ACMD.app.Kernel_Layer.Menu.MovementMenu;
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
        Menu menu;

        while(!exit){
            BackStateGame_Enum ret = p.waitInput();
            if(ret == BackStateGame_Enum.QUIT){
                exit = true;
            }
            else if(ret == BackStateGame_Enum.RESTART){
                p = new Prompt();
            }

            menu = getMenuFrom(ret);
            if(menu != null){
                p.changeMenu(menu);
            }
            


        }
    }

    /**
     * Metodo di utilita per converite un enum nel rispettivo menu
     * @return Menu
     */
    private static Menu getMenuFrom(BackStateGame_Enum e){
        switch(e){
            case START:
                return new MovementMenu();
            case UPDATE_MAP:
                return new BattleMenu();
            default:
                return null;
        }
    }
}