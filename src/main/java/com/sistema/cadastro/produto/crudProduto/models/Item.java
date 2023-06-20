package com.sistema.cadastro.produto.crudProduto.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.mapping.Join;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
     
	private Integer quantidade;
	private Double preco;
	//@JoinColumn(name = "produto_id", referencedColumnName = "id")
	@OneToOne/*(cascade = CascadeType.REMOVE, orphanRemoval = true)*/
	private Produto produto;
	
	public Item() {}
	public Item(Integer quant, Double preco, Produto produto) {
		this.quantidade = quant;
		this.preco = preco;
		this.produto = produto;
	}

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
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return  this.produto.getPreco(); //this.preco;
	}
	public void updatePreco() {
		this.preco = this.produto.getPreco();
	}
	public void setPreco(Double preco) {
		this.preco = preco; //this.produto.getPreco();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
