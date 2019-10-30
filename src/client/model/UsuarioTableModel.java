package client.model;

import editor.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author helbert
 */
public class UsuarioTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_NOME = 1;
    private static final int COL_LOGIN = 2;
    private static final int COL_ADM = 3;
    private static final int COL_ATIVO = 4;

    private final List<Usuario> linhas = new ArrayList<>();
    private final String[] colunas = {"Código", "Nome", "Login", "ADM", "Ativo"};

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
        Usuario usuario = linhas.get(rowIndex);
        switch (columnIndex) {
            case COL_CODIGO:
                return usuario.getCodigo();
            case COL_NOME:
                return usuario.getNome();
            case COL_LOGIN:
                return usuario.getLogin();
            case COL_ADM:
                return usuario.isAdm();
            case COL_ATIVO:
                return usuario.isAtivo();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public Usuario getUsuario(int lineIndex) {
        return linhas.get(lineIndex);
    }

    public void addUsuario(Usuario u) {
        linhas.add(u);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeUsuario(int rowIndex) {
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

    public void addUsuarioList(List list) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(list);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public List<Usuario> getAllExits() {
        return linhas;
    }

    public void setValue(int row, Usuario usu) {
        linhas.set(row, usu);
        fireTableRowsUpdated(row, row);
    }

    public void requestFocusForFirstLine(JTable table) {
        table.addRowSelectionInterval(0, 0);
    }
}
