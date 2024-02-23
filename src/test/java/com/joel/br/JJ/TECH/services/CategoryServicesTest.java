package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.CategoryDTO;
import com.joel.br.JJ.TECH.exceptions.CategoryNotFoundException;
import com.joel.br.JJ.TECH.exceptions.DataBaseException;
import com.joel.br.JJ.TECH.factory.CategoryFactory;
import com.joel.br.JJ.TECH.factory.ProductFactory;
import com.joel.br.JJ.TECH.models.Category;
import com.joel.br.JJ.TECH.models.Product;
import com.joel.br.JJ.TECH.repository.CategoryRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class CategoryServicesTest {


    @InjectMocks
    private CategoryServices categoryServices;


    @Mock
    private CategoryRepository repository;


    private Long exitindId,noExistingId,dependId;



    private Category category;


    private CategoryDTO dto;
    private PageImpl<Category> page;


    @BeforeEach
    void  setUp() {
        exitindId= 1L;
        noExistingId= 100L;
        dependId= 3L;
        category = CategoryFactory.createNewCategory();
        page = new PageImpl<>(List.of(category));


        Mockito.when(repository.findById(exitindId)).thenReturn(Optional.of(category));
        Mockito.when(repository.findById(noExistingId)).thenReturn(Optional.empty());
        Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);
        Mockito.when(repository.save(any())).thenReturn(category);
        Mockito.when(repository.findById(noExistingId)).thenThrow(CategoryNotFoundException.class);
        Mockito.when(repository.existsById(exitindId)).thenReturn(true);
        Mockito.when(repository.existsById(noExistingId)).thenReturn(false);
        Mockito.when(repository.existsById(dependId)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(exitindId);
        Mockito.doThrow(CategoryNotFoundException.class).when(repository).deleteById(noExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependId);


    }


    @Test
    void shouldReturnId() {
        CategoryDTO result = categoryServices.findById(exitindId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(),exitindId);
    }

    @Test
    public void shouldReturnNotFoundWhenIdDoesNotExist(){

        Assertions.assertThrows(CategoryNotFoundException.class, () -> {
            categoryServices.findById(noExistingId);
        });

    }

    @Test
    public void shouldReturnFindAllPage() {
        Pageable pageable1 = PageRequest.of(0 , 12);


        Page<CategoryDTO> categoryDTO = categoryServices.findAllCategorys(pageable1);

        Assertions.assertNotNull(categoryDTO);
        Assertions.assertEquals(categoryDTO.getTotalElements(), 1);
        Assertions.assertEquals(categoryDTO.getContent().get(0).getId(), 1L);
        Assertions.assertEquals(categoryDTO.getSize() , 1);
    }



    @Test
    public void  shouldSaveReturn() {
        dto = CategoryFactory.createNewCategoryDTO(category);
        CategoryDTO categoryDTO = categoryServices.save(dto);


        Assertions.assertNotNull(categoryDTO);
        Assertions.assertEquals(categoryDTO.getId(), exitindId);
        Assertions.assertEquals(categoryDTO.getName(), "tecnology");
    }


    @Test
    public void shouldUpdateWhenPassedId() {
        dto = CategoryFactory.createNewCategoryDTO(category);
        CategoryDTO categoryDTO = categoryServices.update(exitindId, dto);

        Assertions.assertNotNull(categoryDTO);
        Assertions.assertEquals(categoryDTO.getId(), exitindId);
        Assertions.assertEquals(categoryDTO.getName(), "tecnology");
    }


    @Test
    public void shouldThrowExceptionWhenIdDoesNotExist() {
        dto  = CategoryFactory.createNewCategoryDTO(category);


        Assertions.assertThrows(CategoryNotFoundException.class, () -> {
            @SuppressWarnings("unused")
            CategoryDTO  categoryDTO = categoryServices.update(noExistingId, dto);

        });

    }


    @Test
    public void shouldDeleteWhenPassedId() {

        Assertions.assertDoesNotThrow(() -> {
            categoryServices.deleteById(exitindId);

        });

    }


    @Test

    public void shouldThrowExceptionWhenIdDoesNotExistToDelete() {
        Assertions.assertThrows(CategoryNotFoundException.class, () -> {
            categoryServices.deleteById(noExistingId);

        });


    }

    @Test
    public void shouldThrowExceptionWhenIdExistToDelete() {
        Assertions.assertThrows(DataBaseException.class, () -> {
            categoryServices.deleteById(dependId);

        });
    }

}