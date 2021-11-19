package com.mateuussilvapb.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mateuussilvapb.cursomc.domain.Cliente;
import com.mateuussilvapb.cursomc.repositories.ClienteRepository;
import com.mateuussilvapb.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		/*
		 * Gera um número inteiro aleatório entre 0 e 2. O intervalo varia de acordo com
		 * a qauntidade passada no método nextInt(?)
		 */

		int opt = rand.nextInt(3);
		/*
		 * Os valores são gerados de acordo com a tabela unicode.
		 */
		if (opt == 0) { // Gera um dígito
			// o valor 48 está relacionado ao unicode (https://unicode-table.com/pt/)
			// o valor 10 é a quantidade de números entre 0 e 9
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {// Gera uma letra maiúscula
			// o valor 65 está relacionado ao unicode (https://unicode-table.com/pt/)
			// o valor 26 é a quantidade de letras no alfabeto

			return (char) (rand.nextInt(26) + 65);
		} else {// Gera uma letra minúscula
				// o valor 97 está relacionado ao unicode (https://unicode-table.com/pt/)
				// o valor 26 é a quantidade de letras no alfabeto

			return (char) (rand.nextInt(26) + 97);
		}
	}
}