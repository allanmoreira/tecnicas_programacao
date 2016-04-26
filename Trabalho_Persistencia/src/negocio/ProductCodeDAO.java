package negocio;

import java.util.List;

public interface ProductCodeDAO {
	List<ProductCode> buscarTodos() throws ProductCodeDAOException;
	ProductCode buscarPorCodigo(int codigo) throws ProductCodeDAOException;
	void inserir(ProductCode editora) throws ProductCodeDAOException;
	void alterar(ProductCode editora) throws ProductCodeDAOException;
}
