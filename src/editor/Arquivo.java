package editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {
	public static final String DIR_ARQUIVOS = "ARQUIVOS";
	public static final String FILE_DATA = ".data";
	public static final String FILE_TEXT = ".txt";

		

	private String nome;
	private int codigoAutor;
	private ArrayList<Integer> codigoUsuarioAcesso;
	private File file;
	private File fileData;

	public Arquivo(String nome, int codigoAutor, ArrayList<Integer> codigoUsuarioAcesso) {
		this.nome = nome;
		this.codigoAutor = codigoAutor;
		this.codigoUsuarioAcesso = (codigoUsuarioAcesso != null) ? codigoUsuarioAcesso : new ArrayList<Integer>();
	}

	public boolean createFile() throws IOException {
		File dir = new File(String.format("%s", DIR_ARQUIVOS));
		if (!dir.exists())
			dir.mkdirs();

		this.file = new File(String.format("%s//%s%s", DIR_ARQUIVOS, this.nome, FILE_TEXT));
		file.createNewFile();

		boolean append_mode = false;
		this.fileData = new File(String.format("%s//%s%s", DIR_ARQUIVOS, this.nome, FILE_DATA));
		try (FileWriter fw = new FileWriter(this.fileData, append_mode);
				BufferedWriter buffer = new BufferedWriter(fw)) {
			buffer.append(this.codigoAutor + "\n");
			for (Integer codUsu : this.codigoUsuarioAcesso) {
				buffer.append(codUsu + "\n");
			}
			buffer.flush();
		}
		return this.file.exists() && this.fileData.exists();
	}
	
	public void excluir() {

	}

	public void alterarAcessoArquivo() {

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

	public ArrayList<Integer> getCodigoUsuarioAcesso() {
		return codigoUsuarioAcesso;
	}

	public void setUsuarioAcessoAdm(ArrayList<Integer> codigoUsuarioAcesso) {
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
