package editor;

import editor.acesso.AcessoUsuario;

/**
 *
 * @author helbert
 */
public class Usuario extends Pessoa {

    private static int cod = 0;
    private int codigo;
    private String login;
    private String senha;
    private AcessoUsuario acessoUsuario;

    public Usuario(int codigo, String nome, String login, String senha, AcessoUsuario acessoUsuario) {
        super(nome);
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.acessoUsuario = acessoUsuario;
    }

    public Usuario(String nome, String login, String senha, boolean adm) {
        super(nome);
        this.codigo = ++cod;
        this.login = login;
        this.senha = senha;
        this.acessoUsuario = new AcessoUsuario(adm);
    }

    public static void set_cod(int cod) {
        Usuario.cod = cod;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the acessoUsuario
     */
    public AcessoUsuario getAcessoUsuario() {
        return acessoUsuario;
    }

    /**
     * @param acessoUsuario the acessoUsuario to set
     */
    public void setAcessoUsuario(AcessoUsuario acessoUsuario) {
        this.acessoUsuario = acessoUsuario;
    }
}
