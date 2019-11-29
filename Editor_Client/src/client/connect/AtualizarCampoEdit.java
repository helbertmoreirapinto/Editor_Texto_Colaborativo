package client.connect;

import client.model.ListUsuarioModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JTextArea;

/**
 *
 * @author helbert
 */
public class AtualizarCampoEdit extends Connect implements Runnable {

    private final JTextArea campo;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final ListUsuarioModel model;

    public AtualizarCampoEdit(JTextArea campo, ObjectInputStream input, ObjectOutputStream output, ListUsuarioModel model) {
        this.campo = campo;
        this.input = input;
        this.output = output;
        this.model = model;
    }

    @Override
    public void run() {
        String msg;
        String[] nomeUsers;
        while (!Thread.interrupted()) {
            try {
                msg = input.readUTF();
                if (msg.startsWith(COMAND_USER_ONLINE)) {
                    try {
                        nomeUsers = msg.substring(msg.indexOf(SEP_CAMPOS) + SEP_CAMPOS.length()).split(",");
                        if (model.getSize() > 0) {
                            model.limpar();
                        }
                        for (String nn : nomeUsers) {
                            model.addElem(nn);
                        }
                    } catch (Exception ex) {
                        System.err.println("Falha atualizar users online: " + ex.getMessage());
                    }
                } else {
                    campo.setText(msg);
                }
            } catch (IOException ex) {
                try {
                    output.writeUTF(COMAND_EXIT);
                    output.flush();

                    output.close();
                    input.close();
                    
                    System.err.println("Thread atualizar texto " + ex.getMessage());
                    
                } catch (IOException ex1) {
                }
            }
        }
    }

}
