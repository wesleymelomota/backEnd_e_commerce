package com.sistema.cadastro.produto.crudProduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findBynome(String nome);
	Role findByid(Integer id);
}
