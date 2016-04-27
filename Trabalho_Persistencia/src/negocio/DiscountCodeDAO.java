package negocio;

import java.util.List;

public interface DiscountCodeDAO {
	List<DiscountCode> buscarTodos() throws DiscountCodeDAOException;
	DiscountCode buscarPorCodigo(String codigo) throws DiscountCodeDAOException;
	void inserir(DiscountCode discountCode) throws DiscountCodeDAOException;
}
