package com.sistema.cadastro.produto.crudProduto.service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import  org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.dtos.Msg;
import com.sistema.cadastro.produto.crudProduto.models.Categoria;
import com.sistema.cadastro.produto.crudProduto.models.Produto;
import com.sistema.cadastro.produto.crudProduto.repository.CategoriaRepository;
import com.sistema.cadastro.produto.crudProduto.repository.ProdutoRepository;

@Service
public class ProdutoService {
	/**/
	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	private static String baseDir = new String("C:\\Users\\wesle\\projetos\\lojaVirtual-java\\FrontEnd_lojaVirtual\\src\\assets\\produto\\");
	
	public Produto save(Produto produto, MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path caminho = Paths.get(baseDir+file.getOriginalFilename());
			Files.write(caminho, bytes);			
		}
		produto.setUrlImagem(file.getOriginalFilename());
		
		return repository.save(produto);
	}
	public Produto update(Produto produto, MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path caminho = Paths.get(baseDir+file.getOriginalFilename());
			Files.write(caminho, bytes);			
		}
		produto.setUrlImagem(file.getOriginalFilename());
		
		return repository.save(produto);
	}
	public List<Produto> obterTodos(){
		//NumberFormat formatar = NumberFormat.getCurrencyInstance();
		return repository.findAll();
	}
	public Produto obterProduto(Long id) {
		Boolean existId = repository.existsById(id);
		if(existId) {
			return repository.findByid(id);
		}else {
			return null;
		}
	}
	public Msg excluir(Long id) {
		Boolean existId = repository.existsById(id);
		Msg msg = new Msg();
		if(existId) {
			msg.setMensagem("Produto de id " + id + " excluido com sucesso");
			repository.deleteById(id);
			return msg;
		}else {
			msg.setMensagem("Produto de id " + id + " não encontrado");
			return msg;
		}
	}
	public List<Produto> obterPorCategoria(String nome){
		Boolean check = categoriaRepository.existsBynome(nome);
		if(check) {
			Categoria categoria = categoriaRepository.findBynome(nome);
			return repository.findBycategoria(categoria);
			
		}return null;
		
	}
}
