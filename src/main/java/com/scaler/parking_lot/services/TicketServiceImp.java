package com.scaler.parking_lot.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.exceptions.ParkingSpotNotAvailableException;
import com.scaler.parking_lot.models.FloorStatus;
import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.GateType;
import com.scaler.parking_lot.models.ParkingFloor;
import com.scaler.parking_lot.models.ParkingLot;
import com.scaler.parking_lot.models.ParkingSpot;
import com.scaler.parking_lot.models.ParkingSpotStatus;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.models.Vehicle;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.GateRepositoryImp;
import com.scaler.parking_lot.respositories.ParkingLotRepository;
import com.scaler.parking_lot.respositories.VehicleRepository;
import com.scaler.parking_lot.respositories.VehicleRepositoryImp;
import com.scaler.parking_lot.strategies.assignment.SpotAssignmentStrategy;

@Service
public class TicketServiceImp implements TicketService {
    private GateRepository gateRepo;
    private VehicleRepository vehicleRepo;
    private ParkingLotRepository parkinglotRepo;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    TicketServiceImp(){
        
    }
    TicketServiceImp(GateRepository gateRepo,VehicleRepository vehicleRepo,ParkingLotRepository parkinglotRepo,SpotAssignmentStrategy spotAssignmentStrategy){
     this.gateRepo=gateRepo;
     this.vehicleRepo=vehicleRepo;
     this.parkinglotRepo=parkinglotRepo;
     this.spotAssignmentStrategy=spotAssignmentStrategy;
    }

    @Override
    public  Ticket generateTicket(int gateId, String registrationNumber, String vehicleType)
            throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException {
                Optional<Gate> gate = gateRepo.findById(gateId);
                if (gate.isEmpty() || gate.get().getType() == GateType.EXIT) {
                    throw new InvalidGateException("Invalid gate: Either gate does not exist or it's an exit gate.");
                }
                Gate gateget = gate.get();
            
                // Create Ticket and set Gate details
                Ticket ticket = new Ticket();
                ticket.setEntryTime(new Date());
                ticket.setParkingAttendant(gateget.getParkingAttendant());
                ticket.setGate(gateget);
            
                // Validate or Create Vehicle
                Optional<Vehicle> vehicle = vehicleRepo.getVehicleByRegistrationNumber(registrationNumber);
                Vehicle v;
                try {
                    VehicleType type = VehicleType.valueOf(vehicleType);
                    if (vehicle.isEmpty()) {
                        v = new Vehicle();
                        v.setRegistrationNumber(registrationNumber);
                        v.setVehicleType(type);
                        vehicleRepo.save(v);
                    } else {
                        v = vehicle.get();
                    }
                    ticket.setVehicle(v);
                } catch (IllegalArgumentException e) {
                    throw new InvalidParkingLotException("Invalid vehicle type provided: " + vehicleType);
                }
            
                // Fetch Parking Lot and Floors
                Optional<ParkingLot> lot = parkinglotRepo.getParkingLotByGateId(gateId);
                if (lot.isEmpty()) {
                    throw new InvalidParkingLotException("Parking lot not found for the provided gate.");
                }
            
                ParkingLot lotget = lot.get();
                Optional<ParkingSpot> selectedSpot=spotAssignmentStrategy.assignSpot(lotget, v.getVehicleType());
                if (selectedSpot.isEmpty()) {
                    throw new ParkingSpotNotAvailableException("No available parking spot found for the vehicle type.");
                }
                 ParkingSpot Spot=selectedSpot.get();
                // Assign Spot to Ticket
                Spot.setStatus(ParkingSpotStatus.OCCUPIED);
                ticket.setParkingSpot(Spot);
            
                return ticket;
           
            
           
    }
    
}
