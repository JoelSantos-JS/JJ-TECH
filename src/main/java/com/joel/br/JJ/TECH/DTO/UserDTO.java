package com.joel.br.JJ.TECH.DTO;

import com.joel.br.JJ.TECH.models.Role;
import com.joel.br.JJ.TECH.models.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<RoleDTO> role;

    public UserDTO() {

    }

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }





    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        user.getRoles().forEach(e -> this.role.add(new RoleDTO(e)));
    }


    public UserDTO (User user , Set<Role> roles){
        this(user);
           roles.forEach(e -> this.role.add(new RoleDTO(e)));
    }

}
