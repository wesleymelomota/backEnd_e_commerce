package com.sistema.cadastro.produto.crudProduto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.dtos.UpdatePassword;
import com.sistema.cadastro.produto.crudProduto.dtos.UserDto;
import com.sistema.cadastro.produto.crudProduto.models.Cliente;
import com.sistema.cadastro.produto.crudProduto.models.Role;
import com.sistema.cadastro.produto.crudProduto.models.Usuario;
import com.sistema.cadastro.produto.crudProduto.repository.ClienteRepository;
import com.sistema.cadastro.produto.crudProduto.repository.RoleRepository;
import com.sistema.cadastro.produto.crudProduto.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private ClienteRepository clienteRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	public UserDto salvar(Usuario user) {
		Usuario usuario = new Usuario();
		UserDto userDto = new UserDto();
		Role role = roleRepo.findBynome("USER");
		String pass = user.getPassword();
		usuario.setEmail(user.getEmail());
		usuario.setName(user.getName());
		usuario.setUsername(user.getUsername());
		usuario.setPassword(encoder.encode(pass));
		usuario.setRole(role);
		if(user.getCliente().getId() != null) {
			usuario.setCliente(user.getCliente());
		}
		
		repository.save(usuario);
		return userDto.convert(user);
	}
	
	public Msg updateSenhaUser(UpdatePassword updatePass) {
		Usuario user = repository.findByusername(updatePass.getUsername());
		Msg msg = new Msg();
		if(user != null){
			String currentPass = user.getPassword();
			Boolean checkPass = encoder.matches(updatePass.getCurrentPassword(), currentPass);
			if(!checkPass) {
				msg.setMensagem("As senhas não confere");
				return msg;
			}
			user.setPassword(encoder.encode(updatePass.getNewPassword()));
			repository.save(user);
			msg.setMensagem("Senha alterada com sucesso");
			return msg;
		}
		msg.setMensagem("Usuario inexistente!");
		return msg;
	}
	//atualiza alguns atributos como nome, email etc. não atualiza senha
	public UserDto updateUser(Usuario usuario) {
		UserDto userDto = new UserDto();
		Boolean existId = repository.existsById(usuario.getId());
		if(!existId) {
			return null;
		}
		Usuario user = repository.findByid(usuario.getId());
		
		user.setUsername(usuario.getUsername());
		user.setName(usuario.getName());
		user.setEmail(usuario.getEmail());
		user.setCliente(usuario.getCliente());
		usuario.getCliente().getPedidos().stream().forEach(obj -> obj.getId());
		repository.save(user);
		return userDto.convert(user);
	}
	public UserDto addRole(Long id, Role role) {
		Usuario user = repository.findByid(id);
		UserDto userDto = new UserDto();
		if(user != null) {
			user.setRole(role);
			repository.save(user);
			return userDto.convert(user);
		}
		return null;
	}
	public UserDto obterUsuario(Long id) {
		Boolean existId = repository.existsById(id);
		UserDto userDto = new UserDto();
		if(!existId) {
			return null;
		}
		Usuario user = repository.findByid(id);
		return userDto.convert(user);
	}
	public Msg excluir(Long id) {
		Msg msg = new Msg();
		msg.setMensagem("Usuario de id " + id + " Excluido com sucesso");
		Boolean existId = repository.existsById(id);
		if(existId) {
			repository.deleteById(id);
			return msg;
			
		}else {
			msg.setMensagem("Usuário de id " + id + " Não encontrado");
			return msg;
		}
	}
	public List<UserDto> obterUsuarios(){
		UserDto userDto = new UserDto();
		return userDto.convertList(repository.findAll());
	} 
	public UserDto addClienteInUser(Usuario usuario) {
		UserDto userDto = new UserDto();
		Usuario user = repository.findByid(usuario.getId());
		Cliente cliente = clienteRepository.findByid(usuario.getCliente().getId());
		user.setCliente(cliente);
		return userDto.convert(repository.save(user));
	}
}
