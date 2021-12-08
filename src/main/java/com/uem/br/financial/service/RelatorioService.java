package com.uem.br.financial.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.br.financial.dto.response.ContaResponseDTO;
import com.uem.br.financial.dto.response.RelatorioFluxoResponseDTO;
import com.uem.br.financial.entity.Usuario;

@Service
public class RelatorioService {

	@Autowired
	ContaService contaService;
	
	@Autowired
	UsuarioService usuarioService;
	
	public List<RelatorioFluxoResponseDTO> geraArquivoFluxo(Long idUsuario){
		
		List<RelatorioFluxoResponseDTO> conteudoRelatorio = new ArrayList<RelatorioFluxoResponseDTO>();
		
		Usuario usuario = usuarioService.buscaPorId(idUsuario);
		
		List<ContaResponseDTO> contas = contaService.consultaTodasContasPorUsuario(idUsuario);
		
		int saldoAnterior = usuario.getCarteira().getFundos().intValue();
		int mesAnterior = -1;
		int entradas = 0;
		int saidas = 0;
		int saldoMensal = 0;
		int contador = 1;
		
		ContaResponseDTO contaAnterior = new ContaResponseDTO();
		
		for(ContaResponseDTO conta : contas) {			
			
			RelatorioFluxoResponseDTO fluxoResponseDTO = new RelatorioFluxoResponseDTO();
			
			LocalDate localDate = conta.getDataPagamento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int mesAtual = localDate.getMonthValue();
			
			if(mesAtual == mesAnterior) {
				if(conta.getCategoria().equals("a pagar"))
					saidas += conta.getValor().intValue();
				
				else if(conta.getCategoria().equals("a receber"))
					entradas += conta.getValor().intValue();
			}
			else {
				
				if(contador != 1) {
					saldoMensal = entradas - saidas;
					
					fluxoResponseDTO.setData(contaAnterior.getDataPagamento());
					fluxoResponseDTO.setDataEmissao(java.util.Date.from(java.time.LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
					fluxoResponseDTO.setEntradas(entradas);
					fluxoResponseDTO.setSaidas(saidas);
					fluxoResponseDTO.setSaldoAnterior(saldoAnterior);
					fluxoResponseDTO.setSaldoMensal(saldoMensal );
					fluxoResponseDTO.setSaldoAcumulado(saldoAnterior + saldoMensal);
					
					saldoAnterior = saldoMensal;
					
					conteudoRelatorio.add(fluxoResponseDTO);
				}				
				
				if(conta.getCategoria().equals("a pagar"))
					saidas = conta.getValor().intValue();
				
				else 
					entradas += conta.getValor().intValue();
			}
			
			mesAnterior = mesAtual;
			contaAnterior = conta;
			contador++;
		}
		
		RelatorioFluxoResponseDTO fluxoResponseDTO = new RelatorioFluxoResponseDTO();
		
		saldoMensal = entradas - saidas;
		
		fluxoResponseDTO.setData(contaAnterior.getDataPagamento());
		fluxoResponseDTO.setDataEmissao(java.util.Date.from(java.time.LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		fluxoResponseDTO.setEntradas(entradas);
		fluxoResponseDTO.setSaidas(saidas);
		fluxoResponseDTO.setSaldoAnterior(saldoAnterior);
		fluxoResponseDTO.setSaldoMensal(saldoMensal );
		fluxoResponseDTO.setSaldoAcumulado(saldoAnterior + saldoMensal);
		
		saldoAnterior = saldoMensal;
		
		conteudoRelatorio.add(fluxoResponseDTO);
		
		return conteudoRelatorio;
	}
}
