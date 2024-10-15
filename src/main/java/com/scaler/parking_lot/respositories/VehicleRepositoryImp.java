package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Vehicle;
import com.scaler.parking_lot.models.VehicleType;

public class VehicleRepositoryImp implements VehicleRepository {
 private Map<String,VehicleType> vmap;
 long id;
 VehicleRepositoryImp()
 {
     this.vmap=new HashMap<>();
 }
    @Override
    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        if(vmap.containsKey(registrationNumber))
        {
            Vehicle vehicle=new Vehicle();
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setVehicleType(vmap.get(registrationNumber));
            return Optional.of(vehicle);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        if(vehicle.getId()==0)
        {
            vehicle.setId(++id);
           
        }
        vmap.put(vehicle.getRegistrationNumber(),vehicle.getVehicleType());
        return vehicle;
  
        
    }
    
}
