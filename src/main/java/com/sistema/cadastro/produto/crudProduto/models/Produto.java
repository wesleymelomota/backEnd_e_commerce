package com.sistema.cadastro.produto.crudProduto.models;



import java.text.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String nome;
	private Double preco;
	@Column(length = 250)
	private String descricao;
	private Boolean promocao;
	//@Lob
	private String urlImagem;
 
	
	//@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	@OneToOne/*(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.LAZY)*/
	private Categoria categoria;

	public Produto() {}
	public Produto(String nome, Double preco, String descricao, Boolean promocao, Categoria categoria, String img) {
		
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.promocao = promocao;
		this.categoria = categoria;
		this.urlImagem = img;
	}
	public Produto(Long id ,String nome, Double preco, String descricao, Boolean promocao, Categoria categoria, String img) {
		
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.promocao = promocao;
		this.categoria = categoria;
		this.urlImagem = img;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String img) {
		this.urlImagem = img;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getPraceFormat() {
		NumberFormat formatar = NumberFormat.getCurrencyInstance();
		return formatar.format(this.preco);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
