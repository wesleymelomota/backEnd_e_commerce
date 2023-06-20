package com.sistema.cadastro.produto.crudProduto.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sistema.cadastro.produto.crudProduto.config.ConfigSecurity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterJwt extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader(JwtCreator.AUTHORIZATION);
		
		try {
			if(token != null && !token.isEmpty()) {
				JwtToken jwt = JwtCreator.create(token, ConfigSecurity.PREFIX, ConfigSecurity.getKey());
				List<SimpleGrantedAuthority> authorities = authorities(jwt.getRoles());
				UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
						jwt.getSubject(), 
						null ,
						authorities);
				SecurityContextHolder.getContext().setAuthentication(userToken);
			}else {
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		} catch ( ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.FORBIDDEN.value());
		}
		 
	}
	public List<SimpleGrantedAuthority> authorities(List<String> roles){
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}
}
