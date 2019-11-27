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
import server.Arquivo;
import server.Usuario;
import server.exc.ArquivoDuplicadoException;

/**
 *
 * @author helbert
 */
public class Server extends Connect implements Runnable {

    private boolean status;
    private final ServerSocket server;
    private Socket socket;

    public Server(ServerSocket server) throws IOException {
        status = false;
        this.server = server;
    }

    @Override
    public void run() {
        String comando;
        String[] campos;
        String ret;

        while (true) {
//            if (status) {
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

                        case COMAND_USERLIST:
                            ret = get_usuarioList();
                            break;

                        case COMAND_GET_USER:
                            ret = get_usuario_pelo_codigo(Integer.parseInt(campos[1]));
                            break;

                        case COMAND_FILELIST:
                            ret = get_fileList(Integer.parseInt(campos[1]));
                            break;

                        case COMAND_SAVE_USER:
                            ret = get_save_user(campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
                            break;

                        case COMAND_UPD_USER:
                            ret = get_update_user(Integer.parseInt(campos[1]), campos[2], campos[3], campos[4], Boolean.parseBoolean(campos[5]), Boolean.parseBoolean(campos[6]));
                            break;
                        case COMAND_NEW_FILE:
                            ret = create_file(campos[1], Integer.parseInt(campos[2]), campos[3]);
                            break;

                        case COMAND_RENAME_FILE:
                            ret = rename_file(campos[1], Integer.parseInt(campos[2]), campos[3], campos[4]);
                            break;

                        case COMAND_REPLACE_FILE:
                            ret = replace_file(campos[1], Integer.parseInt(campos[2]), campos[3]);
                            break;

                        case COMAND_FILE_DATA_UPD:
                            ret = update_file_data(campos[1], Integer.parseInt(campos[2]), campos[3]);
                            break;

                        case COMANDO_STATUS:
                            ret = get_status_server();
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

//        }
    }
    
    
    public void init_application() throws IOException {
        status = true;
    }

    public void close_application() throws IOException {
        status = false;
    }

    public boolean isOnline() {
        return status;
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
        comando.append(String.valueOf(user.getCodigo())).append(SEP_CAMPOS)
                .append(user.getNome()).append(SEP_CAMPOS)
                .append(user.getLogin()).append(SEP_CAMPOS)
                .append(user.getSenha()).append(SEP_CAMPOS)
                .append(String.valueOf(user.isAdm())).append(SEP_CAMPOS)
                .append(String.valueOf(user.isAtivo()));
        return comando.toString();
    }

    private String get_update_user(int codigo, String nome, String login, String senha, boolean ativo, boolean adm) {
        StringBuilder comando = new StringBuilder();
        Usuario user = Usuario.get_usuario_pelo_codigo(codigo);
        user.setAdm(adm);
        user.setAtivo(ativo);
        user.setLogin(login);
        user.setNome(nome);
        user.setSenha(senha);
        Usuario.alterar_usuario(user);
        comando.append(String.valueOf(user.getCodigo())).append(SEP_CAMPOS)
                .append(user.getNome()).append(SEP_CAMPOS)
                .append(user.getLogin()).append(SEP_CAMPOS)
                .append(user.getSenha()).append(SEP_CAMPOS)
                .append(String.valueOf(user.isAdm())).append(SEP_CAMPOS)
                .append(String.valueOf(user.isAtivo()));

        return comando.toString();
    }

    private String get_status_server() {
        return (status) ? "online" : "offline";
    }

    private String create_file(String nomeFile, int codAutor, String codUsers) {
        StringBuilder comando = new StringBuilder();
        try {
            String[] codUsersVet = codUsers.split(",");
            List<Integer> codUsersList = new ArrayList<>();

            for (String cod : codUsersVet) {
                codUsersList.add(Integer.parseInt(cod));
            }

            Arquivo.createFile(new Arquivo(nomeFile, codAutor, codUsersList));

            comando.append(nomeFile).append(SEP_CAMPOS)
                    .append(codAutor).append(SEP_CAMPOS)
                    .append(codUsers);

        } catch (IOException | ArquivoDuplicadoException ex) {
            System.err.println(ex.getMessage());
        }
        return comando.toString();
    }

    private String rename_file(String nomeFile, int codAutor, String codUsers, String rename) {
        StringBuilder comando = new StringBuilder();
        String[] codUsersVet = codUsers.split(",");
        List<Integer> codUsersList = new ArrayList<>();
        for (String cod : codUsersVet) {
            codUsersList.add(Integer.parseInt(cod));
        }
        Arquivo arquivo = new Arquivo(nomeFile, codAutor, codUsersList);
        Arquivo.rename(arquivo, rename);
        comando.append(nomeFile).append(SEP_CAMPOS)
                .append(codAutor).append(SEP_CAMPOS)
                .append(codUsers);
        return comando.toString();
    }

    private String replace_file(String nomeFile, int codAutor, String codUsers) {
        StringBuilder comando = new StringBuilder();
        try {
            String[] codUsersVet = codUsers.split(",");
            List<Integer> codUsersList = new ArrayList<>();

            for (String cod : codUsersVet) {
                codUsersList.add(Integer.parseInt(cod));
            }

            Arquivo.replace(new Arquivo(nomeFile, codAutor, codUsersList));

            comando.append(nomeFile).append(SEP_CAMPOS)
                    .append(codAutor).append(SEP_CAMPOS)
                    .append(codUsers);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return comando.toString();
    }

    private String update_file_data(String nomeFile, int codAutor, String codUsers) {
        StringBuilder comando = new StringBuilder();
        String[] codUsersVet = codUsers.split(",");
        List<Integer> codUsersList = new ArrayList<>();
        for (String cod : codUsersVet) {
            codUsersList.add(Integer.parseInt(cod));
        }

        Arquivo.updateFileData(new Arquivo(nomeFile, codAutor, codUsersList));

        comando.append(nomeFile).append(SEP_CAMPOS)
                .append(codAutor).append(SEP_CAMPOS)
                .append(codUsers);
        return comando.toString();
    }

}
