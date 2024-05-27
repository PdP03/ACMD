package com.ACMD.app.Engine_Layer.Entita;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document; 
import org.w3c.dom.NodeList;
import com.ACMD.app.Engine_Layer.ParsePath;
import org.w3c.dom.Node; 
import org.w3c.dom.Element;
import java.io.File;
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
                
                mValues = getValuesFrom((Element)attribute);
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
    private static MonsterValues getValuesFrom(Element eAttribute){
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
}
