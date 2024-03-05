package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.FormPaymentDTO;
import com.joel.br.JJ.TECH.repository.FormPaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class FormPaymentServices {


    private final FormPaymentRepository repository;

    public FormPaymentServices(FormPaymentRepository repository) {
        this.repository = repository;
    }



    public FormPaymentDTO findById(Long id){
        return new FormPaymentDTO(repository.findById(id).orElseThrow());
    }
}
