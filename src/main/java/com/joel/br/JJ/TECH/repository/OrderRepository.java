package com.joel.br.JJ.TECH.repository;

import com.joel.br.JJ.TECH.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
