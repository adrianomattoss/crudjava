import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;

public class pessoa {//FAZER FORMATAÇÕES AQUI!

	private int codigo = 0;//, ultimoCodigo = 0;
	protected String nome = "" , telefone = "", dataNascimento = "", dataCadastro = "", dataAlteracao = "";

	public int getCodigo() { return this.codigo;}
	//public int getUltimoCodigo() { return this.ultimoCodigo;}
	public String getNome() { return this.nome; }
	public String getTelefone() { return this.telefone; }
	public String getDataNascimento() { return this.dataNascimento; }
	public String getDataCadastro() { return this.dataCadastro; }
	public String getDataAlteracao() { return this.dataAlteracao; }

	//fazer if de validação, digitar numero 
	public void setCodigo (int codigo) { this.codigo = codigo; }
	//public void setUltimoCodigo (int ultimoCodigo) { this.ultimoCodigo = ultimoCodigo; }
	public void setNome (String nome) { this.nome = nome; }
	public void setTelefone (String telefone) { 
		
		/*int numero = 0; 

		try 
		{
			numero = Integer.parseInt(telefone);
			this.telefone = Integer.toString(numero);
		
		} catch(NumberFormatException e) {

			this.telefone = "Invalido";
			System.out.println("Numero telefone invalido, deve ser inserido apenas numeros.");
			

		} */

		//this.telefone = Integer.toString(numero);	
		 
		this.telefone = telefone; 
	}
	public void setDataNascimento (String dataNascimento) { this.dataNascimento = dataNascimento; }
	public void setDataCadastro (String dataCadastro) { this.dataCadastro = dataCadastro; }
	public void setDataAlteracao (String dataAlteracao) { this.dataAlteracao = dataAlteracao; }

}