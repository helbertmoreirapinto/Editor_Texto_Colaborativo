package editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

    public static final String DIR_ARQUIVOS = "ARQUIVOS";
    public static final String FILE_DATA = ".data";
    public static final String FILE_TEXT = ".txt";
    private static final int COD_ELEM_AUTOR = 0;

    private String nome;
    private int codigoAutor;
    private ArrayList<Integer> codigoUsuarioAcesso;
    private File file;
    private File fileData;

    /**
     * Construtor da classe Arquivo(String, int, ArrayList).
     *
     * @param nome
     * @param codigoAutor
     * @param codigoUsuarioAcesso
     */
    public Arquivo(String nome, int codigoAutor, ArrayList<Integer> codigoUsuarioAcesso) {
        this.nome = nome;
        this.codigoAutor = codigoAutor;
        this.codigoUsuarioAcesso = (codigoUsuarioAcesso != null) ? codigoUsuarioAcesso : new ArrayList<>();
    }

    /**
     * Retorna true se criou arquivos fisicos e false se ocorreu algum problema.
     *
     * @return
     * @throws IOException
     */
    public boolean createFile() throws IOException {
        File dir = new File(String.format("%s", DIR_ARQUIVOS));
        if (!dir.exists()) {
            dir.mkdirs();
        }

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

    /**
     * Metodo de edicao do arquivo de texto. Recece o texto completo a ser
     * inserido no arquivo de texto.
     *
     * @param texto
     * @throws IOException
     */
    public void editar(ArrayList<String> texto) throws IOException {
        boolean append_mode = false;
        try (FileWriter fw = new FileWriter(this.file, append_mode); BufferedWriter buffer = new BufferedWriter(fw)) {
            for (String txt : texto) {
                buffer.append(txt + "\n");
            }
            buffer.flush();
        }
    }

    /**
     * Exclui os arquivos fisicos do disco.
     */
    public void excluir() {
        file.delete();
        fileData.delete();
    }

    /**
     * Atualiza as informcoes no arquivo de Data.
     *
     * @throws IOException
     */
    public void updateFileData() throws IOException {
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
    }

    /**
     * Retorna o texto contido no arquivo de texto.
     *
     * @return
     * @throws IOException
     */
    public ArrayList<String> getTexto() throws IOException {
        ArrayList<String> texto = new ArrayList<>();
        try (FileReader reader = new FileReader(this.file); BufferedReader buffer = new BufferedReader(reader)) {
            while (buffer.ready()) {
                texto.add(buffer.readLine());
            }
        }
        return texto;
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

    /* STATIC METHODS */
    /**
     * Carrega para a memoria uma lista de arquivos contidos no diretorio
     * padrao.
     *
     * @param codigoUsuario
     * @param filtro
     * @return
     */
    public static ArrayList<Arquivo> carregar_lista_arquivo(Usuario usuario, String filtro) {
        ArrayList<Arquivo> listaRetorno = new ArrayList<>();
        int codigoAutor;
        String nomeArquivo;
        ArrayList<Integer> usuarioAcesso;
        Arquivo arquivo;

        File diretorioArquivos = new File(DIR_ARQUIVOS);
        File[] arquivoList = diretorioArquivos.listFiles();

        if (arquivoList == null) {
            return listaRetorno;
        }

        for (File fileD : arquivoList) {
            if (fileD.getName().contains(FILE_DATA)) {
                nomeArquivo = fileD.getName().substring(0, fileD.getName().indexOf("."));
                usuarioAcesso = new ArrayList<>();

                try (FileReader reader = new FileReader(fileD); BufferedReader buffer = new BufferedReader(reader)) {

                    while (buffer.ready()) {
                        usuarioAcesso.add(Integer.parseInt(buffer.readLine()));
                    }

                    if (usuario.isAdm() || (usuarioAcesso.contains(usuario.getCodigo()) && nomeArquivo.contains(filtro))) {
                        codigoAutor = usuarioAcesso.get(COD_ELEM_AUTOR);
                        arquivo = new Arquivo(nomeArquivo, codigoAutor, usuarioAcesso);

                        for (File file : arquivoList) {
                            if (file.getName().equals(String.format("%s%s", nomeArquivo, FILE_TEXT))) {
                                arquivo.setFile(file);
                                break;
                            }
                        }

                        arquivo.setFileData(fileD);
                        listaRetorno.add(arquivo);
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

        return listaRetorno;
    }

}
