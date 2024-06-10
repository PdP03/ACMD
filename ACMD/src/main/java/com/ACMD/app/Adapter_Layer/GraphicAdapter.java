package com.ACMD.app.Adapter_Layer;

import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Graphic_Layer.GUI.GameFrame;

/*
NOTA: questa classe mi ha fatto capire quanto sia importante tenere basso il coupling
 modificando il nome in un metodo da Matteo cambiando solo qui continua a funzionare ovunque
 mettendo qui il metodo per il controllo dell'input funziona tutto dapertutto senza dover riscrivere pressapoco nulla

IMPORTANTE: essendo appunto un layer e collegato a molti metodi (perché uso command pattern) è molto importante che
 la priorità di questi metodi sia in assoluto la loro atomicità, devono solo fare da tramite, altrimenti si potrebbero
 davvero avere modifiche inaspettate in vari punti del programmma e non capire perché

Altre 2 cose:
-per diminuire il coupling nel command pattern mi sa che rimetto gli aggiornamenti delle barre dentro lo switch.. non
    so se questo sia meglio o peggio perché da una parte metterlo nel singolo comando che và a richiamare è una cosa
    che facilita la lettura e la comprensione per il debug del programma dato che significa sapere chi fa cosa;
    d'altra parte significa aumentare il numero di dipendenze
        _almeno quelli singoli posso farli dentro il metodo (es: start)
-divertente come far scrivere direttamente nel layer di grafica senza passare per l'engine a volte sia stato molto più
    comodo per modifiche fatte dopo; in particolare per il menù di start che non ha un engine ancora setup-pato
*/
public class GraphicAdapter
{
    private GameFrame gmf;
    private final int limiteVita = 5;

    public GraphicAdapter(GameFrame g)
    {
        gmf = g;
    }
    /*public GameFrame getGraphic()   //per avere più comodo il passaggio nei salvataggi
     {return gmf;}*/

    public void reScaleLifeBar(int min, int max) { reScaleLifeBar(((double)min)/max); }            //0 a 100
    public void reScaleLifeBar(double percentile)
    {
        int val = (int)(percentile*100);
        gmf.setPlayerHealth( val < limiteVita && val > 0 ? 1 : val );
    }   //0 a 100

    public void reScaleEnemyBar(int min, int max) { reScaleEnemyBar(((double)min)/max); }          //0 a 100
    public void reScaleEnemyBar(double percentile)
    {
        int val = (int)(percentile*100);
        gmf.setEnemyHealth( val < limiteVita && val > 0 ? 1 : val );
    }//0 a 100

    public void reScaleWeightBar(int min, int max) { reScaleWeightBar(((double)min)/max); }         //0 a 100
    public void reScaleWeightBar(double percentile)
    {
        int val = (int)(percentile*100);
        gmf.setPeso( val < limiteVita && val > 0 ? 1 : val );
    }//0 a 100

    public void fromBufferToGraphic(String textOUT)
     { gmf.appendOnConsole(textOUT); }
    public void move(Coordinates coord)
     { gmf.move( coord ); }
     //TODO: controlla stringa vuota
    public String busyWaitInput()
     { String s; while( (s= gmf.textInput()) == null ); return s;}

    public void clearScreen()  { gmf.resetConsole();}

    public void showEnemyBar() { gmf.addEnemyBar(); }
    public void hideEnemyBar() { gmf.removeEnemyBar(); }

    public void showBars()
    {
        gmf.addPlayerBar();
        gmf.addWeightBar();
        gmf.addEnemyBar();
    }
    public void hideBars()
    {
        gmf.removePlayerBar();
        gmf.removeWeightBar();
        gmf.removeEnemyBar();
    }
    
}
