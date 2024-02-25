package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.RoleDTO;
import com.joel.br.JJ.TECH.DTO.UserDTO;
import com.joel.br.JJ.TECH.DTO.UserInsertDTO;
import com.joel.br.JJ.TECH.models.Role;
import com.joel.br.JJ.TECH.models.User;
import com.joel.br.JJ.TECH.repository.RoleRepository;
import com.joel.br.JJ.TECH.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService  {


    private final UserRepository  userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }




    public UserDTO createUser(UserInsertDTO userDTO) {
        User user = new User();
        copyEntity(user, userDTO);

        user = userRepository.save(user);
        return  new UserDTO(user);
    }






    public void copyEntity(User user, UserInsertDTO userDTO) {
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.getRoles().clear();



        for(RoleDTO userDTO1 :  userDTO.getRoles()) {
            Role category = roleRepository.getOne(userDTO1.getId());
            user.getRoles().add(category);
        }


    }


}
