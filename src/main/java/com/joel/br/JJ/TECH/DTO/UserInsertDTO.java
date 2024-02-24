package com.joel.br.JJ.TECH.DTO;

public class UserInsertDTO extends UserDTO {

    private String password;
    public UserInsertDTO() {
    }

    public UserInsertDTO(String password ) {
        super();
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
