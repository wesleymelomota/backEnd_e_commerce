package com.sistema.cadastro.produto.crudProduto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.cadastro.produto.crudProduto.models.EstoqueProduto;
import com.sistema.cadastro.produto.crudProduto.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
	private EstoqueService service;
	
	@PostMapping("/adicionar")
	public ResponseEntity<EstoqueProduto>  salvar(@RequestBody EstoqueProduto estoque) {
		service.salvar(estoque);
		return new ResponseEntity<>(estoque, HttpStatus.CREATED);
	}
	@GetMapping("/obter-todos-produtos-estoque")
	public ResponseEntity<List<EstoqueProduto>> obterTodos(){
		return new ResponseEntity<>(service.obterTodosProdutosEstoque(), HttpStatus.OK);
	}
	@PutMapping("/atualizar-item")
	public ResponseEntity<EstoqueProduto> atualizar(@RequestBody EstoqueProduto estoque){
		return new ResponseEntity<>(service.update(estoque), HttpStatus.OK);
	}
	@DeleteMapping("/deletar/item/{Id}")
	public void excluirItemEstoque(@PathVariable Long id) {
		service.deletarUmItemEstoque(id);
	}
}
