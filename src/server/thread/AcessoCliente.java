package server.thread;

import client.Arquivo;
import client.Usuario;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author helbert
 */
public class AcessoCliente implements Runnable {

    private Usuario userTemp;
    private Usuario userLogado;
    private HashMap<Integer, Usuario> usuarioList;
    private List<Arquivo> arquivoList;
    private boolean executar_comando;
    private ComandoEnum comando;

    private boolean stopT = false;

    public AcessoCliente() {
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (executar_comando) {
                executar_comando = false;
                switch (comando) {
                    case NEW_USER:
                        Usuario.inserir_usuario(userTemp);
                        break;
                    case EDIT_USER:
                        Usuario.alterar_usuario(userTemp);
                        break;
                    case LIST_USER:
                        usuarioList = Usuario.carregar_lista_usuario();
                        break;
                    case LIST_FILE:
                        arquivoList = Arquivo.carregar_lista_arquivo(userLogado);
                        break;
                    default:
                        throw new AssertionError(comando.name());
                }
            } else if (stopT) {
                break;
            }
            delay(1);
        }
    }

    public Usuario getUsuarioLogado() {
        return userLogado;
    }

    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
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

    public List<Arquivo> carregar_lista_arquivo(Usuario user) {
        executar_comando = true;
        this.userLogado = user;
        comando = ComandoEnum.LIST_FILE;
        delay(100);
        return arquivoList;
    }

    public void inserir_usuario(Usuario usuario) {
        executar_comando = true;
        userTemp = usuario;
        comando = ComandoEnum.NEW_USER;
        delay(100);
    }

    public void alterar_usuario(Usuario usuarioAlterado) {
        executar_comando = true;
        userTemp = usuarioAlterado;
        comando = ComandoEnum.EDIT_USER;
        delay(100);
    }

    public void stop() {
        stopT = true;
    }

}
