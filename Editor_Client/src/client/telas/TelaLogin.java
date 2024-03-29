package client.telas;

import client.Arquivo;
import client.Sessao;
import client.Usuario;
import client.connect.ArquivoConnect;
import client.crypt.Criptografia;
import client.connect.UsuarioConnect;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author helbert
 */
public class TelaLogin extends JFrame {

    private final Sessao sessao;
    private final UsuarioConnect conn_usu;
    private final ArquivoConnect conn_file;

    public TelaLogin() {
        initComponents();
        sessao = Sessao.getInstance();
        conn_usu = new UsuarioConnect();
        conn_file = new ArquivoConnect();
    }

    /**
     *
     */
    private void logar() {
        try {
            Usuario user;
            String login = txtLogin.getText();

            StringBuilder sb = new StringBuilder();
            for (char c : txtSenha.getPassword()) {
                sb.append(c);
            }
            String senha = Criptografia.criptografar(sb.toString());
            user = conn_usu.logar(login, senha);
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Login/Senha invalidos");
            } else {
                sessao.setUserLogado(user);
                iniciar_menu(user.getCodigo());
            }
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Erro crypto " + ex.getMessage());
        } catch (InterruptedException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Server offline");
            System.err.println("Server offline " + ex.getMessage());
        } finally {
            txtLogin.grabFocus();
        }

    }

    /**
     *
     * @param codigoUsuario
     * @throws IOException
     * @throws InterruptedException
     */
    private void iniciar_menu(int codigoUsuario) throws IOException, InterruptedException {
        List<Arquivo> arquivoList = conn_file.carregar_lista_arquivo(codigoUsuario);
        sessao.setArquivoList(arquivoList);
        TelaMenu tela = new TelaMenu();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panTxt = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JTextField();
        panButton = new javax.swing.JPanel();
        btnSair = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        panImage = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SYSTEM");
        setResizable(false);

        jLabel1.setText("Login:");

        jLabel2.setText("Senha:");

        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        txtLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoginFocusGained(evt);
            }
        });
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panTxtLayout = new javax.swing.GroupLayout(panTxt);
        panTxt.setLayout(panTxtLayout);
        panTxtLayout.setHorizontalGroup(
            panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txtLogin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panTxtLayout.setVerticalGroup(
            panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Cancel.gif"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Confirm.gif"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panButtonLayout = new javax.swing.GroupLayout(panButton);
        panButton.setLayout(panButtonLayout);
        panButtonLayout.setHorizontalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/login.png"))); // NOI18N

        javax.swing.GroupLayout panImageLayout = new javax.swing.GroupLayout(panImage);
        panImage.setLayout(panImageLayout);
        panImageLayout.setHorizontalGroup(
            panImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panImageLayout.setVerticalGroup(
            panImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(panTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
        txtSenha.selectAll();
    }//GEN-LAST:event_txtSenhaFocusGained

    private void txtLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoginFocusGained
        txtLogin.selectAll();
    }//GEN-LAST:event_txtLoginFocusGained

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        char key = evt.getKeyChar();
        if (key == '\n') {
            logar();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyPressed
        char key = evt.getKeyChar();
        if (key == '\n') {
            txtSenha.grabFocus();
        }
    }//GEN-LAST:event_txtLoginKeyPressed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel panButton;
    private javax.swing.JPanel panImage;
    private javax.swing.JPanel panTxt;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

}
