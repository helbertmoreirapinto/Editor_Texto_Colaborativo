package server.model;

import server.Arquivo;
import server.Usuario;

/**
 *
 * @author helbert
 */
public class UsuarioFile {

    private Usuario usuario;
    private Arquivo file;

    public UsuarioFile(Arquivo file, Usuario user) {
        this.usuario = user;
        this.file = file;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Arquivo getFile() {
        return file;
    }

    public void setFile(Arquivo file) {
        this.file = file;
    }

}
