package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Vehicle;

@Repository
public class VehicleRepositoryImp implements VehicleRepository {
      HashMap<String,Vehicle> vehicles=new HashMap<>();
    @Override
    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
      if(vehicles.containsKey(registrationNumber))
       {
           return Optional.of(vehicles.get(registrationNumber));
       }
       else
       {
           return Optional.of(null);
       }
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
       vehicles.put(vehicle.getRegistrationNumber(),vehicle);
        return vehicle;
    }
    
}
