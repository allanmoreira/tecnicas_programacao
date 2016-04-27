/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 12111151
 */
public class InicializadorBancoDadosDataSource {
    
    public static String STRING_CONEXAO_BD = "jdbc:derby://localhost:1527/sample;create=true";
    public static String NOME_BD = "sample";
    public static String USUARIO_BD = "app";
    public static String SENHA_BD = "app";
    
    public static Connection conectarBd() throws Exception {
        return getConexaoViaDriverManager();
    }
    
    private static Connection getConexaoViaDriverManager() throws Exception {
        return DriverManager.getConnection(STRING_CONEXAO_BD, USUARIO_BD, SENHA_BD);
    }
    
}
