package produto;

public class Produto {

	private int codigo;
	private String nome;
	private double preco;
	private String descricao;

	public Produto(int codigo, String nome, double preco, String descricao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * A redefinição é necessaria para permitir que objetos sejam comparados
	 * quando usados em coleções.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Produto) {
			Produto outro = (Produto) obj;
			if(outro.getCodigo() == this.getCodigo()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public String toString(){
		return "Codigo: " + this.getCodigo();
	}

}
