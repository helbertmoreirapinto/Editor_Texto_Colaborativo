package editor.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helbert
 */
public class RodarTempo implements Runnable {

    private int delay = 0;

    @Override
    public void run() {
        while (true) {
            try {
                if (delay > 0) {
                    delay--;
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

    public int getDelay() {
        return delay;
    }

}
