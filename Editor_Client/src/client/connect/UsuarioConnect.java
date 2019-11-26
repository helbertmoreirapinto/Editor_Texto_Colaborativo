package client.connect;

import client.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            comando.append(COMANDO_LOGAR);
            comando.append(SEPARADOR);
            comando.append(login);
            comando.append(SEPARADOR);
            comando.append(senha);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);
            if (input.readBoolean()) {
                resp_serv = input.readUTF();
                campos = resp_serv.split(SEPARADOR);
                usuarioLogado = new Usuario(campos[1], campos[2], campos[3], Boolean.parseBoolean(campos[4]), Boolean.parseBoolean(campos[5]));
                usuarioLogado.setCodigo(Integer.parseInt(campos[0]));
            }
        }
        return usuarioLogado;
    }
}
