package com.scaler.parking_lot.strategies.assignment;

import java.util.List;
import java.util.Optional;

import com.scaler.parking_lot.models.FloorStatus;
import com.scaler.parking_lot.models.ParkingFloor;
import com.scaler.parking_lot.models.ParkingLot;
import com.scaler.parking_lot.models.ParkingSpot;
import com.scaler.parking_lot.models.ParkingSpotStatus;
import com.scaler.parking_lot.models.VehicleType;

public class SpotAssignmentStrategyImp implements SpotAssignmentStrategy{

    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        List<ParkingFloor> floors = parkingLot.getParkingFloors();
            
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
                    spot.getSupportedVehicleType() ==vehicleType) {
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
        return Optional.of(selectedSpot);
    
    }
    
    
}
