package com.sistema.cadastro.produto.crudProduto.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private LocalDate dataNascimento;
	@Column(unique = true)
	private Long cpf;
	private String telefone;
	private String endereco;
	private String cep;
	
	@OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private List<Pedido> pedidos;
	
	public Cliente() {}
	
	public Cliente(String nome, LocalDate dataNascimento, Long cpf, String telefone,
			String endereco, String cep, List<Pedido> pedidos) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cep = cep;
		this.pedidos = pedidos;
	}
	
	public Long getId() {
		return id;
	}
	public void addPedido(List<Pedido> pedido) {
		this.pedidos = pedido;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getIdPedidos(List<Pedido> pedidos){
		List<Long> idList = new ArrayList<>();
		pedidos.stream().forEach(p -> idList.add(p.getId()));
		return idList;
	}
	public List<Long> getIdPedidos(){
		List<Long> idList = new ArrayList<>();
		this.pedidos.stream().forEach(p -> idList.add(p.getId()));
		return idList;
	}
	
	public List<Pedido> getPedidos() {
		List<Pedido> pedidoList = new ArrayList<>();
		this.pedidos.forEach(p -> pedidoList.add(p));
		return this.pedidos;
	}
	
	/*public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	/*public void setPedidos(Pedido pedidos) {
		this.pedidos.add(pedidos);
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
