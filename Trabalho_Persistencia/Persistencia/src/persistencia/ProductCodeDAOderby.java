package persistencia;

import negocio.ProductCodeDAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.ProductCode;
import negocio.DiscountCodeDAO;

/**
 *
 * @author aa
 */
public class ProductCodeDAOderby implements DiscountCodeDAO {

    @Override
    public List<ProductCode> buscarTodos() throws ProductCodeDAOException {
        // TODO implementar código
        return null;
    }

    @Override
    public ProductCode buscarPorCodigo(int codigo) throws ProductCodeDAOException {
        // TODO implementar código
        return null;
    }

    @Override
    public void inserir(ProductCode ed) throws ProductCodeDAOException {
        // TODO implementar código
    }

    @Override
    public void alterar(ProductCode ed) throws ProductCodeDAOException {
        // TODO implementar código
    }
}
