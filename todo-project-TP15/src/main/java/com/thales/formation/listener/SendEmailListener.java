package com.thales.formation.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.thales.formation.config.jms.JmsProperties;
import com.thales.formation.message.EmailMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SendEmailListener {
	@JmsListener(destination = JmsProperties.SEND_EMAIL, containerFactory = "localFactory")
    public void receiveMessage(EmailMessage email) {
		log.info("Sending email to <" + email.getTo() + "> : " + email.getBody());
    }

}
