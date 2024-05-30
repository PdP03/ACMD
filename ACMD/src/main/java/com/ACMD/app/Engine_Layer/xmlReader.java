package com.ACMD.app.Engine_Layer;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document; 
import org.w3c.dom.NodeList;

import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.Mappa.NODE;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemStack;
import com.ACMD.app.Engine_Layer.StorageManagement.ItemType;

import org.w3c.dom.Node; 
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

public class xmlReader{
    Document docXml;

    /**
     * Inizializza il reader caricando il file 
     * @param fileName nome del file (con estensione .xml) da caricare
     */
    public xmlReader(String entityDir, String fileName){
        String fileDir = ParsePath.getPath(entityDir, fileName);

        //Creazione del oggetto che rappresenta il file (non interagisce con il sistema operativo)
        File xml = new File(fileDir);

        //inizializzazione del parser e lettura del file xml
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            docXml = builder.parse(xml);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Errore nella lettura del file xml");
        }
    }

    /**
     * Metodo che legge il file di configurazione e restituisce un vector con i valori di default dei vari mostri
     * @return defaultValues un Vector<MonsterValues> contenente tutti i valori
     */
    public Vector<MonsterValues> getMonsterValues(){
        Vector<MonsterValues> defaultValues = new Vector<MonsterValues>();
        MonsterValues mValues = new MonsterValues();
        MType type;
        
        NodeList monsterAttribute = docXml.getElementsByTagName("monster").item(0).getChildNodes(); //lista dei nodi mostri (mostro marino, armatura...)
        defaultValues.setSize((monsterAttribute.getLength()-1)/2);

        Node attribute;
        for(int j = 0; j < monsterAttribute.getLength(); j++){
            attribute = monsterAttribute.item(j);

            if(attribute.getNodeType() == Node.ELEMENT_NODE){
                type = MType.valueOf(attribute.getNodeName());
                
                mValues = getMonsterValuesFrom((Element)attribute);
                defaultValues.set(type.getId(), mValues);
            }
        }

        return defaultValues;
    }

    /**
     * Legge i valori dei mostri da un nodo di tipo element 
     * @param eAttribute nodo in cui sono specificati i valori da leggere
     * @return mValues struct contenente i valori
     */
    private static MonsterValues getMonsterValuesFrom(Element eAttribute){
        MonsterValues mValues = new MonsterValues();
        Node n;

        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        n = eAttribute.getElementsByTagName("health_multiplier").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <health_multiplier>");
        mValues.healthMul = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("damage_multiplier").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <damage_multiplier>");
        mValues.damageMul = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("armor_multiplier").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <armor_multiplier>");
        mValues.armorMul = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("base_level").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <base_level>");
        mValues.level = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("base_health").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <base_health>");
        mValues.health = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("base_armor").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <base_armor>");
        mValues.armor = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("base_damage").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <base_damage>");
        mValues.damage = Byte.parseByte(n.getTextContent());

        return mValues;
    }


    /**
     * Metodo di utilita usato per lanciare un eccezione se n == null
     * Definizione dei colori usando le secquenze di escape ANSI vedi:
     * @see https://en.wikipedia.org/wiki/ANSI_escape_code
     * @see https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     * @param n Node da controllare rappresenta il singolo tag
     * @param str String da stampare nel errore
     * @throws IllegalArgumentException
     */
    public static final String ANSI_RESET = "\u001B[0m";    //resetta il colore
    public static final String ANSI_RED = "\u001B[31m";     //setta il colore a rosso

    private static void throwExeptionIfNull(Node n, String str) throws IllegalArgumentException{
        if(n == null)
            throw new IllegalArgumentException(ANSI_RED+str+ANSI_RESET);
    }

    /* --------------------------------------------------------------------------------------------------------------
     * |                                                                                                            |
     * |                                         METODI PER ITEM_STACK                                              |
     * |                                                                                                            |
     * --------------------------------------------------------------------------------------------------------------
     */

     /**
     * Metodo che legge il file di configurazione e restituisce un vector con i valori di default dei vari item
     * @return defaultValues un Vector<ItemStack> contenente tutti gli item
     */
    public Vector<ItemStack> getAllItemStack(){
        Vector<ItemStack> defaultValues = new Vector<ItemStack>();
        ItemStack item;
        ItemValues Ivalues = new ItemValues();

        NodeList itemAttribute = docXml.getElementsByTagName("itemstack").item(0).getChildNodes(); //lista dei nodi item (coltello, spada...)

        Node attribute;
        for(int j = 0; j < itemAttribute.getLength(); j++){
            attribute = itemAttribute.item(j);
            if(attribute.getNodeType() == Node.ELEMENT_NODE){
                Ivalues = getItemValuesFrom((Element)attribute);
                
                item = new ItemStack(attribute.getNodeName(), Ivalues.type, Ivalues.weight, Ivalues.quantity, Ivalues.value, Ivalues.description);
                defaultValues.add(item);
            }
        }

        return defaultValues;
    }


    /**
     * Legge i valori dei item da un nodo di tipo element 
     * @param eAttribute nodo in cui sono specificati i valori da leggere
     * @return ItemValues struct contenente i valori
     */
    private static ItemValues getItemValuesFrom(Element eAttribute){
        ItemValues iValues = new ItemValues();
        Node n;

        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        n = eAttribute.getElementsByTagName("description").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <description>");
        iValues.description = n.getTextContent();
        n = eAttribute.getElementsByTagName("type").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <type>");
        iValues.type = ItemType.valueOf(n.getTextContent());
        n = eAttribute.getElementsByTagName("weight").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <weight>");
        iValues.weight = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("quantity").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <quantity>");
        iValues.quantity = Byte.parseByte(n.getTextContent());
        n = eAttribute.getElementsByTagName("value").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <value>");
        iValues.value = Byte.parseByte(n.getTextContent());

        return iValues;
    }

    /* --------------------------------------------------------------------------------------------------------------
     * |                                                                                                            |
     * |                                         METODI PER MAPPA                                                   |
     * |                                                                                                            |
     * --------------------------------------------------------------------------------------------------------------
     */


    /**
     * Metodo che legge il file di configurazione e restituisce una lista con i nodi della mappa
     * @return defaultValues un ArrayList<NODE> contenente tutti i nodi
     */
    public ArrayList<NODE> getAllNode(){
        ArrayList<NODE> defaultValues = new ArrayList<NODE>();
        NodeList itemAttribute = docXml.getElementsByTagName("mappa").item(0).getChildNodes(); //lista dei nodi position
        
        Node attribute;
        for(int j = 0; j < itemAttribute.getLength(); j++){
            attribute = itemAttribute.item(j);
            if(attribute.getNodeType() == Node.ELEMENT_NODE && attribute.getNodeName() == "node"){
                defaultValues.add(getNODEFrom((Element) attribute));
            }
        }
        
        
        return defaultValues;
    }

    /**
     * Legge i valori di un NODO da un nodo di tipo element 
     * @param eAttribute nodo in cui sono specificati i valori da leggere
     * @return NODE nodo inizializzato
     */
    private static NODE getNODEFrom(Element eAttribute){
        NODE node;
        Node n;
       
        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        //TAG POSITION
        n = eAttribute.getElementsByTagName("position").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <position>");
        Coordinates c = getCord(n.getTextContent());
        node = new NODE(c.getX(), c.getY());

        //TAG NORD
        n = eAttribute.getElementsByTagName("nord").item(0);
        if(n != null){
            node.setNorth(getCord(n.getTextContent()));
        }
        else{
            node.setNorth(null);
        }

        //TAG SUD
        n = eAttribute.getElementsByTagName("sud").item(0);
        if(n != null){
            node.setSouth(getCord(n.getTextContent()));
        }
        else{
            node.setSouth(null);
        }

        //TAG EST
        n = eAttribute.getElementsByTagName("est").item(0);
        if(n != null){
            node.setEast(getCord(n.getTextContent()));
        }
        else{
            node.setEast(null);
        }

        //TAG OVEST
        n = eAttribute.getElementsByTagName("ovest").item(0);
        if(n != null){
            node.setWest(getCord(n.getTextContent()));
        }
        else{
            node.setWest(null);
        }

        n = eAttribute.getElementsByTagName("path_image").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <path_image>");
        node.setPathImage(n.getTextContent());
        n = eAttribute.getElementsByTagName("isRoom").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <isRoom>");
        node.setIsRoom(Boolean.parseBoolean(n.getTextContent()));
        
        return node;
    }

    /**
     * ottiene le cordinate x,y da una stringa con il formato: "x,y" (es. "1,2")
     * @param str
     */
    private static Coordinates getCord(String str){
        String[] splitted;

        splitted = str.split(",");
        return new Coordinates(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
    }

    /**
     * Metodo che legge il file di configurazione e restituisce una lista con i valori delle stanze
     * @return defaultValues un ArrayList<RoomValues> contenente tutti i valori delle stanze
     */
    public ArrayList<RoomValues> getAllRoom(){
        ArrayList<RoomValues> defaultValues = new ArrayList<RoomValues>();

        NodeList itemAttribute = docXml.getElementsByTagName("mappa").item(0).getChildNodes(); //lista dei nodi position
        
        Node attribute;
        for(int j = 0; j < itemAttribute.getLength(); j++){
            attribute = itemAttribute.item(j);
            if(attribute.getNodeType() == Node.ELEMENT_NODE && attribute.getNodeName() == "room"){
                defaultValues.add(getRoomValuesFrom((Element) attribute));
            }
        }

        return defaultValues;
    }

    /**
     * Legge i valori di una stanza da un nodo di tipo element 
     * @param eAttribute nodo in cui sono specificati i valori da leggere
     * @return RoomValues struct contenente i valori
     */
    private static RoomValues getRoomValuesFrom(Element eAttribute){
        RoomValues rValues = new RoomValues();
        Node n;

        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        n = eAttribute.getElementsByTagName("mtype").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <mtype>");
        rValues.mtype = MType.valueOf(n.getTextContent());
        n = eAttribute.getElementsByTagName("positionPlayer").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <positionPlayer>");
        Coordinates c = getCord(n.getTextContent());
        rValues.PlayerX = c.getX();
        rValues.PlayerY = c.getY();
        n = eAttribute.getElementsByTagName("position").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <position>");
        Coordinates c2 = getCord(n.getTextContent());
        rValues.StanzaX = c2.getX();
        rValues.StanzaY = c2.getY();
        n = eAttribute.getElementsByTagName("path_image").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <path_image>");
        rValues.path =n.getTextContent();
        n = eAttribute.getElementsByTagName("isRoom").item(0);
        throwExeptionIfNull(n, "[FATAL] Manca il tag <isRoom>");
        rValues.isRoom=Boolean.parseBoolean(n.getTextContent());
        return rValues;
    }

}
