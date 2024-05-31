package com.ACMD.app.Engine_Layer.GameEngine;

import com.ACMD.app.Engine_Layer.Mappa.Direction;

public class main {
    public static void main(String[] args){
        GameEngine engine = new GameEngine();

        engine.runSetup("Marriconda");

        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.EAST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
    }
    
}
