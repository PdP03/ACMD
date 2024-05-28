package com.ACMD.app.Engine_Layer.Entita;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document; 
import org.w3c.dom.NodeList;
import com.ACMD.app.Engine_Layer.ParsePath;
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
    final String entityDir = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer\\Entita\\";

    /**
     * Inizializza il reader caricando il file 
     * @param fileName nome del file (con estensione .xml) da caricare
     */
    public xmlReader(String fileName){
        String fileDir = ParsePath.getPath(entityDir, fileName);

        //Creazione del oggetto che rappresenta il file (non interagisce con il sistema operativo)
        File xml = new File(fileDir);

        //inizializzazione del parser e lettura del file xml
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            docXml = builder.parse(xml);
        }
        catch(Exception e){
            System.err.println("errore nella lettura");
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
                type = getMTypeBy(attribute.getNodeName());
                if(type == null){
                    return null;
                }
                
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

        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        mValues.healthMul = Byte.parseByte(eAttribute.getElementsByTagName("health_multiplier").item(0).getTextContent());
        mValues.damageMul = Byte.parseByte(eAttribute.getElementsByTagName("damage_multiplier").item(0).getTextContent());
        mValues.armorMul = Byte.parseByte(eAttribute.getElementsByTagName("armor_multiplier").item(0).getTextContent());
        mValues.level = Byte.parseByte(eAttribute.getElementsByTagName("base_level").item(0).getTextContent());
        mValues.health = Byte.parseByte(eAttribute.getElementsByTagName("base_health").item(0).getTextContent());
        mValues.armor = Byte.parseByte(eAttribute.getElementsByTagName("base_armor").item(0).getTextContent());
        mValues.damage = Byte.parseByte(eAttribute.getElementsByTagName("base_damage").item(0).getTextContent());

        return mValues;
    }

    /**
     * Metodo di supporto per tradurre un nome di un mostro nel rispettivo tipo. Se non ci sono
     * corrispondenze alla ritorna null
     * @param name nome da controllare
     * @return MType
     */
    private static MType getMTypeBy(String name){
        //IMPORTANTE nei case il nome deve corrispondere a quello del file xml
        switch(name){
            case "mago_nero":
                return MType.MAGO_OSCURO;
            case "armatura":
                return MType.ARMATURA;
            case "coboldo":
                return MType.COBOLDO;
            case "boss_drago":
                return MType.BOSS_DRAGO;
            case "mostro_marino":
                return MType.MOSTRO_MARINO;
            default:
                return null;
        }
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
                System.out.println(Ivalues.description);
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

        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        iValues.description = eAttribute.getElementsByTagName("description").item(0).getTextContent();
        iValues.type = getITypeBy(eAttribute.getElementsByTagName("type").item(0).getTextContent());
        iValues.weight = Byte.parseByte(eAttribute.getElementsByTagName("weight").item(0).getTextContent());
        iValues.quantity = Byte.parseByte(eAttribute.getElementsByTagName("quantity").item(0).getTextContent());
        iValues.value = Byte.parseByte(eAttribute.getElementsByTagName("value").item(0).getTextContent());

        return iValues;
    }

    /**
     * Metodo di supporto per tradurre il tipo(Stringa) di un item nel rispettivo tipo(ItemType). Se non ci sono
     * corrispondenze alla ritorna null
     * @param name nome da controllare
     * @return ItemType
     */
    private static ItemType getITypeBy(String tipo){
        //IMPORTANTE nei case il nome deve corrispondere a quello del file xml
        switch(tipo){
            case "arma":
                return ItemType.ARMA;
            case "armatura":
                return ItemType.ARMATURA;
            case "cibo":
                return ItemType.CIBO;
            case "easter_egg":
                return ItemType.EASTER_EGG;
            case "pozione_cura":
                return ItemType.POZIONE_CURA;
            case "pozione_danno":
                return ItemType.POZIONE_DANNO;
            case "pozione_forza":
                return ItemType.POZIONE_FORZA;
            case "pozione_invalidita":
                return ItemType.POZIONE_INVALIDITA;
            case "pozione_resistenza":
                return ItemType.POZIONE_RESISTENZA;
            case "pozione_veleno":
                return ItemType.POZIONE_VELENO;
            default:
                return null;
        }
    }

    /* --------------------------------------------------------------------------------------------------------------
     * |                                                                                                            |
     * |                                         METODI PER MAPPA                                                   |
     * |                                                                                                            |
     * --------------------------------------------------------------------------------------------------------------
     */


    /**
     * Metodo che legge il file di configurazione e restituisce una lista con i nodi della mappa
     * @return defaultValues un ArrayList<Node> contenente tutti i nodi
     */
    public ArrayList<NODE> getAllNode(){
        ArrayList<NODE> defaultValues = new ArrayList<NODE>();

        NodeList cordList;
        NodeList itemAttribute = docXml.getElementsByTagName("mappa").item(0).getChildNodes(); //lista dei nodi position
        
        Node attribute;
        Node val;
        for(int j = 0; j < itemAttribute.getLength(); j++){
            attribute = itemAttribute.item(j);
            
            if(attribute.getNodeName() == "node"){
                
                cordList = attribute.getChildNodes();
                
                for(int i = 0; i < cordList.getLength(); i++)
                {
                    val = cordList.item(i);
                    if(val.getNodeType() == Node.ELEMENT_NODE){
                        defaultValues.add(getPosition((Element)val));
                    }
                }
            }
        }
        return defaultValues;
    }

    /**
     * Legge le cordinate (x, y) da un nodo element (<position>)
     * @param node nodo in cui sono specificati i valori da leggere
     * @return NODE posizione inizializzata
     */
    private static NODE getPosition(Node node){
        String str;
        String[] splitted;
        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        str = node.getTextContent();
        splitted = str.split(",");

        return new NODE(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
    }

    /**
     * Metodo che legge il file di configurazione e restituisce una lista con i nodi della mappa
     * @return defaultValues un ArrayList<Node> contenente tutti i nodi
     */
    public ArrayList<ConnectionValues> getAllConnection(){
        ArrayList<ConnectionValues> defaultValues = new ArrayList<ConnectionValues>();

        NodeList itemAttribute = docXml.getElementsByTagName("mappa").item(0).getChildNodes(); //lista dei nodi position

        Node attribute;
        for(int j = 0; j < itemAttribute.getLength(); j++){
            attribute = itemAttribute.item(j);
            if(attribute.getNodeName() == "connessione"){
                defaultValues.add(getConnectionValuesFrom((Element) attribute));
            
            }
        }

        return defaultValues;
    }

    /**
     * Legge i valori di una connessione da un nodo di tipo element 
     * @param eAttribute nodo in cui sono specificati i valori da leggere
     * @return ConnectionValues struct contenente i valori
     */
    private static ConnectionValues getConnectionValuesFrom(Element eAttribute){
        ConnectionValues cValues = new ConnectionValues();
        String str;
        String splitted[];

        // ---------------- LETTURA PARAMETRI DA FILE IN BASE AL TAG ----------------
        str = eAttribute.getElementsByTagName("position_start").item(0).getTextContent();
        splitted = str.split(",");
        cValues.x = Integer.parseInt(splitted[0]);
        cValues.y = Integer.parseInt(splitted[1]); 

        str = eAttribute.getElementsByTagName("position_end").item(0).getTextContent();
        splitted = str.split(",");
        cValues.x2 = Integer.parseInt(splitted[0]);
        cValues.y2 = Integer.parseInt(splitted[1]); 
        cValues.direction1 = getDirectionBy(eAttribute.getElementsByTagName("direction1").item(0).getTextContent());
        cValues.direction2 = getDirectionBy(eAttribute.getElementsByTagName("direction2").item(0).getTextContent());

        return cValues;
    }

    /**
     * Metodo di supporto per tradurre la direction(Stringa) in Direction(enum) . Se non ci sono
     * corrispondenze alla ritorna null
     * @param dir nome da controllare
     * @return ItemType
     */
    private static Direction getDirectionBy(String dir){
        //IMPORTANTE nei case il nome deve corrispondere a quello del file xml
        switch(dir){
            case "est":
                return Direction.EAST;
            case "ovest":
                return Direction.WEST;
            case "sud":
                return Direction.SOUTH;
            case "nord":
                return Direction.NORTH;
            default:
                return null;
        }
    }

}
