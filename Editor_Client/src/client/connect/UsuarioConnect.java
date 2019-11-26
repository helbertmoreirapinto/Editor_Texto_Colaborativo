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

    public Usuario logar(String login, String senha) throws IOException, InterruptedException, ClassNotFoundException {
        Usuario usuarioLogado = null;
        String resp_serv;
        String[] campos;
        try (Socket socket = new Socket(IP_SERVER, PORT_SERVER_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_LOGAR).append(SEP_CAMPOS).append(login).append(SEP_CAMPOS).append(senha);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                resp_serv = input.readUTF();
                campos = resp_serv.split(SEP_CAMPOS);
                usuarioLogado = new Usuario(campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
                usuarioLogado.setCodigo(Integer.parseInt(campos[0]));
            }
        }
        return usuarioLogado;
    }

    public Usuario get_usuario_pelo_codigo(int codigoUsuario) throws IOException, InterruptedException {
        Usuario usuario = null;
        String resp_serv;
        String[] campos;
        try (Socket socket = new Socket(IP_SERVER, PORT_SERVER_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_GET_USER).append(SEP_CAMPOS).append(codigoUsuario);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                resp_serv = input.readUTF();
                campos = resp_serv.split(SEP_CAMPOS);
                usuario = new Usuario(campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
                usuario.setCodigo(Integer.parseInt(campos[0]));
            }
        }
        return usuario;
    }

    public void inserir_usuario(Usuario userAlt) throws IOException {
        try (Socket socket = new Socket(IP_SERVER, PORT_SERVER_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_SAVE_USER).append(SEP_CAMPOS)
                    .append(userAlt.getNome()).append(SEP_CAMPOS)
                    .append(userAlt.getLogin()).append(SEP_CAMPOS)
                    .append(userAlt.getSenha()).append(SEP_CAMPOS)
                    .append(userAlt.isAtivo()).append(SEP_CAMPOS)
                    .append(userAlt.isAdm()).append(SEP_CAMPOS);
            output.writeUTF(comando.toString());
            output.flush();
        }
    }

    public void alterar_usuario(Usuario userAlt) throws IOException {
        try (Socket socket = new Socket(IP_SERVER, PORT_SERVER_USUARIO); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_SAVE_USER).append(SEP_CAMPOS)
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

    public HashMap<Integer, Usuario> carregar_lista_usuario() {
        return null;
    }
}
