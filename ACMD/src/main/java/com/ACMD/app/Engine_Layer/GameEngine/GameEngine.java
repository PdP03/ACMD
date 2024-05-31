package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.Player;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;
import com.ACMD.app.Engine_Layer.StorageManagement.Chest;
import com.ACMD.app.Engine_Layer.StorageManagement.Inventario;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemFactory;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;
import com.ACMD.app.Engine_Layer.StorageManagement.noItem_Exception;

/**
 * Classe che implementa tutti i comportamenti neccessari per far interagire Player
 * con Monster, Chest e Mappa:
 * - lose, variabile booleana se true il player è morto
 * - buffer, stringa che contine il testo da stampare
 */
public class GameEngine{
    final byte MAX_POTION_USAGE = 3;
    final byte INSTANT_POTION_USAGE = 1;
    ItemFactory factory;
    Player p;
    Stack<Coordinates> playerStack;
    MapGraph map;
    Boolean lose;
    String buffer;
    Map<ItemType, Byte> potionsActiveted;

    /**
     * Costruttore di default istanzia solo GameEngine non è possibile utilizzarla
     * se prima non si richiama runSetup(String)
     */
    public GameEngine(){
        p = null;
        map = null;
        buffer = null;
        lose = null;
        potionsActiveted = null;
        playerStack = null;
        factory = null;
    }

    /**
     * Metodo che fa partire il game engine inizializzato player, mappa
     */
    public void runSetup(String playerName){
        lose = false;
        p = new Player(playerName);
        map = new MapGraph();
        buffer = "";
        playerStack = new Stack<Coordinates>();
        potionsActiveted = new HashMap<ItemType, Byte>();
        factory = new ItemFactory();

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
     * Ottiene il valore della pozione passata come ItemType è decrementa gli utilizzi
     * delle pozioni attivate se la pozione non è stata attivare allora il valore è 0
     * @param t ItemType da valutare
     * @return byte valore della pozione
     */
    private byte getPotionValue(ItemType t){
        Byte uses = potionsActiveted.get(t);
        if(uses == null){
            return 0;
        }
        uses--;
        if(uses == 0){
            potionsActiveted.remove(t);
        }
        if(uses < 0){
            return 0;
        }
        buffer += "Stai usando pozione "+ t.name();
        return (byte)factory.getItem(t).getValue();
    }

    /**
     * Metodo per attacare monster da player se il mostro muore freeRoom viene impostato a true
     * @param m Monster che subisce l'attacco
     */
    private void playerAttack(Monster m){
        short healthChange = (short)-(p.getAttack() - m.getArmor() + getPotionValue(ItemType.POZIONE_FORZA) + getPotionValue(ItemType.POZIONE_VELENO));
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
        short healthChange = (short)-(m.getAttack() - p.getArmor() - getPotionValue(ItemType.POZIONE_RESISTENZA) - getPotionValue(ItemType.POZIONE_INVALIDITA));

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
        if(!map.isStanza(map.getPlayerPos())){
            return false;
        }

        return map.getMonsterAt(map.getPlayerPos()).getLife() > 0;
    }

    /**
     * Sposta il Player alla posizione precedente
     */
    public void movePlayerBack(){
        if(!playerStack.empty()){
            Coordinates c = playerStack.pop();
            System.out.println(c);
            map.setPlayerPos(c);
        }

        buffer = "";
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
                playerStack.add(map.getPlayerPos());
                map.movePlayerTo(d);
                System.out.println("Dico al player di andare a "+d);
                try{
                System.out.println(map.isStanza(map.getPlayerPos()));
                }
                catch(NoSuchElementException e){
                    System.out.println("Nodo non trovato");
                }
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
            e.printStackTrace();
            throw new IllegalArgumentException("Sono un' eccezione inutile che non dovrebbe mia essere lanciata");
        }
    }

    /**
     * Restituisce true se item può essere preso dal player ovvero se le seguenti condizioni sono verificate:
     * <ul>
     *      <li>player è in una stana</li>
     *      <li>la chest è sbloccata</li>
     *      <li>la chest contiene l'item</li>
     *      <li>l'item non riempie l'inventario del player</li>
     * </ul>
     * @param item nome del item da controllare
     * @return true se l'item può essere preso altrimenti false
     */
    @SuppressWarnings("unused")
    public boolean canPlayerTake(String item){
        if(!map.isStanza(map.getPlayerPos())){
            return false;
        }
        
        Chest c = map.getChestAt(map.getPlayerPos());
        if(c.getLock()){
            return false;
        }

        ItemStack it = c.searchFor(item);
        if(c == null){
            return false;
        }
        
        return !p.doesFillInv(it);
    }

    /**
     * ottiene rimuove l'item dalla chest e lo inserisce nel inventario del 
     * player se l'oggetto non esiste allora viene lanciata noItem_Exception
     * @param item nome del item da prendere
     */
    public void playerTake(String item)throws noItem_Exception{
        if(!canPlayerTake(item)){
            throw new noItem_Exception();
        }
        
        Chest c = map.getChestAt(map.getPlayerPos());
        ItemStack it = c.searchFor(item);
        c.remove(it);
        p.addItem(it);
    }

    /**
     * Restituisce true se player ha l'item
     * @return boolean
     */
    public boolean playerHave(String item){
        return p.getInv().searchFor(item) != null;
    }

    //TODO: INIZIO CODICE DA RIFARE
    /**
     * Prende un item al interno del inventario e gli applica l'effetto, se item non
     * viene trovato viene lanciata noItem_Exception
     * @param item
     */
    public void playerUse(String item)throws noItem_Exception, IllegalArgumentException{
        ItemStack it = p.getInv().searchFor(item);
        if(it == null){
            throw new noItem_Exception();
        }

        if(it.getType() == ItemType.CIBO){
            p.changeHealth((short)it.getValue());
            p.removeItem(it);
            return;
        }

        ItemType type = getPotion(it);
        if(type == null){
            throw new IllegalArgumentException("Questo item: "+it.getName()+" non può essere usato");
        }
        if(potionsActiveted.get(type) != null){//se il player vuole attivare 2 pozioni dello stesso tipo la 2 NON viene usata
            return;
        }

        applayPotion(it);
        p.removeItem(it);
    }

    /**
     * restituisce il tipo della pozione. Se item non è una pozione allora restituisce null 
     * @param item Item da verificare
     * @return ItemType solo se è una pozione
     */
    private ItemType getPotion(ItemStack item){
        String str = item.getType().name();
        
        if(str.contains("POZIONE")){
            return item.getType();
        }

        return null;
    }

    /**
     * Applica l'effetto della pozione al player in particolare:
     * <ul>
     *      <li>POZIONE_CURA, cura istantaneamente il player</li>
     *      <li>POZIONE_DANNO, fa danno istantaneo al player</li>
     *      <li>POZIONE_FORZA, aumenta per 3 turni il danno del player</li>
     *      <li>POZIONE_RESISTENZA, aumenta per 3 turni la difesa del player</li>
     *      <li>POZIONE_INVALIDITA, diminuisce per 3 turni il l'attacco di monster</li>
     *      <li>POZIONE_VELENO, diminuisce per 3 turni la difesa di monster</li>
     * </ul>
     * @param item
     */
    private void applayPotion(ItemStack item){
        switch(item.getType()){
            case POZIONE_CURA:
                p.changeHealth((short)item.getValue());
                break;
            case POZIONE_DANNO:
                p.changeHealth((short)-item.getValue());
                break;
            case POZIONE_FORZA:
                potionsActiveted.put(ItemType.POZIONE_FORZA, MAX_POTION_USAGE);
                break;
            case POZIONE_INVALIDITA:
                potionsActiveted.put(ItemType.POZIONE_INVALIDITA, MAX_POTION_USAGE);
                break;
            case POZIONE_RESISTENZA:
                potionsActiveted.put(ItemType.POZIONE_RESISTENZA, MAX_POTION_USAGE);
                break;
            case POZIONE_VELENO:
                potionsActiveted.put(ItemType.POZIONE_VELENO, MAX_POTION_USAGE);
                break;
            default:
                return;
        }
    }

    //TODO: FINE CODICE DA RIFARE

    /**
     * Rimuove un item dal inventario del player lancia noItem_Exception se
     * l'item non è presente nel inventario
     * @param item da rimuovere
     */
    public void playerRemove(String item) throws noItem_Exception{
        Inventario inv = p.getInv();
        ItemStack i = inv.searchFor(item);
        if(i==null){
            throw new noItem_Exception();
        }

        inv.remove(i);
    }

    /**
     * Rimuove tutti gli item con lo stesso nome prensenti nel inventario
     * @param item da rimuovere
     */
    public void playerRemoveStack(String item) throws noItem_Exception{
        Inventario inv = p.getInv();
        ItemStack i = inv.searchFor(item);
        if(i==null){
            throw new noItem_Exception();
        }

        do{
            inv.remove(i);
        }
        while(inv.searchFor(item) != null);
    }

    /**
     * Restituisce gli elementi presenti nel inventario in formato stringa
     * @return
     */
    public String getPlayerInv(){
        return ""+p.getInv();
    }

    /**
     * Restituisce la vita max del player, lancia DeathException se il player è morto
     * @return short massima vita del player
     */
    public short getPlayerMaxLife(){
        if(lose){
            throw new DeathException();
        }
        
        return p.getMaxLife();
    }

    /**
     * Restituisce la vita attuale del player, lancia DeathException se il player è morto
     * @return short vita attuale
     */
    public short getPlayerLife() throws DeathException{
        if(lose){
            throw new DeathException();
        }
        
        return p.getLife();
    }

    /**
     * Restituisce il livello atuale del player, lancia DeathException se il player è morto
     * @return byte livello
     */
    public byte getPlayerLv() throws DeathException{
        if(lose){
            throw new DeathException();
        }

        return p.getLv();
    }

    /**
     * Ottine la vita max di un mostro. Lancia IllegalAccessError se non ci sono mostri nella 
     * posizione del player (ovvero se player può attaccare)
     * @return short vita massima
     */
    public short getMonsterMaxLife() throws IllegalAccessError{
        if(!playerCanAttack()){
            throw new IllegalAccessError(); 
        }

        return map.getMonsterAt(map.getPlayerPos()).getMaxLife();
    }
    
    /**
     * Ottine la vita attuale del mostro. Lancia IllegalAccessError se non ci sono mostri nella 
     * posizione del player (ovvero se player può attaccare)
     * @return short vita attuale
     */
    public short getMonsterLife() throws IllegalAccessError{
        if(!playerCanAttack()){
            throw new IllegalAccessError(); 
        }

        return map.getMonsterAt(map.getPlayerPos()).getLife();
    }

    /**
     * Svuota il buffer e restituisce il contenuto in una stringa
     * @return String eventi
     */
    public String getBuffer(){
        String s = buffer;
        buffer = "";
        return s;
    }

    /**
     * Carica un nuovo player con i settaggi passati
     * @param newPlayer
     */
    public void loadPlayer(Player newPlayer){
        p = newPlayer;
        ArrayList<Monster> list = map.getAllMonster();
        for(Monster m: list){
            p.addObserver(m);
        }

        lose = false;
        buffer = "";
        potionsActiveted = new HashMap<ItemType, Byte>();
    }

    /**
     * Carica una nuova mappa con i settaggi passati
     * @param newMap
     */
    public void loadMap(MapGraph newMap){
        map = newMap;
    }


}