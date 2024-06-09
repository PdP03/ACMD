package com.ACMD.app.Kernel_Layer.Menu;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.NoCommandExistException;
import com.ACMD.app.Kernel_Layer.Prompt.command.Command_Corridoio.Exit;



public class ExitMenu extends Menu
{
    private static final String fileName = "ExitMenu.xml";
    private boolean charged= false;     //evitare carichi una seconda volta

    public ExitMenu(GameEngine generateMethods)
    {
        super(fileName);
        if( !charged )
        {
            //: oridne alfabetico
            //loadMethods("", new );

            loadMethods("exit", new Exit(generateMethods));
            //loadMethods("help", new );

            //loadMethods("q",    new );
            //loadMethods("Q",    new );



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
