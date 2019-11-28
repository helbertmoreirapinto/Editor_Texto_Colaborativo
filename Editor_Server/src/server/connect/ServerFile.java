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
                    if (this == sf) {
                        System.out.println(String.format("%s", msg));
                    } else {
                        sf.output.writeUTF(msg);
                        sf.output.flush();
                    }
                }
            } catch (IOException ex) {
                System.err.println("error: " + ex.getMessage());
                break;
            }
        }
        try {
            editFileList.remove(this);
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
}
