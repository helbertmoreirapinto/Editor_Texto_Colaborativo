package editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

import editor.crypt.Criptografia;
import editor.exc.ArquivoDuplicadoException;
import editor.exc.CriarDiretorioException;
import editor.exc.LoginInvalidoException;
import editor.exc.NewFileException;
import editor.exc.UsuarioInativoException;

public class Principal {

    private static final String NOME_ARQUIVO_USUARIOS = "USU_DB//USUARIOS.txt";
    private static final int COD_ELEM_AUTOR = 0;

    private static final String SIM = "s";
    private static final String NAO = "n";

    private static final int MENU_GER_ARQ = 1;
    private static final int MENU_GER_USU = 2;
    private static final int MENU_SAIR = 0;

    private static final int MENU_ARQ_NOVO = 1;
    private static final int MENU_ARQ_CON = 2;
    private static final int MENU_ARQ_EDIT = 1;
    private static final int MENU_ARQ_GERE = 2;
    private static final int MENU_ARQ_EXC = 3;

    private static final int MENU_ARQ_VOLT = 0;

    private static final int MENU_EDIT_TERMINAR_EDICAO = 0;
    private static final int MENU_EDIT_INS = 1;
    private static final int MENU_EDITAR_LINHA = 2;
    private static final int MENU_APAGAR_LINHA = 3;
    private static final int MENU_EDIT_DESFAZER = 4;
    private static final int MENU_EDIT_REFAZER = 5;

    private static final int MENU_USU_NOVO = 1;
    private static final int MENU_USU_CONS = 2;
    private static final int MENU_USU_VOLT = 0;

    public static void main(String[] args) {
        int opcaoMenu = -1;
        Usuario user = null;

        try (Scanner entrada = new Scanner(System.in)) {
            do {
                try {

                    if (user == null) {
                        user = logar(entrada);
                        System.out
                                .println(String.format("Usuario Logado: [%d] %s\n", user.getCodigo(), user.getNome()));
                    }

//                  Menu Principal
                    System.out.println("--- MENU ---");
                    System.out.println("1 - Gerenciar Arquivo");
                    if (user.isAdm()) {
                        System.out.println("2 - Gerenciar Usuario");
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Selecione: ");
                    opcaoMenu = entrada.nextInt();
                    System.out.print("\n");
                    entrada.nextLine();

                    switch (opcaoMenu) {
                        case MENU_GER_ARQ:
                            gerenciar_arquivo(entrada, user);
                            break;
                        case MENU_GER_USU:
                            if (user.isAdm()) {
                                gerenciar_usuario(entrada);
                            }
                            break;
                        case MENU_SAIR:
                            System.out.println("BYE!");
                            break;
                        default:
                            System.err.println("Opcao invalida!");
                    }
                } catch (LoginInvalidoException | CriarDiretorioException | UsuarioInativoException | IOException
                        | NoSuchAlgorithmException err) {
                    System.err.println(err.getMessage());
                } catch (InputMismatchException err) {
                    entrada.nextLine();
                    System.err.println("Opcao invalida!");
                }
            } while (opcaoMenu != MENU_SAIR);
        }
    }

    /*
    * ---------------------------- USUARIOS ----------------------------
     */
    private static HashMap<Integer, Usuario> carregar_lista_usuario() throws FileNotFoundException, IOException {
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
        }
        return usuarioList;
    }

    private static Usuario logar(Scanner s) throws NoSuchAlgorithmException, LoginInvalidoException,
            FileNotFoundException, IOException, UsuarioInativoException {
        String login, senha;
        HashMap<Integer, Usuario> usuarioList = carregar_lista_usuario();
        System.out.println("--- LOGIN ---");
        System.out.print("Usuario: ");
        login = s.nextLine();
        System.out.print("Senha: ");
        senha = Criptografia.criptografar(s.nextLine());

        for (Entry<Integer, Usuario> entrySet : usuarioList.entrySet()) {
            if (login.equalsIgnoreCase(entrySet.getValue().getLogin())
                    && senha.equals(entrySet.getValue().getSenha())) {
                if (!entrySet.getValue().isAtivo()) {
                    throw new UsuarioInativoException("Usuario inativo!");
                }
                return entrySet.getValue();
            }
        }
        throw new LoginInvalidoException("Login/Senha invalidos");
    }

    private static void gerenciar_usuario(Scanner s) throws NoSuchAlgorithmException, IOException {
        HashMap<Integer, Usuario> usuarioList;
        int opcaoMenu = -1;
        Usuario u;
        do {
            try {

//		Menu Usuario
                System.out.println("--- MENU USUARIO ---");
                System.out.println("1 - Novo Usuario");
                System.out.println("2 - Consultar Usuario");
                System.out.println("0 - Voltar");
                System.out.print("Selecione: ");
                opcaoMenu = s.nextInt();
                System.out.print("\n");
                s.nextLine();

                switch (opcaoMenu) {
                    case MENU_USU_NOVO:
                        inserir_usuario(s);
                        System.out.println("Usuario inserido com sucesso!\n");
                        break;
                    case MENU_USU_CONS:
                        usuarioList = carregar_lista_usuario();
                        u = consultar_usuario(s, usuarioList);
                        if (u != null) {
                            alterar_usuario(s, u, usuarioList);
                        }
                        break;
                    case MENU_USU_VOLT:
                        break;
                    default:
                        System.err.println("Opcao invalida!");
                }

            } catch (InputMismatchException es) {
                s.nextLine();
                System.err.println("Opcao invalida!");
            }
        } while (opcaoMenu != MENU_USU_VOLT);

    }

    private static void inserir_usuario(Scanner s) throws IOException, NoSuchAlgorithmException {
        Usuario usuario;
        String nome, login, senha;
        boolean adm, ativo = true, append_mode = true;

        System.out.println("--- NOVO USUARIO --- ");
        System.out.print("Nome: ");
        nome = s.nextLine();
        System.out.print("Login: ");
        login = s.nextLine();
        System.out.print("Senha: ");
        senha = Criptografia.criptografar(s.nextLine());
        System.out.print("Usuario ADM? ");
        adm = s.nextBoolean();
        s.nextLine();
        usuario = new Usuario(nome, login, senha, adm, ativo);

        try (FileWriter writer = new FileWriter(NOME_ARQUIVO_USUARIOS, append_mode);
                BufferedWriter buffer = new BufferedWriter(writer)) {

            PrintWriter out = new PrintWriter(buffer);
            out.printf("%d#%s#%s#%s#%s#%s#%n", usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
                    usuario.getSenha(), String.valueOf(usuario.isAdm()), String.valueOf(usuario.isAtivo()));
            buffer.flush();
        }
    }

    private static Usuario consultar_usuario(Scanner s, HashMap<Integer, Usuario> usuarioList)
            throws IOException, NoSuchAlgorithmException {
        int opc = -1;
        Usuario usu = null;
        do {
            try {

//		Menu Consulta Usuario
                System.out.println("--- SELECIONAR USUARIO ---");
                System.out.println("COD\t|NOME\t\t\t\t|NICK\t\t|ATIVO\t|");
                usuarioList.entrySet().forEach((entryset) -> {
                    System.out.println(String.format("%03d\t|%-31s|%-15s|%s\t|", entryset.getValue().getCodigo(),
                            entryset.getValue().getNome(), entryset.getValue().getLogin(),
                            String.valueOf(entryset.getValue().isAtivo())));
                });
                System.out.println("0 - Voltar");
                System.out.print("Selecione: ");
                opc = s.nextInt();
                s.nextLine();
                System.out.print("\n");

                if (opc < 0 || opc > usuarioList.size()) {
                    throw new InputMismatchException();
                }

                if (opc == MENU_USU_VOLT) {
                    return usu;
                }

                return usuarioList.get(opc);
            } catch (InputMismatchException e) {
                System.err.println("Opcao invalida!");
                s.nextLine();
            }
        } while (opc != MENU_USU_VOLT);

        return usu;
    }

    private static void alterar_usuario(Scanner s, Usuario usuario, HashMap<Integer, Usuario> usuarioList)
            throws NoSuchAlgorithmException, IOException {
        String val = "";
        do {
            try {
                System.out.println("--- ALTERAR USUARIO ---");
                System.out.println("Usuario: " + usuario.getNome());
                System.out.print("Deseja alterar dados do usuario? [S/n] ");
                val = s.nextLine();
                System.out.print("\n");
                if (val.equalsIgnoreCase(NAO)) {
                    return;
                }
            } catch (InputMismatchException err) {
                System.err.println("Valor invalido!");
                s.nextLine();
            }
        } while (!val.equalsIgnoreCase(SIM));

        try {
            System.out.print(String.format("Nome [%s]: ", usuario.getNome()));
            usuario.setNome(s.nextLine());
            System.out.print(String.format("Login [%s]: ", usuario.getLogin()));
            usuario.setLogin(s.nextLine());
            System.out.print(String.format("Senha: "));
            usuario.setSenha(Criptografia.criptografar(s.nextLine()));
            System.out.print(String.format("Usuario ADM? [%s]: ", usuario.isAdm()));
            usuario.setAdm(s.nextBoolean());
            System.out.print(String.format("Ativo? [%s]: ", usuario.isAtivo()));
            usuario.setAtivo(s.nextBoolean());
            s.nextLine();

            boolean append_mode = false;
            try (FileWriter writer = new FileWriter(NOME_ARQUIVO_USUARIOS, append_mode);
                    BufferedWriter buffer = new BufferedWriter(writer)) {
                Usuario user;
                for (Entry<Integer, Usuario> entrySet : usuarioList.entrySet()) {
                    user = entrySet.getValue();
                    PrintWriter out = new PrintWriter(buffer);
                    out.printf("%d#%s#%s#%s#%s#%s#%n", user.getCodigo(), user.getNome(), user.getLogin(),
                            user.getSenha(), String.valueOf(user.isAdm()), String.valueOf(user.isAtivo()));
                }
                buffer.flush();
            }

            System.out.println("Usuario alterado com sucesso!");

        } catch (InputMismatchException err) {
            System.err.println("Valor invalido!");
            s.nextLine();
        }
        System.out.print("\n");
    }

    /*
    * ---------------------------- ARQUIVOS ----------------------------
     */
    private static boolean configurar_acesso_arquivo(Scanner s) {
        String opc;
        do {
            System.out.println("--- ARQUIVO ---");
            System.out.println("Deseja configurar acessos ao arquivo?[S/n]");
            opc = s.nextLine();
            if (opc.equalsIgnoreCase(SIM)) {
                return true;
            }
        } while (!opc.equalsIgnoreCase(NAO));
        return false;
    }

    private static ArrayList<Integer> selecionar_usuario_acesso(Scanner s) throws FileNotFoundException, IOException {
        ArrayList<Integer> listaRetorno = new ArrayList<>();
        String opc;
        String[] vet;
        HashMap<Integer, Usuario> usuarioList = carregar_lista_usuario();
        System.out.println("--- SELECIONAR USUARIO ---");
        System.out.println("Selecione os usuarios com acesso ADM ao arquivo: ");
        usuarioList.entrySet().forEach((Entry<Integer, Usuario> entrySet) -> {
            System.out.println(entrySet.getKey() + " - " + entrySet.getValue().getNome());
        });
        System.out.println("0 - Voltar");
        System.out.println("Selecione [utilize ; como separador]:");
        opc = s.nextLine();

        if (opc.equals("") || opc.equals(String.valueOf(MENU_ARQ_VOLT))) {
            System.out.print("\n");
            return listaRetorno;
        }

        vet = opc.split(";");
        for (String cod : vet) {
            listaRetorno.add(Integer.parseInt(cod));
        }
        return listaRetorno;
    }

    private static void editar_arquivo(Scanner s, Arquivo arq) throws IOException {
        ArrayList<ArrayList<String>> historico_alteracao = new ArrayList<>();
        ArrayList<String> aux;
        ArrayList<String> texto;
        int opc = -1;
        int ind_alteracao = 0;
        int lin;

        texto = arq.getTexto();
        aux = new ArrayList<>();
        aux.addAll(texto);
        historico_alteracao.add(aux);

        do {
            try {

//		Menu editar
                System.out.println(String.format("\nNome arquivo: %s", arq.getNome()));
                System.out.println("---------------------------------------");
                for (int i = 0; i < texto.size(); i++) {
                    System.out.println(String.format("%02d | %s", i, texto.get(i)));
                }
                System.out.println("---------------------------------------");

                System.out.println("-- EDITAR ARQUIVO --");
                System.out.println("1 - Inserir texto");
                System.out.println("2 - Editar linha");
                if (texto.size() > 0) {
                    System.out.println("3 - Apagar linha");
                }
                if (ind_alteracao > 0) {
                    System.out.println("4 - Desfazer");
                }
                if (ind_alteracao < (historico_alteracao.size() - 1)) {
                    System.out.println("5 - Refazer");
                }
                System.out.println("0 - Terminar edicao");
                System.out.print("Selecione: ");
                opc = s.nextInt();
                s.nextLine();

                switch (opc) {
                    case MENU_EDIT_INS:
                        System.out.print("Texto: ");
                        texto.add(s.nextLine());
                        break;
                    case MENU_EDIT_DESFAZER:
                        if (ind_alteracao > 0) {
                            texto = historico_alteracao.get(--ind_alteracao);
                        }
                        break;
                    case MENU_EDIT_REFAZER:
                        if (ind_alteracao < (historico_alteracao.size() - 1)) {
                            texto = historico_alteracao.get(++ind_alteracao);
                        }
                        break;
                    case MENU_APAGAR_LINHA:
                        if (texto.size() > 0) {
                            lin = selecionar_linha(s, texto.size());
                            texto.remove(lin);
                        }
                        break;
                    case MENU_EDITAR_LINHA:
                        lin = selecionar_linha(s, texto.size());
                        System.out.print("Texto: ");
                        texto.add(lin, s.nextLine());
                        break;
                    case MENU_EDIT_TERMINAR_EDICAO:
                        arq.editar(texto);
                        System.out.print("\n");
                        continue;
                    default:
                        System.err.println("Opcao invalida!");
                }

                if (opc == MENU_EDIT_INS || opc == MENU_APAGAR_LINHA || opc == MENU_EDITAR_LINHA) {
                    for (int i = ind_alteracao; i < historico_alteracao.size() - 1; i++) {
                        historico_alteracao.remove(i);
                    }
                    aux = new ArrayList<>();
                    aux.addAll(texto);
                    historico_alteracao.add(aux);
                    ind_alteracao++;
                }

            } catch (InputMismatchException err) {
                System.err.println("Opcao invalida!");
                s.nextLine();
            }
        } while (opc != MENU_EDIT_TERMINAR_EDICAO);
    }

    private static int selecionar_linha(Scanner s, int max) throws InputMismatchException {
        int lin;
        System.out.print("Linha: ");
        lin = s.nextInt();
        if (lin < 0 || lin > max - 1) {
            throw new InputMismatchException("Valor invalido");
        }
        s.nextLine();
        return lin;
    }

    private static ArrayList<Arquivo> carregar_lista_arquivo(Usuario user) throws FileNotFoundException, IOException {
        ArrayList<Arquivo> listaRetorno = new ArrayList<>();
        File diretorioArquivos = new File(Arquivo.DIR_ARQUIVOS);
        File[] arquivoList = diretorioArquivos.listFiles();
        int codigoAutor;
        String nomeArquivo;
        ArrayList<Integer> usuarioAcesso;
        Arquivo arquivo;

        if (arquivoList != null) {
            for (File fileD : arquivoList) {
                if (fileD.getName().contains(Arquivo.FILE_DATA)) {
                    nomeArquivo = fileD.getName().substring(0, fileD.getName().indexOf("."));
                    usuarioAcesso = new ArrayList<>();
                    try (FileReader reader = new FileReader(fileD);
                            BufferedReader buffer = new BufferedReader(reader)) {
                        while (buffer.ready()) {
                            usuarioAcesso.add(Integer.parseInt(buffer.readLine()));
                        }
                        if (usuarioAcesso.contains(user.getCodigo())) {
                            codigoAutor = usuarioAcesso.get(COD_ELEM_AUTOR);
                            arquivo = new Arquivo(nomeArquivo, codigoAutor, usuarioAcesso);
                            for (File file : arquivoList) {
                                if (file.getName().equals(String.format("%s%s", nomeArquivo, Arquivo.FILE_TEXT))) {
                                    arquivo.setFile(file);
                                    break;
                                }
                            }
                            arquivo.setFileData(fileD);
                            listaRetorno.add(arquivo);
                        }
                    }
                }
            }
        }

        return listaRetorno;
    }

    private static void gerenciar_arquivo(Scanner s, Usuario user) throws CriarDiretorioException, IOException {
        int opcaoMenu = -1;
        Arquivo arquivo;
        do {
            try {

//		Menu de Arquivo
                System.out.println("--- MENU ARQUIVO ---");
                System.out.println("1 - Novo Arquivo");
                System.out.println("2 - Consltar Arquivo");
                System.out.println("0 - Voltar");
                System.out.print("Selecione: ");
                opcaoMenu = s.nextInt();
                System.out.print("\n");
                s.nextLine();

                switch (opcaoMenu) {
                    case MENU_ARQ_NOVO:
                        inserir_arquivo(s, user);
                        break;
                    case MENU_ARQ_CON:
                        arquivo = selecionar_arquivo(s, user);
                        if (arquivo != null) {
                            consultar_arquivo(s, arquivo);
                        }
                        break;
                    case MENU_ARQ_VOLT:
                        continue;
                    default:
                        System.err.println("Opcao invalida!");
                }
            } catch (InputMismatchException err) {
                s.nextLine();
                System.err.println("Opcao invalida!");
            } catch (NewFileException | ArquivoDuplicadoException e) {
                System.err.println(e.getMessage());
            }
        } while (opcaoMenu != MENU_ARQ_VOLT);
    }

    private static void consultar_arquivo(Scanner s, Arquivo arq) throws IOException {
        System.out.println("--- GERENCIAR ARQUIVO ---");
        ArrayList<String> texto = arq.getTexto();
        ArrayList<Integer> codigoUsuarioAcesso;
        int opc = -1;
        String confirm;

        System.out.println("Nome arquivo: " + arq.getNome());
        System.out.println("---------------------------------------");
        for (int i = 0; i < texto.size(); i++) {
            System.out.println(texto.get(i));
        }
        System.out.println("---------------------------------------\n");

        do {
            try {
                System.out.println("1 - Editar");
                System.out.println("2 - Alterar acessos");
                System.out.println("3 - Remover");
                System.out.println("0 - Voltar");
                System.out.print("Selecione: ");
                opc = s.nextInt();
                System.out.print("\n");
                s.nextLine();

                switch (opc) {
                    case MENU_ARQ_EDIT:
                        editar_arquivo(s, arq);
                        return;
                    case MENU_ARQ_GERE:
                        codigoUsuarioAcesso = selecionar_usuario_acesso(s);
                        if (codigoUsuarioAcesso != null && codigoUsuarioAcesso.size() > 0) {
                            arq.setUsuarioAcessoAdm(codigoUsuarioAcesso);
                            arq.updateFileData();
                            System.out.println("Acessos do arquivo alterados com sucesso!\n");
                        }
                        return;
                    case MENU_ARQ_EXC:
                        System.out.print("Deseja mesmo remover arquivo? [S/n] ");
                        confirm = s.nextLine();
                        if (confirm.equalsIgnoreCase(SIM)) {
                            arq.excluir();
                            System.out.println("Arquivo removido com sucesso!\n");
                        }
                        return;
                    case MENU_ARQ_VOLT:
                        return;
                    default:
                        System.err.println("Opcao invalida!");
                }
            } catch (InputMismatchException err) {
                s.nextLine();
                System.err.println("Opcao invalida!");
            }
        } while (opc != MENU_ARQ_VOLT);

    }

    private static Arquivo selecionar_arquivo(Scanner s, Usuario user) throws FileNotFoundException, IOException {
        Arquivo arquivo = null;
        ArrayList<Arquivo> lista = carregar_lista_arquivo(user);
        int opc;
        try {
            do {
                System.out.println("--- SELECIONAR ARQUIVO ---");
                for (int i = 1; i <= lista.size(); i++) {
                    System.out.println(String.format("%d - %s", i, lista.get(i - 1).getNome()));
                }
                System.out.println("0 - Voltar");
                System.out.print("Selecione: ");
                opc = s.nextInt();
                if (opc < 0 || opc > lista.size()) {
                    throw new InputMismatchException("Valor invalido!");
                }
                s.nextLine();
                System.out.print("\n");

                if (opc == MENU_ARQ_VOLT) {
                    return arquivo;
                }

                return lista.get(opc - 1);

            } while (opc != MENU_ARQ_VOLT);
        } catch (InputMismatchException err) {
            s.nextLine();
            System.err.println(err.getMessage());
        }

        return arquivo;
    }

    private static void inserir_arquivo(Scanner s, Usuario user)
            throws IOException, NewFileException, ArquivoDuplicadoException {

        ArrayList<Integer> codigoUsuarioAcesso;
        boolean arquivoExiste = false;
        Arquivo arquivo = null;

        System.out.println("--- NOVO ARQUIVO ---");
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = s.nextLine();
        System.out.print("\n");

        ArrayList<Arquivo> arquivoList = carregar_lista_arquivo(user);
        for (Arquivo a : arquivoList) {
            if (a.getNome().equalsIgnoreCase(nomeArquivo)) {
                if (!a.getCodigoUsuarioAcesso().contains(user.getCodigo())) {
                    throw new ArquivoDuplicadoException("Arquivo ja existe!");
                }
                // arquivo existe e user tem acesso pra editar
                arquivoExiste = true;
                arquivo = a;
                break;
            }
        }

        if (!arquivoExiste) {
            arquivo = new Arquivo(nomeArquivo, user.getCodigo(), null);
            if (configurar_acesso_arquivo(s)) {
                codigoUsuarioAcesso = selecionar_usuario_acesso(s);
                arquivo.setUsuarioAcessoAdm(codigoUsuarioAcesso);
            }
            if (arquivo.createFile()) {
                System.out.println("Arquivo inserido com sucesso!\n");
            } else {
                throw new NewFileException("Problema ao criar arquivo!");
            }
        }

        editar_arquivo(s, arquivo);
    }
}
