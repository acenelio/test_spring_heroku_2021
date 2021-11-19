package com.mateuussilvapb.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mateuussilvapb.cursomc.domain.Categoria;
import com.mateuussilvapb.cursomc.domain.Produto;

/*
 * Na classe herdada 'JpaRepository', o pirmeiro parâmetro representa
 * o tipo do objeto que será lidado pelo repositório e o segundo, o 
 * tipo do identificador do objeto. Como em categoria, o ID do tipo 
 * Integer é o identificador da classe, então o tipo será Integer.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/*
	 * Utilizando o padrão de nomes fornecido pelo Spring
	 * (https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.
	 * query-methods.query-creation) é possível utilizar o métodos sem a necessidade
	 * de implementar a consulta. Caso a consulta fosse implementada de modo manula,
	 * seria necessário utilizar a anotação @Param nos atributos que seriam passados
	 * para a consulta e, dentro de parênteses e entre aspas, o nome que está na
	 * consulta: @Param("nome") String nome, @Param("categorias") List<Categoria>
	 * categorias
	 */

	// @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat
	// WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			Pageable pageRequest);
}
