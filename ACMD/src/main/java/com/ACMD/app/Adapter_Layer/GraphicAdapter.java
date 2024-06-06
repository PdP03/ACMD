package com.ACMD.app.Adapter_Layer;

import com.ACMD.app.Graphic_Layer.GUI.GameFrame;

public class GraphicAdapter
{
    private GameFrame gmf;

    public GraphicAdapter(GameFrame g)
    {
        gmf = g;
    }
    public void reScaleLifeBar(int min, int max)
    {
        gmf.setPlayerHealth(0); //0 a 100
    }
    public void reScaleLifeBar(double percentile)
    {
        gmf.setPlayerHealth(0); //0 a 100
    }

    public void reScaleEnemyBar(int min, int max)
    {
        gmf.setEnemyHealth(0); //0 a 100
    }
    public void reScaleEnemyBar(double percentile)
    {
        gmf.setEnemyHealth(0); //0 a 100
    }

    public void reScaleWeightBar(int min, int max)
    {
        gmf.setPeso(0);       //0 a 100
    }
    public void reScaleWeightBar(double percentile)
    {
        gmf.setPeso(0);       //0 a 100
    }

    public void fromBufferToGraphic(String textOUT)
    {
        gmf.writeBlueOnConsole(textOUT);
    }
}
