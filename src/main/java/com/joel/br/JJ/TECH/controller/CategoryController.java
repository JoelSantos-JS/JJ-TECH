package com.joel.br.JJ.TECH.controller;

import com.joel.br.JJ.TECH.DTO.CategoryDTO;
import com.joel.br.JJ.TECH.models.Category;
import com.joel.br.JJ.TECH.services.CategoryServices;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/category")
@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryServices services;





    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(services.findAllCategorys(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(services.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO dto) {
        return ResponseEntity.status(201).body(services.save(dto));
    }

    @PutMapping
    ("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(services.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        services.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
