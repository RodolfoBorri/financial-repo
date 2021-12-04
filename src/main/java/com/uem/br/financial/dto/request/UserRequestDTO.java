package com.uem.br.financial.dto.request;

import java.util.Date;

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
public class UserRequestDTO {
	
	private String nomeUsuario;
	
	private String senhaUsuario;
	
	private String nome;

	private String cpfcnpj;

	private Date dataNasc;
	
	private Long idTipoUsuario;
	
}
