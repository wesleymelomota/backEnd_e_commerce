package com.sistema.cadastro.produto.crudProduto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Categoria;
import com.sistema.cadastro.produto.crudProduto.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria salvar(Categoria categoria) {
		return repository.save(categoria);
	}
	public Optional<Categoria> obterPorId(Long id) {
		Boolean existId = repository.existsById(id);
		if(existId) {
			return repository.findById(id);			
		}else {
			return null;
		}
	}
	public List<Categoria> obterCategorias(){
		return repository.findAll();
	}
	public Msg excluir(Long id) {
		Msg msg = new Msg();
		Boolean existId = repository.existsById(id);
		if(existId) {
			repository.deleteById(id);
			msg.setMensagem("Categoria de id " + id + " Excluido com sucesso");
			return msg;
		}else {
			msg.setMensagem("Categoria de id " + id + " não encontrado");
			return msg;
		}
	}
}
