package com.sistema.cadastro.produto.crudProduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	List<Item> findAllByid(Object object);
	Item findByid(Long id);
}
