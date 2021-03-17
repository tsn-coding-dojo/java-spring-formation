package com.thales.formation.repository.ext.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.thales.formation.message.EmailMessage;
import com.thales.formation.repository.ext.email.dto.EmailDto;

@Repository
public class EmailRepository {
	
	private RestTemplate restTemplate;
	
	@Autowired
	public EmailRepository(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}
	
	public void sendEmail(EmailMessage emailMessage) {
		EmailDto emailDto = new EmailDto();
		emailDto.setTo(emailMessage.getTo());
		emailDto.setContent(emailMessage.getContent());
		
		String url = "http://localhost:8090/api/emails";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<EmailDto> requestEntity = new HttpEntity<>(emailDto, headers);
		restTemplate.postForEntity(url, requestEntity, Void.class);
	}

}
