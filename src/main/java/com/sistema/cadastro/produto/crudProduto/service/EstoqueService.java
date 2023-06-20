package com.sistema.cadastro.produto.crudProduto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.enums.StatusProd;
import com.sistema.cadastro.produto.crudProduto.models.EstoqueProduto;
import com.sistema.cadastro.produto.crudProduto.repository.EstoqueRepository;

@Service
public class EstoqueService {
	@Autowired
	private EstoqueRepository repository;
	
	public void salvar(EstoqueProduto estoque) {
		estoque.setStatus(StatusProd.DISPONIVEL);
		repository.save(estoque);
	}
	public EstoqueProduto update(EstoqueProduto estoque) {
		if(estoque.getQuantidade() == 0) {
			estoque.setStatus(StatusProd.INDISPONIVEL);
			return repository.save(estoque);
		}
		return repository.save(estoque);
	}
	public List<EstoqueProduto> obterTodosProdutosEstoque(){
		return repository.findAll();
	}
	public void deletarUmItemEstoque(Long id) {
		repository.deleteById(id);
	}
}
