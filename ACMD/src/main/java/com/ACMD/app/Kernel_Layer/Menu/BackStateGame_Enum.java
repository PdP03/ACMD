
package com.ACMD.app.Kernel_Layer.Menu;

/*
    Tutte azioni che il prompt può dire al game
    -per esempio per aggiornare la grafica
*/

public enum BackStateGame_Enum
{
    START,          //inizia
    QUIT,           //stacca stacca ; ma prima chiedi se bisogna salvare
    COMBACT,        //attaccatto nemico
    MOVE,           //movimento in corridoio
    MOVE_INVALID,   //provato un'azione non valida
    UPDATE,         //aggiornamento generico
    UPDATE_STORAGE, //un inventario o cassa
    UPDATE_ENTITY,  //alle barre della vita
    UPDATE_MAP,     //cambio della stanza
    ERROR_DIGIT,    //se il comando inserito non esiste
    RESTART,        //riavvia il gioco nel caso player muore
    SAVE,           //effettuato qualcosa su LOAD o SALVATAGGIO
    LOAD,           //aggiornare il menù rispettivo
    YN              //booleano per le scelte sì-no come se vuole salvare
;
}
