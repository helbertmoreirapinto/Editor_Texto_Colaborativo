package src.server.thread;

import editor.Arquivo;
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
        String texto;
        int cursor;

        while (true) {
            try {
                texto = arquivo.getTexto();

                if (!texto.equals(campo.getText())) {
                    cursor = campo.getCaretPosition();
                    campo.setText(arquivo.getTexto());
                    try {
//                    cursor = (cursor > max_text-1) ? max_text : cursor;
                        campo.setCaretPosition(cursor);
                    } catch (IllegalArgumentException ex) {
                        System.err.println(ex.getMessage());
                    }

                    Thread.sleep(200);
                }
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
