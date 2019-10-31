/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

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
public class UsuarioArquivoModel extends AbstractTableModel {

    private static final int COL_USER = 0;
    private static final int COL_ARQ = 1;

    private final List<UsuarioArquivo> linhas = new ArrayList<>();
    private final String[] colunas = {"Usuario", "Arquivo"};

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
        UsuarioArquivo obj = linhas.get(rowIndex);
        switch (columnIndex) {
            case COL_USER:
                return obj.getUser().getNome();
            case COL_ARQ:
                return obj.getArquivo().getNome();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public UsuarioArquivo getUsuarioArquivo(int lineIndex) {
        return linhas.get(lineIndex);
    }

    public void addUsuarioArquivo(UsuarioArquivo obj) {
        linhas.add(obj);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addUsuarioArquivo(Usuario u, Arquivo arq) {
        addUsuarioArquivo(new UsuarioArquivo(u, arq));
    }

    public void removeUsuarioArquivo(int rowIndex) {
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

    public void addUsuarioArquivoList(List list) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(list);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public List<UsuarioArquivo> getAllExits() {
        return linhas;
    }

    public void setValue(int row, UsuarioArquivo obj) {
        linhas.set(row, obj);
        fireTableRowsUpdated(row, row);
    }

    public void setValue(int row, Usuario u, Arquivo a) {
        setValue(row, new UsuarioArquivo(u, a));
    }

    public void requestFocusForFirstLine(JTable table) {
        table.addRowSelectionInterval(0, 0);
    }

    public class UsuarioArquivo {

        private final Usuario user;
        private final Arquivo arquivo;

        public UsuarioArquivo(Usuario user, Arquivo arquivo) {
            this.user = user;
            this.arquivo = arquivo;
        }

        public Usuario getUser() {
            return user;
        }

        public Arquivo getArquivo() {
            return arquivo;
        }

    }
}
