package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.ProductDTO;
import com.joel.br.JJ.TECH.exceptions.DataBaseException;
import com.joel.br.JJ.TECH.exceptions.ProductNotFoundException;
import com.joel.br.JJ.TECH.models.Product;
import com.joel.br.JJ.TECH.repository.ProductRepository;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public Page<ProductDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ProductDTO::new);
    }

    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new ProductDTO(product);
    }

    public ProductDTO save(ProductDTO dto) {
        Product product = new Product();
        copyEntity(product, dto);

        product = repository.save(product);

        return new ProductDTO(product);

    }


    public ProductDTO update(Long id, ProductDTO dto) {
        Product product  = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        copyEntity(product , dto);

        product = repository.save(product);


        return new ProductDTO(product);
    }


    public void  deleteById(Long id) throws  Exception {

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("it's now allowed to delete this product");
        }
    }















    public void  copyEntity(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }
}
