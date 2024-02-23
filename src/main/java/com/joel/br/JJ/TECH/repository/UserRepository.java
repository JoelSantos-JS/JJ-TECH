package com.joel.br.JJ.TECH.repository;

import com.joel.br.JJ.TECH.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
