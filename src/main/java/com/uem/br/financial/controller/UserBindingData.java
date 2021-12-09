package com.uem.br.financial.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class UserBindingData {
	
	public static Long idUsuario;
	
	public static Long getIdUsuario() {
		return idUsuario;
	}
	
	@PostConstruct
	public void setBind() {
		idUsuario = new Long(1);
	}
}
