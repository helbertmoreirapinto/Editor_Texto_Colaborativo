package telas;

import editor.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author helbert
 */
public class TelaConUsuario extends javax.swing.JFrame {

    private static UsuarioTableModel model;

    public TelaConUsuario() {
        initComponents();
        model = new UsuarioTableModel();

        tabUsuario.setModel(model);
        txtPesquisar.requestFocus();

        tabUsuario.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabUsuario.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabUsuario.getColumnModel().getColumn(3).setPreferredWidth(55);
        tabUsuario.getColumnModel().getColumn(4).setPreferredWidth(55);

    }

    private void selecionar_usuario() {
        int reg = tabUsuario.getSelectedRow();
        if (reg >= 0) {
            consultar_usuario(model.getUsuario(reg));
        } else {
            JOptionPane.showMessageDialog(null, "Favor selecionar registro");
        }
    }

    private void listar_usuario() {
        String nomeUsuario = txtPesquisar.getText();
        ArrayList<Usuario> usuarioList = Usuario.consultar_usuario(nomeUsuario);
        model.limpar();
        model.addUsuarioList(usuarioList);
    }

    private void consultar_usuario(Usuario u) {
        TelaCadUsuario tela = new TelaCadUsuario(u);
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panPesquisar = new javax.swing.JPanel();
        lblPesquisar = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        btnPequisar = new javax.swing.JButton();
        panConsulta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabUsuario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPesquisar.setText("Nome:");

        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        btnPequisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.gif"))); // NOI18N
        btnPequisar.setText("Pesquisar");
        btnPequisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPequisarActionPerformed(evt);
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
                .addComponent(btnPequisar)
                .addContainerGap())
        );
        panPesquisarLayout.setVerticalGroup(
            panPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisar)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPequisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Login", "ADM", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabUsuarioMouseClicked(evt);
            }
        });
        tabUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabUsuarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabUsuario);
        if (tabUsuario.getColumnModel().getColumnCount() > 0) {
            tabUsuario.getColumnModel().getColumn(0).setResizable(false);
            tabUsuario.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabUsuario.getColumnModel().getColumn(1).setResizable(false);
            tabUsuario.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabUsuario.getColumnModel().getColumn(2).setResizable(false);
            tabUsuario.getColumnModel().getColumn(2).setPreferredWidth(30);
            tabUsuario.getColumnModel().getColumn(3).setResizable(false);
            tabUsuario.getColumnModel().getColumn(3).setPreferredWidth(30);
            tabUsuario.getColumnModel().getColumn(4).setResizable(false);
            tabUsuario.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        javax.swing.GroupLayout panConsultaLayout = new javax.swing.GroupLayout(panConsulta);
        panConsulta.setLayout(panConsultaLayout);
        panConsultaLayout.setHorizontalGroup(
            panConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        panConsultaLayout.setVerticalGroup(
            panConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Confirm.gif"))); // NOI18N
        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cancel.gif"))); // NOI18N
        jButton2.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPequisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPequisarActionPerformed
        listar_usuario();
    }//GEN-LAST:event_btnPequisarActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        char key = evt.getKeyChar();
        if (key == '\n') {
            listar_usuario();
        }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        selecionar_usuario();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabUsuarioMouseClicked
        if (evt.getClickCount() == 2) {
            selecionar_usuario();
        }
    }//GEN-LAST:event_tabUsuarioMouseClicked

    private void tabUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabUsuarioKeyPressed
        char key = evt.getKeyChar();
        if (key == '\n') {
            selecionar_usuario();
        }
    }//GEN-LAST:event_tabUsuarioKeyPressed

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
                new TelaConUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPequisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPesquisar;
    private javax.swing.JPanel panConsulta;
    private javax.swing.JPanel panPesquisar;
    private javax.swing.JTable tabUsuario;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    public class UsuarioTableModel extends AbstractTableModel {

        public static final int COL_CODIGO = 0;
        public static final int COL_NOME = 1;
        public static final int COL_LOGIN = 2;
        public static final int COL_ADM = 3;
        public static final int COL_ATIVO = 4;

        private final List<Usuario> linhas = new ArrayList<>();
        private final String[] colunas = {"Código", "Nome", "Login", "ADM", "Ativo"};
        private TableRowSorter sorter;

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

        public void sorter(JTable table, final JTextField filterText, UsuarioTableModel model) {
            sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
            table.setFillsViewportHeight(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            filterText.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    newFilter(filterText);
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    newFilter(filterText);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    newFilter(filterText);
                }
            });
        }

        private void newFilter(JTextField filterText) {
            RowFilter<UsuarioTableModel, Object> rf;
            try {
                rf = RowFilter.regexFilter(filterText.getText(), 1);
                sorter.setRowFilter(rf);
            } catch (PatternSyntaxException e) {
            }
        }

        public List<Usuario> getAllExits() {
            return linhas;
        }

        public String getIdIntoTheRow(JTable table) {
            int row = table.getSelectedRow();
            String pk = String.valueOf(table.getValueAt(row, 0));
            return pk;
        }

        public void setValue(int row, Usuario usu) {
            linhas.set(row, usu);
            fireTableRowsUpdated(row, row);
        }

        public void requestFocusForFirstLine(JTable table) {
            table.addRowSelectionInterval(0, 0);
        }
    }
}
