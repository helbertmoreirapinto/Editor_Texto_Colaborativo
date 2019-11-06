package server;

import server.exc.ArquivoDuplicadoException;
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

    /**
     * Retorna true se criou arquivos fisicos e false se ocorreu algum problema.
     *
     * @param arquivo
     * @return
     * @throws IOException
     * @throws ArquivoDuplicadoException
     */
    public static boolean createFile(Arquivo arquivo) throws IOException, ArquivoDuplicadoException {
        File dir = new File(String.format("%s", DIR_ARQUIVOS));
        if (!dir.exists()) {
            dir.mkdirs();
        }

        arquivo.file = new File(String.format("%s//%s%s", DIR_ARQUIVOS, arquivo.nome, FILE_TEXT));
        if (arquivo.file.exists()) {
            throw new ArquivoDuplicadoException("Arquivo com este nome encontrado");
        }
        return c_file(arquivo);
    }

    /**
     * Retorna o texto contido no arquivo de texto.
     *
     * @param arquivo
     * @return
     */
    public static String getTexto(Arquivo arquivo) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(arquivo.file); BufferedReader buffer = new BufferedReader(reader)) {
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

    /**
     * Atualiza as informcoes no arquivo de Data.
     *
     * @param arquivo
     */
    public static void updateFileData(Arquivo arquivo) {
        boolean append_mode = false;
        arquivo.fileData = new File(String.format("%s//%s%s", DIR_ARQUIVOS, arquivo.nome, FILE_DATA));
        try (FileWriter fw = new FileWriter(arquivo.fileData, append_mode);
                BufferedWriter buffer = new BufferedWriter(fw)) {
            buffer.append(arquivo.codigoAutor + "\n");
            for (Integer codUsu : arquivo.codigoUsuarioAcesso) {
                buffer.append(codUsu + "\n");
            }
            buffer.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Substitui um arquivo com mesmo nome
     * @param arquivo
     * @return
     * @throws IOException 
     */
    public static boolean replace(Arquivo arquivo) throws IOException {
        return c_file(arquivo);
    }

    private static boolean c_file(Arquivo arquivo) throws IOException {
        arquivo.file.createNewFile();

        boolean append_mode = false;
        arquivo.fileData = new File(String.format("%s//%s%s", DIR_ARQUIVOS, arquivo.nome, FILE_DATA));
        try (FileWriter fw = new FileWriter(arquivo.fileData, append_mode); BufferedWriter buffer = new BufferedWriter(fw)) {
            buffer.append(arquivo.codigoAutor + "\n");
            for (Integer codUsu : arquivo.codigoUsuarioAcesso) {
                buffer.append(codUsu + "\n");
            }
            buffer.flush();
        }
        return arquivo.file.exists() && arquivo.fileData.exists();
    }

    /**
     * Metodo de edicao do arquivo de texto.Recece o texto completo a ser
     * inserido no arquivo de texto.
     *
     * @param arquivo
     * @param texto
     */
    public static void editar(Arquivo arquivo, String texto) {
        boolean append_mode = false;
        try (FileWriter fw = new FileWriter(arquivo.file, append_mode); BufferedWriter buffer = new BufferedWriter(fw)) {
            buffer.append(texto);
            buffer.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Renomear arquivo em disco
     *
     * @param arquivo
     * @param novoNome
     */
    public static void rename(Arquivo arquivo, String novoNome) {
        File aux_txt = new File(String.format("%s//%s%s", arquivo.file.getParent(), novoNome, FILE_TEXT));
        File aux_data = new File(String.format("%s//%s%s", arquivo.file.getParent(), novoNome, FILE_DATA));
        arquivo.file.renameTo(aux_txt);
        arquivo.fileData.renameTo(aux_data);
    }

    /**
     * Exclui os arquivos fisicos do disco.
     *
     * @param arquivo
     */
    public static void excluir(Arquivo arquivo) {
        arquivo.file.delete();
        arquivo.fileData.delete();
    }

}
