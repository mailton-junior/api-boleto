package br.com.mailton.validator_ticket.service.kafka;

import br.com.mailton.avro.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TicketConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumerTicket(Ticket ticket) {
        LOGGER.info(String.format("Consumindo mensagem -> %s", ticket));
    }
}
