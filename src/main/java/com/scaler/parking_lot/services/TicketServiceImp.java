package com.scaler.parking_lot.services;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.exceptions.ParkingSpotNotAvailableException;
import com.scaler.parking_lot.models.Ticket;

public class TicketServiceImp implements TicketService{

    @Override
    public Ticket generateTicket(int gateId, String registrationNumber, String vehicleType)
            throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException {
        
    }
    
}
