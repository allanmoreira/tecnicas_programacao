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
	public void setUp() throws Exception {
		cm = new ContaMagica("Allan");
//		cm.deposito(new BigDecimal(500));
		cm.deposito(new BigDecimal("50000"));
		cm.deposito(new BigDecimal("100"));
//		cm.deposito(new BigDecimal(100));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSaldo() {
		assertEquals(50000, cm.getSaldo().doubleValue(), 0.01);
	}
	
	@Test
	public void testGetStatus(){
		assertEquals(Categoria.GOLD, cm.getStatus());
	}

}
