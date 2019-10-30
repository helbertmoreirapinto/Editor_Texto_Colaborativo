package src.server.thread;

import editor.Arquivo;
import javax.swing.JTextArea;

/**
 *
 * @author helbert
 */
public class EscreverArquivo implements Runnable {

    private final JTextArea campo;
    private boolean escrever = false;
    private final Arquivo arquivo;

    public EscreverArquivo(JTextArea campo, Arquivo arquivo) {
        this.campo = campo;
        this.arquivo = arquivo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (escrever) {
                    arquivo.editar(campo.getText());
                    escrever = false;
                    Thread.sleep(10);
                }
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void alterar_arquivo() {
        escrever = true;
    }

}
