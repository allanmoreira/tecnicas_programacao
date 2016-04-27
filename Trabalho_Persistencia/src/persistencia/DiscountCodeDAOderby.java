package persistencia;

import negocio.DiscountCodeDAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.DiscountCode;
import negocio.DiscountCodeDAO;

/**
 *
 * @author aa
 */
public class DiscountCodeDAOderby implements DiscountCodeDAO {

    @Override
    public List<DiscountCode> buscarTodos() throws DiscountCodeDAOException {
        List<DiscountCode> listaDiscountCode = new ArrayList<>();
        String sql = "select * from DISCOUNT_CODE";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (Statement comando = conexao.createStatement()) {
                try (ResultSet resultado = comando.executeQuery(sql)) {
                    while (resultado.next()) {
                        DiscountCode dc = new DiscountCode(
                                resultado.getString("DISCOUNT_CODE"),
                                resultado.getBigDecimal("RATE")
                        );
                        listaDiscountCode.add(dc);
                    }
                    return listaDiscountCode;
                }
            }
        } catch (Exception e) {
            throw new DiscountCodeDAOException("Falha na busca", e);
        }
    }

    @Override
    public DiscountCode buscarPorCodigo(String codigo) throws DiscountCodeDAOException {
        String sql = "select * from DISCOUNT_CODE where DISCOUNT_CODE = ?";
        DiscountCode ed = null;
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, codigo);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        ed = new DiscountCode(
                                resultado.getString("DISCOUNT_CODE"),
                                resultado.getBigDecimal("RATE")
                        );
                    }
                    return ed;
                }
            }
        } catch (Exception e) {
            throw new DiscountCodeDAOException("Falha na busca", e);
        }
    }

    @Override
    public void inserir(DiscountCode ed) throws DiscountCodeDAOException {
        String sql = "insert into DISCOUNT_CODE (DISCOUNT_CODE, RATE) values(?,?)";
        int resultado = 0;
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, ed.getDiscountCode());
                comando.setBigDecimal(2, ed.getRate());
                resultado = comando.executeUpdate();
            }
        } catch (Exception e) {
            throw new DiscountCodeDAOException("Falha na inserção", e);
        }
        if (resultado == 0) {
            throw new DiscountCodeDAOException("Falha na inserção");
        }
    }
}
