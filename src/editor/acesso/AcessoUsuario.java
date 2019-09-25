package editor.acesso;

import java.util.HashMap;

/**
 *
 * @author helbert
 */
public class AcessoUsuario {

    private boolean usuarioAdm;
    private HashMap<Integer, HashMap<Integer, AcessoArquivo>> acessoArquivoList;

    public AcessoUsuario(boolean usuarioAdm) {
        this.usuarioAdm = usuarioAdm;
        this.acessoArquivoList = new HashMap<>();
    }

    public AcessoUsuario(boolean usuarioAdm, HashMap<Integer, HashMap<Integer, AcessoArquivo>> acessoArquivoList) {
        this.usuarioAdm = usuarioAdm;
        this.acessoArquivoList = acessoArquivoList;
    }

    /**
     * @return the usuarioAdm
     */
    public boolean isUsuarioAdm() {
        return usuarioAdm;
    }

    /**
     * @param usuarioAdm the usuarioAdm to set
     */
    public void setUsuarioAdm(boolean usuarioAdm) {
        this.usuarioAdm = usuarioAdm;
    }

    /**
     * @return the acessoArquivoList
     */
    public HashMap<Integer, HashMap<Integer, AcessoArquivo>> getAcessoArquivoList() {
        return acessoArquivoList;
    }

    /**
     * @param acessoArquivoList the acessoArquivoList to set
     */
    public void setAcessoArquivoList(HashMap<Integer, HashMap<Integer, AcessoArquivo>> acessoArquivoList) {
        this.acessoArquivoList = acessoArquivoList;
    }
}
