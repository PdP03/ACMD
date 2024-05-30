package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.ArrayList;
import java.util.Stack;

import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;
import com.ACMD.app.Engine_Layer.StorageManagement.InventoryOutOfBound_Exception;

/**
 * Classe che implementa tutti i comportamenti neccessari per far interagire Player
 * con Monster, Chest e Mappa:
 * - lose, variabile booleana se true il player è morto
 * - buffer, stringa che contine il testo da stampare
 */
public class GameEngine{
    Player p;
    Stack<Coordinates> playerStack;
    MapGraph map;
    Boolean lose;
    String buffer;

    /**
     * Costruttore di default istanzia solo GameEngine non è possibile utilizzarla
     * se prima non si richiama runSetup(String)
     */
    public GameEngine(){
        p = null;
        map = null;
        buffer = null;
        lose = null;
    }

    /**
     * Metodo che fa partire il game engine inizializzato player, mappa
     */
    public void runSetup(String playerName){
        lose = false;
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
     * Scrive nel buffer cosa c'è attorno al playr in particolare:
     * <ol>
     *      <li>nome giocatore e posizione del giocatore</li>
     *      <li>lista direzioni disponibli:
     *          <ul>
     *              <li>direzione 1</li>
     *              <li>....</li>
     *          </ul>
     *      </li>
     *      <li>descrizione della stanza ovvero:
     *          <ul>
     *              <li>nome del mostro e livello</li>
     *              <li>info sulla chest</li>
     *          </ul>
     *      </li>
     * </ol>
     */
    public void lookAround(){
        Coordinates cord = map.getPlayerPos();
        
        buffer+="Attualmente "+p.getName()+" sei nella posizione " + cord.toString()+"\n";
        buffer += getNearDirection(cord);
        buffer += getRoomInfo(cord);
    }

    /**
     * Metodo di utilità che restituisce una stringa con le informazioni sulla stanza
     * @param c coordinate della stanza
     * @return stringa con la descrizione della stanza
     */
    private String getRoomInfo(Coordinates c){
        String str = "";
        if(map.isStanza(c)){
            Monster m= map.getMonsterAt(c);
            str+= "Attento c'è "+m.getName()+"(lv."+m.getLv()+")";
            Chest chest = map.getChestAt(c);
            if(chest.getLock())
                str+="Ce anche una chest ma è bloccata cosa ci sara dentro?";
            else{
                str+="La chest è sbloccata gli item al suo interno sono:"+chest;
            }

            return str+"\n";
        }
        return str;
    }

    /**
     * Metodo di utilità che restituisce una stringa con le posizione disponibili
     * @param c coordinate in cui verificare le direzioni disponibli
     * @return stringa che descrive le direzioni disponibli
     */
    private String getNearDirection(Coordinates c){
        String str="";
        ArrayList<Direction> list = map.validDirectionOf(c);
        str += "Le direzioni disponibili sono:";
        for(Direction d: list){
            str += "\n-"+d;
        }
        if(list.size() == 0){
            str += "\n- nessuna direzione disponibile";
        }

        return str+"\n";
    }

    /**
     * Metodo che restituisce true se il player è ancora in vita
     * @return boolean
     */
    public boolean playerIsAlive(){
        return !lose;
    }

    /**
     * Se il player si trova in una stanza prima attacca il mostro, poi riceve danno
     * dal attacco del mostro. Lancia DeathException se il player è morto oppure IllegalArgumentException
     * se il player non si trova in una stanza
     */
    public void attack() throws DeathException, IllegalArgumentException{
        if(lose){
            throw new DeathException();
        }

        Coordinates cord = map.getPlayerPos();
        if(!map.isStanza(cord))
            throw new IllegalArgumentException("Player si trova nel nodo: " + cord + " non sono presenti nemici\n");
        
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
                buffer += p.getName() + " hai sconfitto il mostro: " + m.getName() + " ora puoi aprire la chest\n";
                map.setFreeRoomAt(map.getPlayerPos());
            }
        }
    }

    /**
     * Metodo per attaccare il player dal mostro se il player muore allora lose viene impostato a true
     * @param m Mostro che attacca
     */
    private void monsterAttack(Monster m){
        short healthChange = (short)-(m.getAttack() - p.getArmor());

        if(healthChange < 0){
            p.changeHealth(healthChange);
            if(p.getLife() < 0){
                lose = true;
                buffer += "Mi dispiace sei stato sconfitto da: "+m.getName()+" fai un nuovo tentativo!\n";
            }
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
    public void movePlayer(Direction d) throws IllegalArgumentException{
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
                else{
                    buffer += p.getName()+" hai raggiunto il nodo "+map.getPlayerPos()+".\n";
                }
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Sono un eccezione inutile non dovrei mai essere lanciata");
        }
    }

}