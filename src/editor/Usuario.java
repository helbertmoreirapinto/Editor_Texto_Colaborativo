package editor;

public class Usuario extends Pessoa {

    private static int cod = 0;
    private int codigo;
    private String login;
    private String senha;
    private boolean ativo;
    private boolean adm;

    /**
     * Construtor da classe Usuario(String, String, String, boolean, boolean).
     *
     * @param nome
     * @param login
     * @param senha
     * @param adm
     * @param ativo
     */
    public Usuario(String nome, String login, String senha, boolean adm, boolean ativo) {
        super(nome);
        this.codigo = ++cod;
        this.login = login;
        this.senha = senha;
        this.adm = adm;
        this.ativo = ativo;
    }

    /* GETTER AND SETTER */
    public static void set_cod(int cod) {
        Usuario.cod = cod;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
}
