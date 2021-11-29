package com.uem.br.financial.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "db_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_usuario", nullable = false)
	private String nomeUsuario;
	
	@Column(name = "senha_usuario", nullable = false)
	private String senhaUsuario;
	
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "cpfcnpj", nullable = false)
	private String CPFCNPJ;

	@Column(name = "data_nasc", nullable = false)
	private Date DataNasc;
	
	@OneToOne
	@JoinColumn(name = "id_carteira", nullable = false, referencedColumnName = "id")
	private Carteira carteira;
	
	@OneToOne
	@JoinColumn(name = "id_tipo_usuario", nullable = false, referencedColumnName = "id")
	private TipoUsuario tipoUsuario;
}
