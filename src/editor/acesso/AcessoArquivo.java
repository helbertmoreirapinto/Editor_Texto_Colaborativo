package editor.acesso;

/**
 *
 * @author helbert
 */
public class AcessoArquivo {

    private int codigoArquivo;
    private boolean editarAcessos;
    private boolean editar;
    private boolean excluir;

    /**
     * @return the codigoArquivo
     */
    public int getCodigoArquivo() {
        return codigoArquivo;
    }

    /**
     * @param codigoArquivo the codigoArquivo to set
     */
    public void setCodigoArquivo(int codigoArquivo) {
        this.codigoArquivo = codigoArquivo;
    }

    /**
     * @return the editarAcessos
     */
    public boolean isEditarAcessos() {
        return editarAcessos;
    }

    /**
     * @param editarAcessos the editarAcessos to set
     */
    public void setEditarAcessos(boolean editarAcessos) {
        this.editarAcessos = editarAcessos;
    }

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the excluir
     */
    public boolean isExcluir() {
        return excluir;
    }

    /**
     * @param excluir the excluir to set
     */
    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

}
