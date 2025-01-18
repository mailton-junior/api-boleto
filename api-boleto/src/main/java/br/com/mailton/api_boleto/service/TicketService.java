package br.com.mailton.api_boleto.service;

import br.com.mailton.api_boleto.controller.exception.ApplicationException;
import br.com.mailton.api_boleto.dto.TicketDTO;
import br.com.mailton.api_boleto.entity.TicketEntity;
import br.com.mailton.api_boleto.entity.enums.SituationTicket;
import br.com.mailton.api_boleto.entity.mapper.TicketMapper;
import br.com.mailton.api_boleto.repository.TicketRepository;
import br.com.mailton.api_boleto.service.kafka.TicketProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketProducer ticketProducer;

    public TicketService(TicketRepository ticketRepository, TicketProducer ticketProducer) {
        this.ticketRepository = ticketRepository;
        this.ticketProducer = ticketProducer;
    }

    public TicketDTO save(String barcode) {
        Optional<TicketEntity> ticketOptional = ticketRepository.findTicketByBarcode(barcode);
        if (ticketOptional.isPresent()) {
            throw new ApplicationException("Já existe uma solicitação de pagamento para este boleto");
        }

        TicketEntity ticketEntity = TicketEntity.builder()
                .barcode(barcode)
                .situationTicket(SituationTicket.INICIALIZADO)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        ticketRepository.save(ticketEntity);
        ticketProducer.sendMessage(TicketMapper.toAvroO(ticketEntity));
        return TicketMapper.toDTO(ticketEntity);
    }


}
