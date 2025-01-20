package br.com.mailton.api_boleto.service.kafka;

import br.com.mailton.avro.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TicketProducer {

    @Value("${spring.kafka.topico-boleto}")
    public String topico;
    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    public TicketProducer(KafkaTemplate<String, Ticket> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Ticket ticket) {
        kafkaTemplate.send(topico, "2" ,ticket);
    }

    private String getKey(Ticket ticket) {
        if (ticket.getBarcode().toString().substring(0,1).equals("2")) {
            return "chave1";
        }
        return "chave2";
    }

}
