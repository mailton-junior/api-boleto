package br.com.mailton.validator_ticket.service;

import br.com.mailton.validator_ticket.entity.TicketEntity;
import br.com.mailton.validator_ticket.entity.enums.SituationTicket;
import br.com.mailton.validator_ticket.mapper.TicketMapper;
import br.com.mailton.validator_ticket.repository.TicketRepository;
import br.com.mailton.validator_ticket.service.kafka.NotificationProducer;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PayTicketService {

    private final TicketRepository ticketRepository;
    private final NotificationProducer notificationProducer;

    public PayTicketService(TicketRepository ticketRepository, NotificationProducer notificationProducer) {
        this.ticketRepository = ticketRepository;
        this.notificationProducer = notificationProducer;
    }


    @SneakyThrows
    public void pagar(TicketEntity ticket) {
        Thread.sleep(10000);
        String codigoBarrasNumeros = ticket.getBarcode().replaceAll("[^0-9]", "");
        if (codigoBarrasNumeros.length() > 47) {
            complementTicketErro(ticket);
        } else {
            complementTicketSuccess(ticket);
        }

        ticketRepository.save(ticket);
        notificationProducer.sendMessage(TicketMapper.toAvro(ticket));
    }

    private void complementTicketErro(TicketEntity ticket) {
        ticket.setUpdateDate(LocalDateTime.now());
        ticket.setSituationTicket(SituationTicket.ERRO_PAGAMENTO);
    }

    private void complementTicketSuccess(TicketEntity ticket) {
        ticket.setUpdateDate(LocalDateTime.now());
        ticket.setSituationTicket(SituationTicket.PAGO);
    }


}
