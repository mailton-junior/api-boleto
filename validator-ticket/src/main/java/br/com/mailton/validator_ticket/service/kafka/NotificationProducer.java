package br.com.mailton.validator_ticket.service.kafka;

import br.com.mailton.avro.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Value("${spring.kafka.topico-notificacao}")
    public String topico;

    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Ticket> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Ticket ticket) {
        this.kafkaTemplate.send(topico, ticket);
    }
}
