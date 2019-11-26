package client.connect;

/**
 *
 * @author helbert
 */
public abstract class Connect {

    protected final String IP_SERVER = "127.0.0.1";
    protected final int PORT_SERVER_USUARIO = 3130;
    protected final int PORT_SERVER_FILE = 3131;
    protected final int EDIT_FILE = 3132;

    protected final String SEPARADOR = "##";
    protected final String COMANDO_LOGAR = "01";
    protected final String COMANDO_USERLIST = "02";
    protected final String COMANDO_FILELIST = "03";
    protected final String COMANDO_GETUSER = "04";
    protected final String COMANDO_GETFILE = "05";

    protected void delay(int delay) throws InterruptedException {
        Thread.sleep(delay);
    }

}
