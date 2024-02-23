package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository  userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
