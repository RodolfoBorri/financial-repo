package com.uem.br.financial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.request.ContaRequestDTO;
import com.uem.br.financial.dto.response.ContaResponseDTO;
import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.service.ContaService;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaController extends ControllerBase{
	
	@Autowired
	ContaService contaService;

	public ContaController(MessageSource messageSource) {
		super(messageSource);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<ContaResponseDTO>> adiciona(@Valid @RequestBody ContaRequestDTO contaRequestDTO){
		ContaResponseDTO contaResponseDTO = contaService.adiciona(contaRequestDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(new ResponseDTO<>(contaResponseDTO));
	}
	
	@GetMapping("/contas/{id}")
	public ResponseEntity<ResponseDTO<List<ContaResponseDTO>>> consultaTodasContasPorUsuario(@PathVariable Long id){
		List<ContaResponseDTO> contasUsuario = contaService.consultaTodasContasPorUsuario(id);
		
		return ResponseEntity.ok(new ResponseDTO<>(contasUsuario));	
	}
	
	@PutMapping("{/id}")
	public ResponseEntity<ResponseDTO<Void>> altera(@PathVariable Long id, @Valid @RequestBody ContaRequestDTO contaRequestDTO){
		contaService.altera(id, contaRequestDTO);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO<Void>> deleta(@PathVariable Long id){
		contaService.deleta(id);
				
		return ResponseEntity.noContent().build();
	}
}
