package com.thales.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.thales.formation.config.jms.JmsProperties;
import com.thales.formation.message.EmailMessage;

@Service
public class EmailService {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendEmail(EmailMessage emailMessage) {
		jmsTemplate.convertAndSend(JmsProperties.SEND_EMAIL, emailMessage);
	}

}
