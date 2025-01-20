package br.com.mailton.validator_ticket.mapper;

import br.com.mailton.avro.Ticket;
import br.com.mailton.validator_ticket.entity.TicketEntity;
import br.com.mailton.validator_ticket.entity.enums.SituationTicket;

public class TicketMapper {

    public static TicketEntity toEntity(Ticket ticket) {
        return TicketEntity.builder()
                .barcode(ticket.getBarcode().toString())
                .situationTicket(SituationTicket.values()[ticket.getSituationTicket()])
                .build();
    }

    public static Ticket toAvro(TicketEntity ticket) {
        return Ticket.newBuilder()
                .setBarcode(ticket.getBarcode())
                .setSituationTicket(ticket.getSituationTicket().ordinal()).build();
    }
}
