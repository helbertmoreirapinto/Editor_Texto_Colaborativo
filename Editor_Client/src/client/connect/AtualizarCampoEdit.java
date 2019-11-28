package client.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JTextArea;

/**
 *
 * @author helbert
 */
public class AtualizarCampoEdit extends Connect implements Runnable {

    private final JTextArea campo;
    private final ObjectInputStream input;

    public AtualizarCampoEdit(JTextArea campo, ObjectInputStream input) {
        this.campo = campo;
        this.input = input;
    }

    @Override
    public void run() {
        System.out.println("TESTE AtualizarCampoEdit");
        while (!Thread.interrupted()) {
            try {

                campo.setText(input.readUTF());

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

}
