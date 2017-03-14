package produto;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestRepositorioProdutos {
	
	private RepositorioProdutoArrayList repositorio;
	
	@Before
	public void setUp() {
		this.repositorio = new RepositorioProdutoArrayList(10);
	}
	
	@Test
	public void testExiste() {
		assertFalse(repositorio.existe(5));
	}

	@Test
	public void testInserir() {
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijão carioca"));
		assertNotNull(repositorio.existe(5));
	}

	@Test
	public void testAtualizar() {
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijão carioca"));
		repositorio.atualizar(new Produto(5,"Feijao",10.0,"Feijão macassar"));
		assertEquals("Feijão macassar",repositorio.procurar(5).getDescricao());
	}

	@Test
	public void testRemover() {
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijão carioca"));
		repositorio.remover(5);
		assertFalse(repositorio.existe(5));
	}

	@Test
	public void testProcurar() {
		try{
			assertNull(repositorio.procurar(5));
		}catch(RuntimeException e){
			assertEquals("Produto inexistente.", e.getMessage());
		}
		repositorio.inserir(new Produto(5,"Feijao",10.0,"Feijão carioca"));
		assertNotNull(repositorio.procurar(5));		
	}
	
	@Test
	public void tostring(){
		ProdutoNaoPerecivel um = new ProdutoNaoPerecivel(111, "a", 55, "rajada", "aaS");
		ProdutoNaoPerecivel dois = new ProdutoNaoPerecivel(222, "a", 55, "rajada", "aaS");
		ProdutoNaoPerecivel tres = new ProdutoNaoPerecivel(333, "a", 55, "rajada", "aaS");
		ProdutoNaoPerecivel quatro = new ProdutoNaoPerecivel(444, "a", 55, "rajada", "aaS");
		ProdutoNaoPerecivel cinco = new ProdutoNaoPerecivel(555, "a", 55, "rajada", "aaS");

		repositorio.inserir(um);
		repositorio.inserir(dois);
		repositorio.inserir(tres);
		repositorio.inserir(quatro);
		repositorio.inserir(cinco);
		repositorio.inserir(tres);
		
		assertEquals("[Codigo: 111, Codigo: 222, Codigo: 333, Codigo: 444, Codigo: 555, Codigo: 333]",
				repositorio.toString());
		
		repositorio.remover(222);
		repositorio.remover(333);
		
		assertEquals("[Codigo: 111, Codigo: 444, Codigo: 555, Codigo: 333]",
				repositorio.toString());
	}
}
