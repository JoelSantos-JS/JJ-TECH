package com.joel.br.JJ.TECH.controller;

import com.joel.br.JJ.TECH.DTO.UserDTO;
import com.joel.br.JJ.TECH.DTO.UserInsertDTO;
import com.joel.br.JJ.TECH.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;





    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserInsertDTO userDTO){


        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(userDTO));
    }

}
