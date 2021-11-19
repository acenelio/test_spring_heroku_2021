package com.mateuussilvapb.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateuussilvapb.cursomc.domain.PagamentoComBoleto;
import com.mateuussilvapb.cursomc.domain.PagamentoComCartao;

/*
 * O trecho de código abaixo é padrão de exigência do framework Jackson. 
 * A única coisa que muda de projeto para projeto é: 
 * 'objectMapper.registerSubtypes(NomeDaClasse.class);'
 */

@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}