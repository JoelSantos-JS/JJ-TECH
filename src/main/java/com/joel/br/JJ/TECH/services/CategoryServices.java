package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServices {

    private final CategoryRepository repository;

    public CategoryServices(CategoryRepository repository) {
        this.repository = repository;
    }


}
