package telas;

import editor.Arquivo;
import editor.Usuario;
import editor.thread.EscreverArquivo;
import editor.thread.LerArquivo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author helbert
 */
public class TelaEditarArquivo extends javax.swing.JFrame {

    private final ListUsuarioModel model;
    private final EscreverArquivo escArq;
    private final LerArquivo lerArq;
    private final Arquivo arquivo;
    private final Usuario user;

    public TelaEditarArquivo(Usuario user, Arquivo arquivo) {
        initComponents();
        
        this.arquivo = arquivo;
        this.user = user;
        
        lerArq = new LerArquivo(areaTexto, arquivo);
        escArq = new EscreverArquivo(areaTexto, arquivo);
        Thread ler = new Thread(lerArq);
        Thread escrever = new Thread(escArq);
        ler.start();
        escrever.start();
        
        txtNomeArquivo.setText(this.arquivo.getNome());
        txtAutor.setText(Usuario.get_usuario_pelo_codigo(this.arquivo.getCodigoAutor()).getNome());
        model = new ListUsuarioModel();
        listOnline.setModel(model);
        model.addElem(user.getNome());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panEditar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        panButton = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        panArquivo = new javax.swing.JPanel();
        lblNomeArquivo = new javax.swing.JLabel();
        txtNomeArquivo = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        txtAutor = new javax.swing.JLabel();
        panOnline = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOnline = new javax.swing.JList<>();
        menu = new javax.swing.JMenuBar();
        menArquivo = new javax.swing.JMenu();
        itemNovoArquivo = new javax.swing.JMenuItem();
        itemAbrirArquivo = new javax.swing.JMenuItem();
        itemFechar = new javax.swing.JMenuItem();
        menEditar = new javax.swing.JMenu();
        itemSelecionarTudo = new javax.swing.JMenuItem();
        itemInvSelecao = new javax.swing.JMenuItem();
        itemDesfazer = new javax.swing.JMenuItem();
        itemRefazer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panEditar.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar"));

        areaTexto.setColumns(20);
        areaTexto.setRows(5);
        areaTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                areaTextoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                areaTextoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(areaTexto);

        javax.swing.GroupLayout panEditarLayout = new javax.swing.GroupLayout(panEditar);
        panEditar.setLayout(panEditarLayout);
        panEditarLayout.setHorizontalGroup(
            panEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panEditarLayout.setVerticalGroup(
            panEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        btnSalvar.setText("Salvar");

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panButtonLayout = new javax.swing.GroupLayout(panButton);
        panButton.setLayout(panButtonLayout);
        panButtonLayout.setHorizontalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panButtonLayout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnSair)
                .addGap(310, 310, 310))
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panArquivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivo"));

        lblNomeArquivo.setText("Nome:");

        lblAutor.setText("Autor:");

        javax.swing.GroupLayout panArquivoLayout = new javax.swing.GroupLayout(panArquivo);
        panArquivo.setLayout(panArquivoLayout);
        panArquivoLayout.setHorizontalGroup(
            panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panArquivoLayout.createSequentialGroup()
                        .addComponent(lblAutor)
                        .addGap(18, 18, 18)
                        .addComponent(txtAutor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panArquivoLayout.createSequentialGroup()
                        .addComponent(lblNomeArquivo)
                        .addGap(18, 18, 18)
                        .addComponent(txtNomeArquivo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panArquivoLayout.setVerticalGroup(
            panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeArquivo)
                    .addComponent(txtNomeArquivo))
                .addGap(18, 18, 18)
                .addGroup(panArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutor)
                    .addComponent(txtAutor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panOnline.setBorder(javax.swing.BorderFactory.createTitledBorder("Online"));

        listOnline.setEnabled(false);
        jScrollPane2.setViewportView(listOnline);

        javax.swing.GroupLayout panOnlineLayout = new javax.swing.GroupLayout(panOnline);
        panOnline.setLayout(panOnlineLayout);
        panOnlineLayout.setHorizontalGroup(
            panOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );
        panOnlineLayout.setVerticalGroup(
            panOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        menArquivo.setText("Arquivo");

        itemNovoArquivo.setText("Novo");
        menArquivo.add(itemNovoArquivo);

        itemAbrirArquivo.setText("Abrir");
        menArquivo.add(itemAbrirArquivo);

        itemFechar.setText("Fechar");
        menArquivo.add(itemFechar);

        menu.add(menArquivo);

        menEditar.setText("Editar");

        itemSelecionarTudo.setText("Selecionar tudo");
        menEditar.add(itemSelecionarTudo);

        itemInvSelecao.setText("Inverter seleção");
        menEditar.add(itemInvSelecao);

        itemDesfazer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        itemDesfazer.setText("Desfazer");
        menEditar.add(itemDesfazer);

        itemRefazer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        itemRefazer.setText("Refazer");
        menEditar.add(itemRefazer);

        menu.add(menEditar);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void areaTextoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaTextoKeyPressed

    }//GEN-LAST:event_areaTextoKeyPressed

    private void areaTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaTextoKeyReleased
        escArq.alterar_arquivo();
    }//GEN-LAST:event_areaTextoKeyReleased

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JMenuItem itemAbrirArquivo;
    private javax.swing.JMenuItem itemDesfazer;
    private javax.swing.JMenuItem itemFechar;
    private javax.swing.JMenuItem itemInvSelecao;
    private javax.swing.JMenuItem itemNovoArquivo;
    private javax.swing.JMenuItem itemRefazer;
    private javax.swing.JMenuItem itemSelecionarTudo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblNomeArquivo;
    private javax.swing.JList<String> listOnline;
    private javax.swing.JMenu menArquivo;
    private javax.swing.JMenu menEditar;
    private javax.swing.JMenuBar menu;
    private javax.swing.JPanel panArquivo;
    private javax.swing.JPanel panButton;
    private javax.swing.JPanel panEditar;
    private javax.swing.JPanel panOnline;
    private javax.swing.JLabel txtAutor;
    private javax.swing.JLabel txtNomeArquivo;
    // End of variables declaration//GEN-END:variables

    public class ListUsuarioModel extends AbstractListModel<String> {

        private final List<String> lista = new ArrayList<>();

        @Override
        public int getSize() {
            return lista.size();
        }

        @Override
        public String getElementAt(int index) {
            return lista.get(index);
        }

        public void addListAll(List list) {
            int tamanhoAntigo = getSize();
            lista.addAll(list);
            fireIntervalAdded(this, tamanhoAntigo, getSize() - 1);
        }

        public void addElem(String elem) {
            lista.add(elem);
            int ultimoIndice = getSize() - 1;
            fireIntervalAdded(this, ultimoIndice, ultimoIndice);
        }

        public void removeElem(int rowIndex) {
            lista.remove(rowIndex);
            fireIntervalRemoved(this, rowIndex, rowIndex);
        }

        public void limpar() {
            lista.clear();
            fireIntervalRemoved(this, 0, getSize() - 1);
        }

        public boolean isEmpty() {
            return lista.isEmpty();
        }

        public void setValue(int row, String elem) {
            lista.set(row, elem);
            fireContentsChanged(this, row, row);
        }

        public List<String> getAllExits() {
            return lista;
        }
    }
}
