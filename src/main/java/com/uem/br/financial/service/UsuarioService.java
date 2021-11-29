package com.uem.br.financial.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.request.UserRequestDTO;
import com.uem.br.financial.dto.response.UserResponseDTO;
import com.uem.br.financial.entity.Usuario;
import com.uem.br.financial.exception.ServiceException;
import com.uem.br.financial.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository userRepository;
	
	@Autowired
	CarteiraService carteiraService;
	
	@Autowired
	TipoUsuarioService tipoUsuarioService;

	public UserResponseDTO criaUsuario(UserRequestDTO userRequestDTO) {
		validacaoUsuario(userRequestDTO);
		
		Usuario user = usuarioRequestDTOParaEntidade(userRequestDTO, new Usuario());
		
		userRepository.save(user);
		
		return entidadeParaUsuarioResponseDTO(user);
	}

	private UserResponseDTO entidadeParaUsuarioResponseDTO(Usuario user) {
		return UserResponseDTO.builder().id(user.getId())
										.nomeUsuario(user.getNomeUsuario())
										.build();
	}

	private Usuario usuarioRequestDTOParaEntidade(UserRequestDTO userRequestDTO, Usuario user) {
		user.setNomeUsuario(userRequestDTO.getNomeUsuario());
		user.setSenhaUsuario(userRequestDTO.getSenhaUsuario());
		user.setNome(userRequestDTO.getNome());
		user.setCarteira(carteiraService.criaCarteira());
		user.setTipoUsuario(tipoUsuarioService.buscaPorId(userRequestDTO.getIdTipoUsuario()));
		user.setCPFCNPJ(userRequestDTO.getCPFCNPJ());
		user.setDataNasc(userRequestDTO.getDataNasc());
		
		return user;
	}

	private void validacaoUsuario(UserRequestDTO userRequestDTO) {
		if(userRepository.findByNomeUsuario(userRequestDTO.getNomeUsuario()).isPresent()) {
			throw new ServiceException("DB-1", userRequestDTO.getNomeUsuario());
		}
		
		if(userRepository.findByCPFCNPJ(userRequestDTO.getCPFCNPJ()).isPresent()) {
			throw new ServiceException("DB-2", userRequestDTO.getCPFCNPJ());
		}
	}
	
	public Usuario buscaPorId(Long idUsuario) {
		return userRepository.findById(idUsuario).get();
	}

	public void altera(Long idUsuario, @Valid UserRequestDTO userRequestDTO) {
		validacaoUsuario(userRequestDTO);
		
		Usuario usuarioAlterado = usuarioRequestDTOParaEntidade(userRequestDTO, buscaPorId(idUsuario));
		
		userRepository.save(usuarioAlterado);		
	}
}
