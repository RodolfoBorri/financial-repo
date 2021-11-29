package com.uem.br.financial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.br.financial.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByNomeUsuario(String nomeUsuario);

	Optional<Usuario> findByCPFCNPJ(String cpfcnpj);
}
