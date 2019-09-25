package editor;

import editor.exc.DiretorioException;
import editor.acesso.AcessoUsuario;
import editor.crypt.Criptografia;
import editor.exc.SenhaInvalidaException;
import editor.exc.LoginInvalidoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;

/**
 *
 * @author helbert
 */
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

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Principal princ = new Principal();
		int opcaoMenu = -1;
		Usuario usuario = null;
		try (Scanner entrada = new Scanner(System.in)) {
			do {
				try {
					if (usuario == null) {
						usuario = princ.logar(entrada);
						System.out.println(String.format("Usuario Logado: %s\n", usuario.getNome()));
					}

					opcaoMenu = princ.exibir_menu(entrada, usuario);

					switch (opcaoMenu) {
					case MENU_INS_ARQ:
						princ.inserir_arquivo(entrada, usuario);
						break;
					case MENU_CON_ARQ:
						break;
					case MENU_GER_USU:
						if (usuario.getAcessoUsuario().isUsuarioAdm()) {
							princ.gerenciar_usuario(entrada);
						}
						break;
					case MENU_SAIR:
						System.out.println("BYE!");
						break;
					default:
						System.err.println("Opcao Invalida!");
					}
				} catch (SenhaInvalidaException | LoginInvalidoException | DiretorioException | IOException ex) {
					System.err.println(ex.getMessage());
				} catch (InputMismatchException ex) {
					entrada.nextLine();
					System.err.println("Opcao Invalida!");
				}
			} while (opcaoMenu != MENU_SAIR);
		}
	}

	public void gerenciar_usuario(Scanner s) throws NoSuchAlgorithmException, IOException {
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
			System.out.println("3 - Gerencia Usaurios");
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
			out.printf("%d#%s#%s#%s#%s#%n", usuario.getCodigo(), usuario.getNome(), usuario.getLogin(),
					usuario.getSenha(), String.valueOf(usuario.getAcessoUsuario().isUsuarioAdm()));
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
		usuario = new Usuario(nome, login, senha, adm);
		this.salvar_dados_usuario(usuario);
	}

	public Usuario logar(Scanner s) throws NoSuchAlgorithmException, SenhaInvalidaException, LoginInvalidoException,
			FileNotFoundException, IOException {
		Usuario u = null;
		String login, senha;
		String registro, campoCod, campoLogin, campoSenha;
		String dadosUsuario[];
		String arq_acesso[];
		AcessoUsuario aceUsu;
		int ultimoCodigo = 0;
		
		System.out.println("--- LOGIN ---");
		System.out.print("Usuario: ");
		login = s.nextLine();
		System.out.print("Senha: ");
		senha = Criptografia.criptografar(s.nextLine());

		try (FileReader reader = new FileReader(NOME_ARQUIVO_USUARIOS);
				BufferedReader buffer = new BufferedReader(reader)) {
			while (buffer.ready()) {
				registro = buffer.readLine();
				dadosUsuario = registro.split("#");
				campoCod = dadosUsuario[0];
				campoLogin = dadosUsuario[2];
				campoSenha = dadosUsuario[3];
				if (campoLogin.equalsIgnoreCase(login)) {
					if (campoSenha.equals(senha)) {
						aceUsu = new AcessoUsuario(Boolean.parseBoolean(dadosUsuario[4]));
						if (dadosUsuario.length > 5) {
							arq_acesso = dadosUsuario[5].split(",");
							for (String x : arq_acesso) {
								System.out.println("VER AQUI");
							}
						}
						u = new Usuario(Integer.parseInt(dadosUsuario[0]), dadosUsuario[1], login, senha, aceUsu);
					} else {
						throw new SenhaInvalidaException("Senha invalida!");
					}
				}
				if (Integer.parseInt(campoCod) > ultimoCodigo) {
					ultimoCodigo = Integer.parseInt(campoCod);
				}
			}
			if (u == null) {
				throw new LoginInvalidoException("Login não encontrado!");
			}
		}
		Usuario.set_cod(ultimoCodigo);
		return u;
	}

	private void inserir_arquivo(Scanner s, Usuario usuarioLogado) throws IOException, DiretorioException {
		boolean append_mode = true;
		String nomeArquivo, PATH_FILE, URL_FILE;
		File dir, file;
		boolean isMakeDir;

		System.out.println("--- NOVO ARQUIVO ---");
		System.out.print("Nome do arquivo: ");
		nomeArquivo = s.nextLine();
		PATH_FILE = String.format("%s//%s", DIR_ARQUIVOS, nomeArquivo);
		URL_FILE = String.format("%s//%s.txt", PATH_FILE, nomeArquivo);

		dir = new File(PATH_FILE);
		file = new File(URL_FILE);
		if (file.exists()) {
			System.out.println("ARQUIVO JA EXISTE!");
		}
		isMakeDir = dir.mkdirs();

		System.out.println(isMakeDir);
		if (isMakeDir) {
			try (FileWriter writer = new FileWriter(URL_FILE, append_mode);
					BufferedWriter buffer = new BufferedWriter(writer)) {

			}
		} else {
			throw new DiretorioException("Erro ao criar diretorio do arquivo!");
		}
	}

}
