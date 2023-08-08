package com.thales.formation.repository.ext.email;

import com.thales.formation.message.EmailMessage;
import com.thales.formation.repository.ext.email.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class EmailRepository {

    private final WebClient webClient;

    @Autowired
    public EmailRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9090").build();
    }

    public void sendEmail(EmailMessage emailMessage) {
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(emailMessage.getTo());
        emailDto.setContent(emailMessage.getBody());

        this.webClient.post()
                .uri("/api/emails")
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(Mono.just(emailDto), EmailDto.class)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
