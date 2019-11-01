package server.thread;

/**
 *
 * @author helbert
 */
public enum ComandoEnum {

    NEW_ACESS(0),
    WRITE_FILE(1),
    READ_FILE(2),
    NEW_FILE(3),
    DELETE_FILE(4),
    RENAME_FILE(5),
    NEW_USER(6),
    EDIT_USER(7),
    LIST_USER(8),
    LIST_FILE(9);

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
