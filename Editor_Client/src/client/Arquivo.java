package client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helbert
 */
public class Arquivo {

    private String nome;
    private int codigoAutor;
    private List<Integer> codigoUsuarioAcesso;
    private File file;
    private File fileData;

    /**
     * Construtor da classe Arquivo(String, int, ArrayList).
     *
     * @param nome
     * @param codigoAutor
     * @param codigoUsuarioAcesso
     */
    public Arquivo(String nome, int codigoAutor, List<Integer> codigoUsuarioAcesso) {
        this.nome = nome;
        this.codigoAutor = codigoAutor;
        this.codigoUsuarioAcesso = new ArrayList<>(codigoUsuarioAcesso);
    }

    /* GETTER AND SETTER */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(int codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public List<Integer> getCodigoUsuarioAcesso() {
        return codigoUsuarioAcesso;
    }

    public void setUsuarioAcessoAdm(List<Integer> codigoUsuarioAcesso) {
        this.codigoUsuarioAcesso = codigoUsuarioAcesso;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }
}
