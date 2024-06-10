package test.java.com.ACMD.app.Kernel_Layer.Menu;

import org.junit.Test;

import com.ACMD.app.Adapter_Layer.GraphicAdapter;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Kernel_Layer.Menu.BattleMenu;
import com.ACMD.app.Kernel_Layer.Menu.MovementMenu;
import com.ACMD.app.Kernel_Layer.Menu.StartMenu;

public class MenuGenerate_test
{

    @Test
    public void menuBattle()
    {
        new BattleMenu(new GameEngine(false),new GraphicAdapter(null));
        //si supera se non vengono lanciare eccezioni
    }
    @Test
    public void menuMovement()
    {
        new MovementMenu( new GameEngine(false),new GraphicAdapter(null) );
        //si supera se non vengono lanciare eccezioni
    }
    @Test
    public void menuStart()
    {
        new StartMenu( new GameEngine(false),new GraphicAdapter(null) );
        //si supera se non vengono lanciare eccezioni
    }


}
