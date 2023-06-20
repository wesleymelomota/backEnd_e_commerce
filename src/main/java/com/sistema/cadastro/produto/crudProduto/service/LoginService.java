package com.sistema.cadastro.produto.crudProduto.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.config.ConfigSecurity;
import com.sistema.cadastro.produto.crudProduto.dtos.LoginDto;
import com.sistema.cadastro.produto.crudProduto.dtos.Sessao;
import com.sistema.cadastro.produto.crudProduto.models.Usuario;
import com.sistema.cadastro.produto.crudProduto.repository.UserRepository;
import com.sistema.cadastro.produto.crudProduto.security.JwtCreator;
import com.sistema.cadastro.produto.crudProduto.security.JwtToken;

@Service
public class LoginService {
	@Autowired
	private UserRepository repoUser;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public Sessao checarUser(LoginDto login) {
		Usuario usuario = repoUser.findByusername(login.getUsername());
		if(usuario != null) {
			Boolean checkPass = encoder.matches(login.getPassword(), usuario.getPassword());
			
			if(!checkPass) {
				Sessao sessao = new Sessao();
				//throw new RuntimeException("Erro! senha ou usuario invalido");
				
				sessao.setMensage("Erro! senha ou usuario invalido");
				return sessao;
			}
			Sessao sessao = new Sessao();
			sessao.setUsername(usuario.getUsername());
			JwtToken token = new JwtToken();
			
			token.setExpiration(new Date(System.currentTimeMillis() + ConfigSecurity.EXPIRATION));
			token.setIssuedAT(new Date(System.currentTimeMillis()));
			token.setRoles(usuario.getRole().getNome());
			token.setSubject(usuario.getUsername());
			sessao.setToken(JwtCreator.create(ConfigSecurity.PREFIX, ConfigSecurity.getKey(), token));
			sessao.setPerfil(usuario.getRole().getNome());
			sessao.setId(usuario.getId());
			sessao.setMensage("Autenticado!");
			return sessao;
		}else {
			Sessao sessao = new Sessao();
			sessao.setMensage("ERRO! Usario não encontrado");
			//throw new RuntimeException("ERRO! Usario não encontrado");
			return sessao;
		}
	}
} 
