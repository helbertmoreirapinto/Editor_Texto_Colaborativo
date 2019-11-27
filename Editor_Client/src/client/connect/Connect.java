package client.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author helbert
 */
public abstract class Connect {

    protected final String IP_SERVER = "127.0.0.1";
    protected static final int PORT_USUARIO = 6060;
    protected static final int PORT_FILE = 6160;
    protected static final int EDIT_FILE = 6260;

    protected final String SEP_CAMPOS = "!!";
    protected final String SEP_REGS = "!_!";

    protected final String COMANDO_STATUS = "00";

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

    protected void delay(int delay) throws InterruptedException {
        Thread.sleep(delay);
    }

    public boolean get_status_server() throws IOException, InterruptedException {
        boolean online;
        try (Socket socket = new Socket(IP_SERVER, PORT_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_STATUS);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            online = input.readBoolean();
        }
        return online;
    }

}