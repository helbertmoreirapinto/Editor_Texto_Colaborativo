package editor;

import editor.acesso.AcessoUsuario;

public class Usuario extends Pessoa {

	private static int cod = 0;
	private int codigo;
	private String login;
	private String senha;
	private boolean ativo;
	private AcessoUsuario acessoUsuario;

	public Usuario(int codigo, String nome, String login, String senha, AcessoUsuario acessoUsuario) {
		super(nome);
		this.codigo = codigo;
		this.login = login;
		this.senha = senha;
		this.acessoUsuario = acessoUsuario;
	}

	public Usuario(String nome, String login, String senha, boolean adm, boolean ativo) {
		super(nome);
		this.codigo = ++cod;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.acessoUsuario = new AcessoUsuario(adm);
	}

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

	public AcessoUsuario getAcessoUsuario() {
		return acessoUsuario;
	}

	public void setAcessoUsuario(AcessoUsuario acessoUsuario) {
		this.acessoUsuario = acessoUsuario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
