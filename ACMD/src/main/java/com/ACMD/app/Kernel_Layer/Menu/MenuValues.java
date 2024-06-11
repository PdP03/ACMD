package com.ACMD.app.Kernel_Layer.Menu;


/**
 * Questa classe in realta è una struct che memorizza una voce del menu
 */
public class MenuValues {
    public String cmdName;
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
     { return cmdName.hashCode(); }
    @Override
    public String toString()
     { return cmdName + "  " + cmdDescription; }
    @Override
    public boolean equals(Object obj)
    {
        MenuValues e = (MenuValues)obj;
        return this.cmdName.equals(e.cmdName);
    }
}
