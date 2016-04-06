package testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classe.Categoria;
import classe.ContaMagica;

public class ContaMagicaTest {
	ContaMagica cm;

	@Before
	public void setUp(){
		cm = new ContaMagica("Allan Moreira");
	}

    public void a(){
        cm.getSaldo();
    }

	@After
	public void tearDown(){
	}
	
	@Test
	public void testConstrutor(){
		assertEquals("Allan Moreira", cm.getNomeCliente());
        assertEquals(Categoria.SILVER, cm.getStatus());
	}


    @Test
	public void testSaldo() {
        cm.deposito(new BigDecimal(500));
        cm.deposito(new BigDecimal(500));
		assertEquals(1000, cm.getSaldo().doubleValue(), 0.001);
	}

	@Test
	public void testGetSaldoSaque() {
        cm.deposito(new BigDecimal(500));
        cm.retirada(new BigDecimal(400));
		assertEquals(100, cm.getSaldo().doubleValue(), 0.001);
	}

    /*
    * Atingindo 50.000 ou mais, muda para gold
    * */
    @Test
    public void testUpgradeParaGold_1(){
        cm.deposito(new BigDecimal(50000));
        assertEquals(Categoria.GOLD, cm.getStatus());
    }

    /*
    * Não é explícito no texto se quando o depósito for maior do que 200.000 o cliente passa direto de Silver para Platinum
    * ou se não pode passar duas categorias de uma única vez
    * */
    // TODO cenário 5
    @Test
    public void testUpgradeParaGold_2(){
        cm.deposito(new BigDecimal(1000000));
        assertEquals(Categoria.PLATINUM, cm.getStatus());
    }

    /*
    * Ao atingir o saldo de 200.000, a categoria passa para Platinum
    * */
    @Test
    public void testUpgradeParaPlatinum(){
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        assertEquals(Categoria.PLATINUM, cm.getStatus());
    }

    /*
    * Ao fazer um depósito na categoria Silver, não há porcentagem em cima do valor depositado
    * */
    @Test
    public void testPorcentagemCategoriaSilver() {
        cm.deposito(new BigDecimal(100));
        assertEquals(100, cm.getSaldo().doubleValue(), 0.001);
    }

    /*
    * Ao realizar um depósito para um cliente da categoria Gold, o valor depositado é valorizado em 1%.
    * Exemplo: Ao depositar 100 reais, o valor creditado será de 101 reais.
    * */
    @Test
    public void testPorcentagemCategoriaGold_1() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(100));
        assertEquals(50101, cm.getSaldo().doubleValue(), 0.001);
    }

    /*
    * Ao realizar um depósito para um cliente da categoria Gold, o valor depositado é valorizado em 1%. E para um cliente Platinum, 2,5%.
    * Ao depositar 150000 reais, o cliente passa de Gold para Platinum. Qual porcentagem aplicar, a anterior ou a nova?
    * No caso abaixo, foi aplicado a porcentagem de 1% para depois migrar de categoria.
    * */
    // TODO cenário 8
    @Test
    public void testPorcentagemCategoriaGold_2() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        assertEquals(201500, cm.getSaldo().doubleValue(), 0.001);
    }

    /*
    * Ao realizar um depósito para um cliente da categoria Gold, o valor depositado é valorizado em 2,5%.
    * Exemplo: Ao depositar 100 reais, o valor creditado será de 102,50 reais (o saldo anterior ao depósito de 100 reais era de 201.500 reais)
    * */
    @Test
    public void testPorcentagemCategoriaPlatinum() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        cm.deposito(new BigDecimal(100));
        assertEquals(201602.5, cm.getSaldo().doubleValue(), 0.001);
    }

    @Test
    public void testSaque() {
        cm.deposito(new BigDecimal(500));
        cm.retirada(new BigDecimal(100));
        assertEquals(400, cm.getSaldo().doubleValue(), 0.001);
    }

    /*
    * Se o saldo cai abaixo de 100.000 reais, o cliente PLatinum cai para Gold.
    * Deposita até chegar à categoria Platinum, com saldo de 201.500.
    * Ao sacar 120.000 reais, o saldo fica em 81.500 reais
    */
    @Test
    public void testDowngradeParaGold() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        cm.retirada(new BigDecimal(102000));
        assertEquals(99500, cm.getSaldo().doubleValue(), 0.001);
        assertEquals(Categoria.GOLD, cm.getStatus());
    }

    /*
    * Um cliente nunca perde duas categorias em uma única operação de saque, mesmo que o saldo caia abaixo de 25.000 reais.
    * Deposita até chegar à categoria Platinum, com saldo de 201.500.
    * Ao sacar 177.500 reais, o saldo fica em 24.000 reais
    */
    @Test
    public void testDowngradeParaGoldSemPularCategoria() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        cm.retirada(new BigDecimal(177500));
        assertEquals(24000, cm.getSaldo().doubleValue(), 0.001);
        assertEquals(Categoria.GOLD, cm.getStatus());
    }

    /*
    * Um cliente nunca perde duas categorias em uma única operação de saque, mesmo que o saldo caia abaixo de 25.000 reais.
    * Deposita até chegar à categoria Platinum, com saldo de 201.500.
    * Ao sacar 171.500 reais, o saldo fica em 30.000 reais, e cliente cai para Gold.
    * Sacando 6.000 reais, o saldo vai para 24.000, e o cliente cai para Silver.
    */
    /*
    @Test
    public void testDowngradeParaSilverPartindoDePlatinum() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        cm.retirada(new BigDecimal(171500));
        cm.retirada(new BigDecimal(6000));
        assertEquals(Categoria.SILVER, cm.getStatus());
    }
    */

    @Test
    public void testDowngradeParaSilverPartindoDeGold() {
        cm.deposito(new BigDecimal(50000));
        cm.retirada(new BigDecimal(26000));
        assertEquals(24000, cm.getSaldo().doubleValue(), 0.001);
        assertEquals(Categoria.SILVER, cm.getStatus());
    }

    /*
    * Não é indicada que deve haver validação de tentativa de saque sem saldo, podendo o saque ficar negativo
    * */
    @Test
    public void testSaqueSaldoIndisponivel() {
        cm.deposito(new BigDecimal(100));
        cm.retirada(new BigDecimal(200));
        assertEquals(5, cm.getSaldo().doubleValue(), 0.001);
    }
}
