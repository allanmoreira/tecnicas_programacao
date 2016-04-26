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
public class DiscountCodeDAOderby implements DiscountCodeDAO {

    @Override
    public List<ProductCode> buscarTodos() throws ProductCodeDAOException {
        List<ProductCode> editoras = new ArrayList<>();
        String sql = "select * from CUSTOMER";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (Statement comando = conexao.createStatement()) {
                try (ResultSet resultado = comando.executeQuery(sql)) {
                    while (resultado.next()) {
//                        Editora editora = new Editora(
//                                resultado.getInt("codigo"),
//                                resultado.getString("nome")
//                        );
//                        editoras.add(editora);
                        System.out.println(resultado.getString("NAME"));
                    }
                    return editoras;
                }
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na busca", e);
        }
    }

    @Override
    public ProductCode buscarPorCodigo(int codigo) throws ProductCodeDAOException {
        String sql = "select * from editoras where codigo = ?";
        ProductCode ed = null;
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, codigo);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        ed = new ProductCode(
                                resultado.getInt("codigo"),
                                resultado.getString("nome")
                        );
                    }
                    return ed;
                }
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na busca", e);
        }
    }

    @Override
    public void inserir(ProductCode ed) throws ProductCodeDAOException {
        String sql = "insert into editoras(codigo,nome) values(?,?)";
        int resultado = 0;
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, ed.getCodigo());
                comando.setString(2, ed.getNome());
                resultado = comando.executeUpdate();
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na inserção", e);
        }
        if (resultado == 0) {
            throw new ProductCodeDAOException("Falha na inserção");
        }
    }

    @Override
    public void alterar(ProductCode ed) throws ProductCodeDAOException {
        String sql = "update editoras set nome=? where codigo=?";
        int resultado = 0;
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, ed.getNome());
                comando.setInt(2, ed.getCodigo());
                resultado = comando.executeUpdate();
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na alteração", e);
        }
        if (resultado == 0) {
            throw new ProductCodeDAOException("Falha na alteração");
        }
    }
}
