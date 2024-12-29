package com.scaler.parking_lot.controllers;

import com.scaler.parking_lot.dtos.GenerateTicketRequestDto;
import com.scaler.parking_lot.dtos.GenerateTicketResponseDto;
import com.scaler.parking_lot.dtos.ResponseStatus;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.services.TicketService;
import com.scaler.parking_lot.services.TicketServiceImp;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        try{
            Ticket ticket = ticketService.generateTicket(requestDto.getGateId(),requestDto.getRegistrationNumber(),requestDto.getVehicleType());
            GenerateTicketResponseDto responseDto=new GenerateTicketResponseDto();
        GenerateTicketResponseDto response=responseDto.from(ticket);
        return response;
        }
        catch(Exception e){
            return null;
        }
        
    }
}
