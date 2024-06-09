package com.ACMD.app.Adapter_Layer;

import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Graphic_Layer.GUI.GameFrame;

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
     { gmf.writeBlueOnConsole(textOUT); }
    public void move(Coordinates coord)
     { gmf.move( coord ); }
     //TODO: controlla stringa vuota
    public String busyWaitInput()
     { String s; while( (s= gmf.textInput()) == null ); return s;}
    

    public void showEnemyBar() { gmf.addEnemy(); }
    public void hideEnemyBar() { gmf.removeEnemy(); }
    public void clearScreen()  { gmf.resetConsole();}
}
