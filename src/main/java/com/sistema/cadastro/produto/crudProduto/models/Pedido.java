package com.sistema.cadastro.produto.crudProduto.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dataCompra;
	private Double total;
	private Boolean status;
	
	@OneToMany( cascade = CascadeType.REMOVE, fetch = FetchType.EAGER )
	private List<Item> itens;
	/*@ManyToOne
	private Cliente cliente;*/

	public Pedido() {}
	public Pedido(LocalDateTime data, Boolean status, List<Item> itens) {
		this.dataCompra = data;
		this.status = status;
		this.itens = itens;
	}
	public List<Long> getItensIds(List<Item> itens){
		List<Long> ids = new ArrayList<>();
		itens.stream().forEach(obj -> ids.add(obj.getId()));
		return ids;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Double getTotal() {
	    Double total = 0.0;
	    for(Item obj : this.itens) {
	    	total += obj.getPreco() * obj.getQuantidade();
	    }
	    
		return formatarValor(total);
	}
	public Double formatarValor(Double total) {
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("###0.00", formatSymbols);
		String valorFormatado = df.format(total);
		return Double.parseDouble(valorFormatado);
	}

	public void setTotal(Double t) {
		this.total = t;
		
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public void addItem(Item item) {
		this.itens.add(item);
	}
	/*public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}*/
	
	
}
