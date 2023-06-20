package com.sistema.cadastro.produto.crudProduto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sistema.cadastro.produto.crudProduto.security.FilterJwt;


@Configuration
@EnableWebSecurity
public class WebConfig {
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.cors().and().csrf().disable()
		.addFilterBefore(new FilterJwt(), UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests()
		.requestMatchers("/").permitAll()
		.requestMatchers(HttpMethod.POST, "/usuario/create").permitAll()
		.requestMatchers(HttpMethod.POST, "/send-email").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.GET, "/usuario/obter-todos").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/usuario/obter/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.PUT, "/usuario/add/role/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/usuario/add/cliente").hasAnyAuthority("USER")
		.requestMatchers(HttpMethod.PUT, "/usuario/update").hasAnyAuthority("USER", "ADMIN")
		.requestMatchers(HttpMethod.PUT, "/usuario/update/password").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.DELETE, "/usuario/excluir/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.POST, "/produto/create").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/produto/obter/categoria/{nome}").permitAll()
		.requestMatchers(HttpMethod.GET, "/produto/obter-todos").permitAll()
		.requestMatchers(HttpMethod.PUT, "/produto/update").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/produto/excluir/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/produto/obter/{id}").permitAll()
		.requestMatchers(HttpMethod.POST, "/categoria/create").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/categoria/obter-todos").permitAll()
		.requestMatchers(HttpMethod.GET, "/categoria/obter/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.DELETE, "/categoria/excluir/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/categoria/update").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/role/create").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/role/obter-todos").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/pedido/create").hasAnyAuthority("USER")
		.requestMatchers(HttpMethod.GET, "/pedido/obter-todos").hasAnyAuthority("USER", "ADMIN")
		.requestMatchers(HttpMethod.GET, "/pedido/obter/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.GET, "/pedido/obter/cliente/{id}").permitAll()
		.requestMatchers(HttpMethod.PUT, "/pedido/update").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.PUT, "/pedido/add/item/{id}").hasAnyAuthority("ADMIN", "USER")//
		.requestMatchers(HttpMethod.PUT, "/pedido/clear-items/{idPedido}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.DELETE, "/pedido/excluir/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.POST, "/item/create").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.GET, "/item/obter-todos").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.GET, "/item/obter/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.DELETE, "/item/excluir/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.PUT, "/item/update").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.POST, "/cliente/create").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.GET, "/cliente/obter-todos").hasAnyAuthority("ADMIN")//
		.requestMatchers(HttpMethod.GET, "/cliente/obter/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.GET, "/cliente/obter/pedido/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.PUT, "/cliente/update").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.DELETE, "/cliente/excluir/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.DELETE, "/cliente/excluir/pedido/{id}").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	@Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
}
