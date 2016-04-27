package persistencia;

import negocio.ProductCodeDAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.ProductCode;
import negocio.DiscountCode;
import negocio.ProductCodeDAO;

/**
 *
 * @author 12111151
 */
public class ProductCodeDAOderby implements ProductCodeDAO {
    private DiscountCodeDAOderby discountCodeDAOderby;
    
    public ProductCodeDAOderby() {
        discountCodeDAOderby = new DiscountCodeDAOderby();
    }

    @Override
    public List<ProductCode> buscarTodos() throws ProductCodeDAOException {
        List<ProductCode> listaProductCode = new ArrayList<>();
        DiscountCode dc = null;
        ProductCode pc;
        String sqlProductCode = "select * from PRODUCT_CODE";
        String sqlDiscountCode = "select * from DISCOUNT_CODE where DISCOUNT_CODE = ?";
        
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (Statement comandoProductCode = conexao.createStatement()) {
                try (ResultSet resultadoProductCode = comandoProductCode.executeQuery(sqlProductCode)) {
                    
                    while (resultadoProductCode.next()) {
                        try (PreparedStatement comandoDiscountCode =  conexao.prepareStatement(sqlDiscountCode)) {
                            comandoDiscountCode.setString(1, resultadoProductCode.getString("DISCOUNT_CODE"));
                            try (ResultSet resultadoDiscount = comandoDiscountCode.executeQuery()) {
                                while (resultadoDiscount.next()) {
                                    String disc_code = resultadoDiscount.getString("DISCOUNT_CODE");
                                    dc = discountCodeDAOderby.buscarPorCodigo(disc_code);
                                }
                            }
                        }
                        pc = new ProductCode(
                                resultadoProductCode.getString("PROD_CODE"), 
                                dc, 
                                resultadoProductCode.getString("DESCRIPTION")
                        );
                        listaProductCode.add(pc);
                    }
                    return listaProductCode;
                }
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na busca: " + e.getMessage(), e);
        }                        
    }

    @Override
    public ProductCode buscarPorCodigoDeProductCode(String codigo) throws ProductCodeDAOException {
        DiscountCode dc = null;
        ProductCode pc = null;
        String sqlProductCode = "select * from PRODUCT_CODE where PROD_CODE = ?";
        String sqlDiscountCode = "select * from DISCOUNT_CODE where DISCOUNT_CODE = ?";
        
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comandoProductCode = conexao.prepareStatement(sqlProductCode)) {
                comandoProductCode.setString(1, codigo);
                try (ResultSet resultadoProductCode = comandoProductCode.executeQuery()) {
                    while (resultadoProductCode.next()) {
                        try (PreparedStatement comandoDiscountCode =  conexao.prepareStatement(sqlDiscountCode)) {
                            comandoDiscountCode.setString(1, resultadoProductCode.getString("DISCOUNT_CODE"));
                            try (ResultSet resultadoDiscount = comandoDiscountCode.executeQuery()) {
                                while (resultadoDiscount.next()) {
                                    String disc_code = resultadoDiscount.getString("DISCOUNT_CODE");
                                    dc = discountCodeDAOderby.buscarPorCodigo(disc_code);
                                }
                            }
                        }
                        pc = new ProductCode(
                                resultadoProductCode.getString("PROD_CODE"), 
                                dc, 
                                resultadoProductCode.getString("DESCRIPTION")
                        );
                    }
                    return pc;
                }
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na busca: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<ProductCode> filtrarProductCodePorCodigoDeDiscountCode(String codigo) throws ProductCodeDAOException {
        DiscountCode dc = null;
        ProductCode pc = null;
        String sqlProductCode = "select * from PRODUCT_CODE where DISCOUNT_CODE = ?";
        String sqlDiscountCode = "select * from DISCOUNT_CODE where DISCOUNT_CODE = ?";
        List<ProductCode> listaProductCodes = new ArrayList<>();
        
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comandoProductCode = conexao.prepareStatement(sqlProductCode)) {
                comandoProductCode.setString(1, codigo);
                try (ResultSet resultadoProductCode = comandoProductCode.executeQuery()) {
                    while (resultadoProductCode.next()) {
                        try (PreparedStatement comandoDiscountCode =  conexao.prepareStatement(sqlDiscountCode)) {
                            comandoDiscountCode.setString(1, resultadoProductCode.getString("DISCOUNT_CODE"));
                            try (ResultSet resultadoDiscount = comandoDiscountCode.executeQuery()) {
                                while (resultadoDiscount.next()) {
                                    String disc_code = resultadoDiscount.getString("DISCOUNT_CODE");
                                    dc = discountCodeDAOderby.buscarPorCodigo(disc_code);
                                }
                            }
                        }
                        pc = new ProductCode(
                                resultadoProductCode.getString("PROD_CODE"), 
                                dc, 
                                resultadoProductCode.getString("DESCRIPTION")
                        );
                        listaProductCodes.add(pc);
                    }
                    return listaProductCodes;
                }
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na busca: " + e.getMessage(), e);
        }
    }

    @Override
    public void inserir(ProductCode pc) throws ProductCodeDAOException {
        String sqlProductCode = "insert into PRODUCT_CODE (PROD_CODE, DISCOUNT_CODE, DESCRIPTION) "
                + "values(?,?,?)";
        
        int resultado = 0;
        try {
            // Recupera a editora
            String codDiscountCode = pc.getDiscountCode().getDiscountCode();
            DiscountCode dc = discountCodeDAOderby.buscarPorCodigo(codDiscountCode);
            
            // Executa a inserção do livro propriamente dita
            try(Connection conexao = InicializadorBancoDadosDataSource.conectarBd()){
                // Insere o Livro na tabela Livros
                try(PreparedStatement comandoProductCode = conexao.prepareStatement(sqlProductCode)){
                    comandoProductCode.setString(1, pc.getProdCode());
                    comandoProductCode.setString(2, dc.getDiscountCode());
                    comandoProductCode.setString(3, pc.getDescription());
                    resultado = comandoProductCode.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new ProductCodeDAOException("Falha na inserção", e);
        }
        // Verifica se foi feita a inserção de 1 livro e de "size" referencias
        // para autores na tabela livrosautores
        if(resultado < 1) {
            throw new ProductCodeDAOException("Falha na inserção");
        }
    }

}
