package com.scaler.parking_lot.dtos;


import com.scaler.parking_lot.models.Ticket;

public class GenerateTicketResponseDto {

    private Ticket ticket;
    private ResponseStatus responseStatus;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
    public GenerateTicketResponseDto from(Ticket ticket){
        GenerateTicketResponseDto responseDto=new GenerateTicketResponseDto();
        responseDto.setTicket(ticket);
        if(ticket==null)
        {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        else
        {
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }

        return responseDto;

    }
}
