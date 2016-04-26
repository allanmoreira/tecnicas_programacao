/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.ProductCodeDAOException;
import persistencia.ProductCodeDAOderby;
import persistencia.InicializadorBancoDadosDataSource;

/**
 *
 * @author 12111151
 */
public class App {
    public static void main (String[] args) throws Exception {
        ProductCodeDAOderby editoraDAOderby = new ProductCodeDAOderby();
        
        editoraDAOderby.buscarTodos();
        
    }
}
