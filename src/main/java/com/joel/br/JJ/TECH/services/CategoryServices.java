package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.CategoryDTO;
import com.joel.br.JJ.TECH.exceptions.CategoryNotFoundException;
import com.joel.br.JJ.TECH.exceptions.DataBaseException;
import com.joel.br.JJ.TECH.models.Category;
import com.joel.br.JJ.TECH.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServices {

    private final CategoryRepository repository;

    public CategoryServices(CategoryRepository repository) {
        this.repository = repository;
    }



    public Page<CategoryDTO> findAllCategorys(Pageable pageable) {
        Page<Category> category = repository.findAll(pageable);
        return category.map(CategoryDTO::new);
    }

    public CategoryDTO findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return new CategoryDTO(category);
    }


    public CategoryDTO save(CategoryDTO dto) {
        Category category = new Category();
        copyEntity(category, dto);

        category = repository.save(category);

        return new CategoryDTO(category);
    }


    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category ccategory  = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        copyEntity(ccategory, dto);
        ccategory = repository.save(ccategory);


        return new CategoryDTO(ccategory);
    }









    public void deleteById(Long id) {

        if(!repository.existsById(id)) {
            throw  new CategoryNotFoundException("category not found");
        }

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("it's now allowed to delete this category");
        }
    }

    public void copyEntity(Category category, CategoryDTO dto) {
        category.setId(dto.getId());
        category.setName(dto.getName());
    }
}
