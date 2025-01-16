package br.com.mailton.api_boleto.dto;

import br.com.mailton.api_boleto.entity.enums.SituationTicket;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {

    private String barcode;

    private SituationTicket situationTicket;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
