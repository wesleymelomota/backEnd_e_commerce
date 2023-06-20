package com.sistema.cadastro.produto.crudProduto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Role;
import com.sistema.cadastro.produto.crudProduto.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public Role salvar(Role role) {
		return repository.save(role);
	}
	public List<Role> obterRoles(){
		return repository.findAll();
	}
	public Optional<Role> obterRole(Integer id) {
		Boolean existId = repository.existsById(id);
		if(!existId) {
			return null;
		}
		return repository.findById(id);
	}
	public Msg excluir(Integer id) {
		Boolean existId = repository.existsById(id);
		Msg msg = new Msg();
		if(!existId) {
			msg.setMensagem("Role de id " + id + " não encontrado");
			return msg;
		}
		repository.deleteById(id);
		msg.setMensagem("Role de id " + id + " excluido");
		return msg;
	}
}
