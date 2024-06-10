package com.ACMD.app.Engine_Layer.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
    final float PROBABILITY_ATTACK_MISTAKE = 0.2f;
    Random generator;
    byte level;
    ItemFactory factory;
    Player p;
    Stack<Coordinates> playerStack;
    MapGraph map;
    boolean lose;
    String buffer;
    Map<ItemType, Byte> potionsActiveted;
    static boolean enableFormatted;

    /**
     * Costruttore di default istanzia solo GameEngine non è possibile utilizzarla
     * se prima non si richiama runSetup(String)
     */
    public GameEngine(boolean enableColor){
        p = null;
        generator = null;
        map = new MapGraph();
        buffer = null;
        lose = false;
        potionsActiveted = null;
        playerStack = null;
        factory = null;
        enableFormatted = enableColor;
    }

    /**
     * Metodo che fa partire il game engine inizializzato player, mappa
     */
    public void runSetup(String playerName){
        lose = false;
        p = new Player(playerName);
        level = p.getLv();
        buffer = "";
        playerStack = new Stack<Coordinates>();
        generator = new Random(System.currentTimeMillis());
        potionsActiveted = new HashMap<ItemType, Byte>();
        factory = new ItemFactory();

        //aggiunta degli observer(Monster) a player
        ArrayList<Monster> list = map.getAllMonster();
        for(Monster m: list){
            p.addObserver(m);
        }
    }

    /**
     * Metodo di utilita usato per formattare un testo cambiando colore
     * Definizione dei colori usando le secquenze di escape ANSI vedi:
     * @see https://en.wikipedia.org/wiki/ANSI_escape_code
     * @see https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     * @param str Stringa da formattare
     * @param colorTAG colore da applicare alla stringa
     * @return String formatta con il colore scelto
     */
    public static final String ANSI_RESET = "\u001B[0m";    //resetta il colore
    public static final String ANSI_RED = "\u001B[31m";     //setta il colore a rosso
    public static final String ANSI_CYAN = "\u001B[36m";    //setta il colore a ciano
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE_BOLD = "\033[1;34m";   // blu in grassetto

    private static String format(String str, String colorTAG){
        if(enableFormatted){
            return colorTAG+str+ANSI_RESET;
        }

        return str;
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
        buffer += "["+format("INFO", ANSI_CYAN)+"]";
        buffer += "Attualmente "+p.getName()+" sei nella posizione " + format(cord.toString(), ANSI_GREEN)+"\n";
        if(!MapGraph.isStanza(map.getPlayerPos())){
            buffer += getNearDirection(cord);
        }
        buffer += getRoomInfo(cord);
    }

    /**
     * Metodo di utilità che restituisce una stringa con le informazioni sulla stanza
     * @param c coordinate della stanza
     * @return stringa con la descrizione della stanza
     */
    private String getRoomInfo(Coordinates c){
        String str = "";
        
        if(MapGraph.isStanza(c)){
            Monster m= map.getMonsterAt(c);
            Chest chest = map.getChestAt(c);
            if(chest.isClosed())
                str+="["+format("INFO", ANSI_CYAN)+"]C'è anche una chest ma è bloccata cosa ci sara dentro?";
            else{
                str+="["+format("INFO", ANSI_CYAN)+"]La chest è sbloccata gli item al suo interno sono:\n"+chest;
            }
            if(m.getLife() > 0){
                str+= "\n["+format("INFO", ANSI_CYAN)+"]Attento c'è "+m.getName()+" (lv."+m.getLv()+")";
                str += "\n["+format(m.getName(), ANSI_RED)+"]: "+m.getHistory();
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
        str += "["+format("INFO", ANSI_CYAN)+"]"+"Le direzioni disponibili sono:";
        for(Direction d: list){
            str += "\n-"+d;
        }
        if(list.size() == 0){
            str += "\n- nessuna direzione disponibile\n";
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
     * dal attacco del mostro. Lancia DeathException se il player o mostro è morto oppure IllegalArgumentException
     * se il player non si trova in una stanza. Il player può sbagliare l'attacco con una certa probabilità
     */
    public void attack() throws DeathException, IllegalArgumentException{
        if(lose){
            throw new DeathException();
        }
        
        Coordinates cord = map.getPlayerPos();
        if(!MapGraph.isStanza(cord))
            throw new IllegalArgumentException("Player si trova nel nodo: " + cord + " non sono presenti nemici\n");
        
        Monster m = map.getMonsterAt(cord);
        if(m.getLife() <= 0){
            throw new IllegalArgumentException("Il mostro è morto non puoi ancora attaccarlo");
        }
        if(!map.isFreeRoomAt(cord)){
            potionsActiveted.forEach((key, value)->{potionsActiveted.put(key, --value);});
            short val;

            if(!throwRandomCoin(PROBABILITY_ATTACK_MISTAKE)){
                val = playerAttack(m);
                buffer+="["+format("INFO", ANSI_CYAN)+"]"+p.getName()+" hai attaccato "+ m.getName() +" infliggendo il danno "+(-val)+format(" (la vita del mostro è "+m.getLife()+")\n", ANSI_YELLOW);
            } 
            else{
                buffer+="["+format("INFO", ANSI_CYAN)+"]"+p.getName() +" sei scivolato e non hai colpito il mostro\n";
            }
            if(m.getLife() > 0){
                val = monsterAttack(m);
                buffer += "["+format("INFO", ANSI_CYAN)+"]"+m.getName()+" ti ha attaccato infliggendo il danno "+(-val)+format(" (la vita di "+p.getName()+" è "+p.getLife()+")\n", ANSI_YELLOW);
            }
            else{
                buffer += "["+format("WIN", ANSI_BLUE_BOLD)+"]"+p.getName() + " hai sconfitto " + m.getName() + " la chest si è aperta potresti trovare oggetti interressanti\n";
            }
        }
    }

    /**
     * Lancia una moneta con probabilità esce testa = val e restistuice true se esce testa
     * @param val probabilità  (0<val<1)
     * @return boolean
     */
    private boolean throwRandomCoin(float val) throws IllegalArgumentException{
        final int MAX_BOUND = 100;
        if (val < 0 || val > 1){
            throw new IllegalArgumentException("La probabilta è un valore compreso tra [0, 1]");
        }

        int limit = (int)(val*100);
        return generator.nextInt(MAX_BOUND) < limit;
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
        
        if(uses == 0){
            potionsActiveted.remove(t);
        }
        if(uses < 0){
            return 0;
        }

        ItemStack potion = factory.getItem(t);
        buffer += "["+format("MODIFICATORI", ANSI_PURPLE)+"]Stai usando pozione "+ potion.getName()+"\n";
        return (byte)potion.getValue();
    }

    /**
     * Metodo per attacare monster da player se il mostro muore freeRoom viene impostato a true
     * @param m Monster che subisce l'attacco
     */
    private short playerAttack(Monster m){
        short healthChange = (short)-(p.getAttack() - m.getArmor() + getPotionValue(ItemType.POZIONE_FORZA) + getPotionValue(ItemType.POZIONE_VELENO));
        if(healthChange < 0){
            m.changeHealth(healthChange);

            if(m.getLife() <= 0){
                level++;
                p.setLv(level);
                map.setFreeRoomAt(map.getPlayerPos());
                map.getChestAt(map.getPlayerPos()).unlock();
            }

            return healthChange;
        }
        return 0;
    }

    /**
     * Metodo per attaccare il player dal mostro se il player muore allora lose viene impostato a true
     * @param m Mostro che attacca
     */
    private short monsterAttack(Monster m){
        short healthChange = (short)-(m.getAttack() - p.getArmor() - getPotionValue(ItemType.POZIONE_RESISTENZA) - getPotionValue(ItemType.POZIONE_INVALIDITA));

        if(healthChange < 0){
            p.changeHealth(healthChange);
            if(p.getLife() < 0){
                lose = true;
                buffer += "Mi dispiace sei stato sconfitto da: "+m.getName()+" fai un nuovo tentativo!\n";
            }

            return healthChange;
        }

        return 0;
    }

    /**
     * Restituisce true se il player può attaccare
     * @return boolean
     */
    public boolean playerCanAttack(){
        if(!MapGraph.isStanza(map.getPlayerPos())){
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
        try{
            if(map.isValidDirectionTo(map.getPlayerPos(), d)){
                playerStack.add(map.getPlayerPos());
                map.movePlayerTo(d);
                buffer += "["+format("INFO", ANSI_CYAN)+"]"+p.getName()+" si è spostato in direzione " + d+"\n";
                if(MapGraph.isStanza(map.getPlayerPos())){
                    buffer += "["+format("INFO", ANSI_CYAN)+"]"+p.getName()+" sei entrato in una stanza\n";
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Sono un' eccezione inutile che non dovrebbe mia essere lanciata");
        }
    }

    /**
     * Restituisce true se il player può adare verso quella direzione
     * @param d direzione da controllare
     * @return Boolean true/false
     */
    public boolean canPlayerGo(Direction d){
        try{
            return map.isValidDirectionTo(map.getPlayerPos(), d);
        }
        catch(Exception e){
            return false;
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

    public boolean canPlayerTake(String item){
      
        if(!MapGraph.isStanza(map.getPlayerPos())){
            return false;
        }
        
        Chest c = map.getChestAt(map.getPlayerPos());
        if(c.isClosed()){
            buffer += "["+format("INFO", ANSI_CYAN)+"]La chest è chiusa\n";
            return false;
        }

        ItemStack it = c.searchFor(item);
        
        if(it == null){
            buffer+="["+format("INFO", ANSI_CYAN)+"]l'item "+item+" non esite nella chest\n";
            return false;
        }
        if(p.doesFillInv(it)){
            buffer += "["+format("INFO", ANSI_CYAN)+"]l'item "+item+" non può essere preso dal player perchè troppo pesante\n";
            return false;
        }
        if(p.hasAlreadyArmaOrArmatura(it)){
            buffer += "["+format("INFO", ANSI_CYAN)+"]l'item "+item+" non può essere preso dal player perchè è gia presente nel inventario\n";
            return false;
        }

        return true;
    }

    /**
     * rimuove l'item dalla chest e lo inserisce nel inventario del 
     * player se l'oggetto non esiste allora viene lanciata noItem_Exception
     * Se non è possibile inserire l'oggetto nel inventario di player allora viene 
     * scritto nel buffer la motivazione
     * @param item nome del item da prendere
     */
    public void playerTake(String item)throws noItem_Exception{
        boolean taked;
        if(!canPlayerTake(item)){
            throw new noItem_Exception();
        }
        
        Chest c = map.getChestAt(map.getPlayerPos());
        ItemStack it = c.searchFor(item);
        
        taked = p.addItem(it);
        if(taked){
            c.remove(it);
        }
        else{
            buffer += "["+format("INFO", ANSI_CYAN)+"]Non puoi prendere questo oggetto poichè nel inventario ne hai gia uno dello stesso tipo!\n";
        }
    }

    /**
     * Da usare solo per fare debugging: stampa le informazioni di player:
     * -attacco
     * -difesa
     * -vita
     * -maxvita
     */
    public void onlyForDebugging(){
        System.out.println(" armor:"+p.getArmor()+" attack:"+p.getAttack()+" life:"+p.getLife()+" max life"+p.getMaxLife());
    }

    /**
     * Restituisce true se player ha l'item
     * @return boolean
     */
    public boolean playerHave(String item){
        return p.getInv().searchFor(item) != null;
    }

    /**
     * Prende un item al interno del inventario e gli applica l'effetto, se item non
     * viene trovato viene lanciata noItem_Exception.
     * L'item viene tolto dal inventario automaticamente dopo l'uso
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
        if(p.getLife() < 0){
            throw new DeathException();
        }
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

        return map.getMonsterAt(map.getPlayerPos()).getMaxLife();
    }
    
    /**
     * Ottine la vita attuale del mostro. Lancia IllegalAccessError se non ci sono mostri nella 
     * posizione del player (ovvero se player può attaccare)
     * @return short vita attuale
     */
    public short getMonsterLife() throws IllegalAccessError{

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
     * Restituisce true se il player è entrato in una stanza
     * @return boolean
     */
    public boolean isPlayerInRoom(){
        return MapGraph.isStanza(map.getPlayerPos());
    }

    /**
     * Aggiunge al buffer la stringa str 
     * NB: va a capo in automatico dopo la fine di str
     * @param str
     */
    public void addBuffer(String str){
        buffer+=str+"\n";
    }

    /**
     * Carica un nuovo player con i settaggi passati
     * @param newPlayer
     */
    public void loadPlayer(Player newPlayer){
        p = newPlayer;
        p.initHandler();
        ArrayList<Monster> list = map.getAllMonster();
        for(Monster m: list){
            p.addObserver(m);
        }

        lose = false;
        buffer = "";
        potionsActiveted = new HashMap<ItemType, Byte>();
        playerStack = new Stack<Coordinates>();
        level = p.getLv();
    }

    /**
     * Resetta la partita ripristinando player e mappa alle condizioni iniziali
     * il nome del player non viene cambiato
     */
    public void reset(){
        String name = p.getName();
        runSetup(name);
        map = new MapGraph();

    }

    /**
     * Carica una nuova mappa con i settaggi passati
     * @param newMap
     */
    public void loadMap(MapGraph newMap){
        map = newMap;
    }

    /**
     * Restituisce il player per effettuare il salvataggio su cloud
     * @return Player
     */
    public Player getPlayer(){
        return p;
    }

    /**
     * Restituisce la mappa per effettuare il salvattaggio su cloud
     * @return MapGraph
     */
    public MapGraph getMap(){
        return map;
    }

    /**
     * Restituisce le cordinate di player
     * @return Coordinates
     */
    public Coordinates getPlayerCord(){
        return map.getPlayerPos();
    }

    /**
     * restituisce il peso massimo del player
     * @return byte peso
     */
    public byte getPlayerMaxWeight(){
        return p.getMaxWeight();
    }

    /**
     * Restituisce il peso del inventario di player
     * @return byte peso
     */
    public byte getPlayerWeight(){
        return p.getWeight();
    }
}