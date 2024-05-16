
package StorageManagement;

public class InventoryOutOfBound_Exception extends RuntimeException
{
    public InventoryOutOfBound_Exception()
     {System.out.println("Inventario è pieno");}

    public InventoryOutOfBound_Exception(String s)
     {System.out.println(s);}
}
