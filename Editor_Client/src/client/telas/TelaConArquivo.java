package client.telas;

import client.Arquivo;
import client.Sessao;
import client.Usuario;
import client.connect.ArquivoConnect;
import client.model.ArquivoTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author helbert
 */
public class TelaConArquivo extends JFrame {

    private final ArquivoTableModel model;
    private List<Arquivo> arquivoList;
    private final ArquivoConnect conn;
    private final Sessao sessao;
    private final Usuario user;

    public TelaConArquivo() {
        initComponents();
        sessao = Sessao.getInstance();
        user = sessao.getUserLogado();
        conn = new ArquivoConnect();

        model = new ArquivoTableModel();
        tabArquivo.setModel(model);
        txtPesquisar.requestFocus();
        tabArquivo.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabArquivo.getColumnModel().getColumn(1).setPreferredWidth(100);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (JOptionPane.showConfirmDialog(null, "Deseja sair") == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void pesquisar_arquivos() {
        try {
            model.limpar();
            arquivoList = conn.carregar_lista_arquivo(user.getCodigo());
            for (Arquivo arq : arquivoList) {
                if (arq.getNome().contains(txtPesquisar.getText())) {
                    model.addArquivo(arq);
                }
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getMessage());
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
        if (verifica_server_online()) {
            TelaCadArquivo tela = new TelaCadArquivo(arquivo);
            tela.setVisible(true);
            tela.setLocationRelativeTo(null);
            this.dispose();
        }
    }

    private boolean verifica_server_online() {
        try {
            return conn.get_status_server();
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Server offline");
            System.exit(0);
        }
        return false;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        lblPesquisar.setText("Nome:");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Search.gif"))); // NOI18N
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

        btnSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Confirm.gif"))); // NOI18N
        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Cancel.gif"))); // NOI18N
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
                .addContainerGap()
                .addComponent(btnSelecionar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panPesquisar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (verifica_server_online()) {
            TelaMenu tela = new TelaMenu();
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
            this.dispose();
        }
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

}
