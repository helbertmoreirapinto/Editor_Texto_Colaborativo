package server.telas;

import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import javax.swing.JFrame;
import server.Usuario;
import server.connect.Connect;
import server.connect.ServerApplication;
import server.connect.ThreadEdit;
import server.model.UsuarioFileTableModel;

/**
 *
 * @author helbert
 */
public class TelaServer extends JFrame {

    private final UsuarioFileTableModel model;
    private ThreadEdit s_edit;
    private ServerApplication s_file;
    private ServerApplication s_user;

    public TelaServer() {
        initComponents();
        HashMap<Integer, Usuario> userList = Usuario.carregar_lista_usuario();
        model = new UsuarioFileTableModel();
        tabOnline.setModel(model);
        init();
        txtUsuariosLogados.setText(String.valueOf(model.getRowCount()));
        txtStatusServer.setText(getStatus());
        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent evt) {
//                if (JOptionPane.showConfirmDialog(null, "Deseja sair") == JOptionPane.OK_OPTION) {
//                    encerrar_aplicacoes();
//                }
//            }

            private void encerrar_aplicacoes() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     *
     */
    private void init() {
        try {
            ServerSocket server_file = new ServerSocket(Connect.PORT_FILE);
            s_file = new ServerApplication(server_file);
            Thread t_file = new Thread(s_file);
            t_file.start();

            ServerSocket server_user = new ServerSocket(Connect.PORT_USUARIO);
            s_user = new ServerApplication(server_user);
            Thread t_user = new Thread(s_user);
            t_user.start();

            ServerSocket serv = new ServerSocket(Connect.PORT_EDIT_FILE);
            s_edit = new ThreadEdit(serv, model, txtUsuariosLogados);
            Thread t_edit = new Thread(s_edit);
            t_edit.start();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     *
     */
    private void iniciar_server() {
        try {
            s_file.init_application();
            s_user.init_application();
            s_edit.init_application();
            txtStatusServer.setText(getStatus());
            btnIniciarServer.setText("Reiniciar");
            txtUsuariosLogados.setText(String.valueOf(model.getRowCount()));
            btnEncerrarServer.setEnabled(true);
        } catch (IOException ex) {
            System.err.println("Erro init application " + ex.getMessage());
        }

    }

    /**
     *
     */
    private void encerrar_server() {
        try {
            s_file.close_application();
            s_user.close_application();
            s_edit.close_application();
            txtStatusServer.setText(getStatus());
            btnIniciarServer.setText("Iniciar");
            model.limpar();
            txtUsuariosLogados.setText(String.valueOf(model.getRowCount()));
            btnEncerrarServer.setEnabled(false);
        } catch (IOException ex) {
            System.err.println("Erro close application " + ex.getMessage());
        }
    }

    private String getStatus() {
        return (s_file.isOnline()) ? "online" : "offline";
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(btnIniciarServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEncerrarServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarServerActionPerformed
        if (s_file.isOnline()) {
            encerrar_server();
        }
        iniciar_server();

    }//GEN-LAST:event_btnIniciarServerActionPerformed

    private void btnEncerrarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarServerActionPerformed
        encerrar_server();
    }//GEN-LAST:event_btnEncerrarServerActionPerformed

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
            java.util.logging.Logger.getLogger(TelaServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            TelaServer tela = new TelaServer();

            @Override
            public void run() {
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncerrarServer;
    private javax.swing.JButton btnIniciarServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panOnline;
    private javax.swing.JTable tabOnline;
    private javax.swing.JLabel txtStatusServer;
    private javax.swing.JLabel txtUsuariosLogados;
    // End of variables declaration//GEN-END:variables

}
