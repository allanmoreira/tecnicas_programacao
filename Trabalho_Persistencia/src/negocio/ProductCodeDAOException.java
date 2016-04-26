package negocio;

public class ProductCodeDAOException extends Exception {
	public ProductCodeDAOException(){
		super();
	}
	public ProductCodeDAOException(String mensagem) {
		super(mensagem);
	}
	public ProductCodeDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
