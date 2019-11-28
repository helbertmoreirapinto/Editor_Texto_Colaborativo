package server.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import server.Arquivo;
import server.Usuario;

/**
 * Classe ClientTableModel. Model utilizado na tabela onde se exibe os clientes
 * logados.
 *
 * @author helbert
 */
public class UsuarioFileTableModel extends AbstractTableModel {

    private static final int COL_NOME_FILE = 0;
    private static final int COL_NOME_USER = 1;

    private final List<UsuarioFile> linhas = new ArrayList<>();
    private final String[] colunas = {"Arquivo", "Usuario"};

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
        UsuarioFile usuario = linhas.get(rowIndex);
        switch (columnIndex) {
            case COL_NOME_FILE:
                return usuario.getFile().getNome();
            case COL_NOME_USER:
                return usuario.getUsuario().getNome();
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
     * @param lineIndex
     * @return
     */
    public UsuarioFile getUsuarioFile(int lineIndex) {
        return linhas.get(lineIndex);
    }

    /**
     *
     * @param u
     * @param file
     */
    public void addUsuarioFile(Usuario u, Arquivo file) {
        addUsuarioFile(new UsuarioFile(file, u));
    }

    /**
     * Adiciona um registro a tabela
     *
     * @param u
     */
    public void addUsuarioFile(UsuarioFile u) {
        linhas.add(u);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /**
     *
     * @param u
     */
    public void removeUsuarioFile(UsuarioFile u) {
        UsuarioFile l;
        for (int i = 0; i < getRowCount(); i++) {
            l = linhas.get(i);
            if (u.getUsuario().getCodigo() == l.getUsuario().getCodigo() && u.getFile().getNome().equals(l.getFile().getNome())) {
                removeUsuarioFile(i);
            }
        }
    }

    /**
     * Remove um registro da tabela
     *
     * @param rowIndex
     */
    public void removeUsuarioFile(int rowIndex) {
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
    public void addUsuarioFileList(List list) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(list);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /**
     * Retorna todos os registros contidos na tabela
     *
     * @return
     */
    public List<UsuarioFile> getAllExits() {
        return linhas;
    }

    /**
     *
     * @param row
     * @param usu
     * @param file
     */
    public void setValue(int row, Usuario usu, Arquivo file) {
        setValue(row, new UsuarioFile(file, usu));
    }

    /**
     * Insere um elemento numa posicao especifica
     *
     * @param row
     * @param usu
     */
    public void setValue(int row, UsuarioFile usu) {
        linhas.set(row, usu);
        fireTableRowsUpdated(row, row);
    }

    public void requestFocusForFirstLine(JTable table) {
        table.addRowSelectionInterval(0, 0);
    }
}
