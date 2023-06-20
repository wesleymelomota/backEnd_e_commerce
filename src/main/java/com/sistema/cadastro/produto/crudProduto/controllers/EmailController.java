package com.sistema.cadastro.produto.crudProduto.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.cadastro.produto.crudProduto.dtos.EmailDto;
import com.sistema.cadastro.produto.crudProduto.models.EmailModel;
import com.sistema.cadastro.produto.crudProduto.service.EmailService;

@CrossOrigin
@RestController
public class EmailController {
	@Autowired
    private EmailService service;
	
	@PostMapping("/send-email")
	public ResponseEntity< EmailModel> sendEmail(@RequestBody EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		service.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}
}
