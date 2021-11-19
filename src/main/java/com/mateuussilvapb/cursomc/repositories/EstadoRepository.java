package com.mateuussilvapb.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mateuussilvapb.cursomc.domain.Estado;

/*
 * Na classe herdada 'JpaRepository', o pirmeiro parâmetro representa
 * o tipo do objeto que será lidado pelo repositório e o segundo, o 
 * tipo do identificador do objeto. Como em categoria, o ID do tipo 
 * Integer é o identificador da classe, então o tipo será Integer.
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
