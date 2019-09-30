package editor;

import java.util.ArrayList;

public class Arquivo {
	private static int cod = 0;
	private int codigo;
	private String nome;
	private ArrayList<String> autorList;

	public Arquivo(String nome, ArrayList<String> autorList) {
		this.codigo = ++cod;
		this.nome = nome;
		this.autorList = autorList;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<String> getAutorList() {
		return autorList;
	}

	public void setAutorList(ArrayList<String> autorList) {
		this.autorList = autorList;
	}
}
