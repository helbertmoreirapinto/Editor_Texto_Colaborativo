package client.connect;

import client.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author helbert
 */
public class UsuarioConnect extends Connect {

    /**
     *
     * @param login
     * @param senha
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public Usuario logar(String login, String senha) throws IOException, InterruptedException {
        Usuario usuarioLogado = null;
        String resp_serv;
        String[] campos;
        try (Socket socket = new Socket(IP_SERVER, PORT_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_LOGAR).append(SEP_CAMPOS)
                    .append(login).append(SEP_CAMPOS).append(senha);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                resp_serv = input.readUTF();
                campos = resp_serv.split(SEP_CAMPOS);
                usuarioLogado = new Usuario(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
            }
        }
        return usuarioLogado;
    }

    /**
     *
     * @param codigoUsuario
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public Usuario get_usuario_pelo_codigo(int codigoUsuario) throws IOException, InterruptedException {
        Usuario usuario = null;
        String resp_serv;
        String[] campos;
        try (Socket socket = new Socket(IP_SERVER, PORT_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_GET_USER).append(SEP_CAMPOS).append(codigoUsuario);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                resp_serv = input.readUTF();
                campos = resp_serv.split(SEP_CAMPOS);
                usuario = new Usuario(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
            }
        }
        return usuario;
    }

    /**
     *
     * @param userAlt
     * @throws IOException
     */
    public void inserir_usuario(Usuario userAlt) throws IOException {
        try (Socket socket = new Socket(IP_SERVER, PORT_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_SAVE_USER).append(SEP_CAMPOS)
                    .append(userAlt.getNome()).append(SEP_CAMPOS)
                    .append(userAlt.getLogin()).append(SEP_CAMPOS)
                    .append(userAlt.getSenha()).append(SEP_CAMPOS)
                    .append(userAlt.isAtivo()).append(SEP_CAMPOS)
                    .append(userAlt.isAdm()).append(SEP_CAMPOS);
            output.writeUTF(comando.toString());
            output.flush();
        }
    }

    /**
     *
     * @param userAlt
     * @throws IOException
     */
    public void alterar_usuario(Usuario userAlt) throws IOException {
        try (Socket socket = new Socket(IP_SERVER, PORT_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_UPD_USER).append(SEP_CAMPOS)
                    .append(userAlt.getCodigo()).append(SEP_CAMPOS)
                    .append(userAlt.getNome()).append(SEP_CAMPOS)
                    .append(userAlt.getLogin()).append(SEP_CAMPOS)
                    .append(userAlt.getSenha()).append(SEP_CAMPOS)
                    .append(userAlt.isAtivo()).append(SEP_CAMPOS)
                    .append(userAlt.isAdm()).append(SEP_CAMPOS);
            output.writeUTF(comando.toString());
            output.flush();
        }
    }

    /**
     *
     * @return @throws IOException
     * @throws InterruptedException
     */
    public HashMap<Integer, Usuario> carregar_lista_usuario() throws IOException, InterruptedException {
        HashMap<Integer, Usuario> list = new HashMap<>();
        String retorno;
        String[] registros;
        String[] campo;
        Usuario user;

        try (Socket socket = new Socket(IP_SERVER, PORT_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_USERLIST);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                retorno = input.readUTF();
                registros = retorno.split(SEP_REGS);
                for (String reg : registros) {
                    campo = reg.split(SEP_CAMPOS);
                    user = new Usuario(Integer.parseInt(campo[0]), campo[1], campo[2], campo[3], Boolean.parseBoolean(campo[4]), Boolean.parseBoolean(campo[5]));
                    list.put(user.getCodigo(), user);
                }
            }
        }
        return list;
    }
}
