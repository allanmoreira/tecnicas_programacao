package negocio;

public class DiscountCodeDAOException extends Exception {
	public DiscountCodeDAOException(){
		super();
	}
	public DiscountCodeDAOException(String mensagem) {
		super(mensagem);
	}
	public DiscountCodeDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
