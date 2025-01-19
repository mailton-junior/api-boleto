package br.com.mailton.api_boleto.repository;

import br.com.mailton.api_boleto.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    Optional<TicketEntity> findTicketByBarcode(String barcode);
}
