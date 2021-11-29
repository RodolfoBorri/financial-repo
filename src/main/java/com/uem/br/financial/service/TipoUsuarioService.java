package com.uem.br.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.response.TipoUsuarioResponseDTO;
import com.uem.br.financial.entity.TipoUsuario;
import com.uem.br.financial.repository.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	
	public TipoUsuario buscaPorId(Long idTipoUsuario) {
		return tipoUsuarioRepository.findById(idTipoUsuario).get();
	}
	
	public List<TipoUsuarioResponseDTO> consultaTodos(){

		List<TipoUsuarioResponseDTO> retorno = new ArrayList<TipoUsuarioResponseDTO>();
		
		List<TipoUsuario> tiposUsuario = tipoUsuarioRepository.findAll();
		
		for(TipoUsuario tipoUsuario : tiposUsuario) {
			retorno.add(entidadeParaTipoUsuarioResponseDTO(tipoUsuario));
		}
		
		return retorno;
	}

	private TipoUsuarioResponseDTO entidadeParaTipoUsuarioResponseDTO(TipoUsuario tipoUsuario) {
		return TipoUsuarioResponseDTO.builder().id(tipoUsuario.getId())
											   .tipo(tipoUsuario.getTipo())
											   .build();
	}
}
