package com.joel.br.JJ.TECH.factory;

import com.joel.br.JJ.TECH.DTO.CategoryDTO;
import com.joel.br.JJ.TECH.models.Category;
import com.joel.br.JJ.TECH.models.Product;

public class CategoryFactory {



    public static Category createNewCategory(){
        return  new Category(1L, "tecnology");
    }



    public  static CategoryDTO createNewCategoryDTO(Category category){

        return new CategoryDTO(category);

    }

}
