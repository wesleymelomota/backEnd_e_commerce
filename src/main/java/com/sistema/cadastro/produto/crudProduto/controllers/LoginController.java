package com.sistema.cadastro.produto.crudProduto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.cadastro.produto.crudProduto.dtos.LoginDto;
import com.sistema.cadastro.produto.crudProduto.dtos.Sessao;
import com.sistema.cadastro.produto.crudProduto.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService service;
	
	@PostMapping
	public Sessao logar(@RequestBody LoginDto login) {
		return service.checarUser(login);
	}
}
