package client;

import java.util.List;

/**
 * Classe Sessao. Contem variaveis comuns ao sistema. Evita de passa-las por
 * parametro.
 *
 * @author helbert
 */
public class Sessao {

    private static Sessao instance;
    private Usuario userLogado;
    private List<Arquivo> arquivoList;
    

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

    public List<Arquivo> getArquivoList() {
        return arquivoList;
    }

    public void setArquivoList(List<Arquivo> arquivoList) {
        this.arquivoList = arquivoList;
    }
}
