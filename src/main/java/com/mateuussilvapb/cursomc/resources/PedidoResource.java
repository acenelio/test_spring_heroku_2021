package com.mateuussilvapb.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mateuussilvapb.cursomc.domain.Pedido;
import com.mateuussilvapb.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	/*
	 * Ao invés de passar um obj do tipo categoria, foi passado um obj do tipo
	 * CategoriaDTO. Isso se dá pois na classe CategoriaDTO foi implementado algumas
	 * validações para a Categoria. Com isso, é necessário que o método passe pela
	 * classe CategoriaDTO para realizar a validação. A anotação @Valid serve
	 * exatamente para isso. Também é necessário que na classe CategoriaService seja
	 * implementado um método que converta os objetos do tipo CategoriaDTO para
	 * Categoria.
	 */
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
			/*
			 * Utilizando esta notação, é possível informar os valores das variáveis não
			 * diretamente no path, mas como parâmetros. A primeira variável indica o nome,
			 * a segunda o valor padrão (caso nem um outro valor seja informado).
			 */
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			/*
			 * Nesse caso, foi utilizando 24 como valor padrão porque ele é múltiplo de 1,
			 * 2, 3 e 4, o que auxilia na hora da organização da página.
			 */
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Pedido> lista = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(lista);
	}
}
