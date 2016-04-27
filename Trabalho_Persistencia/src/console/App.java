/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DiscountCode;
import negocio.ProductCode;
import negocio.ProductCodeDAOException;
import persistencia.DiscountCodeDAOderby;
import persistencia.ProductCodeDAOderby;
import persistencia.InicializadorBancoDadosDataSource;

/**
 *
 * @author 12111151
 */
public class App {
    public static void main (String[] args) throws Exception {
        ProductCodeDAOderby productCodeDAOderby = new ProductCodeDAOderby();
        DiscountCodeDAOderby discountCodeDAOderby = new DiscountCodeDAOderby();        
        
        DiscountCode dc1 = discountCodeDAOderby.buscarPorCodigo("L");
        ProductCode pc1 = new ProductCode("PC", dc1, "Notebook");
        System.out.println("Inserindo um novo produto com os dados [" + pc1.toString() + "]");
        productCodeDAOderby.inserir(pc1);
        System.out.println();
        
        System.out.println("Listando todos os produtos...");
        List<ProductCode> productCodes = productCodeDAOderby.buscarTodos();
        for (ProductCode pc2 : productCodes) {
            System.out.println(pc2.toString());
        }
        System.out.println();
        System.out.println("Buscando produto por código...");
        ProductCode pc3 = productCodeDAOderby.buscarPorCodigoDeProductCode("BK");
        System.out.println(pc3.toString());
        System.out.println();
        
        DiscountCode dc4 = new DiscountCode("P", new BigDecimal(10));
        System.out.println("Inserindo novo desconto com os dados [" + dc4.toString() + "]");
        discountCodeDAOderby.inserir(dc4);
        System.out.println();
        
        System.out.println("Listando todos os descontos...");
        List<DiscountCode> listaDescontos = discountCodeDAOderby.buscarTodos();
        for (DiscountCode dc2 : listaDescontos) {
            System.out.println(dc2.toString());
        }
        System.out.println();
        
        System.out.println("Buscando descontos por código...");
        DiscountCode dc3 = discountCodeDAOderby.buscarPorCodigo("L");
        System.out.println(dc3.toString());
        System.out.println();
        
        System.out.println("Filtrando produtos por código de desconto...");
        List<ProductCode> listaProductCodes = productCodeDAOderby.filtrarProductCodePorCodigoDeDiscountCode("L");
        for (ProductCode pc4 : listaProductCodes) {
            System.out.println(pc4.toString());
        }
    }
}
