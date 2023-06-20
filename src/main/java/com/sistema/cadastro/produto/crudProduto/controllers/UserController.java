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
import com.sistema.cadastro.produto.crudProduto.dtos.UpdatePassword;
import com.sistema.cadastro.produto.crudProduto.dtos.UserDto;
import com.sistema.cadastro.produto.crudProduto.models.Role;
import com.sistema.cadastro.produto.crudProduto.models.Usuario;
import com.sistema.cadastro.produto.crudProduto.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/create")
	public UserDto create(@RequestBody Usuario user) {
		return service.salvar(user);
	}
	@GetMapping("/obter-todos")
	public List<UserDto> obterTodos(){
		return service.obterUsuarios();
	}
	@GetMapping("/obter/{id}")
	public UserDto obterUsuario(@PathVariable Long id) {
		return service.obterUsuario(id);
	}
	@PutMapping("/update")
	public UserDto updadteUser(@RequestBody Usuario user) {
		return service.updateUser(user);
	}
	@PutMapping("/update/password")
	public Msg updatePassword(@RequestBody UpdatePassword updatePass) {
		return service.updateSenhaUser(updatePass);
	}
	
	@PutMapping("/add/role/{id}")
	public UserDto addRole(@PathVariable Long id, @RequestBody Role role) {
		return service.addRole(id, role);
	}
	@PutMapping("add/cliente")
	public UserDto addCliente(@RequestBody Usuario usuario) {
		return service.addClienteInUser(usuario);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg excluirUser(@PathVariable Long id) {
		
		return service.excluir(id);
	}
}
