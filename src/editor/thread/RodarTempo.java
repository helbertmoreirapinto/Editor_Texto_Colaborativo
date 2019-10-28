package editor.thread;

import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author helbert
 */
public class RodarTempo implements Runnable {

    private int delay = 0;
    private final JLabel lab;

    public RodarTempo(JLabel lab) {
        this.lab = lab;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (delay == 0) {
                    lab.setText("parou");
                } else {
                    delay--;
                    lab.setText("editando");
                }
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(RodarTempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reset_time() {
        delay = 1000;
    }

}
