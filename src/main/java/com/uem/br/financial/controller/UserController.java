package com.uem.br.financial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uem.br.financial.dto.request.UserRequestDTO;
import com.uem.br.financial.dto.response.ResponseDTO;
import com.uem.br.financial.dto.response.UserResponseDTO;
import com.uem.br.financial.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends ControllerBase{

	@Autowired
	UserService userService;
	
	public UserController(MessageSource messageSource) {
		super(messageSource);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<UserResponseDTO>> signUp(@Valid @RequestBody UserRequestDTO userRequestDTO){
		UserResponseDTO userResponseDTO = userService.signUp(userRequestDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(new ResponseDTO<>(userResponseDTO));
	}
	
}
