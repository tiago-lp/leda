package produto;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoPerecivelArray{
	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoPerecivel[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoPerecivelArray(int size) {
		this.produtos = new ProdutoPerecivel[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		if(this.getIndex() > -1){
			for(int i = 0; i < this.index; i++){
				if(this.getProdutos()[i].getCodigo() == codigo){
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		if(procurarIndice(codigo) != -1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(Produto produto) {
		setIndex(this.index + 1);
		this.getProdutos()[this.getIndex()] = produto;
		
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto) {
		int codigo = produto.getCodigo();
		if(!existe(codigo)){
			throw new RuntimeException("Produto inexistente.");
		}
		int indice = procurarIndice(codigo);
		this.getProdutos()[indice] = produto;
		
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		if(!existe(codigo)){
			throw new RuntimeException("Produto inexistente.");
		}
		int indice = procurarIndice(codigo);
		for(int i = indice; i < this.getProdutos().length -1; i++){
			this.getProdutos()[i] = this.getProdutos()[i+1];
		}
		setIndex(this.getIndex() - 1);
	}


	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		if(!existe(codigo)){
			throw new RuntimeException("Produto inexistente.");
		}
		int indice = procurarIndice(codigo);
		return this.getProdutos()[indice];
		
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i = 0; i < this.getIndex(); i++){
			s.append(this.getProdutos()[i]);
			s.append(", ");
		}
		if(this.index > -1){
			s.append(this.getProdutos()[this.index]);
		}
		s.append("]");
		
		return s.toString();
	}
	
	public Produto[] getProdutos(){
		return this.produtos;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public void setIndex(int ind){
		this.index = ind;
	}
}
