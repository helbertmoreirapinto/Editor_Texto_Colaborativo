package client;

import client.thread.ClienteServidor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Classe Sessao. Contem variaveis comuns ao sistema. Evita de passa-las por
 * parametro.
 *
 * @author helbert
 */
public class Sessao {

    private static Sessao instance;
    private Usuario userLogado;
    

    /**
     * Se nao houver, inicia uma instancia da classe Sessao
     *
     * @return
     */
    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }

    public static void setInstance(Sessao instance) {
        instance = new Sessao();
    }

    public Usuario getUserLogado() {
        return userLogado;
    }

    public void setUserLogado(Usuario userLogado) {
        this.userLogado = userLogado;
    }
}
