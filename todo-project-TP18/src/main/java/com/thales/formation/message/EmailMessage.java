package com.thales.formation.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class EmailMessage implements Serializable {
	
	private String to;
    private String content;

    public EmailMessage() {
    }

    public EmailMessage(String to, String content) {
        this.to = to;
        this.content = content;
    }

}
