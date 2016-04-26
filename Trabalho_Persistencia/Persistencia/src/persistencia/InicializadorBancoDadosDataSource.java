/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 *
 * @author Júlio
 */
public class InicializadorBancoDadosDataSource {
    
    public static String STRING_CONEXAO_BD = "jdbc:derby://localhost:1527/sample;create=true";
    public static String NOME_BD = "sample";
    public static String USUARIO_BD = "app";
    public static String SENHA_BD = "app";
    private static DataSource dataSource;
    
    public static Connection conectarBd() throws Exception {
        return getConexaoViaDriverManager();
    }
    
    private static Connection getConexaoViaDriverManager() throws Exception {
        return DriverManager.getConnection(STRING_CONEXAO_BD, USUARIO_BD, SENHA_BD);
    }
    /*
    public static void criarBd() throws Exception {
        try (Connection connection = getConexaoViaDriverManager();
                Statement statement = connection.createStatement()) {
            String sqlCreateTableCustomer = "CREATE TABLE CUSTOMER " + 
                                "( " + 
                                   "CUSTOMER_ID int PRIMARY KEY NOT NULL, " + 
                                   "DISCOUNT_CODE char(1) NOT NULL, " + 
                                   "ZIP varchar(10) NOT NULL, " + 
                                   "NAME varchar(30), " + 
                                   "ADDRESSLINE1 varchar(30), " + 
                                   "ADDRESSLINE2 varchar(30), " + 
                                   "CITY varchar(25), " + 
                                   "STATE char(2), " + 
                                   "PHONE char(12), " + 
                                   "FAX char(12), " + 
                                   "EMAIL varchar(40), " + 
                                   "CREDIT_LIMIT int " + 
                                ")";
            statement.executeUpdate(sqlCreateTableCustomer);
        }
    }
    */
    
}
