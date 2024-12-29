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

@Service
public class TicketServiceImp implements TicketService {
    private GateRepository gateRepo;
    private VehicleRepository vehicleRepo;
    private ParkingLotRepository parkinglotRepo;
    TicketServiceImp(GateRepository gateRepo,VehicleRepository vehicleRepo,ParkingLotRepository parkinglotRepo){
     this.gateRepo=gateRepo;
     this.vehicleRepo=vehicleRepo;
     this.parkinglotRepo=parkinglotRepo;
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
                List<ParkingFloor> floors = lotget.getParkingFloors();
            
                ParkingSpot selectedSpot = null;
                ParkingFloor selectedFloor = null;
                int minAvailableSpots = Integer.MAX_VALUE;
            
                for (ParkingFloor floor : floors) {
                    if (floor.getStatus()!=FloorStatus.OPERATIONAL) {
                        continue; // Skip non-operational floors
                    }
            
                    int availableSpots = 0;
                    ParkingSpot nearestSpot = null;
                    int nearestSpotNumber = Integer.MAX_VALUE;
            
                    for (ParkingSpot spot : floor.getSpots()) {
                        if (spot.getStatus() == ParkingSpotStatus.AVAILABLE &&
                            spot.getSupportedVehicleType() == v.getVehicleType()) {
                            availableSpots++;
                            if (spot.getNumber() < nearestSpotNumber) {
                                nearestSpotNumber = spot.getNumber();
                                nearestSpot = spot;
                            }
                        }
                    }
            
                    // Update if this floor has fewer available spots
                    if (availableSpots > 0 && availableSpots < minAvailableSpots) {
                        minAvailableSpots = availableSpots;
                        selectedFloor = floor;
                        selectedSpot = nearestSpot;
                    }
                }
            
                if (selectedSpot == null) {
                    throw new ParkingSpotNotAvailableException("No available parking spot found for the vehicle type.");
                }
            
                // Assign Spot to Ticket
                selectedSpot.setStatus(ParkingSpotStatus.OCCUPIED);
                ticket.setParkingSpot(selectedSpot);
            
                return ticket;
           
            
           
        // TODO Auto-generated method stub
    }
    
}
