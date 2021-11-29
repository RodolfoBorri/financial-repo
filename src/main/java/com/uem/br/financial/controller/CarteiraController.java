package com.uem.br.financial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.request.CarteiraRequestDTO;
import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.service.CarteiraService;

@RestController
@RequestMapping("/api/v1/carteiras")
public class CarteiraController extends ControllerBase{

	@Autowired
	CarteiraService carteiraService;
	
	public CarteiraController(MessageSource messageSource) {
		super(messageSource);
	}

	@PutMapping
	public ResponseEntity<ResponseDTO<Void>> adicionaFundos(@Valid @RequestBody CarteiraRequestDTO carteiraRequestDTO){
		carteiraService.adicionaFundosERentabilidade(carteiraRequestDTO);		
		
		return ResponseEntity.noContent().build();
	}
	
}
