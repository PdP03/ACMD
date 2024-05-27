package com.ACMD.app.Engine_Layer;
/**
 * Questa è una classe di utilità, data una path con root la directory ACMD
 * restituisce una stringa formattata per il specifico SO, generalmente la root è 
 * /usr/.../ACMD (per Linux) oppure C:\....\ACMD (per Windows).
 * IMPORTANTE il percorso passato va aggiunto alla directory di root cioè viene fatto
 * rootDir + packagePath + fileName.
 */
public class ParsePath {
    /**
     * Metodo che fa la conversione di path
     * @param filePath path del file da aggiungere alla root (es. \\ACMD\\src\\main\\java\\com\\ACMD\\app\\Engine_Layer)
     * @return String fullPath formattata secondo il tipo di SO
     */
    public static String getPath(String packagePath, String fileName){
        //directory da cui viene lanciata l'applicazione
        String rootDir = System.getProperty("user.dir");

        /*
        *                          ------------------------------ IMPORTANTE ------------------------------
        * Queste righe sono neccessarie poichè ParsePath viene richiamato da un file di test la directory di root è C:\....\ACMD\ACMD (Windows) 
        * o usr/..../ACMD/ACMD (Linux) mentre se viene richiamato da una classe nel main la root è  C:\...\ACMD (Windows) usr/..../ACMD (Linux)
        * in questo modo il file viene sempre trovato
        */
        String[] splitted = rootDir.split("ACMD");
        rootDir = splitted[0]+"ACMD";

        String fileDir = rootDir+packagePath+fileName;
        //check sul SO in uso poichè Unix usa / come separatore mentre windws \
        String SOtype = System.getProperty("os.name");
        if(SOtype.contains("Linux")){
            fileDir = fileDir.replaceAll("\\\\", "\\/");
        }
        else if(SOtype.contains("Windows")){
            fileDir = fileDir.replaceAll("\\/","\\\\");
        }

        return fileDir;
    }
    
}
