package com.uem.br.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uem.br.financial.entity.Conta;
import com.uem.br.financial.entity.Usuario;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findAllByUsuario(Usuario usuario);

	@Query(value = "select co.data_pagamento," + 
			"	   		   co.valor," 		   + 
			"       	   co.categoria"       + 
			" 		 from db_conta co" 		   + 
			"		where co.id_usuario = ?"   + 
			"		order by co.data_pagamento desc;", nativeQuery = true)
	List<Conta> findRelatorioFluxo(Long idUsuario);

}
