package negocio;

import java.util.List;

public interface ProductCodeDAO {
	List<ProductCode> buscarTodos() throws ProductCodeDAOException;
	ProductCode buscarPorCodigoDeProductCode(String codigo) throws ProductCodeDAOException;
        List<ProductCode> filtrarProductCodePorCodigoDeDiscountCode(String codigo) throws ProductCodeDAOException;
	void inserir(ProductCode editora) throws ProductCodeDAOException;
}
