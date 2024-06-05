package com.ACMD.app.Kernel_Layer.Menu;


/**
 * Questa classe in realta è una struct che memorizza una voce del menu
 */
public class MenuValues {
    public String cmdName;                  //? se no pubbliche rome xmlReader
    public String cmdDescription;

    public MenuValues() //per compatibilità
     {}
    public MenuValues(String cmdName, String cmdDescription)
    {
        this.cmdName= cmdName;
        this.cmdDescription= cmdDescription;
    }

    @Override
    public int hashCode()
        {
            return cmdName.hashCode();    //i comandi devono essere unici, quindi nessun problema di conflitti
        }
        @Override
        public String toString()
        {
            return cmdName + "\n  " + cmdDescription; 
        }
}
