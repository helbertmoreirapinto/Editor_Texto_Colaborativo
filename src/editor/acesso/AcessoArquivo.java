package editor.acesso;

public class AcessoArquivo {

    private int codigoArquivo;
    private boolean editarAcessos;
    private boolean editar;
    private boolean excluir;

    public int getCodigoArquivo() {
        return codigoArquivo;
    }

    public void setCodigoArquivo(int codigoArquivo) {
        this.codigoArquivo = codigoArquivo;
    }

    public boolean isEditarAcessos() {
        return editarAcessos;
    }

    public void setEditarAcessos(boolean editarAcessos) {
        this.editarAcessos = editarAcessos;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

}
