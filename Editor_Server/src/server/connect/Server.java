package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String ret;
        
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
                        ret = logar(campos[1], campos[2]);
                        break;

                    case COMANDO_USERLIST:
                        ret = get_usuarioList();
                        break;

                    case COMANDO_GET_USER:
                        ret = get_usuario_pelo_codigo(Integer.parseInt(campos[1]));
                        break;

                    case COMANDO_FILELIST:
                        ret = get_fileList(Integer.parseInt(campos[1]));
                        break;

                    case COMANDO_SAVE_USER:
                        ret = get_save_user(campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
                        break;

                    case COMANDO_UPD_USER:
                        ret = get_update_user(Integer.parseInt(campos[1]), campos[2], campos[3], campos[4], Boolean.parseBoolean(campos[5]), Boolean.parseBoolean(campos[6]));
                        break;

                    default:
                        ret = "";
                        break;
                }
                out.writeBoolean(ret != null && !ret.trim().isEmpty());
                out.writeUTF(ret);
                out.flush();

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

    public boolean isOnline() {
        return online;
    }

    private String logar(String login, String senha) {
        Usuario user = Usuario.logar(login, senha);
        StringBuilder comando = new StringBuilder();
        if (user != null) {
            System.out.println(String.format("--> connect: [%d] %s", user.getCodigo(), user.getNome()));
            comando.append(String.valueOf(user.getCodigo())).append(SEP_CAMPOS);
            comando.append(user.getNome()).append(SEP_CAMPOS);
            comando.append(user.getLogin()).append(SEP_CAMPOS);
            comando.append(user.getSenha()).append(SEP_CAMPOS);
            comando.append(String.valueOf(user.isAdm())).append(SEP_CAMPOS);
            comando.append(String.valueOf(user.isAtivo()));
        }
        return comando.toString();
    }

    private String get_usuarioList() {
        StringBuilder comando = new StringBuilder();
        HashMap<Integer, Usuario> list = Usuario.carregar_lista_usuario();
        System.out.println(String.format("load_userList tam:%d", list.size()));
        for (Map.Entry<Integer, Usuario> elem : list.entrySet()) {
            comando.append(String.valueOf(elem.getValue().getCodigo())).append(SEP_CAMPOS);
            comando.append(elem.getValue().getNome()).append(SEP_CAMPOS);
            comando.append(elem.getValue().getLogin()).append(SEP_CAMPOS);
            comando.append(elem.getValue().getSenha()).append(SEP_CAMPOS);
            comando.append(String.valueOf(elem.getValue().isAdm())).append(SEP_CAMPOS);
            comando.append(String.valueOf(elem.getValue().isAtivo())).append(SEP_CAMPOS).append(SEP_REGS);
        }
        return comando.toString();
    }

    private String get_usuario_pelo_codigo(int codUser) {
        Usuario user = Usuario.get_usuario_pelo_codigo(codUser);
        StringBuilder comando = new StringBuilder();
        if (user != null) {
            System.out.println(String.format("user_found: [%d] %s", user.getCodigo(), user.getNome()));
            comando.append(String.valueOf(user.getCodigo())).append(SEP_CAMPOS);
            comando.append(user.getNome()).append(SEP_CAMPOS);
            comando.append(user.getLogin()).append(SEP_CAMPOS);
            comando.append(user.getSenha()).append(SEP_CAMPOS);
            comando.append(String.valueOf(user.isAdm())).append(SEP_CAMPOS);
            comando.append(String.valueOf(user.isAtivo()));
        }
        return comando.toString();
    }

    private String get_fileList(int codUser) {
        StringBuilder comando = new StringBuilder();
        String[] campos;
        List<Arquivo> fileList = Arquivo.carregar_lista_arquivo(codUser);
        System.out.println(String.format("cod_user: %d file_found: %d", codUser, fileList.size()));
        for (Arquivo file : fileList) {
            comando.append(file.getNome()).append(SEP_CAMPOS);
            comando.append(file.getCodigoAutor()).append(SEP_CAMPOS);
            for (Integer u : file.getCodigoUsuarioAcesso()) {
                comando.append(u).append(",");
            }
            comando.deleteCharAt(comando.length() - 1);
            comando.append(SEP_CAMPOS).append(SEP_REGS);
        }
        return comando.toString();
    }

    private String get_save_user(String nome, String login, String senha, boolean ativo, boolean adm) {
        StringBuilder comando = new StringBuilder();
        Usuario user = new Usuario(nome, login, senha, adm, ativo);
        Usuario.inserir_usuario(user);
        return comando.toString();
    }

    private String get_update_user(int codigo, String nome, String login, String senha, boolean ativo, boolean adm) {
        StringBuilder comando = new StringBuilder();

        return comando.toString();
    }

}
