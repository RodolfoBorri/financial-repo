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
public class ContaResponseDTO {

	private Long id;
	
	private String descricao;
	
	private Double valor;

	private Date dataPagamento;

	private Date dataVencimento;
	
	private String tipoConta;
	
	private String categoria;
	
}
