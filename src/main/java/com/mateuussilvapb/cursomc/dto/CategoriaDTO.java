package com.mateuussilvapb.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mateuussilvapb.cursomc.domain.Categoria;

/*
 * Essa classe serve para acessar as informações da categoria. 
 * Observe que a classe possui como variáveis o ID e o nome.
 * Isso se dá pois essa classe serve para acessar as informações
 * inerentes a classe 'Categoria' em si. Demais informações, como
 * associações, por exemplo, são deixadas de lado.
 */
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
