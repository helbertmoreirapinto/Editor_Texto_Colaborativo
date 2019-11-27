package client.thread;

import client.Arquivo;
import client.Usuario;
import java.util.HashMap;
import java.util.List;

/**
 * Classe AcessoCliente. Thread utilizada pelo cliente para requisitar acoes do
 * servidor.
 *
 * @author helbert
 */
public class AcessoCliente implements Runnable {

    private boolean status;
    private Usuario userLogado;
    private HashMap<Integer, Usuario> usuarioList;
    private List<Arquivo> arquivoList;
    private boolean executar_comando;
    private ComandoEnum comando;
    private boolean stopT = false;
    private Exception e;
    private Usuario userTemp;
    private Arquivo arqTemp;
    private String textTemp;

    @Override
    public void run() {
//        while (!Thread.interrupted()) {
//            status = true;
//            if (executar_comando) {
//                try {
//                    executar_comando = false;
//                    switch (comando) {
//                        case NEW_USER:
//                            Usuario.inserir_usuario(userTemp);
//                            break;
//                        case EDIT_USER:
//                            Usuario.alterar_usuario(userTemp);
//                            break;
//                        case LIST_USER:
//                            usuarioList = Usuario.carregar_lista_usuario();
//                            break;
//                        case LIST_FILE:
//                            arquivoList = Arquivo.carregar_lista_arquivo(userLogado);
//                            break;
//                        case UPDATE_FILE_DATA:
//                            Arquivo.updateFileData(arqTemp);
//                            break;
//                        case RENAME_FILE:
//                            Arquivo.rename(arqTemp, textTemp);
//                            break;
//                        case NEW_FILE:
//                            Arquivo.createFile(arqTemp);
//                            break;
//                        case REPLACE_FILE:
//                            Arquivo.replace(arqTemp);
//                            break;
//                        case READ_FILE:
//                            textTemp = Arquivo.getTexto(arqTemp);
//                            break;
//                        case WRITE_FILE:
//                            Arquivo.editar(arqTemp, textTemp);
//                            break;
//                        default:
//                            throw new AssertionError(comando.name());
//                    }
//                } catch (ArquivoDuplicadoException | IOException ex) {
//                    e = ex;
//                }
//            } else if (stopT) {
//                break;
//            }
//            delay(1);
//        }
//        status = false;
    }

    /**
     * Retorna o status da thread
     *
     * @return
     */
    public boolean isStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public Usuario getUsuarioLogado() {
        return userLogado;
    }

    /**
     *
     * @param i
     */
    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public Arquivo getArqTemp() {
        return arqTemp;
    }

    /**
     * Metodo responsavel em carregar para a memoria os usuarios salvos no
     * arquivo de dados.
     *
     * @return
     */
    public HashMap<Integer, Usuario> carregar_lista_usuario() {
        executar_comando = true;
        comando = ComandoEnum.LIST_USER;
        delay(100);
        return usuarioList;
    }

    /**
     *
     * @param user
     * @return
     */
    public List<Arquivo> carregar_lista_arquivo(Usuario user) {
        executar_comando = true;
        this.userLogado = user;
        comando = ComandoEnum.LIST_FILE;
        delay(100);
        return arquivoList;
    }

    /**
     *
     * @param usuario
     */
    public void inserir_usuario(Usuario usuario) {
        executar_comando = true;
        userTemp = usuario;
        comando = ComandoEnum.NEW_USER;
        delay(100);
    }

    /**
     *
     * @param usuarioAlterado
     */
    public void alterar_usuario(Usuario usuarioAlterado) {
        executar_comando = true;
        userTemp = usuarioAlterado;
        comando = ComandoEnum.EDIT_USER;
        delay(100);
    }

    /**
     *
     * @param arquivo
     */
    public void updateFileData(Arquivo arquivo) {
        executar_comando = true;
        arqTemp = arquivo;
        comando = ComandoEnum.UPDATE_FILE_DATA;
        delay(100);
    }

    /**
     *
     * @param arquivo
     * @param text
     */
    public void rename(Arquivo arquivo, String text) {
        executar_comando = true;
        arqTemp = arquivo;
        textTemp = text;
        comando = ComandoEnum.RENAME_FILE;
        delay(100);
    }

    /**
     *
     * @param arq
     * @throws Exception
     */
    public void createFile(Arquivo arq) throws Exception {
        executar_comando = true;
        arqTemp = arq;
        comando = ComandoEnum.NEW_FILE;
        delay(100);
        if (e != null) {
            throw new Exception(e);
        }
    }

    /**
     *
     * @param arq
     * @throws Exception
     */
    public void replace(Arquivo arq) throws Exception {
        executar_comando = true;
        arqTemp = arq;
        comando = ComandoEnum.REPLACE_FILE;
        delay(100);
        if (e != null) {
            throw new Exception(e);
        }
    }

    /**
     *
     */
    public void stop() {
        stopT = true;
    }

    /**
     *
     * @param arquivo
     * @param text
     */
    public void salvarTexto(Arquivo arquivo, String text) {
        executar_comando = true;
        arqTemp = arquivo;
        textTemp = text;
        comando = ComandoEnum.WRITE_FILE;
        delay(100);
    }

    /**
     *
     * @param arquivo
     * @return
     */
    public String lerTexto(Arquivo arquivo) {
        executar_comando = true;
        arqTemp = arquivo;
        comando = ComandoEnum.READ_FILE;
        delay(100);
        return textTemp;
    }

}