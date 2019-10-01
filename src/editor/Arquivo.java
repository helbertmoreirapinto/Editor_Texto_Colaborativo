package editor;

import java.util.ArrayList;

public class Arquivo {
	private String nome;
	private int codigoAutor;
	private ArrayList<String> usuarioAcessoAdm;

	public Arquivo(String nome, int codigoAutor) {
		this.nome = nome;
		this.codigoAutor = codigoAutor;
	}
	
	public Arquivo(String nome, int codigoAutor, ArrayList<String> usuarioAcessoAdm) {
		this.nome = nome;
		this.codigoAutor = codigoAutor;
		this.usuarioAcessoAdm = usuarioAcessoAdm;
	}
	
	
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

	public ArrayList<String> getUsuarioAcessoAdm() {
		return usuarioAcessoAdm;
	}

	public void setUsuarioAcessoAdm(ArrayList<String> usuarioAcessoAdm) {
		this.usuarioAcessoAdm = usuarioAcessoAdm;
	}
}
