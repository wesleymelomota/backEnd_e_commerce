package com.sistema.cadastro.produto.crudProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByusername(String username);
	Usuario findByid(Long id);
}
