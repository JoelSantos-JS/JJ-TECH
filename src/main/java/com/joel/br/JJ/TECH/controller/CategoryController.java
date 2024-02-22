package com.joel.br.JJ.TECH.controller;

import com.joel.br.JJ.TECH.models.Category;
import com.joel.br.JJ.TECH.services.CategoryServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/category")
@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryServices services;
}
