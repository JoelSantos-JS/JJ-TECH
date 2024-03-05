package com.joel.br.JJ.TECH.controller;

import com.joel.br.JJ.TECH.DTO.OrderDTO;
import com.joel.br.JJ.TECH.services.OrderServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/orders")
@AllArgsConstructor
public class OrderController {


    private final OrderServices services;





    @GetMapping
    public ResponseEntity<Page<OrderDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(services.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(services.findById(id));
    }


    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createOrder(dto));
    }

}
