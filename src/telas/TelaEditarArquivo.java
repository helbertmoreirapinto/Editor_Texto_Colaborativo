package telas;

import editor.Arquivo;
import editor.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 *
 * @author helbert
 */
public class TelaEditarArquivo extends javax.swing.JFrame {

    private final ListUsuarioModel model;
    private final Arquivo arquivo;
    private final Usuario user;
    private String tempText;
    private final UndoAction undoAction;
    private final RedoAction redoAction;
    protected UndoManager undoManager;

    public TelaEditarArquivo(Usuario user, Arquivo arquivo) {
        initComponents();

//      INIT PARAM
        this.arquivo = arquivo;
        this.user = user;
        this.model = new ListUsuarioModel();

//      SET VALUES SCREEN
        txtNomeArquivo.setText(this.arquivo.getNome());
        txtAutor.setText(Usuario.get_usuario_pelo_codigo(this.arquivo.getCodigoAutor()).getNome());
        txtUsuarioLogado.setText(String.format("[%d] %s", user.getCodigo(), user.getNome()));
        listOnline.setModel(model);
        model.addElem(user.getNome());

//      SET REDO UNDO
        undoManager = new UndoManager();
        areaTexto.getDocument().addUndoableEditListener(new UndoListener());
        undoAction = new UndoAction();
        itemDesfazer.setAction(undoAction);
        undoAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        redoAction = new RedoAction();
        itemRefazer.setAction(redoAction);
        redoAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
    }

    private void fechar_arquivo() {
        TelaMenu tela = new TelaMenu(user);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu();
        itemPopupCopiar = new javax.swing.JMenuItem();
        itemPopupColar = new javax.swing.JMenuItem();
        panEditar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        panButton = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        panArquivo = new javax.swing.JPanel();
        lblUsuarioLogado = new javax.swing.JLabel();
        txtUsuarioLogado = new javax.swing.JLabel();
        lblNomeArquivo = new javax.swing.JLabel();
        txtNomeArquivo = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        txtAutor = new javax.swing.JLabel();
        panOnline = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOnline = new javax.swing.JList<>();
        menu = new javax.swing.JMenuBar();
        menArquivo = new javax.swing.JMenu();
        itemNovoArquivo = new javax.swing.JMenuItem();
        itemAbrirArquivo = new javax.swing.JMenuItem();
        itemFechar = new javax.swing.JMenuItem();
        menEditar = new javax.swing.JMenu();
        itemSelecionarTudo = new javax.swing.JMenuItem();
        itemDesfazer = new javax.swing.JMenuItem();
        itemRefazer = new javax.swing.JMenuItem();

        itemPopupCopiar.setText("Copiar");
        itemPopupCopiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemPopupCopiarMouseReleased(evt);
            }
        });
        popup.add(itemPopupCopiar);

        itemPopupColar.setText("Colar");
        itemPopupColar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemPopupColarMouseReleased(evt);
            }
        });
        popup.add(itemPopupColar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panEditar.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar"));

        areaTexto.setColumns(20);
        areaTexto.setRows(5);
        areaTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                areaTextoMouseReleased(evt);
            }
        });
        areaTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                areaTextoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(areaTexto);

        javax.swing.GroupLayout panEditarLayout = new javax.swing.GroupLayout(panEditar);
        panEditar.setLayout(panEditarLayout);
        panEditarLayout.setHorizontalGroup(
            panEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panEditarLayout.setVerticalGroup(
            panEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        btnSalvar.setText("Salvar");

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panButtonLayout = new javax.swing.GroupLayout(panButton);
        panButton.setLayout(panButtonLayout);
        panButtonLayout.setHorizontalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panButtonLayout.createSequentialGroup()
                .addContainerGap(344, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnFechar)
                .addGap(310, 310, 310))
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnFechar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panArquivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivo"));

        lblUsuarioLogado.setText("Usuario:");

        lblNomeArquivo.setText("Arquivo:");

        lblAutor.setText("Autor:");

        javax.swing.GroupLayout panArquivoLayout = new javax.swing.GroupLayout(panArquivo);
        panArquivo.setLayout(panArquivoLayout);
        panArquivoLayout.setHorizontalGroup(
            panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuarioLogado)
                    .addComponent(lblAutor)
                    .addComponent(lblNomeArquivo))
                .addGap(18, 18, 18)
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeArquivo)
                    .addComponent(txtAutor)
                    .addComponent(txtUsuarioLogado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panArquivoLayout.setVerticalGroup(
            panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioLogado)
                    .addComponent(txtUsuarioLogado))
                .addGap(18, 18, 18)
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeArquivo)
                    .addComponent(txtNomeArquivo))
                .addGap(18, 18, 18)
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutor)
                    .addComponent(txtAutor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panOnline.setBorder(javax.swing.BorderFactory.createTitledBorder("Online"));

        listOnline.setEnabled(false);
        jScrollPane2.setViewportView(listOnline);

        javax.swing.GroupLayout panOnlineLayout = new javax.swing.GroupLayout(panOnline);
        panOnline.setLayout(panOnlineLayout);
        panOnlineLayout.setHorizontalGroup(
            panOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );
        panOnlineLayout.setVerticalGroup(
            panOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        menArquivo.setText("Arquivo");

        itemNovoArquivo.setText("Novo");
        menArquivo.add(itemNovoArquivo);

        itemAbrirArquivo.setText("Abrir");
        menArquivo.add(itemAbrirArquivo);

        itemFechar.setText("Fechar");
        itemFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemFecharMouseReleased(evt);
            }
        });
        menArquivo.add(itemFechar);

        menu.add(menArquivo);

        menEditar.setText("Editar");

        itemSelecionarTudo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itemSelecionarTudo.setText("Selecionar tudo");
        itemSelecionarTudo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemSelecionarTudoMouseReleased(evt);
            }
        });
        menEditar.add(itemSelecionarTudo);

        itemDesfazer.setText("Desfazer");
        menEditar.add(itemDesfazer);

        itemRefazer.setText("Refazer");
        menEditar.add(itemRefazer);

        menu.add(menEditar);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void areaTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaTextoKeyReleased
//      SALVAR ARQUIVO
    }//GEN-LAST:event_areaTextoKeyReleased

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        fechar_arquivo();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void areaTextoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaTextoMouseReleased
        if (evt.isPopupTrigger()) {
            int x = this.getX();
            int y = this.getY();
            popup.show(this, evt.getXOnScreen() - x, evt.getYOnScreen() - y);
        }
    }//GEN-LAST:event_areaTextoMouseReleased

    private void itemPopupCopiarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemPopupCopiarMouseReleased
        tempText = areaTexto.getSelectedText();
    }//GEN-LAST:event_itemPopupCopiarMouseReleased

    private void itemPopupColarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemPopupColarMouseReleased
        areaTexto.insert(tempText, areaTexto.getCaretPosition());
    }//GEN-LAST:event_itemPopupColarMouseReleased

    private void itemSelecionarTudoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemSelecionarTudoMouseReleased
        areaTexto.selectAll();
    }//GEN-LAST:event_itemSelecionarTudoMouseReleased

    private void itemFecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemFecharMouseReleased
        fechar_arquivo();
    }//GEN-LAST:event_itemFecharMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JMenuItem itemAbrirArquivo;
    private javax.swing.JMenuItem itemDesfazer;
    private javax.swing.JMenuItem itemFechar;
    private javax.swing.JMenuItem itemNovoArquivo;
    private javax.swing.JMenuItem itemPopupColar;
    private javax.swing.JMenuItem itemPopupCopiar;
    private javax.swing.JMenuItem itemRefazer;
    private javax.swing.JMenuItem itemSelecionarTudo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblNomeArquivo;
    private javax.swing.JLabel lblUsuarioLogado;
    private javax.swing.JList<String> listOnline;
    private javax.swing.JMenu menArquivo;
    private javax.swing.JMenu menEditar;
    private javax.swing.JMenuBar menu;
    private javax.swing.JPanel panArquivo;
    private javax.swing.JPanel panButton;
    private javax.swing.JPanel panEditar;
    private javax.swing.JPanel panOnline;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JLabel txtAutor;
    private javax.swing.JLabel txtNomeArquivo;
    private javax.swing.JLabel txtUsuarioLogado;
    // End of variables declaration//GEN-END:variables

    private class ListUsuarioModel extends AbstractListModel<String> {

        private final List<String> lista = new ArrayList<>();

        @Override
        public int getSize() {
            return lista.size();
        }

        @Override
        public String getElementAt(int index) {
            return lista.get(index);
        }

        void addListAll(List list) {
            int tamanhoAntigo = getSize();
            lista.addAll(list);
            fireIntervalAdded(this, tamanhoAntigo, getSize() - 1);
        }

        void addElem(String elem) {
            lista.add(elem);
            int ultimoIndice = getSize() - 1;
            fireIntervalAdded(this, ultimoIndice, ultimoIndice);
        }

        void removeElem(int rowIndex) {
            lista.remove(rowIndex);
            fireIntervalRemoved(this, rowIndex, rowIndex);
        }

        void limpar() {
            lista.clear();
            fireIntervalRemoved(this, 0, getSize() - 1);
        }

        boolean isEmpty() {
            return lista.isEmpty();
        }

        void setValue(int row, String elem) {
            lista.set(row, elem);
            fireContentsChanged(this, row, row);
        }

        List<String> getAllExits() {
            return lista;
        }
    }

    private class UndoListener implements UndoableEditListener {

        @Override
        public void undoableEditHappened(UndoableEditEvent e) {
            undoManager.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }

    private class UndoAction extends AbstractAction {

        UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.isEnabled()) {
                undoManager.undo();
                undoAction.update();
                redoAction.update();
            }
        }

        void update() {
            if (undoManager.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undoManager.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    private class RedoAction extends AbstractAction {

        RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.isEnabled()) {
                undoManager.redo();
                undoAction.update();
                redoAction.update();
            }
        }

        void update() {
            if (undoManager.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undoManager.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
}
