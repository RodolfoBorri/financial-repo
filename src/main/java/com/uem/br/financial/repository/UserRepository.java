package com.uem.br.financial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.br.financial.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByNomeUsuario(String nomeUsuario);
}
