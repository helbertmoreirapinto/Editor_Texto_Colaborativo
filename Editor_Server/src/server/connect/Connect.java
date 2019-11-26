package server.connect;

/**
 *
 * @author helbert
 */
public abstract class Connect {

    protected static final int PORT_USER = 3130;
    protected static final int PORT_FILE = 3131;
    protected static final int PORT_EDIT_FILE = 3132;

    protected final String SEP_CAMPOS = "!!";
    protected final String SEP_REGS = "!_!";
    protected final String COMANDO_LOGAR = "01";
    protected final String COMANDO_USERLIST = "02";
    protected final String COMANDO_FILELIST = "03";
    protected final String COMANDO_GET_USER = "04";
    protected final String COMANDO_GET_FILE = "05";
    protected final String COMANDO_SAVE_USER = "06";
    protected final String COMANDO_UPD_USER = "07";

    protected void delay(int delay) throws InterruptedException {
        Thread.sleep(delay);
    }
}
