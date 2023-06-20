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
import com.sistema.cadastro.produto.crudProduto.models.Item;
import com.sistema.cadastro.produto.crudProduto.models.Pedido;
import com.sistema.cadastro.produto.crudProduto.service.PedidoService;

@CrossOrigin
@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private PedidoService service;
	@PostMapping("/create")
	public Pedido create(@RequestBody Pedido pedido) {
		return service.salvar(pedido);
	}
	@GetMapping("/obter/{id}")
	public Pedido obterPedido(@PathVariable Long id) {
		return service.obterPorId(id);
	}
	/*@GetMapping("/obter/cliente/{id}")
	public Cliente getClientePedido(@PathVariable Long id) {
		return service.obterCliente(id);
	}*/
	@GetMapping("/obter-todos")
	public List<Pedido> obterPedidos(){
		return service.obterTodos();
	}
	@PutMapping("/update")
	public Pedido atualizarPedido(@RequestBody Pedido pedido) {
		return service.salvar(pedido);
	}
	@PutMapping("/add/item/{id}")
	public Pedido addItem(@PathVariable String id, @RequestBody Item item) {
		return service.addItemPedido(id, item);
	}
	@PutMapping("/clear-items/{idPedido}")
	public Msg limparItens(@PathVariable Long idPedido) {
		return service.limparItensInPedidos(idPedido);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg deletarPedido(@PathVariable Long id) {
		return service.excluir(id);
	}
}
