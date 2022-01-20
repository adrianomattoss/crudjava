//package projetocrud.classes;
package classes;

public class pessoa implements Comparable<pessoa> {//FAZER FORMATAÇÕES AQUI!

	private int codigo ;
	protected String nome, telefone, dataNascimento, dataCadastro, dataAlteracao;

	public int getCodigo() { return this.codigo;}

	public String getNome() { return this.nome; }
	public String getTelefone() { return this.telefone; }
	public String getDataNascimento() { return this.dataNascimento; }
	public String getDataCadastro() { return this.dataCadastro; }
	public String getDataAlteracao() { return this.dataAlteracao; }

	public void setCodigo (int codigo) { this.codigo = codigo; }
	public void setNome (String nome) { this.nome = nome; }
	public void setTelefone (String telefone) { this.telefone = telefone; }
	public void setDataNascimento (String dataNascimento) { this.dataNascimento = dataNascimento; }
	public void setDataCadastro (String dataCadastro) { this.dataCadastro = dataCadastro; }
	public void setDataAlteracao (String dataAlteracao) { this.dataAlteracao = dataAlteracao; }


	@Override
	public int compareTo(pessoa outraPessoa) {
		
		if (this.codigo < outraPessoa.getCodigo()){
			return -1;
		
		} else if (this.codigo > outraPessoa.getCodigo()) {
			return 1;
		}

		return 0;
	}
}