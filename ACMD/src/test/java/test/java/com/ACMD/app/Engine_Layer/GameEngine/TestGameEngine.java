package test.java.com.ACMD.app.Engine_Layer.GameEngine;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ACMD.app.Engine_Layer.xmlReader;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.GameEngine.DeathException;
import com.ACMD.app.Engine_Layer.GameEngine.GameEngine;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;

public class TestGameEngine {
    GameEngine engine;

    @Before
    public void startup(){
        engine = new GameEngine(false);
        engine.runSetup("prova");
    }

    @Test
    public void testRunSetup(){
        Assert.assertEquals(engine.getBuffer(), "");

        // ------------ CHECK INIZIALIZZAZIONE PLAYER ------------
        engine.lookAround();
        Assert.assertTrue(engine.getBuffer().contains("prova"));
    }

    @Test
    public void testLookAround(){
        engine.lookAround();
        String buffer = engine.getBuffer();

        Assert.assertTrue(buffer.contains("INFO"));
        Assert.assertTrue(buffer.contains((new Coordinates(1,19)).toString()));
        Assert.assertTrue(buffer.contains("-NORTH"));
    }

    @Test
    public void testPlayerIsAlive(){
        Assert.assertTrue(engine.playerIsAlive());
    }

    @Test
    public void testPlayerCanAttack(){
        Assert.assertFalse(engine.playerCanAttack());

        movePlayerToCoboldo(engine);
        Assert.assertTrue(engine.playerCanAttack());
    }

    @Test(expected=DeathException.class)
    public void testAttack(){
        // ------------ CHECK SU COBOLDO ------------
        movePlayerToCoboldo(engine);
        Assert.assertTrue(engine.getBuffer().contains("Coboldo"));
        PlayerAttacca(engine);
        Assert.assertTrue(engine.getBuffer().contains("WIN"));
        movePlayerBack(engine, 5);

        // ------------ CHECK SU ARMATURA ------------
        movePlayerToArmatura(engine);
        Assert.assertTrue(engine.getBuffer().contains("Armatura"));
        PlayerAttacca(engine);
        Assert.assertTrue(engine.getBuffer().contains("WIN"));
        movePlayerBack(engine, 5);

        // ------------ CHECK SU MAGO OSCURO (il player dovrebbe morire)------------
        movePlayerToMagoOscuro(engine);
        Assert.assertTrue(engine.getBuffer().contains("Mago Oscuro"));
        PlayerAttacca(engine);
    }

    @Test
    public void testMovePlayerBack(){
        movePlayerToCoboldo(engine);
        engine.getBuffer();
        Assert.assertEquals("", engine.getBuffer());
        engine.movePlayerBack();
        engine.lookAround();
        Assert.assertTrue(engine.getBuffer().contains((new Coordinates(2,11)).toString()));
        engine.movePlayerBack();
        engine.lookAround();
        Assert.assertTrue(engine.getBuffer().contains((new Coordinates(8,13)).toString()));
        engine.movePlayerBack();
        engine.lookAround();
        Assert.assertTrue(engine.getBuffer().contains((new Coordinates(1,19)).toString()));
    }

    @Test
    public void testCanPlayerTake(){
        final String StorageDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
        final String fileName = "ItemStackConfig.xml";
        xmlReader reader = new xmlReader(StorageDir, fileName);

        movePlayerToCoboldo(engine);
        try{
            PlayerAttacca(engine);
            Assert.assertTrue(engine.getBuffer().contains("WIN"));
        }
        catch(DeathException e){}//Tutto ok il player può morire
            

        // ------------ CHECK SE L'ITEM ESISTE ------------
        String itemName = getItemNameFromChest(reader.getAllItemStack(), engine);
        if(itemName != ""){
            Assert.assertTrue(engine.canPlayerTake(itemName));
        }

        // ------------ CHECK PER UN ITEM CHE NON ESISTE ------------
        Assert.assertFalse(engine.canPlayerTake("item_che_non_esiste"));
    }

    @Test
    public void testPlayerTake(){
        final String StorageDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
        final String fileName = "ItemStackConfig.xml";
        xmlReader reader = new xmlReader(StorageDir, fileName);

        movePlayerToCoboldo(engine);
        try{
        PlayerAttacca(engine);
        Assert.assertTrue(engine.getBuffer().contains("WIN"));
        }
        catch(DeathException e){} //Tutto ok il player può morire

        // ------------ CHECK DOPO CHE PLAYER HA PRESO UN ITEM ------------
        String itemName = getItemNameFromChest(reader.getAllItemStack(), engine);
        if(itemName != ""){
            engine.playerTake(itemName);
            String inv = engine.getPlayerInv();
            Assert.assertTrue(inv.contains("Peso"));
            Assert.assertTrue(inv.contains("Valore"));
            Assert.assertTrue(inv.contains("Quantità"));
        }
    }

    @Test
    public void testPlayerHave(){
        final String StorageDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\StorageManagement\\";
        final String fileName = "ItemStackConfig.xml";
        xmlReader reader = new xmlReader(StorageDir, fileName);

        movePlayerToCoboldo(engine);
        try{
        PlayerAttacca(engine);
        Assert.assertTrue(engine.getBuffer().contains("WIN"));
        }
        catch(DeathException e){}//Tutto ok il player può morire

        // ------------ CHECK SU UN ITEM ESISTENTE ------------
        String itemName = getItemNameFromChest(reader.getAllItemStack(), engine);
        if(itemName != ""){
            engine.playerTake(itemName);
            Assert.assertTrue(engine.playerHave(itemName));
        }

        // ------------ CHECK SU UN ITEM CHE NON ESISTE ------------
        Assert.assertFalse(engine.playerHave("item_che_non_esiste"));
    }

    @Test
    public void testGetPlayerInv(){
        String inv = engine.getPlayerInv();
        Assert.assertEquals("", inv);
    }

    @Test
    public void testGetPlayerMaxLife(){
        Assert.assertEquals(5, engine.getPlayerMaxLife());
    }

    @Test
    public void testGetPlayerLife(){
        Assert.assertEquals(5, engine.getPlayerLife());
    }

    @Test
    public void testGetMonsterMaxLife(){
        movePlayerToCoboldo(engine);
        Assert.assertEquals(3, engine.getMonsterMaxLife());
    }

    @Test
    public void testGetMonsterLife(){
        movePlayerToCoboldo(engine);
        Assert.assertEquals(3, engine.getMonsterLife());
    }

    @Test
    public void testLoadPlayer(){
        engine.loadPlayer(new Player("Piero"));
        engine.lookAround();
        Assert.assertTrue(engine.getBuffer().contains("Piero"));
    }

    /*
     * ---------------------------------------------------------------------
     * |         METODI PRIVATI PER SPOSTARE IL PLAYER NELLE STANZE        |
     * ---------------------------------------------------------------------
     */

    /**
     * Sposta il player per andare verso coboldo SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * @param engine
     */
    private static void movePlayerToCoboldo(GameEngine engine){
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
    }

    /**
     * Sposta il player per andare verso armatura SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * @param engine
     */
    private static void movePlayerToArmatura(GameEngine engine){
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        engine.movePlayer(Direction.EAST);
        engine.lookAround();
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
        engine.movePlayer(Direction.WEST);
        engine.lookAround();
    }

    /**
     * 
     * Sposta il player per andare verso Mago Oscuro SI ASSUME CHE IL PAYER INIZIALMENTE ALLE CORDINATE (1,19)
     * stampa cosa avveie
     * @param engine
     */
    private static void movePlayerToMagoOscuro(GameEngine engine){
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
        engine.movePlayer(Direction.EAST);
        engine.lookAround();
        engine.movePlayer(Direction.NORTH);
        engine.lookAround();
    }

     /**
     * Fa attaccare il player per i volte stampa cosa avviene 
     * @param engine
     * @param i volte che il player attacca
     */
    private static void PlayerAttacca(GameEngine engine){
        int i = 0;
        while(engine.playerCanAttack()){
            engine.attack();
            i++;
            if(i >= 300){
                throw new DeathException();
            }
        }
    }

    /**
     * Sposta indietro il player di i volte
     * @param engine
     * @param i
     */
    private static void movePlayerBack(GameEngine engine, int quantity){
        for(int j = 0; j < quantity; j++){
            engine.movePlayerBack();
        }
    }

     /**
     * restituisce il nome del item che puo prendere
     * @return
     */
    private static String getItemNameFromChest(Vector<ItemStack> item, GameEngine engine){
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
