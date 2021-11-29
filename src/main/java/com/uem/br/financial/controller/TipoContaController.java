package com.uem.br.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.dto.response.TipoContaResponseDTO;
import com.uem.br.financial.service.TipoContaService;

@RestController
@RequestMapping("/api/v1/tipoConta")
public class TipoContaController extends ControllerBase{
	
	@Autowired
	TipoContaService tipoContaService;

	public TipoContaController(MessageSource messageSource) {
		super(messageSource);
	}

	@GetMapping
	public ResponseEntity<ResponseDTO<List<TipoContaResponseDTO>>> consultaTodos(){
		List<TipoContaResponseDTO> retorno = tipoContaService.consultaTodos();
		
		return ResponseEntity.ok(new ResponseDTO<>(retorno));		
	}
	
}
