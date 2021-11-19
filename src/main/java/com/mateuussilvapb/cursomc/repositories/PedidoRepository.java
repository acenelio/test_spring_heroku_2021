package com.mateuussilvapb.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mateuussilvapb.cursomc.domain.Cliente;
import com.mateuussilvapb.cursomc.domain.Pedido;

/*
 * Na classe herdada 'JpaRepository', o pirmeiro parâmetro representa
 * o tipo do objeto que será lidado pelo repositório e o segundo, o 
 * tipo do identificador do objeto. Como em categoria, o ID do tipo 
 * Integer é o identificador da classe, então o tipo será Integer.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);

}
