package br.com.mailton.api_boleto.service;

import br.com.mailton.api_boleto.dto.TicketDTO;
import br.com.mailton.api_boleto.entity.TicketEntity;
import br.com.mailton.api_boleto.entity.enums.SituationTicket;
import br.com.mailton.api_boleto.entity.mapper.TicketMapper;
import br.com.mailton.api_boleto.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketDTO save(String barcode) {
        Optional<TicketEntity> ticketOptional = ticketRepository.findTicketByBarcode(barcode);
        if (ticketOptional.isPresent()) {
            throw new RuntimeException("Já existe uma solicitação de pagamento para este boleto");
        }

        TicketEntity ticketEntity = TicketEntity.builder()
                .barcode(barcode)
                .situationTicket(SituationTicket.INICIALIZADO)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        ticketRepository.save(ticketEntity);
        return TicketMapper.toDTO(ticketEntity);
    }


}
