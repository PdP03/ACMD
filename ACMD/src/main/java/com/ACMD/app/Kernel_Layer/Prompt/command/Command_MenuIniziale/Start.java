package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Start implements Command
{
    private GameEngine gme;         //non necessario
    private GraphicAdapter gra;
    public Start(GameEngine g, GraphicAdapter gra)
    {
        if(g==null) throw new IllegalArgumentException();   //NOTA: in realtà ho raccolto questo nei menù dato che sono le classi che istanziano i comandi, così si evita di rifare molte volte
                                                            //in realtà avrei potuto pensare anche di raccogliere le variabili.. ma forse avrei comunque dovuto usare dei metodi
        gme= g;
        this.gra = gra;
    }

    @Override
    /**
     * Carica il menù dei corridoi ed inizializza la mappa
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size() > 1 )
         return BackStateGame_Enum.ERROR_DIGIT;     //qualcosa che non và

        //_siccome possono essere tanti, faccio la pulizia dello schermo prima
        ClearConsole cls = new ClearConsole(gra);
        cls.execute(nothing);

        gra.fromBufferToGraphic("Digita il tuo nome per la partita: ");
        String s= gra.busyWaitInput();

        s= s.length() < 8 ? s : s.substring(0, 7);
        gme.runSetup( s );

        cls.execute(nothing);
        gra.fromBufferToGraphic( "\nBene "+s+" verrai lanciato/a nel dungeon."      //messo così la stringa perché se no mi tocca in una riga; se se non metto il + vscode segna errore
                                    +"\nTi vedi piccolo in quell'angolino/a a sinistra?"
                                    +"\nÈ solo proporzionale ai mostri."
                                    +"\nSe avessi bisogno di una guida scrivi help e premi invio/enter" );

        gra.reScaleLifeBar( gme.getPlayerLife(), gme.getPlayerMaxLife());
        gra.reScaleWeightBar( gme.getPlayerWeight(), gme.getPlayerMaxWeight());
        //IMPORTANTE: potrei passarli direttamente 100%? sì perché è quello che voglio, è meglio non farlo? SÌ, perché se ci sono problemi lo vedo

        gra.showBars();gra.hideEnemyBar();

        return BackStateGame_Enum.START;
    }   
}
