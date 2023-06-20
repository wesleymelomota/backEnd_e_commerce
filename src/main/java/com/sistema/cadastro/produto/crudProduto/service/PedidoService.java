package com.sistema.cadastro.produto.crudProduto.service;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Cliente;
import com.sistema.cadastro.produto.crudProduto.models.Item;
import com.sistema.cadastro.produto.crudProduto.models.Pedido;
import com.sistema.cadastro.produto.crudProduto.repository.ClienteRepository;
import com.sistema.cadastro.produto.crudProduto.repository.ItemRepository;
import com.sistema.cadastro.produto.crudProduto.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private ItemRepository repoItem;
	
	public Pedido salvar(Pedido pedido) {
		List<Item> itens = repoItem.findAllById(pedido.getItensIds(pedido.getItens()));
		Double total = 0.0;
		for(Item obj : itens) {
			total += obj.getPreco() * obj.getQuantidade();
		}
		pedido.setDataCompra(LocalDateTime.now());
		pedido.setTotal( total);
		pedido.setItens(itens);
		
		
		return repository.save(pedido);	
	}
	public Pedido addItemPedido(String id, Item item) {
		
		Pedido entityPedido = repository.findByid(Long.parseLong(id));
		if(item.getId() != null) {
			Boolean existItem = repoItem.existsById(item.getId());
			if(existItem) {
				Item entityItem = repoItem.findByid(item.getId());
				entityPedido.addItem(entityItem);
				return repository.save(entityPedido);
			}			
		}
		Item entityItem = repoItem.save(item);
		entityPedido.addItem(entityItem);
		return repository.save(entityPedido);
	}
	
	public List<Pedido> obterTodos(){
		return repository.findAll();
	}
	public Pedido obterPorId(Long id) {
		Boolean existId = repository.existsById(id);
		if(existId) {
			return repository.findByid(id);			
		}else {
			return null;
		}
	}
	public Msg excluir(Long id) {
		Msg msg = new Msg();
		try {
			Pedido pedido = repository.findByid(id);
			if(pedido != null) {
				repository.delete(pedido);
				msg.setMensagem("Excluido com sucesso!");
				return msg;
			}else {
				msg.setMensagem("Id não encontrado");
				return msg;
			}
		} catch (Exception e) {
			msg.setMensagem("Ocorreu um erro interno");
			return msg;
		}
		
	}
	public Msg limparItensInPedidos(Long idPedido) {
		Msg msg = new Msg();
		try {
			Boolean existById = repository.existsById(idPedido);
			if(existById) {
				Pedido pedido = repository.findByid(idPedido);
				pedido.getItens().clear();
				repository.save(pedido);
				msg.setMensagem("lista de itens excluido");
				return msg;
			}else {
				msg.setMensagem("Pedido não encontrado");
				return msg;
			}
		} catch (Exception e) {
			msg.setMensagem("Ocorreu um erro: " + e);
			return msg;
		}
	}
}
