package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JLabel;
import server.Arquivo;
import server.Usuario;
import server.model.UsuarioFile;
import server.model.UsuarioFileTableModel;

/**
 *
 * @author helbert
 */
public class ServerFile extends Connect implements Runnable {

    private boolean status;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final List<ServerFile> editFileList;
    private final UsuarioFileTableModel model;
    private final JLabel txtNum;
    private final Usuario user;
    private final Arquivo file;

    /**
     *
     * @param input
     * @param output
     * @param editFileList
     * @param model
     * @param txtNum
     * @param user
     * @param file
     */
    public ServerFile(ObjectInputStream input, ObjectOutputStream output, List<ServerFile> editFileList, UsuarioFileTableModel model, JLabel txtNum, Usuario user, Arquivo file) {
        this.input = input;
        this.output = output;
        this.editFileList = editFileList;
        this.model = model;
        this.txtNum = txtNum;
        this.user = user;
        this.file = file;
        status = true;
    }

    @Override
    public void run() {
        String msg;
        while (status) {
            try {
                msg = input.readUTF();
                if (msg.equals(COMAND_EXIT)) {
                    status = false;
                    continue;
                }
                for (ServerFile sf : editFileList) {
                    if (this != sf) {
                        sf.output.writeUTF(msg);
                        sf.output.flush();
                    } else {
                        Arquivo.editar(file, msg);
                    }
                }
            } catch (IOException ex) {
                System.err.println("error: " + ex.getMessage());
                break;
            }
        }
        try {
            editFileList.remove(this);
            StringBuilder usuariosLogados = new StringBuilder();
            usuariosLogados.append(COMAND_USER_ONLINE)
                    .append(SEP_CAMPOS);

            for (ServerFile clients : editFileList) {
                usuariosLogados.append(clients.getUser().getNome())
                        .append(",");
            }
            usuariosLogados.deleteCharAt(usuariosLogados.length() - 1);
            for (ServerFile clients : editFileList) {
                clients.getOutput().writeUTF(usuariosLogados.toString());
                clients.getOutput().flush();
            }

            model.removeUsuarioFile(new UsuarioFile(file, user));
            txtNum.setText(String.valueOf(model.getRowCount()));
            this.input.close();
            this.output.close();
        } catch (IOException err) {
            System.err.println("error: " + err.getMessage());
        }
    }

    public void init() {

    }

    public void playServer() {
        status = true;
    }

    public void stopServer() {
        status = false;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public Usuario getUser() {
        return user;
    }
}
