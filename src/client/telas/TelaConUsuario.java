package client.telas;

import client.Sessao;
import client.Usuario;
import client.model.UsuarioTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import server.thread.AcessoCliente;

/**
 *
 * @author helbert
 */
public class TelaConUsuario extends JFrame {

    private static UsuarioTableModel model;
    private final Sessao sessao;
    private final Usuario user;
    private final AcessoCliente acesso;

    public TelaConUsuario(int codigoUsuario) {
        initComponents();
        sessao = Sessao.getInstance();
        user = sessao.getUsuario(codigoUsuario);
        acesso = sessao.getAcesso(codigoUsuario);
        model = new UsuarioTableModel();

        tabUsuario.setModel(model);
        txtPesquisar.requestFocus();

        tabUsuario.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabUsuario.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabUsuario.getColumnModel().getColumn(3).setPreferredWidth(55);
        tabUsuario.getColumnModel().getColumn(4).setPreferredWidth(55);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (JOptionPane.showConfirmDialog(null, "Deseja sair") == JOptionPane.OK_OPTION) {
                    acesso.stop();
                }
            }
        });
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
        String campoPesquisa = txtPesquisar.getText();
        ArrayList<Usuario> lista = new ArrayList<>();
        verifica_server_online();
        HashMap<Integer, Usuario> usuarioList = acesso.carregar_lista_usuario();
        for (Map.Entry<Integer, Usuario> elem : usuarioList.entrySet()) {
            if (elem.getValue().getNome().contains(campoPesquisa) || String.valueOf(elem.getValue().getCodigo()).contains(campoPesquisa)) {
                lista.add(elem.getValue());
            }
        }
        model.limpar();
        model.addUsuarioList(lista);
    }

    private void consultar_usuario(Usuario u) {
        boolean s = verifica_server_online();
        if (s) {
            TelaCadUsuario tela = new TelaCadUsuario(user.getCodigo(), u);
            tela.setVisible(true);
            tela.setLocationRelativeTo(null);
            this.dispose();
        }
    }

    private boolean verifica_server_online() {
        if (!sessao.getThread(user.getCodigo()).isAlive()) {
            JOptionPane.showMessageDialog(null, "Usuario desconectado");
            TelaLogin tela = new TelaLogin(sessao.getServer());
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
            this.dispose();
            return false;
        }
        return true;
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
        btnSelecionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblPesquisar.setText("Nome:");

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
        });

        btnPequisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Search.gif"))); // NOI18N
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
                .addComponent(txtPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSelecionar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                    .addComponent(panConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
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

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed
        char key = evt.getKeyChar();
        if (key == '\n') {
            listar_usuario();
        }
    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        selecionar_usuario();
    }//GEN-LAST:event_btnSelecionarActionPerformed

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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        boolean s = verifica_server_online();
        if (s) {
            TelaMenu tela = new TelaMenu(user.getCodigo());
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPequisar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPesquisar;
    private javax.swing.JPanel panConsulta;
    private javax.swing.JPanel panPesquisar;
    private javax.swing.JTable tabUsuario;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

}
