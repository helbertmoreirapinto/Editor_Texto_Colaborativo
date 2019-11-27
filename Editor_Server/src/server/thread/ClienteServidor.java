package server.thread;

import server.ComandoEnum;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ClienteServidor. Thread do servidor.
 *
 * @author helbert
 */
public class ClienteServidor implements Runnable {

    public static final int STATUS_ONLINE = 1;
    public static final int STATUS_OFFLINE = 2;

    private ComandoEnum comando;
    private final List<AcessoCliente> threadList;
    private boolean executar_comando;
    private boolean rodar;
    private int status;

    /**
     * 
     */
    public ClienteServidor() {
        threadList = new ArrayList<>();
        executar_comando = false;
        status = STATUS_OFFLINE;
//        this.model = model;
    }

    /**
     * 
     */
    @Override
    public void run() {

        while (!Thread.interrupted()) {

            if (rodar) {
                status = STATUS_ONLINE;
                if (executar_comando) {
                    executar_comando = false;
                    switch (comando) {
                        case NEW_ACESS:
                            threadList.add(new AcessoCliente());
                            break;
                        default:
                            throw new AssertionError(comando.name());
                    }
                }

            } else {
                status = STATUS_OFFLINE;
            }
            delay(1);
        }

    }

    /**
     * 
     * @return 
     */
    public AcessoCliente getAcesso() {
        executar_comando = true;
        comando = ComandoEnum.NEW_ACESS;
        delay(100);
        return threadList.get(threadList.size() - 1);
    }

    /**
     * 
     * @param i 
     */
    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * 
     */
    public void parar() {
        rodar = false;
    }

    /**
     * 
     */
    public void iniciar() {
        rodar = true;
    }

    public int getStatus() {
        return status;
    }

    public List<AcessoCliente> getThreadList() {
        return threadList;
    }

    public void setRodar(boolean a) {
        rodar = a;
    }

}