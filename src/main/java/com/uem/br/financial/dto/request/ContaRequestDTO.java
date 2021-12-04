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
public class ContaRequestDTO {

	private String descricao;
	
	private Double valor;

	private Date dataPagamento;

	private Date dataVencimento;
	
	private String categoria;
	
	private Long idTipoConta;
	
	private Long idUsuario;
}
