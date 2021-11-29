package com.uem.br.financial.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "data_pagamento", nullable = true)
	private Date dataPagamento;

	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, referencedColumnName = "id")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "id_tipo_conta", nullable = false, referencedColumnName = "id")
	private TipoConta tipoConta;

}
