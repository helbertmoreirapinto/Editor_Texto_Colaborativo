package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import server.Arquivo;
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
        List<Arquivo> fileList;
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException ex) {
                System.err.println("socket error: " + ex.getMessage());
            }
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                comando = in.readUTF();
                campos = comando.split(SEP_CAMPOS);
                switch (campos[0]) {
                    case COMANDO_LOGAR:
                        user = Usuario.logar(campos[1], campos[2]);
                        out.writeBoolean(user != null);
                        if (user != null) {
                            System.out.println(String.format("--> connect: [%d] %s", user.getCodigo(), user.getNome()));
                            ret = new StringBuilder();
                            ret.append(String.valueOf(user.getCodigo())).append(SEP_CAMPOS);
                            ret.append(user.getNome()).append(SEP_CAMPOS);
                            ret.append(user.getLogin()).append(SEP_CAMPOS);
                            ret.append(user.getSenha()).append(SEP_CAMPOS);
                            ret.append(String.valueOf(user.isAdm())).append(SEP_CAMPOS);
                            ret.append(String.valueOf(user.isAtivo()));
                            out.writeUTF(ret.toString());
                        }
                        out.flush();
                        break;
                    case COMANDO_USERLIST:
                        break;
                    case COMANDO_GETUSER:
                        user = Usuario.get_usuario_pelo_codigo(Integer.parseInt(campos[1]));
                        out.writeBoolean(user != null);
                        if (user != null) {
                            System.out.println(String.format("user_found: [%d] %s", user.getCodigo(), user.getNome()));
                            ret = new StringBuilder();
                            ret.append(String.valueOf(user.getCodigo())).append(SEP_CAMPOS);
                            ret.append(user.getNome()).append(SEP_CAMPOS);
                            ret.append(user.getLogin()).append(SEP_CAMPOS);
                            ret.append(user.getSenha()).append(SEP_CAMPOS);
                            ret.append(String.valueOf(user.isAdm())).append(SEP_CAMPOS);
                            ret.append(String.valueOf(user.isAtivo()));
                            out.writeUTF(ret.toString());
                        }
                        out.flush();
                        break;

                    case COMANDO_FILELIST:
                        fileList = Arquivo.carregar_lista_arquivo(Integer.parseInt(campos[1]));
                        out.writeBoolean(fileList != null && !fileList.isEmpty());
                        ret = new StringBuilder();
                        System.out.println(String.format("cod_user: %d file_found: %d", Integer.parseInt(campos[1]), fileList.size()));
                        for (Arquivo file : fileList) {
                            ret.append(file.getNome()).append(SEP_CAMPOS);
                            ret.append(file.getCodigoAutor()).append(SEP_CAMPOS);
                            for (Integer u : file.getCodigoUsuarioAcesso()) {
                                ret.append(u).append(",");
                            }
                            ret.deleteCharAt(ret.length() - 1);
                            ret.append(SEP_CAMPOS).append(SEP_REGS);
                        }
                        out.writeUTF(ret.toString());
                        out.flush();
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
