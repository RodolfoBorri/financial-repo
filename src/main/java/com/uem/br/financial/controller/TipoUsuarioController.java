package com.uem.br.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.dto.response.TipoUsuarioResponseDTO;
import com.uem.br.financial.service.TipoUsuarioService;

@RestController
@RequestMapping("/api/v1/tipoUsuario")
public class TipoUsuarioController extends ControllerBase{

	@Autowired
	TipoUsuarioService tipoUsuarioService;
	
	public TipoUsuarioController(MessageSource messageSource) {
		super(messageSource);
	}
	
	@GetMapping
	public ResponseEntity<ResponseDTO<List<TipoUsuarioResponseDTO>>> consultaTodos(){
		List<TipoUsuarioResponseDTO> retorno = tipoUsuarioService.consultaTodos();
		
		return ResponseEntity.ok(new ResponseDTO<>(retorno));		
	}

}
