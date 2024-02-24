package com.joel.br.JJ.TECH.DTO;

import com.joel.br.JJ.TECH.models.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }


    public RoleDTO(Role role ) {
        id = role.getId();
        authority = role.getAuthority();
    }
}
