package br.com.mailton.validator_ticket.entity;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Value("${spring.kafka.topico-notificacao}")
    public String notificacaoTopico;

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(this.notificacaoTopico).build();
    }
}
