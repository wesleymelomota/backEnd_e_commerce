package com.sistema.cadastro.produto.crudProduto.controllers;

import java.util.List;

import java.util.Optional;

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
import com.sistema.cadastro.produto.crudProduto.models.Categoria;
import com.sistema.cadastro.produto.crudProduto.service.CategoriaService;

@CrossOrigin
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	@PostMapping("/create")
	public Categoria criar(@RequestBody Categoria categoria) {
		service.salvar(categoria);
		return categoria;
	}
	@GetMapping("/obter-todos")
	public List<Categoria> obterTodos(){
		return service.obterCategorias();
	}
	@GetMapping("/obter/{id}")
	public Optional<Categoria> obterCategoria(@PathVariable Long id) {
		return service.obterPorId(id);
	}
	@PutMapping("/update")
	public Categoria atualizarCategoria(@RequestBody Categoria categoria) {
		return service.salvar(categoria);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg excluirCategoria(@PathVariable Long id) {
		return service.excluir(id);
	}
}
