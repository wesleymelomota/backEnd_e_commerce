package com.sistema.cadastro.produto.crudProduto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Item;
import com.sistema.cadastro.produto.crudProduto.models.Produto;
import com.sistema.cadastro.produto.crudProduto.repository.ItemRepository;
import com.sistema.cadastro.produto.crudProduto.repository.ProdutoRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository repository; 
	@Autowired
	private ProdutoRepository reposito;
	
	
	public Item salvar(Item item) {
		
		Produto produto = reposito.findByid(item.getProduto().getId());
		if(item.getId() == null) {
			Item newItem = new Item(item.getQuantidade(), produto.getPreco(), produto);
			return repository.save(newItem);
		}else {
			item.setProduto(produto);
			item.setPreco(produto.getPreco());
			return repository.save(item);
		}
		
	}
	public List<Item> obterTodos(){
		return repository.findAll();
	}
	public Item obterPorId(Long id) {
		Boolean existId = repository.existsById(id);
		if(existId) {
			return repository.findByid(id);
		}else {
			return null;
		}
	}
	public Msg excluir(Long id) {
		Boolean existId = repository.existsById(id);
		Msg msg = new Msg();
		if(existId) {
			repository.deleteById(id);
			msg.setMensagem("Item de id " + id + " Excluido com sucesso");
			return msg;
		}else {
			msg.setMensagem("Item de id " + id + " não encontrado");
			return msg;
		}
	}
}
