package com.ACMD.app.Engine_Layer;

import com.ACMD.app.Engine_Layer.Entita.MType;

/**
 * Questa classe in realta è una struct serve solo a memorizzare i valori di una connessione nel grafo
 */
public class RoomValues {
    public MType mtype;
    public int PlayerX,PlayerY; //Player
    public int StanzaX,StanzaY; // Stanza
    public String path; 
    public boolean isRoom;

}
