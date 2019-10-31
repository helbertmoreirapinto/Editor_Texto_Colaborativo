package server.thread;

/**
 *
 * @author helbert
 */
public class ClienteServidor implements Runnable {

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }
}
