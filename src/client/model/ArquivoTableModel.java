package client.model;

import client.Arquivo;
import client.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author helbert
 */
public class ArquivoTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_AUTOR = 1;

    private final List<Arquivo> linhas = new ArrayList<>();
    private final String[] colunas = {"Nome", "Autor"};

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Arquivo arquivo = linhas.get(rowIndex);
        switch (columnIndex) {
            case COL_NOME:
                return arquivo.getNome();
            case COL_AUTOR:
                return Usuario.get_usuario_pelo_codigo(arquivo.getCodigoAutor()).getNome();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public Arquivo getArquivo(int lineIndex) {
        return linhas.get(lineIndex);
    }

    public void addArquivo(Arquivo arq) {
        linhas.add(arq);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeArquivo(int rowIndex) {
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addArquivoList(List list) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(list);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public List<Arquivo> getAllExits() {
        return linhas;
    }

    public void setValue(int row, Arquivo arquivo) {
        linhas.set(row, arquivo);
        fireTableRowsUpdated(row, row);
    }

    public void requestFocusForFirstLine(JTable table) {
        table.addRowSelectionInterval(0, 0);
    }
}
