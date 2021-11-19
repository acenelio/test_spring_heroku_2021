package com.mateuussilvapb.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

/*
 * Essa classe, além de herdar os atributos da classe StandardError 
 * com o intuito de gerar o erro personalizado, servirá também para 
 * gerar um conjunto de erros vindos da classe FieldMessage.
 */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
