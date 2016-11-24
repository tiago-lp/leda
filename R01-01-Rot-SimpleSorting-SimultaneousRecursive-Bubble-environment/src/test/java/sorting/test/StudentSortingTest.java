package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.simpleSorting.*;
import sorting.variationsOfBubblesort.*;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] vetorSimples;
	private Integer[] vetorComNulos;
	private Integer[] vetorSoNulos;
	private Integer[] vetorInverso;
	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });
		populaVetorSimples(new Integer[] {9, 1, 2, 3, 4, 5, -3});
		populaVetorComNulos(new Integer[]{null, 9,2,3,4,-2,3,null});
		populaVetorSoNulos(new Integer[] {null, null, null, null, null});
		populaVetorInverso(new Integer[] {10,9,8,7,6,5,4,3,2,1,0,-1,-2,-3});
		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		// TODO O aluno deve instanciar sua implementação abaixo ao invés de
		// null
		this.implementation = new SimultaneousRecursiveBubblesort<Integer>();
		//Assert.fail("Implementation not provided");
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	public void populaVetorSimples(Integer[] arrayPadrao) {
		this.vetorSimples = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	private void populaVetorComNulos(Integer[] arrayPadrao) {
		this.vetorComNulos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	private void populaVetorSoNulos(Integer[] arrayPadrao) {
		this.vetorSoNulos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	private void populaVetorInverso(Integer[] arrayPadrao) {
		this.vetorInverso = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	
	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = Arrays.copyOf(array, array.length);
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}
	
	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
	@Test
	public void testInverso() {
		genericTest(vetorInverso);
	}
	@Test
	public void testSort06() {
		genericTest(vetorSimples);
	}
	
	@Test
	public void testSort07() {
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[null, 9, 2, 3, 4, -2, 3, null]");
		implementation.sort(vetorComNulos);
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[-2, 2, 3, 3, 4, 9, null, null]");
	}
	
	@Test
	public void testSort08() {
		Assert.assertEquals(Arrays.toString(vetorSoNulos), "[null, null, null, null, null]");
		implementation.sort(vetorSoNulos);
		Assert.assertEquals(Arrays.toString(vetorSoNulos), "[null, null, null, null, null]");
	}
	
	@Test
	public void testSort09() {
		Assert.assertEquals(Arrays.toString(vetorTamImpar), "[6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36]");
		implementation.sort(vetorTamImpar, 3, 8);
		Assert.assertEquals(Arrays.toString(vetorTamImpar), "[6, 41, 32, 4, 7, 11, 26, 37, 49, 18, 36]");
	}
	
	@Test
	public void testSort10() {
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[null, 9, 2, 3, 4, -2, 3, null]");
		implementation.sort(vetorComNulos, 1, 6);
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[null, -2, 2, 3, 3, 4, 9, null]");
	}
	
	// se os leftIndice for menor que zero ele ordena do indice 0 ate o rightIndex
	// se o rightIndex for maior que o tamanho do array, ele ordena do leftIndex ate o fim do array
	// se o leftIndex for menor que zero e o rightIndex maior que o array ele ordena todo o array.
	@Test
	public void testSort11() {
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[null, 9, 2, 3, 4, -2, 3, null]");
		implementation.sort(vetorComNulos,-5, 10000);
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[-2, 2, 3, 3, 4, 9, null, null]");
	}
	
	// se o leftIndex for maior que o rightIndex o intervalo eh invalido e o array nao ordena
	@Test
	public void testSort12() {
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[null, 9, 2, 3, 4, -2, 3, null]");
		implementation.sort(vetorComNulos, 7, 1);
		Assert.assertEquals(Arrays.toString(vetorComNulos), "[null, 9, 2, 3, 4, -2, 3, null]");
	}
	
	@Test
	public void testVazioComIntervalo() {
		implementation.sort(vetorVazio,2, 8);
		Assert.assertEquals(Arrays.toString(vetorVazio), "[]");
	}
}