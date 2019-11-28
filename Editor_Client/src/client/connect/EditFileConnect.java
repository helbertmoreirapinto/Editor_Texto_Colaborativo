package client.connect;

import client.Arquivo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author helbert
 */
public class EditFileConnect extends Connect {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public EditFileConnect(int codigo, Arquivo arquivo, JTextArea campo) {
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
                System.out.println("Connect in server");
            }

            AtualizarCampoEdit att = new AtualizarCampoEdit(campo, input);
            Thread t = new Thread(att);
            t.start();
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

    public String get_text(String nomeFile) {
        String texto = "";
        try (Socket s = new Socket(IP_SERVER, PORT_FILE); ObjectOutputStream o = new ObjectOutputStream(s.getOutputStream()); ObjectInputStream i = new ObjectInputStream(s.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_GET_TEXT).append(SEP_CAMPOS).append(nomeFile);
            o.writeUTF(comando.toString());
            o.flush();

            delay(50);

            if (i.readBoolean()) {
                texto = i.readUTF();
            }
        } catch (InterruptedException | IOException ex) {
            System.err.println(ex.getMessage());
        }
        return texto;
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
