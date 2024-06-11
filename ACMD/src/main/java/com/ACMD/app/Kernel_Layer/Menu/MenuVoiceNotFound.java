package com.ACMD.app.Kernel_Layer.Menu;

public class MenuVoiceNotFound extends IllegalArgumentException
    {
        MenuVoiceNotFound()
        { System.out.println("Non è stato trovata la voce in caricamento"); }
        MenuVoiceNotFound(String s)
        { System.out.println(s); }
    }
