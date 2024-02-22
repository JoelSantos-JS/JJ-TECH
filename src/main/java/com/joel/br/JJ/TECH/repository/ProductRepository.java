package com.joel.br.JJ.TECH.repository;

import com.joel.br.JJ.TECH.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
