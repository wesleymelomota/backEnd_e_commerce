package com.sistema.cadastro.produto.crudProduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.cadastro.produto.crudProduto.models.Cliente;
import com.sistema.cadastro.produto.crudProduto.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Pedido findByid(Long id);
	List<Pedido> findAllByid(Object obj);
	//Cliente findBycliente(Long id);
	//Boolean existsByclienteId(Long id);
}
