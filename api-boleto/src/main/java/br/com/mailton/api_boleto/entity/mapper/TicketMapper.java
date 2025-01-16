package br.com.mailton.api_boleto.entity.mapper;

import br.com.mailton.api_boleto.dto.TicketDTO;
import br.com.mailton.api_boleto.entity.TicketEntity;

public class TicketMapper {

    public static TicketDTO toDTO(TicketEntity ticket) {
        return TicketDTO.builder()
                .barcode(ticket.getBarcode())
                .situationTicket(ticket.getSituationTicket())
                .createDate(ticket.getCreateDate())
                .updateDate(ticket.getUpdateDate())
                .build();
    }
}
