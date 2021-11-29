package com.uem.br.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uem.br.financial.entity.Conta;
import com.uem.br.financial.entity.Usuario;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findAllByUsuario(Usuario usuario);

}
