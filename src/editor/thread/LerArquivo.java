package editor.thread;

import editor.Arquivo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author helbert
 */
public class LerArquivo implements Runnable {

    private final JTextArea campo;
    private final Arquivo arquivo;

    public LerArquivo(JTextArea campo, Arquivo arquivo) {
        this.campo = campo;
        this.arquivo = arquivo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                campo.setText(arquivo.getTexto());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
