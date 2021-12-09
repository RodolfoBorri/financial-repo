package com.uem.br.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.response.ContaResponseDTO;
import com.uem.br.financial.dto.response.RelatorioFluxoResponseDTO;
import com.uem.br.financial.dto.response.RelatorioTipoContaResponseDTO;
import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.service.RelatorioService;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController extends ControllerBase{
	
	public RelatorioController(MessageSource messageSource) {
		super(messageSource);
	}

	@Autowired
	RelatorioService relatorioService;
	
	@GetMapping("/fluxoMensal")
	public ResponseEntity<ResponseDTO<List<RelatorioFluxoResponseDTO>>> geraArquivoFluxo(){
		List<RelatorioFluxoResponseDTO> conteudo = relatorioService.geraArquivoFluxo();
		
		return ResponseEntity.ok(new ResponseDTO<>(conteudo));
	}
	
	@GetMapping("/extrato")
	public ResponseEntity<ResponseDTO<List<ContaResponseDTO>>> geraArquivoExtrato(){
		List<ContaResponseDTO> conteudo = relatorioService.geraArquivoExtrato();
		
		return ResponseEntity.ok(new ResponseDTO<>(conteudo));
	}
	
	@GetMapping("/tipoConta")
	public ResponseEntity<ResponseDTO<List<RelatorioTipoContaResponseDTO>>> geraArquivoTipoConta(){
		List<RelatorioTipoContaResponseDTO> conteudo = relatorioService.geraArquivoTipoConta();
		
		return ResponseEntity.ok(new ResponseDTO<>(conteudo));
	}
}
