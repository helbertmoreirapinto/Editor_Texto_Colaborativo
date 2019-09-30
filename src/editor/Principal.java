package editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;

import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;

import java.util.Scanner;

import editor.acesso.AcessoUsuario;
import editor.crypt.Criptografia;
import editor.exc.ArquivoDuplicadoException;
import editor.exc.CriarDiretorioException;
import editor.exc.LoginInvalidoException;
import editor.exc.SenhaInvalidaException;

public class Principal {

	// LOGAR
	// --NA PARTE 3 DE REDES LOGAR NO SERVER AQUI
	// MENU
	// 1.NOVO
	// <ABRE NOVO ARQUIVO E CONFIGURA USUARIO COMO AUTOR>
	// <RESERVAR UMA THREAD PARA SALVAR AS ALTERACOES NO ARQUIVO>
	// 2.CONSULTAR ARQUIVOS
	// <LISTAR ARQUIVOS QUE USUARIO TEM ACESSO>
	// 2.1.EDITAR ARQUIVO
	// ((IF ADM || IF AUTOR) && NAO TEM USUARIO LOGADO NO ARQUIVO)2.2.EDITAR ACESSOS
	// ((IF ADM || IF AUTOR) && NAO TEM USUARIO LOGADO NO ARQUIVO)2.3.APAGAR ARQUIVO
	// (IF ADM)3.GERENCIAR USUARIOS
	private static final String NOME_ARQUIVO_USUARIOS = "USU_DB//USUARIOS.txt";
	private static final String DIR_ARQUIVOS = "ARQUIVOS";

	private static final int MENU_INS_ARQ = 1;
	private static final int MENU_CON_ARQ = 2;
	private static final int MENU_GER_USU = 3;
	private static final int MENU_SAIR = 0;

	private static final int MENU_USU_INS = 1;
	private static final int MENU_USU_CON = 2;
	private static final int MENU_USU_VOL = 0;;

	public static void main(String[] args) {
		Principal princ = new Principal();
		int opcaoMenu = -1;
		Usuario usuarioLogado = null;
		HashMap<Integer, Usuario> usuarioList = new HashMap<Integer, Usuario>();
		ArrayList<Arquivo> arquivoList = new ArrayList<Arquivo>();

		try {

			usuarioList = princ.carregar_lista_usuario();
			arquivoList = princ.carregar_lista_arquivo();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		for (Arquivo a : arquivoList) {
			System.out.println(a.getNome());
		}

		try (Scanner entrada = new Scanner(System.in)) {
			do {
				try {
					if (usuarioLogado == null) {
						usuarioLogado = princ.logar(entrada, usuarioList);
						System.out.println(String.format("Usuario Logado: %s\n", usuarioLogado.getNome()));
					}
					opcaoMenu = princ.exibir_menu(entrada, usuarioLogado);

					switch (opcaoMenu) {
					case MENU_INS_ARQ:
						princ.inserir_arquivo(entrada, usuarioLogado);
						break;
					case MENU_CON_ARQ:
						break;
					case MENU_GER_USU:
						if (usuarioLogado.getAcessoUsuario().isUsuarioAdm()) {
							princ.gerenciar_usuario(entrada, usuarioList);
						}
						break;
					case MENU_SAIR:
						System.out.println("BYE!");
						break;
					default:
						System.err.println("Opcao Invalida!");
					}
				} catch (LoginInvalidoException | CriarDiretorioException | ArquivoDuplicadoException | IOException
						| NoSuchAlgorithmException err) {
					System.err.println(err.getMessage());
				} catch (InputMismatchException err) {
					entrada.nextLine();
					System.err.println("Opcao Invalida!");
				}
			} while (opcaoMenu != MENU_SAIR);
		}
	}

	private ArrayList<Arquivo> carregar_lista_arquivo() {
		ArrayList<Arquivo> arquivoList = new ArrayList<Arquivo>();

		return arquivoList;
	}

	public int exibir_consulta_usuario(Scanner s, HashMap<Integer, Usuario> usuarioList) throws InputMismatchException {
		int opc;
		System.out.println("--- MENU USUARIO ---");
		System.out.println("COD\t|NOME\t\t\t|NICK\t\t|ATIVO\t|");
		for (Entry<Integer, Usuario> entryset : usuarioList.entrySet()) {
			System.out.println(String.format("%03d\t|%-23s|%-15s|%s\t|", entryset.getValue().getCodigo(),
					entryset.getValue().getNome(), entryset.getValue().getLogin(),
					String.valueOf(entryset.getValue().isAtivo())));
		}
		System.out.println("0 - Voltar");
		System.out.println("Selecione: ");
		opc = s.nextInt();
		s.hasNextLine();
		if (opc > usuarioList.size())
			throw new InputMismatchException();
		return opc;
	}

	public void alterar_usuario(Scanner s, Usuario usuario) {
		System.out.println(usuario.getNome());
	}

	public void consultar_usuario(Scanner s, HashMap<Integer, Usuario> usuarioList) {
		int opc = -1;
		Usuario usu;
		do {
			try {
				opc = exibir_consulta_usuario(s, usuarioList);
				if (opc == MENU_USU_VOL)
					return;
				usu = usuarioList.get(opc);
				alterar_usuario(s, usu);
			} catch (InputMismatchException e) {
				s.nextLine();
			}
		} while (opc < 0);
	}

	public HashMap<Integer, Usuario> carregar_lista_usuario() throws FileNotFoundException, IOException {
		HashMap<Integer, Usuario> usuarioList = new HashMap<Integer, Usuario>();
		String registro;
		String campo[];
		Usuario usuario;
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

	public void gerenciar_usuario(Scanner s, HashMap<Integer, Usuario> usuarioList)
			throws NoSuchAlgorithmException, IOException {
		int opcaoMenu = -1;
		do {
			try {

				opcaoMenu = exibir_menu_usuario(s);

				switch (opcaoMenu) {
				case MENU_USU_INS:
					inserir_usuario(s);
					System.out.println("Usuario inserido com sucesso!\n");
					break;
				case MENU_USU_CON:
					consultar_usuario(s, usuarioList);
					break;
				case MENU_USU_VOL:
					break;
				default:
					System.err.println("Opcao Invalida!");
				}

			} catch (InputMismatchException es) {
				s.nextLine();
				System.err.println("Opcao Invalida!");
			}
		} while (opcaoMenu != 0);

	}

	public int exibir_menu_usuario(Scanner s) throws InputMismatchException {
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

	public int exibir_menu(Scanner s, Usuario u) throws InputMismatchException {
		System.out.println("--- MENU ---");
		System.out.println("1 - Novo Arquivo");
		System.out.println("2 - Consultar Arquivos");
		if (u.getAcessoUsuario().isUsuarioAdm()) {
			System.out.println("3 - Gerenciar Usuarios");
		}
		System.out.println("0 - Sair");
		System.out.print("Selecione: ");
		int opc = s.nextInt();
		System.out.print("\n");
		s.nextLine();
		return opc;
	}

	public void salvar_dados_usuario(Usuario usuario) throws IOException {
		boolean append_mode = true;
		try (FileWriter writer = new FileWriter(NOME_ARQUIVO_USUARIOS, append_mode);
				BufferedWriter buffer = new BufferedWriter(writer)) {
			PrintWriter out = new PrintWriter(buffer);
			out.printf("%d#%s#%s#%s#%s#%s#%n", usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
					usuario.getSenha(), String.valueOf(usuario.getAcessoUsuario().isUsuarioAdm()),
					String.valueOf(usuario.isAtivo()));
			buffer.flush();
		}
	}

	public void inserir_usuario(Scanner s) throws IOException, NoSuchAlgorithmException {
		Usuario usuario;
		System.out.print("Nome: ");
		String nome = s.nextLine();
		System.out.print("Login: ");
		String login = s.nextLine();
		System.out.print("Senha: ");
		String senha = Criptografia.criptografar(s.nextLine());
		System.out.print("Usuario ADM? ");
		boolean adm = s.nextBoolean();
		s.nextLine();
		usuario = new Usuario(nome, login, senha, adm, true);
		this.salvar_dados_usuario(usuario);
	}

	public Usuario logar(Scanner s, HashMap<Integer, Usuario> usuarioList)
			throws NoSuchAlgorithmException, LoginInvalidoException, FileNotFoundException, IOException {
		String login, senha;

		System.out.println("--- LOGIN ---");
		System.out.print("Usuario: ");
		login = s.nextLine();
		System.out.print("Senha: ");
		senha = Criptografia.criptografar(s.nextLine());

		for (Entry<Integer, Usuario> entrySet : usuarioList.entrySet()) {
			if (login.equalsIgnoreCase(entrySet.getValue().getLogin())
					&& senha.equals(entrySet.getValue().getSenha())) {
				return entrySet.getValue();
			}
		}
		throw new LoginInvalidoException("Login/Senha invalidos");
	}

	private void inserir_arquivo(Scanner s, Usuario usuarioLogado)
			throws IOException, ArquivoDuplicadoException, CriarDiretorioException {
		boolean append_mode = true;
		String nomeArquivo, PATH_FILE, URL_FILE, URL_FILE_CONFIG;
		File dir, file, fileConfig;
		boolean isMakeDir;

		System.out.println("--- NOVO ARQUIVO ---");
		System.out.print("Nome do arquivo: ");
		nomeArquivo = s.nextLine();

		PATH_FILE = String.format("%s", DIR_ARQUIVOS);
		URL_FILE = String.format("%s//%s.txt", PATH_FILE, nomeArquivo);
		URL_FILE_CONFIG = String.format("%s//%s_config.txt", PATH_FILE, nomeArquivo);

		dir = new File(PATH_FILE);
		isMakeDir = dir.mkdirs();

		if (dir.exists() || isMakeDir) {

			file = new File(URL_FILE);
			fileConfig = new File(URL_FILE_CONFIG);
			String acessoUsuario = ""; // ler config e pegar os acessos do arquivo

			if ((file.exists() || fileConfig.exists())) {
				if (!acessoUsuario.contains(String.valueOf(usuarioLogado.getCodigo()))) {
					throw new ArquivoDuplicadoException("Arquivo com este nome enconrado!");
				}
				// Arquivo existe e usuario tem acesso nele
			}

			try (FileWriter writer = new FileWriter(URL_FILE, append_mode);
					BufferedWriter buffer = new BufferedWriter(writer)) {
				buffer.append("teste escrita");

				buffer.flush();
			}
		} else {
			throw new CriarDiretorioException("Erro ao criar diretorio do arquivo!");
		}
	}

//	private void editar_arquivo(Usuario usuario, Arquivo arquivo) throws IOException {
//		Try(Writer e = new FileWriter(String.format("%s//%s", DIR_ARQUIVOS, arquivo.getNome()))){
//			e.append("Teste\n");
//		}
//	}

}
