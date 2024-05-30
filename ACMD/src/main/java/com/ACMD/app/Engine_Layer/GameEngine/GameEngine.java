package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.ArrayList;
import java.util.Stack;

import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;

public class GameEngine{
    Player p;
    Stack<Coordinates> playerStack;
    MapGraph map;
    Boolean lose;
    String buffer;

    /**
     * Costruttore di default
     */
    public GameEngine(){
        p = null;
        map = null;
        buffer = null;
    }

    /**
     * Metodo che fa partire il game engine inizializzato player, mappa
     */
    public void runSetup(String playerName){
        p = new Player(playerName);
        map = new MapGraph();
        buffer = "";

        //aggiunta degli observer(Monster) a player
        ArrayList<Monster> list = map.getAllMonster();
        for(Monster m: list){
            p.addObserver(m);
        }
    }

    /**
     * Scrive nel buffer interno cosa c'è attorno al playr
     */
    public void lookAround(){
        String str = "";
        Coordinates cord = map.getPlayerPos();
        ArrayList<Direction> list = map.validDirectionOf(cord);
        str+="Attualmente "+p.getName()+"è alla posizione " + cord.toString();
        str += "Le direzioni disponibili sono:";
        for(Direction d: list){
            str += "\n-"+d;
        }

        if(map.isStanza(cord)){
            Monster m= map.getMonsterAt(cord);
            str+= "Attento c'è"+m.getName()+" a lv."+m.getLv();
            Chest chest = map.getChestAt(cord);
            if(chest.getLock())
                str+="Ce anche una chest ma è bloccata cosa ci sara dentro?";
            else{
                str+="La chest è sbloccata gli item al suo interno sono:"+chest;
            }
        }

        buffer += str;
    }

    /**
     * Se il player si trova in una stanza infligge danno al mostro, altrimenti lancia
     * un eccezzione
     */
    public void attack() throws IllegalArgumentException{
        if(lose){
            throw new IllegalArgumentException("Il player ha perso");
        }

        Coordinates cord = map.getPlayerPos();
        if(map.isStanza(cord))
            throw new IllegalArgumentException("Player si trova nel nodo: " + cord + " non sono presenti nemici");
        
        Monster m = map.getMonsterAt(cord);
        if(!map.isFreeRoomAt(cord)){
            playerAttack(m);
            if(m.getLife() > 0){
                monsterAttack(m);
            }
        }
    }

    /**
     * Metodo per attacare monster da player se il mostro muore freeRoom viene impostato a true
     * @param m Monster che subisce l'attacco
     */
    private void playerAttack(Monster m){
        short healthChange = (short)-(p.getAttack() - m.getArmor());
        if(healthChange < 0){
            m.changeHealth(healthChange);

            if(m.getLife() <= 0){
                buffer += p.getName() + " hai sconfitto il mostro" + m.getName() + "ora puoi aprire la chest\n";
                map.setFreeRoomAt(map.getPlayerPos());
            }
        }
    }

    /**
     * Metodo per attaccare il player dal mostro se il player muore allora lose viene impostato a true
     * @param m Mostro che attacca
     * 
     */
    private void monsterAttack(Monster m){
        short healthChange = (short)-(m.getAttack() - p.getArmor());

        if(healthChange < 0){
            p.changeHealth(healthChange);
            if(p.getLife() < 0)
                lose = true;
        }
    }

    /**
     * Restituisce true se il player può attaccare
     * @return boolean
     */
    public boolean playerCanAttack(){
        return map.isStanza(map.getPlayerPos());
    }

    /**
     * Sposta il Player alla posizione precedente
     */
    public void movePlayerBack(){
        if(!playerStack.empty()){
            map.setPlayerPos(playerStack.pop());
        }
    }

    /**
     * Il player si sposta dalla posizione attuale seguendo Direction se il player entra in una
     * stanza con un mostro allora viene aggiunta in buffer la storia del mostro
     * @param d Direction
     */
    public void movePlayer(Direction d){
        Monster m;
        try{
            if(map.isValidDirectionTo(map.getPlayerPos(), d)){
                map.movePlayerTo(d);
                playerStack.add(map.getPlayerPos());
                if(map.isStanza(map.getPlayerPos())){
                    m = map.getMonsterAt(map.getPlayerPos());
                    if(m.getLife() > 0){
                        buffer += m.getHistory()+"\n";
                    }
                }
            }
        }
        catch(Exception e){}
    }

}