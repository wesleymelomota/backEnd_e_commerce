package com.sistema.cadastro.produto.crudProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	Categoria findBynome(String nome);
	Boolean existsBynome(String nome);
}
