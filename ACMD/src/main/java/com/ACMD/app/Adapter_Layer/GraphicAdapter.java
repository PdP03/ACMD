package com.ACMD.app.Adapter_Layer;

import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Graphic_Layer.GUI.GameFrame;

public class GraphicAdapter
{
    private GameFrame gmf;

    public GraphicAdapter(GameFrame g)
    {
        gmf = g;
    }

    public void reScaleLifeBar(int min, int max) { reScaleLifeBar(((double)min)/max); }            //0 a 100
    public void reScaleLifeBar(double percentile){ gmf.setPlayerHealth( (int)(percentile*100) ); } //0 a 100

    public void reScaleEnemyBar(int min, int max) { reScaleEnemyBar(((double)min)/max); }          //0 a 100
    public void reScaleEnemyBar(double percentile){ gmf.setEnemyHealth( (int)(percentile*100) ); }//0 a 100

    public void reScaleWeightBar(int min, int max) { reScaleWeightBar(((double)min)/max); }         //0 a 100
    public void reScaleWeightBar(double percentile){ gmf.setPeso( (int)(percentile*100) ); }//0 a 100

    public void fromBufferToGraphic(String textOUT)
     { gmf.writeBlueOnConsole(textOUT); }
    public void move(Coordinates coord)
     { gmf.move( coord ); }
     //TODO: controlla stringa vuota
    public String busyWaitInput()
     { String s; while( (s= gmf.textInput()) == null ); return s;}
    
}
