package com.scaler.parking_lot.respositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Ticket;

@Repository
public class TicketRepositoryImp implements TicketRepository {

    @Override
    public Ticket save(Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Ticket> getTicketById(long ticketId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTicketById'");
    }
    
}
