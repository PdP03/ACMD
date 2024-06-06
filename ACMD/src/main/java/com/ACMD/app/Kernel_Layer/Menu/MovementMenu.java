
package com.ACMD.app.Kernel_Layer.Menu;

import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Prompt.Command;
import com.ACMD.app.Kernel_Layer.Prompt.NoCommandExistException;

public class MovementMenu extends Menu
{
    private static final String fileName = "MovementMenu.xml";
    private boolean charged= false;     //evitare carichi una seconda volta

    public MovementMenu(GameEngine generateMethods)
    {
        super(fileName);
        if( !charged )
        {
            //: oridne alfabetico
            //loadMethods("", new );
            loadMethods("back", new );
            loadMethods("exit", new );
            loadMethods("help", new );
            loadMethods("looknearsnodes", new );

            if( commandMap.containsValue(null) )
             throw new NoCommandExistException("Esiste ancora una voce che non ha un comando associato");

            charged= true;
        }
    }

    @Override
    protected void loadMethods(String nomeComando, Command c)
    {
        if( ! commandMap.containsKey( new MenuValues(nomeComando,null) ) )
            throw new MenuVoiceNotFound();
            //per avere un debug ogni volta che si avvvia, che almeno tutte le voci che appaiano nel menù facciano qualcosa

        commandMap.put( new MenuValues(nomeComando,null),  c);
        //hash non fatto sulla descrizione
    }

}
