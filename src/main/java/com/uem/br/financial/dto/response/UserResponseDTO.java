package com.uem.br.financial.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class UserResponseDTO {

	private Long id;
	
	private String nomeUsuario;
	
	private String nome;

	private String CPFCNPJ;

	private Date DataNasc;
	
	private String tipoUsuario;
	
}
