package editor.acesso;

import java.util.HashMap;

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

    public boolean isUsuarioAdm() {
        return usuarioAdm;
    }

    public void setUsuarioAdm(boolean usuarioAdm) {
        this.usuarioAdm = usuarioAdm;
    }

    public HashMap<Integer, HashMap<Integer, AcessoArquivo>> getAcessoArquivoList() {
        return acessoArquivoList;
    }

    public void setAcessoArquivoList(HashMap<Integer, HashMap<Integer, AcessoArquivo>> acessoArquivoList) {
        this.acessoArquivoList = acessoArquivoList;
    }
}
