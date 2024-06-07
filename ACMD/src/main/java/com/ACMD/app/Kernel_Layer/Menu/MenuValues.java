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
        {       //System.out.println( cmdName.hashCode() );
            return cmdName.hashCode();    //i comandi devono essere unici, quindi nessun problema di conflitti
        }
        @Override
        public String toString()
        {
            return cmdName + "  " + cmdDescription; 
        }
        @Override
        public boolean equals(Object obj)
        {  // System.out.println("Verificato uguaglianza");
            MenuValues e = (MenuValues)obj;
            return this.cmdName==e.cmdName;// && cmdDescription==e.cmdDescription;
        }
}
