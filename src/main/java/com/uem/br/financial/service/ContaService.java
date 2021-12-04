package com.uem.br.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.request.ContaRequestDTO;
import com.uem.br.financial.dto.response.ContaResponseDTO;
import com.uem.br.financial.entity.Conta;
import com.uem.br.financial.entity.Usuario;
import com.uem.br.financial.exception.ServiceException;
import com.uem.br.financial.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TipoContaService tipoContaService;
	
	@Autowired
	UsuarioService userService;
	
	public ContaResponseDTO adiciona(ContaRequestDTO contaRequestDTO) {
		validacaoConta(contaRequestDTO);
		
		Conta conta = contaRequestDTOParaEntidade(contaRequestDTO, new Conta());
		
		contaRepository.save(conta);
		
		return entidadeParaContaResponseDTO(conta);
	}

	private Conta contaRequestDTOParaEntidade(ContaRequestDTO contaRequestDTO, Conta conta) {
		conta.setDataPagamento(contaRequestDTO.getDataPagamento());
		conta.setDataVencimento(contaRequestDTO.getDataVencimento());
		conta.setCategoria(contaRequestDTO.getCategoria());
		conta.setDescricao(contaRequestDTO.getDescricao());
		conta.setValor(contaRequestDTO.getValor());
		conta.setTipoConta(tipoContaService.buscaPorId(contaRequestDTO.getIdTipoConta()));
		conta.setUsuario(userService.buscaPorId(contaRequestDTO.getIdUsuario()));
		
		return conta;
	}

	private ContaResponseDTO entidadeParaContaResponseDTO(Conta conta) {
		return ContaResponseDTO.builder().id(conta.getId())
										 .dataPagamento(conta.getDataPagamento())
										 .dataVencimento(conta.getDataVencimento())
										 .valor(conta.getValor())
										 .descricao(conta.getDescricao())
										 .categoria(conta.getCategoria())
										 .tipoConta(conta.getTipoConta().getTipo())
										 .build();
	}

	private void validacaoConta(ContaRequestDTO contaRequestDTO) {
		if(contaRequestDTO.getValor() < 0) {
			throw new ServiceException("DB-2");
		}
	}
	
	public Conta buscaPorId(Long idConta) {
		return contaRepository.findById(idConta).get();
	}

	public void altera(Long idConta, ContaRequestDTO contaRequestDTO) {
		validacaoConta(contaRequestDTO);

		Conta contaAlterada = contaRequestDTOParaEntidade(contaRequestDTO, buscaPorId(idConta));
		
		contaRepository.save(contaAlterada);
	}

	public void deleta(Long idConta) {
		Conta conta = contaRepository.findById(idConta).get();
		contaRepository.delete(conta);
	}

	public List<ContaResponseDTO> consultaTodasContasPorUsuario(Long id) {
		Usuario usuario = userService.buscaPorId(id);
		
		List<Conta> contas = contaRepository.findAllByUsuario(usuario);
		
		List<ContaResponseDTO> response = new ArrayList<ContaResponseDTO>();
		
		for(Conta conta : contas)
			response.add(entidadeParaContaResponseDTO(conta));
		
		return response;
	}

}
