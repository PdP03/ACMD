
// #FARE : bisogna trovare il modo che se questa eccezione viene lanciata, il programma và in crash seduta stante, senza possibilità di raccoglierla

package com.ACMD.app.Kernel_Layer.Prompt;

public class NoCommandExistException extends RuntimeException
{
    public NoCommandExistException()
    {
        System.out.println("Nel controllo della sintassi è passato un comando che non è stato però trovato un corrispondente funzione : verificare che tutti i comandi siano stati implementati o di aver rimosso dai file gli alias del comando non trovato");
        System.exit(1);
    }

    public NoCommandExistException(String err)
    {
        System.out.println(err);
        System.exit(1);
    }
}
