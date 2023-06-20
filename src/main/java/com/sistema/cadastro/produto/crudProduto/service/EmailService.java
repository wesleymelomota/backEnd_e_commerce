package com.sistema.cadastro.produto.crudProduto.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sistema.cadastro.produto.crudProduto.enums.StatusEmail;
import com.sistema.cadastro.produto.crudProduto.models.EmailModel;
import com.sistema.cadastro.produto.crudProduto.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository repository;
	
	@Autowired
	private JavaMailSender emailSend;
	
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSend.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		} finally {
			return repository.save(emailModel);
		}
	}
}
