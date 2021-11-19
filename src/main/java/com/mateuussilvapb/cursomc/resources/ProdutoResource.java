package com.mateuussilvapb.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mateuussilvapb.cursomc.domain.Produto;
import com.mateuussilvapb.cursomc.dto.ProdutoDTO;
import com.mateuussilvapb.cursomc.resources.utils.URL;
import com.mateuussilvapb.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			/*
			 * Utilizando esta notação, é possível informar os valores das variáveis não
			 * diretamente no path, mas como parâmetros. A primeira variável indica o nome,
			 * a segunda o valor padrão (caso nem um outro valor seja informado).
			 */
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			/*
			 * Nesse caso, foi utilizando 24 como valor padrão porque ele é múltiplo de 1,
			 * 2, 3 e 4, o que auxilia na hora da organização da página.
			 */
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> lista = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok(listDTO);
	}

}
