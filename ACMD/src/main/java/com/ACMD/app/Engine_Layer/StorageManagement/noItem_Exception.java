
package com.ACMD.app.Engine_Layer.StorageManagement;

public class noItem_Exception extends RuntimeException
{
   public noItem_Exception()
     { System.out.println("Non ci sono oggetti di questo tipo"); }

    public noItem_Exception(String s)
     { System.out.println(s); }
}
