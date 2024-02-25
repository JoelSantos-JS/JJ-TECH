package com.joel.br.JJ.TECH.factory;

import com.joel.br.JJ.TECH.DTO.ProductDTO;
import com.joel.br.JJ.TECH.models.Product;

public class ProductFactory {



    public static Product createNewProduce(){
        return  new Product(1L, "Laptop" ,"computer" , 2.0 , "gooogle.com");
    }

    public  static ProductDTO createProductDto(Product product) {

        return new ProductDTO(product);

    }

}
