package com.uem.br.financial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.request.UserRequestDTO;
import com.uem.br.financial.dto.response.UserResponseDTO;
import com.uem.br.financial.entity.User;
import com.uem.br.financial.exception.ServiceException;
import com.uem.br.financial.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public UserResponseDTO signUp(UserRequestDTO userRequestDTO) {
		signUpValidation(userRequestDTO);
		
		User user = userRequestDTOToEntity(userRequestDTO, new User());
		
		userRepository.save(user);
		
		return entityToUserResponseDTO(user);
	}

	private UserResponseDTO entityToUserResponseDTO(User user) {
		return UserResponseDTO.builder().id(user.getId())
										.nomeUsuario(user.getNomeUsuario())
										.build();
	}

	private User userRequestDTOToEntity(UserRequestDTO userRequestDTO, User user) {
		user.setNomeUsuario(userRequestDTO.getNomeUsuario());
		user.setSenhaUsuario(userRequestDTO.getSenhaUsuario());
		
		return user;
	}

	private void signUpValidation(UserRequestDTO userRequestDTO) {
		if(userRepository.findByNomeUsuario(userRequestDTO.getNomeUsuario()).isPresent()) {
			throw new ServiceException("DB-1", userRequestDTO.getNomeUsuario());
		}
	}
}
