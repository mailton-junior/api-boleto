package br.com.mailton.api_boleto.controller;

import br.com.mailton.api_boleto.dto.TicketDTO;
import br.com.mailton.api_boleto.dto.TicketRequestDTO;
import br.com.mailton.api_boleto.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> save(@Valid @RequestBody TicketRequestDTO ticketRequestDTO) {
        TicketDTO ticket = ticketService.save(ticketRequestDTO.getBarcode());
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
