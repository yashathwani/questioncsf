package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Ticket;

public class TicketRepositoryImp implements TicketRepository {
  Map<Long,Ticket> tmap;
  long id=0;
  TicketRepositoryImp()
  {
      this.tmap=new HashMap<>();
  }
    @Override
    public Ticket save(Ticket ticket) {
        if(ticket.getId()==0)
        {
            ticket.setId(++id);
        }
        tmap.put(ticket.getId(),ticket);
       return ticket;   
    }

    @Override
    public Optional<Ticket> getTicketById(long ticketId) {
       if(tmap.containsKey(ticketId))
       {
            return Optional.of(tmap.get(ticketId));
       }
       else
       {
           return Optional.empty();
       }
    }
    
}
