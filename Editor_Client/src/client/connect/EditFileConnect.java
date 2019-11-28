package client.connect;

import client.Arquivo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helbert
 */
public class EditFileConnect extends Connect {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public EditFileConnect(int codigo, Arquivo arquivo) {
        try {
            StringBuilder comando = new StringBuilder();
            comando.append(arquivo.getNome()).append(SEP_CAMPOS)
                    .append(codigo);
            socket = new Socket(IP_SERVER, PORT_EDIT_FILE);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF(comando.toString());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            delay(50);
            if (input.readBoolean()) {
                System.out.println("Deu bom");
            }
        } catch (InterruptedException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void send_text(String text) {
        try {
            StringBuilder comando = new StringBuilder();
            comando.append(text);
            output.writeUTF(comando.toString());
            output.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String get_text() {
        return "";
    }

    public void exit() {
        try {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_EXIT);
            output.writeUTF(comando.toString());
            output.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
