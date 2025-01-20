package br.com.mailton.validator_ticket.service.kafka;

import br.com.mailton.avro.Ticket;
import br.com.mailton.validator_ticket.mapper.TicketMapper;
import br.com.mailton.validator_ticket.service.ValidateTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TicketConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketConsumer.class);
    private final ValidateTicketService validateTicketService;

    public TicketConsumer(ValidateTicketService validateTicketService) {
        this.validateTicketService = validateTicketService;
    }

    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumerTicket(@Payload Ticket ticket, Acknowledgment acknowledgment)  {
        LOGGER.info(String.format("Consumindo mensagem -> %s", ticket));
        validateTicketService.validate(TicketMapper.toEntity(ticket));
        acknowledgment.acknowledge();
    }
}
