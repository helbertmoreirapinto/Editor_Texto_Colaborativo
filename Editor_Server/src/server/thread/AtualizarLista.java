package server.thread;

import server.ClientTableModel;
import java.util.List;
import javax.swing.JLabel;

/**
 * Class AtualizarLista. Thread responsavel em atualizar a lista do servidor
 *
 * @author helbert
 */
public class AtualizarLista implements Runnable {

    private final ClientTableModel model;
    private final ClienteServidor cs;
    private final JLabel num;

    /**
     *
     * @param num
     * @param model
     * @param cs
     */
    public AtualizarLista(JLabel num, ClientTableModel model, ClienteServidor cs) {
        this.model = model;
        this.cs = cs;
        this.num = num;
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
    @Override
    public void run() {
        List<AcessoCliente> acessoList;
        while (!Thread.interrupted()) {
            model.limpar();
            acessoList = cs.getThreadList();
            for (int i = acessoList.size() - 1; i >= 0; i--) {
                if (!acessoList.get(i).isStatus()) {
                    acessoList.remove(i);
                }
            }
            for (AcessoCliente a : acessoList) {
                if (a.getUsuarioLogado() != null) {
                    model.addUsuario(a.getUsuarioLogado());
                    num.setText(String.valueOf(model.getRowCount()));
                }
            }
            delay(5000);
        }
    }
}
