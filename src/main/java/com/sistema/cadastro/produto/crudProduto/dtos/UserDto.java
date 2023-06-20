package com.sistema.cadastro.produto.crudProduto.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sistema.cadastro.produto.crudProduto.models.Cliente;
import com.sistema.cadastro.produto.crudProduto.models.Role;
import com.sistema.cadastro.produto.crudProduto.models.Usuario;

public class UserDto {
	
	private Long id;
	private String name;
	private String username;
	private String email;
	private Role role;
	private Cliente cliente;
	
	public UserDto convert(Usuario user) {
		BeanUtils.copyProperties(user, this, "password");
		return this;
	}
	public UserDto() {}
	public UserDto(Usuario user) {
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.cliente = user.getCliente();
	}
	public List<UserDto> convertList(List<Usuario> users){
		List<UserDto> userDtoList = new ArrayList<>();
		users.forEach(obj -> userDtoList.add(new UserDto(obj)));//
		return userDtoList;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
