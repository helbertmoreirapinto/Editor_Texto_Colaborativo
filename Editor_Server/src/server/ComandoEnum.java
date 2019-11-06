package server;

/**
 * ENUM ComandoEnum. POssui os comandos que os clientes podem fazer ao servidor.
 *
 * @author helbert
 */
public enum ComandoEnum {

    NEW_ACESS(0),
    NEW_USER(1),
    EDIT_USER(2),
    LIST_USER(3),
    LIST_FILE(4),
    UPDATE_FILE_DATA(5),
    RENAME_FILE(6),
    NEW_FILE(7),
    REPLACE_FILE(8),
    READ_FILE(9),
    WRITE_FILE(10);

    private final int codigo;

    private ComandoEnum(int codigo) {
        this.codigo = codigo;
    }

    public static ComandoEnum getComandoPeloCodigo(int cod) {
        for (ComandoEnum e : ComandoEnum.values()) {
            if (e.getCodigo() == cod) {
                return e;
            }
        }
        return null;
    }

    public int getCodigo() {
        return codigo;
    }

}
