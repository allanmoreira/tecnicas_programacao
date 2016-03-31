package classe;

import java.math.BigDecimal;


public class ContaMagica {
	private String nomeCliente;
	private BigDecimal saldo;
		
	public ContaMagica(String nome){
		this.nomeCliente = nome;
	}
	
	public String getNomeCliente(){
		return nomeCliente;
	}
	
	public BigDecimal getSaldo(){
		return saldo;
	}
	/*
	public Categorias getStatus(){
		return null;
	}
	*/
	public void deposito(BigDecimal valor) {
		
	}
	
	public void retirada(BigDecimal valor) {
		
	}
}
