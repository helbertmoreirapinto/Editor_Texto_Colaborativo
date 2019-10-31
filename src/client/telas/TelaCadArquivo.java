package client.telas;

import client.Arquivo;
import client.Usuario;
import client.model.ListUsuarioModel;
import editor.exc.ArquivoDuplicadoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author helbert
 */
public class TelaCadArquivo extends javax.swing.JFrame {

    private final ListUsuarioModel model1;
    private final ListUsuarioModel model2;

    private final Usuario user;
    private final HashMap<Integer, Usuario> listaUsuario;
    private final Arquivo arquivo;

    public TelaCadArquivo(int codigoUsuario, Arquivo arquivo) {
        initComponents();
        this.arquivo = arquivo;
        user = Usuario.get_usuario_pelo_codigo(codigoUsuario);
        model1 = new ListUsuarioModel();
        model2 = new ListUsuarioModel();
        listUsuario.setModel(model1);
        listSelecionados.setModel(model2);
        listaUsuario = Usuario.carregar_lista_usuario();

        if (arquivo != null) {
            txtNomeArquivo.setText(arquivo.getNome());
            txtAutor.setText(Usuario.get_usuario_pelo_codigo(arquivo.getCodigoAutor()).getNome());
            listaUsuario.remove(arquivo.getCodigoAutor());
            for (Map.Entry<Integer, Usuario> elem : listaUsuario.entrySet()) {
                if (arquivo.getCodigoUsuarioAcesso().contains(elem.getKey())) {
                    model2.addElem(elem.getValue().getNome());
                } else {
                    model1.addElem(elem.getValue().getNome());
                }
            }
        } else {
            txtAutor.setText(Usuario.get_usuario_pelo_codigo(user.getCodigo()).getNome());
            listaUsuario.remove(codigoUsuario);

            for (Map.Entry<Integer, Usuario> elem : listaUsuario.entrySet()) {
                model1.addElem(elem.getValue().getNome());
            }
        }

    }

    private void marcar_usuario() {
        int i = listUsuario.getSelectedIndex();
        if (i >= 0) {
            String x = listUsuario.getSelectedValue();
            model1.removeElem(i);
            model2.addElem(x);
        }
    }

    private void desmarcar_usuario() {
        int i = listSelecionados.getSelectedIndex();
        if (i >= 0) {
            String x = listSelecionados.getSelectedValue();
            model2.removeElem(i);
            model1.addElem(x);
        }
    }

    private ArrayList<Integer> lista_codigos_usuarios_selecionados() {
        ArrayList<Integer> listaCodigos = new ArrayList<>();
        List<String> aux = model2.getAllExits();
        for (Map.Entry<Integer, Usuario> elem : listaUsuario.entrySet()) {
            if (aux.contains(elem.getValue().getNome())) {
                listaCodigos.add(elem.getKey());
            }
        }
        return listaCodigos;
    }

    private void alterar_arquivo() {
        List<Integer> selecionados = lista_codigos_usuarios_selecionados();
        arquivo.setUsuarioAcessoAdm(selecionados);
        arquivo.updateFileData();
        arquivo.rename(txtNomeArquivo.getText());
        JOptionPane.showMessageDialog(null, "Arquivo alterado com sucesso");
    }

    /**
     * @see verificar se arquivo existe na criacao
     *
     */
    private void incluir_arquivo() {
        List<Integer> selecionados = lista_codigos_usuarios_selecionados();
        Arquivo arq = new Arquivo(txtNomeArquivo.getText(), user.getCodigo(), selecionados);
        try {

            arq.createFile();

            JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ArquivoDuplicadoException ex) {
            int resp = JOptionPane.showConfirmDialog(null, "Arquivo já exsite. Substrituir?");
            if (resp == JOptionPane.OK_OPTION) {
                try {
                    arq.replace();
                    JOptionPane.showMessageDialog(null, "Arquivo atualizado com sucesso");
                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }
            System.err.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panLists = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listUsuario = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSelecionados = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        btnMarcar = new javax.swing.JButton();
        btnDesmarcar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panTxt = new javax.swing.JPanel();
        lblNomeArquivo = new javax.swing.JLabel();
        txtNomeArquivo = new javax.swing.JTextField();
        lblAutor = new javax.swing.JLabel();
        txtAutor = new javax.swing.JLabel();
        panButton = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        listUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listUsuario);

        listSelecionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSelecionadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listSelecionados);

        btnMarcar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Right.gif"))); // NOI18N
        btnMarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarActionPerformed(evt);
            }
        });

        btnDesmarcar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Left.gif"))); // NOI18N
        btnDesmarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesmarcarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMarcar)
                    .addComponent(btnDesmarcar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMarcar)
                .addGap(18, 18, 18)
                .addComponent(btnDesmarcar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Todos Usaurios:");

        jLabel2.setText("Usuarios COM acesso ao arquivo:");

        javax.swing.GroupLayout panListsLayout = new javax.swing.GroupLayout(panLists);
        panLists.setLayout(panListsLayout);
        panListsLayout.setHorizontalGroup(
            panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panListsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panListsLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panListsLayout.setVerticalGroup(
            panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panListsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panListsLayout.createSequentialGroup()
                        .addGroup(panListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panListsLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        lblNomeArquivo.setText("Nome:");

        lblAutor.setText("Autor:");

        javax.swing.GroupLayout panTxtLayout = new javax.swing.GroupLayout(panTxt);
        panTxt.setLayout(panTxtLayout);
        panTxtLayout.setHorizontalGroup(
            panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panTxtLayout.createSequentialGroup()
                        .addComponent(lblNomeArquivo)
                        .addGap(18, 18, 18)
                        .addComponent(txtNomeArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panTxtLayout.createSequentialGroup()
                        .addComponent(lblAutor)
                        .addGap(18, 18, 18)
                        .addComponent(txtAutor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panTxtLayout.setVerticalGroup(
            panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeArquivo)
                    .addComponent(txtNomeArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutor)
                    .addComponent(txtAutor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/images/Confirm.gif"))); // NOI18N
        btnSalvar.setText("Salvar");
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
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(155, 155, 155)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panLists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panLists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarActionPerformed
        marcar_usuario();
    }//GEN-LAST:event_btnMarcarActionPerformed

    private void listUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listUsuarioMouseClicked
        if (evt.getClickCount() == 2) {
            marcar_usuario();
        }
    }//GEN-LAST:event_listUsuarioMouseClicked

    private void btnDesmarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesmarcarActionPerformed
        desmarcar_usuario();
    }//GEN-LAST:event_btnDesmarcarActionPerformed

    private void listSelecionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSelecionadosMouseClicked
        if (evt.getClickCount() == 2) {
            desmarcar_usuario();
        }
    }//GEN-LAST:event_listSelecionadosMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!txtNomeArquivo.getText().trim().equals("")) {
            if (arquivo != null) {
                alterar_arquivo();
            } else {
                incluir_arquivo();
            }
            TelaConArquivo tela = new TelaConArquivo(user.getCodigo());
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Nome do arquivo invalido");
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (arquivo != null) {
            TelaConArquivo tela = new TelaConArquivo(user.getCodigo());
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);

        } else {
            TelaMenu tela = new TelaMenu(user);
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesmarcar;
    private javax.swing.JButton btnMarcar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblNomeArquivo;
    private javax.swing.JList<String> listSelecionados;
    private javax.swing.JList<String> listUsuario;
    private javax.swing.JPanel panButton;
    private javax.swing.JPanel panLists;
    private javax.swing.JPanel panTxt;
    private javax.swing.JLabel txtAutor;
    private javax.swing.JTextField txtNomeArquivo;
    // End of variables declaration//GEN-END:variables

}
