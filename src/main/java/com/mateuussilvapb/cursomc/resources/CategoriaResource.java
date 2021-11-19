package com.mateuussilvapb.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mateuussilvapb.cursomc.domain.Categoria;
import com.mateuussilvapb.cursomc.dto.CategoriaDTO;
import com.mateuussilvapb.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	/*
	 * Ao invés de passar um obj do tipo categoria, foi passado um obj do tipo
	 * CategoriaDTO. Isso se dá pois na classe CategoriaDTO foi implementado algumas
	 * validações para a Categoria. Com isso, é necessário que o método passe pela
	 * classe CategoriaDTO para realizar a validação. A anotação @Valid serve
	 * exatamente para isso. Também é necessário que na classe CategoriaService seja
	 * implementado um método que converta os objetos do tipo CategoriaDTO para
	 * Categoria.
	 */
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findALL() {
		List<Categoria> lista = service.findAll();
		List<CategoriaDTO> listDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
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
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> lista = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = lista.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok(listDTO);
	}

}
