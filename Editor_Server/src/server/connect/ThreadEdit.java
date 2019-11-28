package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import server.Arquivo;
import server.Usuario;
import server.model.UsuarioFileTableModel;

/**
 *
 * @author helbert
 */
public class ThreadEdit extends Connect implements Runnable {

    private final ServerSocket serv;
    private final UsuarioFileTableModel model;
    private final JLabel txtNum;
    private boolean online;

    public ThreadEdit(ServerSocket serv, UsuarioFileTableModel model, JLabel txtNum) {
        this.serv = serv;
        this.model = model;
        this.txtNum = txtNum;
        online = false;
    }

    @Override
    public void run() {
        Socket soc = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            HashMap<String, List<ServerFile>> list = new HashMap<>();

            String param;
            String[] campos;
            ArrayList<Arquivo> listFile;
            Arquivo file = null;
            Usuario u;
            while (true) {
//                if (online) {
                soc = serv.accept();
                for (Map.Entry<String, List<ServerFile>> elem : list.entrySet()) {
                    if (elem.getValue() != null && elem.getValue().isEmpty()) {
                        list.remove(elem.getKey());
                    }
                }

                out = new ObjectOutputStream(soc.getOutputStream());
                in = new ObjectInputStream(soc.getInputStream());
                param = in.readUTF();
                campos = param.split(SEP_CAMPOS);

                listFile = Arquivo.carregar_lista_arquivo(Integer.parseInt(campos[1]));

                for (Arquivo f : listFile) {
                    if (f.getNome().equals(campos[0])) {
                        file = f;
                        break;
                    }
                }

                if (!list.containsKey(campos[0])) {
                    list.put(campos[0], new ArrayList<>());
                }
                u = Usuario.get_usuario_pelo_codigo(Integer.parseInt(campos[1]));
                model.addUsuarioFile(u, file);
                txtNum.setText(String.valueOf(model.getRowCount()));

                ServerFile client = new ServerFile(in, out, list.get(campos[0]), model, txtNum, u, file);
                list.get(campos[0]).add(client);
                Thread t = new Thread(client);
                t.start();
//                }

            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (soc != null && out != null && in != null) {
                    soc.close();
                    out.close();
                    in.close();
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void close_application() {
        online = false;
    }

    public void init_application() {
        online = true;
    }

}
