
package KernelEnum;

public enum PromptReturn_Enum
{
    save_changes,               //per dire di chiedere se vuole salvare la partita
    exit,

    update,
    update_entitys_state,
    update_position,

    command_not_found,          //stampa segnale di errore a terminale
    enemy_room,
    floor
;
}
