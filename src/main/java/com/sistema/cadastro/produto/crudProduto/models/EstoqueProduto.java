package com.sistema.cadastro.produto.crudProduto.models;

import com.sistema.cadastro.produto.crudProduto.enums.StatusProd;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class EstoqueProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	@OneToOne
	private Produto produto;
	@Enumerated(EnumType.STRING)
	private StatusProd status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		if(quantidade < 0) {
			this.quantidade = 0;
		}
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public StatusProd getStatus() {
		return status;
	}
	public void setStatus(StatusProd status) {
		this.status = status;
	}
	
	
}
