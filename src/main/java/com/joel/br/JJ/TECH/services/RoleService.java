package com.joel.br.JJ.TECH.services;

import org.springframework.stereotype.Service;

@Service

public class RoleService {

    private final RoleService userService;

    public RoleService(RoleService userService) {
        this.userService = userService;
    }
}
