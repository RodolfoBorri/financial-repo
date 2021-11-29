package com.uem.br.financial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.request.CarteiraRequestDTO;
import com.uem.br.financial.entity.Carteira;
import com.uem.br.financial.entity.Usuario;
import com.uem.br.financial.exception.ServiceException;
import com.uem.br.financial.repository.CarteiraRepository;

@Service
public class CarteiraService {

	@Autowired
	CarteiraRepository carteiraRepository;

	@Autowired
	UsuarioService usuarioService;

	public Carteira buscaPorId(Long idCarteira) {
		return carteiraRepository.findById(idCarteira).get();
	}

	public Carteira criaCarteira() {
		return new Carteira();
	}

	public void adicionaFundosERentabilidade(CarteiraRequestDTO carteiraRequestDTO) {
		Usuario usuario = usuarioService.buscaPorId(carteiraRequestDTO.getIdUsuario());
		Carteira carteira = buscaPorId(usuario.getCarteira().getId());
		
		validaFundos(carteiraRequestDTO);

		if (carteiraRequestDTO.getRentabilidade() > 0 && carteiraRequestDTO.getFundos() > 0) {
			carteira.setFundos(carteiraRequestDTO.getFundos());
			carteira.setRentabilidade(carteiraRequestDTO.getRentabilidade());
		} else if (carteiraRequestDTO.getFundos() > 0) {
			carteira.setFundos(carteiraRequestDTO.getFundos());
		} else if (carteiraRequestDTO.getRentabilidade() > 0) {
			carteira.setRentabilidade(carteiraRequestDTO.getRentabilidade());
		}

		usuario.setCarteira(carteira);
		carteiraRepository.save(carteira);
	}

	private void validaFundos(CarteiraRequestDTO carteiraRequestDTO) {
		if(carteiraRequestDTO.getFundos() < 0) {
			throw new ServiceException("DB-4");
		}
		
		if(carteiraRequestDTO.getRentabilidade() < 0) {
			throw new ServiceException("DB-5");
		}
	}

}
