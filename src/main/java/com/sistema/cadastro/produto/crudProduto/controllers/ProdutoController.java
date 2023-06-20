package com.sistema.cadastro.produto.crudProduto.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Categoria;
import com.sistema.cadastro.produto.crudProduto.models.Produto;
import com.sistema.cadastro.produto.crudProduto.service.ProdutoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	@PostMapping("/create")
	public Produto criar(
			@RequestParam("nome") String nome, 
			@RequestParam("preco") Double preco,  
			@RequestParam("descricao") String descricao,
			@RequestParam("promocao") Boolean promocao,
			@RequestParam("categoriaId") String categoria,
			@RequestPart("imagem") MultipartFile imagem
			
			) throws IOException {
		
		Categoria categori = new Categoria();
		categori.setId(Long.parseLong(categoria));
		
		Produto prod = new Produto(nome, preco, descricao, 
				Boolean.parseBoolean(promocao.toString()), categori, imagem.getOriginalFilename());
		/*ObjectMapper mapper = new ObjectMapper();
		String convertProduto = produto.toString();
		Produto produtoConvertido = mapper.readValue(convertProduto, Produto.class);*/
		
		return service.save(prod, imagem);
	}
	@GetMapping("/obter-todos")
	public List<Produto> obterProdutos(){
		return service.obterTodos();
	}
	@GetMapping("/obter/{id}")
	public Produto obterProduto(@PathVariable Long id) {
		return service.obterProduto(id);
	}
	@GetMapping("/obter/categoria/{nome}")
	public List<Produto> obterProdutosPorCategoria(@PathVariable String nome){
		return service.obterPorCategoria(nome);
	}
	@PutMapping("/update")
	public Produto atualizarProduto(
			@RequestParam("id") String id,
			@RequestParam("nome") String nome, 
			@RequestParam("preco") Double preco,  
			@RequestParam("descricao") String descricao,
			@RequestParam("promocao") Boolean promocao,
			@RequestParam("categoriaId") String categoria,
			@RequestPart("imagem") MultipartFile imagem
			) throws IOException {
		
		Categoria categori = new Categoria();
		categori.setId(Long.parseLong(categoria));
		
		Produto produto = new Produto();
		produto.setId(Long.parseLong(id));
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setDescricao(descricao);
		produto.setPromocao(Boolean.parseBoolean(promocao.toString()));
		produto.setCategoria(categori);
		produto.setUrlImagem(imagem.getOriginalFilename());
		return service.update(produto, imagem);
	}
	@DeleteMapping("/excluir/{id}")
	public Msg excluirProduto(@PathVariable Long id) {
		return service.excluir(id);
	}
}
