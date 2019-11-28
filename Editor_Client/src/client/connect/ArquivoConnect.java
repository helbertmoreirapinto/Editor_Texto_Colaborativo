package client.connect;

import client.Arquivo;
import editor.exc.ArquivoDuplicadoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helbert
 */
public class ArquivoConnect extends Connect {

    public List<Arquivo> carregar_lista_arquivo(int codUser) throws IOException, InterruptedException {
        List<Arquivo> list = new ArrayList<>();
        List<Integer> codigoUsuarioAcesso;
        Arquivo arquivo;
        String[] registros;
        String[] campo;
        String[] listUsers;
        String retorno;

        try (Socket socket = new Socket(IP_SERVER, PORT_FILE); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            comando.append(COMAND_FILELIST).append(SEP_CAMPOS)
                    .append(codUser);
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

    public Arquivo updateFileData(Arquivo arquivo) throws IOException, InterruptedException {
        try (Socket socket = new Socket(IP_SERVER, PORT_FILE); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            StringBuilder userList = new StringBuilder();
            ArrayList<Integer> codigoUsuarioAcesso = new ArrayList<>();
            String retorno;
            String[] campo;
            String[] listUsers;

            for (int cod : arquivo.getCodigoUsuarioAcesso()) {
                userList.append(cod).append(",");
            }
            userList.deleteCharAt(userList.length() - 1);

            comando.append(COMAND_FILE_DATA_UPD).append(SEP_CAMPOS)
                    .append(arquivo.getNome()).append(SEP_CAMPOS)
                    .append(arquivo.getCodigoAutor()).append(SEP_CAMPOS)
                    .append(userList.toString());
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                retorno = input.readUTF();
                campo = retorno.split(SEP_CAMPOS);
                if (campo.length > 2 && !campo[2].trim().isEmpty()) {
                    listUsers = campo[2].split(",");
                    for (String x : listUsers) {
                        codigoUsuarioAcesso.add(Integer.parseInt(x));
                    }
                }
                return new Arquivo(campo[0], Integer.parseInt(campo[1]), codigoUsuarioAcesso);
            }
        }
        return null;
    }

    public Arquivo rename(Arquivo arquivo, String nome) throws IOException, InterruptedException {
        try (Socket socket = new Socket(IP_SERVER, PORT_FILE); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            StringBuilder userList = new StringBuilder();
            ArrayList<Integer> codigoUsuarioAcesso = new ArrayList<>();

            for (int cod : arquivo.getCodigoUsuarioAcesso()) {
                userList.append(cod).append(",");
            }
            userList.deleteCharAt(userList.length() - 1);
            comando.append(COMAND_RENAME_FILE).append(SEP_CAMPOS)
                    .append(arquivo.getNome()).append(SEP_CAMPOS)
                    .append(arquivo.getCodigoAutor()).append(SEP_CAMPOS)
                    .append(userList.toString()).append(SEP_CAMPOS)
                    .append(nome);
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            if (input.readBoolean()) {
                String retorno = input.readUTF();
                String[] campo = retorno.split(SEP_CAMPOS);
                if (campo.length > 2 && !campo[2].trim().isEmpty()) {
                    String[] listUsers = campo[2].split(",");
                    for (String x : listUsers) {
                        codigoUsuarioAcesso.add(Integer.parseInt(x));
                    }
                }
                return new Arquivo(campo[0], Integer.parseInt(campo[1]), codigoUsuarioAcesso);
            }
        }
        return null;

    }

    public Arquivo createFile(Arquivo arquivo) throws IOException, InterruptedException, ArquivoDuplicadoException {
        try (Socket socket = new Socket(IP_SERVER, PORT_FILE); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            StringBuilder userList = new StringBuilder();
            ArrayList<Integer> codigoUsuarioAcesso = new ArrayList<>();
            String retorno;

            for (int cod : arquivo.getCodigoUsuarioAcesso()) {
                userList.append(cod).append(",");
            }
            userList.deleteCharAt(userList.length() - 1);
            comando.append(COMAND_NEW_FILE).append(SEP_CAMPOS)
                    .append(arquivo.getNome()).append(SEP_CAMPOS)
                    .append(arquivo.getCodigoAutor()).append(SEP_CAMPOS)
                    .append(userList.toString());
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);

            retorno = input.readUTF();
            if (input.readBoolean()) {
                String[] campo = retorno.split(SEP_CAMPOS);
                if (campo.length > 2 && !campo[2].trim().isEmpty()) {
                    String[] listUsers = campo[2].split(",");
                    for (String x : listUsers) {
                        codigoUsuarioAcesso.add(Integer.parseInt(x));
                    }
                }
                return new Arquivo(campo[0], Integer.parseInt(campo[1]), codigoUsuarioAcesso);
            }
            if (retorno.equals(FILE_DUPLICADO)) {
                throw new ArquivoDuplicadoException("");
            }
        }
        return null;
    }

    public Arquivo replace(Arquivo arquivo) throws IOException, InterruptedException {
        try (Socket socket = new Socket(IP_SERVER, PORT_FILE); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            StringBuilder comando = new StringBuilder();
            StringBuilder userList = new StringBuilder();
            ArrayList<Integer> codigoUsuarioAcesso = new ArrayList<>();

            for (int cod : arquivo.getCodigoUsuarioAcesso()) {
                userList.append(cod).append(",");
            }
            userList.deleteCharAt(userList.length() - 1);
            comando.append(COMAND_REPLACE_FILE).append(SEP_CAMPOS)
                    .append(arquivo.getNome()).append(SEP_CAMPOS)
                    .append(arquivo.getCodigoAutor()).append(SEP_CAMPOS)
                    .append(userList.toString());
            output.writeUTF(comando.toString());
            output.flush();
            delay(50);
            if (input.readBoolean()) {
                String retorno = input.readUTF();
                String[] campo = retorno.split(SEP_CAMPOS);
                if (campo.length > 2 && !campo[2].trim().isEmpty()) {
                    String[] listUsers = campo[2].split(",");
                    for (String x : listUsers) {
                        codigoUsuarioAcesso.add(Integer.parseInt(x));
                    }
                }
                return new Arquivo(campo[0], Integer.parseInt(campo[1]), codigoUsuarioAcesso);
            }
        }
        return null;
    }

}
