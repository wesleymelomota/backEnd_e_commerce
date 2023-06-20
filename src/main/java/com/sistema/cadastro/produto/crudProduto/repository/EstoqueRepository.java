package com.sistema.cadastro.produto.crudProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.EstoqueProduto;

public interface EstoqueRepository extends JpaRepository<EstoqueProduto, Long>{

}
