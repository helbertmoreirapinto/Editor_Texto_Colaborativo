/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.thread;

import client.Usuario;
import client.model.ListUsuarioModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helbert
 */
public class AtualizarLista implements Runnable {

    private final ListUsuarioModel model;
    private final ClienteServidor cs;

    public AtualizarLista(ListUsuarioModel model, ClienteServidor cs) {
        this.model = model;
        this.cs = cs;
    }

    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        List<AcessoCliente> acessoList;
        List<String> nomeUusuarioList = new ArrayList<>();
        while (!Thread.interrupted()) {
            model.limpar();
            acessoList = cs.getThreadList();
            for (AcessoCliente a : acessoList) {
                if (a != null && a.getUsuarioLogado() != null) {
                    nomeUusuarioList.add(a.getUsuarioLogado().getNome());
                }
            }
            if (nomeUusuarioList.size() > 0) {
                model.addListAll(nomeUusuarioList);
            }
            delay(1500);
        }
    }
}
