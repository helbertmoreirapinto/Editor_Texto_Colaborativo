package client.telas;

import client.Sessao;
import client.Usuario;
import client.connect.UsuarioConnect;
import client.crypt.Criptografia;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author helbert
 */
public class TelaCadUsuario extends JFrame {

    private Usuario userAlt;
    private final UsuarioConnect conn;
    private final Sessao sessao;

    public TelaCadUsuario(Usuario userAlterar) {
        initComponents();
        sessao = Sessao.getInstance();
        conn = new UsuarioConnect();
        this.userAlt = userAlterar;
        txtCod.setText("");
        if (userAlt != null) {
            txtCod.setText(String.valueOf(userAlt.getCodigo()));
            txtLogin.setText(userAlt.getLogin());
            txtNome.setText(userAlt.getNome());
            txtSenha1.setText("");
            txtSenha2.setText("");
            chkADM.setSelected(userAlt.isAdm());
            chkAtivo.setSelected(userAlt.isAtivo());
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (JOptionPane.showConfirmDialog(null, "Deseja sair") == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private boolean validar_campos() {
        if (txtNome.getText().trim().equals("") || txtLogin.getText().trim().equals("") || txtSenha1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor preencher todos os campos");
            return false;
        }
        if (!txtSenha1.getText().equals(txtSenha2.getText())) {
            JOptionPane.showMessageDialog(null, "Confirmação de senha invalida");
            return false;
        }

        return true;
    }

    private void incluir_usuario() throws IOException {
        try {
            userAlt = new Usuario(
                    -1,
                    txtNome.getText(),
                    txtLogin.getText(),
                    Criptografia.criptografar(txtSenha1.getText()),
                    chkADM.isSelected(), chkAtivo.isSelected());
            txtCod.setText(String.valueOf(userAlt.getCodigo()));
            verifica_server_online();
            conn.inserir_usuario(userAlt);
            JOptionPane.showMessageDialog(null, "Usuario incluido com sucesso");
            sessao.setUsuarioList(conn.carregar_lista_usuario());
        } catch (InterruptedException | NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void alterar_usuario() throws IOException {
        try {
            userAlt.setNome(txtNome.getText());
            userAlt.setLogin(txtLogin.getText());
            userAlt.setSenha(Criptografia.criptografar(txtSenha1.getText()));
            userAlt.setAdm(chkADM.isSelected());
            userAlt.setAtivo(chkAtivo.isSelected());
            conn.alterar_usuario(userAlt);
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");

        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private boolean verifica_server_online() {
        boolean online;
        try {
            online = conn.get_status_server();
        } catch (IOException | InterruptedException ex) {
            return false;
        }
        return online;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panTxt = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblConfSenha = new javax.swing.JLabel();
        chkADM = new javax.swing.JCheckBox();
        chkAtivo = new javax.swing.JCheckBox();
        txtCod = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        txtSenha1 = new javax.swing.JTextField();
        txtSenha2 = new javax.swing.JTextField();
        panButton = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNome.setText("Nome:");

        lblLogin.setText("Login:");

        lblSenha.setText("Senha:");

        lblConfSenha.setText("Confirmar Senha:");

        chkADM.setText("Usuario ADM");

        chkAtivo.setSelected(true);
        chkAtivo.setText("Usuario Ativo");

        txtCod.setText("Codigo");

        lblCod.setText("Código:");

        javax.swing.GroupLayout panTxtLayout = new javax.swing.GroupLayout(panTxt);
        panTxt.setLayout(panTxtLayout);
        panTxtLayout.setHorizontalGroup(
            panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkAtivo)
                    .addComponent(chkADM)
                    .addGroup(panTxtLayout.createSequentialGroup()
                        .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConfSenha)
                            .addComponent(lblSenha))
                        .addGap(31, 31, 31)
                        .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenha1)
                            .addComponent(txtSenha2)))
                    .addGroup(panTxtLayout.createSequentialGroup()
                        .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(lblCod)
                            .addComponent(lblLogin))
                        .addGap(78, 78, 78)
                        .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panTxtLayout.setVerticalGroup(
            panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCod)
                    .addComponent(txtCod))
                .addGap(18, 18, 18)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfSenha)
                    .addComponent(txtSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkADM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAtivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Confirm.gif"))); // NOI18N
        btnSalvar.setText("Confirmar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
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
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (validar_campos()) {
                if (userAlt != null) {
                    alterar_usuario();
                } else {
                    incluir_usuario();
                }
                if (verifica_server_online()) {
                    TelaConUsuario tela = new TelaConUsuario();
                    tela.setLocationRelativeTo(null);
                    tela.setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Dados invalidos");
            }
        } catch (HeadlessException | IOException ex) {
            System.err.println(ex.getMessage());
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (userAlt != null) {
            if (verifica_server_online()) {
                TelaConUsuario tela = new TelaConUsuario();
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        } else {
            if (verifica_server_online()) {
                TelaMenu tela = new TelaMenu();
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        }

        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkADM;
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblConfSenha;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JPanel panButton;
    private javax.swing.JPanel panTxt;
    private javax.swing.JLabel txtCod;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha1;
    private javax.swing.JTextField txtSenha2;
    // End of variables declaration//GEN-END:variables
}
