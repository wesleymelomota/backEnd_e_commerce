package com.sistema.cadastro.produto.crudProduto.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long>{

}
