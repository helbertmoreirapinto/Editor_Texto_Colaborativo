package server.connect;

/**
 *
 * @author helbert
 */
public abstract class Connect {

    public static final int PORT_USUARIO = 6060;
    public static final int PORT_FILE = 6160;
    public static final int PORT_EDIT_FILE = 6260;

    protected final String SEP_CAMPOS = "!!";
    protected final String SEP_REGS = "!_!";

    protected final String COMAND_STATUS = "00";
    protected final String FILE_DUPLICADO = "01";

    protected final String COMAND_FILELIST = "11";
    protected final String COMAND_GET_FILE = "12";
    protected final String COMAND_NEW_FILE = "13";
    protected final String COMAND_REPLACE_FILE = "14";
    protected final String COMAND_FILE_DATA_UPD = "15";
    protected final String COMAND_RENAME_FILE = "16";

    protected final String COMANDO_LOGAR = "21";
    protected final String COMAND_USERLIST = "22";
    protected final String COMAND_GET_USER = "23";
    protected final String COMAND_SAVE_USER = "24";
    protected final String COMAND_UPD_USER = "25";

    protected final String COMAND_SEND_TEXT = "31";
    protected final String COMAND_GET = "32";
    protected final String COMAND_EXIT = "33";

    protected void delay(int delay) throws InterruptedException {
        Thread.sleep(delay);
    }
}
