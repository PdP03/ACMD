package com.ACMD.app.Engine_Layer.GameEngine;

import java.io.File;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Graphic_Layer.GUI.GameFrame;

public class main {
    static xmlReader reader;
    final static String StorageDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
    
    static final String fileName = "ItemStackConfig.xml";
    public static void main(String[] args){
      
        GameEngine engine = new GameEngine();

        reader = new xmlReader(StorageDir, fileName);
       
        Vector<ItemStack> items = reader.getAllItemStack();

        

      

        engine.runSetup("Marriconda");
        GameFrame g = new GameFrame();
        g.setVisible(true);
        //vado a trovare coboldo FUNZIONA CORRETTAMNETE
        engine.lookAround();
        System.out.println(engine.getBuffer());
        movePlayerToCoboldo(engine);
        
        //Player attacca
        PlayerAttacca(engine, 3);

        //stampo la chest
        engine.lookAround();
        System.out.println(engine.getBuffer());

       
        engine.onlyForDebugging();
        
         
       
        
        String itemChoiche = getItemNameFromChest(items, engine);
        System.out.println("selezionato: "+itemChoiche);
        if(itemChoiche != ""){
            engine.playerTake(itemChoiche);
        } 
        engine.onlyForDebugging();
        //controllo che sia stato eliminato l'item dalla chest
        
        engine.lookAround();
        System.out.println(engine.getBuffer());
 
        /* 
        //torno idietro
        movePlayerBack(engine, 4);


        //--------------------------------------------------- NON FUNZIONA DA CONTROLLARE 1---------------------------------------------------
        //vado a trovare mare
        engine.lookAround();
        System.out.println(engine.getBuffer());
        movePlayerToMare(engine);
        
        //torno indietro
        movePlayerBack(engine, 4);
        //--------------------------------------------------- FINE DEL NON FUNZIONA DA CONTROLLARE 1---------------------------------------------------
        
        //vado a trovare mago oscuro FUNZIONA CORRETTAMNETE
        movePlayerToMagoOscuro(engine);

        //Player attacca
        PlayerAttacca(engine, 3);
        engine.lookAround();
        System.out.println(engine.getBuffer());

        //torna indietro
        movePlayerBack(engine, 4);

        engine.lookAround();
        System.out.println(engine.getBuffer());
       

        //--------------------------------------------------- NON FUNZIONA DA CONTROLLARE 2---------------------------------------------------
        //vado a trovare ARMATURA qui IL NODO NON è UNA STANZA ma la transizione avviene correttamente poiche le direzini disponbili sono -nessuna direzione disponibile
        movePlayerToArmatura(engine);

        System.out.println(engine.getBuffer());
        //--------------------------------------------------- NON FUNZIONA DA CONTROLLARE 2---------------------------------------------------
           //torno idietro
         movePlayerBack(engine, 4);

        //vado a trovare mago oscuro FUNZIONA CORRETTAMNETE
        movePlayerToMagoOscuro(engine);

        //Player attacca
        PlayerAttacca(engine, 5);
        engine.lookAround();
        System.out.println(engine.getBuffer());

        //torna indietro
        movePlayerBack(engine, 4);

        engine.lookAround();
        System.out.println(engine.getBuffer());

        engine.lookAround();
        System.out.println(engine.getBuffer());
        */
    }

    /**
     * Fa attaccare il player per i volte stampa cosa avviene 
     * @param engine
     * @param i volte che il player attacca
     */
    public static void PlayerAttacca(GameEngine engine, int i){
        for(int j = 0; j < i; j++){
            engine.attack();
        System.out.println(engine.getBuffer());
        }
    }

    /**
     * Sposta indietro il player di i volte
     * @param engine
     * @param i
     */
    public static void movePlayerBack(GameEngine engine, int i){
        for(int j = 0; j < i; j++){
            engine.movePlayerBack();
        }
    }

    /**
     * Sposta il player per andare verso armatura SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * stampa cosa avveie
     * @param engine
     */
    public static void movePlayerToArmatura(GameEngine engine){
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

    /**
     * Sposta il player per andare verso coboldo SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * stampa cosa avviene
     * @param engine
     */
    public static void movePlayerToCoboldo(GameEngine engine){
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
    }

    /**
     * 
     * Sposta il player per andare verso Mago Oscuro SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * stampa cosa avveie
     * @param engine
     */
    public static void movePlayerToMagoOscuro(GameEngine engine){
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.EAST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        System.out.println(engine.getBuffer());
    }

    /**
     * Sposta il player per andare verso mare SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * stampa cosa avveie
     * @param engine
     */
    public static void movePlayerToMare(GameEngine engine){
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        System.out.println(engine.getBuffer());
        engine.movePlayer(Direction.EAST);
        engine.lookAround();
        System.out.println(engine.getBuffer());
    }


    /**
     * restituisce il nome del item che puo prendere
     * @return
     */
    public static String getItemNameFromChest(Vector<ItemStack> item, GameEngine engine){
        String name = "";
        for(ItemStack it: item){
            name = it.getName();
            if(engine.canPlayerTake(name)){
                
                return name;
            }
        }

        return "";
    }
   
}
