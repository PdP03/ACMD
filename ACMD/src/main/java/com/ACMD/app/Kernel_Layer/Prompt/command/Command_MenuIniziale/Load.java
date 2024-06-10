package com.ACMD.app.Kernel_Layer.Prompt.command.Command_MenuIniziale;

import java.util.List;
import java.util.Vector;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Adapter_Layer.SaveAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BackStateGame_Enum;
import com.ACMD.app.Kernel_Layer.Prompt.Command;

public class Load implements Command
{
    private GameEngine gme;
    private GraphicAdapter gra;
    public final int ERRORE_INPUT = -1;
    public Load(GameEngine g,GraphicAdapter gra)
    {
        if(g==null) throw new IllegalArgumentException();
        gme= g;
        this.gra= gra;
    }

    @Override
    /**
     * Tutti i metodi necessari per comunicare con layer di salvataggio
     */
    public BackStateGame_Enum execute(Vector<String> nothing)
    {
        if( nothing.size()>1 )
         return BackStateGame_Enum.ERROR_DIGIT;

        //_siccome possono essere tanti, faccio la pulizia dello schermo prima
        (new ClearConsole(gra)).execute(nothing);

        //_ottengo i nomi
        List<String> salvataggi= SaveAdapter.GetUploadedFiles();
        System.out.println( "Numero di salvataggi disponibili: "+salvataggi.size() );//DEBUG

        if(salvataggi.size() > 0)
        {
            int num= chooseFile(salvataggi);
            System.out.println(num);

            if( num == ERRORE_INPUT )
             return BackStateGame_Enum.SAVE;

            //_carico tutto             (Alex ha detto che non serve il runsetu)
            SaveAdapter.DownloadGame(num);
            gme.loadPlayer( SaveAdapter.retrievePlayer() );
            gme.loadMap(    SaveAdapter.retrieveMap() );

            gra.fromBufferToGraphic("Partita è stata caricata. Ben tornato/a "+
                                    gme.getPlayer().getName()+
                                    "\nSolo un folle lo farebbe\n");
        }
        else gra.fromBufferToGraphic("Non sono presenti dei salvataggi");

        return BackStateGame_Enum.SAVE;
    }

    private int chooseFile(List<String> ls)
    {
        gra.fromBufferToGraphic("Salvataggi disponibili, inserire indice:\n");
        for(int i=0; i<ls.size(); i++)
         gra.fromBufferToGraphic( i+")"+ls.get(i)+"\n" );

        int numSalvataggio=-1;  //numero a caso
       // boolean valoreNonValido= true;
        
        //do{
            try
            {
                numSalvataggio = Integer.parseInt( gra.busyWaitInput() );
                //valoreNonValido= false;
            }
            catch(NumberFormatException e)
            { gra.fromBufferToGraphic("Non esiste il salvataggio\n"); return ERRORE_INPUT; }

       // }while( valoreNonValido );

        return numSalvataggio;
    }
}
