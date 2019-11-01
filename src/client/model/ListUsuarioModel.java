package client.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author helbert
 */
public class ListUsuarioModel extends AbstractListModel<String> {

    private final List<String> lista = new ArrayList<>();

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public String getElementAt(int index) {
        return lista.get(index);
    }

    public void addListAll(List list) {
        int tamanhoAntigo = getSize();
        lista.addAll(list);
        fireIntervalAdded(this, tamanhoAntigo, getSize() - 1);
    }

    public void addElem(String elem) {
        lista.add(elem);
        int ultimoIndice = getSize() - 1;
        fireIntervalAdded(this, ultimoIndice, ultimoIndice);
    }

    public void removeElem(int rowIndex) {
        lista.remove(rowIndex);
        fireIntervalRemoved(this, rowIndex, rowIndex);
    }

    public void limpar() {
        lista.clear();
        fireIntervalRemoved(this, 0, getSize() - 1);
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public void setValue(int row, String elem) {
        lista.set(row, elem);
        fireContentsChanged(this, row, row);
    }

    public List<String> getAllExits() {
        return lista;
    }
}
