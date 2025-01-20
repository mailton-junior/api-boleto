package br.com.mailton.validator_ticket.service;

import br.com.mailton.validator_ticket.entity.TicketEntity;
import br.com.mailton.validator_ticket.entity.enums.SituationTicket;
import br.com.mailton.validator_ticket.mapper.TicketMapper;
import br.com.mailton.validator_ticket.repository.TicketRepository;
import br.com.mailton.validator_ticket.service.kafka.NotificationProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidateTicketService {

    private final TicketRepository ticketRepository;
    private final NotificationProducer notificationProducer;
    private final PayTicketService payTicketService;

    public ValidateTicketService(TicketRepository ticketRepository, NotificationProducer notificationProducer, PayTicketService payTicketService) {
        this.ticketRepository = ticketRepository;
        this.notificationProducer = notificationProducer;
        this.payTicketService = payTicketService;
    }

    public void validate(TicketEntity ticket) {
        int code = Integer.parseInt(ticket.getBarcode().substring(0, 1));
        if (code % 2 == 0) {
            complementTicketErro(ticket);
            ticketRepository.save(ticket);
            notificationProducer.sendMessage(TicketMapper.toAvro(ticket));
        } else {
            complementTicketSuccess(ticket);
            ticketRepository.save(ticket);
            notificationProducer.sendMessage(TicketMapper.toAvro(ticket));
            payTicketService.pagar(ticket);
        }

    }

    private void complementTicketErro(TicketEntity ticket) {
        ticket.setCreateDate(LocalDateTime.now());
        ticket.setUpdateDate(LocalDateTime.now());
        ticket.setSituationTicket(SituationTicket.ERRO_VALIDACAO);
    }

    private void complementTicketSuccess(TicketEntity ticket) {
        ticket.setCreateDate(LocalDateTime.now());
        ticket.setUpdateDate(LocalDateTime.now());
        ticket.setSituationTicket(SituationTicket.VALIDADO);
    }
}
