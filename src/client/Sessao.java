package client;

import java.util.HashMap;
import java.util.Map;
import server.thread.AcessoCliente;
import server.thread.ClienteServidor;

/**
 * Classe Sessao. Contem variaveis comuns ao sistema. Evita de passa-las por
 * parametro.
 *
 * @author helbert
 */
public class Sessao {

    private static Sessao instance;
    private final Map<Integer, AcessoCliente> acessoMap;
    private final Map<Integer, Thread> threadMap;
    private final Map<Integer, Usuario> usuarioMap;
    private Arquivo arquivo;
    private ClienteServidor server;

    private Sessao() {
        this.acessoMap = new HashMap<>();
        this.threadMap = new HashMap<>();
        this.usuarioMap = new HashMap<>();
    }

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

    public ClienteServidor getServer() {
        return server;
    }

    public void setServer(ClienteServidor server) {
        this.server = server;
    }

    public static void setInstance(Sessao instance) {
        instance = new Sessao();
    }

    public AcessoCliente getAcesso(int codigo) {
        return acessoMap.get(codigo);
    }

    public void putAcessoMap(int codigo, AcessoCliente acesso) {
        this.acessoMap.put(codigo, acesso);
    }

    public Thread getThread(int codigo) {
        return threadMap.get(codigo);
    }

    public void putThreadMap(int codigo, Thread thread) {
        this.threadMap.put(codigo, thread);
    }

    public Usuario getUsuario(int codigo) {
        return usuarioMap.get(codigo);
    }

    public void putUsuarioMap(int codigo, Usuario usuario) {
        this.usuarioMap.put(codigo, usuario);
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

}
