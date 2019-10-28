package telas;

import editor.Arquivo;
import editor.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author helbert
 */
public class TelaConArquivo extends javax.swing.JFrame {

    private final Usuario usuario;
    private final ArquivoTableModel model;
    private final List<Arquivo> arquivoList;

    public TelaConArquivo(int codigoUsuario) {
        initComponents();
        model = new ArquivoTableModel();
        usuario = Usuario.get_usuario_pelo_codigo(codigoUsuario);

        tabArquivo.setModel(model);
        txtPesquisar.requestFocus();

        tabArquivo.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabArquivo.getColumnModel().getColumn(1).setPreferredWidth(100);

        arquivoList = Arquivo.carregar_lista_arquivo(usuario);
    }

    private void pesquisar_arquivos() {
        model.limpar();
        for (Arquivo arq : arquivoList) {
            if (arq.getNome().contains(txtPesquisar.getText())) {
                model.addArquivo(arq);
            }
        }
    }

    private void selecionar_arquivo() {
        int index = tabArquivo.getSelectedRow();
        Arquivo a;
        if (index >= 0) {
            a = model.getArquivo(index);
            consultar_arquivo(a);
        }
    }

    private void consultar_arquivo(Arquivo arquivo) {
        TelaCadArquivo tela = new TelaCadArquivo(usuario.getCodigo(), arquivo);
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panPesquisar = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        lblPesquisar = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        panTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabArquivo = new javax.swing.JTable();
        panButton = new javax.swing.JPanel();
        btnSelecionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        lblPesquisar.setText("Nome:");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.gif"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panPesquisarLayout = new javax.swing.GroupLayout(panPesquisar);
        panPesquisar.setLayout(panPesquisarLayout);
        panPesquisarLayout.setHorizontalGroup(
            panPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPesquisar)
                .addGap(18, 18, 18)
                .addComponent(txtPesquisar)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisar)
                .addContainerGap())
        );
        panPesquisarLayout.setVerticalGroup(
            panPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisar)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabArquivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabArquivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabArquivoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabArquivo);
        if (tabArquivo.getColumnModel().getColumnCount() > 0) {
            tabArquivo.getColumnModel().getColumn(0).setResizable(false);
            tabArquivo.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout panTabLayout = new javax.swing.GroupLayout(panTab);
        panTab.setLayout(panTabLayout);
        panTabLayout.setHorizontalGroup(
            panTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panTabLayout.setVerticalGroup(
            panTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panButtonLayout = new javax.swing.GroupLayout(panButton);
        panButton.setLayout(panButtonLayout);
        panButtonLayout.setHorizontalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panButtonLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnSelecionar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panPesquisar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        pesquisar_arquivos();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        TelaMenu tela = new TelaMenu(usuario);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        char key = evt.getKeyChar();
        if (key == '\n') {
            pesquisar_arquivos();
        }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void tabArquivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabArquivoMouseClicked
        if (evt.getClickCount() == 2) {
            selecionar_arquivo();
        }
    }//GEN-LAST:event_tabArquivoMouseClicked

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        selecionar_arquivo();
    }//GEN-LAST:event_btnSelecionarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaConArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConArquivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaConArquivo(3).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPesquisar;
    private javax.swing.JPanel panButton;
    private javax.swing.JPanel panPesquisar;
    private javax.swing.JPanel panTab;
    private javax.swing.JTable tabArquivo;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    public class ArquivoTableModel extends AbstractTableModel {

        public static final int COL_NOME = 0;
        public static final int COL_AUTOR = 1;

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
}
