package server.thread;

/**
 *
 * @author helbert
 */
public enum ComandoEnum {

    NEW_ACESS(0),
    NEW_USER(1),
    EDIT_USER(2),
    LIST_USER(3),
    LIST_FILE(4);

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
