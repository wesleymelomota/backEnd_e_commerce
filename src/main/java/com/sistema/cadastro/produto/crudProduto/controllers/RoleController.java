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
import com.sistema.cadastro.produto.crudProduto.models.Role;
import com.sistema.cadastro.produto.crudProduto.service.RoleService;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService service;
	
	@PostMapping("/create")
	public Role create(@RequestBody Role role) {
		return service.salvar(role);
	}
	@GetMapping("/obter-todos")
	public List<Role> obterTodos(){
		return service.obterRoles();
	}
	@GetMapping("/obter/{id}")
	public Optional<Role> obterRole(@PathVariable Integer id){
		return service.obterRole(id);
	}
	@PutMapping("/update")
	public Role atualizarRole(@RequestBody Role role) {
		return service.salvar(role);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg excluirRole(@PathVariable Integer id) {
		 return service.excluir(id);
	}
}
