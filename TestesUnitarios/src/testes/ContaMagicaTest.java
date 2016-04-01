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
		cm = new ContaMagica("Ronaldo Moreira");
	}

	@After
	public void tearDown(){
	}
	
	@Test
	public void testNomeCliente(){
		assertEquals("Ronaldo Moreira", cm.getNomeCliente());
	}

	@Test
	public void testGetSaldo() {
        cm.deposito(new BigDecimal(500));
		assertEquals(500, cm.getSaldo().doubleValue(), 0.001);
	}
	
	@Test
	public void testGetStatus(){
        cm = new ContaMagica("João");
		assertEquals(Categoria.SILVER, cm.getStatus());
	}

    @Test
    public void testUpgradeParaGold(){
        cm.deposito(new BigDecimal(50000));
        assertEquals(Categoria.GOLD, cm.getStatus());
    }

    /*
        segundo a sentença, quando o saldo da conta atinge ou ultrapassa R$ 50.000,00,
        o cliente passa para a categoria Gold. Ou seja, mesmo que ultrapasse o valor,
        passa primeiro para gold
     */
    @Test
    public void testUpgradeParaGoldSemPularCategoria(){
        cm.deposito(new BigDecimal(1000000));
        assertEquals(Categoria.GOLD, cm.getStatus());
    }

    @Test
    public void testUpgradeParaPlatinum(){
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        assertEquals(Categoria.PLATINUM, cm.getStatus());
    }

    @Test
    public void testPorcentagemCategoriaSilver() {
        cm.deposito(new BigDecimal(100));
        assertEquals(100, cm.getSaldo().doubleValue(), 0.001);
    }

    @Test
    public void testPorcentagemCategoriaGold() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(100));
        assertEquals(50101, cm.getSaldo().doubleValue(), 0.001);
    }

    // TODO ver a possibilidade de alterar este método, validando somente a entrada que a porcentagem de platinum incide
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
        cm.retirada(new BigDecimal(150));
        assertEquals(350, cm.getSaldo().doubleValue(), 0.001);
    }

    @Test
    public void testDowngradeParaGold() {
        cm.deposito(new BigDecimal(50000));
        cm.deposito(new BigDecimal(150000));
        cm.retirada(new BigDecimal(100));
        assertEquals(199900, cm.getSaldo().doubleValue(), 0.001);
    }
}
