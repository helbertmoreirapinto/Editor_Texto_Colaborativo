package client.model;

import client.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Classe UsuarioTableModel. Model utilizado na tabela onde se exibe os Usuaios.
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

    /**
     * Retorna o numero de registros
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    /**
     * Retorna o numero de colunas
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
     * Retorna o valor dos campos
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
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

    /**
     * Retorna o nome das colunas
     *
     * @param columnIndex
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    /**
     * Retorna o registro cujo indice é o do parametro
     *
     * @param rowIndex
     * @return
     */
    public Usuario getUsuario(int rowIndex) {
        return linhas.get(rowIndex);
    }
/**
     * Adiciona um registro a tabela
     *
     * @param u
     */
    public void addUsuario(Usuario u) {
        linhas.add(u);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /**
     * Remove um registro da tabela
     *
     * @param rowIndex
     */
    public void removeUsuario(int rowIndex) {
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Limpa a tabela
     */
    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    /**
     * Verifica se a tabela esta vazia
     *
     * @return
     */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    /**
     * Adciona na tabela uma lista de elementos
     *
     * @param list
     */
    public void addUsuarioList(List list) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(list);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /**
     * Retorna todos os registros contidos na tabela
     *
     * @return
     */
    public List<Usuario> getAllExits() {
        return linhas;
    }

    /**
     * Insere um elemento numa posicao especifica
     *
     * @param row
     * @param usu
     */
    public void setValue(int row, Usuario usu) {
        linhas.set(row, usu);
        fireTableRowsUpdated(row, row);
    }

    public void requestFocusForFirstLine(JTable table) {
        table.addRowSelectionInterval(0, 0);
    }
}
