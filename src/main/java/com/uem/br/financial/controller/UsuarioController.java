package com.uem.br.financial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.request.UserRequestDTO;
import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.dto.response.UserResponseDTO;
import com.uem.br.financial.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController extends ControllerBase{

	@Autowired
	UsuarioService userService;
	
	public UsuarioController(MessageSource messageSource) {
		super(messageSource);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<UserResponseDTO>> criaUsuario(@Valid @RequestBody UserRequestDTO userRequestDTO){
		UserResponseDTO userResponseDTO = userService.criaUsuario(userRequestDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(new ResponseDTO<>(userResponseDTO));
	}

	@GetMapping
	public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> consultaTodos(){
		List<UserResponseDTO> retorno = userService.consultaTodos();
		
		return ResponseEntity.ok(new ResponseDTO<>(retorno));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO<UserResponseDTO>> consultaPorId(@PathVariable Long id){
		UserResponseDTO retorno = userService.consultaPorId(id);
		
		return ResponseEntity.ok(new ResponseDTO<>(retorno));		
	}
	
	@PutMapping("{/id}")
	public ResponseEntity<ResponseDTO<Void>> altera(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO){
		userService.altera(id, userRequestDTO);
		
		return ResponseEntity.noContent().build();
	}
}
