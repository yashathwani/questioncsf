package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Ticket;

@Repository
public class TicketRepositoryImp implements TicketRepository {
   HashMap<Long,Ticket> tickets=new HashMap<>();
   private Long id=1L;
    @Override
    public Ticket save(Ticket ticket) {
        ticket.setId(id);
          tickets.put(id,ticket);
         
          id++;
        return ticket;
    }

    @Override
    public Optional<Ticket> getTicketById(long ticketId) {
       if(tickets.containsKey(ticketId))
       {
           return Optional.of(tickets.get(ticketId));
       }
       else
       {
           return Optional.of(null);
       }
    }
    
}
