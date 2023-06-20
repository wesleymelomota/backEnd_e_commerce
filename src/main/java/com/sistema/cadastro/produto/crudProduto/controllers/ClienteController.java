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
import com.sistema.cadastro.produto.crudProduto.models.Cliente;
import com.sistema.cadastro.produto.crudProduto.models.Pedido;
import com.sistema.cadastro.produto.crudProduto.service.ClienteService;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@PostMapping("/create")
	public Cliente criarCliente(@RequestBody Cliente cliente) {
		return service.salvar(cliente);
	}
	
	@GetMapping("/obter-todos")
	public List<Cliente> obterClientes(){
		return service.obterClientes();
	}
	
	@GetMapping("/obter/pedido/{id}")
	public List<Pedido> obterPedidos(@PathVariable Long id){
		return service.obterPedidos(id);
	}
	@PutMapping("/update")
	public Cliente atualizarCliente(@RequestBody Cliente cliente) {
		return service.salvar(cliente);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg deletarCliente(@PathVariable Long id) {
		return service.excluir(id);
	}
	@DeleteMapping("/excluir/pedido/{id}")
	public Msg deletarPedidoCliente(@PathVariable Long id) {
		return service.removerPedido(id);
	}
}
