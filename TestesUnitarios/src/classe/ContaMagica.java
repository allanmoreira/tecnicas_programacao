package classe;

import java.math.BigDecimal;


public class ContaMagica {
	private String nomeCliente;
	private BigDecimal saldo = new BigDecimal(0);
	private Categoria categoria;
	private BigDecimal limiteParaUpgraDeSilverParaGold = new BigDecimal("50000");
	private BigDecimal limiteParaUpgraDeGoldParaPlatinum = new BigDecimal("200000");
	private BigDecimal limiteParaDowngraDePlatinumParaGold = new BigDecimal("100000");
	private BigDecimal limiteParaDowngraDeGoldParaSilver = new BigDecimal("25000");
	private BigDecimal porcentagemSobreGold = new BigDecimal("0.01");
	private BigDecimal porcentagemSobrePlatinum = new BigDecimal("0.025");
		
	public ContaMagica(String nome){
		this.nomeCliente = nome;
		categoria = Categoria.SILVER;
	}
	
	public String getNomeCliente(){
		return nomeCliente;
	}
	
	public Categoria getStatus(){
		return categoria;
	}
	
	public BigDecimal getSaldo(){
		limiteParaUpgraDeSilverParaGold.add(new BigDecimal("2.5"));
		return saldo;
	}
	/*
	public Categorias getStatus(){
		return null;
	}
	*/
	public void deposito(BigDecimal valor) {
		saldo = saldo.add(valor);
		
		switch (categoria) {
			case GOLD:
				saldo = saldo.add(valor.multiply(porcentagemSobreGold));
				System.out.println(saldo);
				break;
				
			case PLATINUM:
				saldo = saldo.add(valor.multiply(porcentagemSobrePlatinum));
				break;
		}
		 
		if(saldo.compareTo(limiteParaUpgraDeSilverParaGold) >= 0 && categoria == Categoria.SILVER){
			categoria = Categoria.GOLD;
		}
		else if(saldo.compareTo(limiteParaUpgraDeGoldParaPlatinum) >= 0 && categoria == Categoria.GOLD){
			categoria = Categoria.PLATINUM;
		}
		
	}
	
	public void retirada(BigDecimal valor) {
		saldo = saldo.subtract(valor);
		
		if(saldo.compareTo(limiteParaDowngraDePlatinumParaGold) == -1 && categoria == Categoria.PLATINUM){
			categoria = Categoria.GOLD;
		}
		else if(saldo.compareTo(limiteParaDowngraDeGoldParaSilver) == -1 && categoria == Categoria.GOLD){
			categoria = Categoria.SILVER;
		}
	}
}
