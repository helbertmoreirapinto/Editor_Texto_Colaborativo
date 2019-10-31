package client;

import editor.exc.ArquivoDuplicadoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helbert
 */
public class Arquivo {

    public static final String DIR_ARQUIVOS = "ARQUIVOS";
    public static final String FILE_DATA = ".data";
    public static final String FILE_TEXT = ".txt";
    private static final int COD_ELEM_AUTOR = 0;

    private String nome;
    private int codigoAutor;
    private List<Integer> codigoUsuarioAcesso;
    private File file;
    private File fileData;

    /**
     * Construtor da classe Arquivo(String, int, ArrayList).
     *
     * @param nome
     * @param codigoAutor
     * @param codigoUsuarioAcesso
     */
    public Arquivo(String nome, int codigoAutor, List<Integer> codigoUsuarioAcesso) {
        this.nome = nome;
        this.codigoAutor = codigoAutor;
        this.codigoUsuarioAcesso = new ArrayList<>(codigoUsuarioAcesso);
    }

    /**
     * Retorna true se criou arquivos fisicos e false se ocorreu algum problema.
     *
     * @return
     * @throws IOException
     * @throws ArquivoDuplicadoException
     */
    public boolean createFile() throws IOException, ArquivoDuplicadoException {
        File dir = new File(String.format("%s", DIR_ARQUIVOS));
        if (!dir.exists()) {
            dir.mkdirs();
        }

        this.file = new File(String.format("%s//%s%s", DIR_ARQUIVOS, this.nome, FILE_TEXT));
        if (this.file.exists()) {
            throw new ArquivoDuplicadoException("Arquivo com este nome encontrado");
        }

        return c_file();

    }

    public boolean replace() throws IOException {
        return c_file();
    }

    private boolean c_file() throws IOException {
        file.createNewFile();

        boolean append_mode = false;
        this.fileData = new File(String.format("%s//%s%s", DIR_ARQUIVOS, this.nome, FILE_DATA));
        try (FileWriter fw = new FileWriter(this.fileData, append_mode); BufferedWriter buffer = new BufferedWriter(fw)) {
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
     */
    public void editar(String texto) {
        boolean append_mode = false;
        try (FileWriter fw = new FileWriter(this.file, append_mode); BufferedWriter buffer = new BufferedWriter(fw)) {
            buffer.append(texto);
            buffer.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void rename(String novoNome) {
        File aux_txt = new File(String.format("%s//%s%s", file.getParent(), novoNome, FILE_TEXT));
        File aux_data = new File(String.format("%s//%s%s", file.getParent(), novoNome, FILE_DATA));
        file.renameTo(aux_txt);
        fileData.renameTo(aux_data);
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
     */
    public void updateFileData() {
        boolean append_mode = false;
        this.fileData = new File(String.format("%s//%s%s", DIR_ARQUIVOS, this.nome, FILE_DATA));
        try (FileWriter fw = new FileWriter(this.fileData, append_mode);
                BufferedWriter buffer = new BufferedWriter(fw)) {
            buffer.append(this.codigoAutor + "\n");
            for (Integer codUsu : this.codigoUsuarioAcesso) {
                buffer.append(codUsu + "\n");
            }
            buffer.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Retorna o texto contido no arquivo de texto.
     *
     * @return
     */
    public String getTexto() {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(this.file); BufferedReader buffer = new BufferedReader(reader)) {
            String linha = buffer.readLine();
            if (linha != null && !linha.isEmpty()) {
                sb.append(linha);
            }
            do {
                linha = buffer.readLine();
                if (linha != null && !linha.isEmpty()) {
                    sb.append("\n").append(linha);
                }
            } while (buffer.ready());

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return sb.toString();
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

    public List<Integer> getCodigoUsuarioAcesso() {
        return codigoUsuarioAcesso;
    }

    public void setUsuarioAcessoAdm(List<Integer> codigoUsuarioAcesso) {
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
     * @param usuario
     * @return
     */
    public static ArrayList<Arquivo> carregar_lista_arquivo(Usuario usuario) {
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

                    if (usuario.isAdm() || usuarioAcesso.contains(usuario.getCodigo())) {
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
