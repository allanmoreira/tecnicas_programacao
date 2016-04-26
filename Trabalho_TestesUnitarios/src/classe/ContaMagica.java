package classe;

import java.math.BigDecimal;

public class ContaMagica {
	private String nomeCliente;
	private BigDecimal saldo = new BigDecimal(0);
	private Categoria categoria;

	private final BigDecimal limiteParaUpgraDeSilverParaGold = new BigDecimal("50000");
	private final BigDecimal limiteParaUpgraDeGoldParaPlatinum = new BigDecimal("200000");
	private final BigDecimal limiteParaDowngraDePlatinumParaGold = new BigDecimal("100000");
	private final BigDecimal limiteParaDowngraDeGoldParaSilver = new BigDecimal("25000");
	private final BigDecimal porcentagemSobreGold = new BigDecimal("0.01");
	private final BigDecimal porcentagemSobrePlatinum = new BigDecimal("0.025");
		
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

	public void deposito(BigDecimal valor) {
		saldo = saldo.add(valor);
		
		switch (categoria) {
			case GOLD:
				saldo = saldo.add(valor.multiply(porcentagemSobreGold));
				break;
				
			case PLATINUM:
				saldo = saldo.add(valor.multiply(porcentagemSobrePlatinum));
				break;
		}
		 
		if(saldo.compareTo(limiteParaUpgraDeSilverParaGold) >= 0 && categoria == Categoria.SILVER){
			categoria = Categoria.GOLD;
		}
		if(saldo.compareTo(limiteParaUpgraDeGoldParaPlatinum) >= 0 && categoria == Categoria.GOLD){
			categoria = Categoria.PLATINUM;
		}
		
	}

	public void retirada(BigDecimal valor) {
		if(valor.compareTo(getSaldo()) >= 0){
			throw new IllegalArgumentException("O valor informado é menor do que o saldo.");
		}
		else {
			saldo = saldo.subtract(valor);

			if(saldo.compareTo(limiteParaDowngraDePlatinumParaGold) == -1 && categoria == Categoria.PLATINUM){
				categoria = Categoria.GOLD;
			}
			else if(saldo.compareTo(limiteParaDowngraDeGoldParaSilver) == -1 && categoria == Categoria.GOLD){
				categoria = Categoria.SILVER;
			}
		}
	}
}
