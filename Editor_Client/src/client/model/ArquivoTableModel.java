package client.model;

import client.Arquivo;
import client.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Classe ArquivoTableModel. Model utilizado na tabela onde se exibe os
 * arquivos.
 *
 * @author helbert
 */
public class ArquivoTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_AUTOR = 1;

    private final List<Arquivo> linhas = new ArrayList<>();
    private final String[] colunas = {"Nome", "Autor"};

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
    public Arquivo getArquivo(int lineIndex) {
        return linhas.get(lineIndex);
    }

    /**
     * Adiciona um registro a tabela
     *
     * @param arq
     */
    public void addArquivo(Arquivo arq) {
        linhas.add(arq);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /**
     * Remove um registro da tabela
     *
     * @param rowIndex
     */
    public void removeArquivo(int rowIndex) {
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
    public void addArquivoList(List list) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(list);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /**
     * Retorna todos os registros contidos na tabela
     *
     * @return
     */
    public List<Arquivo> getAllExits() {
        return linhas;
    }

    /**
     * Insere um elemento numa posicao especifica
     *
     * @param row
     * @param arquivo
     */
    public void setValue(int row, Arquivo arquivo) {
        linhas.set(row, arquivo);
        fireTableRowsUpdated(row, row);
    }

    public void requestFocusForFirstLine(JTable table) {
        table.addRowSelectionInterval(0, 0);
    }
}
