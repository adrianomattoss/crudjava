import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class projetocrud {

	private static Scanner digitado = new Scanner (System.in);
	private static dados objDados = new dados();

	private static boolean validaNome(String texto) {

		boolean nomeValido = false;

		texto = texto.replaceAll(" ","");
		
		nomeValido = texto.matches("[A-Za-z]*");

		return nomeValido;
	}

	private static boolean validaTelefone (String texto) {
		
		boolean telefoneValido = false;

		texto = texto.replaceAll(" ","");

		telefoneValido = texto.matches("[0-9]*");

		return telefoneValido;
	}

	private static boolean validaDataNascimento (String texto) {
		
		boolean dataNascValida = false;

		texto = texto.replaceAll(" ","");

		dataNascValida = texto.matches("[0-9]*");

		return dataNascValida;
	}

	private static boolean validaNota (String texto) {
		
		boolean validaNota = false;

		texto = texto.replaceAll(" ","");

		validaNota = texto.matches("[0-9]*");

		return validaNota;
	}

	private static void inserirCabecalho(int opcao) {

		System.out.println("");
		System.out.println("+----------------------------------------------------------------------+");
			
		switch (opcao) {
			case 0 :

				System.out.println("+------------------------ SELECIONE UMA OPCAO -------------------------+");
				System.out.println("|                                                                      |");
				System.out.println("|       Cadastrar(C)     Alterar(A)     Excluir(X)     Listar(L)       |");
				System.out.println("|                                                                      |");
				System.out.println("|                                                                      |");
				System.out.println("|                         Encerrar Programa(E)                         |");
				System.out.println("|                                                                      |");
				System.out.println("+----------------------------------------------------------------------+");

				break;

			case 1: System.out.println("--------------------- CADASTRO DE PESSOAS E ALUNOS ---------------------"); 
				break;
			case 2:	System.out.println("------------------------- ALTERACAO DE CADASTRO ------------------------"); 
				break;
			case 3: System.out.println("------------------------- EXCLUSAO DE CADASTRO -------------------------"); 
				break;
			case 4: System.out.println("------------------------- LISTA DE CADASTRADOS -------------------------"); 
				break;

		}

		System.out.println("");
	}

	public static boolean continuarEdicao(boolean editar) {

		boolean respostaValida, encerraCadastro = false;	
		String res = "";	

		System.out.println("");
		System.out.println("Cadastrar novo registro? [S/N]");	
		
		do {

			res = digitado.nextLine();
			System.out.println("");

			respostaValida = (res.equals("S") || res.equals("s"));
			encerraCadastro = res.equals("N") || res.equals("n");
			
			if (encerraCadastro) {
				editar = false;
				break;
			} else if (!respostaValida) {
				System.out.println("");
				System.out.println("Valor informado esta incorreto. Digitar S ou N.");
				System.out.println("");
			}

		} while (!respostaValida);

		return editar;
	}

	private static ArrayList<String> inserir() {
	
		ArrayList<String> inserir = new ArrayList<String>();

		boolean editar = true, nomeValido = false, telefoneValido = false;

		boolean valorValido = false;

		String valorDigitado = "";

		while (editar) {

			inserir.add("CODIGO");

			while (!valorValido) {

				System.out.print("> Digite o nome: "); 
				
				valorDigitado = digitado.nextLine();

				if (validaNome (valorDigitado)) {
					
					inserir.add(valorDigitado);
					valorValido = true;

				} else {
					System.out.println("Nome invalido, digite novamente...");
					System.out.println("");
				}
			}

			System.out.println("");
			valorValido = false;

			while (!valorValido) {

				System.out.print("> Digite o Telefone: "); 
				
				valorDigitado = digitado.nextLine();

				if (validaTelefone(valorDigitado)) {

					inserir.add(valorDigitado);
					valorValido = true;

				} else {
					System.out.println("Telefone invalido, digite novamente...");	
					System.out.println("");

				}
			}

			System.out.println("");
			valorValido = false;

			while (!valorValido) {

				System.out.print("> Data de nascimento (DDMMAAAA): "); 
				
				valorDigitado = digitado.nextLine();

				if (validaDataNascimento(valorDigitado)) {

					inserir.add(valorDigitado);
					valorValido = true;

				} else {
					System.out.println("Data de Nascimento invalida, digite novamente...");	
					System.out.println("");

				}
			}

			System.out.println("");
			valorValido = false;

			while (!valorValido) {

				System.out.print("> Nota final: "); 
				
				valorDigitado = digitado.nextLine();

				if (validaNota(valorDigitado)) {

					inserir.add(valorDigitado);
					valorValido = true;

				} else {
					System.out.println("Nota final invalida, digite novamente...");	
					System.out.println("");

				}
			}

			System.out.println("");
			valorValido = false;
			

			//System.out.print("> Nota Final: "); 
			//inserir.add(digitado.nextLine());

			editar = continuarEdicao(editar);					
		}
		
		return inserir;
	}

	private static void alterar() {

		int codigo = 0;
		int opcaoSelecionada = 0;
		String novoValor = "", msgSelecao = "";

		Scanner digitadoAlteracao = new Scanner (System.in);

		System.out.print("> Digite o codigo a ser alterado: "); 
		
		codigo = Integer.parseInt(digitadoAlteracao.next());

		if (objDados.buscarRegistro(codigo)) {
		
			System.out.println("");
			System.out.println("> Selecione qual campo deseja alterar: ");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("[1] Nome   [2] Telefone   [3] Data de Nascimento   [4] Nota final");
			System.out.println("");

			opcaoSelecionada = Integer.parseInt(digitadoAlteracao.next());

			msgSelecao = "> Insira o novo valor para ";
			
			switch (opcaoSelecionada) {

				case 1: msgSelecao += "[Nome]: "; break;
				case 2: msgSelecao += "[Telefone]: "; break;
				case 3: msgSelecao += "[Data de Nascimento]: "; break;
				
				//VERIFICAR SE É ALUNO ANTES DE ALTERAR A NOTA! ***
				case 4: msgSelecao += "[Nota Final]: "; break;
			
			}

			System.out.print(msgSelecao);

			novoValor = digitadoAlteracao.next();

			System.out.println("");

			objDados.alterarRegistro(codigo, opcaoSelecionada, novoValor);

			System.out.println("");
			System.out.print("Registro alualizado com sucesso!");
			System.out.println("");

		} else {

			System.out.println("");
			System.out.print("Registro nao encontrado!");
			System.out.println("");	

		}	
	}

	private static int excluir() {

		boolean confirmar, cancelar, respostaValida = false;	
		String res = "";	
		int codigo = 0;

		Scanner digitadoExclusao = new Scanner (System.in);

		while (!respostaValida) { //enquanto a resposta for invalida faz as solicicao

			System.out.print("> Digite o codigo a ser excluido: "); 
			
			codigo = Integer.parseInt(digitadoExclusao.next());

			System.out.print("> Tem certeza que deseja excluir o registro com o codigo [" + codigo + "]? " );

			res = digitadoExclusao.next();

			confirmar = (res.equals("S") || res.equals("s"));
			cancelar = (res.equals("N") || res.equals("n"));	

				if (confirmar) {
					
					respostaValida = true;

					codigo = codigo;

					System.out.println("");
					System.out.println("Exclusao concluida!");
					System.out.println("");


				} else if (cancelar) {
					
					respostaValida = true;

					codigo = 0;

					System.out.println("");
					System.out.println("Exclusao cancelada!");
					System.out.println("");

				} else {

					codigo = 0;

					System.out.println("");
					System.out.println("Valor informado esta incorreto. Digite S ou N.");
					System.out.println("");	

				}

			}

		return codigo;
	}
	

	public static void main (String arg[]) {

		boolean encerraPrograma = false;
		String selecionado = "";
		//dados objDados = new dados();	

		do { //FAZER VALIDAÇÃO DA SELECAO SOMENTE ANTES DE INICIAR
		
			inserirCabecalho(0);

			selecionado = digitado.nextLine(); //TECLA ENTER!

			if (selecionado.equals("c")) {
			
				inserirCabecalho(1);
				objDados.cadastrarPessoas(inserir());

			} else if (selecionado.equals("a")) {

				inserirCabecalho(2);
				alterar();

			} else if (selecionado.equals("x")) {

				inserirCabecalho(3);	
				objDados.excluirRegristo(excluir());

			} else if (selecionado.equals("l")) {

				inserirCabecalho(4);
				System.out.println(objDados.listarRegistros());
			
			} else if (selecionado.equals("e")) {

				System.out.println("Tem certeza que deseja sair do programa? ");
				encerraPrograma = true; //////////////NÃO FUNCIONA
			
			};

		} while (encerraPrograma == false);
	}	

}