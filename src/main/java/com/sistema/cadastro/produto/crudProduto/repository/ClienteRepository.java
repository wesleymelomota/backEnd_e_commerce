package com.sistema.cadastro.produto.crudProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findBynome(String nome);
	Cliente findByid(Long id);
}
