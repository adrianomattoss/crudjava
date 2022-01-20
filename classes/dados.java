//Camada de dados, separa e faz a integração dos dados com a tela
package classes;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

public class dados {

	//private static final int INDEX_CODIGO = 0;
	private static final int INDEX_NOME = 1;
	private static final int INDEX_TELEFONE = 2;
	private static final int INDEX_DATANASCIMENTO = 3;
	private static final int INDEX_NOTA = 4;
	private static final int INDEX_TOTAL_CAMPOS = 5;

	private ArrayList<pessoa> cadastroPessoa = new ArrayList<pessoa>();
	private ArrayList<aluno> cadastroAluno = new ArrayList<aluno>();

	private int codigoCadastro = 0;
	//armazena o ultimo registro do cadastro para evitar codigos repetidos após a exclusão de algum registro

	private static String getDataHora() {

		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dataAtual = new Date();
		return dataFormatada.format(dataAtual);

	}

	public boolean buscarRegistro(int codigo) {

		boolean encontrouCodigo = false;	

		for (int i=0; i < this.cadastroPessoa.size(); i++) {

			if (codigo == this.cadastroPessoa.get(i).getCodigo()) {
				encontrouCodigo = true;
			}		
		}

		for (int i=0; i < this.cadastroAluno.size(); i++) {

			if (codigo == this.cadastroAluno.get(i).getCodigo()) {
				encontrouCodigo = true;		 
			} 

		}
	
		return encontrouCodigo;
	}

	public void cadastrarPessoas (ArrayList<String> digitado) { //APRIMORAR PARA ORVERLOAD? ***
			
		//Busca a quantidade de vezes que 'CODIGO' aparece no ArrayList para saber a quantidade exata de pessoas adicionadas, 
		//esse valor será usado abaixo para montar o array com o valor exato de registros 
		int quantidadeRegistros = 0;
		for (int i=0; i < digitado.size(); i++){
			if (digitado.get(i) == "CODIGO") {
				quantidadeRegistros += 1;
			}
		}

		//Cria um array multidimensional com a quantidade de posições referente a quantidade de registros, para simular um insert no banco. 
		//Serve para separar de forma correta os registros do ArrayList, cada indice do array [i] é um cadastro com 5 campos [j], cada um referente à:
		//0: Codigo - 1: Nome - 2: Telefone - 3: Data Nascimento - 4: Nota (Se aluno)

		String[][] matriz = new String[quantidadeRegistros][INDEX_TOTAL_CAMPOS]; 

		int codigo = 0, aux = 0; 
		//var aux: auxilia na contagem do arrayslist, inicia a contagem onde parou na ultima iteração

		for(int i=0; i < matriz.length; i++) {
		
			for (int j=0; j < matriz[i].length; j++) { 
	
				//Se encontrar o 'CODIGO' substitui por um novo código auto increment na linha abaixo 
				if (digitado.get(aux) == "CODIGO") { 	
					
					if (this.codigoCadastro == 0)
						this.codigoCadastro = this.cadastroPessoa.size() + this.cadastroAluno.size() + 1; 	
					else
						this.codigoCadastro += 1;
				
				} else { //Se não encontrar o 'CODIGO' adiciona os demais campos referente ao codigo já criado dentro do array
										
					if (j == INDEX_NOTA) { //Verifica se existe nota 
							
						if (digitado.get(aux) != "") 
							matriz[i][j] = digitado.get(aux);
					
					} else {
						matriz[i][j] = digitado.get(aux); 

					}
				}	

				aux += 1;	

			}
	
			//Adiciona as informações do array no objeto pessoa ou aluno (caso tenha nota)
			if (matriz[i][INDEX_NOTA] != null) {

				aluno objAluno = new aluno(); 

				objAluno.setCodigo(this.codigoCadastro);	
				objAluno.setNome(matriz[i][INDEX_NOME]);
				objAluno.setTelefone(matriz[i][INDEX_TELEFONE]);
				objAluno.setDataNascimento(matriz[i][INDEX_DATANASCIMENTO]);
				objAluno.setNota(Float.parseFloat(matriz[i][INDEX_NOTA]));
				objAluno.setDataCadastro(getDataHora());
				objAluno.setDataAlteracao(getDataHora());
				
				this.cadastroAluno.add(objAluno);

			} else {

				pessoa objPessoa = new pessoa(); 

				objPessoa.setCodigo(this.codigoCadastro);	
				objPessoa.setNome(matriz[i][INDEX_NOME]);
				objPessoa.setTelefone(matriz[i][INDEX_TELEFONE]);
				objPessoa.setDataNascimento(matriz[i][INDEX_DATANASCIMENTO]);		
				objPessoa.setDataCadastro(getDataHora());
				objPessoa.setDataAlteracao(getDataHora());
		
				this.cadastroPessoa.add(objPessoa);

			}					
		} 
	}

	public void alterarRegistro(int codigo, int campoAlterado, String novoValor){

		boolean encontrouCodigo = false;
		
		if (codigo > 0) {

			//Procura o codigo dentro do cadastro de pessoas
			for (int i=0; i < this.cadastroPessoa.size(); i++) {

				if (codigo == this.cadastroPessoa.get(i).getCodigo()) {
					
					encontrouCodigo = true;

					this.cadastroPessoa.get(i).setDataAlteracao(getDataHora());

					switch (campoAlterado) {

						case 1: this.cadastroPessoa.get(i).setNome(novoValor); break;
						case 2: this.cadastroPessoa.get(i).setTelefone(novoValor); break;
						case 3: this.cadastroPessoa.get(i).setDataNascimento(novoValor); break;
					}
						
				} 
			}

			if (encontrouCodigo == false) { //Se não encontrar o codigo em pessoas procura em alunos

				for (int i=0; i < this.cadastroAluno.size(); i++) {

					if (codigo == this.cadastroAluno.get(i).getCodigo()) {
						
						this.cadastroAluno.get(i).setDataAlteracao(getDataHora());

						switch (campoAlterado) {

							case 1: this.cadastroAluno.get(i).setNome(novoValor); break;
							case 2: this.cadastroAluno.get(i).setTelefone(novoValor); break;
							case 3: this.cadastroAluno.get(i).setDataNascimento(novoValor); break;
							case 4: this.cadastroAluno.get(i).setNota(Integer.parseInt(novoValor)); break;
						
						}
					} 
				} 
			}
		} 
	}

	
	public void excluirRegristo(int codigo) {

		boolean encontrouCodigo = false;
		
		if (codigo > 0) {

			//Procura o codigo dentro do cadastro de pessoas
			for (int i=0; i < this.cadastroPessoa.size(); i++) {

				if (codigo == this.cadastroPessoa.get(i).getCodigo()) {
					
					this.cadastroPessoa.remove(i);
					encontrouCodigo = true;

				} 
			}

			if (encontrouCodigo == false) { //Se não encontrar o codigo em pessoas procura em alunos

				for (int i=0; i < this.cadastroAluno.size(); i++) {

					if (codigo == this.cadastroAluno.get(i).getCodigo()) {
						this.cadastroAluno.remove(i);

					}
				} 
			}
		} 
	}

	public String listarRegistros() {
		
		String lista = "";

		ArrayList<pessoa> listaOrdenada = new ArrayList<pessoa>();

		for (int i=0; i < this.cadastroPessoa.size(); i++){
			listaOrdenada.add(this.cadastroPessoa.get(i));
		}

		for (int i=0; i < this.cadastroAluno.size(); i++){
			listaOrdenada.add(this.cadastroAluno.get(i));
		}

		Collections.sort(listaOrdenada);

		
		if (listaOrdenada.isEmpty()) {
			
			lista = "*** Nenhum registro cadastrado ***";

		} else {
		
			for (int i=0; i < listaOrdenada.size(); i++){
					
				lista =  lista  + " > Cod: " + listaOrdenada.get(i).getCodigo() 
								+ " - Nome: " + listaOrdenada.get(i).getNome() 
								+ " - Telefone: " + listaOrdenada.get(i).getTelefone() 
								+ " - Data Nascimento: " + listaOrdenada.get(i).getDataNascimento() + "\r\n";
	
					for (int j=0; j < this.cadastroAluno.size(); j++) {
	
						if (this.cadastroAluno.get(j).getCodigo() == listaOrdenada.get(i).getCodigo()) {
	
							lista =  lista + " Nota Final: " + this.cadastroAluno.get(j).getNota() + " (ALUNO)" + "\r\n";	
	
						}
					}
				
				lista =  lista + " Data Cadastro: " + listaOrdenada.get(i).getDataCadastro()
							   + " - Data alteracao: " + listaOrdenada.get(i).getDataAlteracao() + "\r\n" + "\r\n";
			}

		}

		return lista;

	}	

}