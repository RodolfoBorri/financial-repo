package com.uem.br.financial.service;

import java.util.ArrayList;
import java.util.List;

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
										.CPFCNPJ(user.getCPFCNPJ())
										.DataNasc(user.getDataNasc())
										.nome(user.getNome())
										.tipoUsuario(user.getTipoUsuario().getTipo())										
										.build();
	}

	private Usuario usuarioRequestDTOParaEntidade(UserRequestDTO userRequestDTO, Usuario user) {
		user.setNomeUsuario(userRequestDTO.getNomeUsuario());
		user.setSenhaUsuario(userRequestDTO.getSenhaUsuario());
		user.setNome(userRequestDTO.getNome());
		user.setCarteira(carteiraService.criaCarteira());
		user.setTipoUsuario(tipoUsuarioService.buscaPorId(userRequestDTO.getIdTipoUsuario()));
		user.setCPFCNPJ(userRequestDTO.getCpfcnpj());
		user.setDataNasc(userRequestDTO.getDataNasc());
		
		return user;
	}

	private void validacaoUsuario(UserRequestDTO userRequestDTO) {
		if(userRepository.findByNomeUsuario(userRequestDTO.getNomeUsuario()).isPresent()) {
			throw new ServiceException("DB-1", userRequestDTO.getNomeUsuario());
		}
		
		if(userRepository.findByCPFCNPJ(userRequestDTO.getCpfcnpj()).isPresent()) {
			throw new ServiceException("DB-3", userRequestDTO.getCpfcnpj());
		}
	}
	
	public Usuario buscaPorId(Long idUsuario) {
		return userRepository.findById(idUsuario).orElseThrow(() -> new ServiceException("DB-6", idUsuario));
	}

	public void altera(Long idUsuario, @Valid UserRequestDTO userRequestDTO) {
		validacaoUsuario(userRequestDTO);
		
		Usuario usuarioAlterado = usuarioRequestDTOParaEntidade(userRequestDTO, buscaPorId(idUsuario));
		
		userRepository.save(usuarioAlterado);		
	}

	public List<UserResponseDTO> consultaTodos() {
		List<UserResponseDTO> retorno = new ArrayList<UserResponseDTO>();
		
		List<Usuario> usuarios = userRepository.findAll();
		
		for(Usuario usuario : usuarios) {
			retorno.add(entidadeParaUsuarioResponseDTO(usuario));
		}
		
		return retorno;
	}

	public UserResponseDTO consultaPorId(Long id) {
		Usuario usuario = buscaPorId(id);
		
		return entidadeParaUsuarioResponseDTO(usuario);
	}
}
