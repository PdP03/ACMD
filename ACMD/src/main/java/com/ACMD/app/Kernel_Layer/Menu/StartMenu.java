package com.ACMD.app.Kernel_Layer.Menu;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.NoCommandExistException;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale.*;

public class StartMenu extends Menu
{
    private static final String fileName = "StartMenu.xml";
    private boolean charged= false;     //evitare carichi una seconda volta

    public StartMenu(GameEngine generateMethods, GraphicAdapter gra)
    {
        super(fileName);
        if( generateMethods == null || gra == null )
         throw new IllegalArgumentException( "Non si stanno inserendo dei valori nel menù Start" );

        if( !charged )
        {
            //: oridne alfabetico
            //loadMethods("", new );

            loadMethods("exit",  new ExitFromMenu(generateMethods));
            loadMethods("clear", new ClearConsole(gra));
            loadMethods("load",  new Load(generateMethods,gra));
            loadMethods("start", new Start(generateMethods,gra));

            loadMethods("help",  new HelpStart(generateMethods,commandMap));

            if( commandMap.containsValue(null) )
             throw new NoCommandExistException("Esiste ancora una voce che non ha un comando associato");

            charged= true;
        }
    }

    @Override
    protected void loadMethods(String nomeComando, Command c)
    {
        if( ! commandMap.containsKey( new MenuValues(nomeComando,null) ) )
         throw new MenuVoiceNotFound("Comando non trovato in file xml: "+nomeComando);
            //per avere un debug ogni volta che si avvvia, che almeno tutte le voci che appaiano nel menù facciano qualcosa

        commandMap.put( new MenuValues(nomeComando,null),  c);
        //hash non fatto sulla descrizione
    }

}
