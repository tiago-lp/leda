package produto;

import java.util.ArrayList;

/**
 * Classe que representa um repositório de produtos usando ArrayList como
 * estrutura sobrejacente. Alguns métodos (atualizar, remover e procurar) ou
 * executam com sucesso ou retornam um erro. Para o caso desde exercício, o erro
 * será representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 *
 * @author Adalberto
 */
public class RepositorioProdutoArrayList{

	/**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar
	 * por enquanto com o uso de generics em ArrayList.
	 */
	private ArrayList<Produto> produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList<Produto>();
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
		for(int i=0; i < produtos.size(); i++){
			if(produtos.get(i).getCodigo() == codigo){
				return i;
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
		if(produto != null){
			this.produtos.add(produto);
			this.index += 1;
		}
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto){
		int codigo = produto.getCodigo();
		if(!existe(codigo)){
			throw new RuntimeException("Produto inexistente.");
		}
		int indice = procurarIndice(codigo);
		this.produtos.set(indice, produto);
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
		this.produtos.remove(indice);
		this.index -= 1;
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		if(existe(codigo)){
			int indice = procurarIndice(codigo);
			return this.produtos.get(indice);
		}else{
			throw new RuntimeException("Produto inexistente.");
		}
		
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i = 0; i < this.produtos.size(); i++){
			if(i < this.produtos.size() -1){
				s.append(this.produtos.get(i));
				s.append(", ");
			}else{
				s.append(this.produtos.get(this.index));
			}
		}
		
		s.append("]");
		
		return s.toString();
	}
}
