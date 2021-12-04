package com.uem.br.financial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.request.CarteiraRequestDTO;
import com.uem.br.financial.dto.response.CarteiraResponseDTO;
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
		Carteira carteira = new Carteira();
		
		carteira.setFundos(new Double(0));	
		
		carteiraRepository.save(carteira);
		
		return carteira;
	}
	
	private CarteiraResponseDTO entidadeParaCarteiraResponseDTO(Carteira carteira) {
		return CarteiraResponseDTO.builder().fundos(carteira.getFundos()).build();
	}
	
	public CarteiraResponseDTO buscaCarteiraPorIdUsuario(Long idUsuario) {
		Carteira carteira = carteiraRepository.findById(usuarioService.buscaPorId(idUsuario).getCarteira().getId()).get();
		
		return entidadeParaCarteiraResponseDTO(carteira);
	}

	public void adicionaFundos(CarteiraRequestDTO carteiraRequestDTO) {
		Usuario usuario = usuarioService.buscaPorId(carteiraRequestDTO.getIdUsuario());
		Carteira carteira = buscaPorId(usuario.getCarteira().getId());
		
		validaFundos(carteiraRequestDTO);

		usuario.setCarteira(carteira);
		carteiraRepository.save(carteira);
	}

	private void validaFundos(CarteiraRequestDTO carteiraRequestDTO) {
		if(carteiraRequestDTO.getFundos() < 0) {
			throw new ServiceException("DB-4");
		}
	}

}
