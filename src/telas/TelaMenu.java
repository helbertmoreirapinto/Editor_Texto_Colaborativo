package telas;

import editor.Usuario;

/**
 *
 * @author helbert
 */
public class TelaMenu extends javax.swing.JFrame {
    
    private Usuario usuario;
    
    public TelaMenu(Usuario usuarioLogado) {
        initComponents();
        usuario = usuarioLogado;
        txtUsuario.setText(String.format("[%d] %s", usuarioLogado.getCodigo(), usuarioLogado.getNome()));
        if(!usuarioLogado.isAdm()){
            menUsuario.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        panArquivo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menArquivo = new javax.swing.JMenu();
        itemIncArquivo = new javax.swing.JMenuItem();
        itemConArquivo = new javax.swing.JMenuItem();
        menUsuario = new javax.swing.JMenu();
        itemIncUsuario = new javax.swing.JMenuItem();
        itemConUsuario = new javax.swing.JMenuItem();

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
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
        menArquivo.add(itemIncArquivo);

        itemConArquivo.setText("Consultar");
        menArquivo.add(itemConArquivo);

        jMenuBar1.add(menArquivo);

        menUsuario.setText("Usuario");

        itemIncUsuario.setText("Novo Usuario");
        menUsuario.add(itemIncUsuario);

        itemConUsuario.setText("Consultar");
        menUsuario.add(itemConUsuario);

        jMenuBar1.add(menUsuario);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemConArquivo;
    private javax.swing.JMenuItem itemConUsuario;
    private javax.swing.JMenuItem itemIncArquivo;
    private javax.swing.JMenuItem itemIncUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menArquivo;
    private javax.swing.JMenu menUsuario;
    private javax.swing.JPanel panArquivo;
    private javax.swing.JPanel panUsuario;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
