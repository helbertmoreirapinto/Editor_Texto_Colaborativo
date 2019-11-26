package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import server.Usuario;

/**
 *
 * @author helbert
 */
public class Server extends Connect implements Runnable {

    private boolean online;
    private final ServerSocket server;
    private Socket socket;

    public Server(ServerSocket server) throws IOException {
        online = false;
        this.server = server;
    }

    @Override
    public void run() {
        String comando;
        String[] campos;
        Usuario user;
        StringBuilder ret;
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException ex) {
                System.err.println("socket error: " + ex.getMessage());
            }
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                comando = in.readUTF();
                campos = comando.split(SEPARADOR);
                switch (campos[0]) {
                    case COMANDO_LOGAR:
                        user = Usuario.logar(campos[1], campos[2]);
                        out.writeBoolean(user != null);
                        if (user != null) {
                            System.out.println(String.format("user_connect: [%d] %s", user.getCodigo(), user.getNome()));
                            ret = new StringBuilder();
                            ret.append(String.valueOf(user.getCodigo()));
                            ret.append(SEPARADOR);
                            ret.append(user.getNome());
                            ret.append(SEPARADOR);
                            ret.append(user.getLogin());
                            ret.append(SEPARADOR);
                            ret.append(user.getSenha());
                            ret.append(SEPARADOR);
                            ret.append(String.valueOf(user.isAdm()));
                            ret.append(SEPARADOR);
                            ret.append(String.valueOf(user.isAtivo()));
                            out.writeUTF(ret.toString());
                        }
                        out.flush();
                        break;
                    case COMANDO_USERLIST:
                        break;
                    case COMANDO_GETUSER:
                        break;
                    default:
                        break;
                }
            } catch (IOException ex) {
                System.err.println("Erro run: " + ex.getMessage());
            }
        }
    }

    public static void init_application() throws IOException {
        ServerSocket server_file = new ServerSocket(PORT_FILE);
        Server s_file = new Server(server_file);
        Thread t_file = new Thread(s_file);
        t_file.start();

        ServerSocket server_user = new ServerSocket(PORT_USER);
        Server s_user = new Server(server_user);
        Thread t_user = new Thread(s_user);
        t_user.start();
    }

    public void close_application() throws IOException {
        online = false;
    }

    public void close_application_file() {

    }

    public void close_application_user() {

    }

    public boolean isOnline() {
        return online;
    }

}
