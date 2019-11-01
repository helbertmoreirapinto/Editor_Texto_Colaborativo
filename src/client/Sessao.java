package client;

import server.thread.AcessoCliente;

/**
 *
 * @author helbert
 */
public class Sessao {

    private static Sessao instance;
    private AcessoCliente thread;
    private Usuario userLogado;
    private Arquivo arquivo;
    private Thread t;

    private Sessao() {
    }

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public void setThread(AcessoCliente thread) {
        this.thread = thread;
    }

    public void setUserLogado(Usuario userLogado) {
        this.userLogado = userLogado;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public AcessoCliente getThread() {
        return thread;
    }

    public Usuario getUserLogado() {
        return userLogado;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

}
