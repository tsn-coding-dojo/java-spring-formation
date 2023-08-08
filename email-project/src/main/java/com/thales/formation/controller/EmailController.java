package com.thales.formation.controller;

import com.thales.formation.dto.EmailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public void send(@RequestBody(required = true) EmailDto emailDto) {
		LOGGER.info("Envoi de email :"
				+ "\nDestinataire : " + emailDto.getTo()
				+ "\nContenu : " + emailDto.getContent());
	}

}
