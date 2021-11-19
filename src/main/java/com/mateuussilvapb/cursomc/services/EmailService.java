package com.mateuussilvapb.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.mateuussilvapb.cursomc.domain.Cliente;
import com.mateuussilvapb.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
