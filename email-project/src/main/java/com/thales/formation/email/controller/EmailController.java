package com.thales.formation.email.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.email.dto.EmailDto;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@RequestMapping(method = RequestMethod.POST)
	public void send(@RequestBody(required = true) EmailDto emailDto) {
		LOGGER.info("Envoi de email :"
				+ "\nDestinataire : " + emailDto.getTo()
				+ "\nContenu : " + emailDto.getContent());
	}

}
