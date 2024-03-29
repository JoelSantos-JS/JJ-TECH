package com.joel.br.JJ.TECH.DTO;

import com.joel.br.JJ.TECH.models.Category;
import com.joel.br.JJ.TECH.models.Product;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class ProductDTO {


    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private Double price;
    @NotEmpty
    private String imgUrl;

    private Set<CategoryDTO> categories = new HashSet<>();
    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();


    }

    public ProductDTO(Product product , Set<Category> categories ) {
        this(product);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
