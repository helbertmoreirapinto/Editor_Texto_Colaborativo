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
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

import editor.crypt.Criptografia;
import editor.exc.ArquivoDuplicadoException;
import editor.exc.CriarDiretorioException;
import editor.exc.LoginInvalidoException;
import editor.exc.UsuarioInativoException;

public class Principal {

	private static final String NOME_ARQUIVO_USUARIOS = "USU_DB//USUARIOS.txt";
	private static final String DIR_ARQUIVOS = "ARQUIVOS";
	private static final String TERMINAR_EDICAO = "\\q";
	private static final String FILE_DATA = ".data";
	private static final String FILE_TEXT = ".txt";
	private static final int COD_ELEM_AUTOR = 0;

	private static final int MENU_GER_ARQ = 1;
	private static final int MENU_GER_USU = 2;
	private static final int MENU_SAIR = 0;

	private static final int MENU_ARQ_NOVO = 1;
	private static final int MENU_ARQ_EDIT = 2;
	private static final int MENU_ARQ_GERE = 3;
	private static final int MENU_ARQ_ALT_ACESS = 1;
	private static final int MENU_ARQ_DEL = 2;
	private static final int MENU_ARQ_VOLT = 0;
	private static final int MENU_USU_ALT = 1;
	private static final int MENU_USU_INATIVO = 2;

	private static final int MENU_USU_NOVO = 1;
	private static final int MENU_USU_CONS = 2;
	private static final int MENU_USU_VOLT = 0;;

	public static void main(String[] args) {
		Principal princ = new Principal();
		int opcaoMenu = -1;
		Usuario usuarioLogado = null;

		try (Scanner entrada = new Scanner(System.in)) {
			do {
				try {
					if (usuarioLogado == null) {
						usuarioLogado = princ.logar(entrada);
						System.out.println(String.format("Usuario Logado: %s\n", usuarioLogado.getNome()));
					}
					opcaoMenu = princ.exibir_menu(entrada, usuarioLogado);

					switch (opcaoMenu) {
					case MENU_GER_ARQ:
						princ.gerenciar_arquivo(entrada, usuarioLogado);
						break;
					case MENU_GER_USU:
						if (usuarioLogado.isAdm()) {
							princ.gerenciar_usuario(entrada);
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

	/* MENU */
	private int exibir_menu(Scanner s, Usuario u) throws InputMismatchException {
		System.out.println("--- MENU ---");
		System.out.println("1 - Gerenciar Arquivo");
		if (u.isAdm()) {
			System.out.println("2 - Gerenciar Usuario");
		}
		System.out.println("0 - Sair");
		System.out.print("Selecione: ");
		int opc = s.nextInt();
		System.out.print("\n");
		s.nextLine();
		return opc;
	}

	/* CRUD USUARIO */
	private int exibir_menu_usuario(Scanner s) throws InputMismatchException {
		System.out.println("--- MENU USUARIO ---");
		System.out.println("1 - Novo Usuario");
		System.out.println("2 - Consultar Usuario");
		System.out.println("0 - Voltar");
		System.out.print("Selecione: ");
		int opc = s.nextInt();
		System.out.print("\n");
		s.nextLine();
		return opc;
	}

	private HashMap<Integer, Usuario> carregar_lista_usuario() throws FileNotFoundException, IOException {
		HashMap<Integer, Usuario> usuarioList = new HashMap<Integer, Usuario>();
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

	private Usuario logar(Scanner s) throws NoSuchAlgorithmException, LoginInvalidoException, FileNotFoundException,
			IOException, UsuarioInativoException {
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

	private void gerenciar_usuario(Scanner s) throws NoSuchAlgorithmException, IOException {
		HashMap<Integer, Usuario> usuarioList;
		int opcaoMenu = -1;
		do {
			try {
				opcaoMenu = exibir_menu_usuario(s);

				switch (opcaoMenu) {
				case MENU_USU_NOVO:
					inserir_usuario(s);
					System.out.println("Usuario inserido com sucesso!\n");
					break;
				case MENU_USU_CONS:
					usuarioList = carregar_lista_usuario();
					consultar_usuario(s, usuarioList);
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

	private void inserir_usuario(Scanner s) throws IOException, NoSuchAlgorithmException {
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

	private void consultar_usuario(Scanner s, HashMap<Integer, Usuario> usuarioList)
			throws IOException, NoSuchAlgorithmException {
		int opc = -1;
		Usuario usu;
		do {
			try {
				opc = exibir_consulta_usuario(s, usuarioList);
				if (opc == MENU_USU_VOLT)
					return;
				usu = usuarioList.get(opc);
				if (alterar_usuario(s, usu)) {
					atualizar_arquivo(usuarioList);
					System.out.println("Usuario alterado com sucesso!");
				}
				usuarioList = carregar_lista_usuario();
			} catch (InputMismatchException e) {
				System.err.println("Opcao invalida!");
				s.nextLine();
			}
		} while (opc != MENU_USU_VOLT);
	}

	private void atualizar_arquivo(HashMap<Integer, Usuario> usuarioList) throws IOException {
		boolean append_mode = false;
		try (FileWriter writer = new FileWriter(NOME_ARQUIVO_USUARIOS, append_mode);
				BufferedWriter buffer = new BufferedWriter(writer)) {
			Usuario usuario;
			for (Entry<Integer, Usuario> entrySet : usuarioList.entrySet()) {
				usuario = entrySet.getValue();
				PrintWriter out = new PrintWriter(buffer);
				out.printf("%d#%s#%s#%s#%s#%s#%n", usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
						usuario.getSenha(), String.valueOf(usuario.isAdm()), String.valueOf(usuario.isAtivo()));
			}
			buffer.flush();
		}
	}

	private int exibir_consulta_usuario(Scanner s, HashMap<Integer, Usuario> usuarioList)
			throws InputMismatchException {
		int opc;
		System.out.println("--- SELECIONAR USUARIO ---");
		System.out.println("COD\t|NOME\t\t\t\t|NICK\t\t|ATIVO\t|");
		for (Entry<Integer, Usuario> entryset : usuarioList.entrySet()) {
			System.out.println(String.format("%03d\t|%-31s|%-15s|%s\t|", entryset.getValue().getCodigo(),
					entryset.getValue().getNome(), entryset.getValue().getLogin(),
					String.valueOf(entryset.getValue().isAtivo())));
		}
		System.out.println("0 - Voltar");
		System.out.print("Selecione: ");
		opc = s.nextInt();
		s.nextLine();
		System.out.print("\n");
		if (opc > usuarioList.size())
			throw new InputMismatchException();
		return opc;
	}

	private boolean alterar_usuario(Scanner s, Usuario usuario) throws NoSuchAlgorithmException {
		int opc = -1;
		do {
			try {
				System.out.println("--- ALTERAR USUARIO ---");
				System.out.println("USUARIO: " + usuario.getNome());
				System.out.println("1 - Alterar dados");
				System.out.println(String.format("2 - Tornar %s", (usuario.isAtivo()) ? "inativo" : "ativo"));
				System.out.println("0 - Voltar");
				System.out.print("Selecione: ");
				opc = s.nextInt();
				s.nextLine();

				if (opc == MENU_USU_ALT) {
					System.out.print(String.format("Nome [%s]: ", usuario.getNome()));
					usuario.setNome(s.nextLine());
					System.out.print(String.format("Login [%s]: ", usuario.getLogin()));
					usuario.setLogin(s.nextLine());
					System.out.print(String.format("Senha: "));
					usuario.setSenha(Criptografia.criptografar(s.nextLine()));
					System.out.print("Usuario ADM? ");
					usuario.setAdm(s.nextBoolean());
					s.nextLine();
					return true;
				} else if (opc == MENU_USU_INATIVO) {
					usuario.setAtivo(!usuario.isAtivo());
					return true;
				}
			} catch (InputMismatchException err) {
				System.err.println("Opcao invalida!");
				s.nextLine();
				return false;
			}
		} while (opc != MENU_USU_VOLT);
		return false;
	}

	/* CRUD ARQUIVO */
	private int exibir_menu_arquivo(Scanner s) throws InputMismatchException {
		System.out.println("--- MENU ARQUIVO ---");
		System.out.println("1 - Novo Arquivo");
		System.out.println("2 - Editar Arquivo");
		System.out.println("3 - Gerenciar Arquivo");
		System.out.println("0 - Voltar");
		System.out.print("Selecione: ");
		int opc = s.nextInt();
		System.out.print("\n");
		s.nextLine();
		return opc;
	}

	private ArrayList<Arquivo> carregar_lista_arquivo() throws FileNotFoundException, IOException {
		ArrayList<Arquivo> listaRetorno = new ArrayList<Arquivo>();
		File diretorioArquivos = new File(DIR_ARQUIVOS);
		File[] arquivoList = diretorioArquivos.listFiles();
		int codigoAutor;
		String nomeArquivo;
		ArrayList<String> usuarioAcesso;
		if (arquivoList != null) {
			for (File file : arquivoList) {
				if (file.getName().contains(FILE_DATA)) {
					nomeArquivo = file.getName().substring(0, file.getName().indexOf("."));
					usuarioAcesso = new ArrayList<String>();
					try (FileReader reader = new FileReader(file); BufferedReader buffer = new BufferedReader(reader)) {
						while (buffer.ready()) {
							usuarioAcesso.add(buffer.readLine());
						}
						codigoAutor = Integer.parseInt(usuarioAcesso.get(COD_ELEM_AUTOR));
						listaRetorno.add(new Arquivo(nomeArquivo, codigoAutor, usuarioAcesso));
					}
				}
			}
		}

		return listaRetorno;
	}

	private void gerenciar_arquivo(Scanner s, Usuario usuarioLogado) throws CriarDiretorioException, IOException {

		int opcaoMenu = -1;
		ArrayList<Arquivo> arquivosComAcesso;
		Arquivo arquivo;
		int cod;
		do {
			try {
				arquivosComAcesso = filtrar_acesso_arquivo(usuarioLogado);
				opcaoMenu = exibir_menu_arquivo(s);
				switch (opcaoMenu) {
				case MENU_ARQ_NOVO:
					inserir_arquivo(s, usuarioLogado);
					System.out.println("Arquivo inserido com sucesso!\n");
					break;
				case MENU_ARQ_EDIT:
					cod = selecionar_arquivo(s, arquivosComAcesso);
					if (cod != MENU_ARQ_VOLT) {
						arquivo = arquivosComAcesso.get(cod - 1);
//						editar arquivo selecionado
					}
					break;
				case MENU_ARQ_GERE:
					cod = selecionar_arquivo(s, arquivosComAcesso);
					if (cod != MENU_ARQ_VOLT) {
						arquivo = arquivosComAcesso.get(cod - 1);
						alterar_acesso_arquivo(s, arquivo);
					}
					break;
				case MENU_ARQ_VOLT:
					break;
				default:
					System.err.println("Opcao invalida!");
				}

			} catch (InputMismatchException err) {
				s.nextLine();
				System.err.println("Opcao invalida!");
			} catch (ArquivoDuplicadoException err) {
				System.err.println(err.getMessage());
			}
		} while (opcaoMenu != MENU_ARQ_VOLT);
	}

	private void inserir_arquivo(Scanner s, Usuario usuarioLogado)
			throws IOException, ArquivoDuplicadoException, CriarDiretorioException {
		boolean append_mode = true;
		String nomeArquivo, PATH_FILE, URL_FILE, URL_FILE_DATA;
		File dir, file, fileData;
		boolean isMakeDir;

		System.out.println("--- NOVO ARQUIVO ---");
		System.out.print("Nome do arquivo: ");
		nomeArquivo = s.nextLine();

		PATH_FILE = String.format("%s", DIR_ARQUIVOS);
		URL_FILE = String.format("%s//%s%s", PATH_FILE, nomeArquivo, FILE_TEXT);
		URL_FILE_DATA = String.format("%s//%s%s", PATH_FILE, nomeArquivo, FILE_DATA);

		dir = new File(PATH_FILE);
		isMakeDir = dir.mkdirs();

		if (dir.exists() || isMakeDir) {

			file = new File(URL_FILE);
			fileData = new File(URL_FILE_DATA);
			ArrayList<String> acessoUsuario;

			if ((file.exists() || fileData.exists())) {
				acessoUsuario = acesso_usuario_arquivo(fileData);
				if (!acessoUsuario.contains(String.valueOf(usuarioLogado.getCodigo()))) {
					throw new ArquivoDuplicadoException("Arquivo com este nome enconrado!");
				}
//				Arquivo existe e usuario tem acesso nele
//				Entrar no modo edicao
				return;
			}

			System.out.println("Insira o texto o arquivo:");
			try (FileWriter writer = new FileWriter(URL_FILE, append_mode);
					BufferedWriter buffer = new BufferedWriter(writer)) {
				String linha;
				while (!(linha = s.nextLine()).equals(TERMINAR_EDICAO)) {
					buffer.append(linha + "\n");
				}
				buffer.flush();
			}

			try (FileWriter writer = new FileWriter(URL_FILE_DATA, !append_mode);
					BufferedWriter buffer = new BufferedWriter(writer)) {

				ArrayList<String> usuarioAcessoArquivoList = selecionar_usuario_acesso_arquivo(s);
				buffer.write(usuarioLogado.getCodigo() + "\n");
				for (String codUsu : usuarioAcessoArquivoList) {
					buffer.write(codUsu + "\n");
				}
				buffer.flush();
			}
		} else {
			throw new CriarDiretorioException("Erro ao criar diretorio do arquivo!");
		}
	}

	private ArrayList<String> acesso_usuario_arquivo(File fileData) throws FileNotFoundException, IOException {
		ArrayList<String> listaRetorno = new ArrayList<String>();
		try (FileReader reader = new FileReader(fileData); BufferedReader buffer = new BufferedReader(reader)) {
			while (buffer.ready()) {
				listaRetorno.add(buffer.readLine());
			}
		}
		return listaRetorno;
	}

	private void alterar_acesso_arquivo(Scanner s, Arquivo a) throws IOException {
		String URL_ARQ = String.format("%s//%s%s", DIR_ARQUIVOS, a.getNome(), FILE_TEXT);
		String URL_ARQ_DATA = String.format("%s//%s%s", DIR_ARQUIVOS, a.getNome(), FILE_DATA);
		File file = new File(URL_ARQ);
		File fileData = new File(URL_ARQ_DATA);
		boolean append_mode = false;
		ArrayList<String> usuarioAcessoArquivoList;
		int opc = -1;
		do {
			try {
				System.out.println("--- GERENCIAR ARQUIVO ---");
				System.out.println(String.format("ARQUIVO: %s", a.getNome()));
				System.out.println("1 - Alterar acessos do arquivo");
				System.out.println("2 - Deletar arquivo");
				System.out.println("0 - Voltar");
				System.out.print("Selecione: ");

				opc = s.nextInt();
				s.hasNextLine();

				if (opc == MENU_ARQ_ALT_ACESS) {
					try (FileWriter writer = new FileWriter(URL_ARQ_DATA, append_mode);
							BufferedWriter buffer = new BufferedWriter(writer)) {

						usuarioAcessoArquivoList = selecionar_usuario_acesso_arquivo(s);
						buffer.write(a.getCodigoAutor() + "\n");
						for (String codUsu : usuarioAcessoArquivoList) {
							buffer.write(codUsu + "\n");
						}
						buffer.flush();
					}
				} else if (opc == MENU_ARQ_DEL) {
					if (file.delete() && fileData.delete()) {
						System.out.println("Arquivo excluido com sucesso!");
						break;
					} else {
						throw new FileNotFoundException("Nao foi possivel excluir arquivo");
					}
				}
			} catch (InputMismatchException err) {
				System.err.println("Opcao invalida!");
			}
		} while (opc != MENU_ARQ_VOLT);
		System.out.print("\n");
	}

	private ArrayList<String> selecionar_usuario_acesso_arquivo(Scanner s) throws FileNotFoundException, IOException {
		ArrayList<String> listaRetorno = new ArrayList<String>();
		String opc;
		String[] vet;
		HashMap<Integer, Usuario> usuarioList = carregar_lista_usuario();
		System.out.println("--- SELECIONAR USUARIO ---");
		System.out.println("Selecione os usuarios com acesso ADM ao arquivo: ");
		for (Entry<Integer, Usuario> entrySet : usuarioList.entrySet()) {
			System.out.println(entrySet.getKey() + " - " + entrySet.getValue().getNome());
		}
		System.out.println("0 - Voltar");
		System.out.println("Selecione [utilize ; como separador]:");
		opc = s.nextLine();

		if (opc.equals("") || opc.equals(String.valueOf(MENU_ARQ_VOLT))) {
			return listaRetorno;
		}

		vet = opc.split(";");
		listaRetorno.addAll(Arrays.asList(vet));

		return listaRetorno;
	}

	private ArrayList<Arquivo> filtrar_acesso_arquivo(Usuario usuarioLogado) throws FileNotFoundException, IOException {
		ArrayList<Arquivo> arquivoList = carregar_lista_arquivo();
		ArrayList<Arquivo> listaRetorno = new ArrayList<Arquivo>();

		if (usuarioLogado.isAdm()) {
			return arquivoList;
		}

		for (Arquivo a : arquivoList) {
			if (a.getCodigoAutor() == usuarioLogado.getCodigo()) {
				listaRetorno.add(a);
				continue;
			}

			for (String usu : a.getUsuarioAcessoAdm()) {
				if (Integer.parseInt(usu) == usuarioLogado.getCodigo()) {
					listaRetorno.add(a);
					break;
				}
			}
		}
		return listaRetorno;
	}

	private int selecionar_arquivo(Scanner s, ArrayList<Arquivo> arquivoList) throws InputMismatchException {
		int opc;
		System.out.println("--- SELECIONAR ARQUIVO ---");
		for (int i = 0; i < arquivoList.size(); i++) {
			System.out.println(String.format("%d - %s", (i + 1), arquivoList.get(i).getNome()));
		}
		System.out.println("0 - Voltar");
		System.out.print("Selecione: ");
		opc = s.nextInt();
		s.nextLine();
		System.out.print("\n");
		return opc;
	}

}
