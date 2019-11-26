package client.connect;

import client.Arquivo;
import client.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author helbert
 */
public class ArquivoConnect extends Connect {

    public List<Arquivo> getArquivosUsuario(int codUser) throws IOException, InterruptedException {
        List<Arquivo> list = new ArrayList<>();
        String retorno;
        String[] registros;
        String[] campo;
        Arquivo arquivo;
        String[] listUsers;
        List<Integer> codigoUsuarioAcesso;

        try (Socket socket = new Socket(IP_SERVER, PORT_SERVER_FILE); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMANDO_FILELIST);
            comando.append(SEP_CAMPOS);
            comando.append(codUser);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                retorno = input.readUTF();
                registros = retorno.split(SEP_REGS);
                for (String reg : registros) {
                    codigoUsuarioAcesso = new ArrayList<>();
                    campo = reg.split(SEP_CAMPOS);
                    if (campo.length > 2 && !campo[2].trim().isEmpty()) {
                        listUsers = campo[2].split(",");
                        for (String x : listUsers) {
                            codigoUsuarioAcesso.add(Integer.parseInt(x));
                        }
                    }
                    arquivo = new Arquivo(campo[0], Integer.parseInt(campo[1]), codigoUsuarioAcesso);
                    list.add(arquivo);
                }
            }
        }
        return list;
    }

    public void updateFileData(Arquivo arquivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void rename(Arquivo arquivo, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createFile(Arquivo arq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void replace(Arquivo arq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Arquivo> carregar_lista_arquivo(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
