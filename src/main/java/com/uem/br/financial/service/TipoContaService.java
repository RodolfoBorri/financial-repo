package com.uem.br.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.response.TipoContaResponseDTO;
import com.uem.br.financial.entity.TipoConta;
import com.uem.br.financial.repository.TipoContaRepository;

@Service
public class TipoContaService {
	
	@Autowired
	TipoContaRepository tipoContaRepository;
	
	public TipoConta buscaPorId(Long idTipoConta) {
		return tipoContaRepository.findById(idTipoConta).get();
	}
	
	public List<TipoContaResponseDTO> consultaTodos(){

		List<TipoContaResponseDTO> retorno = new ArrayList<TipoContaResponseDTO>();
		
		List<TipoConta> tiposConta = tipoContaRepository.findAll();
		
		for(TipoConta tipoConta : tiposConta) {
			retorno.add(entidadeParaTipoContaResponseDTO(tipoConta));
		}
		
		return retorno;
	}

	private TipoContaResponseDTO entidadeParaTipoContaResponseDTO(TipoConta tipoConta) {
		return TipoContaResponseDTO.builder().id(tipoConta.getId())
							   			  .tipo(tipoConta.getTipo())
							   			  .build();
	}
}
