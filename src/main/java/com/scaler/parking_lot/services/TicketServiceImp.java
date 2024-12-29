package com.scaler.parking_lot.services;

import java.util.Optional;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.exceptions.ParkingSpotNotAvailableException;
import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.GateType;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.models.Vehicle;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.ParkingLotRepository;
import com.scaler.parking_lot.respositories.TicketRepository;
import com.scaler.parking_lot.respositories.VehicleRepository;

public class TicketServiceImp implements TicketService{
    TicketRepository tr;
    ParkingLotRepository pr;
    GateRepository gr;
    VehicleRepository vr;
    TicketServiceImp(TicketRepository tr,ParkingLotRepository pr,GateRepository gr,VehicleRepository vr)
    {
      this.tr=tr;
      this.pr=pr;
      this.gr=gr;
      this.vr=vr;
    }
    @Override
    public Ticket generateTicket(int gateId, String registrationNumber, String vehicleType)
            throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException {
                Optional<Gate> g=gr.findById(gateId);
                   Gate g1= g.get();
                   if(g1.getType()==GateType.EXIT)
                   {
                       throw new InvalidGateException("invalid gate for the ticket");
                   }
                   Optional<Vehicle> v=vr.getVehicleByRegistrationNumber(registrationNumber);
                   if(v.isEmpty())
                   {
                       Vehicle v1=new Vehicle();
                       v1.setRegistrationNumber(registrationNumber);
                       v1.setVehicleType();
                   }
                
                

        
    }
    
}
