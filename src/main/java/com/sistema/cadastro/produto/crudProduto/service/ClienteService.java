package com.sistema.cadastro.produto.crudProduto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Cliente;
import com.sistema.cadastro.produto.crudProduto.models.Pedido;
import com.sistema.cadastro.produto.crudProduto.repository.ClienteRepository;
import com.sistema.cadastro.produto.crudProduto.repository.PedidoRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PedidoRepository repoPedido;
	
	
	public Cliente salvar(Cliente cliente) {
		List<Pedido> pedidos = repoPedido.findAllById(cliente.getIdPedidos(cliente.getPedidos())); 
		cliente.setPedidos(pedidos);
		return repository.save(cliente);
	}
	
	public List<Pedido> obterPedidos(Long id){
		Boolean existId = repository.existsById(id);
		if(existId) {
			Cliente cliente = repository.findByid(id);
			return cliente.getPedidos();
		}else {
			return null;
		}
	}
	public List<Cliente> obterClientes(){
		return repository.findAll();
	}
	public Cliente obterCliente(Long id) {
		Boolean existId = repository.existsById(id);
		if(existId) {
			return repository.findByid(id);			
		}else {
			return null;
		}
	}
	public Msg excluir(Long id) {
		Msg msg = new Msg();
		Boolean existId = repository.existsById(id);
		if(existId) {
			repository.deleteById(id);
			msg.setMensagem("Cliente de id " + id + " Excluido com sucesso!");
			return msg;
		}else {
			msg.setMensagem("Cliente de id " + id + " Não encontrado");
			return msg;
		}
	}
	
	public Msg removerPedido(Long idCliente) {
		Msg msg = new Msg();
		Boolean existId = repository.existsById(idCliente);
		if(existId) {
			Cliente cliente = repository.findByid(idCliente);
			//List<Long> idsPedidos = cliente.getIdPedidos();
			cliente.getPedidos().clear();
			repository.save(cliente);
			//repoPedido.deleteAllById(idsPedidos);
			msg.setMensagem("Carrinho  Limpo " );
			return msg;
		}else {
			msg.setMensagem("Cliente de id " + idCliente + " Não encontrado");
			return msg;
		}
	}
}
