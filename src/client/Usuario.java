package client;

import editor.Pessoa;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Usuario extends Pessoa {

    private static final String NOME_ARQUIVO_USUARIOS = "USU_DB//USUARIOS.txt";
    private static int cod = 0;

    private int codigo;
    private String login;
    private String senha;
    private boolean ativo;
    private boolean adm;

    /**
     * Construtor da classe Usuario(String, String, String, boolean, boolean).
     *
     * @param nome
     * @param login
     * @param senha
     * @param adm
     * @param ativo
     */
    public Usuario(String nome, String login, String senha, boolean adm, boolean ativo) {
        super(nome);
        this.codigo = ++cod;
        this.login = login;
        this.senha = senha;
        this.adm = adm;
        this.ativo = ativo;
    }

    /* GETTER AND SETTER */
    public static void set_cod(int cod) {
        Usuario.cod = cod;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    /* STATIC METHODS */
    /**
     * Metodo responsavel em carregar para a memoria os usuarios salvaos no
     * arquivo de dados.
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HashMap<Integer, Usuario> carregar_lista_usuario() {
        HashMap<Integer, Usuario> usuarioList = new HashMap<>();
        String registro;
        String campo[];
        Usuario usuario;
        Usuario.set_cod(0);

        try (FileReader reader = new FileReader(NOME_ARQUIVO_USUARIOS);
                BufferedReader buffer = new BufferedReader(reader)) {
            while (buffer.ready()) {
                registro = buffer.readLine();
                campo = registro.split("#");
                usuario = new Usuario(campo[1], campo[2], campo[3], Boolean.parseBoolean(campo[4]),
                        Boolean.parseBoolean(campo[5]));
                usuarioList.put(usuario.getCodigo(), usuario);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return usuarioList;
    }

    /**
     * Colsulta os usuarios cadastrados filtrando de acordo com o campo de
     * pesquisa Aceita valores de nome ou codigo para o filtro.
     *
     * @param campoPesquisa
     * @return
     */
    public static ArrayList<Usuario> consultar_usuario(String campoPesquisa) {
        ArrayList<Usuario> listaRetorno = new ArrayList<>();

        HashMap<Integer, Usuario> usuarioList = carregar_lista_usuario();
        Usuario u;
        for (Map.Entry<Integer, Usuario> entry : usuarioList.entrySet()) {
            u = entry.getValue();
            if (u.getNome().toLowerCase().contains(campoPesquisa.toLowerCase()) || String.valueOf(u.getCodigo()).contains(campoPesquisa)) {
                listaRetorno.add(entry.getValue());
            }
        }

        return listaRetorno;
    }

    public static void alterar_usuario(Usuario usuarioAlterado) {
        try {
            HashMap<Integer, Usuario> usuarioList = carregar_lista_usuario();
            usuarioList.put(usuarioAlterado.getCodigo(), usuarioAlterado);

            boolean append_mode = false;
            Usuario usuario;

            try (FileWriter writer = new FileWriter(NOME_ARQUIVO_USUARIOS, append_mode); BufferedWriter buffer = new BufferedWriter(writer)) {
                for (Map.Entry<Integer, Usuario> elem : usuarioList.entrySet()) {
                    usuario = elem.getValue();
                    PrintWriter out = new PrintWriter(buffer);
                    out.printf("%d#%s#%s#%s#%s#%s#%n", usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
                            usuario.getSenha(), String.valueOf(usuario.isAdm()), String.valueOf(usuario.isAtivo()));
                    buffer.flush();
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void inserir_usuario(Usuario usuario) {
        boolean append_mode = true;
        try (FileWriter writer = new FileWriter(NOME_ARQUIVO_USUARIOS, append_mode); BufferedWriter buffer = new BufferedWriter(writer)) {

            PrintWriter out = new PrintWriter(buffer);
            out.printf("%d#%s#%s#%s#%s#%s#%n", usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
                    usuario.getSenha(), String.valueOf(usuario.isAdm()), String.valueOf(usuario.isAtivo()));
            buffer.flush();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public static Usuario get_usuario_pelo_codigo(int codigo) {
        HashMap<Integer, Usuario> usuarioList = carregar_lista_usuario();
        return usuarioList.get(codigo);
    }
}
