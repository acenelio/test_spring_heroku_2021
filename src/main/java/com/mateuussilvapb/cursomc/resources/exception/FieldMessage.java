package com.mateuussilvapb.cursomc.resources.exception;

import java.io.Serializable;

/*
 * Essa classe serve de apoio a montagem de uma pilha de menssagens 
 * de erros. Por exemplo, ao preencher um campo vazio, dois erros 
 * podem ser gerados: 'Preenchimento obrigatório' e 'Mínimo de 5 
 * caracteres e máximo de 80'.
 */
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;

	public FieldMessage() {

	}

	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
