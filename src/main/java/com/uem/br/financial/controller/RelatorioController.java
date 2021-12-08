package com.uem.br.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.response.ContaResponseDTO;
import com.uem.br.financial.dto.response.RelatorioFluxoResponseDTO;
import com.uem.br.financial.dto.response.RelatorioTipoContaResponseDTO;
import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.service.RelatorioService;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {
	
	@Autowired
	RelatorioService relatorioService;
	
	@GetMapping("/fluxoMensal/{id}")
	public ResponseEntity<ResponseDTO<List<RelatorioFluxoResponseDTO>>> geraArquivoFluxo(@PathVariable Long id){
		List<RelatorioFluxoResponseDTO> conteudo = relatorioService.geraArquivoFluxo(id);
		
		return ResponseEntity.ok(new ResponseDTO<>(conteudo));
	}
	
	@GetMapping("/extrato/{id}")
	public ResponseEntity<ResponseDTO<List<ContaResponseDTO>>> geraArquivoExtrato(@PathVariable Long id){
		List<ContaResponseDTO> conteudo = relatorioService.geraArquivoExtrato(id);
		
		return ResponseEntity.ok(new ResponseDTO<>(conteudo));
	}
	
	@GetMapping("/tipoConta/{id}")
	public ResponseEntity<ResponseDTO<List<RelatorioTipoContaResponseDTO>>> geraArquivoTipoConta(@PathVariable Long id){
		List<RelatorioTipoContaResponseDTO> conteudo = relatorioService.geraArquivoTipoConta(id);
		
		return ResponseEntity.ok(new ResponseDTO<>(conteudo));
	}
}
