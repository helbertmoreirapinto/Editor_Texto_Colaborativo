package server.telas;

//import client.model.UsuarioTableModel;
import server.Usuario;
import server.ClientTableModel;
import javax.swing.JFrame;
import server.thread.AcessoCliente;
import server.thread.AtualizarLista;
import server.thread.ClienteServidor;

/**
 *
 * @author helbert
 */
public class TelaServer extends JFrame {

    private int usuariosLogados;
    private Thread server;
    private final ClienteServidor cs;
    private final ClientTableModel model;
    private final AtualizarLista tlista;

    public TelaServer() {
        initComponents();
        model = new ClientTableModel();
        cs = new ClienteServidor();
        tabOnline.setModel(model);
        tlista = new AtualizarLista(txtUsuariosLogados, model, cs);
        txtStatusServer.setText(getStatus());
        usuariosLogados = model.getRowCount();
        txtUsuariosLogados.setText(String.valueOf(usuariosLogados));
        inciar_threads();
    }

    private void inciar_threads() {
        server = new Thread(cs);
        server.start();
        Thread t = new Thread(tlista);
        t.start();
    }

    private void iniciar_server() {
        cs.setRodar(true);
        delay(50);
        txtStatusServer.setText(getStatus());
        btnIniciarServer.setText("Reiniciar");
        usuariosLogados = model.getRowCount();
        txtUsuariosLogados.setText(String.valueOf(usuariosLogados));
        btnAdicionarCliente.setEnabled(true);
        btnEncerrarServer.setEnabled(true);

    }

    private void encerrar_server() {
        try {
            for (AcessoCliente acesso : cs.getThreadList()) {
                acesso.stop();
                cs.getThreadList().remove(acesso);
            }
        } catch (Exception e) {
            System.err.println();
        }
        cs.setRodar(false);
        delay(50);
        txtStatusServer.setText(getStatus());
        btnIniciarServer.setText("Iniciar");
        model.limpar();
        usuariosLogados = model.getRowCount();
        txtUsuariosLogados.setText(String.valueOf(usuariosLogados));
        btnAdicionarCliente.setEnabled(false);
        btnEncerrarServer.setEnabled(false);
    }

    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private String getStatus() {
        return (cs.getStatus() == ClienteServidor.STATUS_ONLINE) ? "online" : "offline";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtStatusServer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuariosLogados = new javax.swing.JLabel();
        panOnline = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabOnline = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnIniciarServer = new javax.swing.JButton();
        btnEncerrarServer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAdicionarCliente = new javax.swing.JButton();
        btnEncerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel1.setText("Status Servidor:");

        txtStatusServer.setText("status_server");

        jLabel3.setText("Usuarios Logados:");

        txtUsuariosLogados.setText("users_logados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtStatusServer))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuariosLogados)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtStatusServer))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuariosLogados))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panOnline.setBorder(javax.swing.BorderFactory.createTitledBorder("Online"));

        tabOnline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(tabOnline);

        javax.swing.GroupLayout panOnlineLayout = new javax.swing.GroupLayout(panOnline);
        panOnline.setLayout(panOnlineLayout);
        panOnlineLayout.setHorizontalGroup(
            panOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panOnlineLayout.setVerticalGroup(
            panOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOnlineLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Servidor"));

        btnIniciarServer.setText("Iniciar");
        btnIniciarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarServerActionPerformed(evt);
            }
        });

        btnEncerrarServer.setText("Encerrar");
        btnEncerrarServer.setEnabled(false);
        btnEncerrarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarServer, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEncerrarServer, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarServer)
                .addGap(18, 18, 18)
                .addComponent(btnEncerrarServer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        btnAdicionarCliente.setText("Adicionar");
        btnAdicionarCliente.setEnabled(false);
        btnAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarClienteActionPerformed(evt);
            }
        });

        btnEncerrar.setText("Encerrar");
        btnEncerrar.setEnabled(false);
        btnEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(btnEncerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdicionarCliente)
                .addGap(18, 18, 18)
                .addComponent(btnEncerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarClienteActionPerformed
//        TelaLogin tela = new TelaLogin(cs);
//        tela.setLocationRelativeTo(null);
//        tela.setVisible(true);
//        btnEncerrar.setEnabled(true);
    }//GEN-LAST:event_btnAdicionarClienteActionPerformed

    private void btnIniciarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarServerActionPerformed
        if (cs.getStatus() == ClienteServidor.STATUS_ONLINE) {
            encerrar_server();
        }
        iniciar_server();

    }//GEN-LAST:event_btnIniciarServerActionPerformed

    private void btnEncerrarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarServerActionPerformed
        encerrar_server();
    }//GEN-LAST:event_btnEncerrarServerActionPerformed

    private void btnEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarActionPerformed
        int sel = tabOnline.getSelectedRow();
        Usuario u;
        if (sel >= 0) {
            u = model.getUsuario(sel);
            for (AcessoCliente acesso : cs.getThreadList()) {
                if (acesso.getUsuarioLogado().getCodigo() == u.getCodigo()) {
                    acesso.stop();
                    model.removeUsuario(sel);
                    cs.getThreadList().remove(acesso);
                    break;
                }
            }
        }
    }//GEN-LAST:event_btnEncerrarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            TelaServer tela = new TelaServer();

            public void run() {
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCliente;
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnEncerrarServer;
    private javax.swing.JButton btnIniciarServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panOnline;
    private javax.swing.JTable tabOnline;
    private javax.swing.JLabel txtStatusServer;
    private javax.swing.JLabel txtUsuariosLogados;
    // End of variables declaration//GEN-END:variables
}
