package com.sistema.cadastro.produto.crudProduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Categoria;
import com.sistema.cadastro.produto.crudProduto.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	Produto findByid(Long id);
	Produto findBynome(String nome);
	List<Produto> findBycategoria(Categoria categoria);
}
