package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.ProductDTO;
import com.joel.br.JJ.TECH.exceptions.CategoryNotFoundException;
import com.joel.br.JJ.TECH.exceptions.DataBaseException;
import com.joel.br.JJ.TECH.exceptions.ProductNotFoundException;
import com.joel.br.JJ.TECH.factory.ProductFactory;
import com.joel.br.JJ.TECH.models.Product;
import com.joel.br.JJ.TECH.repository.ProductRepository;
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
class ProductServiceTest {



    @InjectMocks
    private ProductService service;


    @Mock
    private ProductRepository repository;


    private Long existingId,noExistingId,dependId;

    private Product product;


    private ProductDTO productDTO;

    private PageImpl<Product> page;


    @BeforeEach
    void setUp() throws Exception {
        existingId= 1L;
        noExistingId= 5L;
        dependId= 2L;
        product = ProductFactory.createNewProduce();
        page= new PageImpl<>(List.of(product));

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(repository.findById(noExistingId)).thenReturn(Optional.empty());
        Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);
        Mockito.when(repository.save(product)).thenReturn(product);
        Mockito.when(repository.findById(noExistingId)).thenThrow(ProductNotFoundException.class);

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when(repository.existsById(noExistingId)).thenReturn(false);
        Mockito.when(repository.existsById(dependId)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(CategoryNotFoundException.class).when(repository).deleteById(noExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependId);
    }



    @Test
    public void findByIdShouldReturnProductWhenIdExists(){

        ProductDTO result = service.findById(existingId);


        assertNotNull(result);
        assertEquals(result.getId(), existingId);
    }


    @Test
    public void findByIdShouldThrowProductNotFoundExceptionWhenIdDoesNotExists(){
        assertThrows(ProductNotFoundException.class, ()->{
            service.findById(noExistingId);
        });
    }


    @Test
    public void findyAllProductPagined() {
        Pageable result = PageRequest.of(0 , 12);

        Page<ProductDTO> productDTO = service.findAll(result);


        Assertions.assertNotNull(productDTO);
        Assertions.assertEquals(productDTO.getTotalElements(), 1);
        Assertions.assertEquals(productDTO.getSize(), 1);
    }


    @Test
    public void saveShouldReturnProductWhenIdExists(){
        productDTO = ProductFactory.createProductDto(product);

        ProductDTO productDTO1 = service.save(productDTO);


        Assertions.assertNotNull(productDTO1);
        Assertions.assertEquals(productDTO1.getId(), existingId);
        Assertions.assertEquals(productDTO1.getName(), "Laptop");
    }


    @Test
    public void shouldUpdateIdPassed() {
        productDTO = ProductFactory.createProductDto(product);

        ProductDTO productDTO1 = service.update(existingId, productDTO);


        Assertions.assertNotNull(productDTO1);
        Assertions.assertEquals(productDTO1.getId(), existingId);
        Assertions.assertEquals(productDTO1.getName(), "Laptop");
    }

    @Test
    public void shouldNotUpdateWhenIdDoesNotExists() {
        productDTO = ProductFactory.createProductDto(product);

        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.update(noExistingId, productDTO);
        });
    }

    @Test
    public void shouldDeleteWhenPassedId() {

        Assertions.assertDoesNotThrow(() -> {
            service.deleteById(existingId);

        });

    }


    @Test

    public void shouldThrowExceptionWhenIdDoesNotExistToDelete() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.deleteById(noExistingId);

        });


    }

    @Test
    public void shouldThrowExceptionWhenIdExistToDelete() {
        Assertions.assertThrows(DataBaseException.class, () -> {
            service.deleteById(dependId);

        });
    }
}