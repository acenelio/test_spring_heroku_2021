package com.mateuussilvapb.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mateuussilvapb.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	/*
	 * Adiciona 7 dias para a data de vencimento do boleto, a partir da data do
	 * pedido.
	 */
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
