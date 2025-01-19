package br.com.mailton.api_boleto.entity.mapper;

import br.com.mailton.api_boleto.dto.TicketDTO;
import br.com.mailton.api_boleto.entity.TicketEntity;
import br.com.mailton.avro.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(TicketEntity ticket) {
        return TicketDTO.builder()
                .barcode(ticket.getBarcode())
                .situationTicket(ticket.getSituationTicket())
                .createDate(ticket.getCreateDate())
                .updateDate(ticket.getUpdateDate())
                .build();
    }

    public static Ticket toAvroO(TicketEntity ticket) {
        return Ticket.newBuilder()
                .setBarcode(ticket.getBarcode())
                .setSituationTicket(ticket.getSituationTicket().ordinal())
                .build();
    }
}
