package server.thread;

import client.Arquivo;
import client.Usuario;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author helbert
 */
public class AcessoCliente implements Runnable {

    private Usuario user;
    private HashMap<Integer, Usuario> usuarioList;
    private List<Arquivo> arquivoList;
    private boolean executar_comando;
    private ComandoEnum comando;

    private boolean stopT = false;

    public AcessoCliente(Usuario user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (executar_comando) {
                executar_comando = false;
                switch (comando) {
                    case LIST_USER:
                        usuarioList = Usuario.carregar_lista_usuario();
                        break;
                    case LIST_FILE:
                        arquivoList = Arquivo.carregar_lista_arquivo(user);
                        break;
                    default:
                        throw new AssertionError(comando.name());
                }
            } else if (stopT) {
                break;
            }
            delay(1);
        }
    }

    public Usuario getUser() {
        return user;
    }

    public HashMap<Integer, Usuario> carregar_lista_usuario() {
        executar_comando = true;
        comando = ComandoEnum.LIST_USER;
        delay(50);
        return usuarioList;
    }

    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Arquivo> carregar_lista_arquivo(Usuario user) {
        executar_comando = true;
        this.user = user;
        comando = ComandoEnum.LIST_FILE;
        delay(50);
        return arquivoList;
    }

    public void stop() {
        stopT = true;
    }
}
