package com.sistema.cadastro.produto.crudProduto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Item;
import com.sistema.cadastro.produto.crudProduto.service.ItemService;
@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService service;
	
	@PostMapping("/create")
	public Item create(@RequestBody Item item) {
		return service.salvar(item);
	}
	@GetMapping("/obter-todos")
	public List<Item> obterTodos(){
		return service.obterTodos();
	}
	@GetMapping("/obter/{id}")
	public Item obterItem(@PathVariable Long id) {
		return service.obterPorId(id);
	}
	@PutMapping("/update")
	public Item atualizarItem(@RequestBody Item item) {
		return service.salvar(item);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg excluirItem(@PathVariable Long id) {
		return service.excluir(id);
	}
}
