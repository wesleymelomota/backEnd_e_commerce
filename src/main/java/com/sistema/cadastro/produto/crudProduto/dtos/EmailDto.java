package com.sistema.cadastro.produto.crudProduto.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDto {
	@NotBlank
	private String ownerRef;// referencia do cliente que vai receber id do cliente.
	@NotBlank
	@Email
	private String emailFrom; //email de
	@NotBlank
	@Email
	private String emailTo; //email para
	@NotBlank
	private String subject; //assunto
	@NotBlank
	private String text;
	/*@NotBlank
	private LocalDateTime sendDateEmail;*/
	public String getOwnerRef() {
		return ownerRef;
	}
	public void setOwnerRef(String ownerRef) {
		this.ownerRef = ownerRef;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}

