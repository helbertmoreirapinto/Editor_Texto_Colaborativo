package client.telas;

import client.Arquivo;
import client.Usuario;
import client.model.ArquivoTableModel;
import java.util.List;

/**
 *
 * @author helbert
 */
public class TelaMenu extends javax.swing.JFrame {

    private final Usuario usuario;
    private final ArquivoTableModel model;

    public TelaMenu(Usuario usuarioLogado) {
        initComponents();
        usuario = usuarioLogado;
        model = new ArquivoTableModel();
        tabArquivo.setModel(model);
        txtUsuario.setText(String.format("[%d] %s", usuarioLogado.getCodigo(), usuarioLogado.getNome()));
        if (!usuarioLogado.isAdm()) {
            menUsuario.setEnabled(false);
        }
        List<Arquivo> listaArquivo = Arquivo.carregar_lista_arquivo(usuario);
        model.addArquivoList(listaArquivo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        panArquivo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabArquivo = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menArquivo = new javax.swing.JMenu();
        itemIncArquivo = new javax.swing.JMenuItem();
        itemConArquivo = new javax.swing.JMenuItem();
        menUsuario = new javax.swing.JMenu();
        itemIncUsuario = new javax.swing.JMenuItem();
        itemConUsuario = new javax.swing.JMenuItem();
        menLogoff = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblUsuario.setText("Usuário Logado:");

        javax.swing.GroupLayout panUsuarioLayout = new javax.swing.GroupLayout(panUsuario);
        panUsuario.setLayout(panUsuarioLayout);
        panUsuarioLayout.setHorizontalGroup(
            panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panUsuarioLayout.setVerticalGroup(
            panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panArquivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivos"));

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

        javax.swing.GroupLayout panArquivoLayout = new javax.swing.GroupLayout(panArquivo);
        panArquivo.setLayout(panArquivoLayout);
        panArquivoLayout.setHorizontalGroup(
            panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panArquivoLayout.setVerticalGroup(
            panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menArquivo.setText("Arquivo");

        itemIncArquivo.setText("Novo Arquivo");
        itemIncArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIncArquivoActionPerformed(evt);
            }
        });
        menArquivo.add(itemIncArquivo);

        itemConArquivo.setText("Consultar");
        itemConArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConArquivoActionPerformed(evt);
            }
        });
        menArquivo.add(itemConArquivo);

        jMenuBar1.add(menArquivo);

        menUsuario.setText("Usuario");

        itemIncUsuario.setText("Novo Usuario");
        itemIncUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIncUsuarioActionPerformed(evt);
            }
        });
        menUsuario.add(itemIncUsuario);

        itemConUsuario.setText("Consultar");
        itemConUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConUsuarioActionPerformed(evt);
            }
        });
        menUsuario.add(itemConUsuario);

        jMenuBar1.add(menUsuario);

        menLogoff.setText("Logoff");
        menLogoff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menLogoffMouseClicked(evt);
            }
        });
        jMenuBar1.add(menLogoff);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemIncUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIncUsuarioActionPerformed
        TelaCadUsuario tela = new TelaCadUsuario(usuario, null);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemIncUsuarioActionPerformed

    private void itemConUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConUsuarioActionPerformed
        TelaConUsuario tela = new TelaConUsuario(usuario);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemConUsuarioActionPerformed

    private void itemIncArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIncArquivoActionPerformed
        TelaCadArquivo tela = new TelaCadArquivo(usuario.getCodigo(), null);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemIncArquivoActionPerformed

    private void itemConArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConArquivoActionPerformed
        TelaConArquivo tela = new TelaConArquivo(usuario.getCodigo());
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemConArquivoActionPerformed

    private void menLogoffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menLogoffMouseClicked
        TelaLogin tela = new TelaLogin();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menLogoffMouseClicked

    private void tabArquivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabArquivoMouseClicked
        Arquivo arquivo;
        if (evt.getClickCount() == 2 && tabArquivo.getSelectedRow() >= 0) {
            arquivo = model.getArquivo(tabArquivo.getSelectedRow());
            TelaEditarArquivo tela = new TelaEditarArquivo(usuario, arquivo);
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_tabArquivoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemConArquivo;
    private javax.swing.JMenuItem itemConUsuario;
    private javax.swing.JMenuItem itemIncArquivo;
    private javax.swing.JMenuItem itemIncUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menArquivo;
    private javax.swing.JMenu menLogoff;
    private javax.swing.JMenu menUsuario;
    private javax.swing.JPanel panArquivo;
    private javax.swing.JPanel panUsuario;
    private javax.swing.JTable tabArquivo;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
 
}
